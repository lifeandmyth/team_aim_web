package com.project.aim.search.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.project.aim.common.Criteria;
import com.project.aim.search.dto.DetailDTO;
import com.project.aim.search.dto.HistoryDTO;
import com.project.aim.search.dto.SearchDTO;
import com.project.aim.search.dto.UrlDTO;
import com.project.aim.search.entity.KeywordHistory;
import com.project.aim.search.repository.DetailRepo;
import com.project.aim.search.repository.HistoryRepo;
import com.project.aim.search.repository.SearchRepo;
import com.project.aim.search.repository.UrlRepo;

@Service
public class SearchService {
	
	private static final Logger log = LoggerFactory.getLogger(SearchService.class);
	private final SearchRepo searchRepo;
	private final DetailRepo detailRepo;
	private final UrlRepo urlRepo;
	private final HistoryRepo historyRepo;
	private final ModelMapper modelMapper;

	@Autowired
	public SearchService(SearchRepo searchRepo, ModelMapper modelMapper , DetailRepo detailRepo, UrlRepo urlRepo, HistoryRepo historyRepo) {
		this.searchRepo = searchRepo;
		this.detailRepo = detailRepo;
		this.urlRepo = urlRepo;
		this.historyRepo = historyRepo;
		this.modelMapper = modelMapper;
	}
	
	/* keywords를 매개변수로 검색 결과 리스트 크기 반환 */
	public int SearchListSize(String keywords) {
		
		log.info("[MainService] selectSearchList Start ==========");
		
		List<Map<String, Object>> InfoList = searchRepo.findChannelDataWithKeywords(keywords);
		
		log.info("[MainService] selectSearchList End ==========");
		
		return InfoList.size();

	}
	
	/* 검색결과 페이징 처리를 관련 정보 추출 */
	public Object searchListContent(Criteria cri, String keywords) {
		
		log.info("[SearchService] searchkeywordList Start ==========");
		
		int index = cri.getPageNum() - 1;
        int count = cri.getAmount(); 
        Pageable paging = PageRequest.of(index, count);

        Page<Map<String, Object>> result = searchRepo.findChannelDataWithKeywords2(paging, keywords);
        List<Map<String, Object>> channelList = result.getContent();
		
        log.info("[SearchService] searchkeywordList End ==========");
        
		return channelList.stream().map(data -> modelMapper.map(data, SearchDTO.class));
	}
	
	/* 세부 사항의 월별 통계를 호출 */
	public List<DetailDTO> searchMonthlykeyword(String channel, String keywords) {
		
		log.info("[SearchService] searchMonthlykeyword Start ==========");
		
		
    	List<Map<String, Object>> monthgraphlist = detailRepo.findmonthlykeyworddata(channel, keywords);
    	
    	log.info("[SearchService] searchMonthlykeyword End ==========");
    	
	    return monthgraphlist.stream().map(data -> modelMapper.map(data, DetailDTO.class)).collect(Collectors.toList());
	}
	
	/* 채널별 키워드별 월별 영상의 제목과 시점, url을 호출 */
	public List<UrlDTO> searchTitleAndUrl(String channel, String keywords, String month) {
		
		log.info("[SearchService] searchTitleAndUrl Start ==========");
				
    	List<Map<String, Object>> titleurllist = urlRepo.findtitleandurl(channel, keywords, month);
    	
    	log.info("[SearchService] searchTitleAndUrl End ==========");
    	
	    return titleurllist.stream().map(data -> modelMapper.map(data, UrlDTO.class)).collect(Collectors.toList());
	}

	public void saveKeywordHistory(String keywords, LocalDateTime searchDate) {

		log.info("========== [SearchService] saveKeywordHistory Strat ==========");
		log.info("searchDate" + searchDate);
		
        // 검색 일자의 형식을 확인하고 필요한 가공을 수행
        // 검색 키워드와 검색 일자를 키워드 기록 엔티티에 저장
        KeywordHistory keywordHistory = new KeywordHistory();
        keywordHistory.setKeywords(keywords);
        keywordHistory.setSearchDate(searchDate);

        // Repository를 사용하여 DB에 저장
        historyRepo.save(keywordHistory);
        
        log.info("========== [SearchService] saveKeywordHistory End ==========");

    }
	
	/* 기록 삭제 정책 구현 */
	public List<KeywordHistory> findOutdatedKeywordHistories(LocalDateTime thresholdDateTime) {
        return historyRepo.findBysearchDateBefore(thresholdDateTime);
    }

    public void deleteKeywordHistory(Long history_idx) {
    	historyRepo.deleteById(history_idx);
    }
    
    
    public List<HistoryDTO> searchkeywordhistory() {
		
		log.info("[SearchService] searchMonthlykeyword Start ==========");
		
		
    	List<Map<String, Object>> historyList = historyRepo.findsearchhistorytop5();
    	
    	log.info("[SearchService] searchMonthlykeyword End ==========");
    	
	    return historyList.stream().map(data -> modelMapper.map(data, HistoryDTO.class)).collect(Collectors.toList());
	}
}
















