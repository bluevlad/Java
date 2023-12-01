package egovframework.com.academy.lecture.service;

import java.util.List;

public interface SubjectService {

	/**
	 * 과목정보 목록을 조회한다.
	 * @param LectureVO
	 * @return List - 과목 목록
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
	 * 과목정보를 신규로 등록한다.
	 * @param LectureVO
	 */
	void insertSubject(LectureVO LectureVO) throws Exception;

	/**
	 * 기 등록된 과목정보를 수정한다.
	 * @param LectureVO
	 */
	void updateSubject(LectureVO LectureVO) throws Exception;

	/**
	 * 기 등록된 과목정보를 삭제한다.
	 * @param LectureVO
	 */
	void deleteSubject(LectureVO LectureVO) throws Exception;

	/**
	 * 과목정보 목록을 조회한다.
	 * @param LectureVO
	 * @return List - 직렬정보
	 */
	List<?> selectCategoryList(LectureVO LectureVO) throws Exception;

	/**
	 * 과목정보 목록을 조회한다.
	 * @param LectureVO
	 * @return List - 과목 목록
	 */
	List<?> getSubjectList(LectureVO LectureVO) throws Exception;

}