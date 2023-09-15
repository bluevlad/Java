package egovframework.com.academy.exam.service;

import java.util.List;

public interface ExamRequestManageService {

	
	/**
	 * 시험 목록을 조회한다.
	 * @param searchVO
	 * @return List - 시험 목록
	 */
	public List<?> offererList(ExamVO ExamRequestVO) throws Exception;

	/**
	 * 시험 총 갯수를 조회한다.
	 * @param searchVO
	 * @return int - 시험 카운트 수
	 */
	int offererListCount(ExamVO ExamVO) throws Exception;

	ExamVO offererView(ExamVO ExamVO) throws Exception;

    void offererInsert(ExamVO ExamVO) throws Exception;

    void offererUpdate(ExamVO ExamVO) throws Exception;

}
