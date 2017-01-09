
package com.kakasure.bqf.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.kakasure.bqf.dao.dto.ApiCopyrightmultiDto;
import com.kakasure.bqf.dao.dto.CopyrightmultiDto;
import com.kakasure.bqf.dao.mapper.AnnouncemultiMapperExt;
import com.kakasure.bqf.dao.mapper.CopyrightmultiMapperExt;
import com.kakasure.bqf.dao.mapper.LabelsMapperExt;
import com.kakasure.bqf.dao.mapper.MessageMapperExt;
import com.kakasure.bqf.dao.mapper.MessageUserMapperExt;
import com.kakasure.bqf.dao.mapper.OrderMapperExt;
import com.kakasure.bqf.dao.mapper.UserMapperExt;
import com.kakasure.bqf.dao.mapper.VideoCategoryMapperExt;
import com.kakasure.bqf.dao.model.Announcemulti;
import com.kakasure.bqf.dao.model.AnnouncemultiExample;
import com.kakasure.bqf.dao.model.Copyrightmulti;
import com.kakasure.bqf.dao.model.CopyrightmultiExample;
import com.kakasure.bqf.dao.model.Labels;
import com.kakasure.bqf.dao.model.LabelsExample;
import com.kakasure.bqf.dao.model.Message;
import com.kakasure.bqf.dao.model.MessageUser;
import com.kakasure.bqf.dao.model.Order;
import com.kakasure.bqf.dao.model.OrderExample;
import com.kakasure.bqf.dao.model.User;
import com.kakasure.bqf.dao.model.UserExample;
import com.kakasure.bqf.dao.model.VideoCategory;
import com.kakasure.bqf.dao.model.VideoCategoryExample;
import com.kakasure.bqf.service.CopyrightMultiService;
import com.kakasure.bqf.util.NginxUtil;
import com.kakasure.bqf.util.qiniu.QiniuData;
import com.kakasure.bqf.util.qiniu.QiniuManager;
import com.kakasure.common.util.EmojiFilterUtil;
import com.kakasure.common.util.HttpRequestAndResultUtil;
import com.kakasure.common.util.KksKeyUtil;
import com.kakasure.exception.BusinessException;
import com.kakasure.exception.ValidateException;
import com.kakasure.lang.PropertyManager;
import com.kakasure.lang.util.Page;
import com.kakasure.lang.util.UrlUtil;
import com.kakasure.lang.util.UuidUtil;
import com.kakasure.lang.util.VerifyReg;
import com.kakasure.lang.util.pay.MD5Signature;
import com.kakasure.lang.util.pay.ParameterUtil;
import com.kakasure.service.BaseService;

@Service
public class CopyrightMultiServiceImpl extends BaseService
										implements CopyrightMultiService {
	@Resource
	private PropertyManager			propertyManager;
	@Resource
	private CopyrightmultiMapperExt	copyrightmultiMapperExt;
	@Resource
	private OrderMapperExt			orderMapperExt;
	@Resource
	private MessageMapperExt		messageMapperExt;
	@Resource
	private MessageUserMapperExt	messageUserMapperExt;
	@Resource
	private UserMapperExt			userMapperExt;
	@Resource
	private VideoCategoryMapperExt	videoCategoryMapperExt;
	@Resource
	private LabelsMapperExt			labelsMapperExt;
	@Resource
	private AnnouncemultiMapperExt	announcemultiMapperExt;

	@Override
	public List<CopyrightmultiDto> getCopyrightmultiList(Page<CopyrightmultiDto> page, CopyrightmultiDto copyrightmultiDto) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("userId", copyrightmultiDto.getUserId());
		params.put("payType", copyrightmultiDto.getPayType());
		params.put("type", copyrightmultiDto.getType());
		params.put("mediaName", EmojiFilterUtil.filterEmoji(copyrightmultiDto.getMediaName(), "*"));
		params.put("auditStatus", copyrightmultiDto.getAuditStatus());
		page.setParams(params);
		return copyrightmultiMapperExt.getCopyrightmultiList(page);
	}

	@Override
	public void deleteById(String userId, String copyrightmultiId) throws Exception {
		if (StringUtils.isBlank(copyrightmultiId)) {
			throw new ValidateException(600010);
		}
		Copyrightmulti copyrightmulti = copyrightmultiMapperExt.selectByPrimaryKey(copyrightmultiId);
		// 判断是否在有限期内
		if (!validityInterface(copyrightmultiId, userId, copyrightmulti.getPayType())) {
			throw new ValidateException(600015);
		}

		// 删除多媒体
		copyrightmulti.setIsDelete("1");
		copyrightmultiMapperExt.updateByPrimaryKeySelective(copyrightmulti);

		// 视频被修改 软删除推广信息
		Announcemulti record = new Announcemulti();
		record.setIsDelete("1");
		AnnouncemultiExample example = new AnnouncemultiExample();
		example.createCriteria().andCopyrightmultiIdEqualTo(copyrightmultiId);
		announcemultiMapperExt.updateByExampleSelective(record, example);

		savemessage(copyrightmultiId);
		if (copyrightmulti.getAuditStatus().equals("00")) {
			// 调奇码网接口 删主题关联的视频
			Map<String, String> param = new HashMap<String, String>();
			param.put("targetId", copyrightmulti.getCopyrightmultiId());
			param.put("exchange", "bqf_media_delete");
			param.put("target", "删除多媒体");
			param.put("targetStatus", "1");
			param.put("times", new Date().getTime() + "");

			printInfoLog("参数: {}", param);
			// 接口签名
			String signData = ParameterUtil.getSignData(param);
			String sign;
			try {
				sign = MD5Signature.sign(signData, KksKeyUtil.KEY_KAKASURE
														+ propertyManager.signKeySuffix);
			} catch (Exception e) {
				sign = "";
			}
			param.put("sign", sign);

			String str = UrlUtil.getStringByPost(propertyManager.urlKakasureMobileServer
													+ "/api/message/send.json", param);

			HttpRequestAndResultUtil.checkResult(str);
		}
	}

	/**
	 * 版权方修改删除30内有限期的判断
	 * 
	 * @throws Exception
	 */
	@Override
	public boolean validityInterface(String copyrightmultiId, String userId, String payType) throws Exception {
		if (StringUtils.isBlank(copyrightmultiId)) {
			return true;
		}
		if (StringUtils.isBlank(userId)) {
			throw new ValidateException(200601);
		}
		if (payType.equals("0")) {
			return true;
		}
		OrderExample orderExample = new OrderExample();
		orderExample.createCriteria().andCopyrightIdEqualTo(userId).andMediaIdEqualTo(copyrightmultiId);
		orderExample.setOrderByClause("DATE_MODIFY DESC");
		List<Order> orders = orderMapperExt.selectByExample(orderExample);
		if (!orders.isEmpty()) {
			Date now = new Date();

			Date DATE_MODIFY = orders.get(0).getDateModify();
			Date lastBuyDate = DATE_MODIFY;

			Calendar lastBuyCalendar = Calendar.getInstance();
			lastBuyCalendar.setTime(lastBuyDate);
			lastBuyCalendar.add(Calendar.DATE, 30);

			if (lastBuyCalendar.getTime().before(now)) {
				return true;
			} else {
				return false;
			}
		} else {
			return true;
		}
	}

	@Override
	public List<VideoCategory> getVideoCategory() {
		return videoCategoryMapperExt.selectByExample(new VideoCategoryExample());
	}

	@Override
	public void save(CopyrightmultiDto copyrightmultiDto) {
		if (StringUtils.isBlank(copyrightmultiDto.getVideoCategoryId())) {
			throw new ValidateException(900002);
		}
		if (StringUtils.isBlank(copyrightmultiDto.getPicturepath())) {
			throw new ValidateException(600002);
		}
		if (StringUtils.isBlank(copyrightmultiDto.getPath())) {
			throw new ValidateException(600003);
		} else {
			String path = copyrightmultiDto.getPath().toString();
			if (path.indexOf("?") > -1) {
				copyrightmultiDto.setPath(path.substring(0, path.indexOf("?")));
			}
		}
		copyrightmultiDto.setMediaName(EmojiFilterUtil.filterEmoji(copyrightmultiDto.getMediaName().replace(" ", ""), "*"));
		if (StringUtils.isBlank(copyrightmultiDto.getMediaName())) {
			throw new ValidateException(600004);
		}
		if (!VerifyReg.verifyUserName(copyrightmultiDto.getMediaName())
												|| copyrightmultiDto.getMediaName().length() > 32) {
			throw new ValidateException(600014);
		}

		if (StringUtils.isBlank(copyrightmultiDto.getKeywords())) {
			throw new ValidateException(600005);
		}
		if (copyrightmultiDto.getKeywords().length() > 64) {
			throw new ValidateException(600025);
		}
		if (copyrightmultiDto.getPayType().equals("0")) {
			if (copyrightmultiDto.getProductConcession() == null
													|| copyrightmultiDto.getProductConcession().equals("")) {
				throw new ValidateException(600008);
			}
			Boolean concessionResult = copyrightmultiDto.getProductConcession().toString().matches("^-[1-9]d*$");
			if (concessionResult == true) {
				throw new ValidateException(600008);
			}
			copyrightmultiDto.setPrice(0.0);
			copyrightmultiDto.setConcession(0.0);
		} else {
			if (copyrightmultiDto.getPrice() == null || copyrightmultiDto.getPrice().equals("")) {
				throw new ValidateException(600007);
			}
			Boolean strResult = copyrightmultiDto.getPrice().toString().matches("^-([1-9]d*.d*|0.d*[1-9]d*)$");
			if (strResult == true) {
				throw new ValidateException(600007);
			}
			copyrightmultiDto.setConcession(40.0);
			copyrightmultiDto.setProductConcession(0.0);
		}
		if (StringUtils.isBlank(copyrightmultiDto.getDescr())) {
			throw new ValidateException(600009);
		}
		if (copyrightmultiDto.getDescr().length() > 2000) {
			throw new ValidateException(600024);
		}
		String copyrightmultiId = UuidUtil.get32UUID();
		copyrightmultiDto.setCopyrightmultiId(copyrightmultiId);
		copyrightmultiDto.setDateCreate(new Date());
		copyrightmultiDto.setDateModify(new Date());

		if (StringUtils.isBlank(copyrightmultiDto.getLabelStr())) {
			throw new ValidateException(600023);
		}
		String label = replaceAll(copyrightmultiDto.getLabelStr());
		String[] labelsplic = label.split(",");
		for (String s : labelsplic) {
			Labels labels = new Labels();
			labels.setLabelId(UuidUtil.get32UUID());
			labels.setCopyrightmultiId(copyrightmultiId);
			if (s.length() > 10) {
				throw new ValidateException(600021);
			}
			labels.setLabelName(EmojiFilterUtil.filterEmoji(s, "*"));
			labels.setDateCreate(new Date());
			labels.setDateModify(new Date());
			labelsMapperExt.insertSelective(labels);
		}
		copyrightmultiDto.setKeywords(EmojiFilterUtil.filterEmoji(copyrightmultiDto.getKeywords(), "*"));
		copyrightmultiDto.setDescr(EmojiFilterUtil.filterEmoji(copyrightmultiDto.getDescr(), "*"));
		copyrightmultiMapperExt.insertSelective(copyrightmultiDto);

		// 七牛
		QiniuManager.getInstance().addQueue(new QiniuData(propertyManager.staticPath, copyrightmultiDto.getPath(), null, copyrightmultiDto.getPath()));

	}

	@Override
	public void updateMulti(CopyrightmultiDto copyrightmultiDto) throws Exception {
		if (StringUtils.isBlank(copyrightmultiDto.getCopyrightmultiId())) {
			throw new ValidateException(600010);
		}
		if (StringUtils.isBlank(copyrightmultiDto.getVideoCategoryId())) {
			new BusinessException(200044, "请选择类目！");
			throw new ValidateException(900002);
		}
		Copyrightmulti oldMulti = copyrightmultiMapperExt.selectByPrimaryKey(copyrightmultiDto.getCopyrightmultiId());
		if (oldMulti == null) {
			throw new ValidateException(200001);
		}
		if (!oldMulti.getUserId().equals(copyrightmultiDto.getUserId())) {
			throw new ValidateException(600022);
		}
		if (StringUtils.isBlank(copyrightmultiDto.getPicturepath())) {
			throw new ValidateException(600002);
		}
		if (StringUtils.isBlank(copyrightmultiDto.getPath())) {
			throw new ValidateException(600003);
		} else {
			String path = copyrightmultiDto.getPath().toString();
			if (path.indexOf("?") > -1) {
				copyrightmultiDto.setPath(path.substring(0, path.indexOf("?")));
			}
		}
		copyrightmultiDto.setMediaName(EmojiFilterUtil.filterEmoji(copyrightmultiDto.getMediaName().replace(" ", ""), "*"));
		if (StringUtils.isBlank(copyrightmultiDto.getMediaName())) {
			throw new ValidateException(600004);
		}

		if (!VerifyReg.verifyUserName(copyrightmultiDto.getMediaName())
												|| copyrightmultiDto.getMediaName().length() > 32) {
			throw new ValidateException(600014);
		}
		// 校验是否是有效期
		if (!validityInterface(copyrightmultiDto.getCopyrightmultiId(), copyrightmultiDto.getUserId(), copyrightmultiDto.getPayType())) {
			throw new ValidateException(600017);
		}
		if (StringUtils.isBlank(copyrightmultiDto.getKeywords())) {
			throw new ValidateException(600005);
		}
		if (copyrightmultiDto.getKeywords().length() > 64) {
			throw new ValidateException(600025);
		}
		if (copyrightmultiDto.getPayType().equals("0")) {
			if (copyrightmultiDto.getProductConcession() == null
													|| copyrightmultiDto.getProductConcession().equals("")) {
				throw new ValidateException(600008);
			}
			Boolean concessionResult = copyrightmultiDto.getProductConcession().toString().matches("^-[1-9]d*$");
			if (concessionResult == true) {
				throw new ValidateException(600008);
			}
			copyrightmultiDto.setPrice(0.0);
			copyrightmultiDto.setConcession(0.0);
		} else {
			if (copyrightmultiDto.getPrice() == null || copyrightmultiDto.getPrice().equals("")) {
				throw new ValidateException(600007);
			}
			Boolean strResult = copyrightmultiDto.getPrice().toString().matches("^-([1-9]d*.d*|0.d*[1-9]d*)$");
			if (strResult == true) {
				throw new ValidateException(600007);
			}
			copyrightmultiDto.setConcession(40.0);
			copyrightmultiDto.setProductConcession(0.0);
		}
		if (StringUtils.isBlank(copyrightmultiDto.getDescr())) {
			throw new ValidateException(600009);
		}
		if (copyrightmultiDto.getDescr().length() > 2000) {
			throw new ValidateException(600024);
		}
		// 判断收费还是免费
		if (copyrightmultiDto.getPayType().equals("0")) {
			copyrightmultiDto.setPrice(0.00);
			copyrightmultiDto.setStrangeConcession(0.00);
			copyrightmultiDto.setConcession(0.00);
			copyrightmultiDto.setAirProducts("0");
		} else {
			copyrightmultiDto.setStrangeConcession(20.00);
			copyrightmultiDto.setProductConcession(0.00);
			BigDecimal CONCESSION = new BigDecimal(copyrightmultiDto.getConcession());
			BigDecimal PRICE = new BigDecimal(copyrightmultiDto.getPrice());
			CONCESSION = CONCESSION.setScale(2, BigDecimal.ROUND_HALF_UP);
			PRICE = PRICE.setScale(2, BigDecimal.ROUND_HALF_UP);
			BigDecimal a = new BigDecimal(0.00);
			if (PRICE.compareTo(a) == 0) {
				throw new ValidateException(600011);
			}
			if (CONCESSION.compareTo(a) == 0) {
				throw new ValidateException(600012);
			}
		}
		copyrightmultiDto.setAuditStatus("99");
		copyrightmultiDto.setDateModify(new Date());

		// if (copyrightmultiDto.getPayType().equals("1")) {
		// AnnouncemultiExample example = new AnnouncemultiExample();
		// example.createCriteria().andCopyrightmultiIdEqualTo(copyrightmultiDto.getCopyrightmultiId());
		// announcemultiMapperExt.deleteByExample(example);
		// }

		if (StringUtils.isBlank(copyrightmultiDto.getLabelStr())) {
			throw new ValidateException(600023);
		}
		// 先删除 再添加
		LabelsExample labelsExample = new LabelsExample();
		labelsExample.createCriteria().andCopyrightmultiIdEqualTo(copyrightmultiDto.getCopyrightmultiId());
		labelsMapperExt.deleteByExample(labelsExample);
		String label = replaceAll(copyrightmultiDto.getLabelStr());
		String[] labelsplic = label.split(",");
		for (String s : labelsplic) {
			Labels labels = new Labels();
			labels.setLabelId(UuidUtil.get32UUID());
			labels.setCopyrightmultiId(copyrightmultiDto.getCopyrightmultiId());
			if (s.length() > 10) {
				throw new ValidateException(600021);
			}
			labels.setLabelName(s);
			labels.setDateCreate(new Date());
			labels.setDateModify(new Date());
			labelsMapperExt.insertSelective(labels);
		}
		// 添加消息
		savemessage(copyrightmultiDto.getCopyrightmultiId());

		copyrightmultiMapperExt.updateByPrimaryKeySelective(copyrightmultiDto);
		// 修改，且视频做了更改
		if (StringUtils.isNotBlank(oldMulti.getPath())
												&& !oldMulti.getPath().equals(copyrightmultiDto.getPath())) {
			QiniuManager.getInstance().addQueue(new QiniuData(propertyManager.staticPath, copyrightmultiDto.getPath(), oldMulti.getPath(), copyrightmultiDto.getPath()));
		}
		// 调奇码网接口 删主题关联的视频
		if (StringUtils.isNotBlank(copyrightmultiDto.getPath())) {
			if (oldMulti != null && !copyrightmultiDto.getProductConcession().equals(oldMulti.getProductConcession())) {
				// 视频被修改 软删除推广信息
				Announcemulti record = new Announcemulti();
				record.setIsDelete("1");
				AnnouncemultiExample example = new AnnouncemultiExample();
				example.createCriteria().andCopyrightmultiIdEqualTo(copyrightmultiDto.getCopyrightmultiId());
				announcemultiMapperExt.updateByExampleSelective(record, example);

				Map<String, String> param = new HashMap<String, String>();
				param.put("targetId", copyrightmultiDto.getCopyrightmultiId());
				param.put("exchange", "bqf_media_delete");
				param.put("target", "删除多媒体");
				param.put("targetStatus", "1");
				param.put("times", new Date().getTime() + "");

				printInfoLog("参数: {}", param);
				// 接口签名
				String signData = ParameterUtil.getSignData(param);
				String sign;
				try {
					sign = MD5Signature.sign(signData, KksKeyUtil.KEY_KAKASURE
															+ propertyManager.signKeySuffix);
				} catch (Exception e) {
					sign = "";
				}
				param.put("sign", sign);

				String str = UrlUtil.getStringByPost(propertyManager.urlKakasureMobileServer
														+ "/api/message/send.json", param);

				HttpRequestAndResultUtil.checkResult(str);
			}
		}
	}

	private void savemessage(String copyrightmultiId) {
		Message message = new Message();
		String MESSAGE_ID = UuidUtil.get32UUID();
		message.setMessageId(MESSAGE_ID);
		message.setMediaId(copyrightmultiId);
		message.setType("0");
		message.setContent("多媒体做了修改操作。");
		message.setDateCreate(new Date());
		message.setDateModify(new Date());
		messageMapperExt.insertSelective(message);

		// yunear增加消息记录
		MessageUser messageUser = new MessageUser();
		messageUser.setMessageuserId(UuidUtil.get32UUID());
		messageUser.setMessageId(MESSAGE_ID);
		messageUser.setStatus("0");
		messageUser.setDateCreate(new Date());
		messageUser.setDateModify(new Date());
		UserExample userExample = new UserExample();
		userExample.createCriteria().andUsernameEqualTo("yunear");
		List<User> users = userMapperExt.selectByExample(userExample);
		if (!users.isEmpty()) {
			messageUser.setUserId(users.get(0).getUserId());
		}
		messageUserMapperExt.insertSelective(messageUser);
	}

	private String replaceAll(String value) {
		String eng = ",";
		value = value.replaceAll("，", eng);
		if (value.substring(value.length() - 1).equals(",")) {
			value = value.substring(0, value.length() - 1);
		}
		return EmojiFilterUtil.filterEmoji(value);
	}

	@Override
	public CopyrightmultiDto getCopyrightmultiById(String copyrightmultiId) {
		if (StringUtils.isBlank(copyrightmultiId)) {
			throw new ValidateException(600010);
		}
		Copyrightmulti copyrightmulti = copyrightmultiMapperExt.selectByPrimaryKey(copyrightmultiId);
		ModelMapper mapper = new ModelMapper();
		CopyrightmultiDto dto = mapper.map(copyrightmulti, CopyrightmultiDto.class);
		LabelsExample example = new LabelsExample();
		example.createCriteria().andCopyrightmultiIdEqualTo(copyrightmultiId);
		List<Labels> labels = labelsMapperExt.selectByExample(example);
		String labelStr = "";
		for (Labels l : labels) {
			if (labelStr == "") {
				labelStr = l.getLabelName();
			} else {
				labelStr += "," + l.getLabelName();
			}
		}
		dto.setLabelStr(labelStr);
		dto.setPath(NginxUtil.addSecurelinkForApply(dto.getPath()));
		if (StringUtils.isNotBlank(dto.getVideoCategoryId())) {
			VideoCategory category = videoCategoryMapperExt.selectByPrimaryKey(dto.getVideoCategoryId());
			if (category != null) {
				dto.setCategoryName(category.getCategoryName());
			}
		}
		return dto;
	}

	@Override
	public List<ApiCopyrightmultiDto> getFreeVideos(String ids, Boolean isPass) {
		List<ApiCopyrightmultiDto> copyrightmultiDtos = new ArrayList<ApiCopyrightmultiDto>();
		if (StringUtils.isNotBlank(ids)) {
			String[] multIds = ids.split(",");
			List<String> copyId = new ArrayList<String>();
			for (String s : multIds) {
				copyId.add(s);
			}
			ModelMapper mapper = new ModelMapper();
			CopyrightmultiExample copyrightmultiExample = new CopyrightmultiExample();
			if (isPass == null || isPass) {
				copyrightmultiExample.createCriteria().andCopyrightmultiIdIn(copyId).andAuditStatusEqualTo("00");
			} else {
				copyrightmultiExample.createCriteria().andCopyrightmultiIdIn(copyId);
			}

			List<Copyrightmulti> copyrightmultis = copyrightmultiMapperExt.selectByExample(copyrightmultiExample);
			for (String m : multIds) {
				for (Copyrightmulti c : copyrightmultis) {
					if (m.equals(c.getCopyrightmultiId())) {
						if (!c.getType().equals("03")) {
							c.setPath(propertyManager.urlLocalServer + c.getPath());
						}
						String pricturePath = c.getPicturepath();
						if (pricturePath != null && pricturePath.indexOf("http://") == -1
																&& pricturePath.indexOf("https://") == -1) {
							pricturePath = propertyManager.urlLocalServer
																	+ pricturePath;
						}
						c.setPicturepath(pricturePath);
						ApiCopyrightmultiDto copyrightmultiDto = mapper.map(c, ApiCopyrightmultiDto.class);
						copyrightmultiDtos.add(copyrightmultiDto);
					}
				}

			}
		}
		return copyrightmultiDtos;
	}

	@Override
	public List<ApiCopyrightmultiDto> getFreeVideoList(Page<ApiCopyrightmultiDto> page) {
		List<ApiCopyrightmultiDto> copyrightmultis = copyrightmultiMapperExt.getCopyrightmultiListByKks(page);
		for (ApiCopyrightmultiDto c : copyrightmultis) {
			if (!c.getType().equals("03")) {
				c.setPath(propertyManager.urlLocalServer + c.getPath());
			}
			String pricturePath = c.getPicturepath();
			if (pricturePath != null && pricturePath.indexOf("http://") == -1
													&& pricturePath.indexOf("https://") == -1) {
				pricturePath = propertyManager.urlLocalServer + pricturePath;
			}
			c.setPicturepath(pricturePath);
		}
		return copyrightmultis;
	}

	@Override
	public void updateCode(String COPYRIGHTMULTI_ID) {
		if (StringUtils.isBlank(COPYRIGHTMULTI_ID)) {
			throw new ValidateException(600010);
		}
		copyrightmultiMapperExt.updateCode(COPYRIGHTMULTI_ID);
	}

	@Override
	public void updateClickNum(String COPYRIGHTMULTI_ID) {
		if (StringUtils.isBlank(COPYRIGHTMULTI_ID)) {
			printErrorLog("COPYRIGHTMULTI_ID 为空{}", COPYRIGHTMULTI_ID);
			throw new ValidateException(600010);
		}
		printInfoLog("添加点击次数开始", COPYRIGHTMULTI_ID);
		copyrightmultiMapperExt.updateClickNum(COPYRIGHTMULTI_ID);
	}

	@Override
	public CopyrightmultiDto findCopyrightMultiByAnnId(String announcemultiId) {
		CopyrightmultiDto copyrightmultiDto = null;
		if (StringUtils.isBlank(announcemultiId)) {
			throw new ValidateException(200401);
		}
		Announcemulti announcemulti = announcemultiMapperExt.selectByPrimaryKey(announcemultiId);
		if (announcemulti == null) {
			throw new BusinessException(200044, "推广信息不存在！");
		}
		if (!announcemulti.getIsDelete().equals("0")) {
			throw new BusinessException(200044, "推广信息不存在！");
		}
		Copyrightmulti copyrightmulti = copyrightmultiMapperExt.selectByPrimaryKey(announcemulti.getCopyrightmultiId());
		if (copyrightmulti == null) {
			throw new BusinessException(200044, "多媒体信息不存在！");
		}
		if (!copyrightmulti.getIsDelete().equals("0")) {
			throw new BusinessException(200044, "多媒体信息不存在！");
		}
		if (!copyrightmulti.getAuditStatus().equals("00")) {
			throw new BusinessException(200044, "多媒体审核未通过！");
		}
		ModelMapper mapper = new ModelMapper();
		copyrightmultiDto = mapper.map(copyrightmulti, CopyrightmultiDto.class);
		copyrightmultiDto.setPath(propertyManager.urlLocalServer
												+ copyrightmultiDto.getPath());
		String pricturePath = copyrightmultiDto.getPicturepath();
		if (pricturePath != null && pricturePath.indexOf("http://") == -1
												&& pricturePath.indexOf("https://") == -1) {
			pricturePath = propertyManager.urlLocalServer + pricturePath;
		}
		copyrightmultiDto.setPicturepath(pricturePath);
		copyrightmultiDto.setAffiliateUserId(announcemulti.getUserId());
		return copyrightmultiDto;
	}

	@Override
	public String findIsBuyBYUserId(String copyrightmultiId, String userId) {
		if (StringUtils.isBlank(copyrightmultiId)
												&& StringUtils.isBlank(userId)) {
			throw new ValidateException(200401);
		}
		Copyrightmulti copyrightmulti = copyrightmultiMapperExt.selectByPrimaryKey(copyrightmultiId);
		if (copyrightmulti == null) {
			throw new BusinessException(200044, "多媒体信息不存在！");
		}
		String buyFlag = "n";
		if (copyrightmulti.getPayType().equals("1")) {// 收费
			if (StringUtils.isNotBlank(userId)) {// 判断微信扫码用户是否登录
				// 已登录 就判断这个用户是否购买过此多媒体 同一多媒体 不同码商推广 只需购买一次即可 分钱 只分第一次购买的码商
				OrderExample orderExample = new OrderExample();
				orderExample.createCriteria().andMediaIdEqualTo(copyrightmultiId).andBuyUserIdEqualTo(userId).andStatusEqualTo(100);
				List<Order> orderList = orderMapperExt.selectByExample(orderExample);
				if (!orderList.isEmpty()) {
					buyFlag = "y";
				}
			}
		}
		return buyFlag;
	}

}