package web.coop.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;

@Repository
public class CoopOrderDAO extends EgovComAbstractDAO {

    // 전체상품주문관리 리스트
    @SuppressWarnings({ "rawtypes" })
	public List getOrderListDB(Map keyName){
		return getSqlSession().selectList("CoopOrder.getOrderListDB", keyName);
	}

    // 전체상품주문관리 총 건수
    @SuppressWarnings({ "rawtypes" })
	public int getOrderListCount(Map keyName){
		return getSqlSession().selectOne("CoopOrder.getOrderListCount", keyName);
	}

	/**
	 * 경영관리 - 강사별 지급률 상세 (온라인)
	 * @param params
	 * @return
	 */
	public List<HashMap<String, Object>> onPayDetail(Map<String, Object> params) {
		return getSqlSession().selectList("CoopOrder.onPayDetail", params);
	}

}