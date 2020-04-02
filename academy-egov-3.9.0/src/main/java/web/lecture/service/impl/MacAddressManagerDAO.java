package web.lecture.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;

@Repository
public class MacAddressManagerDAO extends EgovComAbstractDAO {

	public List<HashMap<String, String>> macaddressmanagerList(HashMap<String, String> params){
		return getSqlSession().selectList("macaddressmanager.macaddressmanagerList", params);
	}
	
	public List<HashMap<String, String>> devicelist(HashMap<String, String> params){
		return getSqlSession().selectList("macaddressmanager.devicelist", params);
	}
	
	public List<HashMap<String, String>> macaddressView(HashMap<String, String> params){
		return getSqlSession().selectList("macaddressmanager.macaddressView", params);
	}

	public int macaddressmanagerListCount(HashMap<String, String> params){
		return getSqlSession().selectOne("macaddressmanager.macaddressmanagerListCount", params);
	}


	public void macaddressmanagerUpdate(HashMap<String, String> params){
		getSqlSession().insert("macaddressmanager.macaddressmanagerUpdate", params);
	}
	public void macaddressmanagerUpdate1(HashMap<String, String> params){
		getSqlSession().insert("macaddressmanager.macaddressmanagerUpdate1", params);
	}

	public List<HashMap<String, String>> MacAdrUserList(HashMap<String, String> params){
		return getSqlSession().selectList("macaddressmanager.MacAdrUserList", params);
	}
	public int MacAdrUserCount(HashMap<String, String> params){
		return getSqlSession().selectOne("macaddressmanager.MacAdrUserCount", params);
	}
	public List<HashMap<String, String>> MacAdrUserAllList(HashMap<String, String> params){
		return getSqlSession().selectList("macaddressmanager.MacAdrUserAllList", params);
	}
	public void MacAdrUserUpdate(HashMap<String, String> params){
		getSqlSession().update("macaddressmanager.MacAdrUserUpdate", params);
	}

}
