
package com.kakasure.entity.kakasure;

import java.math.BigDecimal;

/**
 * 咔咔硕商品
 * 
 * @author zhangb
 *
 */
public class Product {

	/**
	 * 商品ID
	 */
	private String		id;
	/**
	 * 商品名称
	 */
	private String		name;
	/**
	 * 商品原价
	 */
	private BigDecimal	price;
	/**
	 * 商品咔咔价
	 */
	private BigDecimal	sellprice;
	/**
	 * 商品图片
	 */
	private String		imageUrl;
	/**
	 * 码商ID
	 */
	private String		affiliate;
	/**
	 * 二维码ID
	 */
	private String		qrcodeId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getSellprice() {
		return sellprice;
	}

	public void setSellprice(BigDecimal sellprice) {
		this.sellprice = sellprice;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getAffiliate() {
		return affiliate;
	}

	public void setAffiliate(String affiliate) {
		this.affiliate = affiliate;
	}

	public String getQrcodeId() {
		return qrcodeId;
	}

	public void setQrcodeId(String qrcodeId) {
		this.qrcodeId = qrcodeId;
	}

}
