package com.kakasure.bqf.dao.mapper;

import com.kakasure.bqf.dao.model.CopyrightmultiFolder;
import com.kakasure.bqf.dao.model.CopyrightmultiFolderExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CopyrightmultiFolderMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yunear_copyrightmulti_folder
     *
     * @mbggenerated Thu Sep 22 10:02:32 CST 2016
     */
    int countByExample(CopyrightmultiFolderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yunear_copyrightmulti_folder
     *
     * @mbggenerated Thu Sep 22 10:02:32 CST 2016
     */
    int deleteByExample(CopyrightmultiFolderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yunear_copyrightmulti_folder
     *
     * @mbggenerated Thu Sep 22 10:02:32 CST 2016
     */
    int insertSelective(CopyrightmultiFolder record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yunear_copyrightmulti_folder
     *
     * @mbggenerated Thu Sep 22 10:02:32 CST 2016
     */
    List<CopyrightmultiFolder> selectByExample(CopyrightmultiFolderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yunear_copyrightmulti_folder
     *
     * @mbggenerated Thu Sep 22 10:02:32 CST 2016
     */
    int updateByExampleSelective(@Param("record") CopyrightmultiFolder record, @Param("example") CopyrightmultiFolderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yunear_copyrightmulti_folder
     *
     * @mbggenerated Thu Sep 22 10:02:32 CST 2016
     */
    int updateByExample(@Param("record") CopyrightmultiFolder record, @Param("example") CopyrightmultiFolderExample example);
}