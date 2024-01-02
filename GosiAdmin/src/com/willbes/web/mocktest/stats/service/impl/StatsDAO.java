package com.willbes.web.mocktest.stats.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.willbes.cmm.service.impl.CmmAbstractMapper;

@Repository
public class StatsDAO extends CmmAbstractMapper {

	public List<HashMap<String, String>> statsList(HashMap<String, String> params){
		return getSqlSession().selectList("stats.statsList", params);
	}

	public int statsListCount(HashMap<String, String> params){
		return getSqlSession().selectOne("stats.statsListCount", params);
	}

	public List<HashMap<String, String>> offererList(HashMap<String, String> params){
		return getSqlSession().selectList("stats.offererList", params);
	}

	public int offererListCount(HashMap<String, String> params){
		return getSqlSession().selectOne("stats.offererListCount", params);
	}

	public List<HashMap<String, String>> offererListExcel(HashMap<String, String> params){
		return getSqlSession().selectList("stats.offererListExcel", params);
	}

	public List<HashMap<String, String>> getSubjectList(HashMap<String, String> params){
		return getSqlSession().selectList("stats.getSubjectList", params);
	}


	public List<HashMap<String, String>> statsSubjectList(HashMap<String, String> params){
		return getSqlSession().selectList("stats.statsSubjectList", params);
	}

	public List<HashMap<String, String>> statsSubjectAnswerList(HashMap<String, String> params){
		return getSqlSession().selectList("stats.statsSubjectAnswerList", params);
	}

	public List<HashMap<String, String>> statsSubjectInfoList(HashMap<String, String> params){
		return getSqlSession().selectList("stats.statsSubjectInfoList", params);
	}


	public List<HashMap<String, String>> statsTotalInfoList(HashMap<String, String> params){
		return getSqlSession().selectList("stats.statsTotalInfoList", params);
	}

	public List<HashMap<String, String>> statsTotalInfo1(HashMap<String, String> params){
		return getSqlSession().selectList("stats.statsTotalInfo1", params);
	}

	public List<HashMap<String, String>> statsTotalInfo2(HashMap<String, String> params){
		return getSqlSession().selectList("stats.statsTotalInfo2", params);
	}

	public List<HashMap<String, String>> statsTotalInfo2_TblH(HashMap<String, String> params){
		return getSqlSession().selectList("stats.statsTotalInfo2_TblH", params);
	}

	public List<HashMap<String, String>> statsTotalInfo2_Tbl(HashMap<String, String> params){
		return getSqlSession().selectList("stats.statsTotalInfo2_Tbl", params);
	}

	public List<HashMap<String, String>> statsTotalInfo4SubjectList(HashMap<String, String> params){
		return getSqlSession().selectList("stats.statsTotalInfo4SubjectList", params);
	}

	public List<HashMap<String, String>> statsTotalInfo4MockList(HashMap<String, String> params){
		return getSqlSession().selectList("stats.statsTotalInfo4MockList", params);
	}

	public List<HashMap<String, String>> statsTotalInfo4MockSubjectScoreList(HashMap<String, String> params){
		return getSqlSession().selectList("stats.statsTotalInfo4MockSubjectScoreList", params);
	}

	public List<HashMap<String, String>> statsTotalList(HashMap<String, String> params){
		return getSqlSession().selectList("stats.statsTotalList", params);
	}

	public int statsTotalListCount(HashMap<String, String> params){
		return getSqlSession().selectOne("stats.statsTotalListCount", params);
	}

	public List<HashMap<String, String>> statsTotalViewClassseriesList(HashMap<String, String> params){
		return getSqlSession().selectList("stats.statsTotalViewClassseriesList", params);
	}

	public List<HashMap<String, String>> statsTotalViewInfo(HashMap<String, String> params){
		return getSqlSession().selectList("stats.statsTotalViewInfo", params);
	}

	public List<HashMap<String, String>> statsTotalView_TblH(HashMap<String, String> params){
		return getSqlSession().selectList("stats.statsTotalView_TblH", params);
	}

	public List<HashMap<String, String>> statsTotalSubjectView(HashMap<String, String> params){
		return getSqlSession().selectList("stats.statsTotalSubjectView", params);
	}

	public List<HashMap<String, String>> statsTotalSubjectViewAver(HashMap<String, String> params){
		return getSqlSession().selectList("stats.statsTotalSubjectViewAver", params);
	}

	public int offererOnCntY(HashMap<String, String> params){
		return getSqlSession().selectOne("stats.offererOnCntY", params);
	}

	public int offererOnCntN(HashMap<String, String> params){
		return getSqlSession().selectOne("stats.offererOnCntN", params);
	}

	public int offererOffCntY(HashMap<String, String> params){
		return getSqlSession().selectOne("stats.offererOffCntY", params);
	}

	public int offererOffCntN(HashMap<String, String> params){
		return getSqlSession().selectOne("stats.offererOffCntN", params);
	}

	public int statsTotalCnt(HashMap<String, String> params){
		return getSqlSession().selectOne("stats.statsTotalCnt", params);
	}
}
