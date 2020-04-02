package web.lecture.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web.lecture.service.MacAddressManagerService;

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

	@Override
	public List<HashMap<String, String>> MacAdrUserList(HashMap<String, String> params){
		return macaddressmanagerdao.MacAdrUserList(params);
	}
	@Override
	public int MacAdrUserCount(HashMap<String, String> params){
		return macaddressmanagerdao.MacAdrUserCount(params);
	}
	@Override
	public List<HashMap<String, String>> MacAdrUserAllList(HashMap<String, String> params){
		return macaddressmanagerdao.MacAdrUserAllList(params);
	}
	@Override
	public void MacAdrUserUpdate(HashMap<String, String> params){
		macaddressmanagerdao.MacAdrUserUpdate(params);
	}

}
