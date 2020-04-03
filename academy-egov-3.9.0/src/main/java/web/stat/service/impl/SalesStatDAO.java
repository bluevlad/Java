package web.stat.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;

@Repository
public class SalesStatDAO extends EgovComAbstractDAO {

	/**
	 * 	경영관리 - 강사 정보 리스트
	 * @param params
	 * @return
	 */
	public List<HashMap<String, String>> teacherList(Map<String, String> params) {
		return getSqlSession().selectList("salesStat.teacherList", params);
	}

	public List<HashMap<String, String>> teacherSubjectList(Map<String, String> params) {
		return getSqlSession().selectList("salesStat.teacherSubjectList", params);
	}

	public HashMap<String, String> teacherOne(HashMap<String, String> params) {
		return getSqlSession().selectOne("salesStat.teacherOne", params);
	}

	/**
	 * 	경영관리 - 매출 내역 리스트
	 * @param params
	 * @return
	 */
	public List<HashMap<String, String>> teacherSalesStatList(Map<String, String> params) {
		return getSqlSession().selectList("salesStat.teacherSalesStatList", params);
	}

	/**
	 * 경영관리 - 매출 내역 산출
	 * @param params
	 * @return
	 */
	public void makeOnSalesStat(Map<String, String> params) {
		getSqlSession().update("salesStat.makeOnSalesStat", params);
	}

	public void makeOffSalesStat(Map<String, String> params) {
		getSqlSession().update("salesStat.makeOffSalesStat", params);
	}

	/**
	 * 	경영관리 - 수강생 매출 내역 통계
	 * @param params
	 * @return
	 */
	public HashMap<String, String> userBuyStatList(Map<String, String> params) {
		return getSqlSession().selectOne("salesStat.userBuyStatList", params);
	}
	
	/**
	 * 	사이트관리 - 통합검색 결과 정보
	 * @param params
	 * @return
	 */
	public List<HashMap<String, String>> searchKeywordList(Map<String, String> params) {
		return getSqlSession().selectList("salesStat.searchKeywordList", params);
	}
	public int searchKeywordCount(HashMap<String, String> params){
		return getSqlSession().selectOne("salesStat.searchKeywordCount", params);
	}
	public int searchKeywordSum(HashMap<String, String> params){
		return getSqlSession().selectOne("salesStat.searchKeywordSum", params);
	}

}