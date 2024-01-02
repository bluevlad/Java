package com.willbes.web.memberAdminManagement.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @FileName   : AdminManagementService.java
 * @Project    : dev_willbesWebAdmin
 * @Date       : 2013. 10. 28. 
 * @Author     : seojeong
 * @변경이력    :
 * @프로그램 설명 : 운영자 관리 service
 */
public interface MemberAdminManagementService {
	
	/**
	 * @Method Name  : getMemberList
	 * @Date         : 2013. 10. 28. 
	 * @Author       : seojeong
	 * @변경이력      :
	 * @Method 설명      : 	운영자 관리 -  운영자조회  리스트
	 * @param searchMap
	 * @return
	*/
	List<HashMap<String, Object>> getMemberList(	Map<String, Object> searchMap);
	/**
	 * @Method Name  : getMemberListCount
	 * @Date         : 2013. 11. 1. 
	 * @Author       : seojeong
	 * @변경이력      :
	 * @Method 설명      :	운영자 관리 -  운영자조회  리스트 총 갯수
	 * @param searchMap
	 * @return
	*/
	int getMemberListCount(Map<String, Object> searchMap);
	
	
	/**
	 * @Method Name  : getSiteList
	 * @Date         : 2013. 11. 1. 
	 * @Author       : seojeong
	 * @변경이력      :
	 * @Method 설명      :권한 리스트
	 * @return
	*/
	List<HashMap<String, String>> getSiteList();
	
	/**
	 * @Method Name  : memberIdCheck
	 * @Date         : 2013. 11. 1. 
	 * @Author       : seojeong
	 * @변경이력      :
	 * @Method 설명      : ID 중복체크
	 * @param params
	 * @return
	*/
	int memberIdCheck(HashMap<String, String> params);
	
	/**
	 * @Method Name  : memberInsertProcess
	 * @Date         : 2013. 11. 1. 
	 * @Author       : seojeong
	 * @변경이력      :
	 * @Method 설명      :	운영자 관리 -  관리자 조회  - 등록 프로세스
	 * @param params
	*/
	void memberInsertProcess(HashMap<String, String> params);
	/**
	 * @Method Name  : memberDetail
	 * @Date         : 2013. 10. 28. 
	 * @Author       : seojeong
	 * @변경이력      :
	 * @Method 설명      :	운영자 관리 -  운영자 조회 - 상세 페이지 
	 * @param params
	 * @return
	*/
	HashMap<String, Object> memberDetail(HashMap<String, Object> params);
	/**
	 * @Method Name  : memberUpdateProcess
	 * @Date         : 2013. 10. 28. 
	 * @Author       : seojeong
	 * @변경이력      :
	 * @Method 설명      :	운영자 관리 -  운영자 조회  - 수정 프로세스
	 * @param params
	*/
	void memberUpdateProcess(HashMap<String, String> params);
	/**
	 * @Method Name  : memberDelete
	 * @Date         : 2013. 10. 28. 
	 * @Author       : seojeong
	 * @변경이력      :
	 * @Method 설명      :	운영자 관리 -  코드관리  개별 삭제
	 * @param params
	*/
	void memberDelete(HashMap<String, String> params);
	
	/**
	 * @Method Name  : memberAdminSendMessage
	 * @Date         : 2013. 11. 4. 
	 * @Author       : seojeong
	 * @변경이력      :
	 * @Method 설명      :	운영자 관리 - 쪽지 전송
	 * @param params
	*/
	void memberAdminSendMessage(HashMap<String, String> params);
	
	/**
	 * @Method Name  : getMemberAdminEmail
	 * @Date         : 2013. 11. 4. 
	 * @Author       : seojeong
	 * @변경이력      :
	 * @Method 설명      : 운영자 관리- 메일 전송
	 * @param params
	 * @return
	*/
	HashMap<String, String> getMemberAdminEmail(HashMap<String, String> params);
	
	/**
	 * @Method Name  : MemberAdminInsertEmail
	 * @Date         : 2013. 11. 4. 
	 * @Author       : seojeong
	 * @변경이력      :
	 * @Method 설명      : 메일 전송 내역 저장
	 * @param params
	*/
	
	void MemberAdminInsertEmail(HashMap<String, String> params);

	/**
	 * @Method Name : getAdminLoginList
	 * @Date : 2016. 10. 04.
	 * @Method 설명 : 운영자 관리 - 운영자 로그인 정보 조회
	*/
	List<HashMap<String, Object>> getAdminLoginList(Map<String, Object> searchMap);

	int getAdminLoginListCount(Map<String, Object> searchMap);
	
}
