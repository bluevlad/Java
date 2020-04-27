package egovframework.com.academy.exam.service;

import java.util.List;

public interface ExamPassManageService {

	
	/**
	 * 시험 목록을 조회한다.
	 * @param searchVO
	 * @return List - 시험 목록
	 */
	List<?> selectExamPassList(ExamVO ExamVO) throws Exception;

	/**
	 * 시험 총 갯수를 조회한다.
	 * @param searchVO
	 * @return int - 시험 카운트 수
	 */
	int selectExamPassListTotCnt(ExamVO ExamVO) throws Exception;
	
	/**
	 * 시험 상세정보를 조회한다.
	 * @param ExamMst
	 * @return ExamMst
	 */
	List<ExamVO> selectExamPassDetail(ExamVO ExamVO) throws Exception;

	/**
	 * 시험정보를 신규로 등록한다.
	 * @param examMst
	 */
	void insertExamPass(ExamVO ExamVO) throws Exception;

	/**
	 * 기 등록된 시험정보를 수정한다.
	 * @param ExamVO
	 */
	void deleteExamPass(ExamVO ExamVO) throws Exception;

}
