package web.productOrder.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;

@Repository
public class ProductOrderDAO extends EgovComAbstractDAO {

	/* last modified 2014-08-20 */
	/** log */
    protected static final Log Logger = LogFactory.getLog(ProductOrderDAO.class);

    @SuppressWarnings({ "rawtypes" })
	public List getOrderStatuscodeList(Map keyName){
		return getSqlSession().selectList("productOrder.getOrderStatuscodeList", keyName);
	}

    @SuppressWarnings({ "rawtypes" })
	public List getPaymentList(Map keyName){
		return getSqlSession().selectList("productOrder.getPaymentList", keyName);
	}

    // 전체상품주문관리 리스트(0원)
    @SuppressWarnings({ "rawtypes" })
	public List getOrderListDB_0(Map keyName){
		return getSqlSession().selectList("productOrder.getOrderListDB_0", keyName);
	}

    // 전체상품주문관리 총 건수(0원)
    @SuppressWarnings({ "rawtypes" })
	public int getOrderListCount_0(Map keyName){
		return getSqlSession().selectOne("productOrder.getOrderListCount_0", keyName);
	}

    // 전체상품주문관리 리스트
    @SuppressWarnings({ "rawtypes" })
	public List getOrderListDB(Map keyName){
		return getSqlSession().selectList("productOrder.getOrderListDB", keyName);
	}

    // 전체상품주문관리 총 건수
    @SuppressWarnings({ "rawtypes" })
	public int getOrderListCount(Map keyName){
		return getSqlSession().selectOne("productOrder.getOrderListCount", keyName);
	}

    //2번째 리스트
    @SuppressWarnings({ "rawtypes" })
	public List getTblOrderMgntListDB(Map keyName){
		return getSqlSession().selectList("productOrder.getTblOrderMgntListDB", keyName);
	}

    // 전체상품주문관리 엑셀 리스트
    @SuppressWarnings({ "rawtypes" })
    public List<HashMap<String, String>> getOrderExcelListDB(HashMap<String, String> params){
		return getSqlSession().selectList("productOrder.getOrderExcelListDB", params);
	}

    // 카드변경
    @SuppressWarnings({ "rawtypes" })
	public int setPayKindUpdate(Map keyName){
		return getSqlSession().update("productOrder.setPayKindUpdate", keyName);
	}

    // 입금상태저장
    @SuppressWarnings({ "rawtypes" })
	public int updateDepositStatus1(Map keyName){
		return getSqlSession().update("productOrder.updateDepositStatus1", keyName);
	}

    // 입금상태저장
    @SuppressWarnings({ "rawtypes" })
	public int getDepositStatusCount(Map keyName){
		return getSqlSession().selectOne("productOrder.getDepositStatusCount", keyName);
	}

    // 입금상태저장
    @SuppressWarnings({ "rawtypes" })
	public int updateDepositStatus2(Map keyName){
		return getSqlSession().update("productOrder.updateDepositStatus2", keyName);
	}

    // 입금상태저장
    @SuppressWarnings({ "rawtypes" })
	public int updateDepositStatus3(Map keyName){
		return getSqlSession().update("productOrder.updateDepositStatus3", keyName);
	}

    // 입금상태저장
    @SuppressWarnings({ "rawtypes" })
	public int updateDepositStatus4(Map keyName){
		return getSqlSession().update("productOrder.updateDepositStatus4", keyName);
	}

    // 입금상태저장
    @SuppressWarnings({ "rawtypes" })
	public int updateDepositStatus5(Map keyName){
		return getSqlSession().update("productOrder.updateDepositStatus5", keyName);
	}

    // 도서 재고 더하기
    @SuppressWarnings({ "rawtypes" })
	public int updateBookStockPlus(Map keyName){
		return getSqlSession().update("productOrder.updateBookStockPlus", keyName);
	}


    //상품명 팝업
    @SuppressWarnings({ "rawtypes" })
	public List getPmpdownloadListPop(Map keyName){
		return getSqlSession().selectList("productOrder.getPmpdownloadListPop", keyName);
	}
    //모바일 다운로드 조회
    @SuppressWarnings({ "rawtypes" })
    public List getMobiledownloadListPop(Map keyName){
    	return getSqlSession().selectList("productOrder.getMobiledownloadListPop", keyName);
    }

    //상품명 팝업
    @SuppressWarnings({ "rawtypes" })
	public List getTblOrderList(Map keyName){
		return getSqlSession().selectList("productOrder.getTblOrderList", keyName);
	}

    //상품명 팝업 리스트
    @SuppressWarnings({ "rawtypes" })
	public List getOrderListPopDB(Map keyName){
		return getSqlSession().selectList("productOrder.getOrderListPopDB", keyName);
	}


    //구입금액
    @SuppressWarnings({ "rawtypes" })
	public List getCourse_type_code(Map keyName){
		return getSqlSession().selectList("productOrder.getCourse_type_code", keyName);
	}

    //구입금액
    @SuppressWarnings({ "rawtypes" })
	public List getPlayyn(Map keyName){
		return getSqlSession().selectList("productOrder.getPlayyn", keyName);
	}

    //구입금액
    @SuppressWarnings({ "rawtypes" })
	public List getPoint(Map keyName){
		return getSqlSession().selectList("productOrder.getPoint", keyName);
	}

    //구입금액
    @SuppressWarnings({ "rawtypes" })
	public List getTblOrderMgntNoPopViewList(Map keyName){
		return getSqlSession().selectList("productOrder.getTblOrderMgntNoPopViewList", keyName);
	}

    //구입금액
    @SuppressWarnings({ "rawtypes" })
	public int getTblOrderMgntNoPopViewCount(Map keyName){
		return getSqlSession().selectOne("productOrder.getTblOrderMgntNoPopViewCount", keyName);
	}

    //구입금액
    @SuppressWarnings({ "rawtypes" })
	public int getPrice_Sum(Map keyName){
		return getSqlSession().selectOne("productOrder.getPrice_Sum", keyName);
	}

    //구입금액
    @SuppressWarnings({ "rawtypes" })
	public int getOldRefundViewCount(Map keyName){
		return getSqlSession().selectOne("productOrder.getOldRefundViewCount", keyName);
	}

    //구입금액
    @SuppressWarnings({ "rawtypes" })
	public List getRefund_Point(Map keyName){
		return getSqlSession().selectList("productOrder.getRefund_Point", keyName);
	}

    //구입금액
    @SuppressWarnings({ "rawtypes" })
	public List getOldRefundView(Map keyName){
		return getSqlSession().selectList("productOrder.getOldRefundView", keyName);
	}



    //택배비
	@SuppressWarnings({ "rawtypes" })
	public List getTblDeliver_refund_list(Map keyName){
		return getSqlSession().selectList("productOrder.getTblDeliver_refund_list", keyName);
	}



	//전체상품주문관리 주문번호 상세
    @SuppressWarnings({ "rawtypes" })
	public List getTblApprovalsViewList(Map keyName){
		return getSqlSession().selectList("productOrder.getTblApprovalsViewList", keyName);
	}

    //전체상품주문관리 주문번호 상세
    @SuppressWarnings({ "rawtypes" })
	public int getTblDeliversViewListCount(Map keyName){
		return getSqlSession().selectOne("productOrder.getTblDeliversViewListCount", keyName);
	}

    //전체상품주문관리 주문번호 상세
    @SuppressWarnings({ "rawtypes" })
	public List getTblDeliversViewList(Map keyName){
		return getSqlSession().selectList("productOrder.getTblDeliversViewList", keyName);
	}

    //전체상품주문관리 주문번호 상세
    @SuppressWarnings({ "rawtypes" })
	public List getTblOrdersViewList(Map keyName){
		return getSqlSession().selectList("productOrder.getTblOrdersViewList", keyName);
	}

    //전체상품주문관리 주문번호 상세
    @SuppressWarnings({ "rawtypes" })
	public List getLecMstViewList(Map keyName){
		return getSqlSession().selectList("productOrder.getLecMstViewList", keyName);
	}

    @SuppressWarnings({ "rawtypes" })
	public int updateDelivers(Map keyName){
		return getSqlSession().update("productOrder.updateDelivers", keyName);
	}

    @SuppressWarnings({ "rawtypes" })
	public int updateApprovals(Map keyName){
		return getSqlSession().update("productOrder.updateApprovals", keyName);
	}

    @SuppressWarnings({ "rawtypes" })
	public int updateDeliversWmv(Map keyName){
		return getSqlSession().update("productOrder.updateDeliversWmv", keyName);
	}


    @SuppressWarnings({ "rawtypes" })
	public int updateMoney1(Map keyName){
		return getSqlSession().update("productOrder.updateMoney1", keyName);
	}

    @SuppressWarnings({ "rawtypes" })
	public int updateStudy_Per(Map keyName){
		return getSqlSession().update("productOrder.updateStudy_Per", keyName);
	}
    
    @SuppressWarnings({ "rawtypes" })
	public int getMoneySum(Map keyName){
		return getSqlSession().selectOne("productOrder.getMoneySum", keyName);
	}

    @SuppressWarnings({ "rawtypes" })
	public int updateMoney2(Map keyName){
		return getSqlSession().update("productOrder.updateMoney2", keyName);
	}

    @SuppressWarnings({ "rawtypes" })
	public int refund_money_delete(Map keyName){
		return getSqlSession().delete("productOrder.refund_money_delete", keyName);
	}



    @SuppressWarnings({ "rawtypes" })
	public List getMylecture(Map keyName){
		return getSqlSession().selectList("productOrder.getMylecture", keyName);
	}

    @SuppressWarnings({ "rawtypes" })
	public int updateMylecture1(Map keyName){
		return getSqlSession().update("productOrder.updateMylecture1", keyName);
	}

    @SuppressWarnings({ "rawtypes" })
	public int updateMylecture2(Map keyName){
		return getSqlSession().update("productOrder.updateMylecture2", keyName);
	}

    @SuppressWarnings({ "rawtypes" })
	public int updateMylecture3(Map keyName){
		return getSqlSession().update("productOrder.updateMylecture3", keyName);
	}

    @SuppressWarnings({ "rawtypes" })
	public int updateMylecture4(Map keyName){
		return getSqlSession().update("productOrder.updateMylecture4", keyName);
	}

    @SuppressWarnings({ "rawtypes" })
	public int updateEndDateMyLecture(Map keyName){
		return getSqlSession().update("productOrder.updateEndDateMyLecture", keyName);
	}

    // 전체상품주문관리 총 건수
    @SuppressWarnings({ "rawtypes" })
	public int getMylectureCount(Map keyName){
		return getSqlSession().selectOne("productOrder.getMylectureCount", keyName);
	}

    @SuppressWarnings({ "rawtypes" })
	public int updateMylecture5(Map keyName){
		return getSqlSession().update("productOrder.updateMylecture5", keyName);
	}

    @SuppressWarnings({ "rawtypes" })
	public int insertMylecture6(Map keyName){
		return getSqlSession().insert("productOrder.insertMylecture6", keyName);
	}

    @SuppressWarnings({ "rawtypes" })
	public int insertOrderMgntRefund(Map keyName){
		return getSqlSession().insert("productOrder.insertOrderMgntRefund", keyName);
	}
    @SuppressWarnings({ "rawtypes" })
	public int insertOrderMgntNo1(Map keyName){
		return getSqlSession().insert("productOrder.insertOrderMgntNo1", keyName);
	}
    @SuppressWarnings({ "rawtypes" })
    public int updateOrderMgntNo2(Map keyName){
    	return getSqlSession().insert("productOrder.updateOrderMgntNo2", keyName);
    }
    @SuppressWarnings({ "rawtypes" })
    public int updateOrderMgntNo3(Map keyName){
    	return getSqlSession().insert("productOrder.updateOrderMgntNo3", keyName);
    }

    @SuppressWarnings({ "rawtypes" })
	public int insertOrderMgntNo2(Map keyName){
		return getSqlSession().insert("productOrder.insertOrderMgntNo2", keyName);
	}

    @SuppressWarnings({ "rawtypes" })
	public int updateApprovals2(Map keyName){
		return getSqlSession().update("productOrder.updateApprovals2", keyName);
	}

    @SuppressWarnings({ "rawtypes" })
	public int insertMileageHistory(Map keyName){
		return getSqlSession().insert("productOrder.insertMileageHistory", keyName);
	}

    @SuppressWarnings({ "rawtypes" })
	public int insertMileageHistory3(Map keyName){
		return getSqlSession().insert("productOrder.insertMileageHistory3", keyName);
	}

    @SuppressWarnings({ "rawtypes" })
	public int updateMaMember(Map keyName){
		return getSqlSession().update("productOrder.updateMaMember", keyName);
	}

    @SuppressWarnings({ "rawtypes" })
	public int updateMylecture_1(Map keyName){
		return getSqlSession().update("productOrder.updateMylecture_1", keyName);
	}

    @SuppressWarnings({ "rawtypes" })
	public int updateMylecture_2(Map keyName){
		return getSqlSession().update("productOrder.updateMylecture_2", keyName);
	}




    @SuppressWarnings({ "rawtypes" })
	public int updateOrderMgntNo1(Map keyName){
		return getSqlSession().update("productOrder.updateOrderMgntNo1", keyName);
	}

    @SuppressWarnings({ "rawtypes" })
	public int updateMileageHistory(Map keyName){
		return getSqlSession().update("productOrder.updateMileageHistory", keyName);
	}

    @SuppressWarnings({ "rawtypes" })
	public int updateMaMember2(Map keyName){
		return getSqlSession().update("productOrder.updateMaMember2", keyName);
	}

    @SuppressWarnings({ "rawtypes" })
	public int updateApprovals3(Map keyName){
		return getSqlSession().update("productOrder.updateApprovals3", keyName);
	}

    @SuppressWarnings({ "rawtypes" })
   	public int deleteOrderMgntNo(Map keyName){
   		return getSqlSession().delete("productOrder.deleteOrderMgntNo", keyName);
   	}

    @SuppressWarnings({ "rawtypes" })
	public int updateMaMember3(Map keyName){
		return getSqlSession().update("productOrder.updateMaMember3", keyName);
	}

    @SuppressWarnings({ "rawtypes" })
	public int updateApprovals4(Map keyName){
		return getSqlSession().update("productOrder.updateApprovals4", keyName);
	}

    @SuppressWarnings({ "rawtypes" })
	public int insertMileageHistory2(Map keyName){
		return getSqlSession().insert("productOrder.insertMileageHistory2", keyName);
	}

    @SuppressWarnings({ "rawtypes" })
	public int updateMylecture_3(Map keyName){
		return getSqlSession().update("productOrder.updateMylecture_3", keyName);
	}

    @SuppressWarnings({ "rawtypes" })
	public int updateApprovals5(Map keyName){
		return getSqlSession().update("productOrder.updateApprovals5", keyName);
	}



    //SMS보내기
    @SuppressWarnings({ "rawtypes" })
	public int insertSendMsgMultiSendUser(Map keyName){
		return getSqlSession().insert("productOrder.insertSendMsgMultiSendUser", keyName);
	}



    // 사용자 팝업
    @SuppressWarnings({ "rawtypes" })
	public List getTmMember_View(Map keyName){
		return getSqlSession().selectList("productOrder.getTmMember_View", keyName);
	}

    // 사용자 팝업
    @SuppressWarnings({ "rawtypes" })
	public List getCsccode(Map keyName){
		return getSqlSession().selectList("productOrder.getCsccode", keyName);
	}

    // 사용자 팝업
    @SuppressWarnings({ "rawtypes" })
	public List Cs_board_list(Map keyName){
		return getSqlSession().selectList("productOrder.Cs_board_list", keyName);
	}

    // 사용자 팝업
    @SuppressWarnings({ "rawtypes" })
	public int getCsBoardListCount(Map keyName){
		return getSqlSession().selectOne("productOrder.getCsBoardListCount", keyName);
	}

    @SuppressWarnings({ "rawtypes" })
	public int getSelectBefore_Point(Map keyName){
		return getSqlSession().selectOne("productOrder.getSelectBefore_Point", keyName);
	}

    // 사용자 팝업
    @SuppressWarnings({ "rawtypes" })
	public List getTm_mycoupon_list_admin(Map keyName){
		return getSqlSession().selectList("productOrder.getTm_mycoupon_list_admin", keyName);
	}

    // 사용자 팝업
    @SuppressWarnings({ "rawtypes" })
	public int getTm_mycoupon_listCount_admin(Map keyName){
		return getSqlSession().selectOne("productOrder.getTm_mycoupon_listCount_admin", keyName);
	}

    // 사용자 팝업
    @SuppressWarnings({ "rawtypes" })
	public List Tm_Class_List(Map keyName){
		return getSqlSession().selectList("productOrder.Tm_Class_List", keyName);
	}

    // 사용자 팝업
    @SuppressWarnings({ "rawtypes" })
	public List Off_Class_List(Map keyName){
		return getSqlSession().selectList("productOrder.Off_Class_List", keyName);
	}

    // 사용자 팝업
    @SuppressWarnings({ "rawtypes" })
	public int getTmClassListCount(Map keyName){
		return getSqlSession().selectOne("productOrder.getTmClassListCount", keyName);
	}

    // 사용자 팝업
    @SuppressWarnings({ "rawtypes" })
	public List getMemoList(Map keyName){
		return getSqlSession().selectList("productOrder.getMemoList", keyName);
	}

    // 사용자 팝업
    @SuppressWarnings({ "rawtypes" })
	public int getMemoListCount(Map keyName){
		return getSqlSession().selectOne("productOrder.getMemoListCount", keyName);
	}




    @SuppressWarnings({ "rawtypes" })
	public int insertBoardCs(Map keyName){
		return getSqlSession().insert("productOrder.insertBoardCs", keyName);
	}

    @SuppressWarnings({ "rawtypes" })
	public int updateMemberPoint(Map keyName){
		return getSqlSession().update("productOrder.updateMemberPoint", keyName);
	}

    @SuppressWarnings({ "rawtypes" })
	public int updateMemo(Map keyName){
		return getSqlSession().update("productOrder.updateMemo", keyName);
	}


    // 사용자 팝업
    @SuppressWarnings({ "rawtypes" })
	public List getTmCoupon(Map keyName){
		return getSqlSession().selectList("productOrder.getTmCoupon", keyName);
	}
    // 사용자 팝업
    @SuppressWarnings({ "rawtypes" })
    public List getTmCouponList(Map keyName){
    	return getSqlSession().selectList("productOrder.getTmCouponList", keyName);
    }
    // 사용자 팝업
    @SuppressWarnings({ "rawtypes" })
    public List getTmMoCouponList(Map keyName){
    	return getSqlSession().selectList("productOrder.getTmMoCouponList", keyName);
    }

    // 사용자 팝업
    @SuppressWarnings({ "rawtypes" })
	public int getTmCouponCount(Map keyName){
		return getSqlSession().selectOne("productOrder.getTmCouponCount", keyName);
	}
    // 사용자 팝업
    @SuppressWarnings({ "rawtypes" })
    public int getTmMoCouponCount(Map keyName){
    	return getSqlSession().selectOne("productOrder.getTmMoCouponCount", keyName);
    }

    // 사용자 팝업
    @SuppressWarnings({ "rawtypes" })
	public int getCouponCount(Map keyName){
		return getSqlSession().selectOne("productOrder.getCouponCount", keyName);
	}

    // 신규 쿠폰발행
    @SuppressWarnings({ "rawtypes" })
	public int insertTmCoupon(Map keyName){
		return getSqlSession().insert("productOrder.insertTmCoupon", keyName);
	}

    // 쿠폰 사용자에게 발행
    @SuppressWarnings({ "rawtypes" })
	public int insertMyCoupon(Map keyName){
		return getSqlSession().insert("productOrder.insertMyCoupon", keyName);
	}



    // off 전체상품주문관리 리스트
    @SuppressWarnings({ "rawtypes" })
	public List getOffOrderListDB(Map keyName){
		return getSqlSession().selectList("productOrder.getOffOrderListDB", keyName);
	}

    // off 전체상품주문관리 총 건수
    @SuppressWarnings({ "rawtypes" })
	public int getOffOrderListCount(Map keyName){
		return getSqlSession().selectOne("productOrder.getOffOrderListCount", keyName);
	}

    //off 2번째 리스트
    @SuppressWarnings({ "rawtypes" })
	public List getOffTblOrderMgntListDB(Map keyName){
		return getSqlSession().selectList("productOrder.getOffTblOrderMgntListDB", keyName);
	}

    // off 전체상품주문관리 엑셀 리스트
    @SuppressWarnings({ "rawtypes" })
    public List<HashMap<String, String>> getOffOrderExcelListDB(HashMap<String, String> params){
		return getSqlSession().selectList("productOrder.getOffOrderExcelListDB", params);
	}

    //id체크
    @SuppressWarnings({ "rawtypes" })
	public List getIdChk(Map keyName){
		return getSqlSession().selectList("productOrder.getIdChk", keyName);
	}




    //품목변경 팝업 카테고리 셀렉트박스 리스트
  	@SuppressWarnings({ "rawtypes" })
  	public List getCaCatCdList(Map keyName){
  		return getSqlSession().selectList("productOrder.getCaCatCdList", keyName);
  	}

  	//품목변경 팝업 학습형태 셀렉트박스 리스트
  	@SuppressWarnings({ "rawtypes" })
  	public List getVwMenuMstTree_lec(Map keyName){
  		return getSqlSession().selectList("productOrder.getVwMenuMstTree_lec", keyName);
  	}

  	//품목변경 팝업 과목 셀렉트박스 리스트
  	@SuppressWarnings({ "rawtypes" })
  	public List getCaSubjectCdList(Map keyName){
  		return getSqlSession().selectList("productOrder.getCaSubjectCdList", keyName);
  	}

  	//품목변경 팝업  리스트
  	@SuppressWarnings({ "rawtypes" })
  	public List getCbLecMstFreeOrderList(Map keyName){
  		return getSqlSession().selectList("productOrder.getCbLecMstFreeOrderList", keyName);
  	}

  	// 품목변경 팝업  카운트
  	@SuppressWarnings({ "rawtypes" })
  	public int getCbLecMstListFreeOrderCount(Map keyName){
  		return getSqlSession().selectOne("productOrder.getCbLecMstListFreeOrderCount", keyName);
  	}


  	@SuppressWarnings({ "rawtypes" })
  	public List getChangePrice(Map keyName){
  		return getSqlSession().selectList("productOrder.getChangePrice", keyName);
  	}

  	@SuppressWarnings({ "rawtypes" })
  	public List getUCount(Map keyName){
  		return getSqlSession().selectList("productOrder.getUCount", keyName);
  	}

  	@SuppressWarnings({ "rawtypes" })
	public int insertCart(Map keyName){
		return getSqlSession().insert("productOrder.insertCart", keyName);
	}

  	//장바구니 리스트
  	@SuppressWarnings({ "rawtypes" })
  	public List getSubCode2(Map keyName){
  		return getSqlSession().selectList("productOrder.getSubCode2", keyName);
  	}

  	@SuppressWarnings({ "rawtypes" })
	public int deleteCart(Map keyName){
		return getSqlSession().delete("productOrder.deleteCart", keyName);
	}

  	@SuppressWarnings({ "rawtypes" })
	public int deleteAllCart(Map keyName){
		return getSqlSession().delete("productOrder.deleteAllCart", keyName);
	}




  	@SuppressWarnings({ "rawtypes" })
  	public List getMCount(Map keyName){
  		return getSqlSession().selectList("productOrder.getMCount", keyName);
  	}

  	@SuppressWarnings({ "rawtypes" })
	public int insertOffOrders(Map keyName){
		return getSqlSession().insert("productOrder.insertOffOrders", keyName);
	}

  	@SuppressWarnings({ "rawtypes" })
	public int insertOffApprovals(Map keyName){
		return getSqlSession().insert("productOrder.insertOffApprovals", keyName);
	}

  	@SuppressWarnings({ "rawtypes" })
	public int insertOffOrderMgntNo(Map keyName){
		return getSqlSession().insert("productOrder.insertOffOrderMgntNo", keyName);
	}

  	@SuppressWarnings({ "rawtypes" })
	public int insertOffMylecture(Map keyName){
		return getSqlSession().insert("productOrder.insertOffMylecture", keyName);
	}

  	@SuppressWarnings({ "rawtypes" })
	public int modifyOffMylecture(Map keyName){
		return getSqlSession().insert("productOrder.modifyOffMylecture", keyName);
	}

  	@SuppressWarnings({ "rawtypes" })
	public int insertOffMylecture_N(Map keyName){
		return getSqlSession().insert("productOrder.insertOffMylecture_N", keyName);
	}


  	@SuppressWarnings({ "rawtypes" })
  	public List getUpdateDetail(Map keyName){
  		return getSqlSession().selectList("productOrder.getUpdateDetail", keyName);
  	}

  	@SuppressWarnings({ "rawtypes" })
  	public List getSubLec(Map keyName){
  		return getSqlSession().selectList("productOrder.getSubLec", keyName);
  	}

  	@SuppressWarnings({ "rawtypes" })
  	public List getSubCodeUp(Map keyName){
  		return getSqlSession().selectList("productOrder.getSubCodeUp", keyName);
  	}

  	@SuppressWarnings({ "rawtypes" })
  	public List getSubList(Map keyName){
  		return getSqlSession().selectList("productOrder.getSubList", keyName);
  	}




  	@SuppressWarnings({ "rawtypes" })
	public int updateOffOrders(Map keyName){
		return getSqlSession().update("productOrder.updateOffOrders", keyName);
	}

  	@SuppressWarnings({ "rawtypes" })
	public int Coupon_Del(Map keyName){
		return getSqlSession().update("productOrder.Coupon_Del", keyName);
	}

    @SuppressWarnings({ "rawtypes" })
	public int updateOffApprovals(Map keyName){
		return getSqlSession().update("productOrder.updateOffApprovals", keyName);
	}

    @SuppressWarnings({ "rawtypes" })
	public int updateOffMgntNo(Map keyName){
		return getSqlSession().update("productOrder.updateOffMgntNo", keyName);
	}

    @SuppressWarnings({ "rawtypes" })
	public int updateOffMgntNo_N(Map keyName){
		return getSqlSession().update("productOrder.updateOffMgntNo_N", keyName);
	}

  	@SuppressWarnings({ "rawtypes" })
	public int deleteOffOrderMgntNo(Map keyName){
		return getSqlSession().delete("productOrder.deleteOffOrderMgntNo", keyName);
	}

  	@SuppressWarnings({ "rawtypes" })
	public int deleteOffMylecture(Map keyName){
		return getSqlSession().delete("productOrder.deleteOffMylecture", keyName);
	}

  	@SuppressWarnings({ "rawtypes" })
	public int deleteOffOrders(Map keyName){
		return getSqlSession().delete("productOrder.deleteOffOrders", keyName);
	}

  	@SuppressWarnings({ "rawtypes" })
	public int deleteOffApprovals(Map keyName){
		return getSqlSession().delete("productOrder.deleteOffApprovals", keyName);
	}




  	@SuppressWarnings({ "rawtypes" })
  	public List getPrintPop(Map keyName){
  		return getSqlSession().selectList("productOrder.getPrintPop", keyName);
  	}

  	@SuppressWarnings({ "rawtypes" })
	public int updatePrintOffOrders1(Map keyName){
		return getSqlSession().update("productOrder.updatePrintOffOrders1", keyName);
	}

    @SuppressWarnings({ "rawtypes" })
	public int updatePrintOffOrders2(Map keyName){
		return getSqlSession().update("productOrder.updatePrintOffOrders2", keyName);
	}

    @SuppressWarnings({ "rawtypes" })
	public int insertPrintOffOrders(Map keyName){
		return getSqlSession().insert("productOrder.insertPrintOffOrders", keyName);
	}

  	@SuppressWarnings({ "rawtypes" })
  	public List getPrintOffOrders(Map keyName){
  		return getSqlSession().selectList("productOrder.getPrintOffOrders", keyName);
  	}


    @SuppressWarnings({ "rawtypes" })
	public int updateOffRefund(Map keyName){
		return getSqlSession().update("productOrder.updateOffRefund", keyName);
	}

    @SuppressWarnings({ "rawtypes" })
	public int insertRefundOffOrderMgntNo(Map keyName){
		return getSqlSession().insert("productOrder.insertRefundOffOrderMgntNo", keyName);
	}

    @SuppressWarnings({ "rawtypes" })
	public int updateOffMylecture(Map keyName){
		return getSqlSession().update("productOrder.updateOffMylecture", keyName);
	}

    @SuppressWarnings({ "rawtypes" })
	public int updateOffMylectureRefund(Map keyName){
		return getSqlSession().update("productOrder.updateOffMylectureRefund", keyName);
	}

    @SuppressWarnings({ "rawtypes" })
	public int insertOffRefund(Map keyName){
		return getSqlSession().insert("productOrder.insertOffRefund", keyName);
	}




    @SuppressWarnings({ "rawtypes" })
	public int deleteOffCancelOrderMgntNo(Map keyName){
		return getSqlSession().delete("productOrder.deleteOffCancelOrderMgntNo", keyName);
	}

    @SuppressWarnings({ "rawtypes" })
	public int deleteOffCancelRefund(Map keyName){
		return getSqlSession().update("productOrder.deleteOffCancelRefund", keyName);
	}

    @SuppressWarnings({ "rawtypes" })
	public int updateOffCancelMylecture(Map keyName){
		return getSqlSession().delete("productOrder.updateOffCancelMylecture", keyName);
	}

	public List<HashMap<String, String>> getTmPointHistory(HashMap<String, String> params) {
		return getSqlSession().selectList("productOrder.getTmPointHistory", params);
	}

	public void insertTmBoard(Map<String, Object> searchMap) {
		getSqlSession().insert("productOrder.insertTmBoard", searchMap);
	}

	public List<HashMap<String, String>> getTmBoardList(Map<String, Object> searchMap) {
		return getSqlSession().selectList("productOrder.getTmBoardList", searchMap);
	}

	public List<HashMap<String, String>> getVOCCODEList(HashMap<String, String> params) {
		return getSqlSession().selectList("productOrder.getVOCCODEList", params);
	}

	public List<HashMap<String, String>> getDUTYCODEList(HashMap<String, String> params) {
		return getSqlSession().selectList("productOrder.getDUTYCODEList", params);
	}

	public List<HashMap<String, String>> getOffApprovalsCount(Map<String, String> searchMap) {
		return getSqlSession().selectList("productOrder.getOffApprovalsCount", searchMap);
	}
	
	public List<HashMap<String, String>> getCcode(HashMap<String, String> params){
		return getSqlSession().selectList("productOrder.getCcode", params);
	}

	// 내 강의실 누락 강의 등록 2016-01-18	
	public void insertMyLecture(Map<String, String> params) {
		getSqlSession().insert("productOrder.insertMyLecture", params);
	}

	public void insertMyLectureN(Map<String, String> params) {
		getSqlSession().insert("productOrder.insertMyLectureN", params);
	}
	
	@SuppressWarnings({ "rawtypes" })
	public int Delete_Year_Package_Point(Map keyName){
		return getSqlSession().update("productOrder.Delete_Year_Package_Point", keyName);
	}
	
	public List<HashMap<String, String>> lectureOffSView(Map<String, String> searchMap) {
		return getSqlSession().selectList("productOrder.lectureOffSView", searchMap);
	}
	
	@SuppressWarnings({ "rawtypes" })
	public int getPlusPoint(Map keyName){
		return getSqlSession().selectOne("productOrder.getPlusPoint", keyName);
	}
	
	public void BookPointDel(Map<String, Object> params){
		getSqlSession().update("productOrder.BookPointDel", params);
	}
	
	public void BookPointIns(Map<String, Object> params){
		getSqlSession().update("productOrder.BookPointIns", params);
	}
	
	public void insertMissOrder(Map<String, Object> params){
		getSqlSession().update("productOrder.insertMissOrder", params);
	}

	public List getUserLecMovie(Map keyName){
		return getSqlSession().selectList("productOrder.getUserLecMovie", keyName);
	}
	public List getUserLecMovieLog(Map keyName){
		return getSqlSession().selectList("productOrder.getUserLecMovieLog", keyName);
	}

	public String getMysubjectteacherSelectone(Map<String, Object> searchMap) {
		return getSqlSession().selectOne("productOrder.getMysubjectteacherSelectone", searchMap);
	}

	public String myuserpackageflagSelectone(HashMap<String, String> params) {
		return getSqlSession().selectOne("productOrder.myuserpackageflagSelectone", params);
	}

	public int yearpackageUpdate(HashMap<String, String> params) {
		return getSqlSession().update("productOrder.yearpackageUpdate", params);
	}

	public int mylectureInsert(HashMap<String, String> params) {
		return getSqlSession().insert("productOrder.mylectureInsert", params);
	}

	public int mylectureDelete(HashMap<String, String> params) {
		return getSqlSession().delete("productOrder.mylectureDelete", params);
	}

	public String myuserpackageregtimeSelectone(HashMap<String, String> params) {
		return getSqlSession().selectOne("productOrder.myuserpackageregtimeSelectone", params);
	}

	public int getMysubjectteacherNumSelectone(Map<String, Object> params) {
		return getSqlSession().selectOne("productOrder.getMysubjectteacherNumSelectone", params);
	}

	public String getMyleccodepackageSelectone(Map<String, Object> params) {
		return getSqlSession().selectOne("productOrder.getMyleccodepackageSelectone", params);
	}
	
	// 학원 주문정보 변경 로그 관리 2017-05-24	
	public void insertOffOrderLog(Map<String, Object> params){
		getSqlSession().update("productOrder.insertOffOrderLog", params);
	}
	
	// 무료강좌 주문리스트(0원)
    @SuppressWarnings({ "rawtypes" })
	public List getOrderListDB_freelec(Map keyName){
		return getSqlSession().selectList("productOrder.getOrderListDB_freelec", keyName);
	}

    // 무료강좌 총 건수(0원)
    @SuppressWarnings({ "rawtypes" })
	public int getOrderListCount_freelec(Map keyName){
		return getSqlSession().selectOne("productOrder.getOrderListCount_freelec", keyName);
	}

	public int deleteYearpackage(HashMap<String, String> params) {
		return getSqlSession().delete("productOrder.deleteYearpackage", params);
	}
	
}