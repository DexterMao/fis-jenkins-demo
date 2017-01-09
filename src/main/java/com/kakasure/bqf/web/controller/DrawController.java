
package com.kakasure.bqf.web.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kakasure.bqf.dao.dto.DrawDto;
import com.kakasure.bqf.dao.model.Balance;
import com.kakasure.bqf.dao.model.Userplus;
import com.kakasure.bqf.service.DrawService;
import com.kakasure.controller.BaseController;
import com.kakasure.exception.ValidateException;
import com.kakasure.lang.model.Protocol;
import com.kakasure.lang.util.Page;

/**
 * 类名称：DrawController 创建人：zhangy
 */
@Controller
@RequestMapping(value = "/draw")
public class DrawController extends BaseController {

	@Resource
	private DrawService drawService;

	/**
	 * 去提现说明界面
	 * 
	 * @return
	 */
	@RequestMapping("/drawexplain")
	public String goDrawExplain(Model model) {
		return "/draw/draw_explain";
	}

	/**
	 * 去提现列表页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/goDrawList")
	public String goDrawList(Model model) {
		Balance balance = drawService.balanceByUserId(getLoginUser().getId());
		model.addAttribute("balance", balance);
		Userplus userplus = drawService.userplusByUserId(getLoginUser().getId());
		model.addAttribute("userplus", userplus);
		return "/draw/draw_list";
	}

	/**
	 * 去申请提现界面
	 * 
	 * @return
	 * @throws ValidateException
	 */
	@RequestMapping("/draw/balance")
	public String goEdit(Model model) throws ValidateException {
		Balance balance = drawService.balanceByUserId(getLoginUser().getId());
		model.addAttribute("balance", balance);
		Userplus userplus = drawService.userplusByUserId(getLoginUser().getId());
		model.addAttribute("userplus", userplus);
		return "draw/draw";
	}

	/**
	 * 获取提现列表
	 * 
	 * @param model
	 * @param page
	 * @param drawDto
	 * @throws ValidateException
	 */
	@RequestMapping(value = "/getDrawPages")
	public void getDrawPages(Model model, Page<DrawDto> page, DrawDto drawDto) throws ValidateException {
		Protocol protocol = new Protocol();
		drawDto.setUserId(getLoginUser().getId());
		List<DrawDto> drawDtos = drawService.getDrawPages(page, drawDto);
		page.setResults(drawDtos);
		protocol.setData(page);
		model.addAttribute("protocol", protocol);
	}

	/**
	 * 保存提现
	 * 
	 * @param drawDto
	 * @param model
	 * @throws ValidateException
	 */
	@RequestMapping(value = "/save")
	public void save(DrawDto drawDto, Model model) throws ValidateException {
		Protocol protocol = new Protocol();
		drawDto.setUserId(getLoginUser().getId());
		drawService.saveDraw(drawDto);
		model.addAttribute("protocol", protocol);
	}

}