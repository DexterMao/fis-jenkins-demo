
package com.kakasure.bqf.dao.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.kakasure.bqf.dao.dto.LiveBroadcastDto;
import com.kakasure.lang.util.Page;

@Repository
public interface LiveBroadcastMapperExt extends LiveBroadcastMapper {
	public List<LiveBroadcastDto> getLiveBroList(Page<LiveBroadcastDto> page);

	public List<LiveBroadcastDto> getLiveBroListFromKks(Page<LiveBroadcastDto> page);
}