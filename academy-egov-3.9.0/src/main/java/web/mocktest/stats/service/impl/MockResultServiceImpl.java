package web.mocktest.stats.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web.mocktest.stats.service.MockResultService;

@Service(value="mockresultservice")
public class MockResultServiceImpl implements MockResultService{
	@Autowired 
	private MockResultDAO MockResultDAO;

	@Override
	public List<HashMap<String, String>> getMockSubjectResultList(HashMap<String, String> params){
		return MockResultDAO.getMockSubjectResultList(params);
	}
    
    @Override
    public void makeMockSbjMst(HashMap<String, String> params){
    	MockResultDAO.makeMockSbjMst(params);
    }
    
    @Override
    public void copyMockSbjRst(HashMap<String, String> params){
    	MockResultDAO.copyMockSbjRst(params);
    }
    
    @Override
    public void makeMockAdjust(HashMap<String, String> params){
    	MockResultDAO.makeMockAdjust(params);
    }
    
    @Override
    public void makeMockStndDev(HashMap<String, String> params){
    	MockResultDAO.makeMockStndDev(params);
    }
	
}
