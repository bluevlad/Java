package com.willbes.web.popup.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.willbes.cmm.service.impl.CmmAbstractMapper;

@Repository
public class PopupDAO extends CmmAbstractMapper {

	public List<HashMap<String, Object>> popupList(HashMap<String, String> params){
		return getSqlSession().selectList("popup.popupList", params);
	}

	public int popupListCount(HashMap<String, String> params){
		return getSqlSession().selectOne("popup.popupListCount", params);
	}

	public void popupInsert(HashMap<String, String> params){
		getSqlSession().insert("popup.popupInsert", params);
	}

	public HashMap<String, String> popupView(HashMap<String, String> params){
		return getSqlSession().selectOne("popup.popupView", params);
	}

	public void popupUpdate(HashMap<String, String> params){
		getSqlSession().update("popup.popupUpdate", params);
	}

	public void popupDelete(HashMap<String, String> params){
		getSqlSession().delete("popup.popupDelete", params);
	}

	public void popupCheckOPENY(HashMap<String, String> params) {
		getSqlSession().update("popup.popupCheckOPENY", params);
	}
	public void popupCheckOPENN(HashMap<String, String> params) {
		getSqlSession().update("popup.popupCheckOPENN", params);
	}

	public void popupCheckDelete(HashMap<String, String> params) {
		getSqlSession().delete("popup.popupCheckDelete", params);
	}



}
