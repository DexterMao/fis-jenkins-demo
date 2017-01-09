package com.kakasure.util;

import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kakasure.bqf.dao.mapper.SysConfigMapperExt;
import com.kakasure.bqf.dao.model.SysConfig;
import com.kakasure.common.enums.ResponseCode;
import com.kakasure.exception.BusinessException;
import com.kakasure.lang.ServiceHelper;
import com.kakasure.lang.util.pay.StringUtil;
import com.qcloud.QcloudApiModuleCenter;
import com.qcloud.Module.Live;

public class QcloubLiveUtil {
	private static SysConfigMapperExt	sysConfigMapperExt;
	private static final String			DEFAULT_REGION			= "sh";
	private static final String			DEFAULT_REQUEST_METHOD	= "GET";
	private static String				QCLOUND_SECRETID		= null;
	private static String				QCLOUND_SECRETKEY		= null;
	private Logger						logger					= LoggerFactory.getLogger(getClass());

	/**
	 * 请求腾讯直播接口
	 * 
	 * @param actionName
	 *            接口指令名称
	 * @param requestMethod
	 *            请求方式：GET、POST
	 * @param region
	 *            区域参数，可选: gz:广州; sh:上海; hk:香港; ca:北美;等
	 * @param params
	 *            接口参数
	 * @return
	 */
	private String callQLive(String actionName, String requestMethod, String region, TreeMap<String, Object> params) {
		if (sysConfigMapperExt == null) {
			sysConfigMapperExt = (SysConfigMapperExt) ServiceHelper.getService("sysConfigMapperExt");
		}
		// 获取腾讯系统参数配置
		if (StringUtil.isBlank(QCLOUND_SECRETID)) {
			SysConfig sysConfig = sysConfigMapperExt.selectByPrimaryKey("QCLOUB.SecretId");
			if (sysConfig == null) {
				throw new BusinessException(ResponseCode.FAIL.code, "腾讯直播参数未配置");
			}
			QCLOUND_SECRETID = sysConfig.getKksValue();
		}
		if (StringUtil.isBlank(QCLOUND_SECRETKEY)) {
			SysConfig sysConfig = sysConfigMapperExt.selectByPrimaryKey("QCLOUB.SecretKey");
			if (sysConfig == null) {
				throw new BusinessException(ResponseCode.FAIL.code, "腾讯直播参数未配置");
			}
			QCLOUND_SECRETKEY = sysConfig.getKksValue();
		}
		/* 如果是循环调用下面举例的接口，需要从此处开始你的循环语句。切记！ */
		TreeMap<String, Object> config = new TreeMap<String, Object>();
		config.put("SecretId", QCLOUND_SECRETID);
		config.put("SecretKey", QCLOUND_SECRETKEY);
		/* 请求方法类型 POST、GET */
		if (StringUtil.isBlank(requestMethod)) {
			config.put("RequestMethod", DEFAULT_REQUEST_METHOD);
		} else {
			config.put("RequestMethod", requestMethod);
		}
		/* 区域参数，可选: gz:广州; sh:上海; hk:香港; ca:北美;等。 */
		if (StringUtil.isBlank(region)) {
			config.put("DefaultRegion", DEFAULT_REGION);
		} else {
			config.put("DefaultRegion", region);
		}
		QcloudApiModuleCenter module = new QcloudApiModuleCenter(new Live(), config);
		String result = null;
		try {
			/* call 方法正式向指定的接口名发送请求，并把请求参数params传入，返回即是接口的请求结果。 */
			result = module.call(actionName, params);
		} catch (Exception e) {
			logger.error("腾讯接口异常！" + e.getMessage(), e);
			throw new BusinessException(ResponseCode.FAIL.code, "腾讯直播执行异常:" + e.getMessage());
		}
		return result;
	}

	/**
	 * GET方式请求
	 * 
	 * @param actionName
	 *            接口指令名称
	 * @param region
	 *            区域参数，可选: gz:广州; sh:上海; hk:香港; ca:北美;等
	 * @param params
	 *            接口参数
	 * @return
	 */
	public String callQLiveByGet(String actionName, String region, TreeMap<String, Object> params) {
		return callQLive(actionName, DEFAULT_REQUEST_METHOD, region, params);
	}

	/**
	 * POST方式请求
	 * 
	 * @param actionName
	 *            接口指令名称
	 * @param region
	 *            区域参数，可选: gz:广州; sh:上海; hk:香港; ca:北美;等
	 * @param params
	 *            接口参数
	 * @return
	 */
	public String callQLiveByPost(String actionName, String region, TreeMap<String, Object> params) {
		return callQLive(actionName, "POST", region, params);
	}

	/**
	 * GET方式请求
	 * 
	 * @param actionName
	 *            接口指令名称
	 * @param region
	 *            区域参数，可选: gz:广州; sh:上海; hk:香港; ca:北美;等
	 * @param params
	 *            接口参数
	 * @return
	 */
	public String callQLiveByGetNoRegion(String actionName, TreeMap<String, Object> params) {
		return callQLive(actionName, DEFAULT_REQUEST_METHOD, null, params);
	}

	/**
	 * POST方式请求
	 * 
	 * @param actionName
	 *            接口指令名称
	 * @param region
	 *            区域参数，可选: gz:广州; sh:上海; hk:香港; ca:北美;等
	 * @param params
	 *            接口参数
	 * @return
	 */
	public String callQLiveByPostNoRegion(String actionName, TreeMap<String, Object> params) {
		return callQLive(actionName, "POST", null, params);
	}
}
