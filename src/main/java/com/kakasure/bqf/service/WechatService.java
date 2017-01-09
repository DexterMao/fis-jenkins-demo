
package com.kakasure.bqf.service;

import com.kakasure.entity.kakasure.OrderData;

public interface WechatService {

	/**
	 * 添加扫码次数
	 * 
	 * @throws Exception
	 */
	public void updateCodeNum(String announcemultiId, String userId);

	/**
	 * 支付成功后的回调
	 * 
	 * @param order
	 * @throws Exception
	 */
	public void synOrderMulti(OrderData order) throws Exception;

}
