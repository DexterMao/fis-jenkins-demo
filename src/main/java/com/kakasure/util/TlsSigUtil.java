package com.kakasure.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kakasure.bqf.dao.mapper.SysConfigMapperExt;
import com.kakasure.bqf.dao.model.SysConfig;
import com.kakasure.common.enums.ResponseCode;
import com.kakasure.exception.BusinessException;
import com.kakasure.lang.ServiceHelper;
import com.kakasure.lang.util.pay.StringUtil;
import com.tls.sigcheck.tls_sigcheck;

public class TlsSigUtil {
	private static SysConfigMapperExt	sysConfigMapperExt;
	private static String				APPID;
	private static String				EXPIRE_TIME;
	private static String				ADMIN_IDENTIFIER;
	private static String				ACCOUNT_TYPE;
	private static String				SERV_PLATFORM;
	private static Logger				logger				= LoggerFactory.getLogger(TlsSigUtil.class);
	private static final String			DLL_PATH_LINUX		= TlsSigUtil.class.getClassLoader().getResource("jnisigcheck_mt_x64.so").getPath();
	private static final String			DLL_PATH_WINDOWS	= TlsSigUtil.class.getClassLoader().getResource("jnisigcheck_mt_x64.dll").getPath();
	private static final String			PRIVATE_KEY_PATH	= TlsSigUtil.class.getClassLoader().getResource("qcloud/private_key.pem").getPath();
	private static final String			PUBLIC_KEY_PATH		= TlsSigUtil.class.getClassLoader().getResource("qcloud/public_key.pem").getPath();

	/**
	 * 签名
	 * 
	 * @param identifier
	 * @return
	 */
	public static String signatureTLS(String identifier) {
		if (sysConfigMapperExt == null) {
			sysConfigMapperExt = (SysConfigMapperExt) ServiceHelper.getService("sysConfigMapperExt");
		}
		if (StringUtil.isBlank(APPID)) {
			APPID = getAppId();
		}
		if (StringUtil.isBlank(EXPIRE_TIME)) {
			SysConfig sysConfig = sysConfigMapperExt.selectByPrimaryKey("QCLOUB.TIM.EXPIRETIME");
			if (sysConfig == null) {
				EXPIRE_TIME = "30000";
			}
			EXPIRE_TIME = sysConfig.getKksValue();
		}
		String sigStr = null;
		try {
			tls_sigcheck sigcheck = new tls_sigcheck();
			// 使用前请修改动态库的加载路径
			SERV_PLATFORM = getServPaltform();
			if ("windows".equals(SERV_PLATFORM)) {
				sigcheck.loadJniLib(DLL_PATH_WINDOWS);
			} else {
				sigcheck.loadJniLib(DLL_PATH_LINUX);
			}
			File priKeyFile = new File(PRIVATE_KEY_PATH);
			StringBuilder strBuilder = new StringBuilder();
			String s = "";
			BufferedReader br = new BufferedReader(new FileReader(priKeyFile));
			while ((s = br.readLine()) != null) {
				strBuilder.append(s + '\n');
			}
			br.close();
			String priKey = strBuilder.toString();
			// int ret = sigcheck.tls_gen_signature_ex2(APPID, identifier, priKey);
			int ret = sigcheck.tls_gen_signature_ex2_with_expire(APPID, identifier, priKey, EXPIRE_TIME);

			if (0 != ret) {
				logger.error("TLS签名返回码：{}，返回消息：{} ", ret, sigcheck.getErrMsg());
				throw new BusinessException(ResponseCode.FAIL.code, sigcheck.getErrMsg());
			} else {
				sigStr = sigcheck.getSig();
			}
		} catch (Exception e) {
			logger.error("TLS生成签名异常：" + e.getMessage(), e);
			throw new BusinessException(ResponseCode.FAIL.code, "TLS生成签名异常：" + e.getMessage());
		}
		return sigStr;
	}

	/**
	 * 验签
	 * 
	 * @param identifier
	 * @param signature
	 * @return
	 */
	public static int checkSignatureTls(String identifier, String signature) {
		if (sysConfigMapperExt == null) {
			sysConfigMapperExt = (SysConfigMapperExt) ServiceHelper.getService("sysConfigMapperExt");
		}
		if (StringUtil.isBlank(APPID)) {
			APPID = getAppId();
		}
		try {
			tls_sigcheck sigcheck = new tls_sigcheck();
			// 使用前请修改动态库的加载路径
			SERV_PLATFORM = getServPaltform();
			if ("windows".equals(SERV_PLATFORM)) {
				sigcheck.loadJniLib(DLL_PATH_WINDOWS);
			} else {
				sigcheck.loadJniLib(DLL_PATH_LINUX);
			}
			StringBuilder strBuilder = new StringBuilder();
			File pubKeyFile = new File(PUBLIC_KEY_PATH);
			BufferedReader br = new BufferedReader(new FileReader(pubKeyFile));
			String s = "";
			strBuilder.setLength(0);
			while ((s = br.readLine()) != null) {
				strBuilder.append(s + '\n');
			}
			br.close();
			String pubKey = strBuilder.toString();
			int ret = sigcheck.tls_check_signature_ex2(signature, pubKey, APPID, identifier);
			if (0 != ret) {
				logger.error("TLS验签返回码：{}，返回消息：{} ", ret, sigcheck.getErrMsg());
				throw new BusinessException(ResponseCode.FAIL.code, sigcheck.getErrMsg());
			}
			return ret;
		} catch (Exception e) {
			logger.error("TLS验签异常：" + e.getMessage(), e);
			throw new BusinessException(ResponseCode.FAIL.code, "TLS验签异常：" + e.getMessage());
		}
	}

	/**
	 * 获取APPID
	 * 
	 * @return
	 */
	public static String getAppId() {
		if (sysConfigMapperExt == null) {
			sysConfigMapperExt = (SysConfigMapperExt) ServiceHelper.getService("sysConfigMapperExt");
		}
		if (StringUtil.isBlank(APPID)) {
			SysConfig sysConfig = sysConfigMapperExt.selectByPrimaryKey("QCLOUB.TIM.APPID");
			if (sysConfig == null) {
				throw new BusinessException(ResponseCode.FAIL.code, "系统参数APPID未配置！");
			}
			APPID = sysConfig.getKksValue();
		}
		return APPID;
	}

	/**
	 * 获取管理员账号
	 * 
	 * @return
	 */
	public static String getAdminIdentifier() {
		if (sysConfigMapperExt == null) {
			sysConfigMapperExt = (SysConfigMapperExt) ServiceHelper.getService("sysConfigMapperExt");
		}
		if (StringUtil.isBlank(ADMIN_IDENTIFIER)) {
			SysConfig sysConfig = sysConfigMapperExt.selectByPrimaryKey("QCLOUB.TIM.ADMIN");
			if (sysConfig == null) {
				throw new BusinessException(ResponseCode.FAIL.code, "系统参数APPID未配置！");
			}
			ADMIN_IDENTIFIER = sysConfig.getKksValue();
		}
		return ADMIN_IDENTIFIER;
	}

	/**
	 * 获取账号类型
	 * 
	 * @return
	 */
	public static String getAccountType() {
		if (sysConfigMapperExt == null) {
			sysConfigMapperExt = (SysConfigMapperExt) ServiceHelper.getService("sysConfigMapperExt");
		}
		if (StringUtil.isBlank(ACCOUNT_TYPE)) {
			SysConfig sysConfig = sysConfigMapperExt.selectByPrimaryKey("QCLOUB.TIM.ACCOUNTTYPE");
			if (sysConfig == null) {
				throw new BusinessException(ResponseCode.FAIL.code, "系统参数APPID未配置！");
			}
			ACCOUNT_TYPE = sysConfig.getKksValue();
		}
		return ACCOUNT_TYPE;
	}

	/**
	 * 获取应用部署平台
	 * 
	 * @return
	 */
	public static String getServPaltform() {
		if (sysConfigMapperExt == null) {
			sysConfigMapperExt = (SysConfigMapperExt) ServiceHelper.getService("sysConfigMapperExt");
		}
		if (StringUtil.isBlank(SERV_PLATFORM)) {
			SysConfig sysConfig = sysConfigMapperExt.selectByPrimaryKey("QCLOUB.TIM.PLATFORM");
			if (sysConfig == null) {
				throw new BusinessException(ResponseCode.FAIL.code, "系统参数APPID未配置！");
			}
			SERV_PLATFORM = sysConfig.getKksValue();
		}
		return SERV_PLATFORM;
	}

}
