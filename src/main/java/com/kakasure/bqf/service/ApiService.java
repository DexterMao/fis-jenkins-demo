
package com.kakasure.bqf.service;

import java.util.List;

import com.kakasure.bqf.dao.dto.CopyrightmultiDto;
import com.kakasure.bqf.dao.model.Announcemulti;
import com.kakasure.bqf.dao.model.Copyrightmulti;
import com.kakasure.bqf.dao.model.VideoCategory;
import com.kakasure.entity.kakasure.BqfUserDto;
import com.kakasure.lang.util.Page;

public interface ApiService {

	/**
	 * 获取类目列表
	 * 
	 * @param id
	 * @return
	 */
	List<VideoCategory> getVedioCategoryList(String id);

	/**
	 * 获取推广的多媒体列表
	 * 
	 * @param jsonPage
	 * @return
	 */
	Page<CopyrightmultiDto> getPushedMutilList(String jsonPage);

	/**
	 * 取消推广
	 * 
	 * @param announcemulti
	 */
	void cancelExpand(Announcemulti announcemulti);

	/**
	 * 获取推广的多媒体信息
	 * 
	 * @param jsonPage
	 * @return
	 */
	Object getUnExpandMultiList(String jsonPage);

	/**
	 * 发布方推广多媒体
	 * 
	 * @param announcemulti
	 */
	void expandMulti(Announcemulti announcemulti);

	/**
	 * 获取多媒体详情
	 * 
	 * @param copyrightmulti
	 * @return
	 */
	CopyrightmultiDto getCopyrightmultiDetail(Copyrightmulti copyrightmulti);

	/**
	 * 获取发布方收入列表
	 * 
	 * @param jsonPage
	 * @return
	 */
	Object getMultiOrderList(String jsonPage);

	/**
	 * 获取用户列表
	 * 
	 * @param page
	 * @return
	 */
	public List<BqfUserDto> getBqfusers(Page<BqfUserDto> page);

	/**
	 * admin 删除用户
	 * 
	 * @param model
	 * @param guid
	 */
	public void deleteUser(String guid);
}
