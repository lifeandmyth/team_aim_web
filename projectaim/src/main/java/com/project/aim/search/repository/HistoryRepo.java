package com.project.aim.search.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.aim.search.entity.KeywordHistory;

public interface HistoryRepo extends JpaRepository<KeywordHistory, Long>{

	List<KeywordHistory> findBysearchDateBefore(LocalDateTime thresholdDateTime);

	
    @Query(nativeQuery = true, value =
            "SELECT\r\n" +
            "    keywords,\r\n" +
            "    COUNT(*) AS keyword_count,\r\n" +
            "    (COUNT(*) / (SELECT COUNT(*) FROM keyword_history)) * 100 AS keyword_percentage\r\n" +
            "FROM\r\n" +
            "    keyword_history\r\n" +
            "GROUP BY\r\n" +
            "    keywords\r\n" +
            "ORDER BY\r\n" +
            "    keyword_count DESC\r\n" +
            "LIMIT 10")
	   List<Map<String, Object>> findsearchhistorytop5();
}
