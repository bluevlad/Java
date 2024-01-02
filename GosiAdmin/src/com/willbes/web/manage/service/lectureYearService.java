package com.willbes.web.manage.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface lectureYearService {

	List<HashMap<String, String>> onPayList(Map<String, String> params);
	List<HashMap<String, String>> onOrderList(Map<String, String> params);
	List<HashMap<String, String>> payUserListByLeccode(HashMap<String, String> params);
	int payUserListByLeccodeCount(HashMap<String, String> params);
	
	/* 프리패스 강의별 구매/수강내역 */
	List<HashMap<String, String>> onLecOrderView(HashMap<String, String> params);
}