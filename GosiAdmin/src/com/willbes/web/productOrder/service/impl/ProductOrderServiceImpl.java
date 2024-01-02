package com.willbes.web.productOrder.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.willbes.platform.util.file.service.MultipartFileService;
import com.willbes.web.productOrder.service.ProductOrderService;

@Service
public class ProductOrderServiceImpl  implements  ProductOrderService{
	
	/** log */
	/* last modified 2014-08-20 */
	
	private Logger log = Logger.getLogger(this.getClass());
	
	@Autowired
	private ProductOrderDAO productOrderdao;
	
	@Autowired
	private MultipartFileService multipartFileService;
	
	@Override @SuppressWarnings("rawtypes")
	public List getOrderStatuscodeList(Map keyName){		
		return productOrderdao.getOrderStatuscodeList(keyName);
	}
	
	@Override @SuppressWarnings("rawtypes")
	public List getPaymentList(Map keyName){		
		return productOrderdao.getPaymentList(keyName);
	}
	
	// 전체상품주문관리 리스트(0원)
	@Override @SuppressWarnings("rawtypes")
	public List getOrderListDB_0(Map keyName){		
		return productOrderdao.getOrderListDB_0(keyName);
	}
	
	@Override
	public List getOrderListDB_freelec(Map keyName) {
		return productOrderdao.getOrderListDB_freelec(keyName);
	}

	@Override
	public int getOrderListCount_freelec(Map keyName) {
		return productOrderdao.getOrderListCount_freelec(keyName);
	}
	
	// 전체상품주문관리 총 건수(0원)
	@Override @SuppressWarnings("rawtypes")
	public int getOrderListCount_0(Map keyName) {
		return productOrderdao.getOrderListCount_0(keyName);
	}
		
	// 전체상품주문관리 리스트
	@Override @SuppressWarnings("rawtypes")
	public List getOrderListDB(Map keyName){		
		return productOrderdao.getOrderListDB(keyName);
	}
	
	// 전체상품주문관리 총 건수
	@Override @SuppressWarnings("rawtypes")
	public int getOrderListCount(Map keyName) {
		return productOrderdao.getOrderListCount(keyName);
	}
	
	//2번째 리스트
	@Override @SuppressWarnings("rawtypes")
	public List getTblOrderMgntListDB(Map keyName){		
		return productOrderdao.getTblOrderMgntListDB(keyName);
	}
		
	// 전체상품주문관리 엑셀 리스트
	@Override
	public List<HashMap<String, String>> getOrderExcelListDB(HashMap<String, String> params){
		return productOrderdao.getOrderExcelListDB(params);
	}
		
	// 카드변경
	@Override @SuppressWarnings("rawtypes")
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public int setPayKindUpdate(Map keyName) {
		return productOrderdao.setPayKindUpdate(keyName);
	}
	
	
	
	
	
	
	// 입금상태저장
	@Override @SuppressWarnings("rawtypes")
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public int updateDepositStatus1(Map keyName) {
		return productOrderdao.updateDepositStatus1(keyName);
	}
	
	// 입금상태저장
	@Override @SuppressWarnings("rawtypes")
	public int getDepositStatusCount(Map keyName) {
		return productOrderdao.getDepositStatusCount(keyName);
	}
	
	// 입금상태저장
	@Override @SuppressWarnings("rawtypes")
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public int updateDepositStatus2(Map keyName) {
		return productOrderdao.updateDepositStatus2(keyName);
	}
	
	// 입금상태저장
	@Override @SuppressWarnings("rawtypes")
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public int updateDepositStatus3(Map keyName) {
		return productOrderdao.updateDepositStatus3(keyName);
	}
	
	// 입금상태저장
	@Override @SuppressWarnings("rawtypes")
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public int updateDepositStatus4(Map keyName) {
		return productOrderdao.updateDepositStatus4(keyName);
	}
	
	// 입금상태저장
	@Override @SuppressWarnings("rawtypes")
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public int updateDepositStatus5(Map keyName) {
		return productOrderdao.updateDepositStatus5(keyName);
	}
	
	// 도서 재고 더하기
	@Override @SuppressWarnings("rawtypes")
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public int updateBookStockPlus(Map keyName) {
		return productOrderdao.updateBookStockPlus(keyName);
	}
	
	
	
	
	
	
	
	
	
	
	//상품명 팝업
	@Override @SuppressWarnings("rawtypes")
	public List getPmpdownloadListPop(Map keyName){		
		return productOrderdao.getPmpdownloadListPop(keyName);
	}
	@Override @SuppressWarnings("rawtypes")
	public List getMobiledownloadListPop(Map keyName){		
		return productOrderdao.getMobiledownloadListPop(keyName);
	}
	
	//상품명 팝업
	@Override @SuppressWarnings("rawtypes")
	public List getTblOrderList(Map keyName){		
		return productOrderdao.getTblOrderList(keyName);
	}
		
	//상품명 팝업 리스트
	@Override @SuppressWarnings("rawtypes")
	public List getOrderListPopDB(Map keyName){		
		return productOrderdao.getOrderListPopDB(keyName);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	//구입금액
	@Override @SuppressWarnings("rawtypes")
	public List getCourse_type_code(Map keyName){		
		return productOrderdao.getCourse_type_code(keyName);
	}
	
	//구입금액
	@Override @SuppressWarnings("rawtypes")
	public List getPlayyn(Map keyName){		
		return productOrderdao.getPlayyn(keyName);
	}
	
	//구입금액
	@Override @SuppressWarnings("rawtypes")
	public List getPoint(Map keyName){		
		return productOrderdao.getPoint(keyName);
	}
	
	//구입금액
	@Override @SuppressWarnings("rawtypes")
	public List getTblOrderMgntNoPopViewList(Map keyName){		
		return productOrderdao.getTblOrderMgntNoPopViewList(keyName);
	}

	//구입금액
	@Override @SuppressWarnings("rawtypes")
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public int getTblOrderMgntNoPopViewCount(Map keyName) {
		return productOrderdao.getTblOrderMgntNoPopViewCount(keyName);
	}
	
	//구입금액
	@Override @SuppressWarnings("rawtypes")
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public int getPrice_Sum(Map keyName) {
		return productOrderdao.getPrice_Sum(keyName);
	}
	
	//구입금액
	@Override @SuppressWarnings("rawtypes")
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public int getOldRefundViewCount(Map keyName) {
		return productOrderdao.getOldRefundViewCount(keyName);
	}

	//구입금액
	@Override @SuppressWarnings("rawtypes")
	public List getRefund_Point(Map keyName){		
		return productOrderdao.getRefund_Point(keyName);
	}
	
	//구입금액
	@Override @SuppressWarnings("rawtypes")
	public List getOldRefundView(Map keyName){		
		return productOrderdao.getOldRefundView(keyName);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	//택배비
	@Override @SuppressWarnings("rawtypes")
	public List getTblDeliver_refund_list(Map keyName){		
		return productOrderdao.getTblDeliver_refund_list(keyName);
	}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	//전체상품주문관리 주문번호 상세
	@Override @SuppressWarnings("rawtypes")
	public List getTblApprovalsViewList(Map keyName){		
		return productOrderdao.getTblApprovalsViewList(keyName);
	}
	
	//전체상품주문관리 주문번호 상세
	@Override @SuppressWarnings("rawtypes")
	public int getTblDeliversViewListCount(Map keyName) {
		return productOrderdao.getTblDeliversViewListCount(keyName);
	}
	
	//전체상품주문관리 주문번호 상세
	@Override @SuppressWarnings("rawtypes")
	public List getTblDeliversViewList(Map keyName){		
		return productOrderdao.getTblDeliversViewList(keyName);
	}
	
	//전체상품주문관리 주문번호 상세
	@Override @SuppressWarnings("rawtypes")
	public List getTblOrdersViewList(Map keyName){		
		return productOrderdao.getTblOrdersViewList(keyName);
	}
	
	//전체상품주문관리 주문번호 상세
	@Override @SuppressWarnings("rawtypes")
	public List getLecMstViewList(Map keyName){		
		return productOrderdao.getLecMstViewList(keyName);
	}
		
	@Override @SuppressWarnings("rawtypes")
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public int updateDelivers(Map keyName) {
		return productOrderdao.updateDelivers(keyName);
	}
	
	@Override @SuppressWarnings("rawtypes")
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public int updateApprovals(Map keyName) {
		return productOrderdao.updateApprovals(keyName);
	}
	
	@Override @SuppressWarnings("rawtypes")
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public int updateDeliversWmv(Map keyName) {
		return productOrderdao.updateDeliversWmv(keyName);
	}
		
	
	@Override @SuppressWarnings("rawtypes")
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public int updateMoney1(Map keyName) {
		return productOrderdao.updateMoney1(keyName);
	}
	
	@Override @SuppressWarnings("rawtypes")
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public int updateStudy_Per(Map keyName) {
		return productOrderdao.updateStudy_Per(keyName);
	}
	
	@Override @SuppressWarnings("rawtypes")
	public int getMoneySum(Map keyName) {
		return productOrderdao.getMoneySum(keyName);
	}
	
	@Override @SuppressWarnings("rawtypes")
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public int updateMoney2(Map keyName) {
		return productOrderdao.updateMoney2(keyName);
	}
		
		
		
	@Override @SuppressWarnings("rawtypes")
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public int refund_money_delete(Map keyName) {
		return productOrderdao.refund_money_delete(keyName);
	}	
		
		
		
		
	
	
	
	
	
	
	
	
	
	
	@Override @SuppressWarnings("rawtypes")
	public List getMylecture(Map keyName){		
		return productOrderdao.getMylecture(keyName);
	}
	
	@Override @SuppressWarnings("rawtypes")
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public int updateMylecture1(Map keyName) {
		return productOrderdao.updateMylecture1(keyName);
	}
	
	@Override @SuppressWarnings("rawtypes")
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public int updateMylecture2(Map keyName) {
		return productOrderdao.updateMylecture2(keyName);
	}
	
	@Override @SuppressWarnings("rawtypes")
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public int updateMylecture3(Map keyName) {
		return productOrderdao.updateMylecture3(keyName);
	}
	
	@Override @SuppressWarnings("rawtypes")
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public int updateMylecture4(Map keyName) {
		return productOrderdao.updateMylecture4(keyName);
	}

	@Override @SuppressWarnings("rawtypes")
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public int updateEndDateMyLecture(Map keyName) {
		return productOrderdao.updateEndDateMyLecture(keyName);
	}
	
	@Override @SuppressWarnings("rawtypes")
	public int getMylectureCount(Map keyName) {
		return productOrderdao.getMylectureCount(keyName);
	}
		
	@Override @SuppressWarnings("rawtypes")
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public int updateMylecture5(Map keyName) {
		return productOrderdao.updateMylecture5(keyName);
	}	
		
	@Override @SuppressWarnings("rawtypes")
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public int insertMylecture6(Map keyName) {
		return productOrderdao.insertMylecture6(keyName);
	}	
		
		
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Override @SuppressWarnings("rawtypes")
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public int insertOrderMgntNo1(Map keyName) {
		return productOrderdao.insertOrderMgntNo1(keyName);
	}
	@Override @SuppressWarnings("rawtypes")
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public int updateOrderMgntNo2(Map keyName) {
		return productOrderdao.updateOrderMgntNo2(keyName);
	}
	@Override @SuppressWarnings("rawtypes")
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public int updateOrderMgntNo3(Map keyName) {
		return productOrderdao.updateOrderMgntNo3(keyName);
	}
	
	@Override @SuppressWarnings("rawtypes")
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public int insertOrderMgntNo2(Map keyName) {
		return productOrderdao.insertOrderMgntNo2(keyName);
	}
	
	@Override @SuppressWarnings("rawtypes")
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public int updateApprovals2(Map keyName) {
		return productOrderdao.updateApprovals2(keyName);
	}
	
	@Override @SuppressWarnings("rawtypes")
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public int insertMileageHistory(Map keyName) {
		return productOrderdao.insertMileageHistory(keyName);
	}
	
	@Override @SuppressWarnings("rawtypes")
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public int insertMileageHistory3(Map keyName) {
		return productOrderdao.insertMileageHistory3(keyName);
	}
	
	@Override @SuppressWarnings("rawtypes")
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public int updateMaMember(Map keyName) {
		return productOrderdao.updateMaMember(keyName);
	}
	
	@Override @SuppressWarnings("rawtypes")
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public int updateMylecture_1(Map keyName) {
		return productOrderdao.updateMylecture_1(keyName);
	}
	
	@Override @SuppressWarnings("rawtypes")
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public int updateMylecture_2(Map keyName) {
		return productOrderdao.updateMylecture_2(keyName);
	}
	
	
	
	
	
	@Override @SuppressWarnings("rawtypes")
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public int updateOrderMgntNo1(Map keyName) {
		return productOrderdao.updateOrderMgntNo1(keyName);
	}
	
	@Override @SuppressWarnings("rawtypes")
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public int updateMileageHistory(Map keyName) {
		return productOrderdao.updateMileageHistory(keyName);
	}
	
	@Override @SuppressWarnings("rawtypes")
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public int updateMaMember2(Map keyName) {
		return productOrderdao.updateMaMember2(keyName);
	}
	
	@Override @SuppressWarnings("rawtypes")
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public int updateApprovals3(Map keyName) {
		return productOrderdao.updateApprovals3(keyName);
	}
	
	@Override @SuppressWarnings("rawtypes")
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public int deleteOrderMgntNo(Map keyName) {
		return productOrderdao.deleteOrderMgntNo(keyName);
	}
	
	@Override @SuppressWarnings("rawtypes")
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public int updateMaMember3(Map keyName) {
		return productOrderdao.updateMaMember3(keyName);
	}
	
	@Override @SuppressWarnings("rawtypes")
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public int updateApprovals4(Map keyName) {
		return productOrderdao.updateApprovals4(keyName);
	}
	
	@Override @SuppressWarnings("rawtypes")
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public int insertMileageHistory2(Map keyName) {
		return productOrderdao.insertMileageHistory2(keyName);
	}
	
	@Override @SuppressWarnings("rawtypes")
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public int updateMylecture_3(Map keyName) {
		return productOrderdao.updateMylecture_3(keyName);
	}
	
	@Override @SuppressWarnings("rawtypes")
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public int updateApprovals5(Map keyName) {
		return productOrderdao.updateApprovals5(keyName);
	}
	
	
	
	
	
	//SMS보내기
	@Override @SuppressWarnings("rawtypes")
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public int insertSendMsgMultiSendUser(Map keyName) {
		return productOrderdao.insertSendMsgMultiSendUser(keyName);
	}
	
	
	
	
	// 사용자 팝업
	@Override @SuppressWarnings("rawtypes")
	public List getTmMember_View(Map keyName){		
		return productOrderdao.getTmMember_View(keyName);
	}
	
	// 사용자 팝업
	@Override @SuppressWarnings("rawtypes")
	public List getCsccode(Map keyName){		
		return productOrderdao.getCsccode(keyName);
	}
	
	// 사용자 팝업
	@Override @SuppressWarnings("rawtypes")
	public List Cs_board_list(Map keyName){		
		return productOrderdao.Cs_board_list(keyName);
	}
	
	// 사용자 팝업
	@Override @SuppressWarnings("rawtypes")
	public int getCsBoardListCount(Map keyName) {
		return productOrderdao.getCsBoardListCount(keyName);
	}
	
	@Override @SuppressWarnings("rawtypes")
	public int getSelectBefore_Point(Map keyName) {
		return productOrderdao.getSelectBefore_Point(keyName);
	}

	// 사용자 팝업
	@Override @SuppressWarnings("rawtypes")
	public List getTm_mycoupon_list_admin(Map keyName){		
		return productOrderdao.getTm_mycoupon_list_admin(keyName);
	}
	
	// 사용자 팝업
	@Override @SuppressWarnings("rawtypes")
	public int getTm_mycoupon_listCount_admin(Map keyName) {
		return productOrderdao.getTm_mycoupon_listCount_admin(keyName);
	}
	
	// 사용자 팝업
	@Override @SuppressWarnings("rawtypes")
	public List Tm_Class_List(Map keyName){		
		return productOrderdao.Tm_Class_List(keyName);
	}
	
	// 사용자 팝업
	@Override @SuppressWarnings("rawtypes")
	public List Off_Class_List(Map keyName){		
		return productOrderdao.Off_Class_List(keyName);
	}
	
	// 사용자 팝업
	@Override @SuppressWarnings("rawtypes")
	public int getTmClassListCount(Map keyName) {
		return productOrderdao.getTmClassListCount(keyName);
	}
	
	// 사용자 팝업
	@Override @SuppressWarnings("rawtypes")
	public List getMemoList(Map keyName){		
		return productOrderdao.getMemoList(keyName);
	}
	
	// 사용자 팝업
	@Override @SuppressWarnings("rawtypes")
	public int getMemoListCount(Map keyName) {
		return productOrderdao.getMemoListCount(keyName);
	}
	
	
	@Override @SuppressWarnings("rawtypes")
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public int insertBoardCs(Map keyName) {
		return productOrderdao.insertBoardCs(keyName);
	}
	
	@Override @SuppressWarnings("rawtypes")
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public int updateMemo(Map keyName) {
		return productOrderdao.updateMemo(keyName);
	}
	
	
	
	// 사용자 팝업
	@Override @SuppressWarnings("rawtypes")
	public List getTmCoupon(Map keyName){		
		return productOrderdao.getTmCoupon(keyName);
	}
	// 사용자 팝업
	@Override @SuppressWarnings("rawtypes")
	public List getTmCouponList(Map keyName){		
		return productOrderdao.getTmCouponList(keyName);
	}
	
	// 사용자 팝업
	@Override @SuppressWarnings("rawtypes")
	public List getTmMoCouponList(Map keyName){		
		return productOrderdao.getTmMoCouponList(keyName);
	}
	
	// 사용자 팝업
	@Override @SuppressWarnings("rawtypes")
	public int getTmCouponCount(Map keyName) {
		return productOrderdao.getTmCouponCount(keyName);
	}
	// 사용자 팝업
	@Override @SuppressWarnings("rawtypes")
	public int getTmMoCouponCount(Map keyName) {
		return productOrderdao.getTmMoCouponCount(keyName);
	}
	
	// 사용자 팝업
	@Override @SuppressWarnings("rawtypes")
	public int getCouponCount(Map keyName) {
		return productOrderdao.getCouponCount(keyName);
	}

	@Override @SuppressWarnings("rawtypes")
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public int insertTmCoupon(Map keyName) {
		return productOrderdao.insertTmCoupon(keyName);
	}

	@Override @SuppressWarnings("rawtypes")
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public int insertMyCoupon(Map keyName) {
		return productOrderdao.insertMyCoupon(keyName);
	}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	
	
	// off 전체상품주문관리 리스트
	@Override @SuppressWarnings("rawtypes")
	public List getOffOrderListDB(Map keyName){		
		return productOrderdao.getOffOrderListDB(keyName);
	}
	
	// off 전체상품주문관리 총 건수
	@Override @SuppressWarnings("rawtypes")
	public int getOffOrderListCount(Map keyName) {
		return productOrderdao.getOffOrderListCount(keyName);
	}
	
	//off 2번째 리스트
	@Override @SuppressWarnings("rawtypes")
	public List getOffTblOrderMgntListDB(Map keyName){		
		return productOrderdao.getOffTblOrderMgntListDB(keyName);
	}
		
	// off 전체상품주문관리 엑셀 리스트
	@Override
	public List<HashMap<String, String>> getOffOrderExcelListDB(HashMap<String, String> params){
		return productOrderdao.getOffOrderExcelListDB(params);
	}
	
	//id체크
	@Override @SuppressWarnings("rawtypes")
	public List getIdChk(Map keyName){		
		return productOrderdao.getIdChk(keyName);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	// 품목변경 팝업 카테고리 셀렉트박스 리스트
	@Override @SuppressWarnings("rawtypes")
	public List getCaCatCdList(Map keyName){		
		return productOrderdao.getCaCatCdList(keyName);
	}
	
	// 품목변경 팝업 학습형태 셀렉트박스 리스트
	@Override @SuppressWarnings("rawtypes")
	public List getVwMenuMstTree_lec(Map keyName){		
		return productOrderdao.getVwMenuMstTree_lec(keyName);
	}
	
	// 품목변경 팝업 과목 셀렉트박스 리스트
	@Override @SuppressWarnings("rawtypes")
	public List getCaSubjectCdList(Map keyName){		
		return productOrderdao.getCaSubjectCdList(keyName);
	}
	
	// 품목변경 팝업  리스트
	@Override @SuppressWarnings("rawtypes")
	public List getCbLecMstFreeOrderList(Map keyName){		
		return productOrderdao.getCbLecMstFreeOrderList(keyName);
	}
		
	// 품목변경 팝업  카운트
	@Override @SuppressWarnings("rawtypes")
	public int getCbLecMstListFreeOrderCount(Map keyName) {
		return productOrderdao.getCbLecMstListFreeOrderCount(keyName);
	}
	
	
	
	@Override @SuppressWarnings("rawtypes")
	public List getChangePrice(Map keyName){		
		return productOrderdao.getChangePrice(keyName);
	}
	
	@Override @SuppressWarnings("rawtypes")
	public List getUCount(Map keyName){		
		return productOrderdao.getUCount(keyName);
	}
	
	@Override @SuppressWarnings("rawtypes")
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public int insertCart(Map keyName) {
		return productOrderdao.insertCart(keyName);
	}
	
	// 장바구니 리스트
	@Override @SuppressWarnings("rawtypes")
	public List getSubCode2(Map keyName){		
		return productOrderdao.getSubCode2(keyName);
	}
	
	@Override @SuppressWarnings("rawtypes")
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public int deleteCart(Map keyName) {
		return productOrderdao.deleteCart(keyName);
	}
	
	@Override @SuppressWarnings("rawtypes")
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public int deleteAllCart(Map keyName) {
		return productOrderdao.deleteAllCart(keyName);
	}
	
	// 장바구니 리스트
	@Override @SuppressWarnings("rawtypes")
	public List getSubList(Map keyName){		
		return productOrderdao.getSubList(keyName);
	}
	
	
	
	
	
	
	
	
	
	
	@Override @SuppressWarnings("rawtypes")
	public List getMCount(Map keyName){		
		return productOrderdao.getMCount(keyName);
	}
	
	@Override @SuppressWarnings("rawtypes")
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public int insertOffOrders(Map keyName) {
		return productOrderdao.insertOffOrders(keyName);
	}
	
	@Override @SuppressWarnings("rawtypes")
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public int insertOffApprovals(Map keyName) {
		return productOrderdao.insertOffApprovals(keyName);
	}
	
	@Override @SuppressWarnings("rawtypes")
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public int insertOffOrderMgntNo(Map keyName) {
		return productOrderdao.insertOffOrderMgntNo(keyName);
	}
	
	@Override @SuppressWarnings("rawtypes")
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public int insertOffMylecture(Map keyName) {
		return productOrderdao.insertOffMylecture(keyName);
	}
	
	@Override @SuppressWarnings("rawtypes")
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public int modifyOffMylecture(Map keyName) {
		return productOrderdao.modifyOffMylecture(keyName);
	}
	
	@Override @SuppressWarnings("rawtypes")
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public int insertOffMylecture_N(Map keyName) {
		return productOrderdao.insertOffMylecture_N(keyName);
	}
	
	
	@Override @SuppressWarnings("rawtypes")
	public List getUpdateDetail(Map keyName){		
		return productOrderdao.getUpdateDetail(keyName);
	}
	
	@Override @SuppressWarnings("rawtypes")
	public List getSubCodeUp(Map keyName){		
		return productOrderdao.getSubCodeUp(keyName);
	}
	
	
	
	
	
	@Override @SuppressWarnings("rawtypes")
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public int updateOffOrders(Map keyName) {
		return productOrderdao.updateOffOrders(keyName);
	}
	
	@Override @SuppressWarnings("rawtypes")
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public int Coupon_Del(Map keyName) {
		return productOrderdao.Coupon_Del(keyName);
	}
	
	@Override @SuppressWarnings("rawtypes")
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public int updateOffApprovals(Map keyName) {
		return productOrderdao.updateOffApprovals(keyName);
	}
	
	@Override @SuppressWarnings("rawtypes")
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public int updateOffMgntNo(Map keyName) {
		return productOrderdao.updateOffMgntNo(keyName);
	}
	
	@Override @SuppressWarnings("rawtypes")
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public int updateOffMgntNo_N(Map keyName) {
		return productOrderdao.updateOffMgntNo_N(keyName);
	}
	
	@Override @SuppressWarnings("rawtypes")
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public int deleteOffOrderMgntNo(Map keyName) {
		return productOrderdao.deleteOffOrderMgntNo(keyName);
	}
	
	@Override @SuppressWarnings("rawtypes")
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public int deleteOffMylecture(Map keyName) {
		return productOrderdao.deleteOffMylecture(keyName);
	}
	
	@Override @SuppressWarnings("rawtypes")
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public int deleteOffOrders(Map keyName) {
		return productOrderdao.deleteOffOrders(keyName);
	}
	
	@Override @SuppressWarnings("rawtypes")
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public int deleteOffApprovals(Map keyName) {
		return productOrderdao.deleteOffApprovals(keyName);
	}
	
	
	@Override @SuppressWarnings("rawtypes")
	public List getPrintPop(Map keyName){		
		return productOrderdao.getPrintPop(keyName);
	}
	
	@Override @SuppressWarnings("rawtypes")
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public int updatePrintOffOrders1(Map keyName) {
		return productOrderdao.updatePrintOffOrders1(keyName);
	}
	
	@Override @SuppressWarnings("rawtypes")
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public int updatePrintOffOrders2(Map keyName) {
		return productOrderdao.updatePrintOffOrders2(keyName);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Override @SuppressWarnings("rawtypes")
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public int updateOffRefund(Map keyName) {
		return productOrderdao.updateOffRefund(keyName);
	}
	
	@Override @SuppressWarnings("rawtypes")
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public int insertRefundOffOrderMgntNo(Map keyName) {
		return productOrderdao.insertRefundOffOrderMgntNo(keyName);
	}
	
	@Override @SuppressWarnings("rawtypes")
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public int updateOffMylecture(Map keyName) {
		return productOrderdao.updateOffMylecture(keyName);
	}
	
	@Override @SuppressWarnings("rawtypes")
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public int updateOffMylectureRefund(Map keyName) {
		return productOrderdao.updateOffMylectureRefund(keyName);
	}
	
	@Override @SuppressWarnings("rawtypes")
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public int insertOffRefund(Map keyName) {
		return productOrderdao.insertOffRefund(keyName);
	}
	
	
	@Override @SuppressWarnings("rawtypes")
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public int deleteOffCancelOrderMgntNo(Map keyName) {
		return productOrderdao.deleteOffCancelOrderMgntNo(keyName);
	}
	
	@Override @SuppressWarnings("rawtypes")
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public int deleteOffCancelRefund(Map keyName) {
		return productOrderdao.deleteOffCancelRefund(keyName);
	}
	
	@Override @SuppressWarnings("rawtypes")
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public int updateOffCancelMylecture(Map keyName) {
		return productOrderdao.updateOffCancelMylecture(keyName);
	}

	@Override
	public List<HashMap<String, String>> getTmPointHistory(HashMap<String, String> params) {
		return productOrderdao.getTmPointHistory(params);
	}

	@Override
	public void insertTmBoard(Map<String, Object> searchMap) {
		 productOrderdao.insertTmBoard(searchMap);
	}

	@Override
	public List<HashMap<String, String>> getTmBoardList(Map<String, Object> searchMap) {
		return productOrderdao.getTmBoardList(searchMap);
	}

	@Override
	public List<HashMap<String, String>> getVOCCODEList(HashMap<String, String> params) {
		return productOrderdao.getVOCCODEList(params);
	}

	@Override
	public List<HashMap<String, String>> getDUTYCODEList(HashMap<String, String> params) {
		return productOrderdao.getDUTYCODEList(params);
	}

	@Override
	public List<HashMap<String, String>> getOffApprovalsCount(Map<String, String> searchMap) {
		return  productOrderdao.getOffApprovalsCount(searchMap);
	}
	
	@Override
	public List<HashMap<String, String>> getCcode(HashMap<String, String> params){
		return productOrderdao.getCcode(params);
	}

	// 내 강의실 누락 강의 등록 2016-01-18	
	@Override
	public void insertMyLecture(Map<String, String> params) {
		 productOrderdao.insertMyLecture(params);
	}

	@Override
	public void insertMyLectureN(Map<String, String> params) {
		 productOrderdao.insertMyLectureN(params);
	}
	
	@Override @SuppressWarnings("rawtypes")
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public int Delete_Year_Package_Point(Map keyName) {
		return productOrderdao.Delete_Year_Package_Point(keyName);
	}
	
	@Override @SuppressWarnings("rawtypes")
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public int getPlusPoint(Map keyName) {
		return productOrderdao.getPlusPoint(keyName);
	}
	
	@Override
    public void BookPointDel(Map<String, Object> params){
    	productOrderdao.BookPointDel(params);
    }
    
    @Override
    public void BookPointIns(Map<String, Object> params){
    	productOrderdao.BookPointIns(params);
    }
    
	// 학원 주문정보 변경 로그 관리 2017-05-24	
    @Override
    public void insertOffOrderLog(Map<String, Object> params){
    	productOrderdao.insertOffOrderLog(params);
    }
}
