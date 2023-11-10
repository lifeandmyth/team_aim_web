package com.project.aim.review.controller;

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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.aim.common.Criteria;
import com.project.aim.common.PageDTO;
import com.project.aim.common.PagingResponseDTO;
import com.project.aim.common.ResponseDTO;
import com.project.aim.review.dto.ReviewDTO;
import com.project.aim.review.service.ReviewService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;


@RestController
@RequestMapping("/")
@CrossOrigin(origins = "http://localhost:3000")
public class ReviewController {

   private static final Logger log = LoggerFactory.getLogger(ReviewController.class);
   private final ReviewService reviewService;
   private final ObjectMapper objectMapper;

   @Autowired
   public ReviewController(ReviewService reviewService, ObjectMapper objectMapper) {
      this.reviewService = reviewService;
      this.objectMapper = objectMapper;
   }

   @Operation(summary = "리뷰 게시물 조회 요청", description = "리뷰 게시물 조회 및 페이징 처리가 진행됩니다.", tags = { "ReviewController" })
   @GetMapping("/review")
   public ResponseEntity<ResponseDTO> searchReview(@RequestParam(name = "offset", defaultValue = "1") String offset) {
	   
	   log.info("========== [ReviewController] searchReview Strat =========="); 
	   
	   int total = reviewService.ReviewListSize();
		
	   Criteria cri = new Criteria(Integer.valueOf(offset), 13);
	   PagingResponseDTO pagingResponseDTO = new PagingResponseDTO();
		
	   pagingResponseDTO.setData(reviewService.selectReviewTotlWithPaging(cri));
	   pagingResponseDTO.setPageInfo(new PageDTO(cri, total));
	   
	   log.info("========== [ReviewController] searchReview End =========="); 
	  
	   return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "리뷰 게시물 조회 성공",  pagingResponseDTO));
   }
   @PostMapping("/review/modal")
    public ResponseEntity<?> createReview(@RequestBody ReviewDTO reviewDTO) {
        
        ReviewDTO reviewDto = new ReviewDTO();

        reviewDto.setchannelIdx(20); // 가정된 manager_id 값
        reviewDto.setmanagerId(1); // 가정된 manager_id 값
        reviewDto.setTitle("강아지를 위해 120평 마당에 잔디를 깔아줫더니...");
        reviewDto.setViews(0);
        reviewDto.setuploadDate("2023-11-08 00:00:00"); // 현재 날짜나 특정 포맷에 맞는 문자열
        reviewDto.setcmtVibeGood(210);
        reviewDto.setcmtVibeBad(50);
        reviewDto.setthumbUrl("https://img.youtube.com/vi/PsSPHAJjzfg/0.jpg");
        reviewDto.setvidUrl("https://www.youtube.com/watch?v=PsSPHAJjzfg");
        reviewService.createReview(reviewDto);
        System.out.println(reviewDto);
        return ResponseEntity.ok("Review created successfully       ");
    }
//    @PostMapping("/review/modal")
//     public ResponseEntity<?> createReview(@RequestBody String reviewData) {
//         try {
//         // 로깅을 통해 요청 본문 확인
//         System.out.println("Received review data: " + reviewData);
//         // String 형태의 JSON을 ReviewDTO로 변환
//         ReviewDTO reviewDTO = objectMapper.readValue(reviewData, ReviewDTO.class);

//         reviewService.createReview(reviewDTO);

//         return ResponseEntity.ok("Review created successfully");
//         } catch (Exception e) {
//             e.printStackTrace();
//             // JSON 파싱에 실패하면 클라이언트에 오류 메시지를 반환
//             return ResponseEntity.badRequest().body("Invalid review data");
//         }
//     }
}  
















