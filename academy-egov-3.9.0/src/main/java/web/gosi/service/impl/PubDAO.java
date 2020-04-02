package web.gosi.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;

@Repository
public class PubDAO extends EgovComAbstractDAO {

	public List<HashMap<String, String>> getPubCategory(HashMap<String, String> params){
		return getSqlSession().selectList("pub.getPubCategory", params);
	}
	public HashMap<String, String> getPubCategoryOne(HashMap<String, String> params) {
		return getSqlSession().selectOne("pub.getPubCategoryOne", params);
	}
	public int insertPubCategory(HashMap<String, String> params){
		return getSqlSession().insert("pub.insertPubCategory", params);
	}
	public void updatePubCategory(HashMap<String, String> params){
		getSqlSession().update("pub.updatePubCategory", params);
	}
	public int deletePubCategory(HashMap<String, String> params){
		return getSqlSession().delete("pub.deletePubCategory", params);
	}
	public List<HashMap<String, String>> getPubCategorySel(HashMap<String, String> params){
		return getSqlSession().selectList("pub.getPubCategorySel", params);
	}
	
	public List<HashMap<String, String>> getPubGubun(HashMap<String, String> params){
		return getSqlSession().selectList("pub.getPubGubun", params);
	}
	public List<HashMap<String, String>> getPubList(HashMap<String, String> params){
		return getSqlSession().selectList("pub.getPubList", params);
	}
	public int getPubListCount(HashMap<String, String> params){
		return getSqlSession().selectOne("pub.getPubListCount", params);
	}
	public HashMap<String, String> getPubOne(HashMap<String, String> params) {
		return getSqlSession().selectOne("pub.getPubOne", params);
	}
	public void Pub_Insert(HashMap<String, String> params){
		getSqlSession().insert("pub.Pub_Insert", params);
	}
	public void Pub_Update(HashMap<String, String> params){
		getSqlSession().update("pub.Pub_Update", params);
	}
	public void Pub_delete(HashMap<String, String> params){
		getSqlSession().delete("pub.Pub_delete", params);
	}
    public int getMaxPubNo(HashMap<String, String> params){
        return getSqlSession().selectOne("pub.getMaxPubNo", params);
    }
	public List<HashMap<String, String>> getPubFiles(HashMap<String, String> params){
		return getSqlSession().selectList("pub.getPubFiles", params);
	}
	public void AttachFile_insert(HashMap<String, String> params){
		getSqlSession().update("pub.AttachFile_insert", params);
	}
	public void AttachFile_delete(HashMap<String, String> params){
		getSqlSession().update("pub.AttachFile_delete", params);
	}

}