package web.lecture.service;

import java.util.HashMap;
import java.util.List;

public interface LectureMstService {

	
	List<HashMap<String, String>> lecturemstList(HashMap<String, String> params);
	
	int lecturemstListCount(HashMap<String, String> params);
	
	List<HashMap<String, String>> getBridgeMstcodeSeq(HashMap<String, String> params);
	
	List<HashMap<String, String>> getBridgeMstcode(HashMap<String, String> params);
	
	List<HashMap<String, String>> getMstcode(HashMap<String, String> params);
	
	void lecturemstInsert(HashMap<String, String> params);
	
	void lectureBridgeInsert(HashMap<String, String> params);
	
	void lectureBookInsert2(HashMap<String, String> params);
	
	List<HashMap<String, String>> lectureDataMemoViewList(HashMap<String, String> params);
	
	List<HashMap<String, String>> lectureDataViewList(HashMap<String, String> params);
	
	List<HashMap<String, String>> lectureWMV(HashMap<String, String> params);
	
	List<HashMap<String, String>> lectureDown_Count(HashMap<String, String> params);
	
	
	
	int lectureDeleteCheck(HashMap<String, String> params);
	
	List<HashMap<String, String>> lectureView(HashMap<String, String> params);
	
	List<HashMap<String, String>> lectureViewList(HashMap<String, String> params);
	
	List<HashMap<String, String>> lectureViewBookList(HashMap<String, String> params);
	
	
	
	void lectureBookDelete(HashMap<String, String> params);
	
	void lecturemstUpdate(HashMap<String, String> params);
	
	void lectureBookInsert(HashMap<String, String> params);
	
	void lectureDelete(HashMap<String, String> params);
	
	void lectureBridgeDelete(HashMap<String, String> params);
	
}
