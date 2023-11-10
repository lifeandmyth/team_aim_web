package com.project.aim.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.project.aim.search.entity.KeywordHistory;
import com.project.aim.search.service.SearchService;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Component
public class AutoDeleteScheduler {

    private final SearchService searchService;

    @Autowired
    public AutoDeleteScheduler(SearchService searchService) {
        this.searchService = searchService;
    }

    @Scheduled(cron = "0 0 0 * * ?") // 매일 자정에 실행
    public void cleanupKeywordHistories() {
        // 현재 날짜
    	LocalDateTime currentDateTime = LocalDateTime.now();
        
        // 삭제할 날짜를 계산
    	LocalDateTime thresholdDateTime = currentDateTime.minus(7, ChronoUnit.DAYS);
        
        // 오래된 키워드 기록 조회
        List<KeywordHistory> outdatedHistories = searchService.findOutdatedKeywordHistories(thresholdDateTime);
        
        // 조회된 키워드 기록 삭제
        for (KeywordHistory history : outdatedHistories) {
        	searchService.deleteKeywordHistory(history.getHistory_idx());
        }
    }
}
