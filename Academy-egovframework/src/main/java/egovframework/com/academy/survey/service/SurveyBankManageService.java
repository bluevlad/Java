package egovframework.com.academy.survey.service;

import java.util.List;

public interface SurveyBankManageService {
	
	/**
	 * 시험문제 목록을 조회한다.
	 * @param searchVO
	 * @return List - 시험문제 목록
	 */
	public List<?> selectSurveyBankList(SurveyVO SurveyVO) throws Exception;

	/**
	 * 시험문제 총 갯수를 조회한다.
	 * @param searchVO
	 * @return int - 시험 카운트 수
	 */
	int selectSurveyBankListTotCnt(SurveyVO SurveyVO) throws Exception;
	
	/**
	 * 시험문제 상세정보를 조회한다.
	 * @param ExamVO
	 * @return ExamVO
	 */
	public SurveyVO selectSurveyBankDetail(SurveyVO SurveyVO) throws Exception;

	/**
	 * 시험문제정보를 신규로 등록한다.
	 * @param ExamVO
	 */
	void insertSurveyBank(SurveyVO SurveyVO) throws Exception;

	/**
	 * 기 등록된 시험문제정보를 수정한다.
	 * @param ExamVO
	 */
	void updateSurveyBank(SurveyVO SurveyVO) throws Exception;
	
}
