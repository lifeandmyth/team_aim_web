package com.project.aim.user.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.aim.user.dto.UserDTO;
import com.project.aim.user.entity.WebUser;
import com.project.aim.user.service.UserService;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	/* 회원가입 */
	@PostMapping("/signup")
	public ResponseEntity<String> signup(@RequestBody UserDTO userDto) {
		try {
			userService.createUser(userDto);
			return ResponseEntity.ok("회원가입 성공");
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("회원가입 실패");
		}
	}

	/* 로그인 */
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody UserDTO userDto) {
		try {
			String token = userService.loginUser(userDto);
			 return ResponseEntity.ok(token);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("로그인 실패: " + e.getMessage());
		}
	}
	
	/* 마이페이지 유저 정보 */
	@GetMapping("/info/{userEmail}")
	public ResponseEntity<UserDTO> getUserInfo(@PathVariable String userEmail) {
		UserDTO userDTO = userService.getUserInfo(userEmail);
		if (userDTO != null) {
			return ResponseEntity.ok(userDTO);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}
}
