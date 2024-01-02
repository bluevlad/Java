package com.willbes.web.lecture.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.willbes.cmm.service.impl.CmmAbstractMapper;

@Repository
public class LectureDAO extends CmmAbstractMapper {

	public List<HashMap<String, String>> lectureList(HashMap<String, String> params){
		return getSqlSession().selectList("lecture.lectureList", params);
	}
	
	public List<HashMap<String, String>> oldFreeToNewFree(HashMap<String, String> params){
		return getSqlSession().selectList("lecture.oldFreeToNewFree", params);
	}
	public List<HashMap<String, String>> oldBogangFreeToNewBogangFree(HashMap<String, String> params){
		return getSqlSession().selectList("lecture.oldBogangFreeToNewBogangFree", params);
	}

	public int lectureListCount(HashMap<String, String> params){
		return getSqlSession().selectOne("lecture.lectureListCount", params);
	}

	public List<HashMap<String, String>> bookList(HashMap<String, String> params){
		return getSqlSession().selectList("lecture.bookList", params);
	}

	public int bookListCount(HashMap<String, String> params){
		return getSqlSession().selectOne("lecture.bookListCount", params);
	}

	public List<HashMap<String, String>> getBridgeLeccodeSeq(HashMap<String, String> params){
		return getSqlSession().selectList("lecture.getBridgeLeccodeSeq", params);
	}

	public List<HashMap<String, String>> getJongLeccodeSeq(HashMap<String, String> params){
		return getSqlSession().selectList("lecture.getJongLeccodeSeq", params);
	}

	public List<HashMap<String, String>> getLeccode(HashMap<String, String> params){
		return getSqlSession().selectList("lecture.getLeccode", params);
	}

	public List<HashMap<String, String>> getBridgeLeccode(HashMap<String, String> params){
		return getSqlSession().selectList("lecture.getBridgeLeccode", params);
	}

	public List<HashMap<String, String>> BridgeLeccode(HashMap<String, String> params){
		return getSqlSession().selectList("lecture.BridgeLeccode", params);
	}

	public void lectureInsert(HashMap<String, String> params){
		getSqlSession().insert("lecture.lectureInsert", params);
	}


	public void lectureBridgeInsert(HashMap<String, String> params){
		getSqlSession().insert("lecture.lectureBridgeInsert", params);
	}

	public void lectureBookInsert(HashMap<String, String> params){
		getSqlSession().insert("lecture.lectureBookInsert", params);
	}

	public void lectureBookInsert2(HashMap<String, String> params){
		getSqlSession().insert("lecture.lectureBookInsert2", params);
	}

	public List<HashMap<String, String>> lectureViewList(HashMap<String, String> params){
		return getSqlSession().selectList("lecture.lectureViewList", params);
	}

	public List<HashMap<String, String>> lectureView(HashMap<String, String> params){
		return getSqlSession().selectList("lecture.lectureView", params);
	}

	public List<HashMap<String, String>> lectureViewBookList(HashMap<String, String> params){
		return getSqlSession().selectList("lecture.lectureViewBookList", params);
	}

	public void lectureBookDelete(HashMap<String, String> params){
		getSqlSession().delete("lecture.lectureBookDelete", params);
	}

	public void lectureUpdate(HashMap<String, String> params){
		getSqlSession().update("lecture.lectureUpdate", params);
	}

	public void lectureIsUseUpdate(HashMap<String, String> params){
		getSqlSession().delete("lecture.lectureIsUseUpdate", params);
	}

	public void Modify_Lecture_On_Off(HashMap<String, String> params){
		getSqlSession().delete("lecture.Modify_Lecture_On_Off", params);
	}

	public void lectureDelete(HashMap<String, String> params){
		getSqlSession().delete("lecture.lectureDelete", params);
	}

	public void lectureBridgeDelete(HashMap<String, String> params){
		getSqlSession().delete("lecture.lectureBridgeDelete", params);
	}

	public void lecMovUpdate(HashMap<String, String> params){
		getSqlSession().delete("lecture.lecMovUpdate", params);
	}

	public List<HashMap<String, String>> lectureSeqList(HashMap<String, String> params){
		return getSqlSession().selectList("lecture.lectureSeqList", params);
	}

	public void lectureSeqUpdate(HashMap<String, String> params){
		getSqlSession().update("lecture.lectureSeqUpdate", params);
	}

	public List<HashMap<String, String>> lectureViewJongList(HashMap<String, String> params){
		return getSqlSession().selectList("lecture.lectureViewJongList", params);
	}

	public List<HashMap<String, String>> lectureJongList(HashMap<String, String> params){
		return getSqlSession().selectList("lecture.lectureJongList", params);
	}

	public int lectureJongListCount(HashMap<String, String> params){
		return getSqlSession().selectOne("lecture.lectureJongListCount", params);
	}

	public List<HashMap<String, String>> lectureYearList(HashMap<String, String> params){
		return getSqlSession().selectList("lecture.lectureYearList", params);
	}

	public int lectureYearListCount(HashMap<String, String> params){
		return getSqlSession().selectOne("lecture.lectureYearListCount", params);
	}

	public List<HashMap<String, String>> lectureJongView(HashMap<String, String> params){
		return getSqlSession().selectList("lecture.lectureJongView", params);
	}

	public List<HashMap<String, String>> lectureJongSubjectList(HashMap<String, String> params){
		return getSqlSession().selectList("lecture.lectureJongSubjectList", params);
	}

	public int lectureJongSubjectListCount(HashMap<String, String> params){
		return getSqlSession().selectOne("lecture.lectureJongSubjectListCount", params);
	}

	public void lectureLecJongInsert(HashMap<String, String> params){
		getSqlSession().insert("lecture.lectureLecJongInsert", params);
	}

	public void lectureChoiceJongNoInsert(HashMap<String, String> params){
		getSqlSession().insert("lecture.lectureChoiceJongNoInsert", params);
	}

	public List<HashMap<String, String>> lectureViewLeccodeList(HashMap<String, String> params){
		return getSqlSession().selectList("lecture.lectureViewLeccodeList", params);
	}

	public void lectureLecJongDelete(HashMap<String, String> params){
		getSqlSession().delete("lecture.lectureLecJongDelete", params);
	}

	public void lectureChoiceJongNoDelete(HashMap<String, String> params){
		getSqlSession().delete("lecture.lectureChoiceJongNoDelete", params);
	}

	public List<HashMap<String, String>> lecturePayList(HashMap<String, String> params){
		return getSqlSession().selectList("lecture.lecturePayList", params);
	}

	public List<HashMap<String, String>> lectureJongPayList(HashMap<String, String> params){
		return getSqlSession().selectList("lecture.lectureJongPayList", params);
	}
	
	public List<HashMap<String, String>> lectureFreePassPayList(HashMap<String, String> params){
		return getSqlSession().selectList("lecture.lectureFreePassPayList", params);
	}
	
	public List<HashMap<String, String>> YearIngList(HashMap<String, String> params){
		return getSqlSession().selectList("lecture.YearIngList", params);
	}
	
	public List<HashMap<String, String>> MyYearIngList(HashMap<String, String> params){
		return getSqlSession().selectList("lecture.MyYearIngList", params);
	}

	public List<HashMap<String, String>> lectureDataMemoViewList(HashMap<String, String> params){
		return getSqlSession().selectList("lecture.lectureDataMemoViewList", params);
	}

	public List<HashMap<String, String>> lectureDataViewList(HashMap<String, String> params){
		return getSqlSession().selectList("lecture.lectureDataViewList", params);
	}
	public List<HashMap<String, String>> lectureMobileList(HashMap<String, String> params){
		return getSqlSession().selectList("lecture.lectureMobileList", params);
	}

	public List<HashMap<String, String>> lectureDataMovieViewList(HashMap<String, String> params){
		return getSqlSession().selectList("lecture.lectureDataMovieViewList", params);
	}


	public List<HashMap<String, String>> lectureDataMovieList(HashMap<String, String> params){
		return getSqlSession().selectList("lecture.lectureDataMovieList", params);
	}

	public void lectureMovieInsert(HashMap<String, String> params){
		getSqlSession().insert("lecture.lectureMovieInsert", params);
	}

	public void lectureMovieDelete(HashMap<String, String> params){
		getSqlSession().delete("lecture.lectureMovieDelete", params);
	}

	public void lectureMovieUpdate(HashMap<String, String> params){
		getSqlSession().update("lecture.lectureMovieUpdate", params);
	}

	public void lectureMovieFileDelete(HashMap<String, String> params){
		getSqlSession().update("lecture.lectureMovieFileDelete", params);
	}


	public void lectureMovieMemoInsert(HashMap<String, String> params){
		getSqlSession().insert("lecture.lectureMovieMemoInsert", params);
	}

	public void lectureMovieMemoUpdate(HashMap<String, String> params){
		getSqlSession().update("lecture.lectureMovieMemoUpdate", params);
	}

	public void lectureMovieMemoDelete(HashMap<String, String> params){
		getSqlSession().delete("lecture.lectureMovieMemoDelete", params);
	}

	public int lectureDeleteCheck(HashMap<String, String> params){
		return getSqlSession().selectOne("lecture.lectureDeleteCheck", params);
	}




	public List<HashMap<String, String>> playinfo(HashMap<String, String> params){
		return getSqlSession().selectList("lecture.playinfo", params);
	}

	public List<HashMap<String, String>> getCbMovie4_free_admin(HashMap<String, String> params){
		return getSqlSession().selectList("lecture.getCbMovie4_free_admin", params);
	}

	public int getCbMovie4_free_admin_count(HashMap<String, String> params){
		return getSqlSession().selectOne("lecture.getCbMovie4_free_admin_count", params);
	}

	public HashMap<String, String> lectureOnDetailS(HashMap<String, String> params) {
		return getSqlSession().selectOne("lecture.lectureOnDetailS", params);
	}

	public void insertPmpDownLog(HashMap<String, String> params){
		getSqlSession().insert("lecture.insertPmpDownLog", params);
	}

	public List<HashMap<String, String>> lectureWMV(HashMap<String, String> params){
		return getSqlSession().selectList("lecture.lectureWMV", params);
	}

	public List<HashMap<String, String>> lectureDown_Count(HashMap<String, String> params){
		return getSqlSession().selectList("lecture.lectureDown_Count", params);
	}
	
	public void oldFreeToNewFreeInsert(HashMap<String, String> params){
		getSqlSession().insert("lecture.oldFreeToNewFreeInsert", params);
	}
	public void oldBogangFreeToNewFreeBogangInsert(HashMap<String, String> params){
		getSqlSession().insert("lecture.oldBogangFreeToNewFreeBogangInsert", params);
	}
	
	public String getRleccode(HashMap<String, String> params) {
		return getSqlSession().selectOne("lecture.getRleccode", params);
	}
	
	public void oldTbmovieToNewTbmovieInsert(HashMap<String, String> params){
		getSqlSession().insert("lecture.oldTbmovieToNewTbmovieInsert", params);
	}
	
	public List<HashMap<String, String>> bookView(HashMap<String, String> params){
        return getSqlSession().selectList("lecture.bookView", params);
    }
}
