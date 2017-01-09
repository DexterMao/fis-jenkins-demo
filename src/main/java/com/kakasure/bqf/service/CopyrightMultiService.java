
package com.kakasure.bqf.service;

import java.util.List;

import com.kakasure.bqf.dao.dto.ApiCopyrightmultiDto;
import com.kakasure.bqf.dao.dto.CopyrightmultiDto;
import com.kakasure.bqf.dao.model.VideoCategory;
import com.kakasure.lang.util.Page;

/**
 * 码商信息service
 * 
 * @author zhangy
 *
 */
public interface CopyrightMultiService {

	/**
	 * 获取版权方视频列表
	 * 
	 * @param page
	 * @param copyrightmultiDto
	 * @return
	 */
	public List<CopyrightmultiDto> getCopyrightmultiList(Page<CopyrightmultiDto> page, CopyrightmultiDto copyrightmultiDto);

	/**
	 * 删除多媒体
	 * 
	 * @param userId
	 * @param copyrightmultiId
	 * @throws Exception
	 */
	public void deleteById(String userId, String copyrightmultiId) throws Exception;

	/**
	 * 获取类目列表
	 * 
	 * @return
	 */
	public List<VideoCategory> getVideoCategory();

	/**
	 * 新增多媒体
	 * 
	 * @param copyrightmultiDto
	 */
	public void save(CopyrightmultiDto copyrightmultiDto);

	/**
	 * 修改多媒体
	 * 
	 * @param copyrightmultiDto
	 * @throws Exception
	 */
	public void updateMulti(CopyrightmultiDto copyrightmultiDto) throws Exception;

	/**
	 * 根据id 查找多媒体详情
	 * 
	 * @param copyrightmultiId
	 */
	public CopyrightmultiDto getCopyrightmultiById(String copyrightmultiId);

	/**
	 * 校验是否三十天有效期内
	 * 
	 * @param copyrightmultiId
	 * @param userId
	 * @param payType
	 * @return
	 * @throws Exception
	 */
	public boolean validityInterface(String copyrightmultiId, String userId, String payType) throws Exception;

	/**
	 * 根据id获取多媒体信息
	 * 
	 * @param ids
	 * @return
	 */
	public List<ApiCopyrightmultiDto> getFreeVideos(String ids, Boolean isPass);

	/**
	 * 获取多媒体列表
	 * 
	 * @param page
	 * @return
	 */
	public List<ApiCopyrightmultiDto> getFreeVideoList(Page<ApiCopyrightmultiDto> page);

	/**
	 * 扫码次数修改
	 * 
	 * @param COPYRIGHTMULTI_ID
	 */
	public void updateCode(String COPYRIGHTMULTI_ID);

	/**
	 * 扫码次数修改
	 * 
	 * @param COPYRIGHTMULTI_ID
	 */
	public void updateClickNum(String COPYRIGHTMULTI_ID);

	/**
	 * 
	 * @param announcemultiId
	 * @param userId
	 * @return
	 */
	public CopyrightmultiDto findCopyrightMultiByAnnId(String announcemultiId);

	/**
	 * 微信扫码 收费视频根据多媒体id用户id查询是否购买
	 * 
	 * @param copyrightmultiId
	 * @param userId
	 * @return
	 */
	public String findIsBuyBYUserId(String copyrightmultiId, String userId);

}
