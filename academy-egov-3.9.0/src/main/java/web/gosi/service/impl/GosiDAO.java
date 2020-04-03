package web.gosi.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;

@Repository
public class GosiDAO extends EgovComAbstractDAO {

	public List<HashMap<String, String>> GosiList(HashMap<String, String> params){
		return getSqlSession().selectList("gosi.GosiList", params);
	}
	
	public HashMap<String, String> chkGosiInfo(HashMap<String, String> params){
		return getSqlSession().selectOne("gosi.chkGosiInfo", params);
	}
	
	public List<HashMap<String, String>> sample_List(HashMap<String, String> params){
		return getSqlSession().selectList("gosi.sample_List", params);
	}

	public int sample_ListCount(HashMap<String, String> params){
		return getSqlSession().selectOne("gosi.sample_ListCount", params);
	}
	
	public List<HashMap<String, Object>> Event1_List(HashMap<String, String> params){
		return getSqlSession().selectList("gosi.event1_List", params);
	}

	public int Event1_ListCount(HashMap<String, String> params){
		return getSqlSession().selectOne("gosi.event1_ListCount", params);
	}
	public List<HashMap<String, String>> Event2_List(HashMap<String, String> params){
		return getSqlSession().selectList("gosi.event2_List", params);
	}

	public int Event2_ListCount(HashMap<String, String> params){
		return getSqlSession().selectOne("gosi.event2_ListCount", params);
	}

	public void InsertSampleUser(HashMap<String, String> params){
		getSqlSession().insert("gosi.InsertSampleUser", params);
	}
	
	public List<HashMap<String, Object>> Event3_List(HashMap<String, String> params){
		return getSqlSession().selectList("gosi.event3_List", params);
	}
	public int Event3_ListCount(HashMap<String, String> params){
		return getSqlSession().selectOne("gosi.event3_ListCount", params);
	}
	
	public List<HashMap<String, String>> SampleIdView(HashMap<String, String> params){
		return getSqlSession().selectList("gosi.SampleIdView", params);
	}

	public void SampleIdUpdate(HashMap<String, String> params){
		getSqlSession().update("gosi.SampleIdUpdate", params);
	}

	public void SampleIdDelete(HashMap<String, String> params){
		getSqlSession().delete("gosi.SampleIdDelete", params);
	}

	public List<HashMap<String, String>> getGosiAreaMst(HashMap<String, String> params){
		return getSqlSession().selectList("gosi.getGosiAreaMst", params);
	}

	public void updateGosiAreaMst(HashMap<String, String> params){
		getSqlSession().update("gosi.updateGosiAreaMst", params);
	}

	public List<HashMap<String, String>> getVodSubject(HashMap<String, String> params){
		return getSqlSession().selectList("gosi.getVodSubject", params);
	}

	public void updateGosiVod(HashMap<String, String> params){
		getSqlSession().update("gosi.updateGosiVod", params);
	}

	public List<HashMap<String, String>> getGosiStatMst(HashMap<String, String> params){
		return getSqlSession().selectList("gosi.getGosiStatMst", params);
	}

	public void makeGosiResult(HashMap<String, String> params){
		getSqlSession().update("gosi.makeGosiResult", params);
	}

	public void makeGosiStatMst(HashMap<String, String> params){
		getSqlSession().update("gosi.makeGosiStatMst", params);
	}

	public void makeGosiAdjustMst(HashMap<String, String> params){
		getSqlSession().update("gosi.makeGosiAdjustMst", params);
	}
	
	public void makeGosiRank(HashMap<String, String> params){
		getSqlSession().update("gosi.makeGosiRank", params);
	}
	
	public void makeGosiPer(HashMap<String, String> params){
		getSqlSession().update("gosi.makeGosiPer", params);
	}
	
	public void GosiUpdate(HashMap<String, String> params){
		getSqlSession().update("gosi.GosiUpdate", params);
	}
	
	public List<HashMap<String, String>> getTestArea(HashMap<String, String> params){
		return getSqlSession().selectList("gosi.getTestArea", params);
	}

	public void updateGosiCod(HashMap<String, String> params){
		getSqlSession().update("gosi.updateGosiCod", params);
	}
	
	public List<HashMap<String, String>> getGosiScorePer(HashMap<String, String> params){
		return getSqlSession().selectList("gosi.getGosiScorePer", params);
	}
	
	public List<HashMap<String, String>> getSubjectScorePer(HashMap<String, String> params){
		return getSqlSession().selectList("gosi.getSubjectScorePer", params);
	}
	
	public void updateGosiScorePer(HashMap<String, String> params){
		getSqlSession().update("gosi.updateGosiScorePer", params);
	}
	
	
	public void updateSubjectScorePer(HashMap<String, String> params){
		getSqlSession().update("gosi.updateSubjectScorePer", params);
	}
	
}
