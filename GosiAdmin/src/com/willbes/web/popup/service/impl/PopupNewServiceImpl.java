package com.willbes.web.popup.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.willbes.web.popup.service.PopupNewService;

@Service(value="popupnewservice")
public class PopupNewServiceImpl implements PopupNewService{
	@Autowired 
	private PopupNewDAO popupNewdao;
	
	@Override
	public List<HashMap<String, Object>> popupNewList(HashMap<String, String> params){
		return popupNewdao.popupNewList(params);
	}		
	
	@Override
	public int popupNewListCount(HashMap<String, String> params){
		return popupNewdao.popupNewListCount(params);
	}
	
	@Override
	public void popupNewInsert(HashMap<String, String> params){
		popupNewdao.popupNewInsert(params);
	}	
	
	@Override
	public HashMap<String, String> popupNewView(HashMap<String, String> params){
		return popupNewdao.popupNewView(params);
	}		
	
	@Override
	public void popupNewUpdate(HashMap<String, String> params){
		popupNewdao.popupNewUpdate(params);
	}	
	
	@Override
	public void popupNewDelete(HashMap<String, String> params){
		popupNewdao.popupNewDelete(params);
	}	
	@Override
	public void popupNewCheckOPENY(HashMap<String, String> params){
		popupNewdao.popupNewCheckOPENY(params);
	}	
	@Override
	public void popupNewCheckOPENN(HashMap<String, String> params){
		popupNewdao.popupNewCheckOPENN(params);
	}	
	@Override
	public void popupNewCheckDelete(HashMap<String, String> params){
		popupNewdao.popupNewCheckDelete(params);
	}	
	
			
}