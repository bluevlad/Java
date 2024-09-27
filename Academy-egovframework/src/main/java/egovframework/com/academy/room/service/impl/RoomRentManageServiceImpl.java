package egovframework.com.academy.room.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.com.academy.room.service.RoomRentManageService;
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
@Service("roomRentManageService")
public class RoomRentManageServiceImpl extends EgovAbstractServiceImpl implements RoomRentManageService {

	@Resource(name="roomManageDAO")
	private RoomManageDAO roomManageDAO;

	@Resource(name="roomRentManageDAO")
	private RoomRentManageDAO roomRentManageDAO;

	/**
	 * @param searchVO 검색조건
	 * @return List<?> 독서실 대여 목록정보
	 * @throws Exception
	 */
	@Override
	public List<?> selectRoomRentList(RoomVO RoomVO) throws Exception {
		return roomRentManageDAO.selectRoomRentList(RoomVO);
	}

	/**
	 * @param searchVO 검색조건
	 * @return 독서실 대여 갯수(int)
	 * @throws Exception
	 */
	@Override
	public int selectRoomRentListTotCnt(RoomVO RoomVO) throws Exception {
		return roomRentManageDAO.selectRoomRentListTotCnt(RoomVO);
	}

	/**
	 * @param RoomCd 상세조회대상 독서실코드
	 * @return RoomVO 독서실 대여 상세정보
	 * @throws Exception
	 */
	@Override
	public RoomVO selectRoomRentDetail(RoomVO RoomVO) throws Exception {
		return roomRentManageDAO.selectRoomRentDetail(RoomVO);
	}

	/**
	 * @param RoomVO 독서실 대여 등록정보 추가
	 * @throws Exception
	 */
	@Override
	public void insertRoomRent(RoomVO RoomVO) throws Exception {
		int seq = roomRentManageDAO.selectRoomSeq(RoomVO);
		RoomVO.setRentSeq(seq);
		
		/* 독서실 자리 대여정보를 추가한다. */
		roomRentManageDAO.insertRoomRent(RoomVO);
		/* 독서실 자리 대여번호를 등록한다. */
		roomManageDAO.updateRoomNum(RoomVO);
	}

	/**
	 * @param RoomVO 독서실 대여 등록정보 변경
	 * @throws Exception
	 */
	@Override
	public void updateRoomRent(RoomVO RoomVO) throws Exception {
		roomRentManageDAO.updateRoomRent(RoomVO);
	}

	/**
	 * @param RoomCd 상세조회대상 독서실코드
	 * @param RoomNum 상세조회대상 독서실번호
	 * @param RentSeq 상세조회대상 대여번호
	 * @return List<RoomVO> 독서실 대여 주문 목록정보
	 * @throws Exception
	 */
	public List<?> selectRoomRentOrdList(RoomVO RoomVO) throws Exception {
		return roomRentManageDAO.selectRoomRentOrdList(RoomVO);
	}

	/**
	 * @param RoomCd 상세조회대상 독서실코드
	 * @param RoomNum 상세조회대상 독서실번호
	 * @param RentSeq 상세조회대상 대여번호
	 * @return RoomVO 독서실 대여 주문 상세정보
	 * @throws Exception
	 */
	@Override
	public RoomVO selectRoomRentOrdDetail(RoomVO RoomVO) throws Exception {
		return roomRentManageDAO.selectRoomRentOrdDetail(RoomVO);
	}

}
