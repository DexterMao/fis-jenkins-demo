
package com.kakasure.bqf.dao.dto;

import com.kakasure.bqf.dao.model.Order;

public class OrderDto extends Order {
	private String	multiName;
	private String	statusStr;

	public String getMultiName() {
		return multiName;
	}

	public void setMultiName(String multiName) {
		this.multiName = multiName;
	}

	public String getStatusStr() {
		return statusStr;
	}

	public void setStatusStr(String statusStr) {
		this.statusStr = statusStr;
	}

}