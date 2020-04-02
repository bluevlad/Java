package web.manage.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;

@Repository
public class lectureYearDAO extends EgovComAbstractDAO {

	/**
	 * 경영관리 - 년회원 패키지 매출 내역
	 * @param params
	 * @return
	 */
	public List<HashMap<String, String>> onPayList(Map<String, String> params) {
		return getSqlSession().selectList("lectureYear.onPayList", params);
	}

	public List<HashMap<String, String>> onOrderList(Map<String, String> params) {
		return getSqlSession().selectList("lectureYear.onOrderList", params);
	}
	
	public List<HashMap<String, String>> payUserListByLeccode(HashMap<String, String> params){
		return getSqlSession().selectList("lectureYear.payUserListByLeccode", params);
	}

	public int payUserListByLeccodeCount(HashMap<String, String> params){
		return getSqlSession().selectOne("lectureYear.payUserListByLeccodeCount", params);
	}
}