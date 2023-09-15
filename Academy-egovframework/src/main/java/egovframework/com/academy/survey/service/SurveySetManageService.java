package egovframework.com.academy.survey.service;

import java.util.List;

public interface SurveySetManageService {
	
	/**
	 * 시험문제 목록을 조회한다.
	 * @param searchVO
	 * @return List - 시험문제 목록
	 */
	public List<?> selectSurveySetList(SurveyVO SurveyVO) throws Exception;

	/**
	 * 시험문제 총 갯수를 조회한다.
	 * @param searchVO
	 * @return int - 시험 카운트 수
	 */
	int selectSurveySetListTotCnt(SurveyVO SurveyVO) throws Exception;
	
	/**
	 * 시험문제 상세정보를 조회한다.
	 * @param ExamVO
	 * @return ExamVO
	 */
	public SurveyVO selectSurveySetDetail(SurveyVO SurveyVO) throws Exception;

	/**
	 * 시험문제정보를 신규로 등록한다.
	 * @param ExamVO
	 */
	void insertSurveySet(SurveyVO SurveyVO) throws Exception;

	/**
	 * 기 등록된 시험문제정보를 수정한다.
	 * @param ExamVO
	 */
	void updateSurveySet(SurveyVO SurveyVO) throws Exception;
	
	/**
	 * 설문세트 문항 목록을 조회한다.
	 * @param searchVO
	 * @return List - 세트문항목록
	 */
	public List<?> selectSurveySetItemList(SurveyVO SurveyVO) throws Exception;

	/**
	 * 시험문제정보를 신규로 등록한다.
	 * @param ExamVO
	 */
	void insertSurveySetItem(SurveyVO SurveyVO) throws Exception;

	/**
	 * 기 등록된 시험문제정보를 수정한다.
	 * @param ExamVO
	 */
	void deleteSurveySetItem(SurveyVO SurveyVO) throws Exception;
	
}
