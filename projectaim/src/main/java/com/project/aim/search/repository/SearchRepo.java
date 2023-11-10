package com.project.aim.search.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.aim.search.entity.Channel;

public interface SearchRepo extends JpaRepository<Channel, Integer>{
	
	/* 
	 * 검색결과 리스트 : 검색어(keywords)를 기반으로 해당되는 크리에이터들의 정보를 반환
	 * 반환 목록 : 채널명, 구독자, 활동빈도(최근 1년간 업로드한 영상 갯수, 키워드가 포함된 영상의 수를 반환해야함), 검색어(keyword)수치(최근 1년간 집계된 키워드의 수치), 광고효과점수
	 */
	@Query(value = "SELECT \r\n"
	           + "    c.channel AS channel, \r\n"
	           + "    c.subs AS subscriber_count, \r\n"
	           + "    c.channel_image, \r\n"  
	           + "    COUNT(DISTINCT CASE WHEN v.keywords LIKE %:keywords% THEN v.title ELSE NULL END) AS last_12_months_video_count, \r\n"
	           + "    SUM(LENGTH(v.keywords) - LENGTH(REPLACE(v.keywords, :keywords, ''))) / LENGTH(:keywords) AS keyword_occurrence_count, \r\n"
	           + "    SUM(DISTINCT CASE WHEN v.keywords LIKE %:keywords% THEN v.views ELSE 0 END) AS last_12_month_video_views, \r\n"
	           + "    ((SUM(DISTINCT CASE WHEN v.keywords LIKE %:keywords% THEN v.views ELSE 0 END) / 10000) * (c.subs / 10000) * SUM(LENGTH(v.keywords) - LENGTH(REPLACE(v.keywords, :keywords, '') * 1.5)) / LENGTH(:keywords)) / 10000 AS keyword_effect_score \r\n"
	           + "FROM \r\n"
	           + "    channel_info c \r\n"
	           + "JOIN \r\n"
	           + "    video_info v ON c.channel_idx = v.channel_idx \r\n"
	           + "WHERE \r\n"
	           + "    v.upload_date BETWEEN DATE_SUB(CURDATE(), INTERVAL 12 MONTH) AND CURDATE() \r\n"
	           + "GROUP BY \r\n"
	           + "    c.channel, \r\n"
	           + "    c.subs, \r\n"
	           + "    c.channel_image \r\n"  
	           + "HAVING \r\n"
	           + "    keyword_effect_score > 0 \r\n"
	           + "ORDER BY \r\n"
	           + "    keyword_effect_score DESC;", nativeQuery = true)
		List<Map<String, Object>> findChannelDataWithKeywords(@Param("keywords") String keywords);

	

	@Query(value = "SELECT \r\n"
	           + "    c.channel AS channel, \r\n"
	           + "    c.subs AS subscriber_count, \r\n"
	           + "    c.channel_image, \r\n"  
	           + "    COUNT(DISTINCT CASE WHEN v.keywords LIKE %:keywords% THEN v.title ELSE NULL END) AS last_12_months_video_count, \r\n"
	           + "    SUM(LENGTH(v.keywords) - LENGTH(REPLACE(v.keywords, :keywords, ''))) / LENGTH(:keywords) AS keyword_occurrence_count, \r\n"
	           + "    SUM(DISTINCT CASE WHEN v.keywords LIKE %:keywords% THEN v.views ELSE 0 END) AS last_12_month_video_views, \r\n"
	           + "    ((SUM(DISTINCT CASE WHEN v.keywords LIKE %:keywords% THEN v.views ELSE 0 END) / 10000) * (c.subs / 10000) * SUM(LENGTH(v.keywords) - LENGTH(REPLACE(v.keywords, :keywords, '') * 1.5)) / LENGTH(:keywords)) / 10000 AS keyword_effect_score \r\n"
	           + "FROM \r\n"
	           + "    channel_info c \r\n"
	           + "JOIN \r\n"
	           + "    video_info v ON c.channel_idx = v.channel_idx \r\n"
	           + "WHERE \r\n"
	           + "    v.upload_date BETWEEN DATE_SUB(CURDATE(), INTERVAL 12 MONTH) AND CURDATE() \r\n"
	           + "GROUP BY \r\n"
	           + "    c.channel, \r\n"
	           + "    c.subs, \r\n"
	           + "    c.channel_image \r\n"  
	           + "HAVING \r\n"
	           + "    ((SUM(DISTINCT CASE WHEN v.keywords LIKE %:keywords% THEN v.views ELSE 0 END) / 10000) * (c.subs / 10000) * SUM(LENGTH(v.keywords) - LENGTH(REPLACE(v.keywords, :keywords, '') * 1.5)) / LENGTH(:keywords)) / 10000 > 0 \r\n"
	           + "ORDER BY \r\n"
	           + "    keyword_effect_score DESC;", nativeQuery = true)
		Page<Map<String, Object>> findChannelDataWithKeywords2(Pageable pageable, @Param("keywords") String keywords);
	
}


















