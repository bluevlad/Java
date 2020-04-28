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
	 * @param ExamVO 검색조건
	 * @return List<?> 시시험 응시 과목목록정보
	 * @throws Exception
	 */
	List<?> selectExamRstSbjList(ExamVO ExamVO) throws Exception;

	/**
	 * @param ExamVO 검색조건
	 * @return 시험 응시과목 총갯수
	 */
	int selectExamRstSbjListTotCnt(ExamVO ExamVO) throws Exception;
	
	/**
     * @param examCd 상세조회대상 시험코드
     * @param userId 상세조회대상 사용자아이디
     * @param sbjCd 상세조회대상 과목코드
     * @return ExamVO 시험 과목 선택 정보
	 * @throws Exception
	 */
	ExamVO selectExamRstSbjDetail(ExamVO ExamVO) throws Exception;
	
	/**
	 * 시험 응시과목 정보 등록
	 * @param ExamVO
	 */
	void insertExamRstSbj(ExamVO ExamVO) throws Exception;

	/**
	 * ExamVO 시험정보
	 * @param ExamVO 시험 응시과목 점수 등록
	 */
	void updateExamRstSbj(ExamVO ExamVO) throws Exception;

	/**
	 * 시험 응시과목 정보삭제
	 * @param ExamVO
	 */
	void deleteExamRstSbj(ExamVO ExamVO) throws Exception;

	/**
     * @param examCd 상세조회대상 시험코드
     * @param userId 상세조회대상 사용자아이디
     * @param sbjCd 상세조회대상 과목코드
     * @return List 시시험 응시과목 채점정보
	 * @throws Exception
	 */
	 List<ExamVO> selectExamRstDetList(ExamVO ExamVO) throws Exception;
	
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

	/**
	 * @param ExamVO 검색조건
	 * @return 시험 답안 정답여부
	 */
	String selectExamRstDetYN(ExamVO ExamVO) throws Exception;

}
