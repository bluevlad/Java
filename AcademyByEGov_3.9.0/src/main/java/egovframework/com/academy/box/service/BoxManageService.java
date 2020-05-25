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
	public List<?> selectBoxInfoList(BoxVO BoxVO) throws Exception;
	
	/**
	 * 사물함 상세정보를 조회한다.
	 * @param BoxCd
	 * @param BoxNum
	 * @return BoxVO
	 */
	public BoxVO selectBoxInfoDetail(BoxVO BoxVO) throws Exception;
	
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
	
	/**
	 * 사물함 대여정보를 조회한다.
	 * @param boxCd 상세조회대상 사물함코드
	 * @param boxNum 상세조회대상 사물함번호
     * @param rentSeq 상세조회대상 대여번호
	 * @return BoxVO 사물함 상세정보
	 */
	public BoxVO selectBoxRentInfo(BoxVO BoxVO) throws Exception;
	
	/**
	 * 사물함 대여 목록을 조회한다.
	 * @param boxCd 상세조회대상 사물함코드
	 * @param boxNum 상세조회대상 사물함번호
	 * @return List<BoxVO> 사물함 대여 목록정보
	 */
	public List<?> selectBoxRentList(BoxVO BoxVO) throws Exception;

}
