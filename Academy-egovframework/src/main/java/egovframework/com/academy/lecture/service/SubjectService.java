package egovframework.com.academy.lecture.service;

import java.util.List;

public interface SubjectService {

	/**
	 * 과목정보 목록을 조회한다.
	 * @param LectureVO
	 * @return List - 시험 목록
	 */
	List<?> selectSubjectList(LectureVO LectureVO) throws Exception;

	/**
	 * 과목정보 총 갯수를 조회한다.
	 * @param LectureVO
	 * @return int - 과목 카운트 수
	 */
	int selectSubjectListTotCnt(LectureVO LectureVO) throws Exception;
	
	/**
	 * 시험문제 상세정보를 조회한다.
	 * @param LectureVO
	 * @return LectureVO
	 */
	public LectureVO selectSubjectDetail(LectureVO LectureVO) throws Exception;

	/**
	 * 시험문제정보를 신규로 등록한다.
	 * @param ExamVO
	 */
	void insertSubject(LectureVO LectureVO) throws Exception;

	/**
	 * 기 등록된 시험문제정보를 수정한다.
	 * @param ExamVO
	 */
	void updateSubject(LectureVO LectureVO) throws Exception;

	/**
	 * 기 등록된 시험문제정보를 수정한다.
	 * @param ExamVO
	 */
	void deleteSubject(LectureVO LectureVO) throws Exception;

}