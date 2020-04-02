package web.popup.service;

import java.util.HashMap;
import java.util.List;

public interface PopupNewService {

	List<HashMap<String, Object>> popupNewList(HashMap<String, String> params);
	
	int popupNewListCount(HashMap<String, String> params);
	
	void popupNewInsert(HashMap<String, String> params);
	
	HashMap<String, String> popupNewView(HashMap<String, String> params);
	
	void popupNewUpdate(HashMap<String, String> params);

	
	void popupNewDelete(HashMap<String, String> params);
	void popupNewCheckOPENY(HashMap<String, String> params);
	void popupNewCheckOPENN(HashMap<String, String> params);

	void popupNewCheckDelete(HashMap<String, String> params);
}
