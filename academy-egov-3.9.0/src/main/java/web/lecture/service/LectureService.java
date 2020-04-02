package web.lecture.service;

import java.util.HashMap;
import java.util.List;

public interface LectureService {

	List<HashMap<String, String>> lectureList(HashMap<String, String> params);

	List<HashMap<String, String>> oldFreeToNewFree(HashMap<String, String> params);

	int lectureListCount(HashMap<String, String> params);

	List<HashMap<String, String>> bookList(HashMap<String, String> params);

	int bookListCount(HashMap<String, String> params);

	List<HashMap<String, String>> getBridgeLeccodeSeq(HashMap<String, String> params);// 삭제대상

	List<HashMap<String, String>> getJongLeccodeSeq(HashMap<String, String> params); // 삭제대상

	List<HashMap<String, String>> getLeccode(HashMap<String, String> params); // 삭제대상

	List<HashMap<String, String>> getBridgeLeccode(HashMap<String, String> params);//삭제대상

	List<HashMap<String, String>> BridgeLeccode(HashMap<String, String> params);

	void lectureInsert(HashMap<String, String> params);

	void lectureBridgeInsert(HashMap<String, String> params);

	void lectureBookInsert(HashMap<String, String> params);

	void lectureBookInsert2(HashMap<String, String> params);

	List<HashMap<String, String>> lectureViewList(HashMap<String, String> params);

	List<HashMap<String, String>> lectureView(HashMap<String, String> params);

	List<HashMap<String, String>> lectureViewBookList(HashMap<String, String> params);

	void lectureBookDelete(HashMap<String, String> params);

	void lectureUpdate(HashMap<String, String> params);

	void Modify_Lecture_On_Off(HashMap<String, String> params);

	void lectureIsUseUpdate(HashMap<String, String> params);

	void lectureDelete(HashMap<String, String> params);

	void lectureBridgeDelete(HashMap<String, String> params);

	void lecMovUpdate(HashMap<String, String> params);

	List<HashMap<String, String>> lectureSeqList(HashMap<String, String> params);

	void lectureSeqUpdate(HashMap<String, String> params);



	List<HashMap<String, String>> lectureViewJongList(HashMap<String, String> params);

	List<HashMap<String, String>> lectureJongList(HashMap<String, String> params);

	int lectureJongListCount(HashMap<String, String> params);

	List<HashMap<String, String>> lectureYearList(HashMap<String, String> params);

	int lectureYearListCount(HashMap<String, String> params);

	List<HashMap<String, String>> lectureJongSubjectList(HashMap<String, String> params);

	List<HashMap<String, String>> lectureJongView(HashMap<String, String> params);

	int lectureJongSubjectListCount(HashMap<String, String> params);

	void lectureLecJongInsert(HashMap<String, String> params);

	void lectureChoiceJongNoInsert(HashMap<String, String> params);

	List<HashMap<String, String>> lectureViewLeccodeList(HashMap<String, String> params);

	void lectureLecJongDelete(HashMap<String, String> params);

	void lectureChoiceJongNoDelete(HashMap<String, String> params);

	List<HashMap<String, String>> lecturePayList(HashMap<String, String> params);

	List<HashMap<String, String>> lectureJongPayList(HashMap<String, String> params);

	List<HashMap<String, String>> lectureDataMemoViewList(HashMap<String, String> params);

	List<HashMap<String, String>> lectureDataViewList(HashMap<String, String> params);

	List<HashMap<String, String>> lectureMobileList(HashMap<String, String> params);

	List<HashMap<String, String>> lectureDataMovieViewList(HashMap<String, String> params);

	List<HashMap<String, String>> lectureDataMovieList(HashMap<String, String> params);

	void lectureMovieInsert(HashMap<String, String> params);

	void lectureMovieDelete(HashMap<String, String> params);

	void lectureMovieUpdate(HashMap<String, String> params);

	void lectureMovieFileDelete(HashMap<String, String> params);


	void lectureMovieMemoInsert(HashMap<String, String> params);


	void lectureMovieMemoUpdate(HashMap<String, String> params);

	void lectureMovieMemoDelete(HashMap<String, String> params);

	int lectureDeleteCheck(HashMap<String, String> params);


	List<HashMap<String, String>> playinfo(HashMap<String, String> params);

	List<HashMap<String, String>> getCbMovie4_free_admin(HashMap<String, String> params);

	int getCbMovie4_free_admin_count(HashMap<String, String> params);

	HashMap<String, String> lectureOnDetailS(HashMap<String, String> params);

	void insertPmpDownLog(HashMap<String, String> params);

	List<HashMap<String, String>> lectureWMV(HashMap<String, String> params);

	List<HashMap<String, String>> lectureDown_Count(HashMap<String, String> params);

	void oldFreeToNewFreeInsert(HashMap<String, String> params);
	
	void oldBogangFreeToNewBogangFree(HashMap<String, String> params);
	
	List<HashMap<String, String>> lectureFreePassPayList(HashMap<String, String> params);
	
	List<HashMap<String, String>> YearIngList(HashMap<String, String> params);
	
	List<HashMap<String, String>> MyYearIngList(HashMap<String, String> params);
	
	List<HashMap<String, String>> bookView(HashMap<String, String> params);
}
