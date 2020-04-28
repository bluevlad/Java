package egovframework.com.academy.exam.service;

import java.util.List;

public interface ExamRstService {

	/**
	 * 시험 응시자 목록을 조회한다.
	 * @param ExamVO
	 * @return List - 시험 목록
	 */
	List<?> selectExamRstList(ExamVO ExamVO) throws Exception;

	/**
	 * 시험 응시자 총 갯수를 조회한다.
	 * @param ExamVO
	 * @return int - 시험 카운트 수
	 */
	int selectExamRstListTotCnt(ExamVO ExamVO) throws Exception;
	
	/**
	 * 시험 응시자 상세정보를 조회한다.
     * @param examCd 상세조회대상 시험코드
     * @param userId 상세조회대상 사용자아이디
	 * @return ExamVO
	 */
	ExamVO selectExamRstDetail(ExamVO ExamVO) throws Exception;
	
	/**
	 * 시험응시자정보를 신규로 등록한다.
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
	 * @param ExamVO
	 */
	void insertExamRstDet(ExamVO ExamVO) throws Exception;

	/**
	 * 기 등록된 시험정보를 수정한다.
	 * @param ExamVO
	 */
	void deleteExamRstDet(ExamVO ExamVO) throws Exception;

}
