package web.tm.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;

@Repository
public class TMDAO extends EgovComAbstractDAO {

	// TM관리자 목록 조회  2013.12.30
	public List<HashMap<String, String>> tmAdminList(HashMap<String, String> params) {
		return getSqlSession().selectList("tm.tmAdminList", params);
	}

	public String getTmMemberACount(HashMap<String, String> params) {
		return getSqlSession().selectOne("tm.getTmMemberACount", params);
	}

	public String getTmMemberBCount(HashMap<String, String> params) {
		return getSqlSession().selectOne("tm.getTmMemberBCount", params);
	}

	public String getTmMemberCCount(HashMap<String, String> params) {
		return getSqlSession().selectOne("tm.getTmMemberCCount", params);
	}

	public String getTmMemberDCount(HashMap<String, String> params) {
		return getSqlSession().selectOne("tm.getTmMemberDCount", params);
	}

	public void insertTBTMUsersProcessA(HashMap<String, String> params) {
		getSqlSession().insert("tm.insertTBTMUsersProcessA", params);
	}

	public void insertTBTMUsersProcessB(HashMap<String, String> params) {
		getSqlSession().insert("tm.insertTBTMUsersProcessB", params);
	}

	public void insertTBTMUsersProcessC(HashMap<String, String> params) {
		getSqlSession().insert("tm.insertTBTMUsersProcessC", params);
	}

	public void insertTBTMUsersProcessD(HashMap<String, String> params) {
		getSqlSession().insert("tm.insertTBTMUsersProcessD", params);
	}

	public List<HashMap<String, String>> tmMemberList(HashMap<String, String> params) {
		return getSqlSession().selectList("tm.tmMemberList", params);
	}

	public String tmMemberListCount(HashMap<String, String> params) {
		return getSqlSession().selectOne("tm.tmMemberListCount", params);
	}

	public List<HashMap<String, String>> tmMemberListExcel(HashMap<String, String> params) {
		return getSqlSession().selectList("tm.tmMemberListExcel", params);
	}

	public List<HashMap<String, String>> getTBCCCartInfo(HashMap<String, String> params) {
		return getSqlSession().selectList("tm.getTBCCCartInfo", params);
	}

	public List<HashMap<String, String>> tmBoardList(HashMap<String, String> params) {
		return getSqlSession().selectList("tm.tmBoardList", params);
	}

	public String tmBoardListCount(HashMap<String, String> params) {
		return getSqlSession().selectOne("tm.tmBoardListCount", params);
	}

	public List<HashMap<String, String>> getVOCCODEList(HashMap<String, String> params) {
		return getSqlSession().selectList("tm.getVOCCODEList", params);
	}

	public List<HashMap<String, String>> getDUTYCODEList(HashMap<String, String> params) {
		return getSqlSession().selectList("tm.getDUTYCODEList", params);
	}

	public List<HashMap<String, String>> tmOrderList(HashMap<String, String> params) {
		return getSqlSession().selectList("tm.tmOrderList", params);
	}

	public HashMap<String, String> tmOrderListCount(HashMap<String, String> params) {
		return getSqlSession().selectOne("tm.tmOrderListCount", params);
	}

	public List<HashMap<String, String>> tmRefundList(HashMap<String, String> params) {
		return getSqlSession().selectList("tm.tmRefundList", params);
	}

	public String tmRefundListCount(HashMap<String, String> params) {
		return getSqlSession().selectOne("tm.tmRefundListCount", params);
	}

	public List<HashMap<String, String>> tmIsTmAdmin(HashMap<String, String> params) {
		return getSqlSession().selectList("tm.tmIsTmAdmin", params);
	}

	public HashMap<String, String> tmCancelPayment(HashMap<String, String> params) {
		return getSqlSession().selectOne("tm.tmCancelPayment", params);
	}

	public void tmBoardDelete(HashMap<String, String> params) {
		getSqlSession().delete("tm.tmBoardDelete", params);
	}

	public void tmBoardUpdate(HashMap<String, String> params) {
		getSqlSession().update("tm.tmBoardUpdate", params);
	}

	public List<HashMap<String, String>> tmBoardListExcel(HashMap<String, String> params) {
		return getSqlSession().selectList("tm.tmBoardListExcel", params);
	}

	public List<HashMap<String, String>> tmOrderListExcel(HashMap<String, String> params) {
		return getSqlSession().selectList("tm.tmOrderListExcel", params);
	}

	public List<HashMap<String, String>> tmRefundListExcel(HashMap<String, String> params) {
		return getSqlSession().selectList("tm.tmRefundListExcel", params);
	}

	/* 프리패스 TM관리 대상주문건수 가져오기 */
	public int getTmPassTmCount(HashMap<String, String> params) {
		return getSqlSession().selectOne("tm.getTmPassTmCount", params);
	}
	/* 프리패스 TM관리 배정된 주문건(수강생) 등록하기 */
	public void insertTBTMPassProcess(HashMap<String, String> params) {
		getSqlSession().delete("tm.insertTBTMPassProcess", params);
	}
	/* 프리패스 TM관리 배정된 주문 내역보기 */
	public List<HashMap<String, String>> tmPassList(HashMap<String, String> params) {
		return getSqlSession().selectList("tm.tmPassList", params);
	}
	public int tmPassListCount(HashMap<String, String> params) {
		return getSqlSession().selectOne("tm.tmPassListCount", params);
	}
	/* 프리패스 TM관리 프리패스 강의별 배정내역 보기 */
	public List<HashMap<String, String>> tmPassLecList(HashMap<String, String> params) {
		return getSqlSession().selectList("tm.tmPassLecList", params);
	}
	public int tmPassLecListCount(HashMap<String, String> params) {
		return getSqlSession().selectOne("tm.tmPassLecListCount", params);
	}
	/* 프리패스 TM관리 프리패스 선택강의에 해당하는 수강생명단 */
	public List<HashMap<String, String>> tmPassLecStdList(HashMap<String, String> params) {
		return getSqlSession().selectList("tm.tmPassLecStdList", params);
	}
	public int tmPassLecStdListCount(HashMap<String, String> params) {
		return getSqlSession().selectOne("tm.tmPassLecStdListCount", params);
	}
	/* 프리패스 TM관리 수강생별 TM담당자 지정하기 */
	public void insertTMPassLecUserProcess(HashMap<String, String> params) {
		getSqlSession().delete("tm.insertTMPassLecUserProcess", params);
	}
	/* 프리패스 TM관리 배정된 수강생의 신규 주문정보 */
	public List<HashMap<String, String>> tmPassOrderList(HashMap<String, String> params) {
		return getSqlSession().selectList("tm.tmPassOrderList", params);
	}
	public HashMap<String, String> tmPassOrderListCount(HashMap<String, String> params) {
		return getSqlSession().selectOne("tm.tmPassOrderListCount", params);
	}
	public List<HashMap<String, String>> tmPassBoardList(HashMap<String, String> params) {
		return getSqlSession().selectList("tm.tmPassBoardList", params);
	}
	public int tmPassBoardListCount(HashMap<String, String> params) {
		return getSqlSession().selectOne("tm.tmPassBoardListCount", params);
	}
	public void tmPassBoardInsert(HashMap<String, String> params) {
		getSqlSession().delete("tm.tmPassBoardInsert", params);
	}
	public void tmPassBoardUpdate(HashMap<String, String> params) {
		getSqlSession().update("tm.tmPassBoardUpdate", params);
	}
	public void tmPassBoardDelete(HashMap<String, String> params) {
		getSqlSession().update("tm.tmPassBoardDelete", params);
	}

}
