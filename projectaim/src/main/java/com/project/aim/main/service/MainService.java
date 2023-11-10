package com.project.aim.main.service;

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
import com.project.aim.main.dto.ChannelVideoDTO;
import com.project.aim.main.repository.ChannelRepo;
import com.project.aim.search.dto.DetailDTO;
import com.project.aim.search.dto.SearchDTO;
import com.project.aim.search.repository.DetailRepo;
import com.project.aim.search.repository.SearchRepo;

@Service
public class MainService {
	
	private static final Logger log = LoggerFactory.getLogger(MainService.class);
	private final SearchRepo searchRepo;
	private final ChannelRepo channelRepo;
	private final DetailRepo detailRepo;
	private final ModelMapper modelMapper;

	@Autowired
	public MainService(SearchRepo searchRepo, ModelMapper modelMapper, ChannelRepo channelRepo, DetailRepo detailRepo) {
		this.searchRepo = searchRepo;
		this.detailRepo = detailRepo;
		this.modelMapper = modelMapper;
		this.channelRepo = channelRepo;
	}
	
	/* 메인 페이지 리스트 반환 */
    public int findAllChannelsAndVideos() {
    	
    	List<Map<String, Object>> channelList = channelRepo.findAllChannelsAndVideos();
    	
    	log.info("채널리스트 값은? : " + channelList);
    	
        return channelList.size();
    }
    
	public Object selectMainTotlWithPaging(Criteria cri) {
		
	   log.info("[MainService] selectMainTotlWithPaging Start ==========");
		
       int index = cri.getPageNum() - 1;
       int count = cri.getAmount(); 
       Pageable paging = PageRequest.of(index, count);
       
       Page<Map<String, Object>> result = channelRepo.findAllChannelsAndVideos2(paging);
       List<Map<String, Object>> channelList2 = result.getContent();

       log.info("[MainService] selectMainTotlWithPaging End ==========");
       
       return channelList2.stream().map(data -> modelMapper.map(data, ChannelVideoDTO.class));
	}
    
}
















