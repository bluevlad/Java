package web.lecture.service;

import java.util.HashMap;
import java.util.List;

public interface FormService {

	List<HashMap<String, String>> formList(HashMap<String, String> params);
	
	int formListCount(HashMap<String, String> params);

	String formGetCode();
	
	void formInsert(HashMap<String, String> params);
	
	List<HashMap<String, String>> formView(HashMap<String, String> params);
	
	void formUpdate(HashMap<String, String> params);
	
	void formDelete(HashMap<String, String> params);
	
	int formCheck(HashMap<String, String> params);
	
	List<HashMap<String, String>> getCodeList(HashMap<String, String> params);
}
