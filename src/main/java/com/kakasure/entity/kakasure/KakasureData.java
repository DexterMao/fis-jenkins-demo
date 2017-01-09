
package com.kakasure.entity.kakasure;

import java.util.List;

import javax.servlet.jsp.tagext.PageData;

import com.kakasure.bqf.dao.model.Theme;

public class KakasureData {

	private Media							media;
	private OrderData							order;
	private List<ApiAffiliateProductDto>	products;
	private List<Theme>						themes;

	private String							host;
	private String							urlPay;
	private String							urlLogin;

	private String							userId;

	private String							currentUrl;

	public String getCurrentUrl() {
		return currentUrl;
	}

	public void setCurrentUrl(String currentUrl) {
		this.currentUrl = currentUrl;
	}

	public Media getMedia() {
		if (media == null) {
			media = new Media();
		}
		return media;
	}

	public void setMedia(PageData copyrightmulti, PageData announcemulti) {
		// media = getMedia();
		// media.setId(announcemulti.getString("ANNOUNCEMULTI_ID"));
		// media.setMedia_id(announcemulti.getString("COPYRIGHTMULTI_ID"));
		// media.setCopyright_id(announcemulti.getString("COPYRIGHT_USER_ID"));
		// media.setCopyrightName(copyrightmulti.getString("copyrightName"));
		// media.setPublish_id(announcemulti.getString("USER_ID"));
		//
		// media.setPRODUCT_CONCESSION(new
		// Double(copyrightmulti.getString("PRODUCT_CONCESSION")));
		// media.setName(copyrightmulti.getString("MEDIA_NAME"));
		// media.setDesc(copyrightmulti.getString("DESCR"));
		// media.setPayType(copyrightmulti.getString("PAY_TYPE"));
		// media.setPrice(new BigDecimal(copyrightmulti.getString("PRICE")));
		// media.setImageUrl(copyrightmulti.getString("PICTUREPATH"));
		// media.setType(copyrightmulti.getString("TYPE"));
		//
		// media.setMediaUrl(NginxUtil.addSecurelinkForBuyer(copyrightmulti.getString("PATH")));
	}

	public void setBuyStatus(boolean hasBuy) {
		media = getMedia();
		media.setHasBuy(hasBuy);

		if (!hasBuy) {
			media.setMediaUrl("");
		}
	}

	public OrderData getOrder() {
		return order;
	}

	public void setOrder(OrderData order) {
		this.order = order;
	}

	public List<ApiAffiliateProductDto> getProducts() {
		return products;
	}

	public void setProducts(List<ApiAffiliateProductDto> products) {
		this.products = products;
	}

	public List<Theme> getThemes() {
		return themes;
	}

	public void setThemes(List<Theme> themes) {
		this.themes = themes;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getUrlPay() {
		return urlPay;
	}

	public void setUrlPay(String urlPay) {
		this.urlPay = urlPay;
	}

	public String getUrlLogin() {
		return urlLogin;
	}

	public void setUrlLogin(String urlLogin) {
		this.urlLogin = urlLogin;
	}

	public void setMedia(Media media) {
		// media.setMediaUrl(NginxUtil.addSecurelinkForBuyer(media.getMediaUrl()));
		this.media = media;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}
