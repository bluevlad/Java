package com.willbes.web.tm.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.willbes.web.tm.service.TMService;

@Service(value="tmservice")
public class TMServiceImpl implements TMService {
	
	@Autowired
	private TMDAO tmdao;

	@Override
	public List<HashMap<String, String>> tmAdminList(HashMap<String, String> params) {
		return tmdao.tmAdminList(params);
	}

	@Override
	public String getTmMemberCount(HashMap<String, String> params) {
		String tAllocCnt = "";
		if ("A".equals(params.get("USERTYPE"))) {
			tAllocCnt = tmdao.getTmMemberACount(params);
		} else if ("B".equals(params.get("USERTYPE"))) {
			tAllocCnt = tmdao.getTmMemberBCount(params);
		} else if ("C".equals(params.get("USERTYPE"))) {
			tAllocCnt = tmdao.getTmMemberCCount(params);
		} else if ("D".equals(params.get("USERTYPE"))) {
			tAllocCnt = tmdao.getTmMemberDCount(params);
		}
		return tAllocCnt;
	}
	
	@Override
	public void insertTBTMUsersProcess(HashMap<String, String> params) {
		if ("A".equals(params.get("USERTYPE"))) {
			tmdao.insertTBTMUsersProcessA(params);
		} else if ("B".equals(params.get("USERTYPE"))) {
			tmdao.insertTBTMUsersProcessB(params);
		} else if ("C".equals(params.get("USERTYPE"))) {
			tmdao.insertTBTMUsersProcessC(params);
		} else if ("D".equals(params.get("USERTYPE"))) {
			tmdao.insertTBTMUsersProcessD(params);
		}
	}

	@Override
	public List<HashMap<String, String>> tmMemberList(HashMap<String, String> params) {
		return tmdao.tmMemberList(params);
	}

	@Override
	public String tmMemberListCount(HashMap<String, String> params) {
		return tmdao.tmMemberListCount(params);
	}

	@Override
	public List<HashMap<String, String>> tmMemberListExcel(HashMap<String, String> params) {
		return tmdao.tmMemberListExcel(params);
	}

	@Override
	public List<HashMap<String, String>> getTBCCCartInfo(HashMap<String, String> params) {
		return tmdao.getTBCCCartInfo(params);
	}

	@Override
	public List<HashMap<String, String>> tmBoardList(HashMap<String, String> params) {
		return tmdao.tmBoardList(params);
	}

	@Override
	public String tmBoardListCount(HashMap<String, String> params) {
		return tmdao.tmBoardListCount(params);
	}

	@Override
	public List<HashMap<String, String>> getVOCCODEList(HashMap<String, String> params) {
		return tmdao.getVOCCODEList(params);
	}

	@Override
	public List<HashMap<String, String>> getDUTYCODEList(HashMap<String, String> params) {
		return tmdao.getDUTYCODEList(params);
	}

	@Override
	public List<HashMap<String, String>> tmOrderList(HashMap<String, String> params) {
		return tmdao.tmOrderList(params);
	}

	@Override
	public HashMap<String, String> tmOrderListCount(HashMap<String, String> params) {
		return tmdao.tmOrderListCount(params);
	}

	@Override
	public List<HashMap<String, String>> tmRefundList(HashMap<String, String> params) {
		return tmdao.tmRefundList(params);
	}

	@Override
	public String tmRefundListCount(HashMap<String, String> params) {
		return tmdao.tmRefundListCount(params);
	}

	@Override
	public List<HashMap<String, String>> tmIsTmAdmin(HashMap<String, String> params) {
		return tmdao.tmIsTmAdmin(params);
	}

	@Override
	public HashMap<String, String> tmCancelPayment(HashMap<String, String> params) {
		return tmdao.tmCancelPayment(params);
	}

	@Override
	public void tmBoardDelete(HashMap<String, String> params) {
		tmdao.tmBoardDelete(params);
	}

	@Override
	public void tmBoardUpdate(HashMap<String, String> params) {
		tmdao.tmBoardUpdate(params);
	}

	@Override
	public List<HashMap<String, String>> tmBoardListExcel(HashMap<String, String> params) {
		return tmdao.tmBoardListExcel(params);
	}

	@Override
	public List<HashMap<String, String>> tmOrderListExcel(HashMap<String, String> params) {
		return tmdao.tmOrderListExcel(params);
	}

	@Override
	public List<HashMap<String, String>> tmRefundListExcel(HashMap<String, String> params) {
		return tmdao.tmRefundListExcel(params);
	}
	
}
