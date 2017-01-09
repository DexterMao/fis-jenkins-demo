
package com.kakasure.bqf.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.kakasure.bqf.dao.dto.CopyrightmultiDto;
import com.kakasure.bqf.dao.dto.MultiOrderDto;
import com.kakasure.bqf.dao.mapper.AnnounceThemeMapperExt;
import com.kakasure.bqf.dao.mapper.AnnouncemultiMapperExt;
import com.kakasure.bqf.dao.mapper.CopyrightmultiMapperExt;
import com.kakasure.bqf.dao.mapper.LiveBroadcastMapperExt;
import com.kakasure.bqf.dao.mapper.OrderMapperExt;
import com.kakasure.bqf.dao.mapper.UserMapperExt;
import com.kakasure.bqf.dao.mapper.UserplusMapperExt;
import com.kakasure.bqf.dao.mapper.VideoCategoryMapperExt;
import com.kakasure.bqf.dao.model.AnnounceThemeExample;
import com.kakasure.bqf.dao.model.Announcemulti;
import com.kakasure.bqf.dao.model.AnnouncemultiExample;
import com.kakasure.bqf.dao.model.Copyrightmulti;
import com.kakasure.bqf.dao.model.CopyrightmultiExample;
import com.kakasure.bqf.dao.model.LiveBroadcast;
import com.kakasure.bqf.dao.model.LiveBroadcastExample;
import com.kakasure.bqf.dao.model.User;
import com.kakasure.bqf.dao.model.UserExample;
import com.kakasure.bqf.dao.model.Userplus;
import com.kakasure.bqf.dao.model.VideoCategory;
import com.kakasure.bqf.dao.model.VideoCategoryExample;
import com.kakasure.bqf.service.ApiService;
import com.kakasure.bqf.util.Const;
import com.kakasure.entity.kakasure.BqfUserDto;
import com.kakasure.exception.BusinessException;
import com.kakasure.lang.DelUsersManager;
import com.kakasure.lang.PropertyManager;
import com.kakasure.lang.util.DateUtil;
import com.kakasure.lang.util.Page;
import com.kakasure.lang.util.QrcodeUtil;
import com.kakasure.lang.util.UuidUtil;
import com.kakasure.lang.util.pay.StringUtil;
import com.kakasure.oauth.util.OauthConstants;
import com.kakasure.security.opt.SsoOperate;
import com.kakasure.service.BaseService;

@Service
public class ApiServiceImpl extends BaseService implements ApiService {

	@Resource
	private VideoCategoryMapperExt	videoCategoryMapperExt;
	@Resource
	private CopyrightmultiMapperExt	copyrightmultiMapperExt;
	@Resource
	private AnnouncemultiMapperExt	announcemultiMapperExt;
	@Resource
	private AnnounceThemeMapperExt	announceThemeMapperExt;
	@Resource
	private PropertyManager			propertyManager;
	@Resource
	private OrderMapperExt			orderMapperExt;
	@Resource
	private UserMapperExt			userMapperExt;
	@Resource
	private UserplusMapperExt		userplusMapperExt;
	@Resource
	private LiveBroadcastMapperExt	liveBroadcastMapperExt;

	/**
	 * 获取类目列表
	 */
	@Override
	public List<VideoCategory> getVedioCategoryList(String id) {
		VideoCategoryExample example = new VideoCategoryExample();
		example.setOrderByClause("CATEGORY_NAME ASC");
		if (StringUtil.isNotBlank(id)) {
			example.createCriteria().andVideoCategoryIdEqualTo(id);
		}
		return videoCategoryMapperExt.selectByExample(example);
	}

	/**
	 * 获取推广的多媒体列表
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Page<CopyrightmultiDto> getPushedMutilList(String jsonPage) {
		try {
			JSONObject jsonObject = JSONObject.parseObject(jsonPage);
			Page<CopyrightmultiDto> pages = JSONObject.toJavaObject(jsonObject, Page.class);
			pages.setResults(copyrightmultiMapperExt.getPushedMutilList(pages));
			return pages;
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException(200044, "异常信息:" + e.getMessage());
		}
	}

	/**
	 * 取消推广
	 */
	@Override
	public void cancelExpand(Announcemulti announcemulti) {
		// TODO Auto-generated method stub
		if (announcemulti == null) {
			throw new BusinessException(200044, "参数异常！");
		}
		String announceMultiId = announcemulti.getAnnouncemultiId();
		System.out.println(announceMultiId);
		if (StringUtil.isBlank(announceMultiId)) {
			throw new BusinessException(200044, "发布方ID异常!");
		}
		// 更新推广信息
		Date now = new Date();
		announcemulti.setIsDelete("1");
		announcemulti.setDateModify(now);
		announcemultiMapperExt.updateByPrimaryKeySelective(announcemulti);

		// 删除主题信息
		AnnounceThemeExample themeExample = new AnnounceThemeExample();
		themeExample.createCriteria().andAnnouncemultiIdEqualTo(announceMultiId);
		announceThemeMapperExt.deleteByExample(themeExample);
	}

	/**
	 * 获取推广的多媒体信息
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Object getUnExpandMultiList(String jsonPage) {
		try {
			JSONObject jsonObject = JSONObject.parseObject(jsonPage);
			Page<CopyrightmultiDto> pages = JSONObject.toJavaObject(jsonObject, Page.class);
			pages.setResults(copyrightmultiMapperExt.getUnExpandMultiList(pages));
			return pages;
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException(200044, "异常信息:" + e.getMessage());
		}
	}

	/**
	 * 发布方推广多媒体
	 */
	@Override
	public void expandMulti(Announcemulti announcemulti) {
		if (announcemulti == null || StringUtil.isBlank(announcemulti.getCopyrightmultiId())) {
			throw new BusinessException(200044, "参数异常!");
		}
		String userId = announcemulti.getUserId();
		if (StringUtil.isBlank(userId)) {
			throw new BusinessException(200044, "未登入异常!");
		}
		String copyrightmultiId = announcemulti.getCopyrightmultiId();
		// 查找多媒体信息
		Copyrightmulti copyrightmulti = copyrightmultiMapperExt.selectByPrimaryKey(copyrightmultiId);
		if (copyrightmulti == null) {
			throw new BusinessException(200044, "没有多媒体信息!");
		}
		// 增加推广次数
		copyrightmultiMapperExt.updateScanCode(copyrightmultiId);
		// 查找是否已推广过
		AnnouncemultiExample announcemultiExample = new AnnouncemultiExample();
		announcemultiExample.createCriteria().andCopyrightmultiIdEqualTo(copyrightmultiId).andUserIdEqualTo(userId);
		List<Announcemulti> announcemultiLists = announcemultiMapperExt.selectByExample(announcemultiExample);
		Date now = new Date();
		Announcemulti tmpAnounce = new Announcemulti();
		if (!announcemultiLists.isEmpty()) {
			announcemulti = announcemultiLists.get(0);
			if (announcemulti.getIsDelete().equals("0")) {
				throw new BusinessException(800001, "已经推广！");
			} else {
				tmpAnounce.setAnnouncemultiId(announcemulti.getAnnouncemultiId());
				tmpAnounce.setIsDelete("0");
				tmpAnounce.setDateModify(now);
				announcemultiMapperExt.updateByPrimaryKeySelective(tmpAnounce);
				return;
			}
		}

		// 插入
		String imgName = UuidUtil.get32UUID();
		String xPath = Const.QRCODE_PATH + DateUtil.getDays();// 相对路径,格式：/qrcode/20150101

		String announcemultiId = UuidUtil.get32UUID();
		// 根据url路径生成二维码图片

		// 需要修改
		String scanCodeLink = propertyManager.urlKakasureMobileServer
												+ "/wechat/qrcode?id="
												+ announcemultiId;

		tmpAnounce.setAnnouncemultiId(announcemultiId);
		tmpAnounce.setUserId(userId);
		tmpAnounce.setCopyrightUserId(copyrightmulti.getUserId());
		tmpAnounce.setCopyrightmultiId(copyrightmultiId);
		tmpAnounce.setCodeId(UuidUtil.get32UUID());
		tmpAnounce.setScanCodeLink(scanCodeLink);
		tmpAnounce.setScanCodeNum(0);
		tmpAnounce.setImgLink(xPath + "/" + imgName + ".jpg");
		tmpAnounce.setDateCreate(now);
		tmpAnounce.setDateModify(now);
		tmpAnounce.setIsDelete("0");

		announcemultiMapperExt.insertSelective(tmpAnounce);

		String path = propertyManager.staticPath + xPath;
		QrcodeUtil.generateQrcode(scanCodeLink, path, imgName + ".jpg");

	}

	/**
	 * 获取多媒体详情
	 */
	@Override
	public CopyrightmultiDto getCopyrightmultiDetail(Copyrightmulti copyrightmulti) {
		if (StringUtil.isBlank(copyrightmulti.getCopyrightmultiId())) {
			throw new BusinessException(200044, "参数异常!");
		}
		copyrightmulti = copyrightmultiMapperExt.selectByPrimaryKey(copyrightmulti.getCopyrightmultiId());
		String pricturePath = copyrightmulti.getPicturepath();
		if (pricturePath != null && pricturePath.indexOf("http://") == -1
												&& pricturePath.indexOf("https://") == -1) {
			pricturePath = propertyManager.urlLocalServer + pricturePath;
		}
		copyrightmulti.setPicturepath(pricturePath);
		copyrightmulti.setPath(propertyManager.urlLocalServer
												+ copyrightmulti.getPath());
		ModelMapper mapper = new ModelMapper();
		CopyrightmultiDto dto = mapper.map(copyrightmulti, CopyrightmultiDto.class);
		VideoCategory videoCategory = videoCategoryMapperExt.selectByPrimaryKey(dto.getVideoCategoryId());
		dto.setCategoryName(videoCategory.getCategoryName());
		return dto;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object getMultiOrderList(String jsonPage) {
		JSONObject jsonObject = JSONObject.parseObject(jsonPage);
		Page<MultiOrderDto> pages = JSONObject.toJavaObject(jsonObject, Page.class);
		pages.setResults(orderMapperExt.getMultiOrderList(pages));
		return pages;
	}

	@Override
	public List<BqfUserDto> getBqfusers(Page<BqfUserDto> page) {
		List<BqfUserDto> BqfUserDtos = userMapperExt.getBqfusers(page);
		return BqfUserDtos;
	}

	@Override
	public void deleteUser(String guid) {
		if (StringUtil.isBlank(guid)) {
			throw new BusinessException(200044, "参数异常!");
		}
		UserExample userExample = new UserExample();
		userExample.createCriteria().andGuidEqualTo(guid);
		List<User> users = userMapperExt.selectByExample(userExample);
		if (users.isEmpty()) {
			throw new BusinessException(200044, "版权方系统没有该用户!");
		}
		User user = users.get(0);
		String userId = user.getUserId();
		CopyrightmultiExample copyrightmultiExample = new CopyrightmultiExample();
		copyrightmultiExample.createCriteria().andUserIdEqualTo(userId).andIsDeleteEqualTo("0");
		List<Copyrightmulti> copyrightmultis = copyrightmultiMapperExt.selectByExample(copyrightmultiExample);
		if (!copyrightmultis.isEmpty()) {
			throw new BusinessException(200044, "请删除多媒体后再删除该用户!");
		}

		LiveBroadcastExample liveBroadcastExample = new LiveBroadcastExample();
		liveBroadcastExample.createCriteria().andUserIdEqualTo(userId).andIsDeleteEqualTo("0");
		List<LiveBroadcast> liveBroadcasts = liveBroadcastMapperExt.selectByExample(liveBroadcastExample);
		if (!liveBroadcasts.isEmpty()) {
			throw new BusinessException(200044, "请删除节目后再删除该用户!");
		}

		User record = new User();
		record.setUserId(userId);
		record.setIsDelete("1");
		userMapperExt.updateByPrimaryKeySelective(record);
		Userplus userplus = new Userplus();
		userplus.setUserId(userId);
		userplus.setIsDelete("1");
		userplusMapperExt.updateByPrimaryKeySelective(userplus);

		SsoOperate ssoOperate = new SsoOperate(propertyManager.urlSsoServer, OauthConstants.client_id, OauthConstants.client_secret);
		ssoOperate.deleteUser(user.getUsername());

		DelUsersManager.putCache(user.getUserId(), user.getUsername());
	}
}
