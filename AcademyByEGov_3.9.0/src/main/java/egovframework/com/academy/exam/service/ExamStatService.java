package egovframework.com.academy.exam.service;

import java.util.List;

public interface ExamStatService {

	/**
	 * 시험 응시자 목록을 조회한다.
	 * @param ExamVO
	 * @return List - 시험 목록
	 */
	List<?> selectExamStatSbjList(ExamVO ExamVO) throws Exception;

}
