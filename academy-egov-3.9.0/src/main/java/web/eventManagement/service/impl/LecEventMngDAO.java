package web.eventManagement.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;

@Repository
public class LecEventMngDAO extends EgovComAbstractDAO {

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
	}
	public List<HashMap<String, String>> getReboundEventList(Map<String, String> searchMap) {
		return getSqlSession().selectList("LecEventMng.getReboundEventList", searchMap);
	}
	public int getReboundEventListCount(Map<String, String> searchMap) {
		return getSqlSession().selectOne("LecEventMng.getReboundEventListCount", searchMap);
	}
	public void updateReboundEventChk(HashMap<String, String> params) {
		getSqlSession().delete("LecEventMng.updateReboundEventChk", params);
	}
	public void deleteEventCoupon(HashMap<String, String> params) {
		getSqlSession().delete("LecEventMng.deleteEventCoupon", params);
	}
	public void insertEventCoupon(HashMap<String, String> params) {
		getSqlSession().insert("LecEventMng.insertEventCoupon", params);
	}
	public int chkMyCouponByEventNo(Map<String, String> searchMap) {
		return getSqlSession().selectOne("LecEventMng.chkMyCouponByEventNo", searchMap);
	}
	public void insertDelEventCoupon(HashMap<String, String> params) {
		getSqlSession().insert("LecEventMng.insertDelEventCoupon", params);
	}
	public List<HashMap<String, String>> getSupportEventList(Map<String, String> searchMap) {
		return getSqlSession().selectList("LecEventMng.getSupportEventList", searchMap);
	}
	public int getSupportEventListCount(Map<String, String> searchMap) {
		return getSqlSession().selectOne("LecEventMng.getSupportEventListCount", searchMap);
	}
}
