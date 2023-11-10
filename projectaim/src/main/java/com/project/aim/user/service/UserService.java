package com.project.aim.user.service;

import java.util.Date;
import java.util.Optional;

import org.springframework.core.env.Environment;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.project.aim.user.dto.UserDTO;
import com.project.aim.user.entity.Role;
import com.project.aim.user.entity.WebUser;
import com.project.aim.user.repository.UserRepository;

/* 웹 사용자 */
@Service
public class UserService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final Environment env;
	

	public UserService(Environment env, UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.env = env;
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}
	
	

	/* 회원가입 */
	public WebUser createUser(UserDTO userDto) {
		WebUser user = new WebUser();
		user.setUserEmail(userDto.getUserEmail());
		user.setUserPassword(passwordEncoder.encode(userDto.getUserPassword()));
		user.setUserPhone(userDto.getUserPhone());
		user.setCreatedAt(new Date());
		user.setUpdatedAt(new Date());
		user.setRole(Role.ADVERTISOR);

		return userRepository.save(user);
	}

	
	/* 로그인 */
	public String loginUser(UserDTO userDto) {
		Optional<WebUser> optionalUser = userRepository.findByUserEmail(userDto.getUserEmail());

		WebUser user = optionalUser.orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다"));

		if (passwordEncoder.matches(userDto.getUserPassword(), user.getUserPassword())) {
			return generateToken(user);
		} else {
			throw new RuntimeException("비밀번호가 일치하지 않습니다.");
		}
	}
	
	
	
	
	  // JWT 토큰 생성 메서드
    public String generateToken(WebUser user) {
    	
    	 String secretKey = env.getProperty("jwt.secret.key");
        // 알고리즘 정의, 실제 환경에서는 `secret`를 감추어야 함
        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        // 현재 시간 및 만료 시간 설정 (예: 1시간 후 만료)
        Date issueDate = new Date();
        Date expiryDate = new Date(issueDate.getTime() + 3600 * 1000);

        // JWT 토큰 생성
        String token = JWT.create()
                .withSubject(user.getUserEmail())  // 사용자의 이메일을 subject로 사용
                .withIssuedAt(issueDate) // 발행 시간
                .withExpiresAt(expiryDate) // 만료 시간
                .withClaim("role", user.getRole().name()) // 사용자 역할
                .sign(algorithm); // 알고리즘과 서명

        return token;
    }

    
    /* 마이페이지 */
	public UserDTO getUserInfo(String userEmail) {
		Optional<WebUser> optionalUser = userRepository.findByUserEmail(userEmail);
		WebUser user = optionalUser.orElseThrow();
		if (user != null) {
			UserDTO userDTO = new UserDTO();
			userDTO.setWebLoginIdx(user.getWebLoginIdx());
			userDTO.setUserEmail(user.getUserEmail());
			userDTO.setUserPhone(user.getUserPhone());
			userDTO.setUserName(user.getUserName());
			userDTO.setWebLoginIdx(user.getWebLoginIdx());
			userDTO.setCreatedAt(new Date());
			userDTO.setUpdatedAt(new Date());
			userDTO.setRole(user.getRole().name());

			return userDTO;
		} else {
			throw new RuntimeException("사용자를 찾을 수 없습니다."); // 또는 적절한 응답 처리
		}
	}
}