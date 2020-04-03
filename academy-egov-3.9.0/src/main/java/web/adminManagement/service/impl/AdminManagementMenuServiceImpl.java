package com.willbes.web.adminManagement.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.willbes.web.adminManagement.service.AdminManagementMenuService;

@Service
public class AdminManagementMenuServiceImpl  implements  AdminManagementMenuService{
	
	
	@Autowired
	private AdminManagementMenuDAO adminManagementMenudao;
	
	
	public List<HashMap<String, Object>> getMenuTree(){
		return adminManagementMenudao.getMenuTree();
	}
	public HashMap<String, Object> getDetailMenu(HashMap<String, Object> params){
		return adminManagementMenudao.getDetailMenu(params);
	}
	public void menuUpdateProcess(HashMap<String, Object> params){
		adminManagementMenudao.menuUpdateProcess(params);
	}
	public int  menuDeleteProcess(HashMap<String, Object> params){
		return adminManagementMenudao.menuDeleteProcess(params);
	}
	public int menuInsertProcess(HashMap<String, Object> params){
		return adminManagementMenudao.menuInsertProcess(params);
	}
	public int  menuIdCheck(HashMap<String, Object> params){
		return adminManagementMenudao.menuIdCheck(params);
	}
	public HashMap<String, Object> getMaxMenuId(HashMap<String, Object> params){
		return adminManagementMenudao.getMaxMenuId(params);
	}
	public List<HashMap<String, Object>> getpassMenuTree(){
		return adminManagementMenudao.getpassMenuTree();
	}
	public HashMap<String, Object> getpassDetailMenu(HashMap<String, Object> params){
		return adminManagementMenudao.getpassDetailMenu(params);
	}
	public HashMap<String, Object> getpassMaxMenuId(HashMap<String, Object> params){
		return adminManagementMenudao.getpassMaxMenuId(params);
	}
	public int passmenuInsertProcess(HashMap<String, Object> params){
		return adminManagementMenudao.passmenuInsertProcess(params);
	}
	public void PassmenuUpdateProcess(HashMap<String, Object> params){
		adminManagementMenudao.PassmenuUpdateProcess(params);
	}
	public int  passmenuDeleteProcess(HashMap<String, Object> params){
		return adminManagementMenudao.passmenuDeleteProcess(params);
	}
	
}
