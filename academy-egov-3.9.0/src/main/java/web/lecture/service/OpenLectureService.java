package web.lecture.service;

import java.util.HashMap;
import java.util.List;

public interface OpenLectureService {

	List<HashMap<String, String>> openlectureList(HashMap<String, String> params);
	
	int openlectureListCount(HashMap<String, String> params);

	List<HashMap<String, String>> bookList(HashMap<String, String> params);
	
	int bookListCount(HashMap<String, String> params);	
	
	List<HashMap<String, String>> getBridgeLeccodeSeq(HashMap<String, String> params);// 삭제대상
	
	List<HashMap<String, String>> getJongLeccodeSeq(HashMap<String, String> params); // 삭제대상
	
	List<HashMap<String, String>> getopenLeccode(HashMap<String, String> params); // 삭제대상
	
	List<HashMap<String, String>> getBridgeLeccode(HashMap<String, String> params);//삭제대상
	
	List<HashMap<String, String>> BridgeLeccode(HashMap<String, String> params);
	
	void openlectureInsert(HashMap<String, String> params);
	
	void openlectureBridgeInsert(HashMap<String, String> params);
	
	void openlectureBookInsert(HashMap<String, String> params);
	
	void openlectureBookInsert2(HashMap<String, String> params);
	
	List<HashMap<String, String>> openlectureViewList(HashMap<String, String> params);
	
	List<HashMap<String, String>> openlectureView(HashMap<String, String> params);
	
	List<HashMap<String, String>> openlectureViewBookList(HashMap<String, String> params);
	
	void openlectureBookDelete(HashMap<String, String> params);
	
	void openlectureUpdate(HashMap<String, String> params);
	
	void Modify_Lecture_On_Off(HashMap<String, String> params);
	
	void openlectureIsUseUpdate(HashMap<String, String> params);
	
	void openlectureDelete(HashMap<String, String> params);
	
	void openlectureBridgeDelete(HashMap<String, String> params);	
	
	void lecMovUpdate(HashMap<String, String> params);	
	
	List<HashMap<String, String>> openlectureSeqList(HashMap<String, String> params);
	
	void openlectureSeqUpdate(HashMap<String, String> params);	

	
	
	List<HashMap<String, String>> openlectureViewJongList(HashMap<String, String> params);
	
	List<HashMap<String, String>> openlectureJongList(HashMap<String, String> params);
	
	int openlectureJongListCount(HashMap<String, String> params);	
	
	List<HashMap<String, String>> openlectureJongSubjectList(HashMap<String, String> params);	
	
	List<HashMap<String, String>> openlectureJongView(HashMap<String, String> params);
	
	int openlectureJongSubjectListCount(HashMap<String, String> params);
	
	void openlectureLecJongInsert(HashMap<String, String> params);
	
	void openlectureChoiceJongNoInsert(HashMap<String, String> params);
	
	List<HashMap<String, String>> openlectureViewLeccodeList(HashMap<String, String> params);
	
	void openlectureLecJongDelete(HashMap<String, String> params);	
	
	void openlectureChoiceJongNoDelete(HashMap<String, String> params);
	
	List<HashMap<String, String>> openlecturePayList(HashMap<String, String> params);
	
	List<HashMap<String, String>> openlectureJongPayList(HashMap<String, String> params);
	
	List<HashMap<String, String>> openlectureDataMemoViewList(HashMap<String, String> params);
	
	List<HashMap<String, String>> openlectureDataViewList(HashMap<String, String> params);
	
	List<HashMap<String, String>> openlectureDataMovieViewList(HashMap<String, String> params);
	
	List<HashMap<String, String>> openlectureDataMovieList(HashMap<String, String> params);
	
	void openlectureMovieInsert(HashMap<String, String> params);
	
	void openlectureMovieDelete(HashMap<String, String> params);		
	
	void openlectureMovieUpdate(HashMap<String, String> params);
	
	void openlectureMovieFileDelete(HashMap<String, String> params);	
	

	void openlectureMovieMemoInsert(HashMap<String, String> params);
		
	void openlectureMovieMemoUpdate(HashMap<String, String> params);
	
	void openlectureMovieMemoDelete(HashMap<String, String> params);
	
	int openlectureDeleteCheck(HashMap<String, String> params);
	
	
	List<HashMap<String, String>> playinfo(HashMap<String, String> params);

	List<HashMap<String, String>> getCbMovie4_free_admin(HashMap<String, String> params);
	
	int getCbMovie4_free_admin_count(HashMap<String, String> params);	
	
	HashMap<String, String> openlectureOnDetailS(HashMap<String, String> params);
	
	void insertPmpDownLog(HashMap<String, String> params);
	
	List<HashMap<String, String>> openlectureWMV(HashMap<String, String> params);
	
	List<HashMap<String, String>> openlectureDown_Count(HashMap<String, String> params);
}
