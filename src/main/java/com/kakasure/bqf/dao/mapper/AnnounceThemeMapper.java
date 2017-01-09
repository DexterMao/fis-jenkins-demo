package com.kakasure.bqf.dao.mapper;

import com.kakasure.bqf.dao.model.AnnounceTheme;
import com.kakasure.bqf.dao.model.AnnounceThemeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AnnounceThemeMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yunear_announce_theme
     *
     * @mbggenerated Thu Sep 22 10:02:32 CST 2016
     */
    int countByExample(AnnounceThemeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yunear_announce_theme
     *
     * @mbggenerated Thu Sep 22 10:02:32 CST 2016
     */
    int deleteByExample(AnnounceThemeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yunear_announce_theme
     *
     * @mbggenerated Thu Sep 22 10:02:32 CST 2016
     */
    int insertSelective(AnnounceTheme record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yunear_announce_theme
     *
     * @mbggenerated Thu Sep 22 10:02:32 CST 2016
     */
    List<AnnounceTheme> selectByExample(AnnounceThemeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yunear_announce_theme
     *
     * @mbggenerated Thu Sep 22 10:02:32 CST 2016
     */
    int updateByExampleSelective(@Param("record") AnnounceTheme record, @Param("example") AnnounceThemeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yunear_announce_theme
     *
     * @mbggenerated Thu Sep 22 10:02:32 CST 2016
     */
    int updateByExample(@Param("record") AnnounceTheme record, @Param("example") AnnounceThemeExample example);
}