package com.project.aim.search.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.aim.search.entity.Channel;

public interface DetailRepo extends JpaRepository<Channel, Integer>{

	/* 검색결과 리스트 -> 세부사항 : 채널별 월별 키워드 집계 */
//    @Query(value = 
//    "SELECT\r\n" +
//    "     c.channel AS channel_name,\r\n" +
//    "     c.subs,\r\n" +
//    "     m.month AS upload_month,\r\n" +
//    "     IFNULL(v.keyword_count, 0) AS keyword_count\r\n" +
//    "FROM channel_info c\r\n" +
//    "CROSS JOIN (\r\n" +
//    "    SELECT DISTINCT DATE_FORMAT(NOW(), '%Y-%m') AS month\r\n" +
//    "    UNION ALL\r\n" +
//    "    SELECT DISTINCT DATE_FORMAT(DATE_SUB(NOW(), INTERVAL 1 MONTH), '%Y-%m')\r\n" +
//    "    UNION ALL\r\n" +
//    "    SELECT DISTINCT DATE_FORMAT(DATE_SUB(NOW(), INTERVAL 2 MONTH), '%Y-%m')\r\n" +
//    "    UNION ALL\r\n" +
//    "    SELECT DISTINCT DATE_FORMAT(DATE_SUB(NOW(), INTERVAL 3 MONTH), '%Y-%m')\r\n" +
//    "    UNION ALL\r\n" +
//    "    SELECT DISTINCT DATE_FORMAT(DATE_SUB(NOW(), INTERVAL 4 MONTH), '%Y-%m')\r\n" +
//    "    UNION ALL\r\n" +
//    "    SELECT DISTINCT DATE_FORMAT(DATE_SUB(NOW(), INTERVAL 5 MONTH), '%Y-%m')\r\n" +
//    "    UNION ALL\r\n" +
//    "    SELECT DISTINCT DATE_FORMAT(DATE_SUB(NOW(), INTERVAL 6 MONTH), '%Y-%m')\r\n" +
//    "    UNION ALL\r\n" +
//    "    SELECT DISTINCT DATE_FORMAT(DATE_SUB(NOW(), INTERVAL 7 MONTH), '%Y-%m')\r\n" +
//    "    UNION ALL\r\n" +
//    "    SELECT DISTINCT DATE_FORMAT(DATE_SUB(NOW(), INTERVAL 8 MONTH), '%Y-%m')\r\n" +
//    "    UNION ALL\r\n" +
//    "    SELECT DISTINCT DATE_FORMAT(DATE_SUB(NOW(), INTERVAL 9 MONTH), '%Y-%m')\r\n" +
//    "    UNION ALL\r\n" +
//    "    SELECT DISTINCT DATE_FORMAT(DATE_SUB(NOW(), INTERVAL 10 MONTH), '%Y-%m')\r\n" +
//    "    UNION ALL\r\n" +
//    "    SELECT DISTINCT DATE_FORMAT(DATE_SUB(NOW(), INTERVAL 11 MONTH), '%Y-%m')\r\n" +
//    ") m\r\n" +
//    "LEFT JOIN (\r\n" +
//    "    SELECT\r\n" +
//    "        c.channel AS channel_name,\r\n" +
//    "        DATE_FORMAT(v.upload_date, '%Y-%m') AS month,\r\n" +
//    "        SUM(LENGTH(v.keywords) - LENGTH(REPLACE(v.keywords, :keywords, ''))) / LENGTH(:keywords) AS keyword_count\r\n" +
//    "    FROM channel_info c\r\n" +
//    "    JOIN video_info v ON c.channel_idx = v.channel_idx\r\n" +
//    "    WHERE v.upload_date IS NOT NULL\r\n" +
//    "      AND v.upload_date >= DATE_SUB(NOW(), INTERVAL 1 YEAR)\r\n" +
//    "    GROUP BY channel_name, month\r\n" +
//    ") v ON v.month = m.month AND c.channel = v.channel_name\r\n" +
//    "WHERE c.channel IN (\r\n" +
//    "    SELECT channel_name\r\n" +
//    "    FROM (\r\n" +
//    "        SELECT\r\n" +
//    "            c.channel AS channel_name,\r\n" +
//    "            SUM(LENGTH(v.keywords) - LENGTH(REPLACE(v.keywords, :keywords, ''))) / LENGTH(:keywords) AS total_keyword_count\r\n" +
//    "        FROM channel_info c\r\n" +
//    "        JOIN video_info v ON c.channel_idx = v.channel_idx\r\n" +
//    "        WHERE v.upload_date IS NOT NULL\r\n" +
//    "          AND v.upload_date >= DATE_SUB(NOW(), INTERVAL 1 YEAR)\r\n" +
//    "        GROUP BY channel_name\r\n" +
//    "    ) AS inner_query\r\n" +
//    "    WHERE total_keyword_count > 0\r\n" +
//    ")\r\n" +
//    "GROUP BY channel_name, subs, upload_month\r\n" +
//    "ORDER BY channel_name, upload_month DESC"
//    , nativeQuery = true)
//    List<Map<String, Object>> findmonthlykeyworddata(@Param("keywords") String keywords);

	@Query(value = 
		    "SELECT\r\n" +
		    "     c.channel AS channel_name,\r\n" +
		    "     c.subs,\r\n" +
		    "     m.month AS upload_month,\r\n" +
		    "     IFNULL(v.keyword_count, 0) AS keyword_count\r\n" +
		    "FROM channel_info c\r\n" +
		    "CROSS JOIN (\r\n" +
		    "    SELECT DISTINCT DATE_FORMAT(NOW(), '%Y-%m') AS month\r\n" +
		    "    UNION ALL\r\n" +
		    "    SELECT DISTINCT DATE_FORMAT(DATE_SUB(NOW(), INTERVAL 1 MONTH), '%Y-%m')\r\n" +
		    "    UNION ALL\r\n" +
		    "    SELECT DISTINCT DATE_FORMAT(DATE_SUB(NOW(), INTERVAL 2 MONTH), '%Y-%m')\r\n" +
		    "    UNION ALL\r\n" +
		    "    SELECT DISTINCT DATE_FORMAT(DATE_SUB(NOW(), INTERVAL 3 MONTH), '%Y-%m')\r\n" +
		    "    UNION ALL\r\n" +
		    "    SELECT DISTINCT DATE_FORMAT(DATE_SUB(NOW(), INTERVAL 4 MONTH), '%Y-%m')\r\n" +
		    "    UNION ALL\r\n" +
		    "    SELECT DISTINCT DATE_FORMAT(DATE_SUB(NOW(), INTERVAL 5 MONTH), '%Y-%m')\r\n" +
		    "    UNION ALL\r\n" +
		    "    SELECT DISTINCT DATE_FORMAT(DATE_SUB(NOW(), INTERVAL 6 MONTH), '%Y-%m')\r\n" +
		    "    UNION ALL\r\n" +
		    "    SELECT DISTINCT DATE_FORMAT(DATE_SUB(NOW(), INTERVAL 7 MONTH), '%Y-%m')\r\n" +
		    "    UNION ALL\r\n" +
		    "    SELECT DISTINCT DATE_FORMAT(DATE_SUB(NOW(), INTERVAL 8 MONTH), '%Y-%m')\r\n" +
		    "    UNION ALL\r\n" +
		    "    SELECT DISTINCT DATE_FORMAT(DATE_SUB(NOW(), INTERVAL 9 MONTH), '%Y-%m')\r\n" +
		    "    UNION ALL\r\n" +
		    "    SELECT DISTINCT DATE_FORMAT(DATE_SUB(NOW(), INTERVAL 10 MONTH), '%Y-%m')\r\n" +
		    "    UNION ALL\r\n" +
		    "    SELECT DISTINCT DATE_FORMAT(DATE_SUB(NOW(), INTERVAL 11 MONTH), '%Y-%m')\r\n" +
		    ") m\r\n" +
		    "LEFT JOIN (\r\n" +
		    "    SELECT\r\n" +
		    "        c.channel AS channel_name,\r\n" +
		    "        DATE_FORMAT(v.upload_date, '%Y-%m') AS month,\r\n" +
		    "        SUM(LENGTH(v.keywords) - LENGTH(REPLACE(v.keywords, :keywords, ''))) / LENGTH(:keywords) AS keyword_count\r\n" +
		    "    FROM channel_info c\r\n" +
		    "    JOIN video_info v ON c.channel_idx = v.channel_idx\r\n" +
		    "    WHERE v.upload_date IS NOT NULL\r\n" +
		    "      AND v.upload_date >= DATE_SUB(NOW(), INTERVAL 1 YEAR)\r\n" +
		    "      AND c.channel = :channel\r\n" +
		    "    GROUP BY channel_name, month\r\n" +
		    ") v ON v.month = m.month AND c.channel = v.channel_name\r\n" +
		    "WHERE c.channel IN (\r\n" +
		    "    SELECT channel_name\r\n" +
		    "    FROM (\r\n" +
		    "        SELECT\r\n" +
		    "            c.channel AS channel_name,\r\n" +
		    "            SUM(LENGTH(v.keywords) - LENGTH(REPLACE(v.keywords, :keywords, ''))) / LENGTH(:keywords) AS total_keyword_count\r\n" +
		    "        FROM channel_info c\r\n" +
		    "        JOIN video_info v ON c.channel_idx = v.channel_idx\r\n" +
		    "        WHERE v.upload_date IS NOT NULL\r\n" +
		    "          AND v.upload_date >= DATE_SUB(NOW(), INTERVAL 1 YEAR)\r\n" +
		    "          AND c.channel = :channel\r\n" +
		    "        GROUP BY channel_name\r\n" +
		    "    ) AS inner_query\r\n" +
		    "    WHERE total_keyword_count > 0\r\n" +
		    ")\r\n" +
		    "GROUP BY channel_name, subs, upload_month\r\n" +
		    "ORDER BY channel_name, upload_month ASC"
		    , nativeQuery = true)
		List<Map<String, Object>> findmonthlykeyworddata(@Param("channel") String channel, @Param("keywords") String keywords);


}



