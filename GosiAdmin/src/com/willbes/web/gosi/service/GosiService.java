package com.willbes.web.gosi.service;

import java.util.HashMap;
import java.util.List;

public interface GosiService {
    
	List<HashMap<String, String>> GosiList(HashMap<String, String> params);

    List<HashMap<String, String>> sample_List(HashMap<String, String> params);
    
    int sample_ListCount(HashMap<String, String> params);
    
    void InsertSampleUser(HashMap<String, String> params);
    
    List<HashMap<String, String>> SampleIdView(HashMap<String, String> params);
    
    void SampleIdUpdate(HashMap<String, String> params);
    
    void SampleIdDelete(HashMap<String, String> params);

    List<HashMap<String, String>> getGosiAreaMst(HashMap<String, String> params);
    
    void updateGosiAreaMst(HashMap<String, String> params);

    List<HashMap<String, String>> getVodSubject(HashMap<String, String> params);
    void updateGosiVod(HashMap<String, String> params);

    List<HashMap<String, String>> getGosiStatMst(HashMap<String, String> params);
    void makeGosiResult(HashMap<String, String> params);
    void makeGosiStandard(HashMap<String, String> params);
    void makeGosiStatMst(HashMap<String, String> params);
    void makeGosiAdjustMst(HashMap<String, String> params);
    
    List<HashMap<String, String>> Event_List(HashMap<String, String> params);
    int Event_ListCount(HashMap<String, String> params);

}
