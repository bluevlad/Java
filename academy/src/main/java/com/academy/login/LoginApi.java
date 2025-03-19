package com.academy.login;

import java.util.HashMap;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.academy.common.CORSFilter;
import com.academy.login.service.LoginService;
import com.academy.login.service.MemberVO;

import jakarta.servlet.http.HttpServletRequest;

@RestController
public class LoginApi extends CORSFilter {

	private LoginService loginService;

    public LoginApi(LoginService loginService) {
        this.loginService = loginService;
    }

	/** log */
	private static final Logger LOGGER = LoggerFactory.getLogger(LoginApi.class);

	/**
	 * 일반(세션) 로그인을 처리한다
	 * @param vo - 아이디, 비밀번호가 담긴 LoginVO
	 * @param request - 세션처리를 위한 HttpServletRequest
	 * @return result - 로그인결과(세션정보)
	 * @exception Exception
	 */
	@PostMapping(value = "/api/login/Login")
	public JSONObject actionLogin(@ModelAttribute("MemberVO") MemberVO memberVO, HttpServletRequest request) throws Exception {

		HashMap<String,Object> jsonObject = new HashMap<String,Object>();

		// 2. 로그인 처리
		jsonObject.put("loginVO", loginService.actionLogin(memberVO));
		
		// 3-1. 로그인 정보를 세션에 저장
		request.getSession().setAttribute("loginVO", loginService.actionLogin(memberVO));

		JSONObject jObject = new JSONObject(jsonObject);
		
		return jObject;
	}

	/**
	 * 로그아웃한다.
	 * @return String
	 * @exception Exception
	 */
	@PostMapping(value = "/api/login/LogOut")
	public JSONObject actionLogout(@ModelAttribute("MemberVO") MemberVO memberVO, HttpServletRequest request) throws Exception {

		HashMap<String,Object> jsonObject = new HashMap<String,Object>();

		request.getSession().setAttribute("loginVO", null);

		JSONObject jObject = new JSONObject(jsonObject);

		return jObject;
	}

}
