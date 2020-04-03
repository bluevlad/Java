package com.willbes.web.counsel.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.willbes.cmm.service.impl.CmmAbstractMapper;

@Repository
public class CounselDAO extends CmmAbstractMapper {

    // 상담운영 일자표  2015.01.06
    public List<HashMap<String, String>> dayList(Object obj) {
        return getSqlSession().selectList("counsel.dayList", obj);
    }

    // 상담운영 계획  2015.01.05
    public List<HashMap<String, String>> counselList(HashMap<String, String> params) {
        return getSqlSession().selectList("counsel.counselList", params);
    }

    // 상담요청 명단 2015.01.05
    public List<HashMap<String, String>> getCounselReqList(HashMap<String, String> params) {
        return getSqlSession().selectList("counsel.getCounselReqList", params);
    }

    public List<HashMap<String, String>> getList(Object obj) {
        return getSqlSession().selectList("counsel.getList", obj);
    }

    public int getListCount(Object obj) {
        return getSqlSession().selectOne("counsel.getListCount", obj);
    }

    // 개인별 상담요청 내용 2015.01.05
    public List<HashMap<String, String>> getCounselUserReq(HashMap<String, String> params) {
        return getSqlSession().selectList("counsel.getCounselUserReq", params);
    }

    public List<HashMap<String, String>> getTimeTable(HashMap<String, String> params) {
        return getSqlSession().selectList("counsel.getTimeTable", params);
    }

    public int getScheduleCount(HashMap<String, String> params) {
        return getSqlSession().selectOne("counsel.getScheduleCount", params);
    }

    public void InsertSchedule(Object obj){
        getSqlSession().insert("counsel.InsertSchedule", obj);
    }

    public List<HashMap<String, String>> getSchTable(HashMap<String, String> params) {
        return getSqlSession().selectList("counsel.getSchTable", params);
    }

    public void Sch_Modify(Object obj){
        getSqlSession().update("counsel.Sch_Modify", obj);
    }

    public void Sch_Delete(Object obj){
        getSqlSession().delete("counsel.Sch_Delete", obj);
    }

    public List<HashMap<String, String>> presentReqList(HashMap<String, String> params) {
        return getSqlSession().selectList("counsel.presentReqList", params);
    }

    public int presentReqListCount(HashMap<String, String> params) {
        return getSqlSession().selectOne("counsel.presentReqListCount", params);
    }

    public List<HashMap<String, String>> presentCodeList(HashMap<String, String> params) {
        return getSqlSession().selectList("counsel.presentCodeList", params);
    }

}
