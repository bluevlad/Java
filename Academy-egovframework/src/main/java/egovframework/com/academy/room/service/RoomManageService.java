package egovframework.com.academy.room.service;

import java.util.List;

public interface RoomManageService {
	
	/**
	 * 독서실 목록을 조회한다.
	 * @param searchVO
	 * @return List - 독서실 목록
	 */
	public List<?> selectRoomList(RoomVO RoomVO) throws Exception;

	/**
	 * 독서실 총 갯수를 조회한다.
	 * @param searchVO
	 * @return int - 독서실 카운트 수
	 */
	int selectRoomListTotCnt(RoomVO RoomVO) throws Exception;
	
	/**
	 * 독서실 상세정보를 조회한다.
	 * @param RoomVO
	 * @return RoomVO
	 */
	public RoomVO selectRoomDetail(RoomVO RoomVO) throws Exception;

	/**
	 * 독서실 정보를 신규로 등록한다.
	 * @param RoomVO
	 */
	int insertRoom(RoomVO RoomVO) throws Exception;

	/**
	 * 독서실 정보를 신규로 등록한다.
	 * @param ExamVO
	 */
	void updateRoom(RoomVO RoomVO) throws Exception;

	/**
	 * 기 등록된 독서실 정보를 수정한다.
	 * @param ExamVO
	 */
	void deleteRoom(RoomVO RoomVO) throws Exception;
	
	/**
	 * 독서실 목록을 조회한다.
	 * @param searchVO
	 * @return List - 독서실 목록
	 */
	public List<?> selectRoomNumList(RoomVO RoomVO) throws Exception;
	
	/**
	 * 독서실 상세정보를 조회한다.
	 * @param RoomCd
	 * @param RoomNum
	 * @return RoomVO
	 */
	public RoomVO selectRoomNumDetail(RoomVO RoomVO) throws Exception;
	
	/**
	 * 독서실 정보를 신규로 등록한다.
	 * @param RoomVO
	 */
	public void insertRoomNum(RoomVO RoomVO) throws Exception;

	/**
	 * 독서실 자리 정보를 변경한다.
	 * @param ExamVO
	 */
	void updateRoomNum(RoomVO RoomVO) throws Exception;

	/**
	 * 기 등록된 독서실 정보를 수정한다.
	 * @param RoomVO
	 */
	public void deleteRoomNum(RoomVO RoomVO) throws Exception;
}
