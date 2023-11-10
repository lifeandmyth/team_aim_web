package com.project.aim.search.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.aim.common.Criteria;
import com.project.aim.common.PageDTO;
import com.project.aim.common.PagingResponseDTO;
import com.project.aim.common.ResponseDTO;
import com.project.aim.search.dto.DetailDTO;
import com.project.aim.search.dto.HistoryDTO;
import com.project.aim.search.dto.UrlDTO;
import com.project.aim.search.entity.KeywordHistory;
import com.project.aim.search.service.SearchService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/")
@CrossOrigin(origins = "http://localhost:3000")
public class SearchController {

   private static final Logger log = LoggerFactory.getLogger(SearchController.class);
   private final SearchService searchService;
   
   @Autowired
   public SearchController(SearchService searchService) {
      this.searchService = searchService;
   }

   
   @Operation(summary = "키워드 검색결과 조회 요청", description = "키워드 검색결과 조회 및 페이징 처리가 진행됩니다.", tags = { "SearchController" })
   @GetMapping("/search")
   public ResponseEntity<ResponseDTO> searchKeywords(@RequestParam(name = "offset", defaultValue = "1") String offset, 
		   											 @RequestParam(name = "keywords") String keywords){
	   
	   log.info("========== [MainController] searchKeywords Strat =========="); 
	   
	   int total = searchService.SearchListSize(keywords);
		
	   Criteria cri = new Criteria(Integer.valueOf(offset), 10);
	   PagingResponseDTO pagingResponseDTO = new PagingResponseDTO();
		
	   pagingResponseDTO.setData(searchService.searchListContent(cri, keywords));
	   pagingResponseDTO.setPageInfo(new PageDTO(cri, total));
	   
	   log.info("========== [MainController] searchKeywords End =========="); 
	  
	   return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "키워드 검색 조회 성공",  pagingResponseDTO));
   }
   
  
   @Operation(summary = "월별통계 결과 조회 요청", description = "월별통계 결과 조회가 진행됩니다.", tags = { "SearchController" })
   @GetMapping("/searchdetail")
   public ResponseEntity<ResponseDTO> searchMonthlykeyword(@RequestParam(name = "keywords") String keywords,
		   												   @RequestParam(name = "channel") String channel) {
	   
	   log.info("========== [MainController] searchMonthlykeyword Strat ==========");
	   
       List<DetailDTO> results = searchService.searchMonthlykeyword(channel, keywords);
       	
       return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "월별통계조회 성공", results));
   }
   
   @Operation(summary = "시점url 결과 조회 요청", description = "시점url 결과 조회가 진행됩니다.", tags = { "SearchController" })
   @GetMapping("/searchmodal")
   public ResponseEntity<ResponseDTO> searchMonthlykeyword(@RequestParam(name = "keywords") String keywords,
		   												   @RequestParam(name = "channel") String channel,
		   												   @RequestParam(name = "month") String month) {
	   
	   log.info("========== [MainController] searchMonthlykeyword Strat ==========");
	   
       List<UrlDTO> results = searchService.searchTitleAndUrl(channel, keywords, month);
       	
       return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "월별통계조회 성공", results));
   }

   @Operation(summary = "검색 키워드 기록 등록 요청", description = "검색 키워드 기록 등록이 진행됩니다.", tags = { "SearchController" })
   @PostMapping("/keywordHistory")
   public ResponseEntity<?> InsertKeywordHistory(@RequestBody Map<String, String> requestData) {
	   
	   log.info("========== [SearchController] InsertKeywordHistory Strat ==========");
       try {
           String keyword = requestData.get("keywords");
           String searchDateStr = requestData.get("searchDate");
           LocalDateTime searchDate = LocalDateTime.parse(searchDateStr, DateTimeFormatter.ISO_DATE_TIME);

           searchService.saveKeywordHistory(keyword, searchDate);

           LocalDateTime currentDateTime = LocalDateTime.now();
           LocalDateTime thresholdDateTime = currentDateTime.minus(1, ChronoUnit.DAYS);
           List<KeywordHistory> outdatedHistories = searchService.findOutdatedKeywordHistories(thresholdDateTime);
           for (KeywordHistory history : outdatedHistories) {
               searchService.deleteKeywordHistory(history.getHistory_idx());
           }
           
           Map<String, Object> response = new HashMap<>();
           response.put("message", "키워드 기록이 성공적으로 저장되었습니다.");
           return new ResponseEntity<>(response, HttpStatus.CREATED);
           
       } catch (Exception e) {
           Map<String, Object> errorResponse = new HashMap<>();
           errorResponse.put("error", "키워드 기록 저장 중 오류가 발생했습니다.");
           return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
       }

   }
   
   @Operation(summary = "상위 키워드 기록 조회 요청", description = "상위 키워드 기록 조회가 진행됩니다.", tags = { "SearchController" })
   @GetMapping("/historyranking")
   public ResponseEntity<ResponseDTO> searchkeywordranking() {
	   
	   log.info("========== [MainController] searchMonthlykeyword Strat ==========");
	   
       List<HistoryDTO> results = searchService.searchkeywordhistory();
       	
       return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "월별통계조회 성공", results));
   }
   
}  

















