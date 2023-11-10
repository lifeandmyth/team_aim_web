package com.project.aim.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.aim.user.entity.GoogleLogin;

public interface GoogleLoginRepository extends JpaRepository<GoogleLogin, Integer>{
	
	GoogleLogin findByUserEmail(String userEmail);
}