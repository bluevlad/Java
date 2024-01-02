package com.willbes.web.popup.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.willbes.web.popup.service.PopupService;

@Service(value="popupservice")
public class PopupServiceImpl implements PopupService{
	@Autowired 
	private PopupDAO popupdao;
	
	@Override
	public List<HashMap<String, Object>> popupList(HashMap<String, String> params){
		return popupdao.popupList(params);
	}		
	
	@Override
	public int popupListCount(HashMap<String, String> params){
		return popupdao.popupListCount(params);
	}
	
	@Override
	public void popupInsert(HashMap<String, String> params){
		popupdao.popupInsert(params);
	}	
	
	@Override
	public HashMap<String, String> popupView(HashMap<String, String> params){
		return popupdao.popupView(params);
	}		
	
	@Override
	public void popupUpdate(HashMap<String, String> params){
		popupdao.popupUpdate(params);
	}	
	
	@Override
	public void popupDelete(HashMap<String, String> params){
		popupdao.popupDelete(params);
	}	
	@Override
	public void popupCheckOPENY(HashMap<String, String> params){
		popupdao.popupCheckOPENY(params);
	}	
	@Override
	public void popupCheckOPENN(HashMap<String, String> params){
		popupdao.popupCheckOPENN(params);
	}	
	@Override
	public void popupCheckDelete(HashMap<String, String> params){
		popupdao.popupCheckDelete(params);
	}	
	
			
}