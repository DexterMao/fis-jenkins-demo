
package com.kakasure.bqf.web.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kakasure.bqf.dao.dto.LiveBroadcastDto;
import com.kakasure.bqf.service.LiveBroadcastService;
import com.kakasure.common.enums.ResponseCode;
import com.kakasure.controller.BaseController;
import com.kakasure.exception.BusinessException;
import com.kakasure.lang.PropertyManager;
import com.kakasure.lang.model.Protocol;
import com.kakasure.lang.util.Page;
import com.kakasure.lang.util.pay.StringUtil;

/**
 * 类名称：LiveBroadcastController 创建人：zhangy
 */
@Controller
@RequestMapping(value = "/liveBroadcast")
public class LiveBroadcastController extends BaseController {
	@Resource
	private LiveBroadcastService	liveBroadcastService;
	@Resource
	private PropertyManager			propertyManager;

	/**
	 * 去直播添加页面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/goAddLive")
	public String goAddLive(Model model) {
		model.addAttribute("host_image", propertyManager.imageHost);
		return "/live/liveBroadcast_insert";
	}

	/**
	 * 去切图片页面
	 */
	@RequestMapping(value = "/cropperImg")
	public String cropperImg(Model model, String imgUrl) {
		model.addAttribute("imgUrl", imgUrl);
		return "/live/cropper_img";
	}

	@RequestMapping(value = "/golivelist")
	public String golivelist(Model model) {
		return "/live/liveBroadcast_list";
	}

	/**
	 * 聊天记录页面
	 * 
	 * @param model
	 * @param webcastId
	 * @return
	 */
	@RequestMapping(value = "toChatList")
	public String goChatList(Model model, String webcastId) {
		model.addAttribute("webcastId", webcastId);
		return "live/liveChat_list";
	}

	/**
	 * 去协议页面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/goxieyi")
	public String goxieyi(Model model) {
		return "/live/liveBroadcast_xieyi";
	}

	/**
	 * 新增直播
	 * 
	 * @param model
	 * @param liveBroadcast
	 */
	@RequestMapping(value = "/saveLive")
	public void saveLive(Model model, LiveBroadcastDto liveBroadcastDto) {
		Protocol protocol = new Protocol();
		liveBroadcastDto.setUserId(getLoginUser().getId());
		String channelType = liveBroadcastDto.getChannelType();
		if (StringUtil.isNotBlank(channelType) && "02".equals(channelType)) {
			liveBroadcastService.saveLiveTecent(liveBroadcastDto);
		} else {
			throw new BusinessException(ResponseCode.FAIL.code, "异常请求！");
		}
		model.addAttribute("protocol", protocol);
	}

	/**
	 * 获取版权方多媒体列表
	 * 
	 * @param page
	 * @param copyrightmultiDto
	 */
	@RequestMapping(value = "/getLiveBroList")
	public void getLiveBroList(Model model, Page<LiveBroadcastDto> page, LiveBroadcastDto liveBroadcastDto) {
		Protocol protocol = new Protocol();
		liveBroadcastDto.setUserId(getLoginUser().getId());
		List<LiveBroadcastDto> broadcastDtos = liveBroadcastService.getLiveBroList(page, liveBroadcastDto);
		page.setResults(broadcastDtos);
		protocol.setData(page);
		model.addAttribute("protocol", protocol);
	}

	/**
	 * 删除直播
	 * 
	 * @param model
	 * @param liveBroadcastId
	 */
	@RequestMapping(value = "/deleted")
	public void deleted(Model model, String liveBroadcastId) {
		Protocol protocol = new Protocol();
		liveBroadcastService.deleted(liveBroadcastId);
		model.addAttribute("protocol", protocol);
	}

	@RequestMapping(value = "/getLiveById")
	public String getLiveById(Model model, String liveBroadcastId) {
		LiveBroadcastDto broadcastDto = liveBroadcastService.getLiveById(liveBroadcastId);
		model.addAttribute("lb", broadcastDto);
		return "/live/liveBroadcast_lock";
	}

	/**
	 * 获取聊天记录
	 * 
	 * @param model
	 * @param webcastId
	 */
	@RequestMapping(value = "getChats")
	public void getChats(Model model, String webcastId) {
		Protocol protocol = new Protocol();
		protocol.setData(liveBroadcastService.getChats(webcastId));
		model.addAttribute(protocol);
	}

	/**
	 * 刷新随机数
	 * 
	 * @param model
	 * @param liveBroadcastId
	 */
	@RequestMapping(value = "refreshQrcode")
	public void refreshQrcode(Model model, String liveBroadcastId) {
		Protocol protocol = new Protocol();
		protocol.setData(liveBroadcastService.refreshQrcode(liveBroadcastId));
		model.addAttribute(protocol);
	}
}
