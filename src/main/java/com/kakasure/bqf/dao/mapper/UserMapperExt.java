
package com.kakasure.bqf.dao.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.kakasure.bqf.dao.model.User;
import com.kakasure.entity.kakasure.BqfUserDto;
import com.kakasure.lang.util.Page;

@Repository
public interface UserMapperExt extends UserMapper {

	public void editBalance(User user);

	public List<BqfUserDto> getBqfusers(Page<BqfUserDto> page);
}