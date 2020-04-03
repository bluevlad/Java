package web.memberAdminManagement.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web.memberAdminManagement.service.MemberAdminManagementService;

@Service
public class MemberAdminManagementServiceImpl  implements  MemberAdminManagementService{
	
	
	@Autowired
	private MemberAdminManagementDAO memberAdminManagementdao;
	
	public List<HashMap<String, Object>> getMemberList(	Map<String, Object> searchMap){
		return memberAdminManagementdao.getMemberList(searchMap);
	}
	
	public int getMemberListCount(Map<String, Object> searchMap){
		return memberAdminManagementdao.getMemberListCount(searchMap);
	}
	
	public int memberIdCheck(HashMap<String, String> params){
		return memberAdminManagementdao.memberIdCheck(params);
	}
	
	public List<HashMap<String, String>> getSiteList(){
		return memberAdminManagementdao.getSiteList();
	}
	
	public void memberInsertProcess(HashMap<String, String> params){
		memberAdminManagementdao.memberInsertProcess(params);
	}
	public void memberInfoInsertProcess(HashMap<String, String> params){
		memberAdminManagementdao.memberInfoInsertProcess(params);
	}
	
	public HashMap<String, Object> memberDetail(HashMap<String, Object> params){
		return memberAdminManagementdao.memberDetail(params);
	}
	
	public void memberUpdateProcess(HashMap<String, String> params){
		memberAdminManagementdao.memberUpdateProcess(params);
	}
	public void memberDelete(HashMap<String, String> params){
		memberAdminManagementdao.memberDelete(params);
	}
	public void memberAdminSendMessage(HashMap<String, String> params){
		memberAdminManagementdao.memberAdminSendMessage(params);
	}
	
	public HashMap<String, String> getMemberAdminEmail(HashMap<String, String> params){
		return memberAdminManagementdao.getMemberAdminEmail(params);
	}
	
	public void MemberAdminInsertEmail(HashMap<String, String> params){
		memberAdminManagementdao.MemberAdminInsertEmail(params);
	}
	
	public List<HashMap<String, Object>> getAdminLoginList(	Map<String, Object> searchMap){
		return memberAdminManagementdao.getAdminLoginList(searchMap);
	}
	
	public int getAdminLoginListCount(Map<String, Object> searchMap){
		return memberAdminManagementdao.getAdminLoginListCount(searchMap);
	}
	
}
