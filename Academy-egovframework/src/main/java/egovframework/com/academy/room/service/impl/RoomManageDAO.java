package egovframework.com.academy.room.service.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.academy.room.service.RoomVO;
import egovframework.com.cmm.service.impl.EgovComAbstractDAO;

/**
 * 독서실 정보에 관한 데이터 접근 클래스를 정의한다.
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
@Repository("roomManageDAO")
public class RoomManageDAO extends EgovComAbstractDAO{

    /**
     * @param RoomVO 검색조건
     * @return List 독서실 목록정보
     */
    public List<?> selectRoomList(RoomVO RoomVO) throws Exception{
		return selectList("RoomManage.selectRoomList", RoomVO);
    }

    /**
     * @param RoomVO 검색조건
     * @return int 독서실 총갯수
     */
    public int selectRoomListTotCnt(RoomVO RoomVO) {
        return (Integer)selectOne("RoomManage.selectRoomListTotCnt", RoomVO);
    }

    /**
     * @param roomCd 상세조회대상 독서실코드
     * @return RoomVO 독서실  상세정보
     */
    public RoomVO selectRoomDetail(RoomVO RoomVO){
        return selectOne("RoomManage.selectRoomDetail", RoomVO);
    }

    /**
     * @param RoomVO 독서실 정보 등록
     */
    public void insertRoom(RoomVO RoomVO) throws Exception{
    	insert("RoomManage.insertRoom", RoomVO);                                                                                                                                                                              
    }

    /**
     * @param RoomVO 독서실 정보 변경
     */
    public void updateRoom(RoomVO RoomVO) throws Exception{
        update("RoomManage.updateRoom", RoomVO);
    }

    /**
     * @param RoomVO 독서실 정보 삭제
     */
    public void deleteRoom(RoomVO RoomVO) throws Exception{
        delete("RoomManage.deleteRoom", RoomVO);
    }

    /**
     * @param searchVO 검색조건
     * @return List 독서실 목록정보
     */
    public List<?> selectRoomNumList(RoomVO RoomVO) throws Exception{
		return selectList("RoomManage.selectRoomNumList", RoomVO);
    }

    /**
     * @param RoomCd 상세조회대상 독서실코드
     * @param RoomNum 상세조회대상 독서실번호
     * @return RoomVO 독서실 상세정보
     */
    public RoomVO selectRoomNumDetail(RoomVO RoomVO){
        return selectOne("RoomManage.selectRoomNumDetail", RoomVO);
    }

    /**
     * @param RoomVO 독서실 등록
     */
    public int insertRoomNum(RoomVO RoomVO) throws Exception{
        return getSqlSession().insert("RoomManage.insertRoomNum", RoomVO);
    }

    /**
     * @param RoomVO 독서실 정보 변경
     */
    public void updateRoomNum(RoomVO RoomVO) throws Exception{
        update("RoomManage.updateRoomNum", RoomVO);
    }

    /**
     * @param RoomVO 독서실 등록
     */
    public int deleteRoomNum(RoomVO RoomVO) throws Exception{
        return getSqlSession().delete("RoomManage.deleteRoomNum", RoomVO);
    }

}
