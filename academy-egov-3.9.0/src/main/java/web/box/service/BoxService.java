package web.box.service;

import java.util.HashMap;
import java.util.List;

public interface BoxService {

	List<HashMap<String, String>> boxList(HashMap<String, String> params);

	void boxInsertProcess(HashMap<String, String> params);

	void boxNumInsertProcess(HashMap<String, String> params);

	HashMap<String, String> getBoxDetail(HashMap<String, String> params);

	List<HashMap<String, String>> getBoxNumList(HashMap<String, String> params);

	int getBoxNumYCount(HashMap<String, String> params);

	void boxDeleteBoxAll(HashMap<String, String> params);

	void boxUpdate2Process(HashMap<String, String> params);

	void boxUpdateProcess(HashMap<String, String> params);

	void boxDeleteBoxNum(HashMap<String, String> params);

	HashMap<String, String> boxNumRentDetail(HashMap<String, String> params);

	HashMap<String, String> boxNumRentOrderDetail(HashMap<String, String> params);
	
	List<HashMap<String, String>> boxNumRentOrderList(HashMap<String, String> params);

	void updateBoxFlagAsync(HashMap<String, String> params);

	void boxNumUpdateProcess(HashMap<String, String> params);

	void boxNumRentUpdateProcess(HashMap<String, String> params);

	String getOffOrderNo(HashMap<String, String> params);

	void offOrdersInsertProcess(HashMap<String, String> params);

	void offOrderMgntNoInsertProcess(HashMap<String, String> params);

	void offApprovalsInsertProcess(HashMap<String, String> params);

	void boxRentInsertProcess(HashMap<String, String> params);

	int getBoxRentSeq(HashMap<String, String> params);

	void offApprovalsUpdateProcess(HashMap<String, String> params);

	void offRefundInsertProcess(HashMap<String, String> params);

	void offApprovalsRefundUpdateProcess(HashMap<String, String> params);

	void boxNumRentRefundUpdateProcess(HashMap<String, String> params);

	void boxNumResetUpdateProcess(HashMap<String, String> params);

	HashMap<String, String> boxOrderDetail(HashMap<String, String> params);

	void boxDeleteBoxRentByOrderId(HashMap<String, String> params);

	void boxRentChangeUpdateProcess(HashMap<String, String> params);

	void boxNumChangeUpdateProcess(HashMap<String, String> params);

	List<HashMap<String, String>> getBoxOrderList(HashMap<String, String> params);

	int getBoxOrderListCount(HashMap<String, String> params);

	List<HashMap<String, String>> getBoxOrderListExcel(HashMap<String, String> params);

	List<HashMap<String, String>> getBoxExtOrderList(HashMap<String, String> params);

	int getBoxExtOrderListCount(HashMap<String, String> params);

	List<HashMap<String, String>> getBoxExtOrderListExcel(HashMap<String, String> params);

	void boxNumResetUpdateByOrderIdProcess(HashMap<String, String> params);

	void deleteBoxOrder(HashMap<String, String> params);

}
