
package com.kakasure.bqf.dao.dto;

import com.kakasure.bqf.dao.model.Draw;

public class DrawDto extends Draw {
	private String	startTime;
	private String	endTime;
	private String	type;
	private String	message;

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}