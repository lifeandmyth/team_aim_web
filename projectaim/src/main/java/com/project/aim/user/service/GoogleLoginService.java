package com.project.aim.user.service;

import java.util.Date;

import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.aim.user.dto.GoogleLoginDTO;
import com.project.aim.user.entity.GoogleLogin;
import com.project.aim.user.repository.GoogleLoginRepository;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional
public class GoogleLoginService {

	private final Environment env;
	private final RestTemplate restTemplate = new RestTemplate();
	private final GoogleLoginRepository loginRepository;

	public GoogleLoginService(Environment env, GoogleLoginRepository loginRepository) {
		this.env = env;
		this.loginRepository = loginRepository;
	}

	public String socialLogin(String code) { // registrationId parameter removed
		log.info("======================================================");
		String accessToken = getAccessToken(code);
		JsonNode userResourceNode = getUserResource(accessToken);

		System.out.println("소셜로그인-엑세스토큰" + accessToken);
		System.out.println("소셜로그인-제이슨노드" + userResourceNode);

		GoogleLoginDTO userResource = new GoogleLoginDTO();
		userResource.setId(userResourceNode.get("id").asText());
		userResource.setEmail(userResourceNode.get("email").asText());
		userResource.setName(userResourceNode.get("name").asText());
		userResource.setPicture(userResourceNode.get("picture").asText());

		log.info("id = {}", userResource.getId());
		log.info("email = {}", userResource.getEmail());
		System.out.println(userResource.getEmail());
		log.info("name {}", userResource.getName());
		log.info("======================================================");

		 GoogleLogin googleLogin = loginRepository.findByUserEmail(userResource.getEmail());

		    if (googleLogin == null) {
		        GoogleLogin newLogin = new GoogleLogin();
		        newLogin.setUserEmail(userResource.getEmail());
		        newLogin.setCreatedAt(new Date());
		        newLogin.setUpdateAt(new Date());
		        newLogin.setUserPicture(userResource.getPicture());

		        googleLogin = loginRepository.save(newLogin); // googleLogin을 새 객체로 갱신합니다.
		        log.info("회원가입완료", googleLogin);
		    } else {
		        log.info("등록회원입니다.", googleLogin);
		    }

		       // 인증 정보 생성
	        Authentication auth = new UsernamePasswordAuthenticationToken(
	                googleLogin, null, googleLogin.getAuthorities());

	        // SecurityContext에 인증 객체 설정
	        SecurityContextHolder.getContext().setAuthentication(auth);

	        // JWT 토큰 생성 및 반환
	        String jwtToken = generateToken(googleLogin);

	        log.info("======================================================");

	        // 클라이언트에게 JWT 토큰 반환
	        return jwtToken;
	    }

	    // JWT 토큰 생성 메서드
	    private String generateToken(GoogleLogin googleLogin) {
	        // 환경 변수에서 비밀 키 가져오기
	        String secretKey = env.getProperty("jwt.secret.key");

	        // 알고리즘 정의
	        Algorithm algorithm = Algorithm.HMAC256(secretKey);

	        // 현재 시간 및 만료 시간 설정
	        Date issueDate = new Date();
	        Date expiryDate = new Date(issueDate.getTime() + 3600 * 1000);

	        // JWT 토큰 생성
	        String token = JWT.create()
	                .withSubject(googleLogin.getUserEmail())
	                .withIssuedAt(issueDate)
	                .withExpiresAt(expiryDate)
	                .withClaim("role", googleLogin.getRole().name())
	                .sign(algorithm);

	        return token;
	    }

	public String getAccessToken(String authorizationCode) {
		String clientId = env.getProperty("oauth2.google.client-id");
		String clientSecret = env.getProperty("oauth2.google.client-secret");
		String redirectUri = env.getProperty("oauth2.google.redirect-uri");
		String tokenUri = env.getProperty("oauth2.google.token-uri");

		String actualCode;

		// JSON 형식의 문자열인지 확인
		if (authorizationCode.trim().startsWith("{")) {
			JsonNode rootNode;
			try {
				rootNode = new ObjectMapper().readTree(authorizationCode);
				actualCode = rootNode.get("code").asText();

				System.out.println(actualCode);

			} catch (Exception e) {
				log.error("Error parsing authorization code JSON", e);
				throw new RuntimeException("Invalid authorization code format");
			}
		} else {
			// JSON 형식이 아니면 authorizationCode를 직접 사용
			actualCode = authorizationCode;
		}

		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("code", actualCode);
		params.add("client_id", clientId);
		params.add("client_secret", clientSecret);
		params.add("redirect_uri", redirectUri);
		params.add("grant_type", "authorization_code");

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		HttpEntity entity = new HttpEntity(params, headers);

		System.out.println(authorizationCode);
		System.out.println(clientId);
		System.out.println(clientSecret);
		System.out.println(redirectUri);
		System.out.println("authorization_code");
		System.out.println(params);
		System.out.println(tokenUri);

		ResponseEntity<JsonNode> responseNode = restTemplate.exchange(tokenUri, HttpMethod.POST, entity,
				JsonNode.class);
		return responseNode.getBody().get("access_token").asText();
	}

	private JsonNode getUserResource(String accessToken) {
		String resourceUri = env.getProperty("oauth2.google.resource-uri");

		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer " + accessToken);
		HttpEntity entity = new HttpEntity(headers);
		return restTemplate.exchange(resourceUri, HttpMethod.GET, entity, JsonNode.class).getBody();
	}
}