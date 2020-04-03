package web.coop.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface CoopOrderService {
	
	/* last modified 2014-08-20 */

	List<HashMap<String, Object>> onPayDetail(Map<String, Object> params);

	// 전체상품주문관리 리스트
	@SuppressWarnings({ "rawtypes" })
	List getOrderListDB(Map keyName);
	
	// 전체상품주문관리 총 건수
	@SuppressWarnings({ "rawtypes" })
	int getOrderListCount(Map keyName);

}