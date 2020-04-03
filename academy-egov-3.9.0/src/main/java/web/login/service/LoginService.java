package web.login.service;

import java.util.HashMap;
import java.util.List;

public interface LoginService {

	List<HashMap<String, String>> login(HashMap<String, String> params);
	List<HashMap<String, String>> login_ip(HashMap<String, String> params);

	/**
	 * @Method Name  : mbAccessInsert
	 * @Date         : 2014. 11.
	 * @Author       : rainend
	 * @변경이력      :
	 * @Method 설명      :	관리자 로그인 로그 저장
	 * @param params
	*/
	void mbAccessInsert(HashMap<String, String> params);

	void insertSMS(HashMap<String, String> params);
}
