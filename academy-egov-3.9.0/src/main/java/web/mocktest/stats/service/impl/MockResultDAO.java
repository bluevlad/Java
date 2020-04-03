package com.willbes.web.mocktest.stats.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.willbes.cmm.service.impl.CmmAbstractMapper;

@Repository
public class MockResultDAO extends CmmAbstractMapper {

	public List<HashMap<String, String>> getMockSubjectResultList(HashMap<String, String> params){
		return getSqlSession().selectList("mockTestResult.getMockSubjectResultList", params);
	}

	public void makeMockSbjMst(HashMap<String, String> params){
		getSqlSession().update("mockTestResult.makeMockSbjMst", params);
	}

	public void copyMockSbjRst(HashMap<String, String> params){
		getSqlSession().update("mockTestResult.copyMockSbjRst", params);
	}

	public void makeMockStndDev(HashMap<String, String> params){
		getSqlSession().update("mockTestResult.makeMockStndDev", params);
	}

	public void makeMockAdjust(HashMap<String, String> params){
		getSqlSession().update("mockTestResult.makeMockAdjust", params);
	}

}
