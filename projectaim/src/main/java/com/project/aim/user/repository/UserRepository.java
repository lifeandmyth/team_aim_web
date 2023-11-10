package com.project.aim.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.aim.user.entity.WebUser;

/* 웹 사용자 */
public interface UserRepository extends JpaRepository<WebUser, Long> {

	Optional<WebUser> findByUserEmail(String userEmail);
}