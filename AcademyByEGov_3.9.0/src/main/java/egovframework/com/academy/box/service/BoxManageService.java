package egovframework.com.academy.box.service;

import java.util.List;

public interface BoxManageService {
	
	/**
	 * 시험 목록을 조회한다.
	 * @param searchVO
	 * @return List - 시험 목록
	 */
	public List<?> selectExamList(BoxVO ExamVO) throws Exception;

	/**
	 * 시험 총 갯수를 조회한다.
	 * @param searchVO
	 * @return int - 시험 카운트 수
	 */
	int selectExamListTotCnt(BoxVO ExamVO) throws Exception;
	
	/**
	 * 시험 상세정보를 조회한다.
	 * @param ExamMst
	 * @return ExamMst
	 */
	public BoxVO selectExamDetail(BoxVO ExamVO) throws Exception;

	/**
	 * 시험정보를 신규로 등록한다.
	 * @param examMst
	 */
	void insertExam(BoxVO ExamVO) throws Exception;

	/**
	 * 시험정보를 신규로 등록한다.
	 * @param ExamVO
	 */
	public int insertExamRetcd(BoxVO ExamVO) throws Exception;

	/**
	 * 기 등록된 시험정보를 수정한다.
	 * @param ExamVO
	 */
	void updateExam(BoxVO ExamVO) throws Exception;
	
	/**
	 * 과목 목록을 조회한다.
	 * @param examSearchVO
	 * @return List - 과목 목록
	 */
	public List<BoxVO> selectSubjectList(BoxVO ExamVO) throws Exception;

	/**
	 * 과목 총 갯수를 조회한다.
	 * @param examSearchVO
	 * @return int - 과목 카운트 수
	 */
	public int selectSubjectListTotCnt(BoxVO ExamVO) throws Exception;
	
	/**
	 * 과목 상세정보를 조회한다.
	 * @param ExamVO
	 * @return ExamVO
	 */
	public BoxVO selectSubjectDetail(BoxVO ExamVO) throws Exception;

	/**
	 * 과목정보를 신규로 등록한다.
	 * @param ExamVO
	 */
	public void insertSubject(BoxVO ExamVO) throws Exception;

	/**
	 * 기 등록된 과목정보를 수정한다.
	 * @param ExamVO
	 */
	public void updateSubject(BoxVO ExamVO) throws Exception;

}
