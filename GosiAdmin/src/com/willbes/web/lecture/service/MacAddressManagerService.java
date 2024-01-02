package com.willbes.web.lecture.service;

import java.util.HashMap;
import java.util.List;

public interface MacAddressManagerService {

	List<HashMap<String, String>> macaddressmanagerList(HashMap<String, String> params);
	
	List<HashMap<String, String>> devicelist(HashMap<String, String> params);
	
	List<HashMap<String, String>> macaddressView(HashMap<String, String> params);

	int macaddressmanagerListCount(HashMap<String, String> params);

	void macaddressmanagerUpdate(HashMap<String, String> params);
	void macaddressmanagerUpdate1(HashMap<String, String> params);

}
