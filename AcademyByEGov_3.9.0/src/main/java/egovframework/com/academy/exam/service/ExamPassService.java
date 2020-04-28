package egovframework.com.academy.exam.service;

import java.util.List;

public interface ExamPassService {

	/**
	 * 시험 목록을 조회한다.
	 * @param searchVO
	 * @return List - 시험 목록
	 */
	List<?> selectExamRstList(ExamVO ExamVO) throws Exception;

	/**
	 * 시험 총 갯수를 조회한다.
	 * @param searchVO
	 * @return int - 시험 카운트 수
	 */
	int selectExamRstListTotCnt(ExamVO ExamVO) throws Exception;
	
	/**
	 * 시험정보를 신규로 등록한다.
	 * @param examMst
	 */
	void insertExamRst(ExamVO ExamVO) throws Exception;

	/**
	 * 기 등록된 시험정보를 수정한다.
	 * @param ExamVO
	 */
	void deleteExamRst(ExamVO ExamVO) throws Exception;
	
	/**
	 * 시험정보를 신규로 등록한다.
	 * @param examMst
	 */
	void insertExamRstDet(ExamVO ExamVO) throws Exception;

	/**
	 * 기 등록된 시험정보를 수정한다.
	 * @param ExamVO
	 */
	void deleteExamRstDet(ExamVO ExamVO) throws Exception;

}
