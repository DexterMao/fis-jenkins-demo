
package com.kakasure.bqf.web.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kakasure.bqf.dao.dto.CopyrightmultiDto;
import com.kakasure.bqf.dao.model.VideoCategory;
import com.kakasure.bqf.service.CopyrightMultiService;
import com.kakasure.controller.BaseController;
import com.kakasure.lang.PropertyManager;
import com.kakasure.lang.model.Protocol;
import com.kakasure.lang.util.Page;

/**
 * 类名称：CopyrightMultiController 创建人：zhangy
 */
@Controller
@RequestMapping(value = "/copyrightmulti")
public class CopyrightMultiController extends BaseController {

	@Resource
	private CopyrightMultiService	copyrightMultiService;
	@Resource
	private PropertyManager			propertyManager;

	/**
	 * 去多媒体列表页面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/goCopyrightList")
	public String goCopyrightMultiList(Model model) {
		return "/copyrightmulti/copyrightmulti_list";
	}

	/**
	 * 去多媒体详情页面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/goCopyrightiLock")
	public String goCopyrightiLock(Model model, String copyrightmultiId) {
		CopyrightmultiDto copyrightmulti = copyrightMultiService.getCopyrightmultiById(copyrightmultiId);
		model.addAttribute("cm", copyrightmulti);
		return "/copyrightmulti/copyrightmulti_lock";
	}

	/**
	 * 去上传多媒体页面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/goUploadMulti")
	public String goUploadMulti(Model model) {
		List<VideoCategory> categories = copyrightMultiService.getVideoCategory();
		model.addAttribute("categories", categories);
		model.addAttribute("host_image", propertyManager.imageHost);
		return "/copyrightmulti/copyrightmulti_insert";
	}

	/**
	 * 去修改多媒体页面
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/goUpdateMulti")
	public String goUpdateMulti(Model model, String copyrightmultiId) throws Exception {
		List<VideoCategory> categories = copyrightMultiService.getVideoCategory();
		CopyrightmultiDto copyrightmulti = copyrightMultiService.getCopyrightmultiById(copyrightmultiId);
		// 判断是否在有效期内
		if (copyrightMultiService.validityInterface(copyrightmulti.getCopyrightmultiId(), copyrightmulti.getUserId(), copyrightmulti.getPayType())) {
			model.addAttribute("canEdit", false);
		} else {
			model.addAttribute("canEdit", true);
		}
		model.addAttribute("categories", categories);
		model.addAttribute("cm", copyrightmulti);
		model.addAttribute("host_image", propertyManager.imageHost);
		return "/copyrightmulti/copyrightmulti_update";
	}

	/**
	 * 去切图片页面
	 */
	@RequestMapping(value = "/cropperImg")
	public String cropperImg(Model model, String imgUrl) {
		model.addAttribute("imgUrl", imgUrl);
		return "/copyrightmulti/cropper_img";
	}

	/**
	 * 获取版权方多媒体列表
	 * 
	 * @param page
	 * @param copyrightmultiDto
	 */
	@RequestMapping(value = "/getCopyrightmultiList")
	public void getCopyrightmultiList(Model model, Page<CopyrightmultiDto> page, CopyrightmultiDto copyrightmultiDto) {
		Protocol protocol = new Protocol();
		copyrightmultiDto.setUserId(getLoginUser().getId());
		List<CopyrightmultiDto> copyrightmultiDtos = copyrightMultiService.getCopyrightmultiList(page, copyrightmultiDto);
		page.setResults(copyrightmultiDtos);
		protocol.setData(page);
		model.addAttribute("protocol", protocol);
	}

	/**
	 * 保存多媒体
	 * 
	 * @param model
	 * @param copyrightmultiDto
	 * @throws Exception
	 */
	@RequestMapping(value = "/saveCM")
	public void deleteCopyrightmulti(Model model, CopyrightmultiDto copyrightmultiDto) throws Exception {
		Protocol protocol = new Protocol();
		copyrightmultiDto.setUserId(getLoginUser().getId());
		copyrightMultiService.save(copyrightmultiDto);
		model.addAttribute("protocol", protocol);
	}

	/**
	 * 修改多媒体
	 * 
	 * @param model
	 * @param copyrightmultiDto
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateCM")
	public void updateCopyrightmulti(Model model, CopyrightmultiDto copyrightmultiDto) throws Exception {
		Protocol protocol = new Protocol();
		copyrightmultiDto.setUserId(getLoginUser().getId());
		copyrightMultiService.updateMulti(copyrightmultiDto);
		model.addAttribute("protocol", protocol);
	}

	/**
	 * 删除多媒体
	 * 
	 * @param model
	 * @param id
	 * @throws Exception
	 */
	@RequestMapping(value = "/deleteCopyrightmulti")
	public void deleteCopyrightmulti(Model model, String id) throws Exception {
		Protocol protocol = new Protocol();
		copyrightMultiService.deleteById(getLoginUser().getId(), id);
		model.addAttribute("protocol", protocol);
	}
}
