package egovframework.com.academy.room.service;

import java.util.List;

public interface RoomRentManageService {
	
	/**
	 * 사물함 목록을 조회한다.
	 * @param searchVO
	 * @return List - 사물함 목록
	 */
	public List<?> selectRoomRentList(RoomVO RoomVO) throws Exception;

	/**
	 * 사물함 총 갯수를 조회한다.
	 * @param searchVO
	 * @return int - 사물함 카운트 수
	 */
	int selectRoomRentListTotCnt(RoomVO RoomVO) throws Exception;
	
	/**
	 * 사물함 상세정보를 조회한다.
	 * @param RoomVO
	 * @return RoomVO
	 */
	public RoomVO selectRoomRentDetail(RoomVO RoomVO) throws Exception;

	/**
	 * 사물함 정보를 신규로 등록한다.
	 * @param RoomVO
	 */
	void insertRoomRent(RoomVO RoomVO) throws Exception;

	/**
	 * 사물함 정보를 신규로 등록한다.
	 * @param ExamVO
	 */
	void updateRoomRent(RoomVO RoomVO) throws Exception;

	/**
	 * 사물함 목록을 조회한다.
	 * @param searchVO
	 * @return List - 사물함 목록
	 */
	public List<?> selectRoomRentOrdList(RoomVO RoomVO) throws Exception;
	
	/**
	 * 사물함 상세정보를 조회한다.
	 * @param RoomCd
	 * @param RoomNum
	 * @return RoomVO
	 */
	public RoomVO selectRoomRentOrdDetail(RoomVO RoomVO) throws Exception;
	
}
