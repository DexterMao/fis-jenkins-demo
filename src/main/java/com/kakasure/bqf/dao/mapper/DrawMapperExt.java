
package com.kakasure.bqf.dao.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.kakasure.bqf.dao.dto.DrawDto;
import com.kakasure.lang.util.Page;

@Repository
public interface DrawMapperExt extends DrawMapper {

	public List<DrawDto> getDrawPages(Page<DrawDto> page);
}