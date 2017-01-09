package com.kakasure.bqf.dao.mapper;

import com.kakasure.bqf.dao.model.SysConfig;
import com.kakasure.bqf.dao.model.SysConfigExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysConfigMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_config
     *
     * @mbggenerated Wed Nov 09 11:40:45 GMT+08:00 2016
     */
    int countByExample(SysConfigExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_config
     *
     * @mbggenerated Wed Nov 09 11:40:45 GMT+08:00 2016
     */
    int deleteByExample(SysConfigExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_config
     *
     * @mbggenerated Wed Nov 09 11:40:45 GMT+08:00 2016
     */
    int deleteByPrimaryKey(String kksKey);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_config
     *
     * @mbggenerated Wed Nov 09 11:40:45 GMT+08:00 2016
     */
    int insertSelective(SysConfig record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_config
     *
     * @mbggenerated Wed Nov 09 11:40:45 GMT+08:00 2016
     */
    List<SysConfig> selectByExample(SysConfigExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_config
     *
     * @mbggenerated Wed Nov 09 11:40:45 GMT+08:00 2016
     */
    SysConfig selectByPrimaryKey(String kksKey);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_config
     *
     * @mbggenerated Wed Nov 09 11:40:45 GMT+08:00 2016
     */
    int updateByExampleSelective(@Param("record") SysConfig record, @Param("example") SysConfigExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_config
     *
     * @mbggenerated Wed Nov 09 11:40:45 GMT+08:00 2016
     */
    int updateByExample(@Param("record") SysConfig record, @Param("example") SysConfigExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table sys_config
     *
     * @mbggenerated Wed Nov 09 11:40:45 GMT+08:00 2016
     */
    int updateByPrimaryKeySelective(SysConfig record);
}