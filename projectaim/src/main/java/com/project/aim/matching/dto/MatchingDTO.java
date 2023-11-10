package com.project.aim.matching.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class MatchingDTO {
	
	private int matchingIdx;
	private String matchingTitle;
	private String provider;
	private String userSendName;
	private String matchingDetails;
	private String matchingGoal;
	private int matchingCostsMin;
	private int matchingCostsMax;
	private String adMethodsDetails; 
    private LocalDate date;
    private String updateDate;
    private int status;
    private String userGetEmail;
	private String userSendEmail;
}