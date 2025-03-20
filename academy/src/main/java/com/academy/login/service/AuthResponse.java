//JWT 응답 DTO
package com.academy.login.service;

import org.springframework.stereotype.Service;

@Service
public class AuthResponse {
	
	private String token;

    // 기본 생성자 추가
    public AuthResponse() {}

	public AuthResponse(String token) {
		this.token = token; 
	}

	public String getToken() {
		return token; 
	}
}