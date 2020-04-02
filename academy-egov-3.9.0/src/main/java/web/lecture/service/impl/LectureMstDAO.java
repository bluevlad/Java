package web.lecture.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;

@Repository
public class LectureMstDAO extends EgovComAbstractDAO {

	public List<HashMap<String, String>> lecturemstList(HashMap<String, String> params){
		return getSqlSession().selectList("lecturemst.lecturemstList", params);
	}

	public int lecturemstListCount(HashMap<String, String> params){
		return getSqlSession().selectOne("lecturemst.lecturemstListCount", params);
	}
	
	public List<HashMap<String, String>> getBridgeMstcodeSeq(HashMap<String, String> params){
		return getSqlSession().selectList("lecturemst.getBridgeMstcodeSeq", params);
	}
	
	public List<HashMap<String, String>> getBridgeMstcode(HashMap<String, String> params){
		return getSqlSession().selectList("lecturemst.getBridgeMstcode", params);
	}
	
	public List<HashMap<String, String>> getMstcode(HashMap<String, String> params){
		return getSqlSession().selectList("lecturemst.getMstcode", params);
	}
	
	public void lecturemstInsert(HashMap<String, String> params){
		getSqlSession().insert("lecturemst.lecturemstInsert", params);
	}
	
	public void lectureBridgeInsert(HashMap<String, String> params){
		getSqlSession().insert("lecturemst.lectureBridgeInsert", params);
	}

	public void lectureBookInsert2(HashMap<String, String> params){
		getSqlSession().insert("lecturemst.lectureBookInsert2", params);
	}
	
	public List<HashMap<String, String>> lectureDataMemoViewList(HashMap<String, String> params){
		return getSqlSession().selectList("lecturemst.lectureDataMemoViewList", params);
	}

	public List<HashMap<String, String>> lectureDataViewList(HashMap<String, String> params){
		return getSqlSession().selectList("lecturemst.lectureDataViewList", params);
	}
	
	public List<HashMap<String, String>> lectureWMV(HashMap<String, String> params){
		return getSqlSession().selectList("lecturemst.lectureWMV", params);
	}

	public List<HashMap<String, String>> lectureDown_Count(HashMap<String, String> params){
		return getSqlSession().selectList("lecturemst.lectureDown_Count", params);
	}
	
	public int lectureDeleteCheck(HashMap<String, String> params){
		return getSqlSession().selectOne("lecturemst.lectureDeleteCheck", params);
	}
	
	public List<HashMap<String, String>> lectureView(HashMap<String, String> params){
		return getSqlSession().selectList("lecturemst.lectureView", params);
	}

	public List<HashMap<String, String>> lectureViewBookList(HashMap<String, String> params){
		return getSqlSession().selectList("lecturemst.lectureViewBookList", params);
	}
	
	public List<HashMap<String, String>> lectureViewList(HashMap<String, String> params){
		return getSqlSession().selectList("lecturemst.lectureViewList", params);
	}
	
	public void lectureBookDelete(HashMap<String, String> params){
		getSqlSession().delete("lecturemst.lectureBookDelete", params);
	}
	
	public void lecturemstUpdate(HashMap<String, String> params){
		getSqlSession().update("lecturemst.lecturemstUpdate", params);
	}
	
	public void lectureBookInsert(HashMap<String, String> params){
		getSqlSession().insert("lecturemst.lectureBookInsert", params);
	}
	
	public void lectureDelete(HashMap<String, String> params){
		getSqlSession().delete("lecturemst.lectureDelete", params);
	}

	public void lectureBridgeDelete(HashMap<String, String> params){
		getSqlSession().delete("lecturemst.lectureBridgeDelete", params);
	}

	
}
