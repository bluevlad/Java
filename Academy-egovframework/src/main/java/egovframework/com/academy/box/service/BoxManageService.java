package egovframework.com.academy.box.service;

import java.util.List;

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

	public BoxVO selectBoxOrderDetail(BoxVO BoxVO) throws Exception;

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

	/**
	 * 사물함 상태 정보를 변경한다.
	 * @param BoxVO
	 */
	void updateBoxFlag(BoxVO BoxVO) throws Exception;

	/**
	 * 주문번호를 조회한다.
	 * @param BoxVO
	 * @return String - 주문번호
	 */
	String selectOrderno(BoxVO BoxVO) throws Exception;

	/**
	 * 사물함 주문상세 정보를 등록한다.
	 * @param BoxVO
	 */
	void insertOrderItem(BoxVO BoxVO) throws Exception;
	
	void insertOrders(BoxVO BoxVO) throws Exception;
	
	void insertApprovals(BoxVO BoxVO) throws Exception;

	void updateApprovals(BoxVO BoxVO) throws Exception;
	
	void insertBoxRent(BoxVO BoxVO) throws Exception;
	
	int getBoxRentSeq(BoxVO BoxVO) throws Exception;

	void updateBoxNum(BoxVO BoxVO) throws Exception;

	void updateBoxNumRent(BoxVO BoxVO) throws Exception;

	void updateboxNumChange(BoxVO BoxVO) throws Exception;

	void updateboxNumReset(BoxVO BoxVO) throws Exception;

	void updateBoxRentChange(BoxVO BoxVO) throws Exception;

}
