
package com.kakasure.bqf.service;

import java.util.List;

import com.kakasure.bqf.dao.dto.LiveBqfGiftDto;
import com.kakasure.bqf.dao.dto.OrderDto;
import com.kakasure.bqf.dao.model.LiveBalance;
import com.kakasure.bqf.dao.model.Order;
import com.kakasure.lang.util.Page;

/**
 * 订单service
 * 
 * @author zhangy
 *
 */
public interface OrderService {

	/**
	 * 获取商品记录列表
	 * 
	 * @param page
	 * @param liveBroadcastDto
	 * @return
	 */
	public List<OrderDto> getProOrderList(Page<OrderDto> page, OrderDto order);

	/**
	 * 获取视频列表
	 * 
	 * @param page
	 * @param liveBroadcastDto
	 * @return
	 */
	public List<OrderDto> getMediaOrderList(Page<OrderDto> page, OrderDto order);

	/**
	 * 获取礼物列表
	 * 
	 * @param page
	 * @param liveBroadcastDto
	 * @return
	 */
	public List<LiveBqfGiftDto> getLiveBqfGiftList(Page<LiveBqfGiftDto> page, LiveBqfGiftDto liveBqfGiftDto);

	/**
	 * 获取礼物余额
	 * 
	 * @param userId
	 * @return
	 */
	public LiveBalance saveOrliveBalanceByUserId(String userId);

	/**
	 * 将礼物可转入金额 转入到账户余额
	 * 
	 * @param liveBalance
	 */
	public void saveLiveBalanceIntoBalance(String userId);

	/**
	 * 微信扫码生成订单记录
	 * 
	 * @param copyrightmultiId
	 * @param userId
	 */
	public Order saveOrderFromScancode(String announcemultiId, String userId);

	/**
	 * 根据orderNumber 查找订单记录
	 * 
	 * @param orderNumber
	 * @return
	 */
	public Order findOrderByOrderNumber(String orderNumber);
}
