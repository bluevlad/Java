package com.willbes.web.coop.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.willbes.web.coop.service.CoopBoardManagementService;

@Service
public class CoopBoardManagementServiceImpl  implements  CoopBoardManagementService{
	
	
	@Autowired
	private CoopBoardManagementDAO coopboardManagementdao;
	
	public List<HashMap<String, Object>> getCoopboardList(Map<String, Object> params){
		return coopboardManagementdao.getCoopboardList(params);
	}
	public int getCoopboardListCount(Map<String, Object> params){
		return coopboardManagementdao.getCoopboardListCount(params);
	}
	public HashMap<String, Object> CoopboardView(HashMap<String, Object> params){
		return coopboardManagementdao.CoopboardView(params);
	}
	public void insertCoopboard(HashMap<String, Object> params){
		coopboardManagementdao.insertCoopboard(params);
	}
	public void updateCoopboard(HashMap<String, Object> params){
		coopboardManagementdao.updateCoopboard(params);
	}
	public void deleteCoopboard(HashMap<String, Object> params){
		coopboardManagementdao.deleteCoopboard(params);
	}
	public void updateCoopRank(HashMap<String, Object> params){
		coopboardManagementdao.updateCoopRank(params);
	}

	public List<HashMap<String, Object>> getCoopCodeList(Map<String, Object> params){
		return coopboardManagementdao.getCoopCodeList(params);
	}
	public void CoopboardDeleteFile(HashMap<String, Object> params){
		coopboardManagementdao.CoopboardDeleteFile(params);
	}
}