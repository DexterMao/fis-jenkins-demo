
package com.kakasure.bqf.web.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kakasure.bqf.dao.dto.LiveBqfGiftDto;
import com.kakasure.bqf.dao.dto.OrderDto;
import com.kakasure.bqf.dao.model.LiveBalance;
import com.kakasure.bqf.service.OrderService;
import com.kakasure.controller.BaseController;
import com.kakasure.lang.model.Protocol;
import com.kakasure.lang.util.Page;

/**
 * 类名称：CopyrightMultiController 创建人：zhangy
 */
@Controller
@RequestMapping(value = "/order")
public class OrderController extends BaseController {

	@Resource
	private OrderService orderService;

	/**
	 * 去商品记录页面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/goProductOrderlist")
	public String goProductOrderlist(Model model) {
		return "/order/produt_order_list";
	}

	/**
	 * 去视频记录页面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/goMediaOrderlist")
	public String goMediaOrderlist(Model model) {
		return "/order/media_order_list";
	}

	/**
	 * 去礼物记录页面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/goGiftOrderlist")
	public String goGiftOrderlist(Model model) {
		LiveBalance balance = orderService.saveOrliveBalanceByUserId(getLoginUser().getId());
		model.addAttribute("balance", balance);
		return "/order/gift_order_list";
	}

	/**
	 * 获取商品记录列表
	 * 
	 * @param page
	 * @param OrderDto
	 */
	@RequestMapping(value = "/getProOrderList")
	public void getProOrderList(Model model, Page<OrderDto> page, OrderDto order) {
		Protocol protocol = new Protocol();
		order.setCopyrightId(getLoginUser().getId());
		List<OrderDto> broadcastDtos = orderService.getProOrderList(page, order);
		page.setResults(broadcastDtos);
		protocol.setData(page);
		model.addAttribute("protocol", protocol);
	}

	/**
	 * 获取视频记录列表
	 * 
	 * @param page
	 * @param OrderDto
	 */
	@RequestMapping(value = "/getMediaOrderList")
	public void getMediaOrderList(Model model, Page<OrderDto> page, OrderDto order) {
		Protocol protocol = new Protocol();
		order.setCopyrightId(getLoginUser().getId());
		List<OrderDto> broadcastDtos = orderService.getMediaOrderList(page, order);
		page.setResults(broadcastDtos);
		protocol.setData(page);
		model.addAttribute("protocol", protocol);
	}

	/**
	 * 获取礼物收入列表
	 * 
	 * @param model
	 * @param page
	 * @param liveBqfGiftDto
	 */
	@RequestMapping(value = "/getLiveBqfGiftList")
	public void getLiveBqfGiftList(Model model, Page<LiveBqfGiftDto> page, LiveBqfGiftDto liveBqfGiftDto) {
		Protocol protocol = new Protocol();
		liveBqfGiftDto.setCopyrightUserId(getLoginUser().getId());
		List<LiveBqfGiftDto> liveBqfGiftDtos = orderService.getLiveBqfGiftList(page, liveBqfGiftDto);
		page.setResults(liveBqfGiftDtos);
		protocol.setData(page);
		model.addAttribute("protocol", protocol);
	}

	/**
	 * 转入礼物收入金额到账户余额
	 * 
	 * @param model
	 */
	@RequestMapping(value = "/intoBalance")
	public void intoBalance(Model model) {
		Protocol protocol = new Protocol();
		orderService.saveLiveBalanceIntoBalance(getLoginUser().getId());
		model.addAttribute("protocol", protocol);
	}
}