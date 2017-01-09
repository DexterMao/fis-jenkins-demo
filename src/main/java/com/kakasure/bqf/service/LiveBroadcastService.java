
package com.kakasure.bqf.service;

import java.util.List;

import com.kakasure.bqf.dao.dto.LiveBroadcastDto;
import com.kakasure.bqf.dao.dto.LiveQimanetShowDto;
import com.kakasure.entity.kakasure.KakasureLiveBqfGift;
import com.kakasure.lang.util.Page;

/**
 * 
 * @author zhangy
 *
 */
public interface LiveBroadcastService {

	/**
	 * 新增直播
	 * 
	 * @param copyrightmultiDto
	 */
	public void saveLive(LiveBroadcastDto liveBroadcast);

	/**
	 * 新增直播（腾讯）
	 * 
	 * @param copyrightmultiDto
	 */
	public void saveLiveTecent(LiveBroadcastDto liveBroadcast);

	/**
	 * 获取列表
	 * 
	 * @param page
	 * @param liveBroadcastDto
	 * @return
	 */
	public List<LiveBroadcastDto> getLiveBroList(Page<LiveBroadcastDto> page, LiveBroadcastDto liveBroadcastDto);

	/**
	 * 删除直播
	 * 
	 * @param deleted
	 */
	public void deleted(String liveBroadcastId);

	/**
	 * 根据ID查找直播
	 * 
	 * @param liveBroadcastId
	 * @return
	 */
	public LiveBroadcastDto getLiveById(String liveBroadcastId);

	/**
	 * 根据ids获取直播列表
	 * 
	 * @param ids
	 * @return
	 */
	public List<LiveBroadcastDto> getLivesById(String ids, Boolean isPass);

	/**
	 * kks获取直播列表
	 * 
	 * @param page
	 * @return
	 */
	public List<LiveBroadcastDto> getLiveBroListFromKks(Page<LiveBroadcastDto> page);

	/**
	 * 节目刷礼物同步接口
	 * 
	 * @param bqfGift
	 */
	public void savaLiveBqfGift(KakasureLiveBqfGift bqfGift);

	/**
	 * 获取聊天记录
	 * 
	 * @param webcastId
	 * @return
	 */
	public Object getChats(String webcastId);

	/**
	 * 获取直播主播信息
	 * 
	 * @param id
	 * @return
	 */
	LiveQimanetShowDto getLiveShowOrgInfo(String id, String version, Integer keys);

	/**
	 * 查询直播状态
	 * 
	 * @param liveBroadCastId
	 * @return
	 */
	Object getLiveChannelInfo(String liveBroadCastId);

	/**
	 * 刷新随机数
	 * 
	 * @param liveBroadcastId
	 * @return
	 */
	Object refreshQrcode(String liveBroadcastId);

	/**
	 * 获取观众直播地址
	 * 
	 * @param liveBroadcastDto
	 * @return
	 */
	Object getLiveAddress(LiveBroadcastDto liveBroadcastDto);
}
