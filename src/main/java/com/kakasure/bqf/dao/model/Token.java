package com.kakasure.bqf.dao.model;

import java.util.Date;

public class Token {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sso_token.access_token
     *
     * @mbggenerated Thu Sep 22 10:02:32 CST 2016
     */
    private String accessToken;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sso_token.token_type
     *
     * @mbggenerated Thu Sep 22 10:02:32 CST 2016
     */
    private String tokenType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sso_token.refresh_token
     *
     * @mbggenerated Thu Sep 22 10:02:32 CST 2016
     */
    private String refreshToken;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sso_token.expires_in
     *
     * @mbggenerated Thu Sep 22 10:02:32 CST 2016
     */
    private Integer expiresIn;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sso_token.user_id
     *
     * @mbggenerated Thu Sep 22 10:02:32 CST 2016
     */
    private String userId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sso_token.gmt_created
     *
     * @mbggenerated Thu Sep 22 10:02:32 CST 2016
     */
    private Date gmtCreated;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sso_token.gmt_modified
     *
     * @mbggenerated Thu Sep 22 10:02:32 CST 2016
     */
    private Date gmtModified;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column sso_token.is_deleted
     *
     * @mbggenerated Thu Sep 22 10:02:32 CST 2016
     */
    private String isDeleted;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sso_token.access_token
     *
     * @return the value of sso_token.access_token
     *
     * @mbggenerated Thu Sep 22 10:02:32 CST 2016
     */
    public String getAccessToken() {
        return accessToken;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sso_token.access_token
     *
     * @param accessToken the value for sso_token.access_token
     *
     * @mbggenerated Thu Sep 22 10:02:32 CST 2016
     */
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sso_token.token_type
     *
     * @return the value of sso_token.token_type
     *
     * @mbggenerated Thu Sep 22 10:02:32 CST 2016
     */
    public String getTokenType() {
        return tokenType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sso_token.token_type
     *
     * @param tokenType the value for sso_token.token_type
     *
     * @mbggenerated Thu Sep 22 10:02:32 CST 2016
     */
    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sso_token.refresh_token
     *
     * @return the value of sso_token.refresh_token
     *
     * @mbggenerated Thu Sep 22 10:02:32 CST 2016
     */
    public String getRefreshToken() {
        return refreshToken;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sso_token.refresh_token
     *
     * @param refreshToken the value for sso_token.refresh_token
     *
     * @mbggenerated Thu Sep 22 10:02:32 CST 2016
     */
    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sso_token.expires_in
     *
     * @return the value of sso_token.expires_in
     *
     * @mbggenerated Thu Sep 22 10:02:32 CST 2016
     */
    public Integer getExpiresIn() {
        return expiresIn;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sso_token.expires_in
     *
     * @param expiresIn the value for sso_token.expires_in
     *
     * @mbggenerated Thu Sep 22 10:02:32 CST 2016
     */
    public void setExpiresIn(Integer expiresIn) {
        this.expiresIn = expiresIn;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sso_token.user_id
     *
     * @return the value of sso_token.user_id
     *
     * @mbggenerated Thu Sep 22 10:02:32 CST 2016
     */
    public String getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sso_token.user_id
     *
     * @param userId the value for sso_token.user_id
     *
     * @mbggenerated Thu Sep 22 10:02:32 CST 2016
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sso_token.gmt_created
     *
     * @return the value of sso_token.gmt_created
     *
     * @mbggenerated Thu Sep 22 10:02:32 CST 2016
     */
    public Date getGmtCreated() {
        return gmtCreated;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sso_token.gmt_created
     *
     * @param gmtCreated the value for sso_token.gmt_created
     *
     * @mbggenerated Thu Sep 22 10:02:32 CST 2016
     */
    public void setGmtCreated(Date gmtCreated) {
        this.gmtCreated = gmtCreated;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sso_token.gmt_modified
     *
     * @return the value of sso_token.gmt_modified
     *
     * @mbggenerated Thu Sep 22 10:02:32 CST 2016
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sso_token.gmt_modified
     *
     * @param gmtModified the value for sso_token.gmt_modified
     *
     * @mbggenerated Thu Sep 22 10:02:32 CST 2016
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column sso_token.is_deleted
     *
     * @return the value of sso_token.is_deleted
     *
     * @mbggenerated Thu Sep 22 10:02:32 CST 2016
     */
    public String getIsDeleted() {
        return isDeleted;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column sso_token.is_deleted
     *
     * @param isDeleted the value for sso_token.is_deleted
     *
     * @mbggenerated Thu Sep 22 10:02:32 CST 2016
     */
    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted;
    }
}