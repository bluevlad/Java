package egovframework.com.academy.exam.service;

public interface ExamPassService {

	
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
