package web.counsel.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web.util.CommonUtil;
import web.counsel.service.CounselService;
import web.counsel.service.impl.CounselDAO;

@Service(value="counselservice")
public class CounselServiceImpl implements CounselService {

    @Autowired
    private CounselDAO counseldao;

    @Override
    public List<HashMap<String, String>> dayList(Object obj) {
        return counseldao.dayList(obj);
    }

    @Override
    public List<HashMap<String, String>> counselList(HashMap<String, String> params) {
        return counseldao.counselList(params);
    }

    @Override
    public List<HashMap<String, String>> getCounselReqList(HashMap<String, String> params) {
        return counseldao.getCounselReqList(params);
    }

    @Override
    public List<HashMap<String, String>> getList(Object obj) {
        return counseldao.getList(obj);
    }

    @Override
    public int getListCount(Object obj) {
        return counseldao.getListCount(obj);
    }

    @Override
    public List<HashMap<String, String>> getCounselUserReq(HashMap<String, String> params) {
        return counseldao.getCounselUserReq(params);
    }

    @Override
    public List<HashMap<String, String>> getTimeTable(HashMap<String, String> params) {
        return counseldao.getTimeTable(params);
    }

    @Override
    public int getScheduleCount(HashMap<String, String> params) {
        return counseldao.getScheduleCount(params);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void InsertSchedule(Object obj) throws Exception{
        HashMap<String, String> params = (HashMap<String, String>)obj;

        String CAT_CDs = CommonUtil.isNull(params.get("CAT_CDs"), "");
        if(null != CAT_CDs && CAT_CDs.length() >1) {
            String[] cats = CAT_CDs.split(",");
            for(String cat : cats) {
                if(null != cat && cat.length()>0) {
                    params.put("CAT_CD", cat);

                    for(int i=0; i<Integer.parseInt(params.get("DATE_COUNT")); i++){
                        String year = params.get("SDATE").substring(0, 4);
                        String month = params.get("SDATE").substring(4, 6);
                        String day = params.get("SDATE").substring(6, 8);

                        String DATE = year + "-" + month + "-" + day;
                        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                        Date date = df.parse(DATE);

                        // 날짜 더하기
                        Calendar cal = Calendar.getInstance();
                        cal.setTime(date);
                        cal.add(Calendar.DATE, i);

                        params.put("SCH_DAY", df.format(cal.getTime()));
                        String temp = params.get("REQ_CNT");

                        for(int x=1; x<=Integer.parseInt(params.get("TIME_COUNT")); x++){
                            if(x == 3){
                                params.put("REQ_CNT", "0");
                            }else{
                                params.put("REQ_CNT", temp);
                            }
                            params.put("TS_IDX", String.valueOf(x));
                            counseldao.InsertSchedule(params);
                        }
                    }
                }
            }
        }
    }

    @Override
    public List<HashMap<String, String>> getSchTable(HashMap<String, String> params) {
        return counseldao.getSchTable(params);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void Sch_Modify(Object obj){
        HashMap<String, Object> params = (HashMap<String, Object>)obj;

        String Max_Usr[] = (String[])params.get("REQ_CNT");
        String Isuse[] = (String[])params.get("ISUSE");
        String Req_Type[] = (String[])params.get("REQ_TYPE");

        int TS_IDX = 1;
        for(int a=0; a<Integer.parseInt(String.valueOf(params.get("TIME_COUNT")))-1; a++){
            if(TS_IDX == 3){
                TS_IDX++;
            }
            if(Max_Usr[a].trim().equals("")){
                params.put("REQ_CNT", "0");
            }else{
                params.put("REQ_CNT", Max_Usr[a]);
            }
            params.put("ISUSE", Isuse[a]);
            params.put("REQ_TYPE", Req_Type[a]);
            params.put("TS_IDX", String.valueOf(TS_IDX));

            counseldao.Sch_Modify(params);

            TS_IDX++;
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public void Sch_Delete(Object obj){
        HashMap<String, Object> params = (HashMap<String, Object>)obj;

        String DEL_ARRs[] = (String[])params.get("DEL_ARR");
        for(int i=0; i<DEL_ARRs.length; i++){
            String[] dels = DEL_ARRs[i].split("#");
            params.put("DEL_DATE", dels[0]);
            params.put("CAT_CD", dels[1]);

            counseldao.Sch_Delete(params);
        }
    }

    @Override
    public List<HashMap<String, String>> presentReqList(HashMap<String, String> params) {
        return counseldao.presentReqList(params);
    }

    @Override
    public int presentReqListCount(HashMap<String, String> params) {
        return counseldao.presentReqListCount(params);
    }

    @Override
    public List<HashMap<String, String>> presentCodeList(HashMap<String, String> params) {
        return counseldao.presentCodeList(params);
    }

}
