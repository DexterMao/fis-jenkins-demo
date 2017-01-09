
package com.kakasure.bqf.dao.dto;

import com.kakasure.bqf.dao.model.LiveBroadcast;

public class LiveBroadcastDto extends LiveBroadcast {

	private String	date;
	private String	status;
	private String	dateStartTimeStr;
	private String	dateEndTimeStr;
	private String	dateStartIntervalStr;
	private String	dateEndIntervalStr;
	private String	userName;
	private String	guestQrcodeUrl;
	private String	AttendeeQrcodeUrl;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDateStartTimeStr() {
		return dateStartTimeStr;
	}

	public void setDateStartTimeStr(String dateStartTimeStr) {
		this.dateStartTimeStr = dateStartTimeStr;
	}

	public String getDateEndTimeStr() {
		return dateEndTimeStr;
	}

	public void setDateEndTimeStr(String dateEndTimeStr) {
		this.dateEndTimeStr = dateEndTimeStr;
	}

	public String getDateStartIntervalStr() {
		return dateStartIntervalStr;
	}

	public void setDateStartIntervalStr(String dateStartIntervalStr) {
		this.dateStartIntervalStr = dateStartIntervalStr;
	}

	public String getDateEndIntervalStr() {
		return dateEndIntervalStr;
	}

	public void setDateEndIntervalStr(String dateEndIntervalStr) {
		this.dateEndIntervalStr = dateEndIntervalStr;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getGuestQrcodeUrl() {
		return guestQrcodeUrl;
	}

	public void setGuestQrcodeUrl(String guestQrcodeUrl) {
		this.guestQrcodeUrl = guestQrcodeUrl;
	}

	public String getAttendeeQrcodeUrl() {
		return AttendeeQrcodeUrl;
	}

	public void setAttendeeQrcodeUrl(String attendeeQrcodeUrl) {
		AttendeeQrcodeUrl = attendeeQrcodeUrl;
	}

}