package com.project.aim.main.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.aim.common.Criteria;
import com.project.aim.common.PageDTO;
import com.project.aim.common.PagingResponseDTO;
import com.project.aim.common.ResponseDTO;
import com.project.aim.main.service.MainService;
import com.project.aim.search.dto.DetailDTO;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/")
@CrossOrigin(origins = "http://localhost:3000")
public class MainController {

   private static final Logger log = LoggerFactory.getLogger(MainController.class);
   private final MainService mainService;
   
   @Autowired
   public MainController(MainService mainService) {
      this.mainService = mainService;
   }
   
   @Operation(summary = "메인페이지 랜덤리스트 조회 요청", description = "메인페이지 랜덤리스트 조회 및 페이징 처리가 진행됩니다.", tags = { "MainController" })
   @GetMapping("/main")
   public ResponseEntity<ResponseDTO> selectMainTotlWithPaging(@RequestParam(name = "offset", defaultValue = "1") String offset) {
	   
	 log.info("========== [MainController] selectMainTotlWithPaging Strat ==========");  
	 
	 int total = mainService.findAllChannelsAndVideos();
	 
     Criteria cri = new Criteria(Integer.valueOf(offset), 15);
     PagingResponseDTO pagingResponseDTO = new PagingResponseDTO();
     
     pagingResponseDTO.setData(mainService.selectMainTotlWithPaging(cri));
     pagingResponseDTO.setPageInfo(new PageDTO(cri, total));
     
     log.info("오프셋 값은? : " + offset);
     log.info("========== [MainController] selectMainTotlWithPaging End ==========");
     
     return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공",  pagingResponseDTO));
   }
   
}  

