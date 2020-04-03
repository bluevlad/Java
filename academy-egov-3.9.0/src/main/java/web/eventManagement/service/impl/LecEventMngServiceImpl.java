package web.eventManagement.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web.eventManagement.service.LecEventMngService;

@Service
public class LecEventMngServiceImpl  implements  LecEventMngService{
	
	@Autowired
	private LecEventMngDAO LecEventMngDAO;
	
	public List<HashMap<String, String>> getEventList(	Map<String, String> searchMap){
		return LecEventMngDAO.getEventList(searchMap);
	}
	
	public int getEventListCount(Map<String, String> searchMap){
		return LecEventMngDAO.getEventListCount(searchMap);
	}
	
	public Object eventInsertProcess(HashMap<String, String> params){
		return LecEventMngDAO.eventInsertProcess(params);
	}
	
	public HashMap<String, Object> eventDetail(HashMap<String, String> params){
		return LecEventMngDAO.eventDetail(params);
	}

	public void eventUpdateProcess(HashMap<String, String> params){
		LecEventMngDAO.eventUpdateProcess(params);
	}
	public void eventDelete(HashMap<String, String> params){
		LecEventMngDAO.eventDelete(params);
	}
	
	public List<HashMap<String, String>> getEventlectureList(HashMap<String, String> params){
		return LecEventMngDAO.getEventlectureList(params);
	}
	
	@Override
	public List<HashMap<String, String>> lectureList(HashMap<String, String> params){
		return LecEventMngDAO.lectureList(params);
	}		
	
	@Override
	public int lectureListCount(HashMap<String, String> params){
		return LecEventMngDAO.lectureListCount(params);
	}	
	
	public void insertEventLecture(HashMap<String, String> params){
		LecEventMngDAO.insertEventLecture(params);
	}
	public void deleteEventLecture(HashMap<String, String> params){
		LecEventMngDAO.deleteEventLecture(params);
	}
	
	@Override
	public List<HashMap<String, String>> getReboundEventList(Map<String, String> searchMap) {
		// TODO Auto-generated method stub
		return LecEventMngDAO.getReboundEventList(searchMap);
	}

	@Override
	public int getReboundEventListCount(Map<String, String> searchMap) {
		// TODO Auto-generated method stub
		return LecEventMngDAO.getReboundEventListCount(searchMap);
	}
	
	public void updateReboundEventChk(HashMap<String, String> params){
		LecEventMngDAO.updateReboundEventChk(params);
	}

	@Override
	public void deleteEventCoupon(HashMap<String, String> params) {
		LecEventMngDAO.deleteEventCoupon(params);
	}

	@Override
	public void insertEventCoupon(HashMap<String, String> params) {
		LecEventMngDAO.insertEventCoupon(params);
	}

	@Override
	public int chkMyCouponByEventNo(Map<String, String> searchMap) {
		return LecEventMngDAO.chkMyCouponByEventNo(searchMap);
	}
	
	@Override
	public void insertDelEventCoupon(HashMap<String, String> params) {
		LecEventMngDAO.insertDelEventCoupon(params);
	}
	
	@Override
	public List<HashMap<String, String>> getSupportEventList(Map<String, String> searchMap) {
		// TODO Auto-generated method stub
		return LecEventMngDAO.getSupportEventList(searchMap);
	}

	@Override
	public int getSupportEventListCount(Map<String, String> searchMap) {
		// TODO Auto-generated method stub
		return LecEventMngDAO.getSupportEventListCount(searchMap);
	}
}
