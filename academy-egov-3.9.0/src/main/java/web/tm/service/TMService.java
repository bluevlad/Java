package web.tm.service;

import java.util.HashMap;
import java.util.List;

public interface TMService {

	List<HashMap<String, String>> tmAdminList(HashMap<String, String> params);

	String getTmMemberCount(HashMap<String, String> params);

	void insertTBTMUsersProcess(HashMap<String, String> params);

	List<HashMap<String, String>> tmMemberList(HashMap<String, String> params);

	String tmMemberListCount(HashMap<String, String> params);

	List<HashMap<String, String>> tmMemberListExcel(HashMap<String, String> params);

	List<HashMap<String, String>> getTBCCCartInfo(HashMap<String, String> params1);

	List<HashMap<String, String>> tmBoardList(HashMap<String, String> params);

	String tmBoardListCount(HashMap<String, String> params);

	List<HashMap<String, String>> getVOCCODEList(HashMap<String, String> params);

	List<HashMap<String, String>> getDUTYCODEList(HashMap<String, String> params);

	List<HashMap<String, String>> tmOrderList(HashMap<String, String> params);

	HashMap<String, String> tmOrderListCount(HashMap<String, String> params);

	List<HashMap<String, String>> tmRefundList(HashMap<String, String> params);

	String tmRefundListCount(HashMap<String, String> params);

	List<HashMap<String, String>> tmIsTmAdmin(HashMap<String, String> params);

	HashMap<String, String> tmCancelPayment(HashMap<String, String> params);

	void tmBoardDelete(HashMap<String, String> subparams);

	void tmBoardUpdate(HashMap<String, String> subparams);

	List<HashMap<String, String>> tmBoardListExcel(HashMap<String, String> params);

	List<HashMap<String, String>> tmOrderListExcel(HashMap<String, String> params);

	List<HashMap<String, String>> tmRefundListExcel(HashMap<String, String> params);

	/* 프리패스 TM관리 대상주문건수 가져오기 */
	int getTmPassTmCount(HashMap<String, String> params);
	/* 프리패스 TM관리 배정된 주문건(수강생) 등록하기 */
	void insertTBTMPassProcess(HashMap<String, String> subparams);
	/* 프리패스 TM관리 배정된 주문 내역보기 */
	List<HashMap<String, String>> tmPassList(HashMap<String, String> params);
	int tmPassListCount(HashMap<String, String> params);
	/* 프리패스 TM관리 프리패스 강의별 배정내역 보기 */
	List<HashMap<String, String>> tmPassLecList(HashMap<String, String> params);
	int tmPassLecListCount(HashMap<String, String> params);
	/* 프리패스 TM관리 프리패스 선택강의에 해당하는 수강생명단 */
	List<HashMap<String, String>> tmPassLecStdList(HashMap<String, String> params);
	int tmPassLecStdListCount(HashMap<String, String> params);
	/* 프리패스 TM관리 수강생별 TM담당자 지정하기 */
	void insertTMPassLecUserProcess(HashMap<String, String> subparams);
	/* 프리패스 TM관리 배정된 수강생의 신규 주문정보 */
	List<HashMap<String, String>> tmPassOrderList(HashMap<String, String> params);
	HashMap<String, String> tmPassOrderListCount(HashMap<String, String> params);
	List<HashMap<String, String>> tmPassBoardList(HashMap<String, String> params);
	int tmPassBoardListCount(HashMap<String, String> params);
	void tmPassBoardInsert(HashMap<String, String> subparams);
	void tmPassBoardUpdate(HashMap<String, String> subparams);
	void tmPassBoardDelete(HashMap<String, String> subparams);

}
