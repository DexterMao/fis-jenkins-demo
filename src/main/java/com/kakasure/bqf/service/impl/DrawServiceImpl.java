
package com.kakasure.bqf.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.kakasure.bqf.dao.dto.DrawDto;
import com.kakasure.bqf.dao.mapper.BalanceMapperExt;
import com.kakasure.bqf.dao.mapper.DrawMapperExt;
import com.kakasure.bqf.dao.mapper.UserMapperExt;
import com.kakasure.bqf.dao.mapper.UserplusMapperExt;
import com.kakasure.bqf.dao.model.Balance;
import com.kakasure.bqf.dao.model.BalanceExample;
import com.kakasure.bqf.dao.model.User;
import com.kakasure.bqf.dao.model.Userplus;
import com.kakasure.bqf.service.DrawService;
import com.kakasure.bqf.util.PoundageUtil;
import com.kakasure.exception.ValidateException;
import com.kakasure.lang.util.DateUtil;
import com.kakasure.lang.util.Page;
import com.kakasure.lang.util.UuidUtil;
import com.kakasure.lang.util.VerifyReg;
import com.kakasure.service.BaseService;

@Service
public class DrawServiceImpl extends BaseService implements DrawService {

	@Resource
	private BalanceMapperExt	balanceMapperExt;
	@Resource
	private UserMapperExt		userMapperExt;
	@Resource
	private DrawMapperExt		drawMapperExt;
	@Resource
	private UserplusMapperExt	userplusMapperExt;

	@Override
	public Balance balanceByUserId(String userId) {
		if (StringUtils.isBlank(userId)) {
			throw new ValidateException(200105);
		}
		User user = userMapperExt.selectByPrimaryKey(userId);
		if (user == null) {
			throw new ValidateException(200601);
		}
		Balance balance = balanceMapperExt.selectByPrimaryKey(userId);
		if (balance == null) {
			balance = new Balance();
			balance.setUserId(userId);
			balance.setBalance(new BigDecimal(user.getBalance()));
			balance.setCanPay(new BigDecimal(user.getBalance()));
			balanceMapperExt.insertSelective(balance);
		}
		balance.setBalance(balance.getBalance().setScale(2, RoundingMode.FLOOR));
		balance.setCanPay(balance.getCanPay().setScale(2, RoundingMode.FLOOR));
		balance.setPaying(balance.getPaying().setScale(2, RoundingMode.FLOOR));
		balance.setPaied(balance.getPaied().setScale(2, RoundingMode.FLOOR));
		return balance;
	}

	@Override
	public Userplus userplusByUserId(String userId) {
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
		return userplus;
	}

	@Override
	public List<DrawDto> getDrawPages(Page<DrawDto> page, DrawDto drawDto) {
		if (page == null) {
			page = new Page<>();
		}
		Map<String, Object> params = new HashMap<>();
		params.put("userId", drawDto.getUserId());
		params.put("status", drawDto.getStatus());
		params.put("startTime", drawDto.getStartTime());
		if (StringUtils.isNotBlank(drawDto.getEndTime())) {
			params.put("endTime", drawDto.getEndTime() + " 23:59:59");
		}
		page.setParams(params);
		List<DrawDto> drawDtos = drawMapperExt.getDrawPages(page);
		return drawDtos;
	}

	@Override
	public void saveDraw(DrawDto drawDto) {
		if (StringUtils.isBlank(drawDto.getUserId())) {
			throw new ValidateException(200105);
		}
		if (StringUtils.isBlank(drawDto.getDrawType())) {
			throw new ValidateException(400009);
		}
		if (!drawDto.getDrawType().equals("00") && !drawDto.getDrawType().toString().equals("01")) {
			throw new ValidateException(400010);
		}
		if (drawDto.getAmount() == null) {
			throw new ValidateException(400011);
		}
		if (VerifyReg.isAccount(drawDto.getAmount().toString())) {
			throw new ValidateException(400012);
		}
		// 查询可提现的金额
		Balance balance = balanceMapperExt.selectByPrimaryKey(drawDto.getUserId());
		if (balance.getCanPay().compareTo(new BigDecimal(drawDto.getAmount())) == -1) {
			throw new ValidateException(400012);
		}
		// 先查出当前用户的交易账号信息 然后根据支付类型 进行提现记录表的赋值
		Userplus userplus = userplusMapperExt.selectByPrimaryKey(drawDto.getUserId());
		if (userplus == null) {
			throw new ValidateException(200500);
		}
		boolean isSaveInfo = false;
		if (drawDto.getDrawType().equals("01")) {
			if (StringUtils.isNotBlank(userplus.getBankAccount())) {
				if (StringUtils.isNotBlank(userplus.getBankUserName())) {
					if (StringUtils.isNotBlank(userplus.getBankName())) {
						isSaveInfo = true;
					}
				}
			}
			if (isSaveInfo == false) {
				throw new ValidateException(400013);
			}
			drawDto.setIsShanghai(userplus.getBankType());
			drawDto.setIsTrafficBank(userplus.getBankLocation());
			drawDto.setIsCompany(userplus.getForcompny());
			drawDto.setDrawNumber(userplus.getBankAccount());
			drawDto.setDrawHolder(userplus.getBankUserName());
			drawDto.setDrawBank(userplus.getBankName());
		} else {
			if (StringUtils.isNotBlank(userplus.getAlipayAccount())) {
				if (StringUtils.isNotBlank(userplus.getAlipayname())) {
					isSaveInfo = true;
				}
			}
			if (isSaveInfo == false) {
				throw new ValidateException(400013);
			}
			drawDto.setDrawNumber(userplus.getAlipayAccount());
			drawDto.setDrawHolder(userplus.getAlipayname());
		}
		// 计算手续费
		Double poundage = PoundageUtil.getPoundage(drawDto.getDrawType(), userplus.getBankType(), userplus.getBankLocation(), userplus.getForcompny(), new BigDecimal(drawDto.getAmount()));
		if (poundage.compareTo(drawDto.getPoundage()) != 0) {
			printErrorLog("手续费计算错误 poundage {}withdrawals.getPoundage() {}", poundage, drawDto.getPoundage());
			throw new ValidateException(400014);
		}
		if (poundage.compareTo(drawDto.getAmount()) == 1) {
			throw new ValidateException(400012);
		}
		drawDto.setDrawId(UuidUtil.get32UUID());
		drawDto.setStatus("0");
		Date date = DateUtil.fomatDateHour(DateUtil.getTime());
		drawDto.setDateCreate(date);
		drawDto.setDateStatus(date);
		drawMapperExt.insertSelective(drawDto);

		// 修改用户余额
		// userBalance.setBalance(userBalance.getBalance().subtract(withdrawals.getAmount()));
		balance.setCanPay(balance.getCanPay().subtract(new BigDecimal(drawDto.getAmount())));
		balance.setPaying(balance.getPaying().add(new BigDecimal(drawDto.getAmount())));

		BalanceExample example = new BalanceExample();
		example.createCriteria().andUserIdEqualTo(drawDto.getUserId());
		balanceMapperExt.updateByExample(balance, example);

	}

}