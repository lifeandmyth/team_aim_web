package com.project.aim.review.service;

import java.util.List;
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
import com.project.aim.review.dto.ReviewDTO;
import com.project.aim.review.entity.Review;
import com.project.aim.review.repository.ReviewRepo;
import com.project.aim.search.service.SearchService;

import jakarta.transaction.Transactional;

@Service
public class ReviewService {

	private static final Logger log = LoggerFactory.getLogger(SearchService.class);
	private final ReviewRepo reviewRepo;
	private final ModelMapper modelMapper;
	
	@Autowired
	public ReviewService(ModelMapper modelMapper, ReviewRepo reviewRepo) {
		this.reviewRepo = reviewRepo;
		this.modelMapper = modelMapper;
	}
	
	
	public int ReviewListSize() {
		log.info("[ReviewService] ReviewListSize Start ==========");
		
		List<Review> reviewList = reviewRepo.findAll();
		
		log.info("[reviewList 사이즈는? : " , reviewList.size());
		log.info("[ReviewService] ReviewListSize End ==========");
		
		return reviewList.size();
	}
	
   @Transactional
   public Object selectReviewTotlWithPaging(Criteria cri) {
	   
	   log.info("[ReviewService] selectReviewTotlWithPaging Start ===================================");
      
       int index = cri.getPageNum() - 1;
       int count = cri.getAmount(); 
       Pageable paging = PageRequest.of(index, count);
        
       Page<Review> result = reviewRepo.findAll(paging);
       List<Review> boardList = (List<Review>)result.getContent();
        
       log.info("[ReviewService] selectReviewTotlWithPaging End ===================================");
        
       return boardList.stream().map(board -> modelMapper.map(board, Review.class)).collect(Collectors.toList());
   }
   

    public Review createReview(ReviewDTO reviewDTO) {
        Review review = new Review();
        // Set fields from reviewDTO to review
        // ...
        review.setChannel_idx(reviewDTO.getchannelIdx());
        review.setManager_id(reviewDTO.getmanagerId());
        review.setTitle(reviewDTO.getTitle());
        review.setViews(reviewDTO.getViews());
        review.setUpload_date(reviewDTO.getuploadDate());
        review.setCmt_vibe_good(reviewDTO.getcmtVibeGood());
        review.setCmt_vibe_bad(reviewDTO.getcmtVibeBad());
        review.setThumb_url(reviewDTO.getthumbUrl());
        review.setVid_url(reviewDTO.getvidUrl());

        return reviewRepo.save(review);
    }
}
