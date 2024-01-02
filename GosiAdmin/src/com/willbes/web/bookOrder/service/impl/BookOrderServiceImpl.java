package com.willbes.web.bookOrder.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.willbes.platform.util.file.service.MultipartFileService;
import com.willbes.web.bookOrder.service.BookOrderService;

@Service
public class BookOrderServiceImpl  implements  BookOrderService{
	
	/** log */
	private Logger log = Logger.getLogger(this.getClass());
	
	@Autowired
	private BookOrderDAO bookOrderdao;
	
	@Autowired
	private MultipartFileService multipartFileService;
	
	
	// 교재배송 리스트 (택배)
	@Override @SuppressWarnings("rawtypes")
	public List getJuOrderListDB(Map keyName){		
		return bookOrderdao.getJuOrderListDB(keyName);
	}
	
	// 교재배송 리스트 (택배) 총 건수
	@Override @SuppressWarnings("rawtypes")
	public int getJuOrderListCount(Map keyName) {
		return bookOrderdao.getJuOrderListCount(keyName);
	}
	
	//2번째 리스트
	@Override @SuppressWarnings("rawtypes")
	public List getTblJuOrderMgntListDB(Map keyName){		
		return bookOrderdao.getTblJuOrderMgntListDB(keyName);
	}
		
	// 엑셀 리스트
	@Override
	public List<HashMap<String, String>> getJuOrderExelListDB(HashMap<String, String> params){
		return bookOrderdao.getJuOrderExelListDB(params);
	}	
	
	//전체상품주문관리 주문번호 상세
	@Override @SuppressWarnings("rawtypes")
	public List getTblApprovalsViewList(Map keyName){		
		return bookOrderdao.getTblApprovalsViewList(keyName);
	}
	
	//전체상품주문관리 주문번호 상세
	@Override @SuppressWarnings("rawtypes")
	public int getTblDeliversViewListCount(Map keyName) {
		return bookOrderdao.getTblDeliversViewListCount(keyName);
	}
	
	//전체상품주문관리 주문번호 상세
	@Override @SuppressWarnings("rawtypes")
	public List getTblDeliversViewList(Map keyName){		
		return bookOrderdao.getTblDeliversViewList(keyName);
	}
	
	//전체상품주문관리 주문번호 상세
	@Override @SuppressWarnings("rawtypes")
	public List getTblOrdersViewList(Map keyName){		
		return bookOrderdao.getTblOrdersViewList(keyName);
	}
	
	//전체상품주문관리 주문번호 상세
	@Override @SuppressWarnings("rawtypes")
	public List getLecMstViewList(Map keyName){		
		return bookOrderdao.getLecMstViewList(keyName);
	}
		
	@Override @SuppressWarnings("rawtypes")
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public int updateDelivers(Map keyName) {
		return bookOrderdao.updateDelivers(keyName);
	}
	
	@Override @SuppressWarnings("rawtypes")
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public int updateApprovals(Map keyName) {
		return bookOrderdao.updateApprovals(keyName);
	}
	
	@Override @SuppressWarnings("rawtypes")
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public int updateDeliversWmv(Map keyName) {
		return bookOrderdao.updateDeliversWmv(keyName);
	}
		
	
	@Override @SuppressWarnings("rawtypes")
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public int updateMoney1(Map keyName) {
		return bookOrderdao.updateMoney1(keyName);
	}
	
	@Override @SuppressWarnings("rawtypes")
	public int getMoneySum(Map keyName) {
		return bookOrderdao.getMoneySum(keyName);
	}
	
	@Override @SuppressWarnings("rawtypes")
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public int updateMoney2(Map keyName) {
		return bookOrderdao.updateMoney2(keyName);
	}
		
	@Override @SuppressWarnings("rawtypes")
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public int refund_money_delete(Map keyName) {
		return bookOrderdao.refund_money_delete(keyName);
	}
		
	//송장저장	
	@Override @SuppressWarnings("rawtypes")
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public int updateSendNo1(Map keyName) {
		return bookOrderdao.updateSendNo1(keyName);
	}
	
	//송장저장
	@Override @SuppressWarnings("rawtypes")
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public int updateSendNo2(Map keyName) {
		return bookOrderdao.updateSendNo2(keyName);
	}
	
	// 교재배송관리 'P' 팝업
	@Override @SuppressWarnings("rawtypes")
	public List getPopPrintView(Map keyName){		
		return bookOrderdao.getPopPrintView(keyName);
	}
	
	// 교재배송관리 'P' 팝업
	@Override @SuppressWarnings("rawtypes")
	public List getPopPrintInfoView(Map keyName){		
		return bookOrderdao.getPopPrintInfoView(keyName);
	}
	
	// 교재배송관리 'P' 팝업
	@Override @SuppressWarnings("rawtypes")
	public List getPopPrintOrderInfoView(Map keyName){		
		return bookOrderdao.getPopPrintOrderInfoView(keyName);
	}
	
	// 교재배송 리스트 (직접수령)
	@Override @SuppressWarnings("rawtypes")
	public List getJicOrderListDB(Map keyName){		
		return bookOrderdao.getJicOrderListDB(keyName);
	}
	
	// 교재배송 리스트 (직접수령) 총 건수
	@Override @SuppressWarnings("rawtypes")
	public int getJicOrderListCount(Map keyName) {
		return bookOrderdao.getJicOrderListCount(keyName);
	}
		
	//수령완료	
	@Override @SuppressWarnings("rawtypes")
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public int updateSendNo3(Map keyName) {
		return bookOrderdao.updateSendNo3(keyName);
	}
	
	//수령완료
	@Override @SuppressWarnings("rawtypes")
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public int updateSendNo4(Map keyName) {
		return bookOrderdao.updateSendNo4(keyName);
	}	
		
	// 교재매출내역 리스트
	@Override @SuppressWarnings("rawtypes")
	public List getTolOrderListDB(Map keyName){		
		return bookOrderdao.getTolOrderListDB(keyName);
	}
	
	// 교재매출내역 리스트 총 건수
	@Override @SuppressWarnings("rawtypes")
	public int getTolOrderListCount(Map keyName) {
		return bookOrderdao.getTolOrderListCount(keyName);
	}
	
	// 교재매출내역 리스트 2번째 리스트
	@Override @SuppressWarnings("rawtypes")
	public List getPutLecMstDB(Map keyName){		
		return bookOrderdao.getPutLecMstDB(keyName);
	}
		
	// 교재매출내역 리스트 엑셀 리스트
	@Override
	public List<HashMap<String, String>> getTolOrderExelListDB(HashMap<String, String> params){
		return bookOrderdao.getTolOrderExelListDB(params);
	}	
		
	/** 경찰 택배정보 조회  **/
	@Override @SuppressWarnings("rawtypes")
	public List getJuOrderListDB_COP(Map keyName){		
		return bookOrderdao.getJuOrderListDB_COP(keyName);
	}
	@Override @SuppressWarnings("rawtypes")
	public int getJuOrderListCount_COP(Map keyName) {
		return bookOrderdao.getJuOrderListCount_COP(keyName);
	}
	@Override @SuppressWarnings("rawtypes")
	public List getTblJuOrderMgntListDB_COP(Map keyName){		
		return bookOrderdao.getTblJuOrderMgntListDB_COP(keyName);
	}
	@Override
	public List<HashMap<String, String>> getJuOrderExelListDB_COP(HashMap<String, String> params){
		return bookOrderdao.getJuOrderExelListDB_COP(params);
	}	
	@Override @SuppressWarnings("rawtypes")
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public int updateSendNo1_COP(Map keyName) {
		return bookOrderdao.updateSendNo1_COP(keyName);
	}
	@Override @SuppressWarnings("rawtypes")
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public int updateSendNo2_COP(Map keyName) {
		return bookOrderdao.updateSendNo2_COP(keyName);
	}
	@Override @SuppressWarnings("rawtypes")
	public List getLecMstViewList_COP(Map keyName){		
		return bookOrderdao.getLecMstViewList_COP(keyName);
	}
	@Override @SuppressWarnings("rawtypes")
	public List getTblDeliversViewList_COP(Map keyName){		
		return bookOrderdao.getTblDeliversViewList_COP(keyName);
	}
	
}