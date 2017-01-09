package com.kakasure.bqf.dao.model;

import java.util.Date;

public class VideoCategory {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yunear_video_category.VIDEO_CATEGORY_ID
     *
     * @mbggenerated Thu Sep 22 10:02:32 CST 2016
     */
    private String videoCategoryId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yunear_video_category.CATEGORY_NAME
     *
     * @mbggenerated Thu Sep 22 10:02:32 CST 2016
     */
    private String categoryName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yunear_video_category.DATE_CREATE
     *
     * @mbggenerated Thu Sep 22 10:02:32 CST 2016
     */
    private Date dateCreate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column yunear_video_category.DATE_MODIFY
     *
     * @mbggenerated Thu Sep 22 10:02:32 CST 2016
     */
    private Date dateModify;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yunear_video_category.VIDEO_CATEGORY_ID
     *
     * @return the value of yunear_video_category.VIDEO_CATEGORY_ID
     *
     * @mbggenerated Thu Sep 22 10:02:32 CST 2016
     */
    public String getVideoCategoryId() {
        return videoCategoryId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yunear_video_category.VIDEO_CATEGORY_ID
     *
     * @param videoCategoryId the value for yunear_video_category.VIDEO_CATEGORY_ID
     *
     * @mbggenerated Thu Sep 22 10:02:32 CST 2016
     */
    public void setVideoCategoryId(String videoCategoryId) {
        this.videoCategoryId = videoCategoryId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yunear_video_category.CATEGORY_NAME
     *
     * @return the value of yunear_video_category.CATEGORY_NAME
     *
     * @mbggenerated Thu Sep 22 10:02:32 CST 2016
     */
    public String getCategoryName() {
        return categoryName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yunear_video_category.CATEGORY_NAME
     *
     * @param categoryName the value for yunear_video_category.CATEGORY_NAME
     *
     * @mbggenerated Thu Sep 22 10:02:32 CST 2016
     */
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yunear_video_category.DATE_CREATE
     *
     * @return the value of yunear_video_category.DATE_CREATE
     *
     * @mbggenerated Thu Sep 22 10:02:32 CST 2016
     */
    public Date getDateCreate() {
        return dateCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yunear_video_category.DATE_CREATE
     *
     * @param dateCreate the value for yunear_video_category.DATE_CREATE
     *
     * @mbggenerated Thu Sep 22 10:02:32 CST 2016
     */
    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column yunear_video_category.DATE_MODIFY
     *
     * @return the value of yunear_video_category.DATE_MODIFY
     *
     * @mbggenerated Thu Sep 22 10:02:32 CST 2016
     */
    public Date getDateModify() {
        return dateModify;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column yunear_video_category.DATE_MODIFY
     *
     * @param dateModify the value for yunear_video_category.DATE_MODIFY
     *
     * @mbggenerated Thu Sep 22 10:02:32 CST 2016
     */
    public void setDateModify(Date dateModify) {
        this.dateModify = dateModify;
    }
}