
package com.kakasure.bqf.service.impl;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.kakasure.bqf.dao.dto.UserplusDto;
import com.kakasure.bqf.dao.mapper.BalanceMapperExt;
import com.kakasure.bqf.dao.mapper.MobileCodeMapperExt;
import com.kakasure.bqf.dao.mapper.UserMapperExt;
import com.kakasure.bqf.dao.mapper.UserplusMapperExt;
import com.kakasure.bqf.dao.model.Balance;
import com.kakasure.bqf.dao.model.MobileCode;
import com.kakasure.bqf.dao.model.MobileCodeExample;
import com.kakasure.bqf.dao.model.User;
import com.kakasure.bqf.dao.model.UserExample;
import com.kakasure.bqf.dao.model.Userplus;
import com.kakasure.bqf.dao.model.UserplusExample;
import com.kakasure.bqf.service.BqfService;
import com.kakasure.common.enums.MessageType;
import com.kakasure.common.enums.ResponseCode;
import com.kakasure.common.util.EmojiFilterUtil;
import com.kakasure.common.util.KksKeyUtil;
import com.kakasure.exception.BusinessException;
import com.kakasure.exception.ValidateException;
import com.kakasure.lang.PropertyManager;
import com.kakasure.lang.model.Protocol;
import com.kakasure.lang.util.DateUtil;
import com.kakasure.lang.util.JSONUtil;
import com.kakasure.lang.util.Tools;
import com.kakasure.lang.util.UrlUtil;
import com.kakasure.lang.util.VerifyReg;
import com.kakasure.lang.util.pay.MD5Signature;
import com.kakasure.lang.util.pay.ParameterUtil;
import com.kakasure.oauth.util.OauthConstants;
import com.kakasure.security.opt.SsoOperate;
import com.kakasure.service.BaseService;

@Service
public class BqfServiceImpl extends BaseService implements BqfService {

	@Resource
	private PropertyManager		propertyManager;
	@Resource
	private UserplusMapperExt	userplusMapperExt;
	@Resource
	private UserMapperExt		userMapperExt;
	@Resource
	private MobileCodeMapperExt	mobileCodeMapperExt;
	@Resource
	private BalanceMapperExt	balanceMapperExt;

	@Override
	public UserplusDto saveUserplusByUserId(String userId) {
		if (StringUtils.isBlank(userId)) {
			throw new ValidateException(200105);
		}
		User user = userMapperExt.selectByPrimaryKey(userId);
		if (user == null) {
			throw new ValidateException(200601);
		}
		Userplus userplus = userplusMapperExt.selectByPrimaryKey(userId);
		if (userplus == null) {
			userplus = new Userplus();
			userplus.setUserId(userId);
			userplus.setUserName(user.getUsername());
			userplus.setPhone(user.getPhone());
			userplus.setCreateTime(new Date());
			userplusMapperExt.insertSelective(userplus);
		}
		Balance balance = balanceMapperExt.selectByPrimaryKey(userId);
		if (balance == null) {
			balance = new Balance();
			balance.setUserId(userId);
			balance.setBalance(new BigDecimal(user.getBalance()));
			balance.setCanPay(new BigDecimal(user.getBalance()));
			balanceMapperExt.insertSelective(balance);
		}
		ModelMapper mapper = new ModelMapper();
		UserplusDto userplusDto = mapper.map(userplus, UserplusDto.class);

		return userplusDto;
	}

	@Override
	public String getPhoneByUserId(String userId) {
		return userMapperExt.selectByPrimaryKey(userId).getPhone();
	}

	@Override
	public void hasPhone(String phone, String code) {
		if (StringUtils.isBlank(phone)) {
			throw new ValidateException(300010);// 手机号不能为空
		}
		if (!VerifyReg.IsHandset(phone)) {
			throw new ValidateException(300011);// 手机号格式不合法
		}
		// 校验验证码
		code = verifyMobileCode(code, phone);
		if (!code.equals("200000")) {
			if (code.equals("500101")) {
				throw new ValidateException(300012);
			}
			if (code.equals("500100")) {
				throw new ValidateException(300020);
			}
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public void saveMobileCode(String phone, String codeStatus) throws Exception {
		Date now = new Date();
		now.setMinutes(now.getMinutes() - 1);

		MobileCodeExample example = new MobileCodeExample();
		example.createCriteria().andMobileEqualTo(phone).andGmtCreatedGreaterThanOrEqualTo(now);
		example.setOrderByClause("gmt_created desc limit 1");
		List<MobileCode> mobileCodes = mobileCodeMapperExt.selectByExample(example);

		if (!mobileCodes.isEmpty()) {
			throw new ValidateException(300009);
		}

		String mobileCode = String.valueOf(Tools.getRandomNum());
		// smsRegister(phone, mobileCode, codeStatus);
		String msg = "";
		MessageType type = null;
		if (codeStatus.equals("register")) {// 如果是注册，校验下手机号是否已经存在
			UserExample userExample = new UserExample();
			userExample.createCriteria().andPhoneEqualTo(phone).andIsDeleteEqualTo("0");
			int num = userMapperExt.countByExample(userExample);

			if (num > 0) {
				throw new BusinessException(500016, "该手机号已经被使用");// 该手机号已被注册
			}

			type = MessageType.register_user_code;
			msg = "{\"code\":\"" + mobileCode + "\",\"product\":\"咔咔硕\"}";
		} else if (codeStatus.equals("Ephone")) {
			type = MessageType.user_info;
			msg = "{\"code\":\"" + mobileCode + "\",\"product\":\"咔咔硕\"}";
		}
		if (StringUtils.isBlank(msg)) {
			throw new BusinessException(200444, "发送验证码错误，请稍后重试");
		}
		if (type == null) {
			throw new BusinessException(200444, "发送验证码错误，请稍后重试");
		}

		Date date = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.MINUTE, 15);
		MobileCode record = new MobileCode();
		record.setMobile(phone);
		record.setCode(mobileCode);
		record.setGmtEnd(c.getTime());
		record.setGmtCreated(date);
		mobileCodeMapperExt.insertSelective(record);

		Map<String, String> param = new HashMap<>();
		param.put("type", type + "");
		param.put("times", new Date().getTime() + "");
		param.put("data", msg);
		param.put("mobile", phone);

		String signData = ParameterUtil.getSignData(param);
		String sign = MD5Signature.sign(signData, KksKeyUtil.KEY_ALIDAYU
												+ propertyManager.signKeySuffix);
		param.put("sign", sign);

		String result = UrlUtil.getStringByPost(propertyManager.urlKakasureMobileServer
												+ "/api/mobile/message.json", param);
		if (StringUtils.isBlank(result)) {
			printWarnLog("发送验证码失败： {}", result);
			throw new BusinessException(200444, "发送验证码错误，请稍后重试");
		}
		Protocol protocol = JSONUtil.getJson2Entity(result, Protocol.class);
		if (protocol != null && ResponseCode.SUCCESS.code.equals(protocol.getCode())) {
			// 成功
		} else {
			if (protocol != null && protocol.getCode() != null
													&& StringUtils.isNotBlank(protocol.getMsg())) {
				throw new BusinessException(protocol.getCode(), protocol.getMsg());
			} else {
				throw new BusinessException(200444, "发送验证码错误，请稍后重试");
			}
		}
	}

	@Override
	public void editPhone(UserplusDto userplusDto) throws Exception {
		if (StringUtils.isBlank(userplusDto.getUserId())) {
			throw new ValidateException(200105);
		}
		if (StringUtils.isBlank(userplusDto.getPhone())) {
			throw new ValidateException(300010);// 手机号不能为空
		}
		if (!VerifyReg.IsHandset(userplusDto.getPhone())) {
			throw new ValidateException(300011);// 手机号格式不合法
		}
		// 判断手机号是否已经注册
		UserExample example1 = new UserExample();
		example1.createCriteria().andPhoneEqualTo(userplusDto.getPhone());
		int numPHONE = userMapperExt.countByExample(example1);
		if (numPHONE > 0) {
			throw new ValidateException(300013);// 该手机号已被注册
		}
		// 校验验证码
		String code = verifyMobileCode(userplusDto.getCode(), userplusDto.getPhone());
		if (!code.equals("200000")) {
			if (code.equals("500101")) {
				throw new ValidateException(300012);
			}
			if (code.equals("500100")) {
				throw new ValidateException(300020);
			}
		}

		Userplus record = new Userplus();
		record = userplusMapperExt.selectByPrimaryKey(userplusDto.getUserId());
		// 先去sso改变手机号
		SsoOperate ssoOperate = new SsoOperate(propertyManager.urlSsoServer, OauthConstants.client_id, OauthConstants.client_secret);
		ssoOperate.updatePhone(record.getUserName(), userplusDto.getPhone());

		record.setPhone(userplusDto.getPhone());
		record.setUpdateTime(new Date());
		userplusMapperExt.updateByPrimaryKeySelective(record);

		User user = new User();
		user.setUserId(userplusDto.getUserId());
		user.setPhone(userplusDto.getPhone());
		userMapperExt.updateByPrimaryKeySelective(user);
	}

	public String verifyMobileCode(String code, String phone) {

		Date date = DateUtil.fomatDateHour(DateUtil.getTime());
		MobileCode commonMobileCode = null;
		MobileCodeExample example = new MobileCodeExample();
		example.createCriteria().andMobileEqualTo(phone);
		example.setOrderByClause("gmt_created desc");
		List<MobileCode> commonMobileCodes = mobileCodeMapperExt.selectByExample(example);
		if (commonMobileCodes != null && commonMobileCodes.size() > 0) {
			commonMobileCode = commonMobileCodes.get(0);
		}
		if (commonMobileCode == null) {
			code = "500101";
		} else {
			if (!commonMobileCode.getCode().equals(code)) {
				code = "500101";
			} else if (commonMobileCode.getGmtEnd().before(date)) {
				code = "500100";
			} else {
				code = "200000";
			}
		}

		return code;
	}

	@Override
	public void editUserplus(UserplusDto userplusDto) {
		Userplus userplus = new Userplus();
		if (StringUtils.isBlank(userplusDto.getUserId())) {
			throw new ValidateException(200105);
		}
		if (StringUtils.isBlank(userplusDto.getPhone())) {
			throw new ValidateException(300010);// 手机号不能为空
		}
		if (!VerifyReg.IsHandset(userplusDto.getPhone())) {
			throw new ValidateException(300011);// 手机号格式不合法
		}
		if (StringUtils.isBlank(userplusDto.getCode())) {
			throw new ValidateException(300014);
		}
		String code = verifyMobileCode(userplusDto.getCode(), userplusDto.getPhone());
		if (!code.equals("200000")) {
			if (code.equals("500101")) {
				throw new ValidateException(300012);
			}
			if (code.equals("500100")) {
				throw new ValidateException(300020);
			}
		}
		if (userplusDto.getSaveType().equals("1")) {
			userplus.setAlipayAccount(EmojiFilterUtil.filterEmoji(userplusDto.getAlipayAccount(), "*"));
			userplus.setAlipayname(EmojiFilterUtil.filterEmoji(userplusDto.getAlipayname(), "*"));
		} else {
			if (StringUtils.isBlank(userplusDto.getBankAccount())) {
				throw new ValidateException(300015);
			}
			if (!VerifyReg.IsNumber(userplusDto.getBankAccount())
													|| userplusDto.getBankAccount().length() > 40) {
				throw new ValidateException(300015);
			}
			if (StringUtils.isBlank(userplusDto.getBankName())
													|| userplusDto.getBankName().length() > 40) {
				throw new ValidateException(300016);
			}
			if (StringUtils.isBlank(userplusDto.getBankUserName())
													|| userplusDto.getBankUserName().length() > 40) {
				throw new ValidateException(300017);
			}
			userplus.setForcompny(userplusDto.getForcompny());
			userplus.setBankType(userplusDto.getBankType());
			userplus.setBankLocation(userplusDto.getBankLocation());
			userplus.setBankAccount(userplusDto.getBankAccount());
			userplus.setBankName(EmojiFilterUtil.filterEmoji(userplusDto.getBankName(), "*"));
			userplus.setBankUserName(EmojiFilterUtil.filterEmoji(userplusDto.getBankUserName(), "*"));
		}
		userplus.setUpdateTime(new Date());
		UserplusExample userplusExample = new UserplusExample();
		userplusExample.createCriteria().andUserIdEqualTo(userplusDto.getUserId());
		userplusMapperExt.updateByExampleSelective(userplus, userplusExample);
	}

}