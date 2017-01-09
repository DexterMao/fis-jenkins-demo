
package com.kakasure.entity.kakasure;

import java.math.BigDecimal;

public class Media {

	private boolean		hasBuy;
	private String		id;
	/**
	 * 多媒体ID
	 */
	private String		media_id;
	/**
	 * 版权方ID
	 */
	private String		copyright_id;
	/**
	 * 版权方名称
	 */
	private String		copyrightName;
	/**
	 * 发布方ID
	 */
	private String		publish_id;
	/**
	 * 发布方名称
	 */
	private String		publishName;

	private String		name;
	private String		desc;
	private BigDecimal	price;
	private String		payType;
	private String		imageUrl;
	private String		mediaUrl;
	private String		type;
	private double		PRODUCT_CONCESSION;

	public double getPRODUCT_CONCESSION() {
		return PRODUCT_CONCESSION;
	}

	public void setPRODUCT_CONCESSION(double pRODUCT_CONCESSION) {
		PRODUCT_CONCESSION = pRODUCT_CONCESSION;
	}

	public boolean isHasBuy() {
		return hasBuy;
	}

	public void setHasBuy(boolean hasBuy) {
		this.hasBuy = hasBuy;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMedia_id() {
		return media_id;
	}

	public void setMedia_id(String media_id) {
		this.media_id = media_id;
	}

	public String getCopyright_id() {
		return copyright_id;
	}

	public void setCopyright_id(String copyright_id) {
		this.copyright_id = copyright_id;
	}

	public String getPublish_id() {
		return publish_id;
	}

	public void setPublish_id(String publish_id) {
		this.publish_id = publish_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getMediaUrl() {
		return mediaUrl;
	}

	public void setMediaUrl(String mediaUrl) {
		this.mediaUrl = mediaUrl;
	}

	public String getCopyrightName() {
		return copyrightName;
	}

	public void setCopyrightName(String copyrightName) {
		this.copyrightName = copyrightName;
	}

	public String getPublishName() {
		return publishName;
	}

	public void setPublishName(String publishName) {
		this.publishName = publishName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
