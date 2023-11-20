package egovframework.com.academy.lecture.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.academy.lecture.service.LectureVO;
import egovframework.com.cmm.service.impl.EgovComAbstractDAO;

@Repository
public class LectureDAO extends EgovComAbstractDAO {

	public List<?> selectLectureList(LectureVO LectureVO) throws Exception{
		return selectList("lecture.selectLectureList", LectureVO);
	}
	public int selectLectureListCount(LectureVO LectureVO) throws Exception{
		return (Integer)selectOne("lecture.selectLectureListCount", LectureVO);
	}

	public List<HashMap<String, String>> bookList(HashMap<String, String> params) throws Exception{
		return selectList("lecture.bookList", params);
	}

	public int bookListCount(HashMap<String, String> params) throws Exception{
		return selectOne("lecture.bookListCount", params);
	}

	public List<HashMap<String, String>> getBridgeLeccodeSeq(HashMap<String, String> params) throws Exception{
		return selectList("lecture.getBridgeLeccodeSeq", params);
	}

	public List<HashMap<String, String>> getJongLeccodeSeq(HashMap<String, String> params) throws Exception{
		return selectList("lecture.getJongLeccodeSeq", params);
	}

	public List<HashMap<String, String>> getLeccode(HashMap<String, String> params) throws Exception{
		return selectList("lecture.getLeccode", params);
	}

	public List<HashMap<String, String>> getBridgeLeccode(HashMap<String, String> params) throws Exception{
		return selectList("lecture.getBridgeLeccode", params);
	}

	public List<HashMap<String, String>> BridgeLeccode(HashMap<String, String> params) throws Exception{
		return selectList("lecture.BridgeLeccode", params);
	}

	public void lectureInsert(HashMap<String, String> params) throws Exception{
		insert("lecture.lectureInsert", params);
	}


	public void lectureBridgeInsert(HashMap<String, String> params) throws Exception{
		insert("lecture.lectureBridgeInsert", params);
	}

	public void lectureBookInsert(HashMap<String, String> params) throws Exception{
		insert("lecture.lectureBookInsert", params);
	}

	public void lectureBookInsert2(HashMap<String, String> params) throws Exception{
		insert("lecture.lectureBookInsert2", params);
	}

	public List<HashMap<String, String>> lectureViewList(HashMap<String, String> params) throws Exception{
		return selectList("lecture.lectureViewList", params);
	}

	public List<HashMap<String, String>> lectureView(HashMap<String, String> params) throws Exception{
		return selectList("lecture.lectureView", params);
	}

	public List<HashMap<String, String>> lectureViewBookList(HashMap<String, String> params) throws Exception{
		return selectList("lecture.lectureViewBookList", params);
	}

	public void lectureBookDelete(HashMap<String, String> params) throws Exception{
		delete("lecture.lectureBookDelete", params);
	}

	public void lectureUpdate(HashMap<String, String> params) throws Exception{
		update("lecture.lectureUpdate", params);
	}

	public void lectureIsUseUpdate(HashMap<String, String> params) throws Exception{
		delete("lecture.lectureIsUseUpdate", params);
	}

	public void Modify_Lecture_On_Off(HashMap<String, String> params) throws Exception{
		delete("lecture.Modify_Lecture_On_Off", params);
	}

	public void lectureDelete(HashMap<String, String> params) throws Exception{
		delete("lecture.lectureDelete", params);
	}

	public void lectureBridgeDelete(HashMap<String, String> params) throws Exception{
		delete("lecture.lectureBridgeDelete", params);
	}

	public void lecMovUpdate(HashMap<String, String> params) throws Exception{
		delete("lecture.lecMovUpdate", params);
	}

	public List<HashMap<String, String>> lectureSeqList(HashMap<String, String> params) throws Exception{
		return selectList("lecture.lectureSeqList", params);
	}

	public void lectureSeqUpdate(HashMap<String, String> params) throws Exception{
		update("lecture.lectureSeqUpdate", params);
	}

	public List<HashMap<String, String>> lectureViewJongList(HashMap<String, String> params) throws Exception{
		return selectList("lecture.lectureViewJongList", params);
	}

	public List<HashMap<String, String>> lectureJongList(HashMap<String, String> params) throws Exception{
		return selectList("lecture.lectureJongList", params);
	}

	public int lectureJongListCount(HashMap<String, String> params) throws Exception{
		return selectOne("lecture.lectureJongListCount", params);
	}

	public List<HashMap<String, String>> lectureYearList(HashMap<String, String> params) throws Exception{
		return selectList("lecture.lectureYearList", params);
	}

	public int lectureYearListCount(HashMap<String, String> params) throws Exception{
		return selectOne("lecture.lectureYearListCount", params);
	}

	public List<HashMap<String, String>> lectureJongView(HashMap<String, String> params) throws Exception{
		return selectList("lecture.lectureJongView", params);
	}

	public List<HashMap<String, String>> lectureJongSubjectList(HashMap<String, String> params) throws Exception{
		return selectList("lecture.lectureJongSubjectList", params);
	}

	public int lectureJongSubjectListCount(HashMap<String, String> params) throws Exception{
		return selectOne("lecture.lectureJongSubjectListCount", params);
	}

	public void lectureLecJongInsert(HashMap<String, String> params) throws Exception{
		insert("lecture.lectureLecJongInsert", params);
	}

	public void lectureChoiceJongNoInsert(HashMap<String, String> params) throws Exception{
		insert("lecture.lectureChoiceJongNoInsert", params);
	}

	public List<HashMap<String, String>> lectureViewLeccodeList(HashMap<String, String> params) throws Exception{
		return selectList("lecture.lectureViewLeccodeList", params);
	}

	public void lectureLecJongDelete(HashMap<String, String> params) throws Exception{
		delete("lecture.lectureLecJongDelete", params);
	}

	public void lectureChoiceJongNoDelete(HashMap<String, String> params) throws Exception{
		delete("lecture.lectureChoiceJongNoDelete", params);
	}

	public List<HashMap<String, String>> lecturePayList(HashMap<String, String> params) throws Exception{
		return selectList("lecture.lecturePayList", params);
	}

	public List<HashMap<String, String>> lectureJongPayList(HashMap<String, String> params) throws Exception{
		return selectList("lecture.lectureJongPayList", params);
	}
	
	public List<HashMap<String, String>> lectureFreePassPayList(HashMap<String, String> params){
		return selectList("lecture.lectureFreePassPayList", params);
	}
	
	public List<HashMap<String, String>> YearIngList(HashMap<String, String> params) throws Exception{
		return selectList("lecture.YearIngList", params);
	}
	
	public List<HashMap<String, String>> MyYearIngList(HashMap<String, String> params) throws Exception{
		return selectList("lecture.MyYearIngList", params);
	}

	public List<HashMap<String, String>> lectureDataMemoViewList(HashMap<String, String> params) throws Exception{
		return selectList("lecture.lectureDataMemoViewList", params);
	}

	public List<HashMap<String, String>> lectureDataViewList(HashMap<String, String> params) throws Exception{
		return selectList("lecture.lectureDataViewList", params);
	}
	public List<HashMap<String, String>> lectureMobileList(HashMap<String, String> params) throws Exception{
		return selectList("lecture.lectureMobileList", params);
	}

	public List<HashMap<String, String>> lectureDataMovieViewList(HashMap<String, String> params) throws Exception{
		return selectList("lecture.lectureDataMovieViewList", params);
	}


	public List<HashMap<String, String>> lectureDataMovieList(HashMap<String, String> params) throws Exception{
		return selectList("lecture.lectureDataMovieList", params);
	}

	public void lectureMovieInsert(HashMap<String, String> params) throws Exception{
		insert("lecture.lectureMovieInsert", params);
	}

	public void lectureMovieDelete(HashMap<String, String> params) throws Exception{
		delete("lecture.lectureMovieDelete", params);
	}

	public void lectureMovieUpdate(HashMap<String, String> params) throws Exception{
		update("lecture.lectureMovieUpdate", params);
	}

	public void lectureMovieFileDelete(HashMap<String, String> params) throws Exception{
		update("lecture.lectureMovieFileDelete", params);
	}


	public void lectureMovieMemoInsert(HashMap<String, String> params) throws Exception{
		insert("lecture.lectureMovieMemoInsert", params);
	}

	public void lectureMovieMemoUpdate(HashMap<String, String> params) throws Exception{
		update("lecture.lectureMovieMemoUpdate", params);
	}

	public void lectureMovieMemoDelete(HashMap<String, String> params) throws Exception{
		delete("lecture.lectureMovieMemoDelete", params);
	}

	public int lectureDeleteCheck(HashMap<String, String> params) throws Exception{
		return selectOne("lecture.lectureDeleteCheck", params);
	}




	public List<HashMap<String, String>> playinfo(HashMap<String, String> params) throws Exception{
		return selectList("lecture.playinfo", params);
	}

	public List<HashMap<String, String>> getCbMovie4_free_admin(HashMap<String, String> params) throws Exception{
		return selectList("lecture.getCbMovie4_free_admin", params);
	}

	public int getCbMovie4_free_admin_count(HashMap<String, String> params) throws Exception{
		return selectOne("lecture.getCbMovie4_free_admin_count", params);
	}

	public HashMap<String, String> lectureOnDetailS(HashMap<String, String> params) throws Exception {
		return selectOne("lecture.lectureOnDetailS", params);
	}

	public void insertPmpDownLog(HashMap<String, String> params) throws Exception{
		insert("lecture.insertPmpDownLog", params);
	}

	public List<HashMap<String, String>> lectureWMV(HashMap<String, String> params) throws Exception{
		return selectList("lecture.lectureWMV", params);
	}

	public List<HashMap<String, String>> lectureDown_Count(HashMap<String, String> params) throws Exception{
		return selectList("lecture.lectureDown_Count", params);
	}
	public String getRleccode(HashMap<String, String> params) throws Exception {
		return selectOne("lecture.getRleccode", params);
	}
	
	public void oldTbmovieToNewTbmovieInsert(HashMap<String, String> params) throws Exception{
		insert("lecture.oldTbmovieToNewTbmovieInsert", params);
	}
	
	public List<HashMap<String, String>> bookView(HashMap<String, String> params) throws Exception{
        return selectList("lecture.bookView", params);
    }
}
