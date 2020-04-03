package web.lecture.service;

import java.util.HashMap;
import java.util.List;

public interface KindService {

	List<HashMap<String, String>> getKindList(Object obj);

	List<HashMap<String, String>> kindList(HashMap<String, String> params);

	int kindListCount(HashMap<String, String> params);

	void kindInsert(HashMap<String, String> params);

	List<HashMap<String, String>> kindView(HashMap<String, String> params);

	void kindUpdate(HashMap<String, String> params);

	void kindDelete(HashMap<String, String> params);

	int kindCheck(HashMap<String, String> params);

	List<HashMap<String, String>> selectKindCode();

	void SeqUpdate(HashMap<String, String> params);
}
