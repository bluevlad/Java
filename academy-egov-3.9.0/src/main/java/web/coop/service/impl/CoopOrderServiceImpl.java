package web.coop.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web.coop.service.CoopOrderService;

@Service
public class CoopOrderServiceImpl  implements  CoopOrderService{
	
	@Autowired
	private CoopOrderDAO coopOrderdao;

	@Override
	public List<HashMap<String, Object>> onPayDetail(Map<String, Object> params) {
		return coopOrderdao.onPayDetail(params);
	}
	
	// 전체상품주문관리 리스트
	@Override @SuppressWarnings("rawtypes")
	public List getOrderListDB(Map keyName){		
		return coopOrderdao.getOrderListDB(keyName);
	}
	
	// 전체상품주문관리 총 건수
	@Override @SuppressWarnings("rawtypes")
	public int getOrderListCount(Map keyName) {
		return coopOrderdao.getOrderListCount(keyName);
	}
	
}
