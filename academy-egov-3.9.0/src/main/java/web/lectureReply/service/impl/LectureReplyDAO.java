package web.lectureReply.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;

@Repository
public class LectureReplyDAO extends EgovComAbstractDAO {


	/**
	 * @Method Name  : getCommonCodeList
	 * @Date         : 2020.03
	 * @Author       : rainend
	 * @변경이력      :
	 * @Method 설명      :	 리스트
	 * @param searchMap
	 * @return
	*/
	public List<HashMap<String, Object>> lectureReplyList(Map<String, Object> searchMap) {
		return getSqlSession().selectList("lectureReply.lectureReplyList", searchMap);
	}



	/**
	 * @Method Name  : getCommonCodeListCount
	 * @Date         : 2020.03
	 * @Author       : rainend
	 * @변경이력      :
	 * @Method 설명      :	 리스트 총 갯수
	 * @param searchMap
	 * @return
	*/
	public int lectureReplyListCount(Map<String, Object> searchMap) {
		return getSqlSession().selectOne("lectureReply.lectureReplyListCount", searchMap);
	}
	/**
	 * @Method Name  : lectureReplyDetail
	 * @Date         : 2020.03
	 * @Author       : rainend
	 * @변경이력      :
	 * @Method 설명      :	 상세
	 * @param params
	 * @return
	*/
	public HashMap<String, Object> lectureReplyDetail(HashMap<String, Object> params) {
		return getSqlSession().selectOne("lectureReply.lectureReplyDetail", params);
	}

	public List<HashMap<String, Object>> lectureReplyDetailList(Map<String, Object> searchMap) {
		return getSqlSession().selectList("lectureReply.lectureReplyDetailList", searchMap);
	}
	public int lectureReplyDetailListCount(Map<String, Object> params) {
		return getSqlSession().selectOne("lectureReply.lectureReplyDetailListCount", params);
	}
	/**
	 * @Method Name  : replyDelete
	 * @Date         : 2020.03
	 * @Author       : rainend
	 * @변경이력      :
	 * @Method 설명      :	운영자 관리 - 코드관리 - 코드 개별 삭제
	 * @param params
	*/
	public void replyDelete(HashMap<String, Object> params) {
		getSqlSession().delete("lectureReply.replyDelete", params);
	}

}
