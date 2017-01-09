
package com.kakasure.entity.kakasure;

/**
 * 展视直播间
 * 
 * @author zhangb
 *
 */
public class KakasureLive {
	private String	organizerJoinUrl;		//// 组织者加入URL
	private String	panelistJoinUrl;		// 嘉宾加入 URL
	private String	attendeeJoinUrl;		// 普通参加者加入 URL(带 token)
	private String	id;						// 直播 ID
	private String	number;					// 直播编号
	private String	attendeeAShortJoinUrl;	// 普通参加者加入URL(不带token)
	private String	organizerToken;			// 组织者口令
	private String	panelistToken;			// 嘉宾口令
	private String	attendeeToken;			// 普通参加者口令
	private String	code;					// 返回结果代码
	private String	message;				// 结果说明
	private String	showId;

	public String getOrganizerJoinUrl() {
		return organizerJoinUrl;
	}

	public void setOrganizerJoinUrl(String organizerJoinUrl) {
		this.organizerJoinUrl = organizerJoinUrl;
	}

	public String getPanelistJoinUrl() {
		return panelistJoinUrl;
	}

	public void setPanelistJoinUrl(String panelistJoinUrl) {
		this.panelistJoinUrl = panelistJoinUrl;
	}

	public String getAttendeeJoinUrl() {
		return attendeeJoinUrl;
	}

	public void setAttendeeJoinUrl(String attendeeJoinUrl) {
		this.attendeeJoinUrl = attendeeJoinUrl;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getAttendeeAShortJoinUrl() {
		return attendeeAShortJoinUrl;
	}

	public void setAttendeeAShortJoinUrl(String attendeeAShortJoinUrl) {
		this.attendeeAShortJoinUrl = attendeeAShortJoinUrl;
	}

	public String getOrganizerToken() {
		return organizerToken;
	}

	public void setOrganizerToken(String organizerToken) {
		this.organizerToken = organizerToken;
	}

	public String getPanelistToken() {
		return panelistToken;
	}

	public void setPanelistToken(String panelistToken) {
		this.panelistToken = panelistToken;
	}

	public String getAttendeeToken() {
		return attendeeToken;
	}

	public void setAttendeeToken(String attendeeToken) {
		this.attendeeToken = attendeeToken;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getShowId() {
		return showId;
	}

	public void setShowId(String showId) {
		this.showId = showId;
	}

}
