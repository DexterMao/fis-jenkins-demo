package com.kakasure.bqf.dao.mapper;

import com.kakasure.bqf.dao.model.MessageUser;
import com.kakasure.bqf.dao.model.MessageUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MessageUserMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yunear_message_user
     *
     * @mbggenerated Thu Sep 22 10:02:32 CST 2016
     */
    int countByExample(MessageUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yunear_message_user
     *
     * @mbggenerated Thu Sep 22 10:02:32 CST 2016
     */
    int deleteByExample(MessageUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yunear_message_user
     *
     * @mbggenerated Thu Sep 22 10:02:32 CST 2016
     */
    int deleteByPrimaryKey(String messageuserId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yunear_message_user
     *
     * @mbggenerated Thu Sep 22 10:02:32 CST 2016
     */
    int insertSelective(MessageUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yunear_message_user
     *
     * @mbggenerated Thu Sep 22 10:02:32 CST 2016
     */
    List<MessageUser> selectByExample(MessageUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yunear_message_user
     *
     * @mbggenerated Thu Sep 22 10:02:32 CST 2016
     */
    MessageUser selectByPrimaryKey(String messageuserId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yunear_message_user
     *
     * @mbggenerated Thu Sep 22 10:02:32 CST 2016
     */
    int updateByExampleSelective(@Param("record") MessageUser record, @Param("example") MessageUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yunear_message_user
     *
     * @mbggenerated Thu Sep 22 10:02:32 CST 2016
     */
    int updateByExample(@Param("record") MessageUser record, @Param("example") MessageUserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table yunear_message_user
     *
     * @mbggenerated Thu Sep 22 10:02:32 CST 2016
     */
    int updateByPrimaryKeySelective(MessageUser record);
}