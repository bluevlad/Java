package com.willbes.web.coop.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @FileName   : CoopManagementService.java
 * @Date       : 2017. 12
 * @Author     : rainend
 * @변경이력    :
 * @프로그램 설명 : 제휴사  service
 */
public interface CoopManagementService {
	
	/** @Method 설명 : 제휴사 리스트 **/
	List<HashMap<String, Object>> CoopList(HashMap<String, Object> params);

	/** @Method 설명 : 제휴사 등록 **/
	void CoopInsertProcess(HashMap<String, Object> params);

	/** @Method 설명 : 제휴사 수정 **/
	void CoopUpdateProcess(HashMap<String, Object> params);
	
	/** @Method 설명 : 제휴사 아이피 리스트 **/
	List<HashMap<String, Object>> CoopIpList(HashMap<String, Object> params);

	/** @Method 설명 : 제휴사 아이피 등록 **/
	void CoopIpInsertProcess(HashMap<String, Object> params);

	/** @Method 설명 : 제휴사 아이피 삭제 **/
	void CoopIpDeleteProcess(HashMap<String, Object> params);

}