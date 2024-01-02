package com.willbes.web.adminManagement.service;

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
public interface AdminManagementMenuService {
	
	
	/*
	 * =====================메뉴 관리===========================================
	 * */
	/**
	 * @Method Name  : getMenuTree
	 * @Date         : 2013. 10. 28. 
	 * @Author       : seojeong
	 * @변경이력      :
	 * @Method 설명      :	운영자 관리 -  메뉴관리  트리 리스트
	 * @return
	*/
	List<HashMap<String, Object>> getMenuTree();
	/**
	 * @Method Name  : getDetailMenu
	 * @Date         : 2013. 10. 28. 
	 * @Author       : seojeong
	 * @변경이력      :
	 * @Method 설명      :	운영자 관리 -  메뉴관리  상세 메뉴
	 * @param params
	 * @return
	*/
	HashMap<String, Object> getDetailMenu(HashMap<String, Object> params);
	/**
	 * @Method Name  : menuUpdateProcess
	 * @Date         : 2013. 10. 28. 
	 * @Author       : seojeong
	 * @변경이력      :
	 * @Method 설명      :	운영자 관리 -  메뉴관리  메뉴 수정 프로세스
	 * @param params
	*/
	void menuUpdateProcess(HashMap<String, Object> params);
	/**
	 * @Method Name  : menuDeleteProcess
	 * @Date         : 2013. 10. 28. 
	 * @Author       : seojeong
	 * @변경이력      :
	 * @Method 설명      :	운영자 관리 -  메뉴관리  메뉴 삭제 프로세스
	 * @param params
	 * @return
	*/
	int menuDeleteProcess(HashMap<String, Object> params);
	
	/**
	 * @Method Name  : menuIdCheck
	 * @Date         : 2013. 10. 28. 
	 * @Author       : seojeong
	 * @변경이력      :
	 * @Method 설명      :	운영자 관리 -  메뉴관리  메뉴 ID 중복체크 
	 * @param params
	 * @return
	*/
	int menuIdCheck(HashMap<String, Object> params);
	/**
	 * @Method Name  : menuInsertProcess
	 * @Date         : 2013. 10. 28. 
	 * @Author       : seojeong
	 * @변경이력      :
	 * @Method 설명      :	운영자 관리 -  메뉴관리  메뉴 등록 프로세스
	 * @param params
	 * @return
	*/
	int menuInsertProcess(HashMap<String, Object> params);
	HashMap<String, Object> getMaxMenuId(HashMap<String, Object> params);
	List<HashMap<String, Object>> getpassMenuTree();
	HashMap<String, Object> getpassDetailMenu(HashMap<String, Object> params);
	HashMap<String, Object> getpassMaxMenuId(HashMap<String, Object> params);
	int passmenuInsertProcess(HashMap<String, Object> params);
	void PassmenuUpdateProcess(HashMap<String, Object> params);
	int passmenuDeleteProcess(HashMap<String, Object> params);
	
	/*
	 * =====================메뉴 관리	end ======================================
	 * */
	
}
