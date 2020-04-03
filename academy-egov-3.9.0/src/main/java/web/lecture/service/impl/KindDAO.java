package web.lecture.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;

@Repository
public class KindDAO extends EgovComAbstractDAO {

	public List<HashMap<String, String>> getKindList(Object obj){
		return getSqlSession().selectList("kind.getKindList", obj);
	}

	public List<HashMap<String, String>> kindList(HashMap<String, String> params){
		return getSqlSession().selectList("kind.kindList", params);
	}

	public int kindListCount(HashMap<String, String> params){
		return getSqlSession().selectOne("kind.kindListCount", params);
	}

	public void kindInsert(HashMap<String, String> params){
		getSqlSession().insert("kind.kindInsert", params);
	}

	public List<HashMap<String, String>> kindView(HashMap<String, String> params){
		return getSqlSession().selectList("kind.kindView", params);
	}

	public void kindUpdate(HashMap<String, String> params){
		getSqlSession().update("kind.kindUpdate", params);
	}

	public void kindDelete(HashMap<String, String> params){
		getSqlSession().delete("kind.kindDelete", params);
	}

	public int kindCheck(HashMap<String, String> params){
		return getSqlSession().selectOne("kind.kindCheck", params);
	}

	public List<HashMap<String, String>> selectKindCode() {
		return getSqlSession().selectList("kind.selectKindCode");
	}

	public void SeqUpdate(HashMap<String, String> params){
		getSqlSession().update("kind.SeqUpdate", params);
	}
}
