
package com.kakasure.entity.kakasure;

import java.math.BigDecimal;

/**
 * 展视直播间
 * 
 * @author zhangb
 *
 */
public class KakasureLiveBqfGift {
	private Long		liveAffiliateGiftId;	// 码商礼物记录主键Id
	private String		giftName;				// 礼物名称
	private Integer		giftNum;				// 礼物数量
	private String		copyrightWebcastId;		// 版权方节目id
	private String		copyrightUserId;		// 版权方id
	private BigDecimal	cashCopyright;
	private String		usernameCopyright;
	private Integer		pointCopyright;			// 积分

	private String		sign;					// 签名

	public Long getLiveAffiliateGiftId() {
		return liveAffiliateGiftId;
	}

	public void setLiveAffiliateGiftId(Long liveAffiliateGiftId) {
		this.liveAffiliateGiftId = liveAffiliateGiftId;
	}

	public String getGiftName() {
		return giftName;
	}

	public void setGiftName(String giftName) {
		this.giftName = giftName;
	}

	public Integer getGiftNum() {
		return giftNum;
	}

	public void setGiftNum(Integer giftNum) {
		this.giftNum = giftNum;
	}

	public String getCopyrightWebcastId() {
		return copyrightWebcastId;
	}

	public void setCopyrightWebcastId(String copyrightWebcastId) {
		this.copyrightWebcastId = copyrightWebcastId;
	}

	public String getCopyrightUserId() {
		return copyrightUserId;
	}

	public void setCopyrightUserId(String copyrightUserId) {
		this.copyrightUserId = copyrightUserId;
	}

	public BigDecimal getCashCopyright() {
		return cashCopyright;
	}

	public void setCashCopyright(BigDecimal cashCopyright) {
		this.cashCopyright = cashCopyright;
	}

	public String getUsernameCopyright() {
		return usernameCopyright;
	}

	public void setUsernameCopyright(String usernameCopyright) {
		this.usernameCopyright = usernameCopyright;
	}

	public Integer getPointCopyright() {
		return pointCopyright;
	}

	public void setPointCopyright(Integer pointCopyright) {
		this.pointCopyright = pointCopyright;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

}
