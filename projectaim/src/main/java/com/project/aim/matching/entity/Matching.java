package com.project.aim.matching.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter
@Entity
@Table(name = "seeddata_matching")
public class Matching {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "matching_idx")
	private int matchingIdx;
	
	@Column(name = "matching_title", columnDefinition = "varchar(150)", nullable = false)
	private String matchingTitle;
	
	@Column(name = "provider", columnDefinition = "varchar(100)", nullable = false)
	private String provider;
	
	@Column(name = "user_send_name", columnDefinition = "varchar(100)", nullable = false)
	private String userSendName;
	
	@Column(name = "matching_details", columnDefinition = "varchar(5500)")
	private String matchingDetails;
	
	@Column(name = "matching_goal")
	private String matchingGoal;

	@Column(name = "matching_costs_min")
	private int matchingCostsMin;
	
	@Column(name = "matching_costs_max")
	private int matchingCostsMax;

	@Column(name = "ad_methods_details")
	private String adMethodsDetails;
	
	@Column(name = "date", columnDefinition = "varchar(50)", nullable = false)
	private LocalDate date;

	@Column(name = "update_date", columnDefinition = "varchar(50)")
	private String updateDate;
	
	@Column(name = "status", columnDefinition = "int default 0")
	private int status; // 0: 대기, 1: 승인, 2: 거절
	
	@Column(name = "user_get_email", nullable = true, length = 100)
	private String userGetEmail;
	
	@Column(name = "user_send_email", nullable = true, length = 100)
	private String userSendEmail;
}