package web.room.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web.room.service.impl.RoomDAO;
import web.room.service.RoomService;

@Service(value="roomservice")
public class RoomServiceImpl implements RoomService {

	@Autowired
	private RoomDAO roomdao;

	@Override
	public List<HashMap<String, String>> roomList(HashMap<String, String> params) {
		return roomdao.roomList(params);
	}

	@Override
	public void roomInsertProcess(HashMap<String, String> params) {
		roomdao.roomInsertProcess(params);
	}

	@Override
	public void roomNumInsertProcess(HashMap<String, String> params) {
		roomdao.roomNumInsertProcess(params);
	}

	@Override
	public HashMap<String, String> getRoomDetail(HashMap<String, String> params) {
		return roomdao.getRoomDetail(params);
	}

	@Override
	public List<HashMap<String, String>> getRoomNumList(HashMap<String, String> params) {
		return roomdao.getRoomNumList(params);
	}

	@Override
	public int getRoomNumYCount(HashMap<String, String> params) {
		return roomdao.getRoomNumYCount(params);
	}

	@Override
	public void roomDeleteRoomAll(HashMap<String, String> params) {
		roomdao.roomDeleteRoomAll(params);		
	}

	@Override
	public void roomUpdate2Process(HashMap<String, String> params) {
		roomdao.roomUpdate2Process(params);
	}

	@Override
	public void roomUpdateProcess(HashMap<String, String> params) {
		roomdao.roomUpdateProcess(params);
	}

	@Override
	public void roomDeleteRoomNum(HashMap<String, String> params) {
		roomdao.roomDeleteRoomNum(params);
	}

	@Override
	public HashMap<String, String> roomNumRentDetail(
			HashMap<String, String> params) {
		return roomdao.roomNumRentDetail(params);
	}

	@Override
	public HashMap<String, String> roomNumRentOrderDetail(
			HashMap<String, String> params) {
		return roomdao.roomNumRentOrderDetail(params);
	}

	@Override
	public List<HashMap<String, String>> roomNumRentOrderList(
			HashMap<String, String> params) {
		return roomdao.roomNumRentOrderList(params);
	}

	@Override
	public void updateRoomFlagAsync(HashMap<String, String> params) {
		roomdao.updateRoomFlagAsync(params);
	}

	@Override
	public void roomNumUpdateProcess(HashMap<String, String> params) {
		roomdao.roomNumUpdateProcess(params);
	}

	@Override
	public void roomNumRentUpdateProcess(HashMap<String, String> params) {
		roomdao.roomNumRentUpdateProcess(params);		
	}

	@Override
	public void offApprovalsUpdateProcess(HashMap<String, String> params) {
		roomdao.offApprovalsUpdateProcess(params);		
	}

	@Override
	public String getOffOrderNo(HashMap<String, String> params) {
		return roomdao.getOffOrderNo(params);
	}

	@Override
	public void offOrdersInsertProcess(HashMap<String, String> params) {
		roomdao.offOrdersInsertProcess(params);		
	}

	@Override
	public void offOrderMgntNoInsertProcess(HashMap<String, String> params) {
		roomdao.offOrderMgntNoInsertProcess(params);		
	}

	@Override
	public void offApprovalsInsertProcess(HashMap<String, String> params) {
		roomdao.offApprovalsInsertProcess(params);		
	}

	@Override
	public void roomRentInsertProcess(HashMap<String, String> params) {
		roomdao.roomRentInsertProcess(params);
	}

	@Override
	public int getRoomRentSeq(HashMap<String, String> params) {
		return roomdao.getRoomRentSeq(params);
	}

	@Override
	public void offRefundInsertProcess(HashMap<String, String> params) {
		roomdao.offRefundInsertProcess(params);
	}

	@Override
	public void offApprovalsRefundUpdateProcess(HashMap<String, String> params) {
		roomdao.offApprovalsRefundUpdateProcess(params);
	}

	@Override
	public void roomNumRentRefundUpdateProcess(HashMap<String, String> params) {
		roomdao.roomNumRentRefundUpdateProcess(params);
	}

	@Override
	public void roomNumResetUpdateProcess(HashMap<String, String> params) {
		roomdao.roomNumResetUpdateProcess(params);
	}

	@Override
	public HashMap<String, String> roomOrderDetail(HashMap<String, String> params) {
		return roomdao.roomOrderDetail(params);
	}

	@Override
	public void roomDeleteRoomRentByOrderId(HashMap<String, String> params) {
		roomdao.roomDeleteRoomRentByOrderId(params);		
	}

	@Override
	public void roomRentChangeUpdateProcess(HashMap<String, String> params) {
		roomdao.roomRentChangeUpdateProcess(params);
	}

	@Override
	public void roomNumChangeUpdateProcess(HashMap<String, String> params) {
		roomdao.roomNumChangeUpdateProcess(params);
	}

	@Override
	public List<HashMap<String, String>> getRoomOrderList(HashMap<String, String> params) {
		return roomdao.getRoomOrderList(params);
	}

	@Override
	public int getRoomOrderListCount(HashMap<String, String> params) {
		return roomdao.getRoomOrderListCount(params);
	}

	@Override
	public List<HashMap<String, String>> getRoomOrderListExcel(HashMap<String, String> params) {
		return roomdao.getRoomOrderListExcel(params);
	}

	@Override
	public int getRoomNumExistCount(HashMap<String, String> params) {
		return roomdao.getRoomNumExistCount(params);
	}

	@Override
	public void roomNumResetUpdateByOrderIdProcess(HashMap<String, String> params) {
		roomdao.roomNumResetUpdateByOrderIdProcess(params);
	}

}

