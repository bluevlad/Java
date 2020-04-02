package web.lectureOff.service;

import java.util.HashMap;
import java.util.List;

public interface LectureOffService {

	List<HashMap<String, String>> lectureList(HashMap<String, String> params);
	
	int lectureListCount(HashMap<String, String> params);

	List<HashMap<String, String>> bookList(HashMap<String, String> params);
	
	int bookListCount(HashMap<String, String> params);	
	
	List<HashMap<String, String>> getBridgeLeccodeSeq(HashMap<String, String> params);
	
	List<HashMap<String, String>> getJongLeccodeSeq(HashMap<String, String> params);
	
	List<HashMap<String, String>> getLeccode(HashMap<String, String> params);
	
	List<HashMap<String, String>> getBridgeLeccode(HashMap<String, String> params);
	
	void lectureInsert(HashMap<String, String> params);
	
	void lectureBridgeInsert(HashMap<String, String> params);
	
	void lectureBookInsert(HashMap<String, String> params);
	
	void lectureBookInsert2(HashMap<String, String> params);
	
	List<HashMap<String, String>> lectureViewList(HashMap<String, String> params);
	
	List<HashMap<String, String>> lectureView(HashMap<String, String> params);
	
	List<HashMap<String, String>> lectureViewBookList(HashMap<String, String> params);
	
	void lectureBookDelete(HashMap<String, String> params);
	
	void lectureUpdate(HashMap<String, String> params);
	
	void lectureIsUseUpdate(HashMap<String, String> params);
	
	void lectureDelete(HashMap<String, String> params);
	
	void lectureBridgeDelete(HashMap<String, String> params);	
	
	List<HashMap<String, String>> lectureSeqList(HashMap<String, String> params);
	
	void lectureSeqUpdate(HashMap<String, String> params);	

	List<HashMap<String, String>> lectureViewJongList(HashMap<String, String> params);
	
	List<HashMap<String, String>> lectureJongList(HashMap<String, String> params);
	
	int lectureJongListCount(HashMap<String, String> params);	
	
	List<HashMap<String, String>> lectureJongView(HashMap<String, String> params);
	
	List<HashMap<String, String>> lectureJongSubjectList(HashMap<String, String> params);
	
	int lectureJongSubjectListCount(HashMap<String, String> params);
	
	void lectureLecJongInsert(HashMap<String, String> params);
	
	void lectureChoiceJongNoInsert(HashMap<String, String> params);
	
	List<HashMap<String, String>> lectureViewLeccodeList(HashMap<String, String> params);
	
	void lectureLecJongDelete(HashMap<String, String> params);	
	
	void lectureChoiceJongNoDelete(HashMap<String, String> params);
	
	List<HashMap<String, String>> lecturePayList(HashMap<String, String> params);
	
	List<HashMap<String, String>> off_lecturePayList(HashMap<String, String> params);
	
	List<HashMap<String, String>> lectureJongPayList(HashMap<String, String> params);

	List<HashMap<String, String>> lectureDataMemoViewList(HashMap<String, String> params);
	
	List<HashMap<String, String>> lectureDataViewList(HashMap<String, String> params);
	
	int lectureDeleteCheck(HashMap<String, String> params);
	
	void lectureOffDayDelete(HashMap<String, String> params);
	
	void lectureOffDayInsert(HashMap<String, String> params);
	
	void lectureOffDayInsert2(HashMap<String, String> params);
	
	List<HashMap<String, String>> lectureOffDayList(HashMap<String, String> params);	
}
