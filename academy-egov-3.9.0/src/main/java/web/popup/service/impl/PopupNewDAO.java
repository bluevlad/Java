package web.popup.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;

@Repository
public class PopupNewDAO extends EgovComAbstractDAO {

	public List<HashMap<String, Object>> popupNewList(HashMap<String, String> params){
		return getSqlSession().selectList("popupNew.popupNewList", params);
	}

	public int popupNewListCount(HashMap<String, String> params){
		return getSqlSession().selectOne("popupNew.popupNewListCount", params);
	}

	public void popupNewInsert(HashMap<String, String> params){
		getSqlSession().insert("popupNew.popupNewInsert", params);
	}

	public HashMap<String, String> popupNewView(HashMap<String, String> params){
		return getSqlSession().selectOne("popupNew.popupNewView", params);
	}

	public void popupNewUpdate(HashMap<String, String> params){
		getSqlSession().update("popupNew.popupNewUpdate", params);
	}

	public void popupNewDelete(HashMap<String, String> params){
		getSqlSession().delete("popupNew.popupNewDelete", params);
	}

	public void popupNewCheckOPENY(HashMap<String, String> params) {
		getSqlSession().update("popupNew.popupNewCheckOPENY", params);
	}
	public void popupNewCheckOPENN(HashMap<String, String> params) {
		getSqlSession().update("popupNew.popupNewCheckOPENN", params);
	}

	public void popupNewCheckDelete(HashMap<String, String> params) {
		getSqlSession().delete("popupNew.popupNewCheckDelete", params);
	}



}
