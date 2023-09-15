package egovframework.com.academy.room.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.academy.room.service.RoomVO;
import egovframework.com.cmm.service.impl.EgovComAbstractDAO;

/**
 * 독서실 대여 정보에 관한 데이터 접근 클래스를 정의한다.
 * @author rainend
 * @since 2023.01.10
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      		수정자           수정내용
 *  ----------------    --------    ---------------------------
 *   2023.01.21  		rainend          최초 생성
 * </pre>
 */
@Repository("roomRentManageDAO")
public class RoomRentManageDAO extends EgovComAbstractDAO{

    /**
     * @param RoomVO 검색조건
     * @return List 독서실 대여 목록정보
     */
    public List<?> selectRoomRentList(RoomVO RoomVO) throws Exception{
		return selectList("RoomRentManage.selectRoomRentList", RoomVO);
    }

    /**
     * @param RoomVO 검색조건
     * @return int 독서실 대여 총갯수
     */
    public int selectRoomRentListTotCnt(RoomVO RoomVO) {
        return (Integer)selectOne("RoomRentManage.selectRoomRentListTotCnt", RoomVO);
    }

    /**
     * @param roomCd 상세조회대상 독서실코드
     * @return RoomVO 독서실 대여 상세정보
     */
    public RoomVO selectRoomRentDetail(RoomVO RoomVO){
        return selectOne("RoomRentManage.selectRoomRentDetail", RoomVO);
    }

    /**
     * @param RoomVO 검색조건
     * @return int 독서실 대여 총갯수
     */
    public int selectRoomSeq(RoomVO RoomVO) {
        return (Integer)selectOne("RoomRentManage.selectRoomSeq", RoomVO);
    }

    /**
     * @param RoomVO 독서실 대여 정보 등록
     */
    public void insertRoomRent(RoomVO RoomVO) throws Exception{
    	insert("RoomRentManage.insertRoomRent", RoomVO);                                                                                                                                                                              
    }

    /**
     * @param RoomVO 독서실 대여 정보 변경
     */
    public void updateRoomRent(RoomVO RoomVO) throws Exception{
        insert("RoomRentManage.updateRoomRent", RoomVO);
    }

    /**
     * @param searchVO 검색조건
     * @return List 독서실 대여 주문 목록정보
     */
    public List<?> selectRoomRentOrdList(RoomVO RoomVO) throws Exception{
		return selectList("RoomRentManage.selectRoomRentOrdList", RoomVO);
    }

    /**
     * @param RoomCd 상세조회대상 독서실코드
     * @param RoomNum 상세조회대상 독서실번호
     * @return RoomVO 독서실 대여 상세정보
     */
    public RoomVO selectRoomRentOrdDetail(RoomVO RoomVO){
        return selectOne("RoomRentManage.selectRoomRentOrdDetail", RoomVO);
    }

}
