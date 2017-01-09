
package com.kakasure.bqf.service;

import java.math.BigDecimal;
import java.util.List;

import com.kakasure.bqf.dao.dto.UserDto;
import com.kakasure.bqf.dao.model.User;
import com.kakasure.entity.kakasure.ApiAffiliateProductDto;
import com.kakasure.entity.kakasure.OrderData;
import com.kakasure.exception.ValidateException;

/**
 * 咔咔硕相关操作service
 * 
 * @author zhangb
 *
 */
public interface KakasureService {

	public String getSign(String keywordds);

	/**
	 * 获取咔咔硕指定ID的商品
	 * 
	 * @param ids
	 * @return
	 */
	public List<ApiAffiliateProductDto> getProducts(String keywords, String[] ids);

	/**
	 * 同步订单
	 * 
	 * 100001=版权方不能为空 100002=发布方不能为空 100003=订单ID不能为空 100004=订单编号不能为空
	 * 100005=订单金额不能为空 100006=订单佣金不能为空 100010=购买用户ID不能为空
	 * 100012=该多媒体不存200500=非法操作 200100=签名校验失败
	 * 200101=缺少签名签名100014=多媒体ID不能为空100015=产品名称不能为空
	 * 
	 * @param order
	 * @throws Exception
	 */
	public void synOrder(OrderData order) throws Exception;

	/**
	 * 咔咔硕过来的简单注册
	 * 
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public User saveUserSimple(UserDto userDto) throws Exception;

	public OrderData sendGetToKakasure(OrderData order) throws ValidateException;

	public BigDecimal getOrderPrice(OrderData order) throws Exception;

}
