package com.willbes.web.tm.service;

import java.util.HashMap;
import java.util.List;

public interface TMService {

	List<HashMap<String, String>> tmAdminList(HashMap<String, String> params);

	String getTmMemberCount(HashMap<String, String> params);

	void insertTBTMUsersProcess(HashMap<String, String> params);

	List<HashMap<String, String>> tmMemberList(HashMap<String, String> params);

	String tmMemberListCount(HashMap<String, String> params);

	List<HashMap<String, String>> tmMemberListExcel(HashMap<String, String> params);

	List<HashMap<String, String>> getTBCCCartInfo(HashMap<String, String> params1);

	List<HashMap<String, String>> tmBoardList(HashMap<String, String> params);

	String tmBoardListCount(HashMap<String, String> params);

	List<HashMap<String, String>> getVOCCODEList(HashMap<String, String> params);

	List<HashMap<String, String>> getDUTYCODEList(HashMap<String, String> params);

	List<HashMap<String, String>> tmOrderList(HashMap<String, String> params);

	HashMap<String, String> tmOrderListCount(HashMap<String, String> params);

	List<HashMap<String, String>> tmRefundList(HashMap<String, String> params);

	String tmRefundListCount(HashMap<String, String> params);

	List<HashMap<String, String>> tmIsTmAdmin(HashMap<String, String> params);

	HashMap<String, String> tmCancelPayment(HashMap<String, String> params);

	void tmBoardDelete(HashMap<String, String> subparams);

	void tmBoardUpdate(HashMap<String, String> subparams);

	List<HashMap<String, String>> tmBoardListExcel(HashMap<String, String> params);

	List<HashMap<String, String>> tmOrderListExcel(HashMap<String, String> params);

	List<HashMap<String, String>> tmRefundListExcel(HashMap<String, String> params);

}
