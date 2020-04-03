package com.willbes.web.lecture.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.willbes.cmm.service.impl.CmmAbstractMapper;

@Repository
public class OpenLectureDAO extends CmmAbstractMapper {

	public List<HashMap<String, String>> openlectureList(HashMap<String, String> params){
		return getSqlSession().selectList("openlecture.openlectureList", params);
	}

	public int openlectureListCount(HashMap<String, String> params){
		return getSqlSession().selectOne("openlecture.openlectureListCount", params);
	}

	public List<HashMap<String, String>> bookList(HashMap<String, String> params){
		return getSqlSession().selectList("openlecture.bookList", params);
	}

	public int bookListCount(HashMap<String, String> params){
		return getSqlSession().selectOne("openlecture.bookListCount", params);
	}

	public List<HashMap<String, String>> getBridgeLeccodeSeq(HashMap<String, String> params){
		return getSqlSession().selectList("openlecture.getBridgeLeccodeSeq", params);
	}

	public List<HashMap<String, String>> getJongLeccodeSeq(HashMap<String, String> params){
		return getSqlSession().selectList("openlecture.getJongLeccodeSeq", params);
	}

	public List<HashMap<String, String>> getopenLeccode(HashMap<String, String> params){
		return getSqlSession().selectList("openlecture.getopenLeccode", params);
	}

	public List<HashMap<String, String>> getBridgeLeccode(HashMap<String, String> params){
		return getSqlSession().selectList("openlecture.getBridgeLeccode", params);
	}

	public List<HashMap<String, String>> BridgeLeccode(HashMap<String, String> params){
		return getSqlSession().selectList("openlecture.BridgeLeccode", params);
	}

	public void openlectureInsert(HashMap<String, String> params){
		getSqlSession().insert("openlecture.openlectureInsert", params);
	}

	public void openlectureBridgeInsert(HashMap<String, String> params){
		getSqlSession().insert("openlecture.openlectureBridgeInsert", params);
	}

	public void openlectureBookInsert(HashMap<String, String> params){
		getSqlSession().insert("openlecture.openlectureBookInsert", params);
	}

	public void openlectureBookInsert2(HashMap<String, String> params){
		getSqlSession().insert("openlecture.openlectureBookInsert2", params);
	}

	public List<HashMap<String, String>> openlectureViewList(HashMap<String, String> params){
		return getSqlSession().selectList("openlecture.openlectureViewList", params);
	}

	public List<HashMap<String, String>> openlectureView(HashMap<String, String> params){
		return getSqlSession().selectList("openlecture.openlectureView", params);
	}

	public List<HashMap<String, String>> openlectureViewBookList(HashMap<String, String> params){
		return getSqlSession().selectList("openlecture.openlectureViewBookList", params);
	}

	public void openlectureBookDelete(HashMap<String, String> params){
		getSqlSession().delete("openlecture.openlectureBookDelete", params);
	}

	public void openlectureUpdate(HashMap<String, String> params){
		getSqlSession().update("openlecture.openlectureUpdate", params);
	}

	public void openlectureIsUseUpdate(HashMap<String, String> params){
		getSqlSession().delete("openlecture.openlectureIsUseUpdate", params);
	}

	public void Modify_Lecture_On_Off(HashMap<String, String> params){
		getSqlSession().delete("openlecture.Modify_Lecture_On_Off", params);
	}

	public void openlectureDelete(HashMap<String, String> params){
		getSqlSession().delete("openlecture.openlectureDelete", params);
	}

	public void openlectureBridgeDelete(HashMap<String, String> params){
		getSqlSession().delete("openlecture.openlectureBridgeDelete", params);
	}

	public void lecMovUpdate(HashMap<String, String> params){
		getSqlSession().delete("openlecture.lecMovUpdate", params);
	}

	public List<HashMap<String, String>> openlectureSeqList(HashMap<String, String> params){
		return getSqlSession().selectList("openlecture.openlectureSeqList", params);
	}

	public void openlectureSeqUpdate(HashMap<String, String> params){
		getSqlSession().update("openlecture.openlectureSeqUpdate", params);
	}

	public List<HashMap<String, String>> openlectureViewJongList(HashMap<String, String> params){
		return getSqlSession().selectList("openlecture.openlectureViewJongList", params);
	}

	public List<HashMap<String, String>> openlectureJongList(HashMap<String, String> params){
		return getSqlSession().selectList("openlecture.openlectureJongList", params);
	}

	public int openlectureJongListCount(HashMap<String, String> params){
		return getSqlSession().selectOne("openlecture.openlectureJongListCount", params);
	}

	public List<HashMap<String, String>> openlectureJongView(HashMap<String, String> params){
		return getSqlSession().selectList("openlecture.openlectureJongView", params);
	}

	public List<HashMap<String, String>> openlectureJongSubjectList(HashMap<String, String> params){
		return getSqlSession().selectList("openlecture.openlectureJongSubjectList", params);
	}

	public int openlectureJongSubjectListCount(HashMap<String, String> params){
		return getSqlSession().selectOne("openlecture.openlectureJongSubjectListCount", params);
	}

	public void openlectureLecJongInsert(HashMap<String, String> params){
		getSqlSession().insert("openlecture.openlectureLecJongInsert", params);
	}

	public void openlectureChoiceJongNoInsert(HashMap<String, String> params){
		getSqlSession().insert("openlecture.openlectureChoiceJongNoInsert", params);
	}

	public List<HashMap<String, String>> openlectureViewLeccodeList(HashMap<String, String> params){
		return getSqlSession().selectList("openlecture.openlectureViewLeccodeList", params);
	}

	public void openlectureLecJongDelete(HashMap<String, String> params){
		getSqlSession().delete("openlecture.openlectureLecJongDelete", params);
	}

	public void openlectureChoiceJongNoDelete(HashMap<String, String> params){
		getSqlSession().delete("openlecture.openlectureChoiceJongNoDelete", params);
	}

	public List<HashMap<String, String>> openlecturePayList(HashMap<String, String> params){
		return getSqlSession().selectList("openlecture.openlecturePayList", params);
	}

	public List<HashMap<String, String>> openlectureJongPayList(HashMap<String, String> params){
		return getSqlSession().selectList("openlecture.openlectureJongPayList", params);
	}

	public List<HashMap<String, String>> openlectureDataMemoViewList(HashMap<String, String> params){
		return getSqlSession().selectList("openlecture.openlectureDataMemoViewList", params);
	}

	public List<HashMap<String, String>> openlectureDataViewList(HashMap<String, String> params){
		return getSqlSession().selectList("openlecture.openlectureDataViewList", params);
	}

	public List<HashMap<String, String>> openlectureDataMovieViewList(HashMap<String, String> params){
		return getSqlSession().selectList("openlecture.openlectureDataMovieViewList", params);
	}


	public List<HashMap<String, String>> openlectureDataMovieList(HashMap<String, String> params){
		return getSqlSession().selectList("openlecture.openlectureDataMovieList", params);
	}

	public void openlectureMovieInsert(HashMap<String, String> params){
		getSqlSession().insert("openlecture.openlectureMovieInsert", params);
	}

	public void openlectureMovieDelete(HashMap<String, String> params){
		getSqlSession().delete("openlecture.openlectureMovieDelete", params);
	}

	public void openlectureMovieUpdate(HashMap<String, String> params){
		getSqlSession().update("openlecture.openlectureMovieUpdate", params);
	}

	public void openlectureMovieFileDelete(HashMap<String, String> params){
		getSqlSession().update("openlecture.openlectureMovieFileDelete", params);
	}


	public void openlectureMovieMemoInsert(HashMap<String, String> params){
		getSqlSession().insert("openlecture.openlectureMovieMemoInsert", params);
	}

	public void openlectureMovieMemoUpdate(HashMap<String, String> params){
		getSqlSession().update("openlecture.openlectureMovieMemoUpdate", params);
	}

	public void openlectureMovieMemoDelete(HashMap<String, String> params){
		getSqlSession().delete("openlecture.openlectureMovieMemoDelete", params);
	}

	public int openlectureDeleteCheck(HashMap<String, String> params){
		return getSqlSession().selectOne("openlecture.openlectureDeleteCheck", params);
	}




	public List<HashMap<String, String>> playinfo(HashMap<String, String> params){
		return getSqlSession().selectList("openlecture.playinfo", params);
	}

	public List<HashMap<String, String>> getCbMovie4_free_admin(HashMap<String, String> params){
		return getSqlSession().selectList("openlecture.getCbMovie4_free_admin", params);
	}

	public int getCbMovie4_free_admin_count(HashMap<String, String> params){
		return getSqlSession().selectOne("openlecture.getCbMovie4_free_admin_count", params);
	}

	public HashMap<String, String> openlectureOnDetailS(HashMap<String, String> params) {
		return getSqlSession().selectOne("openlecture.openlectureOnDetailS", params);
	}

	public void insertPmpDownLog(HashMap<String, String> params){
		getSqlSession().insert("openlecture.insertPmpDownLog", params);
	}

	public List<HashMap<String, String>> openlectureWMV(HashMap<String, String> params){
		return getSqlSession().selectList("openlecture.openlectureWMV", params);
	}

	public List<HashMap<String, String>> openlectureDown_Count(HashMap<String, String> params){
		return getSqlSession().selectList("openlecture.openlectureDown_Count", params);
	}
}
