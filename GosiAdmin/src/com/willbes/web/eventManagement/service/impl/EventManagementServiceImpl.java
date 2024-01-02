package com.willbes.web.eventManagement.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.willbes.web.eventManagement.service.EventManagementService;

@Service
public class EventManagementServiceImpl  implements  EventManagementService{
	
	@Autowired
	private EventManagementDAO eventManagementDAO;
	
	public List<HashMap<String, String>> getEventList(	Map<String, String> searchMap){
		return eventManagementDAO.getEventList(searchMap);
	}
	
	public int getEventListCount(Map<String, String> searchMap){
		return eventManagementDAO.getEventListCount(searchMap);
	}
	
	public Object eventInsertProcess(HashMap<String, String> params){
		return eventManagementDAO.eventInsertProcess(params);
	}
	
	public HashMap<String, Object> eventDetail(HashMap<String, String> params){
		return eventManagementDAO.eventDetail(params);
	}
	public List<HashMap<String, String>> eventFileList(HashMap<String, String> params) {
		return eventManagementDAO.eventFileList(params);
	}
	public List<HashMap<String, String>> eventDetailOption1List(HashMap<String, String> params) {
		return eventManagementDAO.eventDetailOption1List(params);
	}	
	public List<HashMap<String, String>> eventDetailOption2ListAll(HashMap<String, String> params) {
		return eventManagementDAO.eventDetailOption2ListAll(params);
	}
	public List<HashMap<String, String>> eventDetailOption2List(HashMap<String, String> params) {
		return eventManagementDAO.eventDetailOption2List(params);
	}
	public int eventDetailOption2ListCount(Map<String, String> searchMap){
		return eventManagementDAO.eventDetailOption2ListCount(searchMap);
	}
	
	public void insertEventFile(HashMap<String, String> params){
		eventManagementDAO.insertEventFile(params);
	}
	public void insertEventOption1(HashMap<String, String> params){
		eventManagementDAO.insertEventOption1(params);
	}
	public void insertEventOption2(HashMap<String, String> params){
		eventManagementDAO.insertEventOption2(params);
	}
	public void insertEventOption3(HashMap<String, String> params){
		eventManagementDAO.insertEventOption3(params);
	}
	public void insertEventOption4(HashMap<String, String> params){
		eventManagementDAO.insertEventOption4(params);
	}
	
	public void deleteEventOption2(HashMap<String, String> params){
		eventManagementDAO.deleteEventOption2(params);
	}
	
	public void deleteEventOption3(HashMap<String, String> params){
		eventManagementDAO.deleteEventOption3(params);
	}
	
	public void deleteEventOption4(HashMap<String, String> params){
		eventManagementDAO.deleteEventOption4(params);
	}
	
	public void eventUpdateProcess(HashMap<String, String> params){
		eventManagementDAO.eventUpdateProcess(params);
	}
	public void eventDelete(HashMap<String, String> params){
		eventManagementDAO.eventDelete(params);
		eventManagementDAO.deleteEventOption1(params);
		eventManagementDAO.deleteEventOption2(params);
		eventManagementDAO.deleteEventFile(params);
	}
	public void deleteEventFile(HashMap<String, String> params){
		eventManagementDAO.deleteEventFile(params);
	}
	public void deleteEventOption1(HashMap<String, String> params){
		eventManagementDAO.deleteEventOption1(params);
	}	
	public void updateEventFile(HashMap<String, String> params){
		eventManagementDAO.insertEventOption1(params);
	}
	
	public List<HashMap<String, String>> getEventResultList(	Map<String, String> searchMap){
		return eventManagementDAO.getEventResultList(searchMap);
	}
	
	public int getEventResultListCount(Map<String, String> searchMap){
		return eventManagementDAO.getEventResultListCount(searchMap);
	}
	
	public List<HashMap<String, String>> getEventlectureList(HashMap<String, String> params){
		return eventManagementDAO.getEventlectureList(params);
	}
	
	@Override
	public List<HashMap<String, String>> lectureList(HashMap<String, String> params){
		return eventManagementDAO.lectureList(params);
	}		
	
	@Override
	public int lectureListCount(HashMap<String, String> params){
		return eventManagementDAO.lectureListCount(params);
	}	
	
	public void insertEventLecture(HashMap<String, String> params){
		eventManagementDAO.insertEventLecture(params);
	}
	public void deleteEventLecture(HashMap<String, String> params){
		eventManagementDAO.deleteEventLecture(params);
	}
}
