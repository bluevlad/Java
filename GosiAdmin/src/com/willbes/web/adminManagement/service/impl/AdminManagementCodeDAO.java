package com.willbes.web.adminManagement.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.willbes.cmm.service.impl.CmmAbstractMapper;

@Repository
public class AdminManagementCodeDAO extends CmmAbstractMapper { 


	/**
	 * @Method Name  : getCommonCodeList
	 * @Date         : 2016. 08. 07.
	 * @Author       : seojeong
	 * @변경이력      :
	 * @Method 설명      :	운영자 관리 - 코드관리 - 코드 리스트
	 * @param searchMap
	 * @return
	*/
	public List<HashMap<String, Object>> getCommonCodeList(Map<String, Object> searchMap) {
		return getSqlSession().selectList("adminManagementCode.getCommonCodeList", searchMap);
	}
	/**
	 * @Method Name  : getCommonCodeListCount
	 * @Date         : 2013. 10. 28.
	 * @Author       : seojeong
	 * @변경이력      :
	 * @Method 설명      :	운영자 관리 - 코드관리 - 코드 리스트 총 갯수
	 * @param searchMap
	 * @return
	*/
	public int getCommonCodeListCount(Map<String, Object> searchMap) {
		return getSqlSession().selectOne("adminManagementCode.getCommonCodeListCount", searchMap);
	}
	/**
	 * @Method Name  : commonCodeInsertProcess
	 * @Date         : 2013. 10. 28.
	 * @Author       : seojeong
	 * @변경이력      :
	 * @Method 설명      :	운영자 관리 - 코드관리 - 코드 등록
	 * @param params
	*/
	public void commonCodeInsertProcess(HashMap<String, Object> params) {
		getSqlSession().insert("adminManagementCode.commonCodeInsertProcess", params);
	}
	/**
	 * @Method Name  : commonCodeDetail
	 * @Date         : 2013. 10. 28.
	 * @Author       : seojeong
	 * @변경이력      :
	 * @Method 설명      :	운영자 관리 - 코드관리 - 코드 상세
	 * @param params
	 * @return
	*/
	public HashMap<String, Object> commonCodeDetail(HashMap<String, Object> params) {
		return getSqlSession().selectOne("adminManagementCode.commonCodeDetail", params);
	}
	/**
	 * @Method Name  : commonCodeUpdateProcess
	 * @Date         : 2013. 10. 28.
	 * @Author       : seojeong
	 * @변경이력      :
	 * @Method 설명      :	운영자 관리 - 코드관리 - 코드 수정
	 * @param params
	*/
	public void commonCodeUpdateProcess(HashMap<String, Object> params) {
		getSqlSession().update("adminManagementCode.commonCodeUpdateProcess", params);
	}
	/**
	 * @Method Name  : commonCodeDelete
	 * @Date         : 2013. 10. 28.
	 * @Author       : seojeong
	 * @변경이력      :
	 * @Method 설명      :	운영자 관리 - 코드관리 - 코드 개별 삭제
	 * @param params
	*/
	public void commonCodeDelete(HashMap<String, Object> params) {
		getSqlSession().delete("adminManagementCode.commonCodeDelete", params);
	}
	/**
	 * @Method Name  : commonCodeCheckDelete
	 * @Date         : 2013. 10. 28.
	 * @Author       : seojeong
	 * @변경이력      :
	 * @Method 설명      :	운영자 관리 - 코드관리 - 코드 일괄 삭제
	 * @param params
	*/
	public void commonCodeCheckDelete(HashMap<String, Object> params) {
		getSqlSession().delete("adminManagementCode.commonCodeCheckDelete", params);
	}
	
	public List<HashMap<String, Object>> getpassCodeTree() {
		return getSqlSession().selectList("adminManagementCode.getpassCodeTree");
	}
	
	public HashMap<String, Object> getpassDetailCode(HashMap<String, Object> params) {
		return getSqlSession().selectOne("adminManagementCode.getpassDetailCode", params);
	}
	
	public int passcodeDeleteProcess(HashMap<String, Object> params) {
		return getSqlSession().delete("adminManagementCode.passcodeDeleteProcess", params);
	}
	
	public void PasscodeUpdateProcess(HashMap<String, Object> params) {
		getSqlSession().update("adminManagementCode.PasscodeUpdateProcess", params);
	}
	
	public HashMap<String, Object> getpassMaxCodeId(HashMap<String, Object> params) {
		return getSqlSession().selectOne("adminManagementCode.getpassMaxCodeId", params);
	}
	
	public int passcodeInsertProcess(HashMap<String, Object> params) {
		return getSqlSession().insert("adminManagementCode.passcodeInsertProcess", params);
	}

	public List<HashMap<String, Object>> getBaConfigCodeList(Map<String, Object> searchMap) {
		return getSqlSession().selectList("adminManagementCode.getBaConfigCodeList", searchMap);
	}
}
