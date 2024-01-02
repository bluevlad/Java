package com.willbes.web.bookOrder.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface BookOrderService {
	
	// 교재배송 리스트 (택배)
	@SuppressWarnings({ "rawtypes" })
	List getJuOrderListDB(Map keyName);
	
	// 교재배송 리스트 (택배) 총 건수
	@SuppressWarnings({ "rawtypes" })
	int getJuOrderListCount(Map keyName);
		
	//2번째 리스트
	@SuppressWarnings({ "rawtypes" })
	List getTblJuOrderMgntListDB(Map keyName);
	
	// 엑셀 리스트
	@SuppressWarnings({ "rawtypes" })
	List<HashMap<String, String>> getJuOrderExelListDB(HashMap<String, String> params);
	
	//전체상품주문관리 주문번호 상세
	@SuppressWarnings({ "rawtypes" })
	List getTblApprovalsViewList(Map keyName);
	
	//전체상품주문관리 주문번호 상세
	@SuppressWarnings({ "rawtypes" })
	int getTblDeliversViewListCount(Map keyName);
	
	//전체상품주문관리 주문번호 상세
	@SuppressWarnings({ "rawtypes" })
	List getTblDeliversViewList(Map keyName);
	
	//전체상품주문관리 주문번호 상세
	@SuppressWarnings({ "rawtypes" })
	List getTblOrdersViewList(Map keyName);
	
	//전체상품주문관리 주문번호 상세
	@SuppressWarnings({ "rawtypes" })
	List getLecMstViewList(Map keyName);
		
	@SuppressWarnings({ "rawtypes" })
	int updateDelivers(Map keyName);
	
	@SuppressWarnings({ "rawtypes" })
	int updateApprovals(Map keyName);
	
	@SuppressWarnings({ "rawtypes" })
	int updateDeliversWmv(Map keyName);
		
	
	@SuppressWarnings({ "rawtypes" })
	int updateMoney1(Map keyName);
	
	@SuppressWarnings({ "rawtypes" })
	int getMoneySum(Map keyName);
	
	@SuppressWarnings({ "rawtypes" })
	int updateMoney2(Map keyName);
		
	@SuppressWarnings({ "rawtypes" })
	int refund_money_delete(Map keyName);
		
	//송장저장
	@SuppressWarnings({ "rawtypes" })
	int updateSendNo1(Map keyName);
	
	//송장저장
	@SuppressWarnings({ "rawtypes" })
	int updateSendNo2(Map keyName);
		
	// 교재배송관리 'P' 팝업
	@SuppressWarnings({ "rawtypes" })
	List getPopPrintView(Map keyName);
	
	//교재배송관리 'P' 팝업
	@SuppressWarnings({ "rawtypes" })
	List getPopPrintInfoView(Map keyName);
	
	// 교재배송관리 'P' 팝업
	@SuppressWarnings({ "rawtypes" })
	List getPopPrintOrderInfoView(Map keyName);
		
	// 교재배송 리스트 (직접수령)
	@SuppressWarnings({ "rawtypes" })
	List getJicOrderListDB(Map keyName);
	
	// 교재배송 리스트 (직접수령) 총 건수
	@SuppressWarnings({ "rawtypes" })
	int getJicOrderListCount(Map keyName);
		
	//수령완료
	@SuppressWarnings({ "rawtypes" })
	int updateSendNo3(Map keyName);
	
	//수령완료
	@SuppressWarnings({ "rawtypes" })
	int updateSendNo4(Map keyName);	
		
	// 교재매출내역 리스트
	@SuppressWarnings({ "rawtypes" })
	List getTolOrderListDB(Map keyName);
	
	// 교재매출내역 리스트 총 건수
	@SuppressWarnings({ "rawtypes" })
	int getTolOrderListCount(Map keyName);
		
	// 교재매출내역 리스트 2번째 리스트
	@SuppressWarnings({ "rawtypes" })
	List getPutLecMstDB(Map keyName);
	
	// 교재매출내역 리스트 엑셀 리스트
	@SuppressWarnings({ "rawtypes" })
	List<HashMap<String, String>> getTolOrderExelListDB(HashMap<String, String> params);	
		
	/** 경찰 택배정보 조회  **/
	@SuppressWarnings({ "rawtypes" })
	List getJuOrderListDB_COP(Map keyName);
	@SuppressWarnings({ "rawtypes" })
	int getJuOrderListCount_COP(Map keyName);
	@SuppressWarnings({ "rawtypes" })
	List getTblJuOrderMgntListDB_COP(Map keyName);
	@SuppressWarnings({ "rawtypes" })
	List<HashMap<String, String>> getJuOrderExelListDB_COP(HashMap<String, String> params);
	@SuppressWarnings({ "rawtypes" })
	int updateSendNo1_COP(Map keyName);
	@SuppressWarnings({ "rawtypes" })
	int updateSendNo2_COP(Map keyName);
	@SuppressWarnings({ "rawtypes" })
	List getLecMstViewList_COP(Map keyName);
	@SuppressWarnings({ "rawtypes" })
	List getTblDeliversViewList_COP(Map keyName);
}