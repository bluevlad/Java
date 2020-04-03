package web.productOrder.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface ProductOrderService {
	
	/* last modified 2014-08-20 */

	@SuppressWarnings({ "rawtypes" })
	List getOrderStatuscodeList(Map keyName);
	
	@SuppressWarnings({ "rawtypes" })
	List getPaymentList(Map keyName);
	
	// 전체상품주문관리 리스트(0원)
	@SuppressWarnings({ "rawtypes" })
	List getOrderListDB_0(Map keyName);
	
	// 전체상품주문관리 총 건수(0원)
	@SuppressWarnings({ "rawtypes" })
	int getOrderListCount_0(Map keyName);
		
	// 전체상품주문관리 리스트
	@SuppressWarnings({ "rawtypes" })
	List getOrderListDB(Map keyName);
	
	// 전체상품주문관리 총 건수
	@SuppressWarnings({ "rawtypes" })
	int getOrderListCount(Map keyName);
	
	// 전체상품주문관리 리스트(0원)
	@SuppressWarnings({ "rawtypes" })
	List getOrderListDB_freelec(Map keyName);
	
	// 전체상품주문관리 총 건수(0원)
	@SuppressWarnings({ "rawtypes" })
	int getOrderListCount_freelec(Map keyName);
	
	//2번째 리스트
	@SuppressWarnings({ "rawtypes" })
	List getTblOrderMgntListDB(Map keyName);
		
	// 전체상품주문관리 엑셀 리스트
	@SuppressWarnings({ "rawtypes" })
	List<HashMap<String, String>> getOrderExcelListDB(HashMap<String, String> params);
		
	// 카드변경
	@SuppressWarnings({ "rawtypes" })
	int setPayKindUpdate(Map keyName);
	
	
	
	
	
	// 입금상태저장
	@SuppressWarnings({ "rawtypes" })
	int updateDepositStatus1(Map keyName);
	
	// 입금상태저장
	@SuppressWarnings({ "rawtypes" })
	int getDepositStatusCount(Map keyName);
	
	// 입금상태저장
	@SuppressWarnings({ "rawtypes" })
	int updateDepositStatus2(Map keyName);
	
	// 입금상태저장
	@SuppressWarnings({ "rawtypes" })
	int updateDepositStatus3(Map keyName);
	
	// 입금상태저장
	@SuppressWarnings({ "rawtypes" })
	int updateDepositStatus4(Map keyName);
	
	// 입금상태저장
	@SuppressWarnings({ "rawtypes" })
	int updateDepositStatus5(Map keyName);
	
	//도서 재고 더하기
	@SuppressWarnings({ "rawtypes" })
	int updateBookStockPlus(Map keyName);
	
	
	
	
	
	
	
	//상품명 팝업
	@SuppressWarnings({ "rawtypes" })
	List getPmpdownloadListPop(Map keyName);
	@SuppressWarnings({ "rawtypes" })
	List getMobiledownloadListPop(Map keyName);
		
	//상품명 팝업
	@SuppressWarnings({ "rawtypes" })
	List getTblOrderList(Map keyName);
	
	//상품명 팝업 리스트
	@SuppressWarnings({ "rawtypes" })
	List getOrderListPopDB(Map keyName);
	
	
	
	
	
	
	
	
	
	//구입금액
	@SuppressWarnings({ "rawtypes" })
	List getCourse_type_code(Map keyName);
	
	//구입금액
	@SuppressWarnings({ "rawtypes" })
	List getPlayyn(Map keyName);
	
	//구입금액
	@SuppressWarnings({ "rawtypes" })
	List getPoint(Map keyName);
	
	//구입금액
	@SuppressWarnings({ "rawtypes" })
	List getTblOrderMgntNoPopViewList(Map keyName);

	//구입금액
	@SuppressWarnings({ "rawtypes" })
	int getTblOrderMgntNoPopViewCount(Map keyName);
	
	//구입금액
	@SuppressWarnings({ "rawtypes" })
	int getPrice_Sum(Map keyName);
	
	//구입금액
	@SuppressWarnings({ "rawtypes" })
	int getOldRefundViewCount(Map keyName);
	
	//구입금액
	@SuppressWarnings({ "rawtypes" })
	List getRefund_Point(Map keyName);
	
	//구입금액
	@SuppressWarnings({ "rawtypes" })
	List getOldRefundView(Map keyName);
	
	
	
	//택배비
	@SuppressWarnings({ "rawtypes" })
	List getTblDeliver_refund_list(Map keyName);
		
		
		
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
	int updateStudy_Per(Map keyName);
	
	@SuppressWarnings({ "rawtypes" })
	int getMoneySum(Map keyName);
	
	@SuppressWarnings({ "rawtypes" })
	int updateMoney2(Map keyName);
		
		
	@SuppressWarnings({ "rawtypes" })
	int refund_money_delete(Map keyName);	
		
	
	
	@SuppressWarnings({ "rawtypes" })
	List getMylecture(Map keyName);
		
	@SuppressWarnings({ "rawtypes" })
	int updateMylecture1(Map keyName);
	
	@SuppressWarnings({ "rawtypes" })
	int updateMylecture2(Map keyName);
	
	@SuppressWarnings({ "rawtypes" })
	int updateMylecture3(Map keyName);
	
	@SuppressWarnings({ "rawtypes" })
	int updateMylecture4(Map keyName);
		
	@SuppressWarnings({ "rawtypes" })	
	int updateEndDateMyLecture(Map keyName); 
	
	@SuppressWarnings({ "rawtypes" })
	int getMylectureCount(Map keyName);	
		
	@SuppressWarnings({ "rawtypes" })
	int updateMylecture5(Map keyName);	
		
	@SuppressWarnings({ "rawtypes" })
	int insertMylecture6(Map keyName);	
	
	@SuppressWarnings({ "rawtypes" })
	int insertOrderMgntRefund(Map keyName);
	@SuppressWarnings({ "rawtypes" })
	int insertOrderMgntNo1(Map keyName);
	@SuppressWarnings({ "rawtypes" })
	int updateOrderMgntNo2(Map keyName);
	@SuppressWarnings({ "rawtypes" })
	int updateOrderMgntNo3(Map keyName);
	
	@SuppressWarnings({ "rawtypes" })
	int insertOrderMgntNo2(Map keyName);
		
	@SuppressWarnings({ "rawtypes" })
	int updateApprovals2(Map keyName);
	
	@SuppressWarnings({ "rawtypes" })
	int insertMileageHistory(Map keyName);
	
	@SuppressWarnings({ "rawtypes" })
	int insertMileageHistory3(Map keyName);
	
	@SuppressWarnings({ "rawtypes" })
	int updateMaMember(Map keyName);
	
	@SuppressWarnings({ "rawtypes" })
	int updateMylecture_1(Map keyName);
	
	@SuppressWarnings({ "rawtypes" })
	int updateMylecture_2(Map keyName);
	
	
	
	
	
	@SuppressWarnings({ "rawtypes" })
	int updateOrderMgntNo1(Map keyName);
	
	@SuppressWarnings({ "rawtypes" })
	int updateMileageHistory(Map keyName);
	
	@SuppressWarnings({ "rawtypes" })
	int updateMaMember2(Map keyName);
	
	@SuppressWarnings({ "rawtypes" })
	int updateApprovals3(Map keyName);
	
	@SuppressWarnings({ "rawtypes" })
	int deleteOrderMgntNo(Map keyName);
	
	@SuppressWarnings({ "rawtypes" })
	int updateMaMember3(Map keyName);
	
	@SuppressWarnings({ "rawtypes" })
	int updateApprovals4(Map keyName);
	
	@SuppressWarnings({ "rawtypes" })
	int insertMileageHistory2(Map keyName);
	
	@SuppressWarnings({ "rawtypes" })
	int updateMylecture_3(Map keyName);
	
	@SuppressWarnings({ "rawtypes" })
	int updateApprovals5(Map keyName);
	
	
	
	
	
	//SMS보내기
	@SuppressWarnings({ "rawtypes" })
	int insertSendMsgMultiSendUser(Map keyName);
	
	
	
	
	
	// 사용자 팝업
	@SuppressWarnings({ "rawtypes" })
	List getTmMember_View(Map keyName);
	
	// 사용자 팝업
	@SuppressWarnings({ "rawtypes" })
	List getCsccode(Map keyName);
	
	// 사용자 팝업
	@SuppressWarnings({ "rawtypes" })
	List Cs_board_list(Map keyName);
	
	// 사용자 팝업
	@SuppressWarnings({ "rawtypes" })
	int getCsBoardListCount(Map keyName);
	
	// 사용자 팝업
	@SuppressWarnings({ "rawtypes" })
	List getTm_mycoupon_list_admin(Map keyName);
	
	// 사용자 팝업
	@SuppressWarnings({ "rawtypes" })
	int getTm_mycoupon_listCount_admin(Map keyName);
	
	// 사용자 팝업 - 동영상 강의 리스트
	@SuppressWarnings({ "rawtypes" })
	List Tm_Class_List(Map keyName);
	
	// 사용자 팝업 - 학원강의 리스트
	@SuppressWarnings({ "rawtypes" })
	List Off_Class_List(Map keyName);
	
	// 사용자 팝업
	@SuppressWarnings({ "rawtypes" })
	int getTmClassListCount(Map keyName);
	
	// 사용자 팝업
	@SuppressWarnings({ "rawtypes" })
	List getMemoList(Map keyName);
	
	// 사용자 팝업
	@SuppressWarnings({ "rawtypes" })
	int getMemoListCount(Map keyName);
	
	
	@SuppressWarnings({ "rawtypes" })
	int insertBoardCs(Map keyName);
	
	@SuppressWarnings({ "rawtypes" })
	int updateMemberPoint(Map keyName);
	
	@SuppressWarnings({ "rawtypes" })
	int updateMemo(Map keyName);
	
	@SuppressWarnings({ "rawtypes" })
	int getSelectBefore_Point(Map keyName);
	
	// 사용자 팝업
	@SuppressWarnings({ "rawtypes" })
	List getTmCoupon(Map keyName);
	
	
	// 사용자 팝업
	@SuppressWarnings({ "rawtypes" })
	List getTmCouponList(Map keyName);
	// 사용자 팝업
	@SuppressWarnings({ "rawtypes" })
	List getTmMoCouponList(Map keyName);
	
	// 사용자 팝업
	@SuppressWarnings({ "rawtypes" })
	int getTmCouponCount(Map keyName);
	// 사용자 팝업
	@SuppressWarnings({ "rawtypes" })
	int getTmMoCouponCount(Map keyName);
	
	// 사용자 팝업
	@SuppressWarnings({ "rawtypes" })
	int getCouponCount(Map keyName);

	@SuppressWarnings({ "rawtypes" })
	int insertTmCoupon(Map keyName);
		
	@SuppressWarnings({ "rawtypes" })
	int insertMyCoupon(Map keyName);
		
	
	
	
	
	// off 전체상품주문관리 리스트
	@SuppressWarnings({ "rawtypes" })
	List getOffOrderListDB(Map keyName);
	
	// off 전체상품주문관리 총 건수
	@SuppressWarnings({ "rawtypes" })
	int getOffOrderListCount(Map keyName);
	
	//off 2번째 리스트
	@SuppressWarnings({ "rawtypes" })
	List getOffTblOrderMgntListDB(Map keyName);
		
	// off 전체상품주문관리 엑셀 리스트
	@SuppressWarnings({ "rawtypes" })
	List<HashMap<String, String>> getOffOrderExcelListDB(HashMap<String, String> params);
	
	//id체크
	@SuppressWarnings({ "rawtypes" })
	List getIdChk(Map keyName);
	
	
	
	
	
	
	
	
	
	// 품목변경 팝업 카테고리 셀렉트박스 리스트
	@SuppressWarnings({ "rawtypes" })
	List getCaCatCdList(Map keyName);
	
	// 품목변경 팝업 학습형태 셀렉트박스 리스트
	@SuppressWarnings({ "rawtypes" })
	List getVwMenuMstTree_lec(Map keyName);
	
	// 품목변경 팝업 과목 셀렉트박스 리스트
	@SuppressWarnings({ "rawtypes" })
	List getCaSubjectCdList(Map keyName);
	
	// 품목변경 팝업  리스트
	@SuppressWarnings({ "rawtypes" })
	List getCbLecMstFreeOrderList(Map keyName);
		
	// 품목변경 팝업  카운트
	@SuppressWarnings({ "rawtypes" })
	int getCbLecMstListFreeOrderCount(Map keyName);
	
	
	@SuppressWarnings({ "rawtypes" })
	List getChangePrice(Map keyName);
	
	@SuppressWarnings({ "rawtypes" })
	List getUCount(Map keyName);
	
	@SuppressWarnings({ "rawtypes" })
	int insertCart(Map keyName);
	
	@SuppressWarnings({ "rawtypes" })
	List getSubCode2(Map keyName);
	
	@SuppressWarnings({ "rawtypes" })
	int deleteCart(Map keyName);
	
	@SuppressWarnings({ "rawtypes" })
	int deleteAllCart(Map keyName);
	
	
	
	
	
	@SuppressWarnings({ "rawtypes" })
	List getMCount(Map keyName);
	
	@SuppressWarnings({ "rawtypes" })
	int insertOffOrders(Map keyName);
	
	@SuppressWarnings({ "rawtypes" })
	int insertOffApprovals(Map keyName);
	
	@SuppressWarnings({ "rawtypes" })
	int insertOffOrderMgntNo(Map keyName);
	
	@SuppressWarnings({ "rawtypes" })
	int insertOffMylecture(Map keyName);
	
	@SuppressWarnings({ "rawtypes" })
	int modifyOffMylecture(Map keyName);
	
	@SuppressWarnings({ "rawtypes" })
	int insertOffMylecture_N(Map keyName);
	
	
	@SuppressWarnings({ "rawtypes" })
	List getUpdateDetail(Map keyName);
	
	@SuppressWarnings({ "rawtypes" })
	List getSubLec(Map keyName);
	
	@SuppressWarnings({ "rawtypes" })
	List getSubCodeUp(Map keyName);
	
	@SuppressWarnings({ "rawtypes" })
	List getSubList(Map keyName);
	
	
	@SuppressWarnings({ "rawtypes" })
	int updateOffOrders(Map keyName);
	
	@SuppressWarnings({ "rawtypes" })
	int Coupon_Del(Map keyName);
	
	@SuppressWarnings({ "rawtypes" })
	int updateOffApprovals(Map keyName);
	
	@SuppressWarnings({ "rawtypes" })
	int updateOffMgntNo(Map keyName);
	
	@SuppressWarnings({ "rawtypes" })
	int updateOffMgntNo_N(Map keyName);
	
	@SuppressWarnings({ "rawtypes" })
	int deleteOffOrderMgntNo(Map keyName);
	
	@SuppressWarnings({ "rawtypes" })
	int deleteOffMylecture(Map keyName);
	
	@SuppressWarnings({ "rawtypes" })
	int deleteOffOrders(Map keyName);
	
	@SuppressWarnings({ "rawtypes" })
	int deleteOffApprovals(Map keyName);
	
	@SuppressWarnings({ "rawtypes" })
	List getPrintPop(Map keyName);
	
	@SuppressWarnings({ "rawtypes" })
	int updatePrintOffOrders1(Map keyName);
	
	@SuppressWarnings({ "rawtypes" })
	int updatePrintOffOrders2(Map keyName);
	
	@SuppressWarnings({ "rawtypes" })
	int insertPrintOffOrders(Map keyName);
	
	@SuppressWarnings({ "rawtypes" })
	List getPrintOffOrders(Map keyName);
	
	@SuppressWarnings({ "rawtypes" })
	int updateOffRefund(Map keyName);
	
	@SuppressWarnings({ "rawtypes" })
	int insertRefundOffOrderMgntNo(Map keyName);
	
	@SuppressWarnings({ "rawtypes" })
	int updateOffMylecture(Map keyName);
	
	@SuppressWarnings({ "rawtypes" })
	int updateOffMylectureRefund(Map keyName);
	
	@SuppressWarnings({ "rawtypes" })
	int insertOffRefund(Map keyName);
	
	
	
	@SuppressWarnings({ "rawtypes" })
	int deleteOffCancelOrderMgntNo(Map keyName);
	
	@SuppressWarnings({ "rawtypes" })
	int deleteOffCancelRefund(Map keyName);
	
	@SuppressWarnings({ "rawtypes" })
	int updateOffCancelMylecture(Map keyName);
	
	@SuppressWarnings({ "rawtypes" })
	int Delete_Year_Package_Point(Map keyName);

	List<HashMap<String, String>> getTmPointHistory(HashMap<String, String> params);

	void insertTmBoard(Map<String, Object> searchMap);

	List<HashMap<String, String>> getTmBoardList(Map<String, Object> searchMap);

	List<HashMap<String, String>> getVOCCODEList(HashMap<String, String> params);

	List<HashMap<String, String>> getDUTYCODEList(HashMap<String, String> params);

	List<HashMap<String, String>> getOffApprovalsCount(Map<String, String> searchMap);
	
	List<HashMap<String, String>> getCcode(HashMap<String, String> params);

	// 내 강의실 누락 강의 등록 2016-01-18	
	void insertMyLecture(Map<String, String> params);
	void insertMyLectureN(Map<String, String> params);
	
	//학원인강 종합반 강의 변경 팝업
	@SuppressWarnings({ "rawtypes" })
	List lectureOffSView(Map keyName);
	
	@SuppressWarnings({ "rawtypes" })
	int getPlusPoint(Map keyName);
	
	void BookPointDel(Map<String, Object> params);
	void BookPointIns(Map<String, Object> params);
			
	void insertMissOrder(Map<String, Object> params);

	List getUserLecMovie(Map keyName);
	List getUserLecMovieLog(Map keyName);

	String getMysubjectteacherSelectone(Map<String, Object> searchMap);

	String myuserpackageflagSelectone(HashMap<String, String> params);

	int yearpackageUpdate(HashMap<String, String> params);

	int mylectureInsert(HashMap<String, String> params);

	int mylectureDelete(HashMap<String, String> params);

	String myuserpackageregtimeSelectone(HashMap<String, String> params);

	int getMysubjectteacherNumSelectone(Map<String, Object> searchMap);

	String getMyleccodepackageSelectone(Map<String, Object> searchMap);
	
	void insertOffOrderLog(Map<String, Object> params);

	// 교수 삭제
	int deleteYearpackage(HashMap<String, String> params);
	
}
