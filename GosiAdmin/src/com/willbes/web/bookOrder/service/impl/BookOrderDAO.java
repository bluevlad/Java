package com.willbes.web.bookOrder.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import com.willbes.cmm.service.impl.CmmAbstractMapper;

@Repository
public class BookOrderDAO extends CmmAbstractMapper {

	/** log */
    protected static final Log Logger = LogFactory.getLog(BookOrderDAO.class);

    // 교재배송 리스트 (택배)
    @SuppressWarnings({ "rawtypes" })
	public List getJuOrderListDB(Map keyName){
		return getSqlSession().selectList("bookOrder.getJuOrderListDB", keyName);
	}
    // 교재배송 리스트 (택배) 총 건수
    @SuppressWarnings({ "rawtypes" })
	public int getJuOrderListCount(Map keyName){
		return getSqlSession().selectOne("bookOrder.getJuOrderListCount", keyName);
	}
    //2번째 리스트
    @SuppressWarnings({ "rawtypes" })
	public List getTblJuOrderMgntListDB(Map keyName){
		return getSqlSession().selectList("bookOrder.getTblJuOrderMgntListDB", keyName);
	}
    // 엑셀 리스트
    @SuppressWarnings({ "rawtypes" })
    public List<HashMap<String, String>> getJuOrderExelListDB(HashMap<String, String> params){
		return getSqlSession().selectList("bookOrder.getJuOrderExelListDB", params);
	}

    //전체상품주문관리 주문번호 상세
    @SuppressWarnings({ "rawtypes" })
	public List getTblApprovalsViewList(Map keyName){
		return getSqlSession().selectList("bookOrder.getTblApprovalsViewList", keyName);
	}
    //전체상품배송관리 주문건수
    @SuppressWarnings({ "rawtypes" })
	public int getTblDeliversViewListCount(Map keyName){
		return getSqlSession().selectOne("bookOrder.getTblDeliversViewListCount", keyName);
	}
    //전체상품배송관리 리스트
    @SuppressWarnings({ "rawtypes" })
	public List getTblDeliversViewList(Map keyName){
		return getSqlSession().selectList("bookOrder.getTblDeliversViewList", keyName);
	}
    //전체상품주문관리 주문번호 상세
    @SuppressWarnings({ "rawtypes" })
	public List getTblOrdersViewList(Map keyName){
		return getSqlSession().selectList("bookOrder.getTblOrdersViewList", keyName);
	}
    //전체상품주문관리 강의 상세
    @SuppressWarnings({ "rawtypes" })
	public List getLecMstViewList(Map keyName){
		return getSqlSession().selectList("bookOrder.getLecMstViewList", keyName);
	}

    @SuppressWarnings({ "rawtypes" })
	public int updateDelivers(Map keyName){
		return getSqlSession().update("bookOrder.updateDelivers", keyName);
	}

    @SuppressWarnings({ "rawtypes" })
	public int updateApprovals(Map keyName){
		return getSqlSession().update("bookOrder.updateApprovals", keyName);
	}

    @SuppressWarnings({ "rawtypes" })
	public int updateDeliversWmv(Map keyName){
		return getSqlSession().update("bookOrder.updateDeliversWmv", keyName);
	}


    @SuppressWarnings({ "rawtypes" })
	public int updateMoney1(Map keyName){
		return getSqlSession().update("bookOrder.updateMoney1", keyName);
	}

    @SuppressWarnings({ "rawtypes" })
	public int getMoneySum(Map keyName){
		return getSqlSession().selectOne("bookOrder.getMoneySum", keyName);
	}

    @SuppressWarnings({ "rawtypes" })
	public int updateMoney2(Map keyName){
		return getSqlSession().update("bookOrder.updateMoney2", keyName);
	}

    @SuppressWarnings({ "rawtypes" })
	public int refund_money_delete(Map keyName){
		return getSqlSession().delete("bookOrder.refund_money_delete", keyName);
	}

    //송장저장
    @SuppressWarnings({ "rawtypes" })
	public int updateSendNo1(Map keyName){
		return getSqlSession().update("bookOrder.updateSendNo1", keyName);
	}
    //송장저장
    @SuppressWarnings({ "rawtypes" })
	public int updateSendNo2(Map keyName){
		return getSqlSession().update("bookOrder.updateSendNo2", keyName);
	}

    // 교재배송관리 'P' 팝업
    @SuppressWarnings({ "rawtypes" })
	public List getPopPrintView(Map keyName){
		return getSqlSession().selectList("bookOrder.getPopPrintView", keyName);
	}

    // 교재배송관리 'P' 팝업
    @SuppressWarnings({ "rawtypes" })
	public List getPopPrintInfoView(Map keyName){
		return getSqlSession().selectList("bookOrder.getPopPrintInfoView", keyName);
	}

    // 교재배송관리 'P' 팝업
    @SuppressWarnings({ "rawtypes" })
	public List getPopPrintOrderInfoView(Map keyName){
		return getSqlSession().selectList("bookOrder.getPopPrintOrderInfoView", keyName);
	}

    // 교재배송 리스트 (직접수령)
    @SuppressWarnings({ "rawtypes" })
	public List getJicOrderListDB(Map keyName){
		return getSqlSession().selectList("bookOrder.getJicOrderListDB", keyName);
	}

    // 교재배송 리스트 (직접수령) 총 건수
    @SuppressWarnings({ "rawtypes" })
	public int getJicOrderListCount(Map keyName){
		return getSqlSession().selectOne("bookOrder.getJicOrderListCount", keyName);
	}

    //수령완료
    @SuppressWarnings({ "rawtypes" })
	public int updateSendNo3(Map keyName){
		return getSqlSession().update("bookOrder.updateSendNo3", keyName);
	}

    //수령완료
    @SuppressWarnings({ "rawtypes" })
	public int updateSendNo4(Map keyName){
		return getSqlSession().update("bookOrder.updateSendNo4", keyName);
	}

    // 교재매출내역 리스트
    @SuppressWarnings({ "rawtypes" })
	public List getTolOrderListDB(Map keyName){
		return getSqlSession().selectList("bookOrder.getTolOrderListDB", keyName);
	}

    // 교재매출내역 리스트 총 건수
    @SuppressWarnings({ "rawtypes" })
	public int getTolOrderListCount(Map keyName){
		return getSqlSession().selectOne("bookOrder.getTolOrderListCount", keyName);
	}

    // 교재매출내역 리스트 2번째 리스트
    @SuppressWarnings({ "rawtypes" })
	public List getPutLecMstDB(Map keyName){
		return getSqlSession().selectList("bookOrder.getPutLecMstDB", keyName);
	}

    // 교재매출내역 리스트 엑셀 리스트
    @SuppressWarnings({ "rawtypes" })
    public List<HashMap<String, String>> getTolOrderExelListDB(HashMap<String, String> params){
		return getSqlSession().selectList("bookOrder.getTolOrderExelListDB", params);
	}

	/** 경찰 택배정보 조회  **/
    @SuppressWarnings({ "rawtypes" })
	public List getJuOrderListDB_COP(Map keyName){
		return getSqlSession().selectList("bookOrder.getJuOrderListDB_COP", keyName);
	}
    @SuppressWarnings({ "rawtypes" })
	public int getJuOrderListCount_COP(Map keyName){
		return getSqlSession().selectOne("bookOrder.getJuOrderListCount_COP", keyName);
	}
    @SuppressWarnings({ "rawtypes" })
	public List getTblJuOrderMgntListDB_COP(Map keyName){
		return getSqlSession().selectList("bookOrder.getTblJuOrderMgntListDB_COP", keyName);
	}
    @SuppressWarnings({ "rawtypes" })
    public List<HashMap<String, String>> getJuOrderExelListDB_COP(HashMap<String, String> params){
		return getSqlSession().selectList("bookOrder.getJuOrderExelListDB_COP", params);
	}
    @SuppressWarnings({ "rawtypes" })
	public int updateSendNo1_COP(Map keyName){
		return getSqlSession().update("bookOrder.updateSendNo1_COP", keyName);
	}
    @SuppressWarnings({ "rawtypes" })
	public int updateSendNo2_COP(Map keyName){
		return getSqlSession().update("bookOrder.updateSendNo2_COP", keyName);
	}
    @SuppressWarnings({ "rawtypes" })
	public List getLecMstViewList_COP(Map keyName){
		return getSqlSession().selectList("bookOrder.getLecMstViewList_COP", keyName);
	}
    @SuppressWarnings({ "rawtypes" })
	public List getTblDeliversViewList_COP(Map keyName){
		return getSqlSession().selectList("bookOrder.getTblDeliversViewList_COP", keyName);
	}

}
