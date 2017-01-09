package com.kakasure.bqf.dao.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.kakasure.bqf.dao.model.VideoCategory;

@Repository
public interface VideoCategoryMapperExt extends VideoCategoryMapper {

	/**
	 * 获取类目列表
	 * 
	 * @param id
	 * @return
	 */
	List<VideoCategory> getVedioCategoryList(String id);
}