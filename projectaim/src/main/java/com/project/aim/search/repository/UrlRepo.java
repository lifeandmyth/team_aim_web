package com.project.aim.search.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.aim.search.entity.Channel;

public interface UrlRepo extends JpaRepository<Channel, Integer>{
	
	   @Query(value = "SELECT\r\n" +
	           "    c.channel,\r\n" +
	           "    v.title,\r\n" +
	           "    v.upload_date,\r\n" +
	           "    v.vid_url,\r\n" +
	           "    v.timeline,\r\n" +
	           "    (LENGTH(v.keywords) - LENGTH(REPLACE(v.keywords, :keywords, ''))) / LENGTH(:keywords) AS keyword_occurrence\r\n" +
	           " FROM video_info v\r\n" +
	           " JOIN channel_info c ON v.channel_idx = c.channel_idx\r\n" +
	           "WHERE v.keywords LIKE %:keywords%\r\n" +
	           "  AND v.upload_date LIKE %:month%\r\n" +
	           "  AND c.channel = :channel", nativeQuery = true)
	   List<Map<String, Object>> findtitleandurl(@Param("channel") String channel, 
	                                   @Param("keywords") String keywords, 
	                                   @Param("month") String month);

}
