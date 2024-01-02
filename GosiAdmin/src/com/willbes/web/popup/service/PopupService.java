package com.willbes.web.popup.service;

import java.util.HashMap;
import java.util.List;

public interface PopupService {

	List<HashMap<String, Object>> popupList(HashMap<String, String> params);
	
	int popupListCount(HashMap<String, String> params);
	
	void popupInsert(HashMap<String, String> params);
	
	HashMap<String, String> popupView(HashMap<String, String> params);
	
	void popupUpdate(HashMap<String, String> params);

	
	void popupDelete(HashMap<String, String> params);
	void popupCheckOPENY(HashMap<String, String> params);
	void popupCheckOPENN(HashMap<String, String> params);

	void popupCheckDelete(HashMap<String, String> params);
}
