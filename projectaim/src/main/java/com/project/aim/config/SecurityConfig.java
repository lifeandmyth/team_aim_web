package com.project.aim.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
//@EnableWebSecurity
public class SecurityConfig {

//	@Bean
//	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//		http.authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests
//				.requestMatchers(new AntPathRequestMatcher("/keywordHistory")).permitAll()
//				.requestMatchers(new AntPathRequestMatcher("/**")).permitAll())
//				.csrf((csrf) -> csrf.ignoringRequestMatchers(new AntPathRequestMatcher("/keywordHistory/**")))
//				.csrf((csrf) -> csrf.ignoringRequestMatchers(new AntPathRequestMatcher("/h2-console/**")))
//				.headers((headers) -> headers.addHeaderWriter(
//						new XFrameOptionsHeaderWriter(XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN)))
//				.formLogin((formLogin) -> formLogin.loginPage("/user/login").defaultSuccessUrl("/"))
//				.logout((logout) -> logout.logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))
//						.logoutSuccessUrl("/").invalidateHttpSession(true));
//		return http.build();
//	}

	/* 비밀번호 암호화 */
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		config.addAllowedOriginPattern("*");
//		config.addAllowedOrigin("*");
		config.addAllowedHeader("*");
		config.addAllowedMethod("*");
		source.registerCorsConfiguration("/**", config);
		return new CorsFilter(source);
	}

//	@Bean
//	AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
//			throws Exception {
//		return authenticationConfiguration.getAuthenticationManager();
//	}
}