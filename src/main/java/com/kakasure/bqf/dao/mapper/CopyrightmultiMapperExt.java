
package com.kakasure.bqf.dao.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.kakasure.bqf.dao.dto.ApiCopyrightmultiDto;
import com.kakasure.bqf.dao.dto.CopyrightmultiDto;
import com.kakasure.bqf.dao.model.Copyrightmulti;
import com.kakasure.lang.util.Page;

@Repository
public interface CopyrightmultiMapperExt extends CopyrightmultiMapper {

	/**
	 * 获取推广的多媒体信息
	 * 
	 * @param page
	 * @return
	 */
	List<CopyrightmultiDto> getPushedMutilList(Page<CopyrightmultiDto> page);

	/**
	 * 获取推广的多媒体信息
	 * 
	 * @param pages
	 * @return
	 */
	List<CopyrightmultiDto> getUnExpandMultiList(Page<CopyrightmultiDto> pages);

	/**
	 * 获取多媒体列表
	 * 
	 * @param page
	 * @return
	 */
	public List<CopyrightmultiDto> getCopyrightmultiList(Page<CopyrightmultiDto> page);

	/**
	 * 根据卖家多媒体id查找 版权方多媒体信息
	 * 
	 * @param announceId
	 * @return
	 */
	public Copyrightmulti getCopyrightmultiByAnnounceId(String announceId);

	/**
	 * kks获取多媒体列表
	 * 
	 * @param page
	 * @return
	 */
	public List<ApiCopyrightmultiDto> getCopyrightmultiListByKks(Page<ApiCopyrightmultiDto> page);

	/**
	 * 扫码次数添加
	 * 
	 * @param COPYRIGHTMULTI_ID
	 */
	public void updateCode(String COPYRIGHTMULTI_ID);

	/**
	 * 点击次数修改
	 * 
	 * @param COPYRIGHTMULTI_ID
	 */
	public void updateClickNum(String COPYRIGHTMULTI_ID);

	/**
	 * 推广次数
	 * 
	 * @param COPYRIGHTMULTI_ID
	 */
	public void updateScanCode(String COPYRIGHTMULTI_ID);

}