package com.willbes.web.adminManagement.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @FileName   : AdminManagementService.java
 * @Project    : dev_willbesWebAdmin
 * @Date       : 2016. 08. 07. 
 * @Author     : seojeong
 * @변경이력    :
 * @프로그램 설명 : 운영자 관리 service
 */
public interface AdminManagementCodeService { 
	
	/**
	 * @Method Name  : getCommonCodeList
	 * @Date         : 2013. 10. 28. 
	 * @Author       : seojeong
	 * @변경이력      :
	 * @Method 설명      : 	운영자 관리 -  코드관리  리스트
	 * @param searchMap
	 * @return
	*/
	List<HashMap<String, Object>> getCommonCodeList(	Map<String, Object> searchMap);
	/**
	 * @Method Name  : getCommonCodeListCount
	 * @Date         : 2013. 10. 28. 
	 * @Author       : seojeong
	 * @변경이력      :
	 * @Method 설명      :	운영자 관리 -  코드관리  리스트 총 갯수
	 * @param searchMap
	 * @return
	*/
	int getCommonCodeListCount(Map<String, Object> searchMap);
	
	/**
	 * @Method Name  : commonCodeInsertProcess
	 * @Date         : 2013. 10. 28. 
	 * @Author       : seojeong
	 * @변경이력      :
	 * @Method 설명      :	운영자 관리 -  코드관리  - 등록 프로세스
	 * @param params
	*/
	void commonCodeInsertProcess(HashMap<String, Object> params);
	/**
	 * @Method Name  : commonCodeDetail
	 * @Date         : 2013. 10. 28. 
	 * @Author       : seojeong
	 * @변경이력      :
	 * @Method 설명      :	운영자 관리 -  코드관리 - 상세 페이지 
	 * @param params
	 * @return
	*/
	HashMap<String, Object> commonCodeDetail(HashMap<String, Object> params);
	/**
	 * @Method Name  : commonCodeUpdateProcess
	 * @Date         : 2013. 10. 28. 
	 * @Author       : seojeong
	 * @변경이력      :
	 * @Method 설명      :	운영자 관리 -  코드관리  - 수정 프로세스
	 * @param params
	*/
	void commonCodeUpdateProcess(HashMap<String, Object> params);
	/**
	 * @Method Name  : commonCodeDelete
	 * @Date         : 2013. 10. 28. 
	 * @Author       : seojeong
	 * @변경이력      :
	 * @Method 설명      :	운영자 관리 -  코드관리  개별 삭제
	 * @param params
	*/
	void commonCodeDelete(HashMap<String, Object> params);
	/**
	 * @Method Name  : commonCodeCheckDelete
	 * @Date         : 2013. 10. 28. 
	 * @Author       : seojeong
	 * @변경이력      :
	 * @Method 설명      :	운영자 관리 -  코드관리  체크박스 일괄 삭제
	 * @param params
	*/
	void commonCodeCheckDelete(HashMap<String, Object> params);
	
	List<HashMap<String, Object>> getpassCodeTree();
	
	HashMap<String, Object> getpassDetailCode(HashMap<String, Object> params);
	
	int passcodeDeleteProcess(HashMap<String, Object> params);
	
	void PasscodeUpdateProcess(HashMap<String, Object> params);
	
	HashMap<String, Object> getpassMaxCodeId(HashMap<String, Object> params);
	
	int passcodeInsertProcess(HashMap<String, Object> params);
	
	List<HashMap<String, Object>> getBaConfigCodeList(	Map<String, Object> searchMap);
	
}
