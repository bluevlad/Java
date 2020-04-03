package web.room.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;

@Repository
public class RoomDAO extends EgovComAbstractDAO {

	// 독서실 목록 조회  2013.11.08
	public List<HashMap<String, String>> roomList(HashMap<String, String> params) {
		return getSqlSession().selectList("room.roomList", params);
	}

	// 독서실 등록 처리  2013.11.08
	public void roomInsertProcess(HashMap<String, String> params) {
		getSqlSession().insert("room.roomInsertProcess",params);
	}

	// 독서실 상세정보 등록 처리
	public void roomNumInsertProcess(HashMap<String, String> params) {
		getSqlSession().insert("room.roomNumInsertProcess",params);
	}

	public HashMap<String, String> getRoomDetail(HashMap<String, String> params) {
		return getSqlSession().selectOne("room.getRoomDetail", params);
	}

	public List<HashMap<String, String>> getRoomNumList(HashMap<String, String> params) {
		return getSqlSession().selectList("room.getRoomNumList", params);
	}

	public int getRoomNumYCount(HashMap<String, String> params) {
		return getSqlSession().selectOne("room.getRoomNumYCount", params);
	}

	public void roomDeleteRoomAll(HashMap<String, String> params) {
		getSqlSession().delete("room.roomDeleteRoomAll", params);
	}

	public void roomUpdate2Process(HashMap<String, String> params) {
		getSqlSession().update("room.roomUpdate2Process", params);
	}

	public void roomUpdateProcess(HashMap<String, String> params) {
		getSqlSession().update("room.roomUpdateProcess", params);
	}

	public void roomDeleteRoomNum(HashMap<String, String> params) {
		getSqlSession().delete("room.roomDeleteRoomNum", params);
	}

	public HashMap<String, String> roomNumRentDetail(HashMap<String, String> params) {
		return getSqlSession().selectOne("room.roomNumRentDetail", params);
	}

	public HashMap<String, String> roomNumRentOrderDetail(HashMap<String, String> params) {
		return getSqlSession().selectOne("room.roomNumRentOrderDetail", params);
	}

	public List<HashMap<String, String>> roomNumRentOrderList(HashMap<String, String> params) {
		return getSqlSession().selectList("room.roomNumRentOrderList", params);
	}

	public void updateRoomFlagAsync(HashMap<String, String> params) {
		getSqlSession().update("room.updateRoomFlagAsync", params);
	}

	public void roomNumUpdateProcess(HashMap<String, String> params) {
		getSqlSession().update("room.roomNumUpdateProcess", params);
	}

	public void roomNumRentUpdateProcess(HashMap<String, String> params) {
		getSqlSession().update("room.roomNumRentUpdateProcess", params);
	}

	public void offApprovalsUpdateProcess(HashMap<String, String> params) {
		getSqlSession().update("room.offApprovalsUpdateProcess", params);
	}

	public String getOffOrderNo(HashMap<String, String> params) {
		return getSqlSession().selectOne("room.getOffOrderNo", params);
	}

	public void offOrdersInsertProcess(HashMap<String, String> params) {
		getSqlSession().insert("room.offOrdersInsertProcess",params);
	}

	public void offOrderMgntNoInsertProcess(HashMap<String, String> params) {
		getSqlSession().insert("room.offOrderMgntNoInsertProcess",params);
	}

	public void offApprovalsInsertProcess(HashMap<String, String> params) {
		getSqlSession().insert("room.offApprovalsInsertProcess",params);
	}

	public void roomRentInsertProcess(HashMap<String, String> params) {
		getSqlSession().insert("room.roomRentInsertProcess",params);
	}

	public int getRoomRentSeq(HashMap<String, String> params) {
		return getSqlSession().selectOne("room.getRoomRentSeq", params);
	}

	public void offRefundInsertProcess(HashMap<String, String> params) {
		getSqlSession().insert("room.offRefundInsertProcess",params);
	}

	public void offApprovalsRefundUpdateProcess(HashMap<String, String> params) {
		getSqlSession().update("room.offApprovalsRefundUpdateProcess", params);
	}

	public void roomNumRentRefundUpdateProcess(HashMap<String, String> params) {
		getSqlSession().update("room.roomNumRentRefundUpdateProcess", params);
	}

	public void roomNumResetUpdateProcess(HashMap<String, String> params) {
		getSqlSession().update("room.roomNumResetUpdateProcess", params);
	}

	public HashMap<String, String> roomOrderDetail(HashMap<String, String> params) {
		return getSqlSession().selectOne("room.roomOrderDetail", params);
	}

	public void roomDeleteRoomRentByOrderId(HashMap<String, String> params) {
		getSqlSession().delete("room.roomDeleteRoomRentByOrderId", params);
	}

	public void roomRentChangeUpdateProcess(HashMap<String, String> params) {
		getSqlSession().update("room.roomRentChangeUpdateProcess", params);
	}

	public void roomNumChangeUpdateProcess(HashMap<String, String> params) {
		getSqlSession().update("room.roomNumChangeUpdateProcess", params);
	}

	public List<HashMap<String, String>> getRoomOrderList(
			HashMap<String, String> params) {
		return getSqlSession().selectList("room.getRoomOrderList", params);
	}

	public int getRoomOrderListCount(HashMap<String, String> params) {
		return getSqlSession().selectOne("room.getRoomOrderListCount", params);
	}

	public List<HashMap<String, String>> getRoomOrderListExcel(HashMap<String, String> params) {
		return getSqlSession().selectList("room.getRoomOrderListExcel",params);
	}

	public int getRoomNumExistCount(HashMap<String, String> params) {
		return getSqlSession().selectOne("room.getRoomNumExistCount", params);
	}

	public void roomNumResetUpdateByOrderIdProcess(HashMap<String, String> params) {
		getSqlSession().update("room.roomNumResetUpdateByOrderIdProcess", params);
	}

}
