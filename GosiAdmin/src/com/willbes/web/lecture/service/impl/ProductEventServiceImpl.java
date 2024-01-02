package com.willbes.web.lecture.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.willbes.web.freeOrder.service.impl.FreeOrderDAO;
import com.willbes.web.lecture.service.ProductEventService;
import com.willbes.web.lecture.service.impl.ProductEventDAO;

@Service(value="ProductEventService")
public class ProductEventServiceImpl implements ProductEventService{
	@Autowired 
	private ProductEventDAO producteventdao;

	@Autowired
	private FreeOrderDAO freeOrderdao;
	
	@Override
	public List<HashMap<String, String>> list(HashMap<String, String> params){
		return producteventdao.list(params);
	}		
	
	@Override
	public int listCount(HashMap<String, String> params){
		return producteventdao.listCount(params);
	}	
	
	@Override
	public HashMap<String, String> getOne(HashMap<String, String> params){
		return producteventdao.getOne(params);
	}		
	
	@Override
	public List<HashMap<String, String>> list_prd(HashMap<String, String> params){
		return producteventdao.list_prd(params);
	}		
	
	// 강의선택 팝업 카테고리 셀렉트박스 리스트
	@Override @SuppressWarnings("rawtypes")
	public List getCaCatCdList(Map keyName){		
		return freeOrderdao.getCaCatCdList(keyName);
	}
	
	// 강의선택 팝업 학습형태 셀렉트박스 리스트
	@Override @SuppressWarnings("rawtypes")
	public List getVwMenuMstTree_lec(Map keyName){		
		return freeOrderdao.getVwMenuMstTree_lec(keyName);
	}
	
	// 강의선택 팝업 과목 셀렉트박스 리스트
	@Override @SuppressWarnings("rawtypes")
	public List getCaSubjectCdList(Map keyName){		
		return freeOrderdao.getCaSubjectCdList(keyName);
	}
	
	// 강의선택 팝업  리스트
	@Override @SuppressWarnings("rawtypes")
	public List getCbLecMstFreeOrderList(Map keyName){		
		return freeOrderdao.getCbLecMstFreeOrderList(keyName);
	}
		
	// 강의선택 팝업  카운트
	@Override @SuppressWarnings("rawtypes")
	public int getCbLecMstListFreeOrderCount(Map keyName) {
		return freeOrderdao.getCbLecMstListFreeOrderCount(keyName);
	}

	@Override
	public void insert(HashMap<String, String> params){
		producteventdao.insert(params);
	}
	
	@Override
	public void update(HashMap<String, String> params){
		producteventdao.update(params);
	}

	@Override
	public void lec_insert(HashMap<String, String> params){
		producteventdao.lec_insert(params);
	}
	
	@Override
	public void lec_delete(HashMap<String, String> params){
		producteventdao.lec_delete(params);
	}
}
