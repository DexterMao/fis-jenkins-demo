
package com.kakasure.bqf.service.impl;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.kakasure.bqf.dao.mapper.AnnouncemultiMapperExt;
import com.kakasure.bqf.dao.mapper.BalanceMapperExt;
import com.kakasure.bqf.dao.mapper.CodenumDayMapperExt;
import com.kakasure.bqf.dao.mapper.CodenumMapperExt;
import com.kakasure.bqf.dao.mapper.CopyrightmultiMapperExt;
import com.kakasure.bqf.dao.mapper.LiveBroadcastMapperExt;
import com.kakasure.bqf.dao.mapper.OrderMapperExt;
import com.kakasure.bqf.dao.mapper.RoleMapperExt;
import com.kakasure.bqf.dao.mapper.UserMapperExt;
import com.kakasure.bqf.dao.model.Announcemulti;
import com.kakasure.bqf.dao.model.Balance;
import com.kakasure.bqf.dao.model.Codenum;
import com.kakasure.bqf.dao.model.CodenumDay;
import com.kakasure.bqf.dao.model.CodenumDayExample;
import com.kakasure.bqf.dao.model.Copyrightmulti;
import com.kakasure.bqf.dao.model.LiveBroadcast;
import com.kakasure.bqf.dao.model.Order;
import com.kakasure.bqf.dao.model.OrderExample;
import com.kakasure.bqf.dao.model.Role;
import com.kakasure.bqf.dao.model.RoleExample;
import com.kakasure.bqf.dao.model.User;
import com.kakasure.bqf.dao.model.UserExample;
import com.kakasure.bqf.service.KakasureService;
import com.kakasure.bqf.service.WechatService;
import com.kakasure.bqf.util.Const;
import com.kakasure.common.util.KksKeyUtil;
import com.kakasure.entity.kakasure.OrderData;
import com.kakasure.exception.BusinessException;
import com.kakasure.exception.ValidateException;
import com.kakasure.lang.PropertyManager;
import com.kakasure.lang.util.DateUtil;
import com.kakasure.lang.util.UuidUtil;
import com.kakasure.lang.util.pay.MD5Signature;
import com.kakasure.lang.util.pay.StringUtil;
import com.kakasure.service.BaseService;

/**
 * 微信功能
 * 
 * @author Administrator
 *
 */
@Service
public class WechatServiceImpl extends BaseService implements WechatService {

	@Resource
	private PropertyManager			propertyManager;
	@Resource
	private AnnouncemultiMapperExt	announcemultiMapperExt;
	@Resource
	private CopyrightmultiMapperExt	copyrightmultiMapperExt;
	@Resource
	private CodenumMapperExt		codenumMapperExt;
	@Resource
	private CodenumDayMapperExt		codenumDayMapperExt;
	@Resource
	private OrderMapperExt			orderMapperExt;
	@Resource
	private BalanceMapperExt		balanceMapperExt;
	@Resource
	private UserMapperExt			userMapperExt;
	@Resource
	private RoleMapperExt			roleMapperExt;
	@Resource
	private KakasureService			kakasureService;
	@Resource
	private LiveBroadcastMapperExt	liveBroadcastMapperExt;

	/**
	 * 增加扫码次数
	 */
	@Override
	public void updateCodeNum(String announcemultiId, String userId) {
		Date now = new Date();
		if (StringUtil.isBlank(announcemultiId)) {
			throw new ValidateException(200401);
		}
		announcemultiMapperExt.updateCode(announcemultiId);
		Announcemulti announcemulti = null;
		announcemulti = announcemultiMapperExt.selectByPrimaryKey(announcemultiId);
		// 版权方扫码次数统计
		String copyrightmultiId = announcemulti.getCopyrightmultiId();
		Copyrightmulti copyrightmulti = copyrightmultiMapperExt.selectByPrimaryKey(copyrightmultiId);
		copyrightmultiMapperExt.updateCode(copyrightmultiId);
		// 增加扫码记录
		Codenum codenum = new Codenum();
		String codenumId = UuidUtil.get32UUID();
		codenum.setCodenumId(codenumId);
		codenum.setCopyrightmultiId(copyrightmultiId);
		codenum.setBqfUserId(copyrightmulti.getUserId());
		codenum.setFbfUserId(announcemulti.getUserId());
		if (StringUtil.isNotBlank(userId)) {
			codenum.setUserId(userId);
		}
		codenum.setDateCreate(now);
		codenumMapperExt.insertSelective(codenum);
		// 更新天统计的扫码次数
		String dateStr = DateUtil.getDay();
		CodenumDayExample codenumDayExample = new CodenumDayExample();
		codenumDayExample.createCriteria().andCopyrightmultiIdEqualTo(copyrightmultiId).andDateDayEqualTo(dateStr).andBqfUserIdEqualTo(copyrightmulti.getUserId()).andFbfUserIdEqualTo(announcemulti.getUserId());
		List<CodenumDay> codenumDayList = codenumDayMapperExt.selectByExample(codenumDayExample);
		CodenumDay codenumDay = null;
		if (codenumDayList.isEmpty()) {
			codenumDay = new CodenumDay();
			codenumDay.setCodenumdayId(UuidUtil.get32UUID());
			codenumDay.setCopyrightmultiId(copyrightmultiId);
			codenumDay.setBqfUserId(copyrightmulti.getUserId());
			codenumDay.setFbfUserId(announcemulti.getUserId());
			codenumDay.setDateDay(dateStr);
			codenumDay.setNumber(1);
			codenumDayMapperExt.insertSelective(codenumDay);
		} else {
			codenumDay = codenumDayList.get(0);
			codenumDay.setNumber(codenumDay.getNumber() + 1);
			codenumDayMapperExt.updateByPrimaryKeySelective(codenumDay);
		}
	}

	@Override
	public void synOrderMulti(OrderData order) throws Exception {
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
		if (order.getCommision() == null) {
			throw new ValidateException(100006);
		}
		if (order.getStatus() == null) {
			throw new ValidateException(100009);
		}
		if (StringUtils.isBlank(order.getMedia_id())
												&& StringUtils.isBlank(order.getVideo_id())) {
			throw new ValidateException(100014);
		}

		String publish_id = order.getPublish_id();
		String media_id = order.getMedia_id();
		String order_number = order.getOrder_number();
		String commision = order.getCommision().doubleValue() + "";
		String data = publish_id + media_id + order_number + commision;

		printErrorLog("data {}", data);
		String sign = MD5Signature.sign(data, KksKeyUtil.KEY_QIMANET
												+ propertyManager.signKeySuffix).toUpperCase();

		if (!sign.equals(order.getSign())) {
			printErrorLog("sign == order.getSign() {} == {}", sign, order.getSign());
			throw new BusinessException(200500, "签名错误");
		}
		boolean isOrder = false;
		// 根据订单号 获取订单信息
		OrderExample orderExample = new OrderExample();
		orderExample.createCriteria().andOrderNumberEqualTo(order_number);
		List<Order> orders = orderMapperExt.selectByExample(orderExample);
		Order order1 = new Order();
		if (orders.isEmpty()) {
			order1.setOrderId(order.getOrder());
			order1.setOrderNumber(order.getOrder_number());
			order1.setCommision(order.getCommision().doubleValue());
			order1.setBuyUserId(order.getBuy_user_id());
			order1.setBuyUserName(order.getUsername());
			order1.setMediaId(order.getMedia_id());
			order1.setType(order.getType());
			order1.setCopyrightId(order.getCopyright_id());
			order1.setPublishId(order.getPublish_id());
			order1.setStatus(order.getStatus());
		} else {
			isOrder = true;
			order1 = orders.get(0);
		}

		printInfoLog("type {}" + order.getType());
		if (order.getType() == 1) {
			// 根据多媒体id查询信息
			Copyrightmulti copyrightmulti = copyrightmultiMapperExt.selectByPrimaryKey(order.getMedia_id());
			if (copyrightmulti == null) {
				throw new ValidateException(100012);
			}
		} else {
			// 根据节目id查询信息
			LiveBroadcast broadcast = liveBroadcastMapperExt.selectByPrimaryKey(order.getMedia_id());
			if (broadcast == null) {
				throw new ValidateException(100017);
			}
		}

		BigDecimal CONCESSION = new BigDecimal(order1.getCommision());
		// 收费按照 4:4:2分成
		// 除以100 和精确到小数点后6为
		MathContext mc = new MathContext(6, RoundingMode.HALF_DOWN);
		BigDecimal percent = new BigDecimal(100);
		// 管理员 余额计算
		BigDecimal yunearCash = CONCESSION.multiply(new BigDecimal(20));
		double yunearCashDouble = yunearCash.divide(percent, mc).doubleValue();
		printInfoLog("管理员金额 {}" + yunearCashDouble);
		// 发布方余额计算
		BigDecimal fbfCash = CONCESSION.multiply(new BigDecimal(40));
		double fbfCashDouble = fbfCash.divide(percent, mc).doubleValue();
		printInfoLog("发布方金额 {}" + fbfCashDouble);
		// 版权方余额计算
		BigDecimal bqfCash = CONCESSION.multiply(new BigDecimal(40));
		double bqfCashDouble = bqfCash.divide(percent, mc).doubleValue();
		printInfoLog("版权方 金额: {}" + bqfCashDouble);

		order1.setCashCopyright(bqfCashDouble);
		order1.setCashPublish(fbfCashDouble);
		order1.setCashYunear(yunearCashDouble);
		order1.setDateModify(new Date());
		if (!isOrder) {
			printInfoLog("视频订单 添加成功！");
			order1.setDateCreate(new Date());
			orderMapperExt.insertSelective(order1);
		} else {
			printInfoLog("视频订单 修改成功！");
			orderMapperExt.updateByPrimaryKeySelective(order1);

		}
		if (order.getStatus().equals(Const.OrderStateCode.SUCCESS.code)
												&& !order1.equals(Const.OrderStateCode.SUCCESS.code)) {
			User user2 = null;
			Balance balance = new Balance();
			// 管理员的余额修改
			Role role = new Role();
			RoleExample roleExample = new RoleExample();
			roleExample.createCriteria().andRoleNameEqualTo("平台管理员");
			List<Role> roles = roleMapperExt.selectByExample(roleExample);
			role = roles.get(0);
			UserExample userExample = new UserExample();
			userExample.createCriteria().andRoleIdEqualTo(role.getRoleId());
			List<User> users = userMapperExt.selectByExample(userExample);
			user2 = users.get(0);
			user2.setBalance(yunearCashDouble);
			userMapperExt.editBalance(user2);

			balance.setUserId(user2.getUserId());
			balance.setBalance(new BigDecimal(yunearCashDouble));
			balanceMapperExt.editBalance(balance);
			// 版权方余额修改
			user2.setUserId(order.getCopyright_id());
			user2.setBalance(bqfCashDouble);
			userMapperExt.editBalance(user2);
			balance.setUserId(order.getCopyright_id());
			balance.setBalance(new BigDecimal(bqfCashDouble));
			balanceMapperExt.editBalance(balance);
			// 发布方(码商)余额修改
			// printInfoLog("订单 码商佣金修改开始 调接口开始 userId {} amount {} order_number
			// {}", order.getPublish_id(), fbfCashDouble, order_number);
			// String path = propertyManager.urlAffiliateServer
			// + "/api/update/balance.json";
			// Map<String, String> param = new HashMap<String, String>();
			// param.put("userId", order.getPublish_id());
			// param.put("amount", fbfCashDouble + "");
			// String str = UrlUtil.getStringByPost(path, param);
			// ResultLive result = null;
			// result = JSON.parseObject(str, ResultLive.class);
			// if (result != null &&
			// result.getCode().equals(Const.CODE_SUCCESS)) {
			// printInfoLog("成功！{}" + result);
			// } else {
			// printErrorLog("Api result is not success,Result："
			// + (result != null ? JSON.toJSONString(result)
			// : null));
			// throw new ValidateException(200444);
			// }
		}

	}

}
