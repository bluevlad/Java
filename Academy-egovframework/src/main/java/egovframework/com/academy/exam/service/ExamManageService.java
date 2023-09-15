package egovframework.com.academy.exam.service;

import java.util.List;

public interface ExamManageService {
	
	/**
	 * 시험 목록을 조회한다.
	 * @param searchVO
	 * @return List - 시험 목록
	 */
	public List<?> selectExamList(ExamVO ExamVO) throws Exception;

	/**
	 * 시험 총 갯수를 조회한다.
	 * @param searchVO
	 * @return int - 시험 카운트 수
	 */
	int selectExamListTotCnt(ExamVO ExamVO) throws Exception;
	
	/**
	 * 시험 상세정보를 조회한다.
	 * @param ExamMst
	 * @return ExamMst
	 */
	public ExamVO selectExamDetail(ExamVO ExamVO) throws Exception;

	/**
	 * 시험정보를 신규로 등록한다.
	 * @param examMst
	 */
	void insertExam(ExamVO ExamVO) throws Exception;

	/**
	 * 시험정보를 신규로 등록한다.
	 * @param ExamVO
	 */
	public int insertExamRetcd(ExamVO ExamVO) throws Exception;

	/**
	 * 기 등록된 시험정보를 수정한다.
	 * @param ExamVO
	 */
	void updateExam(ExamVO ExamVO) throws Exception;
	
	/**
	 * 과목 목록을 조회한다.
	 * @param examSearchVO
	 * @return List - 과목 목록
	 */
	public List<ExamVO> selectSubjectList(ExamVO ExamVO) throws Exception;

	/**
	 * 과목 총 갯수를 조회한다.
	 * @param examSearchVO
	 * @return int - 과목 카운트 수
	 */
	public int selectSubjectListTotCnt(ExamVO ExamVO) throws Exception;
	
	/**
	 * 과목 상세정보를 조회한다.
	 * @param ExamVO
	 * @return ExamVO
	 */
	public ExamVO selectSubjectDetail(ExamVO ExamVO) throws Exception;

	/**
	 * 과목정보를 신규로 등록한다.
	 * @param ExamVO
	 */
	public void insertSubject(ExamVO ExamVO) throws Exception;

	/**
	 * 기 등록된 과목정보를 수정한다.
	 * @param ExamVO
	 */
	public void updateSubject(ExamVO ExamVO) throws Exception;

}
