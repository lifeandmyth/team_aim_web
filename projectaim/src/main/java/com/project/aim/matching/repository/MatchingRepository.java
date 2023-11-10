package com.project.aim.matching.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.aim.matching.entity.Matching;

public interface MatchingRepository extends JpaRepository<Matching, Integer> {

	@Query(value = "SELECT m.* FROM seeddata_matching m "
			+ "JOIN web_login w ON m.user_get_email = w.user_email OR m.user_send_email = w.user_email "
			+ "WHERE w.user_email = :userEmail", nativeQuery = true)
	List<Matching> findMatchingsByUserEmail(@Param("userEmail") String userEmail);

	List<Matching> findAllByOrderByMatchingIdxDesc();

	@Query(value = "SELECT m.* FROM seeddata_matching m "
			+ "JOIN web_login w ON m.user_get_email = w.user_email OR m.user_send_email = w.user_email "
			+ "WHERE (w.user_email = :userEmail OR m.user_send_email = :userEmail) AND m.matching_idx = :matchingIdx", nativeQuery = true)
	Matching findByUserGetEmailAndMatchingIdx(@Param("userEmail") String userEmail,
			@Param("matchingIdx") Long matchingIdx);

}