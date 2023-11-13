package egovframework.com.academy.lecture.service;

import java.util.List;

public interface LecFormService {

	/**
	 * 학습형태정보 목록을 조회한다.
	 * @param LectureVO
	 * @return List - 목록
	 */
	List<?> selectFormList(LectureVO LectureVO) throws Exception;

	/**
	 * 학습형태정보 총 갯수를 조회한다.
	 * @param LectureVO
	 * @return int - 학습형태 카운트 수
	 */
	int selectFormListCount(LectureVO LectureVO) throws Exception;
	
	/**
	 * 학습형태 상세정보를 조회한다.
	 * @param LectureVO
	 * @return LectureVO
	 */
	public LectureVO selectFormDetail(LectureVO LectureVO) throws Exception;

	/**
	 * 학습형태 정보를 신규로 등록한다.
	 * @param ExamVO
	 */
	void insertForm(LectureVO LectureVO) throws Exception;

	/**
	 * 기 등록된 학습형태정보를 수정한다.
	 * @param LectureVO
	 */
	void updateForm(LectureVO LectureVO) throws Exception;

	/**
	 * 기 등록된 학습형태정보를 수정한다.
	 * @param LectureVO
	 */
	void deleteForm(LectureVO LectureVO) throws Exception;

	public int selectFormCheck(LectureVO LectureVO) throws Exception;

}