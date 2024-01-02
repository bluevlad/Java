package com.willbes.web.boardManagement.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.willbes.web.boardManagement.service.BoardManagementService;

@Service
public class BoardManagementServiceImpl  implements  BoardManagementService{
	
	
	@Autowired
	private BoardManagementDAO boardManagementdao;
	
	public List<HashMap<String, Object>> getBoardMngList(	Map<String, Object> searchMap){
		return boardManagementdao.getBoardMngList(searchMap);
	}
	
	public int getBoardMngListCount(Map<String, Object> searchMap){
		return boardManagementdao.getBoardMngListCount(searchMap);
	}
	public void boardMngInsertProcess(HashMap<String, Object> params){
		boardManagementdao.boardMngInsertProcess(params);
	}
	public HashMap<String, Object> boardMngDetail(HashMap<String, Object> params){
		return boardManagementdao.boardMngDetail(params);
	}
	
	public void boardMngUpdateProcess(HashMap<String, Object> params){
		boardManagementdao.boardMngUpdateProcess(params);
	}
	public void boardMngDelete(HashMap<String, Object> params){
		boardManagementdao.boardMngDelete(params);
	}
	public void boardMngCheckDelete(HashMap<String, Object> params){
		boardManagementdao.boardMngCheckDelete(params);
	}
	public List<HashMap<String, String>> getBoardTypeList(){
		return boardManagementdao.getBoardTypeList();
	}

}
