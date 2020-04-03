package com.willbes.web.boardViewManagement.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.willbes.web.boardViewManagement.service.BoardViewManagementService;

@Service
public class BoardViewManagementServiceImpl  implements  BoardViewManagementService{
	
	
	@Autowired
	private BoardViewManagementDAO boardViewManagementdao;
	
	public List<HashMap<String, Object>> getBoardMngList(	Map<String, Object> searchMap){
		return boardViewManagementdao.getBoardMngList(searchMap);
	}
	
	public int getBoardMngListCount(Map<String, Object> searchMap){
		return boardViewManagementdao.getBoardMngListCount(searchMap);
	}
	public void boardMngInsertProcess(HashMap<String, Object> params){
		boardViewManagementdao.boardMngInsertProcess(params);
	}
	public HashMap<String, Object> boardMngDetail(HashMap<String, Object> params){
		return boardViewManagementdao.boardMngDetail(params);
	}
	
	public void boardMngUpdateProcess(HashMap<String, Object> params){
		boardViewManagementdao.boardMngUpdateProcess(params);
	}
	public void boardMngDelete(HashMap<String, Object> params){
		boardViewManagementdao.boardMngDelete(params);
	}
	public void boardMngCheckDelete(HashMap<String, Object> params){
		boardViewManagementdao.boardMngCheckDelete(params);
	}
	public List<HashMap<String, String>> getBoardTypeList(){
		return boardViewManagementdao.getBoardTypeList();
	}

	@Override
	public List<HashMap<String, String>> boardCSList(HashMap<String, String> params) {
		return boardViewManagementdao.boardCSList(params);
	}
	@Override
	public int boardCSListCount(HashMap<String, String> params) {
		return boardViewManagementdao.boardCSListCount(params);
	}

}
