package com.willbes.web.lecture.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.willbes.web.lecture.service.FormService;
import com.willbes.web.lecture.service.impl.FormDAO;

@Service(value="formservice")
public class FormServiceImpl implements FormService{
	@Autowired 
	private FormDAO formdao;
	
	@Override
	public List<HashMap<String, String>> formList(HashMap<String, String> params){
		return formdao.formList(params);
	}		
	
	@Override
	public int formListCount(HashMap<String, String> params){
		return formdao.formListCount(params);
	}	
	
	@Override
	public String formGetCode(){
		return formdao.formGetCode();
	}	
	
	@Override
	public void formInsert(HashMap<String, String> params){
		formdao.formInsert(params);
	}

	@Override
	public List<HashMap<String, String>> formView(HashMap<String, String> params){
		return formdao.formView(params);
	}
	
	@Override
	public void formUpdate(HashMap<String, String> params){
		formdao.formUpdate(params);
	}
	
	@Override
	public void formDelete(HashMap<String, String> params){
		formdao.formDelete(params);
	}
	
	@Override
	public int formCheck(HashMap<String, String> params){
		return formdao.formCheck(params);
	}
	
	@Override
	public List<HashMap<String, String>> getCodeList(HashMap<String, String> params){
		return formdao.getCodeList(params);
	}			
}
