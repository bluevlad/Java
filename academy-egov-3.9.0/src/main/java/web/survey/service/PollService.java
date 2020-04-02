package web.survey.service;

import java.util.HashMap;
import java.util.List;

public interface PollService {

	List<HashMap<String, String>> pollList(HashMap<String, String> params);
	
	int pollListCount(HashMap<String, String> params);
	
	HashMap<String, String> pollView(HashMap<String, String> params);
	
	void pollInsert(HashMap<String, String> params);
	
	void pollUpdate(HashMap<String, String> params);
	
	void pollDelete(HashMap<String, String> params);
}
