package com.kakasure.util;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.kakasure.common.enums.ResponseCode;
import com.kakasure.exception.BusinessException;
import com.kakasure.lang.util.pay.StringUtil;

public class QcloundTimUtil {

	private static final String	ROOM_TYPE			= "AVChatRoom";
	private static final String	GROUP_CREATE_URL	= "https://console.tim.qq.com/v4/group_open_http_svc/create_group";
	private static final String	GROUP_DELETE_URL	= "https://console.tim.qq.com/v4/group_open_http_svc/destroy_group";

	/**
	 * 创建聊天室
	 * 
	 * @param groupName
	 * @return
	 */
	public static String createGroup(String groupName, String identifier) throws Exception {
		StringBuilder requestUrl = getRequestHeader(GROUP_CREATE_URL);
		Map<String, String> params = new HashMap<>();
		// params.put("Owner_Account", identifier + "|主播");
		params.put("Type", ROOM_TYPE);
		params.put("Name", groupName);
		String reqParam = JSONObject.toJSONString(params);
		return HttpClientUtil.postSend(requestUrl.toString(), reqParam);
	}

	/**
	 * 删除聊天室
	 * 
	 * @param groupId
	 * @return
	 * @throws Exception
	 */
	public static String deleteGroup(String groupId) throws Exception {
		StringBuilder requestUrl = getRequestHeader(GROUP_DELETE_URL);
		Map<String, String> params = new HashMap<>();
		params.put("GroupId", groupId);
		String reqParam = JSONObject.toJSONString(params);
		return HttpClientUtil.postSend(requestUrl.toString(), reqParam);
	}

	/**
	 * 获取请求报文头
	 * 
	 * @return
	 */
	private static StringBuilder getRequestHeader(String actionName) {
		StringBuilder requestUrl = new StringBuilder(actionName);
		requestUrl.append("?sdkappid=").append(TlsSigUtil.getAppId());
		String identifier = TlsSigUtil.getAdminIdentifier();
		requestUrl.append("&identifier=").append(identifier);
		String sig = TlsSigUtil.signatureTLS(identifier);
		if (StringUtil.isBlank(sig)) {
			throw new BusinessException(ResponseCode.FAIL.code, "获取签名信息异常");
		}
		requestUrl.append("&usersig=").append(sig).append("&random=").append(getRandom()).append("&contenttype=json");
		return requestUrl;
	}

	/**
	 * 获取随机数
	 * 
	 * @return
	 */
	private static String getRandom() {
		return String.valueOf(Math.random()).replace("0.", "");
	}
}
