package egovframework.com.academy.box.service;

import java.util.List;

public interface BoxManageService {
	
	/**
	 * 사물함 목록을 조회한다.
	 * @param searchVO
	 * @return List - 사물함 목록
	 */
	public List<?> selectBoxList(BoxVO searchVO) throws Exception;

	/**
	 * 사물함 총 갯수를 조회한다.
	 * @param searchVO
	 * @return int - 사물함 카운트 수
	 */
	int selectBoxListTotCnt(BoxVO searchVO) throws Exception;
	
	/**
	 * 사물함 상세정보를 조회한다.
	 * @param BoxVO
	 * @return BoxVO
	 */
	public BoxVO selectBoxDetail(BoxVO BoxVO) throws Exception;

	/**
	 * 사물함 정보를 신규로 등록한다.
	 * @param BoxVO
	 */
	void insertBox(BoxVO BoxVO) throws Exception;

	/**
	 * 사물함 정보를 신규로 등록한다.
	 * @param ExamVO
	 */
	void updateBox(BoxVO BoxVO) throws Exception;

	/**
	 * 기 등록된 사물함 정보를 수정한다.
	 * @param ExamVO
	 */
	void deleteBox(BoxVO BoxVO) throws Exception;
	
	/**
	 * 사물함 목록을 조회한다.
	 * @param searchVO
	 * @return List - 사물함 목록
	 */
	public List<?> selectBoxNumList(BoxVO searchVO) throws Exception;

	/**
	 * 사물함 총 갯수를 조회한다.
	 * @param examSearchVO
	 * @return int - 과목 카운트 수
	 */
	public int selectBoxNumListTotCnt(BoxVO searchVO) throws Exception;
	
	/**
	 * 사물함 정보를 신규로 등록한다.
	 * @param BoxVO
	 */
	public void insertBoxNum(BoxVO BoxVO) throws Exception;

	/**
	 * 기 등록된 사물함 정보를 수정한다.
	 * @param BoxVO
	 */
	public void deleteBoxNum(BoxVO BoxVO) throws Exception;

}
