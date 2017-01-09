package com.kakasure.bqf.dao.dto;

public class LiveQimanetShowDto {
	private String	webcastID;
	private String	watchPassword;
	private String	niceName	= "主播";
	private String	domain;
	private String	channelType	= "01";
	private String	identifier	= "kakasure";
	private String	organizerUrl;
	private String	accountType;
	private String	userSig;
	private String	sdkAppId;
	private String	liveBroadcastId;

	public String getWebcastID() {
		return webcastID;
	}
	public void setWebcastID(String webcastID) {
		this.webcastID = webcastID;
	}
	public String getWatchPassword() {
		return watchPassword;
	}
	public void setWatchPassword(String watchPassword) {
		this.watchPassword = watchPassword;
	}
	public String getNiceName() {
		return niceName;
	}
	public void setNiceName(String niceName) {
		this.niceName = niceName;
	}
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public String getChannelType() {
		return channelType;
	}
	public void setChannelType(String channelType) {
		this.channelType = channelType;
	}
	public String getIdentifier() {
		return identifier;
	}
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	public String getOrganizerUrl() {
		return organizerUrl;
	}
	public void setOrganizerUrl(String organizerUrl) {
		this.organizerUrl = organizerUrl;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public String getUserSig() {
		return userSig;
	}
	public void setUserSig(String userSig) {
		this.userSig = userSig;
	}
	public String getSdkAppId() {
		return sdkAppId;
	}
	public void setSdkAppId(String sdkAppId) {
		this.sdkAppId = sdkAppId;
	}
	public String getLiveBroadcastId() {
		return liveBroadcastId;
	}
	public void setLiveBroadcastId(String liveBroadcastId) {
		this.liveBroadcastId = liveBroadcastId;
	}

}
