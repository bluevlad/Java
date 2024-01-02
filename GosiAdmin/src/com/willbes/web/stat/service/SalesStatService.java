package com.willbes.web.stat.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface SalesStatService {

	List<HashMap<String, String>> teacherList(HashMap<String, String> params);
	List<HashMap<String, String>> teacherSubjectList(HashMap<String, String> params);
	HashMap<String, String> teacherOne(HashMap<String, String> params);

	List<HashMap<String, String>> teacherSalesStatList(HashMap<String, String> params);

	void makeOnSalesStat(Map<String, String> params);
	void makeOffSalesStat(Map<String, String> params);

	HashMap<String, String> userBuyStatList(HashMap<String, String> params);

	List<HashMap<String, String>> searchKeywordList(HashMap<String, String> params);
	int searchKeywordCount(HashMap<String, String> params);
	int searchKeywordSum(HashMap<String, String> params);
}