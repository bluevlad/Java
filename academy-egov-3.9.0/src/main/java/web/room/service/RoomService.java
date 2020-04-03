package web.room.service;

import java.util.HashMap;
import java.util.List;

public interface RoomService {

	List<HashMap<String, String>> roomList(HashMap<String, String> params);

	void roomInsertProcess(HashMap<String, String> params);

	void roomNumInsertProcess(HashMap<String, String> params);

	HashMap<String, String> getRoomDetail(HashMap<String, String> params);

	List<HashMap<String, String>> getRoomNumList(HashMap<String, String> params);

	int getRoomNumYCount(HashMap<String, String> params);

	void roomDeleteRoomAll(HashMap<String, String> params);

	void roomUpdate2Process(HashMap<String, String> params);

	void roomUpdateProcess(HashMap<String, String> params);

	void roomDeleteRoomNum(HashMap<String, String> params);

	HashMap<String, String> roomNumRentDetail(HashMap<String, String> params);

	HashMap<String, String> roomNumRentOrderDetail(HashMap<String, String> params);
	
	List<HashMap<String, String>> roomNumRentOrderList(HashMap<String, String> params);

	void updateRoomFlagAsync(HashMap<String, String> params);

	void roomNumUpdateProcess(HashMap<String, String> params);

	void roomNumRentUpdateProcess(HashMap<String, String> params);

	String getOffOrderNo(HashMap<String, String> params);

	void offOrdersInsertProcess(HashMap<String, String> params);

	void offOrderMgntNoInsertProcess(HashMap<String, String> params);

	void offApprovalsInsertProcess(HashMap<String, String> params);

	void roomRentInsertProcess(HashMap<String, String> params);

	int getRoomRentSeq(HashMap<String, String> params);

	void offApprovalsUpdateProcess(HashMap<String, String> params);

	void offRefundInsertProcess(HashMap<String, String> params);

	void offApprovalsRefundUpdateProcess(HashMap<String, String> params);

	void roomNumRentRefundUpdateProcess(HashMap<String, String> params);

	void roomNumResetUpdateProcess(HashMap<String, String> params);

	HashMap<String, String> roomOrderDetail(HashMap<String, String> params);

	void roomDeleteRoomRentByOrderId(HashMap<String, String> params);

	void roomRentChangeUpdateProcess(HashMap<String, String> params);

	void roomNumChangeUpdateProcess(HashMap<String, String> params);

	List<HashMap<String, String>> getRoomOrderList(HashMap<String, String> params);

	int getRoomOrderListCount(HashMap<String, String> params);

	List<HashMap<String, String>> getRoomOrderListExcel(HashMap<String, String> params);

	int getRoomNumExistCount(HashMap<String, String> params);

	void roomNumResetUpdateByOrderIdProcess(HashMap<String, String> params);

}
