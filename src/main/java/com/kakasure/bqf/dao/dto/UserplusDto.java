
package com.kakasure.bqf.dao.dto;

import com.kakasure.bqf.dao.model.Userplus;

public class UserplusDto extends Userplus {

	private String	code;
	private String	saveType;	// 保存账号信息是支付宝还是银联

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getSaveType() {
		return saveType;
	}

	public void setSaveType(String saveType) {
		this.saveType = saveType;
	}

}