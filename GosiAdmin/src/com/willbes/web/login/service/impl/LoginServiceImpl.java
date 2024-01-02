package com.willbes.web.login.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.willbes.web.login.service.impl.LoginDAO;
import com.willbes.web.login.service.LoginService;

@Service(value="loginService")
public class LoginServiceImpl implements LoginService {
	@Autowired 
	private LoginDAO logindao;
	
	@Override
	public List<HashMap<String, String>> login(HashMap<String, String> params) {
		return logindao.login(params);
	}
	public List<HashMap<String, String>> login_ip(HashMap<String, String> params) {
		return logindao.login_ip(params);
	}
	
	public void mbAccessInsert(HashMap<String, String> params){
		logindao.mbAccessInsert(params);
	}
	
	public void insertSMS(HashMap<String, String> params){
		logindao.insertSMS(params);
	}

}
