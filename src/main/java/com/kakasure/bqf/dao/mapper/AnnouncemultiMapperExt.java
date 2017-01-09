
package com.kakasure.bqf.dao.mapper;

import org.springframework.stereotype.Repository;

@Repository
public interface AnnouncemultiMapperExt extends AnnouncemultiMapper {
	public void updateCode(String ANNOUNCEMULTI_ID);
}