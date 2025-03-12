package com.academy.main;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.academy.common.CORSFilter;
import com.academy.locker.service.LockerService;
import com.academy.locker.service.LockerVO;

@RestController
public class MainApi extends CORSFilter {

    private LockerService lockerService;

    public MainApi(LockerService lockerService) {
        this.lockerService = lockerService;
    }
    
	// 0. Spring Security 사용자권한 처리 - 추후 해당 부분 모두 추가
	/*
	 * Boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();
	 * if(!isAuthenticated) { model.addAttribute("message",
	 * egovMessageSource.getMessage("fail.common.login")); return
	 * "egovframework/com/uat/uia/EgovLoginUsr"; }
	 * 
	 * //로그인 객체 선언 LoginVO loginVO =
	 * (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
	 * 
	 * //아이디 설정 lockerVO.setRegId(loginVO == null ? "" :
	 * EgovStringUtil.isNullToString(loginVO.getUniqId())); lockerVO.setUpdId(loginVO
	 * == null ? "" : EgovStringUtil.isNullToString(loginVO.getUniqId()));
	 */
    
	/**
	 * 사물함 목록화면 이동
	 * @return String
	 * @exception Exception
	 */
	@GetMapping(value = "/api/getSales")
	public JSONObject list(@ModelAttribute("LockerVO") LockerVO lockerVO, @RequestParam Map<?, ?> commandMap) throws Exception, IOException, ParseException { 
				
		JSONParser parser = new JSONParser();
		Reader reader = new FileReader("C:\\jee-2024-12\\upload\\dashSalesData.json");
		
		JSONObject jsonObject = (JSONObject) parser.parse(reader);         

		return jsonObject;
	}
}