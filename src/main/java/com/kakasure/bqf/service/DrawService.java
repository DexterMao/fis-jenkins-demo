
package com.kakasure.bqf.service;

import java.util.List;

import com.kakasure.bqf.dao.dto.DrawDto;
import com.kakasure.bqf.dao.model.Balance;
import com.kakasure.bqf.dao.model.Userplus;
import com.kakasure.lang.util.Page;

/**
 * 码商信息service
 * 
 * @author zhangy
 *
 */
public interface DrawService {

	/**
	 * 根据userId 获取 Balance
	 * 
	 * @param userId
	 * @return
	 */
	public Balance balanceByUserId(String userId);

	/**
	 * 根据userId 获取 Userplus
	 * 
	 * @param userId
	 * @return
	 */
	public Userplus userplusByUserId(String userId);

	/**
	 * 获取提现列表
	 * 
	 * @param page
	 * @param drawDto
	 * @return
	 */
	public List<DrawDto> getDrawPages(Page<DrawDto> page, DrawDto drawDto);

	/**
	 * 提现
	 * 
	 * @param drawDto
	 */
	public void saveDraw(DrawDto drawDto);
}
