package com.willbes.web.login.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.willbes.cmm.service.impl.CmmAbstractMapper;

@Repository
public class LoginDAO extends CmmAbstractMapper {

	public List<HashMap<String, String>> login(HashMap<String, String> params){
		return getSqlSession().selectList("login.login", params);
	}
	public List<HashMap<String, String>> login_ip(HashMap<String, String> params){
		return getSqlSession().selectList("login.login_ip", params);
	}

	/**
	 * @Method Name  : mbAccessInsert
	 * @Date         : 2014. 11.
	 * @Author       : rainend
	 * @변경이력      :
	 * @Method 설명      :	관리자 로그인 로그 저장
	 * @param params
	*/
	public void mbAccessInsert(HashMap<String, String> params) {
		getSqlSession().insert("login.mbAccessInsert", params);
	}

	/**
	 * @Method Name  : insertSMS
	 * @Date         : 2017. 06.
	 * @Author       : rainend
	 * @Method 설명      :	로그인 휴대폰 인증
	*/
	public void insertSMS(HashMap<String, String> params) {
		getSqlSession().insert("login.insertSMS", params);
	}


}
