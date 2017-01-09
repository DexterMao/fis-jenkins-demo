package com.kakasure.bqf.dao.dto;

import java.math.BigDecimal;
import java.util.Date;

public class MultiOrderDto {
	private BigDecimal	cashPublish;
	private String		mediaName;
	private Date		dateModify;
	public BigDecimal getCashPublish() {
		return cashPublish;
	}
	public void setCashPublish(BigDecimal cashPublish) {
		this.cashPublish = cashPublish;
	}
	public String getMediaName() {
		return mediaName;
	}
	public void setMediaName(String mediaName) {
		this.mediaName = mediaName;
	}
	public Date getDateModify() {
		return dateModify;
	}
	public void setDateModify(Date dateModify) {
		this.dateModify = dateModify;
	}

}
