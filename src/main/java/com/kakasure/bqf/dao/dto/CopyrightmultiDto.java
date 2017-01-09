
package com.kakasure.bqf.dao.dto;

import com.kakasure.bqf.dao.model.Copyrightmulti;

public class CopyrightmultiDto extends Copyrightmulti {

	private String	labelStr;
	private String	audioPath;

	private String	announcemultiId;
	private String	imgLink;
	private String	categoryName;
	private String	username;
	private String	buyFlag;		// y 已购买 n 未购买
	private String	affiliateUserId;// 码商userid

	public String getLabelStr() {
		return labelStr;
	}

	public void setLabelStr(String labelStr) {
		this.labelStr = labelStr;
	}

	public String getAudioPath() {
		return audioPath;
	}

	public void setAudioPath(String audioPath) {
		this.audioPath = audioPath;
	}

	public String getAnnouncemultiId() {
		return announcemultiId;
	}

	public void setAnnouncemultiId(String announcemultiId) {
		this.announcemultiId = announcemultiId;
	}

	public String getImgLink() {
		return imgLink;
	}

	public void setImgLink(String imgLink) {
		this.imgLink = imgLink;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getBuyFlag() {
		return buyFlag;
	}

	public void setBuyFlag(String buyFlag) {
		this.buyFlag = buyFlag;
	}

	public String getAffiliateUserId() {
		return affiliateUserId;
	}

	public void setAffiliateUserId(String affiliateUserId) {
		this.affiliateUserId = affiliateUserId;
	}

}