package web.dday.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;

@Repository
public class DdayDAO extends EgovComAbstractDAO {

	public List<HashMap<String, String>> categoryList(HashMap<String, String> params) {
		return getSqlSession().selectList("dday.categoryList", params);
	}

	public List<HashMap<String, String>> list(HashMap<String, String> params) {
		return getSqlSession().selectList("dday.list", params);
	}

	public HashMap<String, String> view(HashMap<String, String> params) {
		return getSqlSession().selectOne("dday.view", params);
	}

	public int listCount(HashMap<String, String> params) {
		return getSqlSession().selectOne("dday.listCount", params);
	}

	public void insert(HashMap<String, String> params) {
		getSqlSession().insert("dday.insert", params);
	}

	public void update(HashMap<String, String> params) {
		getSqlSession().update("dday.update", params);
	}

	public void delete(HashMap<String, String> params) {
		getSqlSession().delete("dday.delete", params);
	}

}
