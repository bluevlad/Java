package com.willbes.web.eventManagement.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.willbes.cmm.service.impl.CmmAbstractMapper;

@Repository
public class LecEventMngDAO extends CmmAbstractMapper {

	public List<HashMap<String, String>> getEventList(Map<String, String> searchMap) {
		return getSqlSession().selectList("LecEventMng.getEventList", searchMap);
	}

	public int getEventListCount(Map<String, String> searchMap) {
		return getSqlSession().selectOne("LecEventMng.getEventListCount", searchMap);
	}

	public Object eventInsertProcess(HashMap<String, String> params) {
		return getSqlSession().insert("LecEventMng.eventInsertProcess", params);
	}

	public HashMap<String, Object> eventDetail(HashMap<String, String> params) {
		return getSqlSession().selectOne("LecEventMng.eventDetail", params);
	}

	public void eventUpdateProcess(HashMap<String, String> params) {
		getSqlSession().update("LecEventMng.eventUpdateProcess", params);
	}

	public void eventDelete(HashMap<String, String> params) {
		getSqlSession().delete("LecEventMng.eventDelete", params);
	}

	public List<HashMap<String, String>> getEventlectureList(Map<String, String> params) {
		return getSqlSession().selectList("LecEventMng.getEventlectureList", params);
	}

	public List<HashMap<String, String>> lectureList(HashMap<String, String> params){
		return getSqlSession().selectList("LecEventMng.lectureList", params);
	}
	
	public int lectureListCount(HashMap<String, String> params){
		return getSqlSession().selectOne("LecEventMng.lectureListCount", params);
	}
	public void insertEventLecture(HashMap<String, String> params) {
		getSqlSession().insert("LecEventMng.insertEventLecture", params);
	}
	public void deleteEventLecture(HashMap<String, String> params) {
		getSqlSession().delete("LecEventMng.deleteEventLecture", params);
	}public List<HashMap<String, String>> getReboundEventList(Map<String, String> searchMap) {
		return getSqlSession().selectList("LecEventMng.getReboundEventList", searchMap);
	}
	public int getReboundEventListCount(Map<String, String> searchMap) {
		return getSqlSession().selectOne("LecEventMng.getReboundEventListCount", searchMap);
	}
	public void updateReboundEventChk(HashMap<String, String> params) {
		getSqlSession().delete("LecEventMng.updateReboundEventChk", params);
	}
}
