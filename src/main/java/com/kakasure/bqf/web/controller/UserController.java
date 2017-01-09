
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
 * 用户Controller
 * 
 * @author zhangy
 *
 */
@Controller
public class UserController extends BaseController {
	@Resource
	private UserService		userService;
	@Resource
	private PropertyManager	propertyManager;

	/**
	 * 去修改密码页面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "changePassword", method = RequestMethod.GET)
	public String goPassword(Model model) {
		return "changePassword";
	}

	/**
	 * 修改密码
	 * 
	 * @param model
	 * @param userDto
	 */
	@RequestMapping(value = "changePassword", method = RequestMethod.POST)
	public void changePassword(Model model, UserDto userDto) {
		Protocol protocol = new Protocol();
		userService.changePassword(userDto, getLoginUser().getId());
		protocol.setData("success");
		model.addAttribute("protocol", protocol);
	}
}
