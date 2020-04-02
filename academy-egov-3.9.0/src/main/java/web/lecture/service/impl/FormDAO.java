package web.lecture.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;

@Repository
public class FormDAO extends EgovComAbstractDAO {

	public List<HashMap<String, String>> formList(HashMap<String, String> params){
		return getSqlSession().selectList("form.formList", params);
	}

	public int formListCount(HashMap<String, String> params){
		return getSqlSession().selectOne("form.formListCount", params);
	}

	public String formGetCode(){
		return getSqlSession().selectOne("form.formGetCode");
	}

	public void formInsert(HashMap<String, String> params){
		getSqlSession().insert("form.formInsert", params);
	}

	public List<HashMap<String, String>> formView(HashMap<String, String> params){
		return getSqlSession().selectList("form.formView", params);
	}

	public void formUpdate(HashMap<String, String> params){
		getSqlSession().update("form.formUpdate", params);
	}

	public void formDelete(HashMap<String, String> params){
		getSqlSession().delete("form.formDelete", params);
	}

	public int formCheck(HashMap<String, String> params){
		return getSqlSession().selectOne("form.formCheck", params);
	}

	public List<HashMap<String, String>> getCodeList(HashMap<String, String> params){
		return getSqlSession().selectList("form.getCodeList", params);
	}

}
