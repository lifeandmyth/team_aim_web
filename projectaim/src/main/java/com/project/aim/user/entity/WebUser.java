package com.project.aim.user.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

/* 웹 사용자 */
@Getter
@Setter
@Entity
@Table(name = "web_login")
public class WebUser {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "web_login_idx")
	private int webLoginIdx;
	
	@Column(name = "user_name", nullable = false, length = 100)
	private String userName;

	@Column(name = "user_email", nullable = false, length = 100)
	private String userEmail;

	@Column(name = "user_password", length = 255)
	private String userPassword;

	@Column(name = "created_at", nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;

	@Column(name = "update_at", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedAt;

	@Column(name = "user_phone", length = 150)
	private String userPhone;

	@Enumerated(EnumType.STRING)
	@Column(name = "role", length = 255)
	private Role role = Role.ADVERTISOR;

}