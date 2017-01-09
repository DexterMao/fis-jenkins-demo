
package com.kakasure.bqf.web.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kakasure.bqf.service.BqfService;
import com.kakasure.bqf.service.UserService;
import com.kakasure.common.enums.ResponseCode;
import com.kakasure.controller.BaseController;
import com.kakasure.lang.PropertyManager;
import com.kakasure.lang.model.Protocol;

/**
 * 首页
 * 
 * @author zhangb
 *
 */
@Controller
public class IndexController extends BaseController {

	@Resource
	private PropertyManager	propertyManager;
	@Resource
	private UserService		userService;
	@Resource
	private BqfService		bqfService;

	/**
	 * 首页
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = { "", "index" }, method = RequestMethod.GET)
	public String index(Model model) throws Exception {
		return "redirect:/copyrightmulti/goCopyrightList";
	}

	/**
	 * 拿到手机号 生成一个验证码 发送信息 保存数据库
	 * 
	 * @param model
	 * @param phone
	 * @throws Exception
	 */
	@RequestMapping(value = "/index/bqf/mobileCode")
	public void saveMobileCode(Model model, String phone, String codeStatus, String code) throws Exception {
		
		Protocol protocol = new Protocol();
		
		if(checkCode(code)){
			bqfService.saveMobileCode(phone, codeStatus);
		} else {
			protocol.setCode(ResponseCode.FAIL.code);
			protocol.setMsg("请输入正确的答案");
		}
		model.addAttribute(protocol);
	}
}
