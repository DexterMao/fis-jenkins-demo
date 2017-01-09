
package com.kakasure.bqf.web.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kakasure.bqf.dao.dto.UserplusDto;
import com.kakasure.bqf.service.BqfService;
import com.kakasure.controller.BaseController;
import com.kakasure.lang.model.Protocol;

/**
 * 类名称：BqfController 创建人：zhangy
 */
@Controller
@RequestMapping(value = "/bqfCon")
public class BqfController extends BaseController {

	@Resource
	private BqfService bqfService;

	/**
	 * 去码商信息页面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/goBqfDetail")
	public String goBqfDetail(Model model) {
		UserplusDto userplusDto = bqfService.saveUserplusByUserId(getLoginUser().getId());
		model.addAttribute("userplusDto", userplusDto);
		return "/bqfinfo/bqf_info";
	}

	/**
	 * 去绑定手机号界面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/goBindMobile")
	public String goBindMobile(Model model) {
		model.addAttribute("phone", bqfService.getPhoneByUserId(getLoginUser().getId()));
		return "/bqfinfo/bind_mobile";
	}

	/**
	 * 校验验证码
	 * 
	 * @param userVendor
	 */
	@RequestMapping(value = "/hasPhone")
	public void hasPhone(String phone, String code, Model model) {
		Protocol protocol = new Protocol();
		bqfService.hasPhone(phone, code);
		model.addAttribute("protocol", protocol);
	}

	/**
	 * 修改用户手机号
	 * 
	 * @param userVendor
	 * @throws Exception
	 */
	@RequestMapping(value = "/editPhone")
	public void editPhone(UserplusDto userplusDto, Model model) throws Exception {
		userplusDto.setUserId(getLoginUser().getId());
		bqfService.editPhone(userplusDto);
		Protocol protocol = new Protocol();
		model.addAttribute("protocol", protocol);
	}

	/**
	 * 修改用户信息
	 * 
	 * @param userVendor
	 * @throws Exception
	 */
	@RequestMapping(value = "/editUserplus")
	public void editUserplus(UserplusDto userplusDto, Model model) throws Exception {
		userplusDto.setUserId(getLoginUser().getId());
		bqfService.editUserplus(userplusDto);
		Protocol protocol = new Protocol();
		model.addAttribute("protocol", protocol);
	}

}