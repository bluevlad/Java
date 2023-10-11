package egovframework.com.academy.box.service;

import java.util.List;

import egovframework.com.academy.lecture.service.LectureVO;

public interface BoxManageService {
	
	/**
	 * 사물함 목록을 조회한다.
	 * @param BoxVO
	 * @return List - 사물함 목록
	 */
	public List<?> selectBoxList(BoxVO BoxVO) throws Exception;

	/**
	 * 사물함 총 갯수를 조회한다.
	 * @param BoxVO
	 * @return int - 사물함 카운트 수
	 */
	int selectBoxListTotCnt(BoxVO BoxVO) throws Exception;
	
	/**
	 * 사물함 상세정보를 조회한다.
	 * @param BoxVO
	 * @return BoxVO
	 */
	public BoxVO selectBoxDetail(BoxVO BoxVO) throws Exception;

	public List<?> selectBoxNumList(BoxVO BoxVO) throws Exception;

	public BoxVO selectBoxNumRentDetail(BoxVO BoxVO) throws Exception;

	/**
	 * 사물함정보를 신규로 등록한다.
	 * @param BoxVO
	 */
	void insertBox(BoxVO BoxVO) throws Exception;

	/**
	 * 사물함 리스트 정보를 신규로 등록한다.
	 * @param BoxVO
	 */
	void insertBoxNum(BoxVO BoxVO) throws Exception;

	/**
	 * 사물함 정보를 변경한다.
	 * @param BoxVO
	 */
	void updateBox(BoxVO BoxVO) throws Exception;

	public BoxVO selectBoxNumRentOrderDetail(BoxVO BoxVO) throws Exception;

	public List<?> selectBoxNumRentOrderList(BoxVO BoxVO) throws Exception;

}
