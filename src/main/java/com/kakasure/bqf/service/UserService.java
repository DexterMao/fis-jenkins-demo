
package com.kakasure.bqf.service;

import com.kakasure.bqf.dao.dto.UserDto;
import com.kakasure.bqf.dao.model.User;

/**
 * 用户service
 * 
 * @author zhangb
 *
 */
public interface UserService {

	/**
	 * 注册
	 * 
	 * @param userDto
	 * @param clientId
	 * @return
	 */
	public User register(UserDto userDto, String clientId, boolean isNeedPhone);

	/**
	 * 修改密码
	 * 
	 * @param userDto
	 * @param userId
	 */
	public void changePassword(UserDto userDto, String userId);

}
