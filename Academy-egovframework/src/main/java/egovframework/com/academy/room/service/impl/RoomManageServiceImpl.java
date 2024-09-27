package egovframework.com.academy.room.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.com.academy.room.service.RoomManageService;
import egovframework.com.academy.room.service.RoomVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * 독서실 관리에 관한 비지니스 클래스를 정의한다.
 * @author rainend
 * @since 2009.04.10
 * @version 1.0
 * @see
 * <pre>
 * << 개정이력(Modification Information) >>
 *   수정일      			수정자           수정내용
 *  ----------------    --------------    ---------------------------
 *  2020.05.21  			rainend          최초 생성
 * </pre>
 */
@Service("roomManageService")
public class RoomManageServiceImpl extends EgovAbstractServiceImpl implements RoomManageService {

	@Resource(name="roomManageDAO")
	private RoomManageDAO roomManageDAO;

	/**
	 * @param searchVO 검색조건
	 * @return List<?> 독서실 목록정보
	 * @throws Exception
	 */
	@Override
	public List<?> selectRoomList(RoomVO RoomVO) throws Exception {
		return roomManageDAO.selectRoomList(RoomVO);
	}

	/**
	 * @param searchVO 검색조건
	 * @return 독서실 갯수(int)
	 * @throws Exception
	 */
	@Override
	public int selectRoomListTotCnt(RoomVO RoomVO) throws Exception {
		return roomManageDAO.selectRoomListTotCnt(RoomVO);
	}

	/**
	 * @param RoomCd 상세조회대상 독서실코드
	 * @return RoomVO 독서실 상세정보
	 * @throws Exception
	 */
	@Override
	public RoomVO selectRoomDetail(RoomVO RoomVO) throws Exception {
		return roomManageDAO.selectRoomDetail(RoomVO);
	}

	/**
	 * @param RoomVO 독서실 등록정보
	 * @throws Exception
	 */
	@Override
	public int insertRoom(RoomVO RoomVO) throws Exception {
		
		int Room_num = RoomVO.getStartNum();
		RoomVO.setEndNum(Room_num+RoomVO.getRoomCount()-1);

		roomManageDAO.insertRoom(RoomVO);

		for(int i = 0; i < RoomVO.getRoomCount(); i++) {
			RoomVO.setRoomNum(Room_num);
			RoomVO.setRoomFlag("Y");
			RoomVO.setRentMemo("신규등록");
			roomManageDAO.insertRoomNum(RoomVO);
			Room_num++;
		}

		return RoomVO.getRoomCd();
	}

	/**
	 * @param RoomVO 독서실 등록정보
	 * @throws Exception
	 */
	@Override
	public void updateRoom(RoomVO RoomVO) throws Exception {
		roomManageDAO.updateRoom(RoomVO);
	}

	/**
	 * RoomVO 독서실 수정
	 * @param RoomVO 독서실
	 * @throws Exception
	 */
	@Override
	public void deleteRoom(RoomVO RoomVO) throws Exception {
		roomManageDAO.deleteRoom(RoomVO);
	}

	/**
	 * @param RoomVO 검색조건
	 * @return List<RoomVO> 독서실 목록정보
	 * @throws Exception
	 */
	public List<?> selectRoomNumList(RoomVO RoomVO) throws Exception {
		return roomManageDAO.selectRoomNumList(RoomVO);
	}

	/**
	 * @param RoomCd 상세조회대상 독서실코드
	 * @param RoomNum 상세조회대상 독서실번호
	 * @return RoomVO 독서실 상세정보
	 * @throws Exception
	 */
	@Override
	public RoomVO selectRoomNumDetail(RoomVO RoomVO) throws Exception {
		return roomManageDAO.selectRoomNumDetail(RoomVO);
	}

	/**
	 * @param RoomVO 독서실 등록
	 * @param RoomCd 독서실코드
	 * @throws Exception
	 */
	public void insertRoomNum(RoomVO RoomVO) throws Exception {
		roomManageDAO.insertRoomNum(RoomVO);
	}

	/**
	 * @param RoomVO 독서실 자리정보 업데이트
	 * @throws Exception
	 */
	@Override
	public void updateRoomNum(RoomVO RoomVO) throws Exception {
		roomManageDAO.updateRoomNum(RoomVO);
	}

	/**
	 * RoomVO 독서실 삭제
	 * @param RoomCd 독서실코드
	 * @param RoomNum 독서실번호
	 * @throws Exception
	 */
	public void deleteRoomNum(RoomVO RoomVO) throws Exception {
		roomManageDAO.deleteRoomNum(RoomVO);
	}

}
