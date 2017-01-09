
package com.kakasure.bqf.dao.dto;

import java.util.Date;

public class ApiCopyrightmultiDto {

	private String	copyrightmultiId;
	private String	userId;
	private String	mediaName;
	private String	payType;
	private Double	price;
	private String	descr;
	private String	picturepath;
	private String	path;
	private String	type;
	private String	auditStatus;		// 审核状态：99-待审核，00-审核通过，01-审核不通过
	private Integer	spreadNum;			// 推广次数
	private Integer	clickNum;			// 点击数
	private Integer	scanCodeNum;		// 扫码数
	private Date	dateCreate;
	private Date	dateModify;
	private Double	concession;
	private String	videoCategoryId;
	private Double	productConcession;

	public String getCopyrightmultiId() {
		return copyrightmultiId;
	}

	public void setCopyrightmultiId(String copyrightmultiId) {
		this.copyrightmultiId = copyrightmultiId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getMediaName() {
		return mediaName;
	}

	public void setMediaName(String mediaName) {
		this.mediaName = mediaName;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public String getPicturepath() {
		return picturepath;
	}

	public void setPicturepath(String picturepath) {
		this.picturepath = picturepath;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}

	public Integer getSpreadNum() {
		return spreadNum;
	}

	public void setSpreadNum(Integer spreadNum) {
		this.spreadNum = spreadNum;
	}

	public Integer getClickNum() {
		return clickNum;
	}

	public void setClickNum(Integer clickNum) {
		this.clickNum = clickNum;
	}

	public Integer getScanCodeNum() {
		return scanCodeNum;
	}

	public void setScanCodeNum(Integer scanCodeNum) {
		this.scanCodeNum = scanCodeNum;
	}

	public Date getDateCreate() {
		return dateCreate;
	}

	public void setDateCreate(Date dateCreate) {
		this.dateCreate = dateCreate;
	}

	public Date getDateModify() {
		return dateModify;
	}

	public void setDateModify(Date dateModify) {
		this.dateModify = dateModify;
	}

	public Double getConcession() {
		return concession;
	}

	public void setConcession(Double concession) {
		this.concession = concession;
	}

	public String getVideoCategoryId() {
		return videoCategoryId;
	}

	public void setVideoCategoryId(String videoCategoryId) {
		this.videoCategoryId = videoCategoryId;
	}

	public Double getProductConcession() {
		return productConcession;
	}

	public void setProductConcession(Double productConcession) {
		this.productConcession = productConcession;
	}

}