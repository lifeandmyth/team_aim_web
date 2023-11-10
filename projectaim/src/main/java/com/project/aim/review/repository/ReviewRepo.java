package com.project.aim.review.repository;

import java.util.List;

import org.springdoc.core.converters.models.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import com.project.aim.review.entity.Review;

public interface ReviewRepo extends JpaRepository<Review, Integer> {

	List<Review> findAll();
	// Page<Review> findAllByOrderByUploadDateDesc(Pageable pageable);

}	
