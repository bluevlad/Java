package com.willbes.web.lecture.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.willbes.web.lecture.service.MacAddressManagerService;

@Service(value="macaddressmanagerservice")
public class MacAddressManagerServiceImpl implements MacAddressManagerService{
	@Autowired
	private MacAddressManagerDAO macaddressmanagerdao;

	@Override
	public List<HashMap<String, String>> macaddressmanagerList(HashMap<String, String> params){
		return macaddressmanagerdao.macaddressmanagerList(params);
	}
	
	@Override
	public List<HashMap<String, String>> devicelist(HashMap<String, String> params){
		return macaddressmanagerdao.devicelist(params);
	}
	
	@Override
	public List<HashMap<String, String>> macaddressView(HashMap<String, String> params){
		return macaddressmanagerdao.macaddressView(params);
	}

	@Override
	public int macaddressmanagerListCount(HashMap<String, String> params){
		return macaddressmanagerdao.macaddressmanagerListCount(params);
	}


	@Override
	public void macaddressmanagerUpdate(HashMap<String, String> params){
		macaddressmanagerdao.macaddressmanagerUpdate(params);
	}
	@Override
	public void macaddressmanagerUpdate1(HashMap<String, String> params){
		macaddressmanagerdao.macaddressmanagerUpdate1(params);
	}


}
