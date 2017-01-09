
package com.kakasure.bqf.dao.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.kakasure.bqf.dao.dto.LiveBqfGiftDto;
import com.kakasure.lang.util.Page;

@Repository
public interface LiveBqfGiftMapperExt extends LiveBqfGiftMapper {
	public List<LiveBqfGiftDto> getLiveBqfGiftList(Page<LiveBqfGiftDto> page);
}