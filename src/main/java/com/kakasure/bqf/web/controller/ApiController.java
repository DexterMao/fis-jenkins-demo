
package com.kakasure.bqf.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kakasure.bqf.dao.dto.ApiCopyrightmultiDto;
import com.kakasure.bqf.dao.dto.CopyrightmultiDto;
import com.kakasure.bqf.dao.dto.LiveBroadcastDto;
import com.kakasure.bqf.dao.model.Announcemulti;
import com.kakasure.bqf.dao.model.Copyrightmulti;
import com.kakasure.bqf.dao.model.Order;
import com.kakasure.bqf.dao.model.VideoCategory;
import com.kakasure.bqf.service.ApiService;
import com.kakasure.bqf.service.CopyrightMultiService;
import com.kakasure.bqf.service.KakasureService;
import com.kakasure.bqf.service.LiveBroadcastService;
import com.kakasure.bqf.service.OrderService;
import com.kakasure.bqf.service.WechatService;
import com.kakasure.entity.kakasure.BqfUserDto;
import com.kakasure.entity.kakasure.KakasureLiveBqfGift;
import com.kakasure.entity.kakasure.OrderData;
import com.kakasure.lang.model.Protocol;
import com.kakasure.lang.util.Page;

@Controller
@RequestMapping(value = "api")
public class ApiController {
	@Resource
	private ApiService				apiService;
	@Resource
	private KakasureService			kakasureService;
	@Resource
	private CopyrightMultiService	copyrightmultiService;
	@Resource
	private LiveBroadcastService	liveBroadcastService;
	@Resource
	private WechatService			wechatService;
	@Resource
	private OrderService			orderService;

	public static final String		KEY	= "yunear@kakasure";

	/**
	 * ID可为空获取类目列表，否则根据ID获取类目名称！
	 * 
	 * @param model
	 * @param id
	 */
	@RequestMapping(value = "/getVedioCategoryList")
	public void getVedioCategoryList(Model model, String id) {
		Protocol protocol = new Protocol();
		protocol.setData(apiService.getVedioCategoryList(id));
		model.addAttribute(protocol);
	}

	/**
	 * 获取已推广的多媒体信息
	 * 
	 * @param model
	 * @param jsonPage
	 */
	@RequestMapping(value = "getPushedMutilList")
	public void getPushedMutilList(Model model, String jsonPage) {
		Protocol protocol = new Protocol();
		protocol.setData(apiService.getPushedMutilList(jsonPage));
		model.addAttribute(protocol);
	}

	/**
	 * 取消推广
	 * 
	 * @param model
	 * @param announcemulti
	 */
	@RequestMapping(value = "cancelExpand")
	public void cancelExpand(Model model, Announcemulti announcemulti) {
		apiService.cancelExpand(announcemulti);
		Protocol protocol = new Protocol();
		model.addAttribute(protocol);
	}

	/**
	 * 获取未推广的多媒体信息
	 * 
	 * @param model
	 * @param jsonPage
	 */
	@RequestMapping(value = "getUnExpandMultiList")
	public void getUnExpandMultiList(Model model, String jsonPage) {
		Protocol protocol = new Protocol();
		protocol.setData(apiService.getUnExpandMultiList(jsonPage));
		model.addAttribute(protocol);
	}

	/**
	 * 发布方推广多媒体
	 * 
	 * @param model
	 * @param announcemulti
	 */
	@RequestMapping(value = "expandMulti")
	public void expandMulti(Model model, Announcemulti announcemulti) {
		apiService.expandMulti(announcemulti);
		Protocol protocol = new Protocol();
		model.addAttribute(protocol);
	}

	/**
	 * 获取多媒体详情
	 * 
	 * @param model
	 * @param copyrightmulti
	 */
	@RequestMapping(value = "getCopyrightmultiDetail")
	public void getCopyrightmultiDetail(Model model, Copyrightmulti copyrightmulti) {
		Protocol protocol = new Protocol();
		protocol.setData(apiService.getCopyrightmultiDetail(copyrightmulti));
		model.addAttribute(protocol);
	}

	/**
	 * 获取发布方收入列表
	 * 
	 * @param model
	 * @param jsonPage
	 */
	@RequestMapping(value = "getMultiOrderList")
	public void getMultiOrderList(Model model, String jsonPage) {
		System.out.println(jsonPage);
		Protocol protocol = new Protocol();
		protocol.setData(apiService.getMultiOrderList(jsonPage));
		model.addAttribute(protocol);
	}

	/**
	 * 商品同步订单，由kakasure通知
	 * 
	 * @param order
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/synOrder")
	public void synOrder(OrderData order, Model model) throws Exception {
		Protocol protocol = new Protocol();
		kakasureService.synOrder(order);
		model.addAttribute(protocol);

	}

	/**
	 * 主动向咔咔硕要订单信息并同步
	 * 
	 * @param order
	 * @throws Exception
	 */
	@RequestMapping(value = "/synOrderSelf")
	public void synOrderSelf(Model model, OrderData order) throws Exception {
		Protocol protocol = new Protocol();
		order = kakasureService.sendGetToKakasure(order);
		if (order != null) {
			kakasureService.synOrder(order);
		}
		model.addAttribute(protocol);
	}

	/**
	 * 获取订单价格
	 * 
	 * @param order
	 *            有order_number & sign 参数
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/order")
	public void order(Model model, OrderData order) throws Exception {
		Protocol protocol = new Protocol();
		protocol.setData(kakasureService.getOrderPrice(order));
		model.addAttribute(protocol);
	}

	/**
	 * 视频咔咔硕通知订单已付款
	 * 
	 * @param order
	 *            有order_number & sign 参数
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/synOrder/multi")
	public void synOrderMulti(Model model, OrderData order) throws Exception {
		Protocol protocol = new Protocol();
		wechatService.synOrderMulti(order);
		model.addAttribute(protocol);
	}

	/**
	 * kks获取 视频
	 * 
	 * @param MEDIA_NAME
	 * @param DATE_CREATE_START
	 * @param DATE_CREATE_END
	 * @param page
	 * @param isPass
	 *            true/false 是否是必须已经通过的
	 * 
	 */
	@RequestMapping(value = "/getFreeVideoPage")
	public void getFreeVideoPage(Model model, String MEDIA_NAME, String type, String sortKey, String sortValue, Page<ApiCopyrightmultiDto> page, String ids, String hasFree, String category, Boolean isPass) {
		Protocol protocol = new Protocol();
		if (page == null) {
			page = new Page<ApiCopyrightmultiDto>();
		}
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("MEDIA_NAME", MEDIA_NAME);
		result.put("type", type);
		result.put("sortKey", sortKey);
		result.put("sortValue", sortValue);
		result.put("hasFree", hasFree);
		result.put("category", category);
		page.setParams(result);
		List<ApiCopyrightmultiDto> copyrightmultiDtos = null;
		if (StringUtils.isNotBlank(ids)) {
			copyrightmultiDtos = copyrightmultiService.getFreeVideos(ids, isPass);

		} else {
			copyrightmultiDtos = copyrightmultiService.getFreeVideoList(page);
		}
		page.setResults(copyrightmultiDtos);
		protocol.setData(page);
		model.addAttribute(protocol);
	}

	/**
	 * 添加扫码量
	 * 
	 * @param COPYRIGHTMULTI_ID
	 * @return
	 */
	@RequestMapping(value = "/addScanCodeNum")
	public void addScanCodeNum(Model model, String COPYRIGHTMULTI_ID) {
		Protocol protocol = new Protocol();
		copyrightmultiService.updateCode(COPYRIGHTMULTI_ID);
		model.addAttribute(protocol);
	}

	/**
	 * 添加点击量
	 * 
	 * @param COPYRIGHTMULTI_ID
	 * @return
	 */
	@RequestMapping(value = "/addClickNum")
	public void addClickNum(Model model, String COPYRIGHTMULTI_ID) {
		Protocol protocol = new Protocol();
		copyrightmultiService.updateClickNum(COPYRIGHTMULTI_ID);
		model.addAttribute(protocol);
	}

	/**
	 * 咔咔硕获取多媒体类目列表
	 */
	@RequestMapping(value = "/getCategory")
	public void getCategory(Model model) {
		Protocol protocol = new Protocol();
		List<VideoCategory> videoCategories = copyrightmultiService.getVideoCategory();
		protocol.setData(videoCategories);
		model.addAttribute(protocol);
	}

	/**
	 * kks获取 节目
	 * 
	 * @param page
	 * @param isPass
	 *            true/false 是否是必须已经通过的
	 * @return
	 */
	@RequestMapping(value = "/getLiveBroadcast")
	public void getLiveBroadcast(Model model, String NAME, Page<LiveBroadcastDto> page, String ids, String PAY_TYPE, String ONEBEFORE, String ONEAFTER, String DATE_START_TIME, String DATE_END_TIME, String STATUS, Boolean isPass) {
		if (page == null) {
			page = new Page<LiveBroadcastDto>();
		}
		Protocol protocol = new Protocol();
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("NAME", NAME);
		result.put("PAY_TYPE", PAY_TYPE);
		result.put("ONEBEFORE", ONEBEFORE);
		result.put("ONEAFTER", ONEAFTER);
		result.put("DATE_START_TIME", DATE_START_TIME);
		result.put("DATE_END_TIME", DATE_END_TIME);
		result.put("status", STATUS);
		page.setParams(result);
		List<LiveBroadcastDto> liveBroadcast = null;
		if (StringUtils.isNotBlank(ids)) {
			liveBroadcast = liveBroadcastService.getLivesById(ids, isPass);
		} else {
			liveBroadcast = liveBroadcastService.getLiveBroListFromKks(page);
		}
		page.setResults(liveBroadcast);
		protocol.setData(page);
		model.addAttribute(protocol);
	}

	/**
	 * 同步礼物记录，由affiliate通知
	 * 
	 * @param order
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/synLiveGift")
	public void synLiveGift(KakasureLiveBqfGift kakasureLiveBqfGift, Model model) throws Exception {
		Protocol protocol = new Protocol();
		liveBroadcastService.savaLiveBqfGift(kakasureLiveBqfGift);
		model.addAttribute(protocol);

	}

	/**
	 * 微信扫码 根据发布方id查找多媒体信息
	 * 
	 * @param model
	 * @param announceId
	 * @param userId
	 */
	@RequestMapping(value = "/media/byAnnId")
	public void findCopyrightMultiByAnnId(Model model, String announcemultiId, String userId, String isScanCode) {
		Protocol protocol = new Protocol();
		CopyrightmultiDto copyrightmultiDto = copyrightmultiService.findCopyrightMultiByAnnId(announcemultiId);
		if (StringUtils.isNotBlank(isScanCode)) {
			// 增加扫码次数
			wechatService.updateCodeNum(announcemultiId, userId);
		}
		protocol.setData(copyrightmultiDto);
		model.addAttribute(protocol);
	}

	/**
	 * 微信扫码 收费视频根据多媒体id用户id查询是否购买
	 * 
	 * @param model
	 * @param copyrightmultiId
	 * @param userId
	 */
	@RequestMapping(value = "/video/isBuy")
	public void findIsbuymultiByUserId(Model model, String copyrightmultiId, String userId) {
		Protocol protocol = new Protocol();
		protocol.setData(copyrightmultiService.findIsBuyBYUserId(copyrightmultiId, userId));
		model.addAttribute(protocol);
	}

	/**
	 * 微信扫码用户购买生成订单
	 * 
	 * @param model
	 * @param copyrightmultiId
	 * @param userId
	 */
	@RequestMapping(value = "/saveOrder")
	public void saveOrder(Model model, String announcemultiId, String userId) {
		Protocol protocol = new Protocol();
		Order order = orderService.saveOrderFromScancode(announcemultiId, userId);
		protocol.setData(order);
		model.addAttribute(protocol);
	}

	/**
	 * 根据orderNumber 查找订单记录
	 * 
	 * @param model
	 * @param orderNumber
	 */
	@RequestMapping(value = "/order/orderNumber")
	public void findOrderByOrderNumber(Model model, String orderNumber) {
		Protocol protocol = new Protocol();
		Order order = orderService.findOrderByOrderNumber(orderNumber);
		protocol.setData(order);
		model.addAttribute(protocol);
	}

	/**
	 * 获取腾讯主播信息
	 * 
	 * @param model
	 * @param id
	 */
	@RequestMapping(value = "qimanet/webcast")
	public void getLiveShowOrgInfo(Model model, String id, String version, Integer keys) {
		Protocol protocol = new Protocol();
		protocol.setData(liveBroadcastService.getLiveShowOrgInfo(id, version, keys));
		model.addAttribute(protocol);
	}

	/**
	 * 查询直播信息（腾讯）
	 * 
	 * @param model
	 * @param liveBroadcastId
	 */
	@RequestMapping(value = "getLiveChannelInfo")
	public void getLiveChannelInfo(Model model, String liveBroadcastId) {
		Protocol protocol = new Protocol();
		protocol.setData(liveBroadcastService.getLiveChannelInfo(liveBroadcastId));
		model.addAttribute(protocol);
	}

	/**
	 * admin 获取版权方用户列表
	 * 
	 * @param model
	 * @param username
	 * @param page
	 * @param startTime
	 * @param endTime
	 */
	@RequestMapping(value = "/bqf/users")
	public void getBqfUsers(Model model, String username, Page<BqfUserDto> page, String startTime, String endTime) {
		if (page == null) {
			page = new Page<BqfUserDto>();
		}
		Protocol protocol = new Protocol();
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("username", username);
		result.put("startTime", startTime);
		result.put("endTime", endTime);
		page.setParams(result);
		List<BqfUserDto> userDtos = null;
		userDtos = apiService.getBqfusers(page);
		page.setResults(userDtos);
		protocol.setData(page);
		model.addAttribute(protocol);
	}

	/**
	 * admin删除用户
	 * 
	 * @param model
	 * @param guid
	 */
	@RequestMapping(value = "/bqf/deleteUser")
	public void deleteUser(Model model, String guid) {
		Protocol protocol = new Protocol();
		apiService.deleteUser(guid);
		model.addAttribute(protocol);
	}

	/**
	 * 获取观众直播地址
	 * 
	 * @param model
	 * @param liveBroadcastDto
	 */
	@RequestMapping(value = "getLiveAddress")
	public void getLiveAddress(Model model, LiveBroadcastDto liveBroadcastDto) {
		Protocol protocol = new Protocol();
		protocol.setData(liveBroadcastService.getLiveAddress(liveBroadcastDto));
		model.addAttribute(protocol);
	}
}
