
package com.kakasure.bqf.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.kakasure.bqf.dao.dto.LiveBqfGiftDto;
import com.kakasure.bqf.dao.dto.OrderDto;
import com.kakasure.bqf.dao.mapper.AnnouncemultiMapperExt;
import com.kakasure.bqf.dao.mapper.BalanceMapperExt;
import com.kakasure.bqf.dao.mapper.CopyrightmultiMapperExt;
import com.kakasure.bqf.dao.mapper.LiveBalanceMapperExt;
import com.kakasure.bqf.dao.mapper.LiveBqfGiftMapperExt;
import com.kakasure.bqf.dao.mapper.LiveBroadcastMapperExt;
import com.kakasure.bqf.dao.mapper.OrderMapperExt;
import com.kakasure.bqf.dao.model.Announcemulti;
import com.kakasure.bqf.dao.model.Balance;
import com.kakasure.bqf.dao.model.Copyrightmulti;
import com.kakasure.bqf.dao.model.LiveBalance;
import com.kakasure.bqf.dao.model.Order;
import com.kakasure.bqf.dao.model.OrderExample;
import com.kakasure.bqf.service.OrderService;
import com.kakasure.bqf.util.Const;
import com.kakasure.exception.BusinessException;
import com.kakasure.exception.ValidateException;
import com.kakasure.lang.PropertyManager;
import com.kakasure.lang.util.Page;
import com.kakasure.lang.util.Tools;
import com.kakasure.lang.util.UuidUtil;
import com.kakasure.service.BaseService;

@Service
public class OrderServiceImpl extends BaseService implements OrderService {
	@Resource
	private PropertyManager			propertyManager;
	@Resource
	private OrderMapperExt			orderMapperExt;
	@Resource
	private CopyrightmultiMapperExt	copyrightmultiMapperExt;
	@Resource
	private LiveBroadcastMapperExt	liveBroadcastMapperExt;
	@Resource
	private LiveBqfGiftMapperExt	liveBqfGiftMapperExt;
	@Resource
	private LiveBalanceMapperExt	liveBalanceMapperExt;
	@Resource
	private BalanceMapperExt		balanceMapperExt;
	@Resource
	private AnnouncemultiMapperExt	announcemultiMapperExt;

	@Override
	public List<OrderDto> getProOrderList(Page<OrderDto> page, OrderDto order) {
		if (page == null) {
			page = new Page<>();
		}
		Map<String, Object> params = new HashMap<>();
		params.put("userId", order.getCopyrightId());
		params.put("type", order.getType());
		params.put("product", order.getProduct());
		params.put("status", order.getStatusStr());
		page.setParams(params);
		List<OrderDto> orders = orderMapperExt.getProOrderList(page);
		return orders;
	}

	@Override
	public List<OrderDto> getMediaOrderList(Page<OrderDto> page, OrderDto order) {
		if (page == null) {
			page = new Page<>();
		}
		Map<String, Object> params = new HashMap<>();
		params.put("userId", order.getCopyrightId());
		params.put("multiName", order.getMultiName());
		page.setParams(params);
		List<OrderDto> orders = orderMapperExt.getMediaOrderList(page);
		return orders;
	}

	@Override
	public List<LiveBqfGiftDto> getLiveBqfGiftList(Page<LiveBqfGiftDto> page, LiveBqfGiftDto liveBqfGiftDto) {
		if (page == null) {
			page = new Page<>();
		}
		Map<String, Object> params = new HashMap<>();
		params.put("userId", liveBqfGiftDto.getCopyrightUserId());
		params.put("name", liveBqfGiftDto.getName());
		params.put("startTime", liveBqfGiftDto.getStartTime());
		if (StringUtils.isNotBlank(liveBqfGiftDto.getEndTime())) {
			params.put("endTime", liveBqfGiftDto.getEndTime() + " 23:59:59");
		}
		page.setParams(params);
		List<LiveBqfGiftDto> bqfGiftDtos = liveBqfGiftMapperExt.getLiveBqfGiftList(page);
		return bqfGiftDtos;
	}

	@Override
	public LiveBalance saveOrliveBalanceByUserId(String userId) {
		if (StringUtils.isBlank(userId)) {
			throw new ValidateException(200105);
		}
		LiveBalance balance = liveBalanceMapperExt.selectByPrimaryKey(userId);
		if (balance == null) {
			balance = new LiveBalance();
			balance.setUserId(userId);
			balance.setBalance(new BigDecimal(0));
			balance.setCanPay(new BigDecimal(0));
			liveBalanceMapperExt.insertSelective(balance);
		}
		balance.setBalance(balance.getBalance().setScale(2, RoundingMode.FLOOR));
		balance.setCanPay(balance.getCanPay().setScale(2, RoundingMode.FLOOR));
		return balance;
	}

	@Override
	public void saveLiveBalanceIntoBalance(String userId) {
		if (StringUtils.isBlank(userId)) {
			throw new ValidateException(200105);
		}
		LiveBalance liveBalance = liveBalanceMapperExt.selectByPrimaryKey(userId);
		BigDecimal canPay = liveBalance.getCanPay();
		if (liveBalance.getCanPay().compareTo(new BigDecimal(0)) != 1) {
			printErrorLog("转入金额为0 不能转入", liveBalance.getCanPay());
			throw new ValidateException(400015);
		}
		Balance balance = balanceMapperExt.selectByPrimaryKey(userId);
		if (balance == null) {
			balance = new Balance();
			balance.setUserId(userId);
			balance.setBalance(canPay);
			balance.setCanPay(canPay);
			balanceMapperExt.insertSelective(balance);
		} else {
			balance.setBalance(balance.getBalance().add(canPay));
			balance.setCanPay(balance.getCanPay().add(canPay));
			balanceMapperExt.updateByPrimaryKeySelective(balance);
		}
		liveBalance.setCanPay(new BigDecimal(0));
		liveBalanceMapperExt.updateByPrimaryKeySelective(liveBalance);
	}

	@Override
	public Order saveOrderFromScancode(String announcemultiId, String userId) {
		if (StringUtils.isBlank(announcemultiId) && StringUtils.isBlank(userId)) {
			throw new ValidateException(200401);
		}
		if (StringUtils.isBlank(announcemultiId) && StringUtils.isBlank(userId)) {
			throw new ValidateException(200401);
		}
		Order order = new Order();
		if (StringUtils.isBlank(announcemultiId) && StringUtils.isBlank(userId)) {
			throw new ValidateException(200401);
		}
		Announcemulti announcemulti = announcemultiMapperExt.selectByPrimaryKey(announcemultiId);
		if (announcemulti == null) {
			throw new BusinessException(200044, "推广信息不存在！");
		}
		Copyrightmulti copyrightmulti = copyrightmultiMapperExt.selectByPrimaryKey(announcemulti.getCopyrightmultiId());
		if (copyrightmulti == null) {
			throw new BusinessException(200044, "多媒体信息不存在！");
		}
		// 已登录 就判断这个用户是否购买过此多媒体 同一多媒体 不同码商推广 只需购买一次即可 分钱 只分第一次购买的码商
		OrderExample orderExample = new OrderExample();
		orderExample.createCriteria().andMediaIdEqualTo(copyrightmulti.getCopyrightmultiId()).andBuyUserIdEqualTo(userId);
		List<Order> orderList = orderMapperExt.selectByExample(orderExample);
		if (!orderList.isEmpty()) {
			order = orderList.get(0);
			if (!order.getStatus().equals("100")) {
				order.setPublishId(announcemulti.getUserId());
				orderMapperExt.updateByPrimaryKeySelective(order);
			}
		} else {
			// TOOD 是否计算比例后的价格 加入到order表中
			order.setOrderId(UuidUtil.get32UUID());
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");// 设置日期格式
			order.setOrderNumber(df.format(new Date()) + Tools.getRandomNum());
			order.setCommision(copyrightmulti.getPrice());
			order.setMediaId(copyrightmulti.getCopyrightmultiId());
			order.setCopyrightId(announcemulti.getCopyrightUserId());
			order.setPublishId(announcemulti.getUserId());
			order.setBuyUserId(userId);
			order.setStatus(Const.OrderStateCode.CREATE.code);
			order.setDateCreate(new Date());
			order.setDateModify(new Date());
			orderMapperExt.insertSelective(order);
		}

		return order;
	}

	@Override
	public Order findOrderByOrderNumber(String orderNumber) {
		Order order = null;
		if (StringUtils.isBlank(orderNumber)) {
			throw new ValidateException(200401);
		}
		OrderExample orderExample = new OrderExample();
		orderExample.createCriteria().andOrderNumberEqualTo(orderNumber);
		List<Order> orderList = orderMapperExt.selectByExample(orderExample);
		if (!orderList.isEmpty()) {
			order = orderList.get(0);
		}
		return order;
	}

}