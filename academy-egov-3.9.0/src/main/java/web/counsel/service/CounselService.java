package web.counsel.service;

import java.util.HashMap;
import java.util.List;

public interface CounselService {

    List<HashMap<String, String>> dayList(Object obj);
    List<HashMap<String, String>> counselList(HashMap<String, String> params);
    List<HashMap<String, String>> getCounselReqList(HashMap<String, String> params);
    List<HashMap<String, String>> getList(Object obj);
    int getListCount(Object obj);
    List<HashMap<String, String>> getCounselUserReq(HashMap<String, String> params);
    List<HashMap<String, String>> getTimeTable(HashMap<String, String> params);
    int getScheduleCount(HashMap<String, String> params);
    void InsertSchedule(Object obj) throws Exception;
    List<HashMap<String, String>> getSchTable(HashMap<String, String> params);
    void Sch_Modify(Object obj);
    void Sch_Delete(Object obj);

    List<HashMap<String, String>> presentReqList(HashMap<String, String> params);
    int presentReqListCount(HashMap<String, String> params);
    List<HashMap<String, String>> presentCodeList(HashMap<String, String> params);
}
