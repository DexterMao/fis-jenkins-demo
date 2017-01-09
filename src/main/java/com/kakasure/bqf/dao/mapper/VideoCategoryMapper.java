package com.kakasure.bqf.dao.mapper;

import com.kakasure.bqf.dao.model.VideoCategory;
import com.kakasure.bqf.dao.model.VideoCategoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface VideoCategoryMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yunear_video_category
     *
     * @mbggenerated Thu Sep 22 10:02:32 CST 2016
     */
    int countByExample(VideoCategoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yunear_video_category
     *
     * @mbggenerated Thu Sep 22 10:02:32 CST 2016
     */
    int deleteByExample(VideoCategoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yunear_video_category
     *
     * @mbggenerated Thu Sep 22 10:02:32 CST 2016
     */
    int deleteByPrimaryKey(String videoCategoryId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yunear_video_category
     *
     * @mbggenerated Thu Sep 22 10:02:32 CST 2016
     */
    int insertSelective(VideoCategory record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yunear_video_category
     *
     * @mbggenerated Thu Sep 22 10:02:32 CST 2016
     */
    List<VideoCategory> selectByExample(VideoCategoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yunear_video_category
     *
     * @mbggenerated Thu Sep 22 10:02:32 CST 2016
     */
    VideoCategory selectByPrimaryKey(String videoCategoryId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yunear_video_category
     *
     * @mbggenerated Thu Sep 22 10:02:32 CST 2016
     */
    int updateByExampleSelective(@Param("record") VideoCategory record, @Param("example") VideoCategoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yunear_video_category
     *
     * @mbggenerated Thu Sep 22 10:02:32 CST 2016
     */
    int updateByExample(@Param("record") VideoCategory record, @Param("example") VideoCategoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yunear_video_category
     *
     * @mbggenerated Thu Sep 22 10:02:32 CST 2016
     */
    int updateByPrimaryKeySelective(VideoCategory record);
}