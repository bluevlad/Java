package egovframework.com.academy.exam.service;

import java.util.List;

public interface ExamSetManageService {
	
	/**
	 * 시험문제 목록을 조회한다.
	 * @param searchVO
	 * @return List - 시험문제 목록
	 */
	public List<?> selectExamSetList(ExamVO ExamVO) throws Exception;

	/**
	 * 시험문제 총 갯수를 조회한다.
	 * @param searchVO
	 * @return int - 시험 카운트 수
	 */
	int selectExamSetListTotCnt(ExamVO ExamVO) throws Exception;
	
	/**
	 * 시험문제 상세정보를 조회한다.
	 * @param ExamVO
	 * @return ExamVO
	 */
	public ExamVO selectExamSetDetail(ExamVO ExamVO) throws Exception;

	/**
	 * 시험문제정보를 신규로 등록한다.
	 * @param ExamVO
	 */
	void insertExamSet(ExamVO ExamVO) throws Exception;

	/**
	 * 기 등록된 시험문제정보를 수정한다.
	 * @param ExamVO
	 */
	void updateExamSet(ExamVO ExamVO) throws Exception;
	
	/**
	 * 설문세트 문항 목록을 조회한다.
	 * @param searchVO
	 * @return List - 세트문항목록
	 */
	public List<?> selectExamSetItemList(ExamVO ExamVO) throws Exception;

	/**
	 * 시험문제정보를 신규로 등록한다.
	 * @param ExamVO
	 */
	void insertExamSetItem(ExamVO ExamVO) throws Exception;

	/**
	 * 기 등록된 시험문제정보를 수정한다.
	 * @param ExamVO
	 */
	void deleteExamSetItem(ExamVO ExamVO) throws Exception;

	/**
	 * 시험문제 총 갯수를 조회한다.
	 * @param ExamVO
	 * @return int - 시험문제 세트 문제 순서
	 */
	int selectExamSetQueSeq(ExamVO ExamVO) throws Exception;
	
}
