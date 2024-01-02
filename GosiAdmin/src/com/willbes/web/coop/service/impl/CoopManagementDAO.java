package com.willbes.web.coop.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Repository;
import com.willbes.cmm.service.impl.CmmAbstractMapper;

@Repository
public class CoopManagementDAO extends CmmAbstractMapper {


	/**
	 * @Method Name  : getCoopList
	 * @Method 설명      :	제휴사 조회
	*/
	public List<HashMap<String, Object>> CoopList(HashMap<String, Object> params) {
		return getSqlSession().selectList("CoopManagement.CoopList", params);
	}

	/**
	 * @Method Name  : CoopInsertProcess
	 * @Method 설명      :	제휴사 등록
	*/
	public void CoopInsertProcess(HashMap<String, Object> params) {
		getSqlSession().insert("CoopManagement.CoopInsertProcess", params);
	}

	/**
	 * @Method Name  : CoopUpdateProcess
	 * @Method 설명      :	제휴사 정보 수정
	*/
	public void CoopUpdateProcess(HashMap<String, Object> params) {
		getSqlSession().update("CoopManagement.CoopUpdateProcess", params);
	}

	/**
	 * @Method Name  : CoopIpList
	 * @Method 설명      :	제휴사 등록 아이피 조회
	*/
	public List<HashMap<String, Object>> CoopIpList(HashMap<String, Object> params) {
		return getSqlSession().selectList("CoopManagement.CoopIpList", params);
	}

	/**
	 * @Method Name  : CoopIpInsertProcess
	 * @Method 설명      :	제휴사 등록 아이피 등록 
	*/
	public void CoopIpInsertProcess(HashMap<String, Object> params) {
		getSqlSession().insert("CoopManagement.CoopIpInsertProcess", params);
	}

	/**
	 * @Method Name  : CoopIpDeleteProcess
	 * @Method 설명      :	제휴사 등록 아이피 수정
	*/
	public void CoopIpDeleteProcess(HashMap<String, Object> params) {
		getSqlSession().update("CoopManagement.CoopIpDeleteProcess", params);
	}

}