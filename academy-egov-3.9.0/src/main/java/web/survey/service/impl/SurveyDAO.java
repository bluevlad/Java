package web.survey.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;

@Repository
public class SurveyDAO extends EgovComAbstractDAO {

	public List<HashMap<String, String>> bankList(HashMap<String, String> params){
		return getSqlSession().selectList("survey.bankList", params);
	}
	public int bankListCount(HashMap<String, String> params){
		return getSqlSession().selectOne("survey.bankListCount", params);
	}
	public HashMap<String, String> bankView(HashMap<String, String> params){
		return getSqlSession().selectOne("survey.bankView", params);
	}
	public void bankInsert(HashMap<String, String> params){
		getSqlSession().update("survey.bankInsert", params);
	}
	public void bankUpdate(HashMap<String, String> params){
		getSqlSession().update("survey.bankUpdate", params);
	}
	public void bankDelete(HashMap<String, String> params){
		getSqlSession().delete("survey.bankDelete", params);
	}

	public List<HashMap<String, String>> surveySetList(HashMap<String, String> params){
		return getSqlSession().selectList("survey.surveySetList", params);
	}
	public int surveySetListCount(HashMap<String, String> params){
		return getSqlSession().selectOne("survey.surveySetListCount", params);
	}
	public HashMap<String, String> surveySetView(HashMap<String, String> params){
		return getSqlSession().selectOne("survey.surveySetView", params);
	}
	public void surveySetInsert(HashMap<String, String> params){
		getSqlSession().update("survey.surveySetInsert", params);
	}
	public void surveySetUpdate(HashMap<String, String> params){
		getSqlSession().update("survey.surveySetUpdate", params);
	}
	public void surveySetDelete(HashMap<String, String> params){
		getSqlSession().delete("survey.surveySetDelete", params);
	}

	public List<HashMap<String, String>> surveySetItemList(HashMap<String, String> params){
		return getSqlSession().selectList("survey.surveySetItemList", params);
	}
	public void surveySetItemInsert(HashMap<String, String> params){
		getSqlSession().update("survey.surveySetItemInsert", params);
	}
	public void surveySetItemUpdate(HashMap<String, String> params){
		getSqlSession().update("survey.surveySetItemUpdate", params);
	}
	public void surveySetItemDelete(HashMap<String, String> params){
		getSqlSession().update("survey.surveySetItemDelete", params);
	}

	public List<HashMap<String, String>> surveyList(HashMap<String, String> params){
		return getSqlSession().selectList("survey.surveyList", params);
	}
	public int surveyListCount(HashMap<String, String> params){
		return getSqlSession().selectOne("survey.surveyListCount", params);
	}
	public HashMap<String, String> surveyView(HashMap<String, String> params){
		return getSqlSession().selectOne("survey.surveyView", params);
	}
	public void surveyInsert(HashMap<String, String> params){
		getSqlSession().update("survey.surveyInsert", params);
	}
	public void surveyUpdate(HashMap<String, String> params){
		getSqlSession().update("survey.surveyUpdate", params);
	}
	public void surveyMerge(HashMap<String, String> params){
		getSqlSession().update("survey.surveyMerge", params);
	}
	public void surveyDelete(HashMap<String, String> params){
		getSqlSession().update("survey.surveyDelete", params);
	}

	public List<HashMap<String, String>> surveyRstList(HashMap<String, String> params){
		return getSqlSession().selectList("survey.surveyRstList", params);
	}
	public List<HashMap<String, String>> answerList(HashMap<String, String> params){
		return getSqlSession().selectList("survey.answerList", params);
	}
}
