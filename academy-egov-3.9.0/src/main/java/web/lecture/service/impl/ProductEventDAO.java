package web.lecture.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;

@Repository
public class ProductEventDAO extends EgovComAbstractDAO {

	public List<HashMap<String, String>> list(HashMap<String, String> params){
		return getSqlSession().selectList("ProductEvent.list", params);
	}

	public int listCount(HashMap<String, String> params){
		return getSqlSession().selectOne("ProductEvent.listCount", params);
	}

	public HashMap<String, String> getOne(HashMap<String, String> params){
		return getSqlSession().selectOne("ProductEvent.getOne", params);
	}

	public List<HashMap<String, String>> list_prd(HashMap<String, String> params){
		return getSqlSession().selectList("ProductEvent.list_prd", params);
	}

	public void insert(HashMap<String, String> params){
		getSqlSession().insert("ProductEvent.insert", params);
	}

	public void update(HashMap<String, String> params){
		getSqlSession().update("ProductEvent.update", params);
	}

	public void lec_insert(HashMap<String, String> params){
		getSqlSession().insert("ProductEvent.lec_insert", params);
	}

	public void lec_delete(HashMap<String, String> params){
		getSqlSession().update("ProductEvent.lec_delete", params);
	}

}
