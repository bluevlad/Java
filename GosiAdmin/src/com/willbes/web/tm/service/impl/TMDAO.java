package com.willbes.web.tm.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.willbes.cmm.service.impl.CmmAbstractMapper;

@Repository
public class TMDAO extends CmmAbstractMapper {

	// TM관리자 목록 조회  2013.12.30
	public List<HashMap<String, String>> tmAdminList(HashMap<String, String> params) {
		return getSqlSession().selectList("tm.tmAdminList", params);
	}

	public String getTmMemberACount(HashMap<String, String> params) {
		return getSqlSession().selectOne("tm.getTmMemberACount", params);
	}

	public String getTmMemberBCount(HashMap<String, String> params) {
		return getSqlSession().selectOne("tm.getTmMemberBCount", params);
	}

	public String getTmMemberCCount(HashMap<String, String> params) {
		return getSqlSession().selectOne("tm.getTmMemberCCount", params);
	}

	public String getTmMemberDCount(HashMap<String, String> params) {
		return getSqlSession().selectOne("tm.getTmMemberDCount", params);
	}

	public void insertTBTMUsersProcessA(HashMap<String, String> params) {
		getSqlSession().insert("tm.insertTBTMUsersProcessA", params);
	}

	public void insertTBTMUsersProcessB(HashMap<String, String> params) {
		getSqlSession().insert("tm.insertTBTMUsersProcessB", params);
	}

	public void insertTBTMUsersProcessC(HashMap<String, String> params) {
		getSqlSession().insert("tm.insertTBTMUsersProcessC", params);
	}

	public void insertTBTMUsersProcessD(HashMap<String, String> params) {
		getSqlSession().insert("tm.insertTBTMUsersProcessD", params);
	}

	public List<HashMap<String, String>> tmMemberList(HashMap<String, String> params) {
		return getSqlSession().selectList("tm.tmMemberList", params);
	}

	public String tmMemberListCount(HashMap<String, String> params) {
		return getSqlSession().selectOne("tm.tmMemberListCount", params);
	}

	public List<HashMap<String, String>> tmMemberListExcel(HashMap<String, String> params) {
		return getSqlSession().selectList("tm.tmMemberListExcel", params);
	}

	public List<HashMap<String, String>> getTBCCCartInfo(HashMap<String, String> params) {
		return getSqlSession().selectList("tm.getTBCCCartInfo", params);
	}

	public List<HashMap<String, String>> tmBoardList(HashMap<String, String> params) {
		return getSqlSession().selectList("tm.tmBoardList", params);
	}

	public String tmBoardListCount(HashMap<String, String> params) {
		return getSqlSession().selectOne("tm.tmBoardListCount", params);
	}

	public List<HashMap<String, String>> getVOCCODEList(HashMap<String, String> params) {
		return getSqlSession().selectList("tm.getVOCCODEList", params);
	}

	public List<HashMap<String, String>> getDUTYCODEList(HashMap<String, String> params) {
		return getSqlSession().selectList("tm.getDUTYCODEList", params);
	}

	public List<HashMap<String, String>> tmOrderList(HashMap<String, String> params) {
		return getSqlSession().selectList("tm.tmOrderList", params);
	}

	public HashMap<String, String> tmOrderListCount(HashMap<String, String> params) {
		return getSqlSession().selectOne("tm.tmOrderListCount", params);
	}

	public List<HashMap<String, String>> tmRefundList(HashMap<String, String> params) {
		return getSqlSession().selectList("tm.tmRefundList", params);
	}

	public String tmRefundListCount(HashMap<String, String> params) {
		return getSqlSession().selectOne("tm.tmRefundListCount", params);
	}

	public List<HashMap<String, String>> tmIsTmAdmin(HashMap<String, String> params) {
		return getSqlSession().selectList("tm.tmIsTmAdmin", params);
	}

	public HashMap<String, String> tmCancelPayment(HashMap<String, String> params) {
		return getSqlSession().selectOne("tm.tmCancelPayment", params);
	}

	public void tmBoardDelete(HashMap<String, String> params) {
		getSqlSession().delete("tm.tmBoardDelete", params);
	}

	public void tmBoardUpdate(HashMap<String, String> params) {
		getSqlSession().update("tm.tmBoardUpdate", params);
	}

	public List<HashMap<String, String>> tmBoardListExcel(HashMap<String, String> params) {
		return getSqlSession().selectList("tm.tmBoardListExcel", params);
	}

	public List<HashMap<String, String>> tmOrderListExcel(HashMap<String, String> params) {
		return getSqlSession().selectList("tm.tmOrderListExcel", params);
	}

	public List<HashMap<String, String>> tmRefundListExcel(HashMap<String, String> params) {
		return getSqlSession().selectList("tm.tmRefundListExcel", params);
	}

}
