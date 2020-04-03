package web.manage.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface lectureYearService {

	List<HashMap<String, String>> onPayList(Map<String, String> params);

	List<HashMap<String, String>> onOrderList(Map<String, String> params);
	
	List<HashMap<String, String>> payUserListByLeccode(HashMap<String, String> params);

	int payUserListByLeccodeCount(HashMap<String, String> params);
	
}