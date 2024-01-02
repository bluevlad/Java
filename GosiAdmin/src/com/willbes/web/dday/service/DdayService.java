package com.willbes.web.dday.service;

import java.util.HashMap;
import java.util.List;

public interface DdayService {
	List<HashMap<String, String>> categoryList(HashMap<String, String> params);
	List<HashMap<String, String>> list(HashMap<String, String> params);
	int listCount(HashMap<String, String> params);
	HashMap<String, String> view(HashMap<String, String> params);
	void insert(HashMap<String, String> params);
	void update(HashMap<String, String> params);
	void delete(HashMap<String, String> params);
}