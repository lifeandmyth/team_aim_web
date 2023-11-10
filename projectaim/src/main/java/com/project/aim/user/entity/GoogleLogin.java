package com.project.aim.user.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

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

@Getter
@Setter
@Entity
@Table(name = "google_login2")
public class GoogleLogin  implements UserDetails{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "google_login_idx")
	private int googleLoginIdx;

	@Column(name = "user_email")
	private String userEmail;

	@Column(name = "user_pw")
	private String userPw;

	@Column(name = "created_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;

	@Column(name = "update_at")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateAt;

	@Column(name = "user_category")
	private int userCategory;

	@Column(name = "user_phone")
	private String userPhone;

	@Column(name = "user_picture")
	private String userPicture;


	@Enumerated(EnumType.STRING)
	@Column(name = "role", length = 255)
	private Role role = Role.ADVERTISOR;

	@Column(name = "provider")
	private String provider;
	
	

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// 사용자의 권한을 GrantedAuthority 객체의 리스트로 반환합니다.
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        // 여기서 'ROLE_USER'는 예시이며, 실제 사용자의 역할에 맞게 수정해야 합니다.

        return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}
}
