package web.eventManagement.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;

@Repository
public class EventManagementDAO extends EgovComAbstractDAO {

	/**
	 * @Method Name  : getMemberList
	 * @Date         : 2020.03
	 * @Author       : rainend
	 * @변경이력      :
	 * @Method 설명      :	운영자 관리 - 운영자조회 - 운영자 리스트
	 * @param searchMap
	 * @return
	*/
	public List<HashMap<String, String>> getEventList(Map<String, String> searchMap) {
		return getSqlSession().selectList("eventManagement.getEventList", searchMap);
	}
	/**
	 * @Method Name  : getMemberListCount
	 * @Date         : 2020.03
	 * @Author       : rainend
	 * @변경이력      :
	 * @Method 설명      :	운영자 관리 - 운영자조회  리스트 총 갯수
	 * @param searchMap
	 * @return
	*/
	public int getEventListCount(Map<String, String> searchMap) {
		return getSqlSession().selectOne("eventManagement.getEventListCount", searchMap);
	}

	/**
	 * @Method Name  : eventInsertProcess
	 * @Date         : 2020.03
	 * @Author       :
	 * @변경이력      :
	 * @Method 설명      : 배너 등록
	 * @param params
	*/
	public Object eventInsertProcess(HashMap<String, String> params) {
		return getSqlSession().insert("eventManagement.eventInsertProcess", params);
	}

	/**
	 * @Method Name  : eventDetail
	 * @Date         : 2020.03
	 * @Author       :
	 * @변경이력      :
	 * @Method 설명      :	배너 조회 상세
	 * @param params
	 * @return
	*/
	public HashMap<String, Object> eventDetail(HashMap<String, String> params) {
		return getSqlSession().selectOne("eventManagement.eventDetail", params);
	}

	public List<HashMap<String, String>> eventDetailOption1List(Map<String, String> searchMap) {
		return getSqlSession().selectList("eventManagement.eventDetailOption1List", searchMap);
	}
	public List<HashMap<String, String>> eventDetailOption2ListAll(Map<String, String> searchMap) {
		return getSqlSession().selectList("eventManagement.eventDetailOption2ListAll", searchMap);
	}
	public List<HashMap<String, String>> eventDetailOption2List(Map<String, String> searchMap) {
		return getSqlSession().selectList("eventManagement.eventDetailOption2List", searchMap);
	}
	public int eventDetailOption2ListCount(Map<String, String> searchMap) {
		return getSqlSession().selectOne("eventManagement.eventDetailOption2ListCount", searchMap);
	}

	public List<HashMap<String, String>> eventFileList(Map<String, String> searchMap) {
		return getSqlSession().selectList("eventManagement.eventFileList", searchMap);
	}

	public void insertEventFile(HashMap<String, String> params) {
		getSqlSession().insert("eventManagement.insertEventFile", params);
	}

	public void insertEventOption1(HashMap<String, String> params) {
		getSqlSession().insert("eventManagement.insertEventOption1", params);
	}
	public void insertEventOption2(HashMap<String, String> params) {
		getSqlSession().insert("eventManagement.insertEventOption2", params);
	}

	public void deleteEventOption1(HashMap<String, String> params) {
		getSqlSession().delete("eventManagement.deleteEventOption1", params);
	}

	public void deleteEventOption2(HashMap<String, String> params) {
		getSqlSession().delete("eventManagement.deleteEventOption2", params);
	}

	public void deleteEventFile(HashMap<String, String> params) {
		getSqlSession().delete("eventManagement.deleteEventFile", params);
	}
	/**
	 * @Method Name  : eventUpdateProcess
	 * @Date         : 2020.03
	 * @Author       :
	 * @변경이력      :
	 * @Method 설명      :	배너 조회 수정
	 * @param params
	*/
	public void eventUpdateProcess(HashMap<String, String> params) {
		getSqlSession().update("eventManagement.eventUpdateProcess", params);
	}
	/**
	 * @Method Name  : memberDelete
	 * @Date         : 2020.03
	 * @Author       : rainend
	 * @변경이력      :
	 * @Method 설명      :	운영자 관리 - 운영자 조회 개별 삭제
	 * @param params
	*/
	public void eventDelete(HashMap<String, String> params) {
		getSqlSession().delete("eventManagement.eventDelete", params);
	}

	public List<HashMap<String, String>> getEventResultList(Map<String, String> searchMap) {
		return getSqlSession().selectList("eventManagement.getEventResultList", searchMap);
	}

	public int getEventResultListCount(Map<String, String> searchMap) {
		return getSqlSession().selectOne("eventManagement.getEventResultListCount", searchMap);
	}
	
	public List<HashMap<String, String>> getEventlectureList(Map<String, String> params) {
		return getSqlSession().selectList("eventManagement.getEventlectureList", params);
	}

	public List<HashMap<String, String>> lectureList(HashMap<String, String> params){
		return getSqlSession().selectList("eventManagement.lectureList", params);
	}
	
	public int lectureListCount(HashMap<String, String> params){
		return getSqlSession().selectOne("eventManagement.lectureListCount", params);
	}
	public void insertEventLecture(HashMap<String, String> params) {
		getSqlSession().insert("eventManagement.insertEventLecture", params);
	}
	public void deleteEventLecture(HashMap<String, String> params) {
		getSqlSession().delete("eventManagement.deleteEventLecture", params);
	}
	public void insertEventOption3(HashMap<String, String> params) {
		getSqlSession().insert("eventManagement.insertEventOption3", params);
	}
	public void deleteEventOption3(HashMap<String, String> params) {
		getSqlSession().delete("eventManagement.deleteEventOption3", params);
	}
}
