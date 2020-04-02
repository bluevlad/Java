package web.gosi.service;

import java.util.HashMap;
import java.util.List;

public interface GosiService {
    
	List<HashMap<String, String>> GosiList(HashMap<String, String> params);
	
	HashMap<String, String> chkGosiInfo(HashMap<String, String> params);
	
    List<HashMap<String, String>> sample_List(HashMap<String, String> params);
    
    int sample_ListCount(HashMap<String, String> params);
    
    List<HashMap<String, Object>> Event1_List(HashMap<String, String> params);
    
    int Event1_ListCount(HashMap<String, String> params);
    
    List<HashMap<String, String>> Event2_List(HashMap<String, String> params);
    
    int Event2_ListCount(HashMap<String, String> params);
    
    List<HashMap<String, Object>> Event3_List(HashMap<String, String> params);
    
    int Event3_ListCount(HashMap<String, String> params);
    
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
    void makeGosiStatMst(HashMap<String, String> params);
    void makeGosiAdjustMst(HashMap<String, String> params);
    void makeGosiRank(HashMap<String, String> params);
    void makeGosiPer(HashMap<String, String> params);
    
    void GosiUpdate(HashMap<String, String> params);
    
    List<HashMap<String, String>> getTestArea(HashMap<String, String> params);
    void updateGosiCod(HashMap<String, String> params);
    
    List<HashMap<String, String>> getGosiScorePer(HashMap<String, String> params);
    List<HashMap<String, String>> getSubjectScorePer(HashMap<String, String> params);
    void updateGosiScorePer(HashMap<String, String> params);
    void updateSubjectScorePer(HashMap<String, String> params);
}
