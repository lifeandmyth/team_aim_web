package com.project.aim.main.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.aim.search.entity.Channel;

public interface ChannelRepo extends JpaRepository<Channel, Integer>{

	/* 메인페이지에서 채널명 구독자 대표키워드 대표키워드 집계를 표현 */
	
	@Query(value = 
		    "SELECT \r\n"
		    + "    c.channel, \r\n"
		    + "    c.subs as subscriber_count, \r\n"
		    + "    c.channel_image, \r\n"
		    + "    GROUP_CONCAT(keyword) as keyword \r\n"
		    + "FROM ( \r\n"
		    + "    SELECT \r\n"
		    + "        v.channel_idx, \r\n"
		    + "        v.keyword, \r\n"
		    + "        COUNT(*) as keyword_count,\r\n"
		    + "        ROW_NUMBER() OVER(PARTITION BY v.channel_idx ORDER BY COUNT(*) DESC) as row_num \r\n"
		    + "    FROM ( \r\n"
		    + "        SELECT channel_idx, SUBSTRING_INDEX(SUBSTRING_INDEX(vi.keywords, ',', numbers.n), ',', -1) as keyword \r\n"
		    + "        FROM video_info vi, (SELECT 1 n UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9 UNION ALL SELECT 10) numbers \r\n"
		    + "        WHERE CHAR_LENGTH(vi.keywords) - CHAR_LENGTH(REPLACE(vi.keywords, ',', '')) >= numbers.n-1 \r\n"
		    + "        AND vi.upload_date BETWEEN DATE_SUB(NOW(), INTERVAL 1 YEAR) AND NOW() \r\n"
		    + "    ) v \r\n"
		    + "    GROUP BY v.channel_idx, v.keyword \r\n"
		    + "    ORDER BY keyword_count DESC \r\n"
		    + ") as RankedKeywords \r\n"
		    + "JOIN channel_info c ON RankedKeywords.channel_idx = c.channel_idx \r\n"
		    + "WHERE row_num <= 5 \r\n"
		    + "GROUP BY c.channel, c.subs, c.channel_image \r\n"
		    + "ORDER BY rand();", 
		    nativeQuery = true)
		List<Map<String, Object>> findAllChannelsAndVideos();

    
	@Query(value = 
		    "SELECT \r\n"
		    + "    c.channel, \r\n"
		    + "    c.subs as subscriber_count, \r\n"
		    + "    c.channel_image, \r\n"
		    + "    GROUP_CONCAT(keyword) as keyword \r\n"
		    + "FROM ( \r\n"
		    + "    SELECT \r\n"
		    + "        v.channel_idx, \r\n"
		    + "        v.keyword, \r\n"
		    + "        COUNT(*) as keyword_count,\r\n"
		    + "        ROW_NUMBER() OVER(PARTITION BY v.channel_idx ORDER BY COUNT(*) DESC) as row_num \r\n"
		    + "    FROM ( \r\n"
		    + "        SELECT channel_idx, SUBSTRING_INDEX(SUBSTRING_INDEX(vi.keywords, ',', numbers.n), ',', -1) as keyword \r\n"
		    + "        FROM video_info vi, (SELECT 1 n UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4 UNION ALL SELECT 5 UNION ALL SELECT 6 UNION ALL SELECT 7 UNION ALL SELECT 8 UNION ALL SELECT 9 UNION ALL SELECT 10) numbers \r\n"
		    + "        WHERE CHAR_LENGTH(vi.keywords) - CHAR_LENGTH(REPLACE(vi.keywords, ',', '')) >= numbers.n-1 \r\n"
		    + "        AND vi.upload_date BETWEEN DATE_SUB(NOW(), INTERVAL 1 YEAR) AND NOW() \r\n"
		    + "    ) v \r\n"
		    + "    GROUP BY v.channel_idx, v.keyword \r\n"
		    + "    ORDER BY keyword_count DESC \r\n"
		    + ") as RankedKeywords \r\n"
		    + "JOIN channel_info c ON RankedKeywords.channel_idx = c.channel_idx \r\n"
		    + "WHERE row_num <= 5 \r\n"
		    + "GROUP BY c.channel, c.subs, c.channel_image \r\n"
		    + "ORDER BY rand();", 
		    nativeQuery = true)
      Page<Map<String, Object>> findAllChannelsAndVideos2(Pageable pageable);
}
