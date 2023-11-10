package com.project.aim.user.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.aim.user.service.GoogleLoginService;

import jakarta.servlet.http.HttpServletResponse;


@RestController
@RequestMapping(value = "/googlelogin", produces = "application/json")
@CrossOrigin(origins = "http://localhost:3000")
public class GoogleLoginController {

    private final GoogleLoginService googleLoginService;
    private static final Logger logger = LoggerFactory.getLogger(GoogleLoginController.class);

    public GoogleLoginController(GoogleLoginService googleLoginService) {
        this.googleLoginService = googleLoginService;
    }

    // 스프링 시큐리티가 자동으로 처리하는 OAuth2AuthenticationToken을 사용하여 구글 로그인 정보를 가져오는 엔드포인트입니다.
    @GetMapping
    public ResponseEntity<String> getGoogleLoginInfo(OAuth2AuthenticationToken authenticationToken) {
        if (authenticationToken != null) {
            OAuth2User oauth2User = authenticationToken.getPrincipal();
            String userName = oauth2User.getAttribute("name");
            String email = oauth2User.getAttribute("email");
            String picture = oauth2User.getAttribute("picture");

            logger.info("로그인한 사용자의 이름: {}", userName);
            logger.info("로그인한 사용자의 이메일: {}", email);

            // 추가적인 처리가 필요한 경우 여기에서 해당 서비스 메서드를 호출합니다.

            String userInfo = String.format("사용자 정보: 이름 - %s, 이메일 - %s, 사진 URL - %s", userName, email, picture);;
        }

        logger.warn("OAuth2AuthenticationToken이 요청에서 발견되지 않았습니다.");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("인증되지 않았습니다");
    }

    // 구글로부터 받은 인증 코드를 직접 처리하는 엔드포인트입니다.
    @PostMapping
    public ResponseEntity<String> googleLogin(@RequestBody String code, HttpServletResponse response) {
        try {
            String jwtToken = googleLoginService.socialLogin(code);
            // 성공적으로 로그인을 마치면 JWT 토큰이 반환됩니다.
            logger.info("사용자가 성공적으로 로그인했으며 JWT 토큰이 생성되었습니다.");
            response.setHeader("Authorization", "Bearer " + jwtToken); // JWT 토큰을 응답 헤더에 추가합니다.
            return ResponseEntity.ok().body(jwtToken);
        } catch (Exception e) {
            logger.error("구글 OAuth를 통한 로그인에 실패했습니다.", e);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인에 실패했습니다: " + e.getMessage());
        }
    }
}

