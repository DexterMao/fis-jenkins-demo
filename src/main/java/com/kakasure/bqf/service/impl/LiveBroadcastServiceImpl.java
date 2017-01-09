package com.kakasure.bqf.service.impl;

import java.math.BigDecimal;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.kakasure.bqf.dao.dto.LiveBroadcastDto;
import com.kakasure.bqf.dao.dto.LiveQimanetShowDto;
import com.kakasure.bqf.dao.mapper.LiveBalanceMapperExt;
import com.kakasure.bqf.dao.mapper.LiveBqfGiftMapperExt;
import com.kakasure.bqf.dao.mapper.LiveBroadcastMapperExt;
import com.kakasure.bqf.dao.mapper.UserMapperExt;
import com.kakasure.bqf.dao.model.LiveBalance;
import com.kakasure.bqf.dao.model.LiveBqfGift;
import com.kakasure.bqf.dao.model.LiveBroadcast;
import com.kakasure.bqf.dao.model.LiveBroadcastExample;
import com.kakasure.bqf.dao.model.User;
import com.kakasure.bqf.service.LiveBroadcastService;
import com.kakasure.bqf.util.Const;
import com.kakasure.common.enums.IsDeleted;
import com.kakasure.common.enums.ResponseCode;
import com.kakasure.common.util.EmojiFilterUtil;
import com.kakasure.common.util.HttpRequestAndResultUtil;
import com.kakasure.common.util.KksKeyUtil;
import com.kakasure.entity.kakasure.KakasureLive;
import com.kakasure.entity.kakasure.KakasureLiveBqfGift;
import com.kakasure.entity.kakasure.ResultLive;
import com.kakasure.exception.BusinessException;
import com.kakasure.exception.ValidateException;
import com.kakasure.lang.PropertyManager;
import com.kakasure.lang.util.DateUtil;
import com.kakasure.lang.util.Page;
import com.kakasure.lang.util.QrcodeUtil;
import com.kakasure.lang.util.UrlUtil;
import com.kakasure.lang.util.UuidUtil;
import com.kakasure.lang.util.VerifyReg;
import com.kakasure.lang.util.pay.MD5Signature;
import com.kakasure.lang.util.pay.ParameterUtil;
import com.kakasure.lang.util.pay.StringUtil;
import com.kakasure.service.BaseService;
import com.kakasure.util.QcloubLiveUtil;
import com.kakasure.util.QcloundTimUtil;
import com.kakasure.util.TlsSigUtil;

@Service
public class LiveBroadcastServiceImpl extends BaseService implements LiveBroadcastService {
	@Resource
	private PropertyManager			propertyManager;
	@Resource
	private LiveBroadcastMapperExt	liveBroadcastMapperExt;
	@Resource
	private LiveBalanceMapperExt	liveBalanceMapperExt;
	@Resource
	private LiveBqfGiftMapperExt	liveBqfGiftMapperExt;
	@Resource
	private UserMapperExt			userMapperExt;

	@Override
	public void saveLive(LiveBroadcastDto liveBroadcast) {
		KakasureLive live = null;
		if (StringUtils.isBlank(liveBroadcast.getName())) {
			throw new ValidateException(600004);
		}
		liveBroadcast.setName(liveBroadcast.getName().trim());
		if (!VerifyReg.verifyUserName(liveBroadcast.getName()) || liveBroadcast.getName().length() > 32) {
			throw new ValidateException(600014);
		}
		if (StringUtils.isBlank(liveBroadcast.getPicturePath())) {
			throw new ValidateException(600002);
		}
		if (StringUtils.isBlank(liveBroadcast.getPayType())) {
			throw new ValidateException(600006);
		}
		if (StringUtils.isBlank(liveBroadcast.getDescr())) {
			throw new ValidateException(600009);
		}
		if (liveBroadcast.getDescr().length() > 2000) {
			throw new ValidateException(600024);
		}
		if (StringUtils.isBlank(liveBroadcast.getDateEndTimeStr()) || StringUtils.isBlank(liveBroadcast.getDateStartTimeStr())) {
			throw new ValidateException(600018);
		}
		if (!DateUtil.compareDate(liveBroadcast.getDateEndTimeStr(), liveBroadcast.getDateStartTimeStr())) {
			throw new ValidateException(600018);
		}
		liveBroadcast.setDateEndTime(DateUtil.fomatDateHour(liveBroadcast.getDateEndTimeStr()));
		liveBroadcast.setDateStartTime(DateUtil.fomatDateHour(liveBroadcast.getDateStartTimeStr()));
		if (StringUtils.isBlank(liveBroadcast.getDateEndIntervalStr()) || StringUtils.isBlank(liveBroadcast.getDateStartIntervalStr())) {
			throw new ValidateException(600018);
		}
		if (getMiao(liveBroadcast.getDateEndIntervalStr()) < getMiao(liveBroadcast.getDateStartIntervalStr())) {
			throw new ValidateException(600018);
		}
		Date now = new Date();
		liveBroadcast.setDateStartInterval(getMiao(liveBroadcast.getDateStartIntervalStr()));
		Date startTime = liveBroadcast.getDateEndTime();
		if (startTime.before(now)) {
			throw new BusinessException(ResponseCode.FAIL.code, "节目开始时间不正确！");
		}
		liveBroadcast.setDateEndInterval(getMiao(liveBroadcast.getDateEndIntervalStr()));
		liveBroadcast.setDateCreate(new Date());
		// 判断收费还是免费
		if (liveBroadcast.getPayType().equals("0")) {
			liveBroadcast.setPrice(0.00);
		} else {
			if (liveBroadcast.getPrice() == null || StringUtils.isBlank(liveBroadcast.getPrice().toString())) {
				throw new ValidateException(600007);
			}
			Boolean strResult = liveBroadcast.getPrice().toString().matches("^-([1-9]d*.d*|0.d*[1-9]d*)$");
			if (strResult == true) {
				throw new ValidateException(600007);
			}
			BigDecimal PRICE = new BigDecimal(liveBroadcast.getPrice());
			PRICE = PRICE.setScale(2, BigDecimal.ROUND_HALF_UP);
		}
		liveBroadcast.setIsDelete("0");

		String path = propertyManager.urlAffiliateServer + "/api/createLive.json";
		Map<String, String> param = new HashMap<String, String>();
		param.put("subject", liveBroadcast.getName());
		param.put("startTime", liveBroadcast.getDateStartTimeStr() + " 00:00:00");
		param.put("maxAttendees", "4");
		param.put("description", liveBroadcast.getDescr());
		param.put("endTime", liveBroadcast.getDateEndTimeStr() + " 23:59:59");
		param.put("channelType", liveBroadcast.getChannelType());
		String str = UrlUtil.getStringByPost(path, param);
		ResultLive result = null;
		result = JSON.parseObject(str, ResultLive.class);
		printInfoLog("result  {}", result);
		if (result != null && result.getCode().equals(Const.CODE_SUCCESS)) {
			printInfoLog("result  {}", result);
			live = result.getData();
		} else {
			printErrorLog("Api result is not success,Result：" + (result != null ? JSON.toJSONString(result) : null));
			throw new ValidateException(200444);
		}

		liveBroadcast.setAnchorPath(live.getOrganizerJoinUrl());

		String url = propertyManager.urlKakasureMobileServer + "/app/qimanet/webcast.json?roomId=" + live.getShowId();
		String imgName = UuidUtil.get32UUID();
		String xPath = Const.QRCODE_PATH + DateUtil.getDays();// 相对路径,格式：/qrcode/20150101
		// 根据url路径生成二维码图片
		String codePath = propertyManager.staticPath + xPath;
		QrcodeUtil.generateQrcode(url, codePath, imgName + ".jpg");
		liveBroadcast.setGuestsCodePath(xPath + "/" + imgName + ".jpg");
		liveBroadcast.setWebcastId(live.getId());

		liveBroadcast.setGuestsPath(live.getPanelistJoinUrl());
		liveBroadcast.setAnchorPassword(new Integer(live.getOrganizerToken()));
		liveBroadcast.setGuestsPassword(new Integer(live.getPanelistToken()));

		liveBroadcast.setLiveBroadcastId(UuidUtil.get32UUID());
		// 节目新增
		liveBroadcastMapperExt.insertSelective(liveBroadcast);
	}

	@Override
	public List<LiveBroadcastDto> getLiveBroList(Page<LiveBroadcastDto> page, LiveBroadcastDto liveBroadcastDto) {
		if (page == null) {
			page = new Page<>();
		}
		Map<String, Object> params = new HashMap<>();
		params.put("PAY_TYPE", liveBroadcastDto.getPayType());
		params.put("date", new Date());
		params.put("NAME", EmojiFilterUtil.filterEmoji(liveBroadcastDto.getName(), "*"));
		params.put("status", liveBroadcastDto.getStatus());
		params.put("userId", liveBroadcastDto.getUserId());
		page.setParams(params);
		List<LiveBroadcastDto> broadcastDtos = liveBroadcastMapperExt.getLiveBroList(page);
		for (LiveBroadcastDto l : broadcastDtos) {
			Date DATE_START_TIME = l.getDateStartTime();
			Date DATE_END_TIME = l.getDateEndTime();
			String PICTURE_PATH = propertyManager.urlLocalServer + l.getPicturePath();
			String status = "22";
			if (DATE_START_TIME.after(new Date())) {
				status = "11";
			} else if (DATE_END_TIME.before(new Date())) {
				status = "33";
			}
			l.setDateStartIntervalStr(getHour(l.getDateStartInterval()));
			l.setDateEndIntervalStr(getHour(l.getDateEndInterval()));
			l.setPicturePath(PICTURE_PATH);
			l.setStatus(status);
			// 截取webcastId
			String channelType = l.getChannelType();
			if (StringUtil.isBlank(channelType) || "01".equals(channelType)) {
				String webcastId = l.getGuestsPath().replace("http://kakasure.gensee.com/webcast/site/entry/live-", "");
				String url = propertyManager.urlKakasureMobileServer + "/app/qimanet/webcast.json?type=guest&roomId=";
				l.setGuestQrcodeUrl(url + webcastId);
			} else if ("02".equals(channelType)) {
				String keys = String.valueOf(l.getAnchorPassword() == null ? 666666 : l.getAnchorPassword());
				String url = propertyManager.urlKakasureMobileServer + "/app/qimanet/webcast.json?channelType=02&roomId=" + l.getLiveBroadcastId() + "&keys=" + keys;
				l.setGuestQrcodeUrl(url);
				String attendUrl = propertyManager.urlKakasureMobileServer + "/api/live/getAttendeeAddr.json?liveBroadcastId=" + l.getLiveBroadcastId() + "&reqType=scan";
				l.setAttendeeQrcodeUrl(attendUrl);
			}
		}
		return broadcastDtos;
	}

	@Override
	public void deleted(String liveBroadcastId) {
		if (StringUtils.isBlank(liveBroadcastId)) {
			throw new ValidateException(600010);
		}
		LiveBroadcast liveBroadcast = liveBroadcastMapperExt.selectByPrimaryKey(liveBroadcastId);
		// 调用码商删除节目接口
		try {
			String channelType = liveBroadcast.getChannelType();
			if (StringUtil.isNotBlank(channelType) && "02".equals(channelType)) {
				deleteLive(liveBroadcast);
			}
			// 展示
			Map<String, String> param = new HashMap<String, String>();
			param.put("targetId", liveBroadcastId);
			param.put("exchange", "bqf_webcast_delete");
			param.put("target", "删除节目");
			param.put("targetStatus", "1");
			param.put("times", new Date().getTime() + "");

			printInfoLog("参数: {}", param);
			// 接口签名
			String signData = ParameterUtil.getSignData(param);
			String sign;
			try {
				sign = MD5Signature.sign(signData, KksKeyUtil.KEY_KAKASURE + propertyManager.signKeySuffix);
			} catch (Exception e) {
				sign = "";
			}
			param.put("sign", sign);

			String str = UrlUtil.getStringByPost(propertyManager.urlKakasureMobileServer + "/api/message/send.json", param);

			HttpRequestAndResultUtil.checkResult(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
		LiveBroadcast record = new LiveBroadcast();
		record.setLiveBroadcastId(liveBroadcastId);
		record.setIsDelete("1");
		liveBroadcastMapperExt.updateByPrimaryKeySelective(record);
	}

	@Override
	public LiveBroadcastDto getLiveById(String liveBroadcastId) {
		if (StringUtils.isBlank(liveBroadcastId)) {
			throw new ValidateException(600010);
		}
		LiveBroadcast record = new LiveBroadcast();
		record = liveBroadcastMapperExt.selectByPrimaryKey(liveBroadcastId);
		if (record == null) {
			record = new LiveBroadcast();
		}
		ModelMapper mapper = new ModelMapper();
		LiveBroadcastDto broadcastDto = mapper.map(record, LiveBroadcastDto.class);
		broadcastDto.setDateStartIntervalStr(getHour(broadcastDto.getDateStartInterval()));
		broadcastDto.setDateEndIntervalStr(getHour(broadcastDto.getDateEndInterval()));
		return broadcastDto;
	}

	private Integer getMiao(String date) {
		Integer miao = 0;
		miao += Integer.parseInt(date.substring(0, date.indexOf(":"))) * 3600;
		miao += Integer.parseInt(date.substring(date.indexOf(":") + 1)) * 60;
		return miao;
	}

	private String getHour(Integer date) {
		String miao;
		if (date == 0) {
			miao = "00:00";
			return miao;
		}
		String hour = String.valueOf(date / 3600);
		String fen = String.valueOf((date % 3600) / 60);
		if (Integer.valueOf(fen) < 10) {
			fen = "0" + fen;
		}
		miao = hour + ":" + fen;
		return miao;
	}

	@Override
	public List<LiveBroadcastDto> getLivesById(String ids, Boolean isPass) {
		List<LiveBroadcastDto> liveBroadcastDtos = new ArrayList<>();
		if (StringUtils.isNotBlank(ids)) {
			String[] multIds = ids.split(",");
			List<String> liveId = new ArrayList<>();
			for (String s : multIds) {
				liveId.add(s);
			}
			ModelMapper mapper = new ModelMapper();
			LiveBroadcastExample liveBroadcastExample = new LiveBroadcastExample();
			liveBroadcastExample.createCriteria().andLiveBroadcastIdIn(liveId);
			List<LiveBroadcast> broadcasts = liveBroadcastMapperExt.selectByExample(liveBroadcastExample);
			LiveBroadcastDto liveBroadcastDto = null;
			for (LiveBroadcast l : broadcasts) {
				// 获取用户名
				User user = userMapperExt.selectByPrimaryKey(l.getUserId());
				liveBroadcastDto = mapper.map(l, LiveBroadcastDto.class);
				liveBroadcastDto.setDateStartIntervalStr(getHour(l.getDateStartInterval()));
				liveBroadcastDto.setDateEndIntervalStr(getHour(l.getDateEndInterval()));
				liveBroadcastDto.setUserName(user.getUsername());
				liveBroadcastDtos.add(liveBroadcastDto);
				String pricturePath = l.getPicturePath();
				if (pricturePath != null && pricturePath.indexOf("http://") == -1 && pricturePath.indexOf("https://") == -1) {
					pricturePath = propertyManager.urlLocalServer + pricturePath;
				}
				liveBroadcastDto.setPicturePath(pricturePath);
			}
		}
		return liveBroadcastDtos;
	}

	@Override
	public List<LiveBroadcastDto> getLiveBroListFromKks(Page<LiveBroadcastDto> page) {
		List<LiveBroadcastDto> liveBroadcastDtos = liveBroadcastMapperExt.getLiveBroListFromKks(page);
		for (LiveBroadcastDto l : liveBroadcastDtos) {
			String status = "22";
			if (l.getDateStartTime().after(new Date())) {
				status = "11";
			} else if (l.getDateEndTime().before(new Date())) {
				status = "33";
			}
			l.setDateStartIntervalStr(getHour(l.getDateStartInterval()));
			l.setDateEndIntervalStr(getHour(l.getDateEndInterval()));
			l.setStatus(status);
			String pricturePath = l.getPicturePath();
			if (pricturePath != null && pricturePath.indexOf("http://") == -1 && pricturePath.indexOf("https://") == -1) {
				pricturePath = propertyManager.urlLocalServer + pricturePath;
			}
			l.setPicturePath(pricturePath);
		}
		return liveBroadcastDtos;
	}

	@Override
	public void savaLiveBqfGift(KakasureLiveBqfGift kakasureLiveBqfGift) {
		if (StringUtils.isBlank(kakasureLiveBqfGift.getCopyrightUserId()) || kakasureLiveBqfGift.getLiveAffiliateGiftId() == null || kakasureLiveBqfGift.getCashCopyright() == null || kakasureLiveBqfGift.getPointCopyright() == null || StringUtils.isBlank(kakasureLiveBqfGift.getGiftName()) || kakasureLiveBqfGift.getGiftNum() == null || StringUtils.isBlank(kakasureLiveBqfGift.getUsernameCopyright())) {
			printErrorLog("参数错误 copyrightUserId{}", kakasureLiveBqfGift.getCopyrightUserId(), kakasureLiveBqfGift.getLiveAffiliateGiftId(), kakasureLiveBqfGift.getCashCopyright(), kakasureLiveBqfGift.getGiftName(), kakasureLiveBqfGift.getGiftNum(), kakasureLiveBqfGift.getUsernameCopyright());
			throw new ValidateException(200401);
		}

		String signData = kakasureLiveBqfGift.getLiveAffiliateGiftId() + kakasureLiveBqfGift.getGiftName() + kakasureLiveBqfGift.getUsernameCopyright() + "礼物";
		String sign = null;
		try {
			sign = MD5Signature.sign(signData, KksKeyUtil.KEY_QIMANET + propertyManager.signKeySuffix);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		printDebugLog("sign:" + sign);
		printDebugLog("kakasureLiveBqfGift sign:" + kakasureLiveBqfGift.getSign());
		if (!sign.equals(kakasureLiveBqfGift.getSign())) {
			throw new ValidateException(200100);
		}
		User user = userMapperExt.selectByPrimaryKey(kakasureLiveBqfGift.getCopyrightUserId());
		if (user == null) {
			printErrorLog("user 不存在", user);
			throw new ValidateException(200601);
		}
		LiveBalance balance = liveBalanceMapperExt.selectByPrimaryKey(kakasureLiveBqfGift.getCopyrightUserId());
		if (balance == null) {
			balance = new LiveBalance();
			balance.setUserId(kakasureLiveBqfGift.getCopyrightUserId());
			balance.setBalance(kakasureLiveBqfGift.getCashCopyright());
			balance.setCanPay(kakasureLiveBqfGift.getCashCopyright());
			balance.setPoints(kakasureLiveBqfGift.getPointCopyright().longValue());
			liveBalanceMapperExt.insertSelective(balance);
		} else {
			balance.setBalance(balance.getBalance().add(kakasureLiveBqfGift.getCashCopyright()));
			balance.setCanPay(balance.getCanPay().add(kakasureLiveBqfGift.getCashCopyright()));
			balance.setPoints(balance.getPoints() + kakasureLiveBqfGift.getPointCopyright().longValue());
			liveBalanceMapperExt.updateByPrimaryKeySelective(balance);
		}
		LiveBqfGift bqfGift = new LiveBqfGift();
		bqfGift.setLiveAffiliateGiftId(kakasureLiveBqfGift.getLiveAffiliateGiftId());
		bqfGift.setGiftName(kakasureLiveBqfGift.getGiftName());
		bqfGift.setGiftNum(kakasureLiveBqfGift.getGiftNum());
		bqfGift.setUsernameCopyright(kakasureLiveBqfGift.getUsernameCopyright());
		bqfGift.setCopyrightUserId(kakasureLiveBqfGift.getCopyrightUserId());
		bqfGift.setCopyrightWebcastId(kakasureLiveBqfGift.getCopyrightWebcastId());
		bqfGift.setCashCopyright(kakasureLiveBqfGift.getCashCopyright());
		bqfGift.setPointCopyright(kakasureLiveBqfGift.getPointCopyright());
		bqfGift.setDateCreate(new Date());
		liveBqfGiftMapperExt.insertSelective(bqfGift);
	}

	/**
	 * 获取聊天记录
	 */
	@Override
	public Object getChats(String webcastId) {
		try {
			Map<String, String> params = new HashMap<>();
			params.put("webcastId", URLDecoder.decode(webcastId, "UTF-8"));
			System.out.println(params);
			String reqUrl = propertyManager.urlKakasureMobileServer + "/app/getChats.json";
			String rslt = UrlUtil.getStringByPost(reqUrl, params);
			if (StringUtil.isBlank(rslt)) {
				return null;
			} else {
				JSONObject jsonObject = JSONObject.parseObject(rslt);
				if (jsonObject.getInteger("code").compareTo(ResponseCode.SUCCESS.code) == 0) {
					return jsonObject.get("data");
				}
			}
		} catch (Exception e) {
			printErrorLog("获取[{}]聊天记录异常：{}", webcastId, e.getMessage());
		}
		return null;
	}

	/**
	 * 创建腾讯直播
	 * 
	 * @param webcastDto
	 * @return
	 */
	private LiveBroadcastDto createWebcastByTecent(LiveBroadcastDto liveBroadcast) {
		if (liveBroadcast == null) {
			throw new BusinessException(ResponseCode.FAIL.code, "参数为空异常！");
		}
		TreeMap<String, Object> params = new TreeMap<String, Object>();
		params.put("channelName", liveBroadcast.getName());
		params.put("outputSourceType", 3);
		params.put("sourceList.1.name", liveBroadcast.getName());
		params.put("sourceList.1.type", 1);
		params.put("outputRate.1", 0);
		QcloubLiveUtil qcloubLiveUtil = new QcloubLiveUtil();
		String rslt = qcloubLiveUtil.callQLiveByGetNoRegion("CreateLVBChannel", params);
		printInfoLog("创建节目（tecent）结果：" + rslt);
		JSONObject jsonObject = JSONObject.parseObject(rslt);
		if (0 == jsonObject.getIntValue("code")) {// 成功
			String channelId = jsonObject.getString("channel_id");
			liveBroadcast.setWebcastNumber(channelId);
			JSONObject channelObject = jsonObject.getJSONObject("channelInfo");
			liveBroadcast.setAnchorPath(channelObject.getString("upstream_address"));
			JSONArray addressJsonArr = channelObject.getJSONArray("downstream_address");
			if (addressJsonArr != null && addressJsonArr.size() > 0) {
				JSONObject addressJsonObj = addressJsonArr.getJSONObject(0);
				String attendAddress = addressJsonObj.getString("hls_downstream_address") + "|" + addressJsonObj.getString("rtmp_downstream_address") + "|" + addressJsonObj.getString("flv_downstream_address");
				liveBroadcast.setAttendeePath(attendAddress);
			}
			// 创建聊天室 失败则删除直播频道
			try {
				String roomJson = QcloundTimUtil.createGroup(DateUtil.getTime() + "R", channelId);
				if (StringUtil.isBlank(roomJson) || roomJson.startsWith("ERR")) {
					params.clear();
					params.put("channelIds.1", channelId);
					qcloubLiveUtil.callQLiveByGetNoRegion("DeleteLVBChannel", params);
					// 删除直播
					throw new BusinessException(ResponseCode.FAIL.code, "创建聊天室失败：" + roomJson);
				} else {
					JSONObject roomObj = JSONObject.parseObject(roomJson);
					if ("OK".equals(roomObj.getString("ActionStatus"))) {
						liveBroadcast.setWebcastId(roomObj.getString("GroupId"));
					} else {
						params.clear();
						params.put("channelIds.1", channelId);
						qcloubLiveUtil.callQLiveByGetNoRegion("DeleteLVBChannel", params);
						// 删除直播
						throw new BusinessException(ResponseCode.FAIL.code, "创建聊天室失败：" + roomObj.getString("ErrorCode") + ":" + roomObj.getString("ErrorInfo"));
					}
				}
			} catch (Exception e) {
				params.clear();
				params.put("channelIds.1", channelId);
				qcloubLiveUtil.callQLiveByGetNoRegion("DeleteLVBChannel", params);
				// 删除直播
				throw new BusinessException(ResponseCode.FAIL.code, "创建聊天室异常：" + e.getMessage());
			}
		} else {
			throw new BusinessException(ResponseCode.FAIL.code, "创建直播失败:" + jsonObject.getString("message"));
		}
		return liveBroadcast;
	}

	/**
	 * 腾讯直播创建
	 */
	@Override
	public void saveLiveTecent(LiveBroadcastDto liveBroadcast) {
		if (StringUtils.isBlank(liveBroadcast.getName())) {
			throw new ValidateException(600004);
		}
		liveBroadcast.setName(EmojiFilterUtil.filterEmoji(liveBroadcast.getName().trim(), "*"));
		if (!VerifyReg.verifyUserName(liveBroadcast.getName()) || liveBroadcast.getName().length() > 40) {
			throw new ValidateException(600014);
		}
		if (StringUtils.isBlank(liveBroadcast.getPicturePath())) {
			throw new ValidateException(600002);
		}
		if (StringUtils.isBlank(liveBroadcast.getPayType())) {
			throw new ValidateException(600006);
		}
		if (StringUtils.isBlank(liveBroadcast.getDescr())) {
			throw new ValidateException(600009);
		}
		if (liveBroadcast.getDescr().length() > 2000) {
			throw new ValidateException(600024);
		}
		if (StringUtils.isBlank(liveBroadcast.getDateEndTimeStr()) || StringUtils.isBlank(liveBroadcast.getDateStartTimeStr())) {
			throw new ValidateException(600018);
		}
		if (!DateUtil.compareDate(liveBroadcast.getDateEndTimeStr(), liveBroadcast.getDateStartTimeStr())) {
			throw new ValidateException(600018);
		}
		Date now = new Date();
		liveBroadcast.setDateEndTime(DateUtil.fomatDateHour(liveBroadcast.getDateEndTimeStr()));
		liveBroadcast.setDateStartTime(DateUtil.fomatDateHour(liveBroadcast.getDateStartTimeStr()));
		Date startTime = liveBroadcast.getDateEndTime();
		if (startTime.before(now) || (liveBroadcast.getDateStartTime()).before(now)) {
			throw new BusinessException(ResponseCode.FAIL.code, "节目时间选择不正确！");
		}
		if (StringUtils.isBlank(liveBroadcast.getDateEndIntervalStr()) || StringUtils.isBlank(liveBroadcast.getDateStartIntervalStr())) {
			throw new ValidateException(600018);
		}
		if (getMiao(liveBroadcast.getDateEndIntervalStr()) < getMiao(liveBroadcast.getDateStartIntervalStr())) {
			throw new ValidateException(600018);
		}
		liveBroadcast.setDateStartInterval(getMiao(liveBroadcast.getDateStartIntervalStr()));
		liveBroadcast.setDateEndInterval(getMiao(liveBroadcast.getDateEndIntervalStr()));
		liveBroadcast.setDateCreate(now);
		// 判断收费还是免费
		if (liveBroadcast.getPayType().equals("0")) {
			liveBroadcast.setPrice(0.00);
		} else {
			if (liveBroadcast.getPrice() == null || StringUtils.isBlank(liveBroadcast.getPrice().toString())) {
				throw new ValidateException(600007);
			}
			Boolean strResult = liveBroadcast.getPrice().toString().matches("^-([1-9]d*.d*|0.d*[1-9]d*)$");
			if (strResult == true) {
				throw new ValidateException(600007);
			}
			BigDecimal PRICE = new BigDecimal(liveBroadcast.getPrice());
			PRICE = PRICE.setScale(2, BigDecimal.ROUND_HALF_UP);
		}
		liveBroadcast.setIsDelete("0");
		liveBroadcast.setDescr(EmojiFilterUtil.filterEmoji(liveBroadcast.getDescr(), "*"));
		liveBroadcast = createWebcastByTecent(liveBroadcast);
		liveBroadcast.setLiveBroadcastId(UuidUtil.get32UUID());
		liveBroadcast.setAnchorPassword(getRandom());
		// 节目新增
		liveBroadcastMapperExt.insertSelective(liveBroadcast);
	}

	/**
	 * 获取随机数
	 * 
	 * @return
	 */
	private int getRandom() {
		return (int) ((Math.random() * 9 + 1) * 100000000);
	}

	/**
	 * 删除节目
	 */
	private void deleteLive(LiveBroadcast liveBroadcast) {
		try {
			String rslt;
			printInfoLog("直播节目ID{}删除", liveBroadcast.getLiveBroadcastId());
			String channelType = liveBroadcast.getChannelType();
			if (!"02".equals(channelType)) {
				return;
			}
			// 删除腾讯直播
			QcloubLiveUtil qcloubLiveUtil = new QcloubLiveUtil();
			TreeMap<String, Object> params = new TreeMap<String, Object>();
			params.put("channelIds.1", liveBroadcast.getWebcastNumber());
			rslt = qcloubLiveUtil.callQLiveByGetNoRegion("StopLVBChannel", params);
			if (StringUtil.isBlank(rslt)) {
				return;
			}
			JSONObject stopJson = JSONObject.parseObject(rslt);
			if (0 == stopJson.getIntValue("code")) {
				rslt = qcloubLiveUtil.callQLiveByGetNoRegion("DeleteLVBChannel", params);
				if (StringUtil.isNotBlank(rslt)) {
					JSONObject deleteJson = JSONObject.parseObject(rslt);
					printInfoLog("腾讯直播频道{}删除结果：code[{}],message[{}]", liveBroadcast.getWebcastNumber(), deleteJson.getString("code"), deleteJson.getString("message"));
				}
			} else {
				printInfoLog("腾讯直播频道{}STOP失败：code[{}],message[{}]", liveBroadcast.getWebcastNumber(), stopJson.getString("code"), stopJson.getString("message"));
			}
			// 删除聊天室
			rslt = QcloundTimUtil.deleteGroup(liveBroadcast.getWebcastId());
			if (StringUtil.isNotBlank(rslt)) {
				JSONObject chatJson = JSONObject.parseObject(rslt);
				printInfoLog("腾讯直播聊天室{}删除结果：code[{}],message[{}]_{}", liveBroadcast.getWebcastId(), chatJson.getString("ActionStatus"), chatJson.getString("ErrorCode"), rslt);
			}
		} catch (Exception e) {
			e.printStackTrace();
			printErrorLog("删除直播异常！" + e.getMessage(), e);
		}
	}

	/**
	 * 获取主播直播信息
	 */
	@Override
	public LiveQimanetShowDto getLiveShowOrgInfo(String id, String version, Integer keys) {
		if (StringUtil.isBlank(id)) {
			throw new BusinessException(ResponseCode.FAIL.code, "主播ID不存在！");
		}
		LiveQimanetShowDto dto = null;
		if (StringUtil.isNotBlank(version)) {
			LiveBroadcast liveBroadcast = liveBroadcastMapperExt.selectByPrimaryKey(id);
			if (liveBroadcast == null || IsDeleted.YES.value.equals(liveBroadcast.getIsDelete()) || "1".equals(liveBroadcast.getLiveStatus())) {
				printErrorLog("节目不存在{}，请核实！", id);
				throw new BusinessException(ResponseCode.FAIL.code, "节目不存在！");
			}
			Integer pwdKey = liveBroadcast.getAnchorPassword();
			if (keys == null || (pwdKey != null && keys.compareTo(pwdKey) != 0)) {
				throw new BusinessException(ResponseCode.FAIL.code, "直播二维码已失效！");
			}
			dto = new LiveQimanetShowDto();
			dto.setWebcastID(liveBroadcast.getWebcastId());
			dto.setLiveBroadcastId(liveBroadcast.getLiveBroadcastId());
			String channel = liveBroadcast.getChannelType();
			if (StringUtil.isNotBlank(channel) && "02".equals(channel)) {
				// 判断主播是否在直播
				JSONObject rslt = (JSONObject) getLiveChannelInfo(liveBroadcast.getLiveBroadcastId());
				if (rslt == null) {
					printErrorLog("节目不存在{}，请核实！", liveBroadcast.getLiveBroadcastId());
					throw new BusinessException(ResponseCode.FAIL.code, "节目不存在！");
				}
				int status = rslt.getIntValue("channel_status");
				if (status == 1) {
					// 重启腾讯直播
					restartQcloudLive(liveBroadcast);
				} else if (status == 2) {
					throw new BusinessException(ResponseCode.FAIL.code, "节目异常！");
				} else if (status == 3) {
					throw new BusinessException(ResponseCode.FAIL.code, "节目已关闭！");
				}

				// 腾讯直播
				dto.setWebcastID(liveBroadcast.getWebcastId());
				dto.setOrganizerUrl(liveBroadcast.getAnchorPath());
				dto.setChannelType(channel);
				String identifier = liveBroadcast.getWebcastNumber();
				dto.setIdentifier(identifier + "|" + dto.getNiceName());
				dto.setUserSig(TlsSigUtil.signatureTLS(identifier + "|" + dto.getNiceName()));
				dto.setAccountType(TlsSigUtil.getAccountType());
				dto.setSdkAppId(TlsSigUtil.getAppId());
			}
		}
		return dto;
	}

	/**
	 * 获取腾讯直播状态
	 */
	@Override
	public Object getLiveChannelInfo(String liveBroadCastId) {
		if (StringUtil.isBlank(liveBroadCastId)) {
			throw new BusinessException(ResponseCode.FAIL.code, "liveBroadCast参数为空！");
		}
		LiveBroadcast liveBroadcast = liveBroadcastMapperExt.selectByPrimaryKey(liveBroadCastId);
		if (liveBroadcast == null || ("1").equals(liveBroadcast.getIsDelete())) {
			throw new BusinessException(ResponseCode.FAIL.code, "节目[" + liveBroadCastId + "]不存在或已删除！");
		}
		TreeMap<String, Object> params = new TreeMap<String, Object>();
		params.put("channelId", liveBroadcast.getWebcastNumber());
		QcloubLiveUtil qcloubLiveUtil = new QcloubLiveUtil();
		String rslt = qcloubLiveUtil.callQLiveByGetNoRegion("DescribeLVBChannel", params);
		if (rslt == null) {
			throw new BusinessException(ResponseCode.FAIL.code, "节目不存在，请核实！");
		}
		JSONObject jsonObject = JSONObject.parseObject(rslt);
		if ("0".equals(jsonObject.getString("code"))) {
			JSONArray array = jsonObject.getJSONArray("channelInfo");
			if (array != null && array.size() > 0) {
				return array.get(0);
			}
			throw new BusinessException(ResponseCode.FAIL.code, "节目不存在，请核实！");
		} else {
			throw new BusinessException(ResponseCode.FAIL.code, jsonObject.getString("code") + "|" + jsonObject.getString("message"));
		}
	}

	/**
	 * 刷新随机数
	 * 
	 * @param liveBroadcastId
	 * @return
	 */
	@Override
	public Object refreshQrcode(String liveBroadcastId) {
		if (StringUtil.isBlank(liveBroadcastId)) {
			throw new BusinessException(ResponseCode.FAIL.code, "参数异常！");
		}
		LiveBroadcast liveBroadcast = liveBroadcastMapperExt.selectByPrimaryKey(liveBroadcastId);
		if (liveBroadcast == null || "1".equals(liveBroadcast.getIsDelete()) || !"02".equals(liveBroadcast.getChannelType())) {
			throw new BusinessException(ResponseCode.FAIL.code, "节目信息不正确！");
		}
		liveBroadcast.setAnchorPassword(getRandom());
		int i = liveBroadcastMapperExt.updateByPrimaryKeySelective(liveBroadcast);
		if (i == 0) {
			throw new BusinessException(ResponseCode.FAIL.code, "更新失败！");
		}
		LiveBroadcastDto tmp = new LiveBroadcastDto();
		tmp.setAnchorPath(liveBroadcast.getAnchorPath());
		String url = propertyManager.urlKakasureMobileServer + "/app/qimanet/webcast.json?channelType=02&roomId=" + liveBroadcast.getLiveBroadcastId() + "&keys=" + liveBroadcast.getAnchorPassword();
		tmp.setGuestQrcodeUrl(url);
		tmp.setLiveBroadcastId(liveBroadcastId);
		return tmp;
	}

	/**
	 * 获取观众腾讯直播地址
	 */
	@Override
	public Object getLiveAddress(LiveBroadcastDto liveBroadcastDto) {
		// 判断节目
		LiveBroadcast liveBroadcast = liveBroadcastMapperExt.selectByPrimaryKey(liveBroadcastDto.getLiveBroadcastId());
		if (liveBroadcast == null || "1".equals(liveBroadcast.getIsDelete()) || IsDeleted.YES.value.equals(liveBroadcast.getLiveStatus())) {
			throw new BusinessException(ResponseCode.FAIL.code, "节目已失效");
		}
		// 重启腾讯直播
		restartQcloudLive(liveBroadcast);
		return liveBroadcast.getAnchorPath();
	}

	/**
	 * 重启直播
	 * 
	 * @param liveBroadcast
	 */
	private void restartQcloudLive(LiveBroadcast liveBroadcast) {
		QcloubLiveUtil qcloubLiveUtil = new QcloubLiveUtil();
		TreeMap<String, Object> params = new TreeMap<String, Object>();
		params.put("channelIds.1", liveBroadcast.getWebcastNumber());
		String stopRslt = qcloubLiveUtil.callQLiveByGetNoRegion("StopLVBChannel", params);
		if (StringUtil.isBlank(stopRslt)) {
			printInfoLog("腾讯直播频道{}STOP失败,返回空！", liveBroadcast.getWebcastNumber());
		}
		JSONObject stopJson = JSONObject.parseObject(stopRslt);
		if (0 != stopJson.getIntValue("code")) {
			printInfoLog("腾讯直播频道{}STOP失败：code[{}],message[{}]", liveBroadcast.getWebcastNumber(), stopJson.getString("code"), stopJson.getString("message"));
		} else {
			String startRslt = qcloubLiveUtil.callQLiveByGetNoRegion("StartLVBChannel", params);
			if (StringUtil.isBlank(startRslt)) {
				printInfoLog("腾讯直播频道{}START失败,返回空！", liveBroadcast.getWebcastNumber());
			}
		}
	}
}