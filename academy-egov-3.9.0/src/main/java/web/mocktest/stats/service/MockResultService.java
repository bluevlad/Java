package web.mocktest.stats.service;

import java.util.HashMap;
import java.util.List;

public interface MockResultService {

	List<HashMap<String, String>> getMockSubjectResultList(HashMap<String, String> params);
    
    void makeMockSbjMst(HashMap<String, String> params);
    
    void copyMockSbjRst(HashMap<String, String> params);
    
    void makeMockStndDev(HashMap<String, String> params);
    
    void makeMockAdjust(HashMap<String, String> params);
	
}
