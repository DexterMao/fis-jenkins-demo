
package com.kakasure.entity.kakasure;

import com.kakasure.lang.util.Page;

/**
 * 咔咔硕推荐商品集合
 * 
 * @author zhangb
 *
 */
public class ResultProducts {

	private Integer							code;
	private String							msg;
	private Page<ApiAffiliateProductDto>	data;

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Page<ApiAffiliateProductDto> getData() {
		return data;
	}

	public void setData(Page<ApiAffiliateProductDto> data) {
		this.data = data;
	}

}
