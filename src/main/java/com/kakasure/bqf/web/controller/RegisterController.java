
package com.kakasure.bqf.web.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kakasure.bqf.dao.dto.UserDto;
import com.kakasure.bqf.service.UserService;
import com.kakasure.controller.BaseController;
import com.kakasure.lang.PropertyManager;
import com.kakasure.lang.model.Protocol;

/**
 * 注册Controller
 * 
 * @author zhangbin
 *
 */
@Controller
public class RegisterController extends BaseController {
	@Resource
	private UserService		userService;
	@Resource
	private PropertyManager	propertyManager;

	/**
	 * 去注册页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "register", method = RequestMethod.GET)
	public String register(Model model) {
		return "register";
	}

	/**
	 * 注册
	 * 
	 * @param userDto
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "register/bqf", method = RequestMethod.POST)
	public void doRegister(Model model, UserDto userDto) {
		Protocol protocol = new Protocol();

		userService.register(userDto, null, true);
		protocol.setMsg("注册成功");
		protocol.setData("success");
		model.addAttribute(protocol);
	}

}
