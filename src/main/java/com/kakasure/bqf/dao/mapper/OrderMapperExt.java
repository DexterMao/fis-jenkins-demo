
package com.kakasure.bqf.dao.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.kakasure.bqf.dao.dto.MultiOrderDto;
import com.kakasure.bqf.dao.dto.OrderDto;
import com.kakasure.lang.util.Page;

@Repository
public interface OrderMapperExt extends OrderMapper {

	/**
	 * 获取发布方收入列表
	 * 
	 * @param pages
	 * @return
	 */
	List<MultiOrderDto> getMultiOrderList(Page<MultiOrderDto> pages);

	/**
	 * 获取商品订单列表
	 * 
	 * @param page
	 * @return
	 */
	public List<OrderDto> getProOrderList(Page<OrderDto> page);

	/**
	 * 获取视频订单列表
	 * 
	 * @param page
	 * @return
	 */
	public List<OrderDto> getMediaOrderList(Page<OrderDto> page);
}