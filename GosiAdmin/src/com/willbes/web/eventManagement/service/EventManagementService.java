package com.willbes.web.eventManagement.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @FileName   : BannerManagementService.java
 * @Project    : dev_willbesWebAdmin
 * @Date       : 2013. 12 
 * @Author     : 
 * @변경이력    :
 * @프로그램 설명 : 배너 관리 service
 */
public interface EventManagementService {
	
	/**
	 * @Method Name  : getBannerList
	 * @Date         : 2013. 12 
	 * @Author       : 
	 * @변경이력      :
	 * @Method 설명      : 	배너 관리 -  배너조회  리스트
	 * @param searchMap
	 * @return
	*/
	List<HashMap<String, String>> getEventList(Map<String, String> searchMap);
	/**
	 * @Method Name  : getMemberListCount
	 * @Date         : 2013. 11. 1. 
	 * @Author       : seojeong
	 * @변경이력      :
	 * @Method 설명      :	운영자 관리 -  운영자조회  리스트 총 갯수
	 * @param searchMap
	 * @return
	*/
	int getEventListCount(Map<String, String> searchMap);
	
	/**
	 * @Method Name  : eventInsertProcess
	 * @Date         : 2013. 12 
	 * @Author       : 
	 * @변경이력      :
	 * @Method 설명      :	배너 등록 프로세스
	 * @param params
	*/
	Object eventInsertProcess(HashMap<String, String> params);
	
	/**
	 * @Method Name  : eventDetail
	 * @Date         : 2013. 12 
	 * @Author       : 
	 * @변경이력      :
	 * @Method 설명      :	배너 상세 페이지 
	 * @param params
	 * @return
	*/
	HashMap<String, Object> eventDetail(HashMap<String, String> params);
	List<HashMap<String, String>> eventFileList(HashMap<String, String> params);
	List<HashMap<String, String>> eventDetailOption2ListAll(HashMap<String, String> params);
	List<HashMap<String, String>> eventDetailOption2List(HashMap<String, String> params);
	List<HashMap<String, String>> eventDetailOption1List(HashMap<String, String> params);
	int eventDetailOption2ListCount(Map<String, String> searchMap);
	void insertEventFile(HashMap<String, String> params);
	void insertEventOption1(HashMap<String, String> params);
	void insertEventOption2(HashMap<String, String> params);
	void insertEventOption3(HashMap<String, String> params);
	void insertEventOption4(HashMap<String, String> params);
	void deleteEventOption2(HashMap<String, String> params);
	void deleteEventFile(HashMap<String, String> params);
	void deleteEventOption1(HashMap<String, String> params);
	void updateEventFile(HashMap<String, String> params);
	void deleteEventOption3(HashMap<String, String> params);
	void deleteEventOption4(HashMap<String, String> params);
	
	/**
	 * @Method Name  : eventUpdateProcess
	 * @Date         : 2013. 12 
	 * @Author       : 
	 * @변경이력      :
	 * @Method 설명      :	배너 수정 프로세스
	 * @param params
	*/
	void eventUpdateProcess(HashMap<String, String> params);
	/**
	 * @Method Name  : eventDelete
	 * @Date         : 2013. 12 
	 * @Author       : 
	 * @변경이력      :
	 * @Method 설명      :	배너 개별 삭제
	 * @param params
	*/
	void eventDelete(HashMap<String, String> params);
	
	List<HashMap<String, String>> getEventResultList(	Map<String, String> searchMap);
	int getEventResultListCount(Map<String, String> searchMap);
	
	List<HashMap<String, String>> getEventlectureList(HashMap<String, String> params);
	List<HashMap<String, String>> lectureList(HashMap<String, String> params);
	int lectureListCount(HashMap<String, String> params);
	void insertEventLecture(HashMap<String, String> params);
	void deleteEventLecture(HashMap<String, String> params);
}
