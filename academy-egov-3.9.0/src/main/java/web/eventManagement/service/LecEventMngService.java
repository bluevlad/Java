package web.eventManagement.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @FileName   : LecEventMngService.java
 * @Project    : dev_willbesWebAdmin
 * @Date       : 2020.03 
 * @Author     : 
 * @변경이력    :
 * @프로그램 설명 : 배너 관리 service
 */
public interface LecEventMngService {
	

	List<HashMap<String, String>> getEventList(Map<String, String> searchMap);

	int getEventListCount(Map<String, String> searchMap);

	Object eventInsertProcess(HashMap<String, String> params);

	HashMap<String, Object> eventDetail(HashMap<String, String> params);
	
	void eventUpdateProcess(HashMap<String, String> params);

	void eventDelete(HashMap<String, String> params);
	
	List<HashMap<String, String>> getEventlectureList(HashMap<String, String> params);
	List<HashMap<String, String>> lectureList(HashMap<String, String> params);
	int lectureListCount(HashMap<String, String> params);
	void insertEventLecture(HashMap<String, String> params);
	void deleteEventLecture(HashMap<String, String> params);
	
	/**
	 * @Method Name  : reboundEvent
	 * @Date         : 2016. 11 
	 * @Author       : 
	 * @변경이력      :
	 * @Method 설명      :	리바운드 환승이벤트
	 * @param params
	*/
	List<HashMap<String, String>> getReboundEventList(Map<String, String> searchMap);
	int getReboundEventListCount(Map<String, String> searchMap);
	void updateReboundEventChk(HashMap<String, String> params);
	void deleteEventCoupon(HashMap<String, String> params);
	void insertEventCoupon(HashMap<String, String> params);
	int chkMyCouponByEventNo(Map<String, String> searchMap);
	void insertDelEventCoupon(HashMap<String, String> params);
	/**
	 * @Method Name  : reboundEvent
	 * @Date         : 2017. 12 
	 * @Author       : 
	 * @변경이력      :
	 * @Method 설명      :	서포터즈 이벤트
	 * @param params
	*/
	List<HashMap<String, String>> getSupportEventList(Map<String, String> searchMap);
	int getSupportEventListCount(Map<String, String> searchMap);
}
