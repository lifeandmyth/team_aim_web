package com.project.aim.user.dto;

import java.util.Date;

import lombok.Data;

@Data
public class UserDTO {

	private int webLoginIdx;
    private String userEmail;
    private String userPhone;
    private String userPassword; 
    private String userName;
    private Date createdAt;
    private Date updatedAt;
    private String role;
}