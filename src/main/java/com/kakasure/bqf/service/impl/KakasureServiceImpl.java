
package com.kakasure.bqf.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.kakasure.bqf.dao.dto.UserDto;
import com.kakasure.bqf.dao.mapper.AnnouncemultiMapperExt;
import com.kakasure.bqf.dao.mapper.BalanceMapperExt;
import com.kakasure.bqf.dao.mapper.CopyrightmultiMapperExt;
import com.kakasure.bqf.dao.mapper.LiveBroadcastMapperExt;
import com.kakasure.bqf.dao.mapper.OrderMapperExt;
import com.kakasure.bqf.dao.mapper.UserMapperExt;
import com.kakasure.bqf.dao.model.Balance;
import com.kakasure.bqf.dao.model.Copyrightmulti;
import com.kakasure.bqf.dao.model.LiveBroadcast;
import com.kakasure.bqf.dao.model.Order;
import com.kakasure.bqf.dao.model.OrderExample;
import com.kakasure.bqf.dao.model.User;
import com.kakasure.bqf.dao.model.UserExample;
import com.kakasure.bqf.service.KakasureService;
import com.kakasure.bqf.util.Const;
import com.kakasure.common.util.KksKeyUtil;
import com.kakasure.common.util.MD5;
import com.kakasure.entity.kakasure.ApiAffiliateProductDto;
import com.kakasure.entity.kakasure.OrderData;
import com.kakasure.entity.kakasure.ResultOrder;
import com.kakasure.entity.kakasure.ResultProducts;
import com.kakasure.exception.ValidateException;
import com.kakasure.lang.PropertyManager;
import com.kakasure.lang.util.DateUtil;
import com.kakasure.lang.util.Tools;
import com.kakasure.lang.util.UrlUtil;
import com.kakasure.lang.util.UuidUtil;
import com.kakasure.lang.util.pay.MD5Signature;
import com.kakasure.lang.util.pay.ParameterUtil;
import com.kakasure.service.BaseService;

/**
 * 咔咔硕相关操作service
 * 
 * @author zhangb
 *
 */
@Service("kakasureService")
public class KakasureServiceImpl extends BaseService
										implements KakasureService {
	public static final String		KEY						= "yunear@kakasure";
	public static final String		URL_RECOMMEND_PRODUCTS	= "/api/recommend/products.json";

	@Resource
	private PropertyManager			propertyManager;

	@Resource
	private OrderMapperExt			orderMapperExt;
	@Resource
	private CopyrightmultiMapperExt	copyrightmultiMapperExt;
	@Resource
	private AnnouncemultiMapperExt	announcemultiMapperExt;
	@Resource
	private LiveBroadcastMapperExt	liveBroadcastMapperExt;
	@Resource
	private UserMapperExt			userMapperExt;
	@Resource
	private BalanceMapperExt		balanceMapperExt;

	@Override
	public String getSign(String keywordds) {
		return MD5.md5(keywordds + KEY + propertyManager.signKeySuffix
												+ propertyManager.signKeySuffix).toUpperCase();
	}

	/**
	 * 获取咔咔硕指定ID的商品
	 * 
	 * @param ids
	 * @return
	 */
	@Override
	public List<ApiAffiliateProductDto> getProducts(String keywords, String[] ids) {
		List<ApiAffiliateProductDto> list = null;
		if (StringUtils.isBlank(keywords))
			keywords = "";
		String url = propertyManager.urlAffiliateServer
												+ URL_RECOMMEND_PRODUCTS;

		Map<String, String> param = new HashMap<>();

		if (ids != null && ids.length > 0) {
			param.put("params[ids]", StringUtils.join(ids, ","));
		} else {
			param.put("params[keywords]", keywords);
		}

		param.put("sign", MD5.md5(keywords + KEY).toUpperCase());
		param.put("pageSize", "6");

		String jsonString = UrlUtil.getStringByGet(url, param);

		ResultProducts result = null;
		try {
			result = JSON.parseObject(jsonString, ResultProducts.class);
			if (result != null && result.getCode().equals(Const.CODE_SUCCESS)) {
				list = result.getData().getResults();
			} else {
				printWarnLog("Api result is not success,Result："
														+ (result != null ? JSON.toJSONString(result)
																								: null));
			}
		} catch (Exception e) {
			printErrorLog("JSON ERROR", e);
		}
		return list;
	}

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
	@Override
	public void synOrder(OrderData order) throws Exception {
		if (order == null) {
			throw new ValidateException(200500);
		}
		printInfoLog("咔咔硕订单同步开始，order_number:" + order.getOrder_number()
												+ ",status:" + order.getStatus()
												+ ",product:"
												+ order.getProduct()
												+ ",media_id:"
												+ order.getMedia_id()
												+ ",video_id:"
												+ order.getVideo_id());
		if (StringUtils.isBlank(order.getSign())) {
			throw new ValidateException(200101);
		}
		if (StringUtils.isBlank(order.getOrder())) {
			throw new ValidateException(100003);
		}
		if (StringUtils.isBlank(order.getOrder_number())) {
			throw new ValidateException(100004);
		}
		if (order.getTotal() == null) {
			throw new ValidateException(100005);
		}
		if (order.getCommision() == null) {
			throw new ValidateException(100006);
		}
		if (order.getStatus() == null) {
			throw new ValidateException(100009);
		}
		if (StringUtils.isBlank(order.getProduct())) {
			throw new ValidateException(100015);
		}
		if (StringUtils.isBlank(order.getMedia_id())
												&& StringUtils.isBlank(order.getVideo_id())) {
			throw new ValidateException(100014);
		}

		// 验证签名
		Map<String, String> param = new HashMap<String, String>();
		param.put("order", order.getOrder());
		param.put("order_number", order.getOrder_number());
		param.put("total", order.getTotal() + "");
		param.put("commision", order.getCommision() + "");
		param.put("product", order.getProduct());
		param.put("copyright_id", order.getCopyright_id());

		String signData = ParameterUtil.getSignData(param);
		String sign = MD5Signature.sign(signData, KksKeyUtil.KEY_QIMANET
												+ propertyManager.signKeySuffix);
		printDebugLog("sign:" + sign);
		printDebugLog("order sign:" + order.getSign());
		if (!sign.equals(order.getSign())) {
			throw new ValidateException(200100);
		}

		Copyrightmulti copyright = null;
		LiveBroadcast liveBroadcast = null;
		if (order.getType() == 1) {
			copyright = new Copyrightmulti();
			copyright = copyrightmultiMapperExt.selectByPrimaryKey(order.getVideo_id());
		} else {
			liveBroadcast = new LiveBroadcast();
			liveBroadcast = liveBroadcastMapperExt.selectByPrimaryKey(order.getVideo_id());
		}
		String userId = "";
		Double productConcession;
		if (copyright == null) {
			if (liveBroadcast == null) {
				throw new ValidateException(100012);
			} else {
				userId = liveBroadcast.getUserId();
				productConcession = liveBroadcast.getProductConcession();
			}
		} else {
			userId = copyright.getUserId();
			productConcession = copyright.getProductConcession();
		}
		if (StringUtils.isBlank(order.getCopyright_id())) {
			order.setCopyright_id(userId);
		}
		printInfoLog("版权方用户id  userId {}", userId);
		if (StringUtils.isBlank(order.getUsername())) {
			throw new ValidateException(100010);
		} else {
			// 根据用户名判断购买用户奇码网是否存在，以此获取用户ID
			UserExample example = new UserExample();
			example.createCriteria().andUsernameEqualTo(order.getUsername());
			List<User> users = userMapperExt.selectByExample(example);
			User user = new User();
			if (users.isEmpty()) {
				user.setUserId(UuidUtil.get32UUID());
				user.setPassword(MD5.md5("kakasure!@#456"));
				user.setUsername(order.getUsername());
				user.setLastLogin(DateUtil.getTime());
				userMapperExt.insertSelective(user);
				order.setBuy_user_id(user.getUserId());
				// throw new ValidateException(100013);
			} else {
				user = users.get(0);
				order.setBuy_user_id(user.getUserId());
			}
		}

		// 接下来还要保存订单信息以及计算平台、版权方、发布方的收入 --->yunear_order
		// 根据返回参数里面发布方ID是否为空，来分别进行计算收入
		BigDecimal commision = order.getCommision();
		BigDecimal yibai = new BigDecimal(100);
		BigDecimal cash_yunear = new BigDecimal(0);
		BigDecimal cash_copyright = new BigDecimal(0);
		BigDecimal cash_publish = new BigDecimal(0);

		// 发布方ID为空，按照奇码网定义规则4：4：2 进行收入的计算(视频码店)
		// if (StringUtils.isBlank(order.getPublish_id())) {
		// cash_yunear = commision.multiply(Const.ORDER_RATIO_YUNEAR);
		// cash_copyright = commision.multiply(Const.ORDER_RATIO_COPYRIGHT);
		// cash_publish = commision.multiply(Const.ORDER_RATIO_PUBLISH);
		// } else {

		// 发布方ID不为空，按照版权方上传视频时定义的分层比例，来计算版权方发布方的收入，奇码网平台在此的收入为空，不予计算。（扫码）
		if (StringUtils.isNotBlank(order.getPublish_id())) {
			String PRODUCT_CONCESSION = productConcession.toString();
			BigDecimal PUBLISH_PRODUCT_CONCESSION = new BigDecimal(PRODUCT_CONCESSION);
			BigDecimal COPYRIGHT_PRODUCT_CONCESSION = yibai.subtract(PUBLISH_PRODUCT_CONCESSION);

			BigDecimal ORDER_RATIO_PUBLISH = PUBLISH_PRODUCT_CONCESSION.divide(yibai);
			BigDecimal ORDER_RATIO_COPYRIGHT = COPYRIGHT_PRODUCT_CONCESSION.divide(yibai);

			cash_publish = commision.multiply(ORDER_RATIO_PUBLISH);
			cash_copyright = commision.multiply(ORDER_RATIO_COPYRIGHT);
		} else {
			// 为空全部的钱都给版权方
			cash_copyright = commision;
		}

		// }
		System.out.println("orderNumber" + order.getOrder_number());
		OrderExample orderExample = new OrderExample();
		orderExample.createCriteria().andOrderNumberEqualTo(order.getOrder_number());
		List<Order> orders = orderMapperExt.selectByExample(orderExample);
		Order order2 = new Order();
		String orderStatus = "";
		// 当订单编号查询的pd不为空时 只做修改状态。
		if (!orders.isEmpty()) {
			order2 = orders.get(0);
			order2.setTotal(order.getTotal().doubleValue());
			order2.setCommision(order.getCommision().doubleValue());
			order2.setCashYunear(cash_yunear.doubleValue());
			order2.setCashPublish(cash_publish.doubleValue());
			order2.setCashCopyright(cash_copyright.doubleValue());

			orderStatus = order2.getStatus().toString();
			order2.setStatus(order.getStatus());
			order2.setProduct(order.getProduct());
			orderMapperExt.updateByPrimaryKeySelective(order2);
			System.out.println("修改");
		} else {
			// 当订单编号查询的pd为空时，添加一条新的记录。
			order2 = new Order();
			order2.setOrderId(order.getOrder());
			order2.setOrderNumber(order.getOrder_number());
			order2.setDateCreate(new Date());
			order2.setTotal(order.getTotal().doubleValue());
			order2.setProduct(order.getProduct());
			order2.setMediaId(order.getVideo_id());
			order2.setType(order.getType());
			order2.setCopyrightId(userId);
			order2.setPublishId(order.getPublish_id());
			order2.setCashYunear(cash_yunear.doubleValue());
			order2.setCashPublish(cash_publish.doubleValue());
			order2.setCashCopyright(cash_copyright.doubleValue());
			order2.setStatus(order.getStatus());
			order2.setCommision(order.getCommision().doubleValue());
			order2.setBuyUserId(order.getBuy_user_id());
			order2.setBuyUserName(order.getUsername());
			order2.setDateModify(new Date());
			orderMapperExt.insertSelective(order2);
			System.out.println("新增");
		}

		// 交易完成才加余额，只加一次
		// 奇码网扫码观看的视频下面的产品产生的收益，奇码网不分账。与kks视频码店关联的多媒体下的产品产生的收益，奇码网分账
		// 。（区分条件，返回的参数里发布方ID是否）

		if (order.getStatus().equals(Const.OrderStateCode.SUCCESS.code)
												&& !orderStatus.equals(Const.OrderStateCode.SUCCESS.code)) {
			User user = new User();
			Balance balance = new Balance();
			// 修改版权方余额
			user.setUserId(order.getCopyright_id());
			user.setBalance(cash_copyright.doubleValue());
			userMapperExt.editBalance(user);

			balance.setUserId(order.getCopyright_id());
			balance.setBalance(cash_copyright);
			balanceMapperExt.editBalance(balance);

		}
	}

	/**
	 * 咔咔硕过来的简单注册
	 * 
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	@Override
	public User saveUserSimple(UserDto userDto) throws Exception {
		if (StringUtils.isBlank(userDto.getUserId())) {
			throw new ValidateException(300001);
		}
		if (StringUtils.isBlank(userDto.getUsername())) {
			throw new ValidateException(300001);
		}
		if (StringUtils.isBlank(userDto.getTimes())) {
			throw new ValidateException(200102);
		} else {
			Date now = new Date();
			if (Math.abs(now.getTime() - Long.parseLong(userDto.getTimes())) > 300000) {
				throw new ValidateException(200103);
			}
		}
		if (StringUtils.isBlank(userDto.getSign())) {
			throw new ValidateException(200101);
		}

		String keywords = userDto.getUserId() + userDto.getTimes();
		printInfoLog("keywords===" + keywords);
		String sign = MD5.md5(keywords + KEY).toUpperCase();
		printInfoLog("sign===" + sign);
		printInfoLog("params.sign===" + userDto.getSign());
		if (!sign.equals(userDto.getSign())) {
			throw new ValidateException(200100);
		}

		User user = new User();
		user.setRoleId(userDto.getRoleId());
		user.setIp(userDto.getIp());
		user.setUserId(userDto.getUserId());
		user.setUsername(userDto.getUsername());
		user.setPassword("kakasure!@#456");

		User userId = userMapperExt.selectByPrimaryKey(userDto.getUserId());
		if (userId == null) {
			UserExample userExample = new UserExample();
			userExample.createCriteria().andUsernameEqualTo(userDto.getUsername());
			List<User> list = userMapperExt.selectByExample(userExample);
			if (!list.isEmpty()) {
				user.setUsername(userDto.getUsername() + Tools.getRandomNum());
			}

			// if (newPd.getString("USERNAME").length() > 16) {
			// newPd.put("USERNAME", newPd.getString("USERNAME").substring(0,
			// 16));
			// }
			user.setPassword(MD5.md5(userDto.getPassword()));
			user.setLastLogin(DateUtil.getTime());
			userMapperExt.insertSelective(user);
		} else {
			user = userId;
		}

		User user2 = new User();
		user2.setUserId(user.getUserId());
		user2.setUsername(user.getUsername());
		user2.setLastLogin(user.getLastLogin());
		user2.setRoleId(user.getRoleId());
		user2.setIp(user.getIp());
		return user;

	}

	@Override
	public OrderData sendGetToKakasure(OrderData order) throws ValidateException {
		if (order == null || StringUtils.isBlank(order.getOrder())) {
			throw new ValidateException(200102);
		}

		String sign = MD5.md5(order.getOrder() + KEY).toUpperCase();
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("order", order.getOrder());
		map.put("sign", sign);
		String jsonString = UrlUtil.getStringByGet(propertyManager.urlKakasureMobileServer
												+ Const.URL_KAKASURE_SYN, map);
		ResultOrder result = null;
		order = null;
		result = JSON.parseObject(jsonString, ResultOrder.class);
		if (result != null && result.getCode().equals(Const.CODE_SUCCESS)) {
			order = result.getData();
		} else {
			printWarnLog("Api result is not success,Result：" + (result != null
													? JSON.toJSONString(result)
													: null));
		}
		return order;
	}

	@Override
	public BigDecimal getOrderPrice(OrderData order) throws Exception {
		if (order == null) {
			throw new ValidateException(200500);
		}
		if (StringUtils.isBlank(order.getOrder_number())) {
			throw new ValidateException(100004);
		}

		StringBuilder keywords = new StringBuilder();
		keywords.append(order.getOrder_number());
		String sign = MD5.md5(keywords + KEY).toUpperCase();
		printDebugLog("sign:" + sign);
		printDebugLog("order sign:" + order.getSign());
		if (!sign.equals(order.getSign())) {
			throw new ValidateException(200100);
		}

		OrderExample orderExample = new OrderExample();
		orderExample.createCriteria().andOrderNumberEqualTo(order.getOrder_number());
		List<Order> orders = orderMapperExt.selectByExample(orderExample);
		if (orders.isEmpty()) {
			throw new ValidateException(100011);
		}

		return new BigDecimal(orders.get(0).getCommision());
	}

}
