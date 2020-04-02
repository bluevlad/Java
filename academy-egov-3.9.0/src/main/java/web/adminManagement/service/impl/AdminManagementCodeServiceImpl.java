package web.adminManagement.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web.adminManagement.service.AdminManagementCodeService;

@Service
public class AdminManagementCodeServiceImpl  implements  AdminManagementCodeService{
	
	/**
	 * @Date         : 2016. 08. 07.
	*/
	
	@Autowired
	private AdminManagementCodeDAO adminManagementCodedao; 
	
	public List<HashMap<String, Object>> getCommonCodeList(	Map<String, Object> searchMap){
		return adminManagementCodedao.getCommonCodeList(searchMap);
	}
	
	public int getCommonCodeListCount(Map<String, Object> searchMap){
		return adminManagementCodedao.getCommonCodeListCount(searchMap);
	}
	public void commonCodeInsertProcess(HashMap<String, Object> params){
		adminManagementCodedao.commonCodeInsertProcess(params);
	}
	public HashMap<String, Object> commonCodeDetail(HashMap<String, Object> params){
		return adminManagementCodedao.commonCodeDetail(params);
	}
	
	public void commonCodeUpdateProcess(HashMap<String, Object> params){
		adminManagementCodedao.commonCodeUpdateProcess(params);
	}
	public void commonCodeDelete(HashMap<String, Object> params){
		adminManagementCodedao.commonCodeDelete(params);
	}
	public void commonCodeCheckDelete(HashMap<String, Object> params){
		adminManagementCodedao.commonCodeCheckDelete(params);
	}
	public List<HashMap<String, Object>> getpassCodeTree(){
		return adminManagementCodedao.getpassCodeTree();
	}
	public HashMap<String, Object> getpassDetailCode(HashMap<String, Object> params){
		return adminManagementCodedao.getpassDetailCode(params);
	}
	public int  passcodeDeleteProcess(HashMap<String, Object> params){
		return adminManagementCodedao.passcodeDeleteProcess(params);
	}
	public void PasscodeUpdateProcess(HashMap<String, Object> params){
		adminManagementCodedao.PasscodeUpdateProcess(params);
	}
	public HashMap<String, Object> getpassMaxCodeId(HashMap<String, Object> params){
		return adminManagementCodedao.getpassMaxCodeId(params);
	}
	public int passcodeInsertProcess(HashMap<String, Object> params){
		return adminManagementCodedao.passcodeInsertProcess(params);
	}

	public List<HashMap<String, Object>> getBaConfigCodeList(Map<String, Object> searchMap){
		return adminManagementCodedao.getBaConfigCodeList(searchMap);
	}
}
