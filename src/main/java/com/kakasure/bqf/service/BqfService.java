
package com.kakasure.bqf.service;

import com.kakasure.bqf.dao.dto.UserplusDto;

/**
 * 码商信息service
 * 
 * @author zhangy
 *
 */
public interface BqfService {

	/**
	 * 根据userId查询用户信息
	 * 
	 * @param userId
	 * @return
	 */
	public UserplusDto saveUserplusByUserId(String userId);

	/**
	 * 根据userId查询用户手机号
	 * 
	 * @param userId
	 * @return
	 */
	public String getPhoneByUserId(String userId);

	/**
	 * 校验验证码
	 * 
	 * @param userVendor
	 */
	public void hasPhone(String phone, String code);

	/**
	 * 拿到手机号 生成一个验证码 发送信息 保存数据库
	 * 
	 * @param phone
	 * @throws Exception
	 */
	public void saveMobileCode(String phone, String codeStatus) throws Exception;

	/**
	 * 修改绑定手机号
	 * 
	 * @param userVendor
	 * @throws Exception
	 */
	public void editPhone(UserplusDto userplusDto) throws Exception;

	/**
	 * 修改用户信息
	 * 
	 * @param userplusDto
	 */
	public void editUserplus(UserplusDto userplusDto);

}
