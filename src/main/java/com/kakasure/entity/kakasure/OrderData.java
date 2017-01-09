
package com.kakasure.entity.kakasure;

import java.math.BigDecimal;
import java.util.Date;

public class OrderData implements Cloneable {

	/**
	 * 订单ID
	 */
	private String		order;
	/**
	 * 订单号
	 */
	private String		order_number;
	/**
	 * 订单金额
	 */
	private BigDecimal	total;
	/**
	 * 订单佣金
	 */
	private BigDecimal	commision;
	/**
	 * 咔咔硕商品名称
	 */
	private String		product;
	/**
	 * 购买用户ID
	 */
	private String		buy_user_id;
	/**
	 * 购买用户用户名
	 */
	private String		username;
	/**
	 * 多媒体ID（版权方的）
	 */
	private String		video_id;
	/**
	 * 发布方的多媒体ID(ANNOUNCEMULTI_ID)
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
	/**
	 * 订单状态：0待处理（刚创建），4交易失败，100交易成功
	 */
	private Integer		status;
	/**
	 * 奇码网平台收入
	 */
	private BigDecimal	cash_yunear;
	/**
	 * 版权方收入
	 */
	private BigDecimal	cash_copyright;
	/**
	 * 发布方收入
	 */
	private BigDecimal	cash_publish;
	/**
	 * 接口签名，和业务无关
	 */
	private String		sign;
	/**
	 * 同步订单类型 1:多媒体 2:节目
	 */
	private Integer		type;

	/**
	 * 分佣信息创建时间
	 * 
	 * @return
	 */
	private Date		DATE_CREATE;

	public Date getDATE_CREATE() {
		return DATE_CREATE;
	}

	public void setDATE_CREATE(Date dATE_CREATE) {
		DATE_CREATE = dATE_CREATE;
	}

	public BigDecimal getCash_yunear() {
		return cash_yunear;
	}

	public void setCash_yunear(BigDecimal cash_yunear) {
		this.cash_yunear = cash_yunear;
	}

	public BigDecimal getCash_copyright() {
		return cash_copyright;
	}

	public void setCash_copyright(BigDecimal cash_copyright) {
		this.cash_copyright = cash_copyright;
	}

	public BigDecimal getCash_publish() {
		return cash_publish;
	}

	public void setCash_publish(BigDecimal cash_publish) {
		this.cash_publish = cash_publish;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getOrder_number() {
		return order_number;
	}

	public void setOrder_number(String order_number) {
		this.order_number = order_number;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public BigDecimal getCommision() {
		return commision;
	}

	public void setCommision(BigDecimal commision) {
		this.commision = commision;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getBuy_user_id() {
		return buy_user_id;
	}

	public void setBuy_user_id(String buy_user_id) {
		this.buy_user_id = buy_user_id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public String getVideo_id() {
		return video_id;
	}

	public void setVideo_id(String video_id) {
		this.video_id = video_id;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

}
