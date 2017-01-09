
package com.kakasure.bqf.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.kakasure.bean.SsoUserDto;
import com.kakasure.bean.UserParam;
import com.kakasure.bqf.dao.dto.UserDto;
import com.kakasure.bqf.dao.mapper.BalanceMapperExt;
import com.kakasure.bqf.dao.mapper.MobileCodeMapperExt;
import com.kakasure.bqf.dao.mapper.RoleMapperExt;
import com.kakasure.bqf.dao.mapper.UserMapperExt;
import com.kakasure.bqf.dao.mapper.UserplusMapperExt;
import com.kakasure.bqf.dao.model.Balance;
import com.kakasure.bqf.dao.model.MobileCode;
import com.kakasure.bqf.dao.model.MobileCodeExample;
import com.kakasure.bqf.dao.model.Role;
import com.kakasure.bqf.dao.model.RoleExample;
import com.kakasure.bqf.dao.model.User;
import com.kakasure.bqf.dao.model.UserExample;
import com.kakasure.bqf.dao.model.Userplus;
import com.kakasure.bqf.service.UserService;
import com.kakasure.exception.ParameterException;
import com.kakasure.exception.ValidateException;
import com.kakasure.lang.PropertyManager;
import com.kakasure.lang.util.DateUtil;
import com.kakasure.lang.util.UuidUtil;
import com.kakasure.lang.util.VerifyReg;
import com.kakasure.oauth.util.OauthConstants;
import com.kakasure.security.opt.SsoOperate;
import com.kakasure.service.BaseService;

@Service
public class UserServiceImpl extends BaseService implements UserService {
	@Resource
	private PropertyManager		propertyManager;
	@Resource
	private UserMapperExt		userMapperExt;
	@Resource
	private MobileCodeMapperExt	mobileCodeMapperExt;
	@Resource
	private BalanceMapperExt	balanceMapperExt;
	@Resource
	private UserplusMapperExt	userplusMapperExt;
	@Resource
	private RoleMapperExt		roleMapperExt;

	@Override
	public User register(UserDto userDto, String clientId, boolean isNeedPhone) {
		if (userDto == null) {// 非法请求
			throw new ParameterException(200401);
		}
		RoleExample roleExample = new RoleExample();
		roleExample.createCriteria().andRoleNameEqualTo("版权方");
		List<Role> roles = roleMapperExt.selectByExample(roleExample);
		if (roles.isEmpty()) {
			throw new ValidateException(300022);
		}

		if (StringUtils.isBlank(userDto.getUsername())) {
			throw new ValidateException(300001);// 用户名不能为空
		}
		if (!VerifyReg.verifyUserName(userDto.getUsername())) {
			throw new ValidateException(300007);// 用户名格式不合法
		}

		if (StringUtils.isBlank(userDto.getPassword())) {
			throw new ValidateException(300002);// 密码不能为空
		}
		if (StringUtils.isBlank(userDto.getPassword2())) {
			throw new ValidateException(300018);// 确认密码不能为空
		}
		if (!userDto.getPassword().equals(userDto.getPassword2())) {
			throw new ValidateException(300019);// 2个密码不一致
		}
		if (!VerifyReg.VerifyPassword(userDto.getPassword())) {
			throw new ValidateException(300008);// 密码格式不合法
		}

		if (isNeedPhone) {
			if (StringUtils.isBlank(userDto.getPhone())) {
				throw new ValidateException(300010);// 手机号不能为空
			}
			if (!VerifyReg.IsHandset(userDto.getPhone())) {
				throw new ValidateException(300011);// 手机号格式不合法
			}
			// 校验验证码
			verifyMobileCode(userDto.getCode(), userDto.getPhone());
		}

		// 先判断用户名是否存在
		UserExample example = new UserExample();
		example.createCriteria().andUsernameEqualTo(userDto.getUsername()).andIsDeleteEqualTo("0");
		int num = userMapperExt.countByExample(example);
		if (num > 0) {
			throw new ValidateException(300005);// 用户已经存在
		}

		if (isNeedPhone) {
			UserExample example1 = new UserExample();
			example1.createCriteria().andPhoneEqualTo(userDto.getPhone()).andIsDeleteEqualTo("0");
			int numPHONE = userMapperExt.countByExample(example1);
			if (numPHONE > 0) {
				throw new ValidateException(300013);// 该手机号已被注册
			}
		}

		UserParam userParam = new UserParam();
		if (StringUtils.isBlank(clientId)) {// 门店的clientId，目前vendor和checker的密钥一样，所以不需要判定
			clientId = OauthConstants.client_id;
		}
		userParam.setUsername(userDto.getUsername());
		if (isNeedPhone) {
			userParam.setMobile(userDto.getPhone());
		}
		userParam.setPassword(userDto.getPassword());
		if (StringUtils.isNotBlank(userDto.getName())) {
			userParam.setName(userDto.getName());
		}
		printInfoLog("“" + userDto.getUsername() + "” 准备注册SSO...");
		SsoOperate ssoOperate = new SsoOperate(propertyManager.urlSsoServer, clientId, OauthConstants.client_secret);
		SsoUserDto user = ssoOperate.register(userParam);

		// 保存用户
		User record = new User();
		record.setUserId(UuidUtil.get32UUID());
		record.setGuid(user.getGuid());
		record.setUsername(userDto.getUsername());
		record.setName(StringUtils.isNotBlank(userDto.getName())
												? userDto.getName()
												: userDto.getUsername());
		record.setEmail(userDto.getEmail());
		record.setPhone(userDto.getPhone());
		record.setPassword(userDto.getPassword());
		record.setLastLogin(DateUtil.getTime(new Date()));
		record.setStatus("0");
		record.setBalance(0.00);
		record.setRoleId(roles.get(0).getRoleId());
		userMapperExt.insertSelective(record);

		// 添加积分余额表
		Balance userBalance = new Balance();
		userBalance.setUserId(record.getUserId());
		userBalance.setBalance(new BigDecimal(0));
		userBalance.setCanPay(new BigDecimal(0));
		userBalance.setPaying(new BigDecimal(0));
		userBalance.setPaied(new BigDecimal(0));
		balanceMapperExt.insertSelective(userBalance);

		// 新增版权方信息表
		Userplus userplus = new Userplus();
		userplus.setUserId(record.getUserId());
		userplus.setUserName(user.getUsername());
		userplus.setPhone(user.getPhone());
		userplus.setCreateTime(new Date());
		userplus.setUpdateTime(new Date());
		userplusMapperExt.insertSelective(userplus);
		return record;
	}

	private void verifyMobileCode(String code, String phone) {
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
			throw new ValidateException(300012);
		} else {
			if (!commonMobileCode.getCode().equals(code)) {
				throw new ValidateException(300012);
			} else if (commonMobileCode.getGmtEnd().before(date)) {
				throw new ValidateException(300020);
			}
		}
	}

	@Override
	public void changePassword(UserDto userDto, String userId) {

		if (StringUtils.isBlank(userDto.getPassword())) {
			throw new ValidateException(300002);// 密码不能为空
		}
		if (StringUtils.isBlank(userDto.getPasswordNew())) {
			throw new ValidateException(300021);// 新密码不能为空
		}
		if (!userDto.getPasswordNew2().equals(userDto.getPasswordNew())) {
			throw new ValidateException(300019);// 2个密码不一致
		}
		if (!VerifyReg.VerifyPassword(userDto.getPasswordNew())) {
			throw new ValidateException(300008);// 密码格式不合法
		}
		if (userDto.getPasswordNew().length() < 6) {
			throw new ValidateException(300008);// 密码格式不合法
		}
		User user = new User();
		user = userMapperExt.selectByPrimaryKey(userId);
		if (user == null) {
			throw new ValidateException(200601);
		}
		SsoOperate ssoOperate = new SsoOperate(propertyManager.urlSsoServer, OauthConstants.client_id, OauthConstants.client_secret);
		ssoOperate.updatePassword(user.getUsername(), userDto.getPassword(), userDto.getPasswordNew());
	}

}
