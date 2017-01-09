
package com.kakasure.entity.kakasure;

import java.math.BigDecimal;

/**
 * 奇码网接口调用返回结果
 * 
 * @author zhangb
 *
 */
public class ApiAffiliateProductDto {

	private String		affiliateProductId;
	private String		affiliateUserId;

	private String		productId;
	private String		name;
	private BigDecimal	priceKks;
	private BigDecimal	price;
	private String		thumbnailUrl;
	private String		url;

	private String		toOpenUrl;

	public String getAffiliateProductId() {
		return affiliateProductId;
	}

	public void setAffiliateProductId(String affiliateProductId) {
		this.affiliateProductId = affiliateProductId;
	}

	public String getAffiliateUserId() {
		return affiliateUserId;
	}

	public void setAffiliateUserId(String affiliateUserId) {
		this.affiliateUserId = affiliateUserId;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPriceKks() {
		return priceKks;
	}

	public void setPriceKks(BigDecimal priceKks) {
		this.priceKks = priceKks;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getThumbnailUrl() {
		return thumbnailUrl;
	}

	public void setThumbnailUrl(String thumbnailUrl) {
		this.thumbnailUrl = thumbnailUrl;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getToOpenUrl() {
		return toOpenUrl;
	}

	public void setToOpenUrl(String toOpenUrl) {
		this.toOpenUrl = toOpenUrl;
	}
}
