package com.willbes.web.lectureOff.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.willbes.cmm.service.impl.CmmAbstractMapper;

@Repository
public class LectureOffDAO extends CmmAbstractMapper {

	public List<HashMap<String, String>> lectureList(HashMap<String, String> params){
		return getSqlSession().selectList("lectureOff.lectureList", params);
	}

	public int lectureListCount(HashMap<String, String> params){
		return getSqlSession().selectOne("lectureOff.lectureListCount", params);
	}

	public List<HashMap<String, String>> bookList(HashMap<String, String> params){
		return getSqlSession().selectList("lectureOff.bookList", params);
	}

	public int bookListCount(HashMap<String, String> params){
		return getSqlSession().selectOne("lectureOff.bookListCount", params);
	}

	public List<HashMap<String, String>> getBridgeLeccodeSeq(HashMap<String, String> params){
		return getSqlSession().selectList("lectureOff.getBridgeLeccodeSeq", params);
	}

	public List<HashMap<String, String>> getJongLeccodeSeq(HashMap<String, String> params){
		return getSqlSession().selectList("lectureOff.getJongLeccodeSeq", params);
	}

	public List<HashMap<String, String>> getLeccode(HashMap<String, String> params){
		return getSqlSession().selectList("lectureOff.getLeccode", params);
	}

	public List<HashMap<String, String>> getBridgeLeccode(HashMap<String, String> params){
		return getSqlSession().selectList("lectureOff.getBridgeLeccode", params);
	}

	public void lectureInsert(HashMap<String, String> params){
		getSqlSession().insert("lectureOff.lectureInsert", params);
	}

	public void lectureBridgeInsert(HashMap<String, String> params){
		getSqlSession().insert("lectureOff.lectureBridgeInsert", params);
	}

	public void lectureBookInsert(HashMap<String, String> params){
		getSqlSession().insert("lectureOff.lectureBookInsert", params);
	}

	public void lectureBookInsert2(HashMap<String, String> params){
		getSqlSession().insert("lectureOff.lectureBookInsert2", params);
	}

	public List<HashMap<String, String>> lectureViewList(HashMap<String, String> params){
		return getSqlSession().selectList("lectureOff.lectureViewList", params);
	}

	public List<HashMap<String, String>> lectureView(HashMap<String, String> params){
		return getSqlSession().selectList("lectureOff.lectureView", params);
	}

	public List<HashMap<String, String>> lectureViewBookList(HashMap<String, String> params){
		return getSqlSession().selectList("lectureOff.lectureViewBookList", params);
	}

	public void lectureBookDelete(HashMap<String, String> params){
		getSqlSession().delete("lectureOff.lectureBookDelete", params);
	}

	public void lectureUpdate(HashMap<String, String> params){
		getSqlSession().update("lectureOff.lectureUpdate", params);
	}

	public void lectureIsUseUpdate(HashMap<String, String> params){
		getSqlSession().delete("lectureOff.lectureIsUseUpdate", params);
	}

	public void lectureDelete(HashMap<String, String> params){
		getSqlSession().delete("lectureOff.lectureDelete", params);
	}

	public void lectureBridgeDelete(HashMap<String, String> params){
		getSqlSession().delete("lectureOff.lectureBridgeDelete", params);
	}

	public List<HashMap<String, String>> lectureSeqList(HashMap<String, String> params){
		return getSqlSession().selectList("lectureOff.lectureSeqList", params);
	}

	public void lectureSeqUpdate(HashMap<String, String> params){
		getSqlSession().update("lectureOff.lectureSeqUpdate", params);
	}

	public List<HashMap<String, String>> lectureViewJongList(HashMap<String, String> params){
		return getSqlSession().selectList("lectureOff.lectureViewJongList", params);
	}

	public List<HashMap<String, String>> lectureJongList(HashMap<String, String> params){
		return getSqlSession().selectList("lectureOff.lectureJongList", params);
	}

	public int lectureJongListCount(HashMap<String, String> params){
		return getSqlSession().selectOne("lectureOff.lectureJongListCount", params);
	}

	public List<HashMap<String, String>> lectureJongSubjectList(HashMap<String, String> params){
		return getSqlSession().selectList("lectureOff.lectureJongSubjectList", params);
	}

	public List<HashMap<String, String>> lectureJongView(HashMap<String, String> params){
		return getSqlSession().selectList("lectureOff.lectureJongView", params);
	}

	public int lectureJongSubjectListCount(HashMap<String, String> params){
		return getSqlSession().selectOne("lectureOff.lectureJongSubjectListCount", params);
	}

	public void lectureLecJongInsert(HashMap<String, String> params){
		getSqlSession().insert("lectureOff.lectureLecJongInsert", params);
	}

	public void lectureChoiceJongNoInsert(HashMap<String, String> params){
		getSqlSession().insert("lectureOff.lectureChoiceJongNoInsert", params);
	}

	public List<HashMap<String, String>> lectureViewLeccodeList(HashMap<String, String> params){
		return getSqlSession().selectList("lectureOff.lectureViewLeccodeList", params);
	}

	public void lectureLecJongDelete(HashMap<String, String> params){
		getSqlSession().delete("lectureOff.lectureLecJongDelete", params);
	}

	public void lectureChoiceJongNoDelete(HashMap<String, String> params){
		getSqlSession().delete("lectureOff.lectureChoiceJongNoDelete", params);
	}

	public List<HashMap<String, String>> lecturePayList(HashMap<String, String> params){
		return getSqlSession().selectList("lectureOff.lecturePayList", params);
	}

	public List<HashMap<String, String>> off_lecturePayList(HashMap<String, String> params){
		return getSqlSession().selectList("lectureOff.off_lecturePayList", params);
	}

	public List<HashMap<String, String>> lectureJongPayList(HashMap<String, String> params){
		return getSqlSession().selectList("lectureOff.lectureJongPayList", params);
	}

	public List<HashMap<String, String>> lectureDataMemoViewList(HashMap<String, String> params){
		return getSqlSession().selectList("lectureOff.lectureDataMemoViewList", params);
	}

	public List<HashMap<String, String>> lectureDataViewList(HashMap<String, String> params){
		return getSqlSession().selectList("lectureOff.lectureDataViewList", params);
	}

	public int lectureDeleteCheck(HashMap<String, String> params){
		return getSqlSession().selectOne("lectureOff.lectureDeleteCheck", params);
	}

	public void lectureOffDayDelete(HashMap<String, String> params){
		getSqlSession().delete("lectureOff.lectureOffDayDelete", params);
	}

	public void lectureOffDayInsert(HashMap<String, String> params){
		getSqlSession().insert("lectureOff.lectureOffDayInsert", params);
	}

	public void lectureOffDayInsert2(HashMap<String, String> params){
		getSqlSession().insert("lectureOff.lectureOffDayInsert2", params);
	}

	public List<HashMap<String, String>> lectureOffDayList(HashMap<String, String> params){
		return getSqlSession().selectList("lectureOff.lectureOffDayList", params);
	}

}
