package com.willbes.web.gosi.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.willbes.cmm.service.impl.CmmAbstractMapper;

@Repository
public class GosiDAO extends CmmAbstractMapper {

	public List<HashMap<String, String>> GosiList(HashMap<String, String> params){
		return getSqlSession().selectList("gosi.GosiList", params);
	}

	public List<HashMap<String, String>> sample_List(HashMap<String, String> params){
		return getSqlSession().selectList("gosi.sample_List", params);
	}

	public int sample_ListCount(HashMap<String, String> params){
		return getSqlSession().selectOne("gosi.sample_ListCount", params);
	}

	public void InsertSampleUser(HashMap<String, String> params){
		getSqlSession().insert("gosi.InsertSampleUser", params);
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

	public void makeGosiStandard(HashMap<String, String> params){
		getSqlSession().update("gosi.makeGosiStandard", params);
	}

	public void makeGosiStatMst(HashMap<String, String> params){
		getSqlSession().update("gosi.makeGosiStatMst", params);
	}

	public void makeGosiAdjustMst(HashMap<String, String> params){
		getSqlSession().update("gosi.makeGosiAdjustMst", params);
	}
	public List<HashMap<String, String>> Event_List(HashMap<String, String> params){
		return getSqlSession().selectList("gosi.Event_List", params);
	}

	public int Event_ListCount(HashMap<String, String> params){
		return getSqlSession().selectOne("gosi.Event_ListCount", params);
	}
}
