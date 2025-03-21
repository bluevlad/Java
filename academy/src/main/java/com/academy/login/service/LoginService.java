package com.academy.login.service;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import com.academy.mapper.LoginMapper;

@Service
public class LoginService {

	private LoginMapper loginMapper;
	
	public LoginService(LoginMapper loginMapper) {
		this.loginMapper = loginMapper;
	}
	
	public JSONObject getUser(MemberVO memberVO) {
		return loginMapper.getUser(memberVO);
	}
	
	public void insertUser(MemberVO memberVO) {
		loginMapper.insertUser(memberVO);
    }
	public void updateUser(MemberVO memberVO) {
		loginMapper.updateUser(memberVO);
    }

	public JSONObject searchId(MemberVO memberVO) {
		return loginMapper.searchId(memberVO);
	}
	public JSONObject searchPassword(MemberVO memberVO) {
        return loginMapper.searchPassword(memberVO);
    }
	
	public void updatePassword(MemberVO memberVO) {
		loginMapper.updatePassword(memberVO);
    }
	
	public void updateToken(MemberVO memberVO) {
		loginMapper.updateToken(memberVO);
    }

}
