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

	public BoxVO selectBoxNumRentOrderDetail(BoxVO BoxVO) throws Exception;

	public List<?> selectBoxNumRentOrderList(BoxVO BoxVO) throws Exception;

}
