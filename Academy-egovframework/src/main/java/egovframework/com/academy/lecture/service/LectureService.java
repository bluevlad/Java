package egovframework.com.academy.lecture.service;

import java.util.HashMap;
import java.util.List;

public interface LectureService {

	List<?> selectLectureList(LectureVO LectureVO) throws Exception;

	int selectLectureListCount(LectureVO LectureVO) throws Exception;

	List<HashMap<String, String>> bookList(HashMap<String, String> params) throws Exception;

	int bookListCount(HashMap<String, String> params) throws Exception;

	List<HashMap<String, String>> getBridgeLeccodeSeq(HashMap<String, String> params) throws Exception;// 삭제대상

	List<HashMap<String, String>> getJongLeccodeSeq(HashMap<String, String> params) throws Exception; // 삭제대상

	List<HashMap<String, String>> getLeccode(HashMap<String, String> params) throws Exception; // 삭제대상

	List<HashMap<String, String>> getBridgeLeccode(HashMap<String, String> params) throws Exception;//삭제대상

	List<HashMap<String, String>> BridgeLeccode(HashMap<String, String> params) throws Exception;

	void lectureInsert(HashMap<String, String> params) throws Exception;

	void lectureBridgeInsert(HashMap<String, String> params) throws Exception;

	void lectureBookInsert(HashMap<String, String> params) throws Exception;

	void lectureBookInsert2(HashMap<String, String> params) throws Exception;

	List<HashMap<String, String>> lectureViewList(HashMap<String, String> params) throws Exception;

	List<HashMap<String, String>> lectureView(HashMap<String, String> params) throws Exception;

	List<HashMap<String, String>> lectureViewBookList(HashMap<String, String> params) throws Exception;

	void lectureBookDelete(HashMap<String, String> params) throws Exception;

	void lectureUpdate(HashMap<String, String> params) throws Exception;

	void Modify_Lecture_On_Off(HashMap<String, String> params) throws Exception;

	void lectureIsUseUpdate(HashMap<String, String> params) throws Exception;

	void lectureDelete(HashMap<String, String> params) throws Exception;

	void lectureBridgeDelete(HashMap<String, String> params) throws Exception;

	void lecMovUpdate(HashMap<String, String> params) throws Exception;

	List<HashMap<String, String>> lectureSeqList(HashMap<String, String> params) throws Exception;

	void lectureSeqUpdate(HashMap<String, String> params) throws Exception;



	List<HashMap<String, String>> lectureViewJongList(HashMap<String, String> params) throws Exception;

	List<HashMap<String, String>> lectureJongList(HashMap<String, String> params) throws Exception;

	int lectureJongListCount(HashMap<String, String> params) throws Exception;

	List<HashMap<String, String>> lectureYearList(HashMap<String, String> params) throws Exception;

	int lectureYearListCount(HashMap<String, String> params) throws Exception;

	List<HashMap<String, String>> lectureJongSubjectList(HashMap<String, String> params) throws Exception;

	List<HashMap<String, String>> lectureJongView(HashMap<String, String> params) throws Exception;

	int lectureJongSubjectListCount(HashMap<String, String> params) throws Exception;

	void lectureLecJongInsert(HashMap<String, String> params) throws Exception;

	void lectureChoiceJongNoInsert(HashMap<String, String> params) throws Exception;

	List<HashMap<String, String>> lectureViewLeccodeList(HashMap<String, String> params) throws Exception;

	void lectureLecJongDelete(HashMap<String, String> params) throws Exception;

	void lectureChoiceJongNoDelete(HashMap<String, String> params) throws Exception;

	List<HashMap<String, String>> lecturePayList(HashMap<String, String> params) throws Exception;

	List<HashMap<String, String>> lectureJongPayList(HashMap<String, String> params) throws Exception;

	List<HashMap<String, String>> lectureDataMemoViewList(HashMap<String, String> params) throws Exception;

	List<HashMap<String, String>> lectureDataViewList(HashMap<String, String> params) throws Exception;

	List<HashMap<String, String>> lectureMobileList(HashMap<String, String> params) throws Exception;

	List<HashMap<String, String>> lectureDataMovieViewList(HashMap<String, String> params) throws Exception;

	List<HashMap<String, String>> lectureDataMovieList(HashMap<String, String> params) throws Exception;

	void lectureMovieInsert(HashMap<String, String> params) throws Exception;

	void lectureMovieDelete(HashMap<String, String> params) throws Exception;

	void lectureMovieUpdate(HashMap<String, String> params) throws Exception;

	void lectureMovieFileDelete(HashMap<String, String> params) throws Exception;


	void lectureMovieMemoInsert(HashMap<String, String> params) throws Exception;


	void lectureMovieMemoUpdate(HashMap<String, String> params) throws Exception;

	void lectureMovieMemoDelete(HashMap<String, String> params) throws Exception;

	int lectureDeleteCheck(HashMap<String, String> params) throws Exception;


	List<HashMap<String, String>> playinfo(HashMap<String, String> params) throws Exception;

	List<HashMap<String, String>> getCbMovie4_free_admin(HashMap<String, String> params) throws Exception;

	int getCbMovie4_free_admin_count(HashMap<String, String> params) throws Exception;

	HashMap<String, String> lectureOnDetailS(HashMap<String, String> params) throws Exception;

	void insertPmpDownLog(HashMap<String, String> params) throws Exception;

	List<HashMap<String, String>> lectureWMV(HashMap<String, String> params) throws Exception;

	List<HashMap<String, String>> lectureDown_Count(HashMap<String, String> params) throws Exception;
	
	List<HashMap<String, String>> lectureFreePassPayList(HashMap<String, String> params) throws Exception;
	
	List<HashMap<String, String>> YearIngList(HashMap<String, String> params) throws Exception;
	
	List<HashMap<String, String>> MyYearIngList(HashMap<String, String> params) throws Exception;
	
	List<HashMap<String, String>> bookView(HashMap<String, String> params) throws Exception;
}
