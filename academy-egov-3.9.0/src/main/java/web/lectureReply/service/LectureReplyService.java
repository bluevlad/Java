package web.lectureReply.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @FileName   : LectureReplyService.java
 * @Project    : dev_willbesWebAdmin
 * @Date       : 2020.03 
 * @Author     : rainend
 * @변경이력    :
 * @프로그램 설명 : 수강후기 게시판 service
 */
public interface LectureReplyService {
	
	/**
	 * @Method Name  : lectureReplyList
	 * @Date         : 2020.03 
	 * @Author       : rainend
	 * @변경이력      :
	 * @Method 설명      : 	 리스트
	 * @param searchMap
	 * @return
	*/
	List<HashMap<String, Object>> lectureReplyList(	Map<String, Object> searchMap);
	/**
	 * @Method Name  : lectureReplyListCount
	 * @Date         : 2020.03 
	 * @Author       : rainend
	 * @변경이력      :
	 * @Method 설명      :	리스트 총 갯수
	 * @param searchMap
	 * @return
	*/
	int lectureReplyListCount(Map<String, Object> searchMap);
	
	/**
	 * @Method Name  : lectureReplyDetail
	 * @Date         : 2020.03 
	 * @Author       : rainend
	 * @변경이력      :
	 * @Method 설명      :	 상세 페이지 
	 * @param params
	 * @return
	*/
	HashMap<String, Object> lectureReplyDetail(HashMap<String, Object> params);
	
	
	List<HashMap<String, Object>> lectureReplyDetailList(HashMap<String, Object> params);
	
	int lectureReplyDetailListCount(HashMap<String, Object> params);
	
	/**
	 * @Method Name  : replyDelete
	 * @Date         : 2020.03 
	 * @Author       : rainend
	 * @변경이력      :
	 * @Method 설명      :	 삭제
	 * @param params
	*/
	void replyDelete(HashMap<String, Object> params);
	
	
	
	
}
