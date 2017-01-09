
package com.kakasure.bqf.util;

import java.util.Base64;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.DigestUtils;

public class NginxUtil {

	public static final String	SECRET	= "wahaha";

	/**
	 * 默认1小时过期
	 */
	public static final int		EXPIRE	= 3600;

	/**
	 * 添加nginx的加密参数，用于买家观看（默认一天有效）
	 * 
	 * @param urlPath
	 * @return
	 */
	public static String addSecurelinkForBuyer(String urlPath) {
		return addSecurelink(urlPath, EXPIRE * 24);
	}

	/**
	 * 添加nginx的加密参数，用于管理员审核（默认1小时有效）
	 * 
	 * @param urlPath
	 * @return
	 */
	public static String addSecurelinkForApply(String urlPath) {
		return addSecurelink(urlPath, EXPIRE);
	}

	/**
	 * 添加nginx的加密参数
	 * 
	 * @param urlPath
	 * @param expire
	 *            过期时间，单位秒
	 * @return
	 */
	public static String addSecurelink(String urlPath, int expire) {
		if (StringUtils.isBlank(urlPath)) {
			return urlPath;
		}

		if (expire == 0) {
			expire = EXPIRE;
		}

		long t = new Date().getTime() / 1000 + expire;

		return urlPath
				+ "?st="
				+ Base64.getEncoder()
						.encodeToString(DigestUtils.md5Digest((SECRET + urlPath + t).getBytes()))
						.replaceAll("\\+", "-").replaceAll("\\/", "_").replaceAll("=", "") + "&e="
				+ t;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
