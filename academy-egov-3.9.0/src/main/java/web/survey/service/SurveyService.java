package com.willbes.web.survey.service;

import java.util.HashMap;
import java.util.List;

public interface SurveyService {

	List<HashMap<String, String>> bankList(HashMap<String, String> params);
	int bankListCount(HashMap<String, String> params);
	HashMap<String, String> bankView(HashMap<String, String> params);
	void bankInsert(HashMap<String, String> params);
	void bankUpdate(HashMap<String, String> params);
	void bankDelete(HashMap<String, String> params);

	List<HashMap<String, String>> surveySetList(HashMap<String, String> params);
	int surveySetListCount(HashMap<String, String> params);
	HashMap<String, String> surveySetView(HashMap<String, String> params);
	void surveySetInsert(HashMap<String, String> params);
	void surveySetUpdate(HashMap<String, String> params);
	void surveySetDelete(HashMap<String, String> params);

	List<HashMap<String, String>> surveySetItemList(HashMap<String, String> params);
	void surveySetItemInsert(HashMap<String, String> params);
	void surveySetItemUpdate(HashMap<String, String> params);
	void surveySetItemDelete(HashMap<String, String> params);

	List<HashMap<String, String>> surveyList(HashMap<String, String> params);
	int surveyListCount(HashMap<String, String> params);
	HashMap<String, String> surveyView(HashMap<String, String> params);
	void surveyInsert(HashMap<String, String> params);
	void surveyUpdate(HashMap<String, String> params);
	void surveyMerge(HashMap<String, String> params);
	void surveyDelete(HashMap<String, String> params);

	List<HashMap<String, String>> surveyRstList(HashMap<String, String> params);
	List<HashMap<String, String>> answerList(HashMap<String, String> params);
	
}
