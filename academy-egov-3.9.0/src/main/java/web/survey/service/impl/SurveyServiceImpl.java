package com.willbes.web.survey.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.willbes.web.survey.service.SurveyService;

@Service(value="SurveyService")
public class SurveyServiceImpl implements SurveyService{
	@Autowired 
	private SurveyDAO surveydao;
	
	@Override
	public List<HashMap<String, String>> bankList(HashMap<String, String> params){
		return surveydao.bankList(params);
	}		
	@Override
	public int bankListCount(HashMap<String, String> params){
		return surveydao.bankListCount(params);
	}
	@Override
	public HashMap<String, String> bankView(HashMap<String, String> params){
		return surveydao.bankView(params);
	}		
	@Override
	public void bankInsert(HashMap<String, String> params){
		surveydao.bankInsert(params);
	}	
	@Override
	public void bankUpdate(HashMap<String, String> params){
		surveydao.bankUpdate(params);
	}	
	@Override
	public void bankDelete(HashMap<String, String> params){
		surveydao.bankDelete(params);
	}	
	
	@Override
	public List<HashMap<String, String>> surveySetList(HashMap<String, String> params){
		return surveydao.surveySetList(params);
	}		
	@Override
	public int surveySetListCount(HashMap<String, String> params){
		return surveydao.surveySetListCount(params);
	}
	@Override
	public HashMap<String, String> surveySetView(HashMap<String, String> params){
		return surveydao.surveySetView(params);
	}		
	@Override
	public void surveySetInsert(HashMap<String, String> params){
		surveydao.surveySetInsert(params);
	}	
	@Override
	public void surveySetUpdate(HashMap<String, String> params){
		surveydao.surveySetUpdate(params);
	}	
	@Override
	public void surveySetDelete(HashMap<String, String> params){
		surveydao.surveySetDelete(params);
	}	
	
	@Override
	public List<HashMap<String, String>> surveySetItemList(HashMap<String,String> params){
		return surveydao.surveySetItemList(params);
	}		
	@Override
	public void surveySetItemInsert(HashMap<String, String> params){
		surveydao.surveySetItemInsert(params);
	}	
	@Override
	public void surveySetItemUpdate(HashMap<String, String> params){
		surveydao.surveySetItemUpdate(params);
	}	
	@Override
	public void surveySetItemDelete(HashMap<String, String> params){
		surveydao.surveySetItemDelete(params);
	}

	@Override
	public List<HashMap<String, String>> surveyList(HashMap<String,String> params){
		return surveydao.surveyList(params);
	}		
	@Override
	public int surveyListCount(HashMap<String, String> params){
		return surveydao.surveyListCount(params);
	}
	@Override
	public HashMap<String, String> surveyView(HashMap<String, String> params){
		return surveydao.surveyView(params);
	}		
	@Override
	public void surveyInsert(HashMap<String, String> params){
		surveydao.surveyInsert(params);
	}
	@Override
	public void surveyUpdate(HashMap<String, String> params){
		surveydao.surveyUpdate(params);
	}
	@Override
	public void surveyMerge(HashMap<String, String> params){
		surveydao.surveyMerge(params);
	}
	@Override
	public void surveyDelete(HashMap<String, String> params){
		surveydao.surveyDelete(params);
	}

	@Override
	public List<HashMap<String, String>> surveyRstList(HashMap<String, String> params){
		return surveydao.surveyRstList(params);
	}		
	@Override
	public List<HashMap<String, String>> answerList(HashMap<String, String> params){
		return surveydao.answerList(params);
	}		
}