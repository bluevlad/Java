package com.academy.login;

import java.util.HashMap;
import java.util.Optional;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.academy.common.CORSFilter;
import com.academy.common.JwtUtil;
import com.academy.login.service.AuthResponse;
import com.academy.login.service.LoginService;
import com.academy.login.service.MemberVO;

@RestController
@RequestMapping("/api/auth")
public class LoginApi extends CORSFilter {

	private LoginService loginService;
    private JwtUtil jwtUtil;
    public LoginApi(LoginService loginService, JwtUtil jwtUtil) {
        this.loginService = loginService;
        this.jwtUtil = jwtUtil;
    }

	/** log */
	private static final Logger LOGGER = LoggerFactory.getLogger(LoginApi.class);

	/**
	 * 일반(세션) 로그인을 처리한다
	 * @param vo - 아이디, 비밀번호가 담긴 LoginVO
	 * @return ResponseEntity - 로그인결과(세션정보)
	 * @exception Exception
	 */
	@PostMapping(value = "/login")
	public JSONObject actionLogin(@ModelAttribute("MemberVO") MemberVO memberVO) throws Exception {
		
		HashMap<String,Object> jsonObject = new HashMap<String,Object>();

		try {
			// 2. 로그인 처리
            // userId 값 검증 (null 방지)
            String userId = Optional.ofNullable(memberVO.getUserId())
                                    .map(Object::toString)
                                    .orElseThrow(() -> new IllegalArgumentException("User ID not found"));

            jsonObject.put("userInfo", loginService.actionLogin(memberVO));
            
            // JWT 토큰 생성
            String token = jwtUtil.generateToken(userId);
    		jsonObject.put("token", token);
            
		} catch (Exception e){
			e.printStackTrace();
			jsonObject.put("retMsg", "FAIL");
		}

		JSONObject jObject = new JSONObject(jsonObject);
        return jObject;
	}

	/**
	 * 일반(세션) 로그인을 처리한다
	 * @param vo - 아이디, 비밀번호가 담긴 LoginVO
	 * @return ResponseEntity - 로그인결과(세션정보)
	 * @exception Exception
	 */
	@PostMapping(value = "/userInfo")
	public ResponseEntity<?> getUser(@RequestHeader("Authorization") String authHeader) {
	    try {
	        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
	            return ResponseEntity.status(401).body("Invalid token format");
	        }

	        // "Bearer " 부분 제거 후 토큰 추출
	        String token = authHeader.substring(7);
	        String userId = jwtUtil.extractUsername(token);

	        return ResponseEntity.ok(userId);
	    } catch (Exception e) {
	        e.printStackTrace();
	        return ResponseEntity.status(401).body("Invalid userInfo");
	    }
	}

	/**
	 * 로그아웃한다.
	 * @return ResponseEntity
	 * @exception Exception
	 */
	@PostMapping(value = "/logOut")
	public ResponseEntity<?> actionLogout(@ModelAttribute("MemberVO") MemberVO memberVO) throws Exception {

        // JSON 응답 반환
        HashMap<String, Object> responseMap = new HashMap<>();
        responseMap.put("message", "Logout successful");

        return ResponseEntity.ok(responseMap);
	}

}
