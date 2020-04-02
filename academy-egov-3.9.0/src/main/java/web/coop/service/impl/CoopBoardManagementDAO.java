package web.coop.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;

@Repository
public class CoopBoardManagementDAO extends EgovComAbstractDAO {

	public List<HashMap<String, Object>> getCoopboardList(Map<String, Object> params) {
		return getSqlSession().selectList("CoopBoardMng.getCoopboardList", params);
	}

	public int getCoopboardListCount(Map<String, Object> params) {
		return getSqlSession().selectOne("CoopBoardMng.getCoopboardListCount", params);
	}

	public HashMap<String, Object> CoopboardView(HashMap<String, Object> params) {
		return getSqlSession().selectOne("CoopBoardMng.CoopboardView", params);
	}

	public void insertCoopboard(HashMap<String, Object> params) {
		getSqlSession().insert("CoopBoardMng.insertCoopboard", params);
	}

	public void updateCoopboard(HashMap<String, Object> params) {
		getSqlSession().update("CoopBoardMng.updateCoopboard", params);
	}

	public void deleteCoopboard(HashMap<String, Object> params) {
		getSqlSession().delete("CoopBoardMng.deleteCoopboard", params);
	}

	public List<HashMap<String, Object>> getCoopCodeList(Map<String, Object> params) {
		return getSqlSession().selectList("CoopBoardMng.getCoopCodeList", params);
	}
	
	public void CoopboardDeleteFile(HashMap<String, Object> params) {
		getSqlSession().update("CoopBoardMng.CoopboardDeleteFile", params);
	}

	public void updateCoopRank(HashMap<String, Object> params) {
		getSqlSession().update("CoopBoardMng.updateCoopRank", params);
	}
}