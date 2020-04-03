package com.willbes.web.adminManagement.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.willbes.cmm.service.impl.CmmAbstractMapper;

@Repository
public class AdminManagementMenuDAO extends CmmAbstractMapper {

	/**
	 * @Method Name  : getMenuTree
	 * @Date         : 2013. 10. 28.
	 * @Author       : seojeong
	 * @변경이력      :
	 * @Method 설명      :	운영자 관리 - 메뉴관리 - 메뉴 트리 리스트
	 * @return
	*/
	public List<HashMap<String, Object>> getMenuTree() {
		return getSqlSession().selectList("adminManagementMenu.getMenuTree");
	}
	/**
	 * @Method Name  : getDetailMenu
	 * @Date         : 2013. 10. 28.
	 * @Author       : seojeong
	 * @변경이력      :
	 * @Method 설명      :	운영자 관리 - 메뉴관리 - 메뉴 상세
	 * @param params
	 * @return
	*/
	public HashMap<String, Object> getDetailMenu(HashMap<String, Object> params) {
		return getSqlSession().selectOne("adminManagementMenu.getDetailMenu", params);
	}
	/**
	 * @Method Name  : menuUpdateProcess
	 * @Date         : 2013. 10. 28.
	 * @Author       : seojeong
	 * @변경이력      :
	 * @Method 설명      :	운영자 관리 - 메뉴관리 - 메뉴 수정
	 * @param params
	*/
	public void menuUpdateProcess(HashMap<String, Object> params) {
		getSqlSession().update("adminManagementMenu.menuUpdateProcess", params);
	}
	/**
	 * @Method Name  : menuDeleteProcess
	 * @Date         : 2013. 10. 28.
	 * @Author       : seojeong
	 * @변경이력      :
	 * @Method 설명      :	운영자 관리 - 메뉴관리 - 메뉴 삭제
	 * @param params
	 * @return
	*/
	public int menuDeleteProcess(HashMap<String, Object> params) {
		return getSqlSession().delete("adminManagementMenu.menuDeleteProcess", params);
	}
	/**
	 * @Method Name  : menuIdCheck
	 * @Date         : 2013. 10. 28.
	 * @Author       : seojeong
	 * @변경이력      :
	 * @Method 설명      :	운영자 관리 - 메뉴관리 - 메뉴 ID 중복 체크
	 * @param params
	 * @return
	*/
	public int menuIdCheck(HashMap<String, Object> params) {
		return getSqlSession().selectOne("adminManagementMenu.menuIdCheck", params);
	}
	/**
	 * @Method Name  : menuInsertProcess
	 * @Date         : 2013. 10. 28.
	 * @Author       : seojeong
	 * @변경이력      :
	 * @Method 설명      :	운영자 관리 - 메뉴관리 - 메뉴 등록
	 * @param params
	 * @return
	*/
	public int menuInsertProcess(HashMap<String, Object> params) {
		return getSqlSession().insert("adminManagementMenu.menuInsertProcess", params);
	}
	/**
	 * @Method Name  : getMaxMenuId
	 * @Date         : 2013. 10. 28.
	 * @Author       : seojeong
	 * @변경이력      :
	 * @Method 설명      :	운영자 관리 - 메뉴관리 - 메뉴 등록시 메뉴 ID 맥스값 추출
	 * @param params
	 * @return
	*/
	public HashMap<String, Object> getMaxMenuId(HashMap<String, Object> params) {
		return getSqlSession().selectOne("adminManagementMenu.getMaxMenuId", params);
	}
	public List<HashMap<String, Object>> getpassMenuTree() {
		return getSqlSession().selectList("adminManagementMenu.getpassMenuTree");
	}
	public HashMap<String, Object> getpassDetailMenu(HashMap<String, Object> params) {
		return getSqlSession().selectOne("adminManagementMenu.getpassDetailMenu", params);
	}
	public HashMap<String, Object> getpassMaxMenuId(HashMap<String, Object> params) {
		return getSqlSession().selectOne("adminManagementMenu.getpassMaxMenuId", params);
	}
	public int passmenuInsertProcess(HashMap<String, Object> params) {
		return getSqlSession().insert("adminManagementMenu.passmenuInsertProcess", params);
	}
	public void PassmenuUpdateProcess(HashMap<String, Object> params) {
		getSqlSession().update("adminManagementMenu.PassmenuUpdateProcess", params);
	}
	public int passmenuDeleteProcess(HashMap<String, Object> params) {
		return getSqlSession().delete("adminManagementMenu.passmenuDeleteProcess", params);
	}
}
