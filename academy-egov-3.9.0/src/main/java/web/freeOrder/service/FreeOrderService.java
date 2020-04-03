package web.freeOrder.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface FreeOrderService {
	
	// 수강신청 리스트
	@SuppressWarnings({ "rawtypes" })
	List getMaMemberFreeOrderList(Map keyName);
	
	// 수강신청 총 건수
	@SuppressWarnings({ "rawtypes" })
	int getMaMemberListFreeOrderCount(Map keyName);
	
	
	
	
	
	// 수강신청 등록 팝업 리스트
	@SuppressWarnings({ "rawtypes" })
	List getCbLecMstBean(Map keyName);
	
	// 수강신청 등록 팝업 리스트(이전)
	@SuppressWarnings({ "rawtypes" })
	List getCbLecMstBean_2(Map keyName);
	
	// 강의선택 팝업 카테고리 셀렉트박스 리스트
	@SuppressWarnings({ "rawtypes" })
	List getCaCatCdList(Map keyName);
	
	// 강의선택 팝업 학습형태 셀렉트박스 리스트
	@SuppressWarnings({ "rawtypes" })
	List getVwMenuMstTree_lec(Map keyName);
	
	// 강의선택 팝업 과목 셀렉트박스 리스트
	@SuppressWarnings({ "rawtypes" })
	List getCaSubjectCdList(Map keyName);
	
	// 강의선택 팝업  리스트
	@SuppressWarnings({ "rawtypes" })
	List getCbLecMstFreeOrderList(Map keyName);
		
	// 강의선택 팝업  카운트
	@SuppressWarnings({ "rawtypes" })
	int getCbLecMstListFreeOrderCount(Map keyName);
	
	
	
	
	
	
	
	
	// 수강신청 등록 팝업 등록처리
	@SuppressWarnings({ "rawtypes" })
	List getMCount(Map keyName);
	
	@SuppressWarnings({ "rawtypes" })
	List getMUser(Map keyName);
	
	@SuppressWarnings({ "rawtypes" })
	int insertLecture1(Map keyName);
	
	@SuppressWarnings({ "rawtypes" })
	int insertLecture2(Map keyName);
	
	@SuppressWarnings({ "rawtypes" })
	int insertLecture3(Map keyName);
	
	@SuppressWarnings({ "rawtypes" })
	int insertLecture4(Map keyName);
	
	@SuppressWarnings({ "rawtypes" })
	int insertLecture5(Map keyName);
	
	@SuppressWarnings({ "rawtypes" })
	List getLeccode(Map keyName);
	
	
	
	
	
	
	// 수강변경 리스트
	@SuppressWarnings({ "rawtypes" })
	List getChangeList(Map keyName);
	
	// 수강변경 총 건수
	@SuppressWarnings({ "rawtypes" })
	int getChangeListCount(Map keyName);
	
	// 수강변경 상세  리스트
	@SuppressWarnings({ "rawtypes" })
	List getChangeViewList(Map keyName);
	
	// 수강변경 상세  리스트
	@SuppressWarnings({ "rawtypes" })
	List getChangeViewList2(Map keyName);
	
	
	//수강변경 등록 팝업 등록처리
	@SuppressWarnings({ "rawtypes" })
	int updateChangeLec(Map keyName);
	
	//수강변경 등록 팝업 등록처리
	@SuppressWarnings({ "rawtypes" })
	int insertChangeLec1(Map keyName);
	
	//수강변경 등록 팝업 등록처리
	@SuppressWarnings({ "rawtypes" })
	int insertChangeLec2(Map keyName);
	
	//수강변경 등록 팝업 등록처리
	@SuppressWarnings({ "rawtypes" })
	int insertChangeLec3(Map keyName);
	
	
	//전체상품주문관리 주문번호 상세
	@SuppressWarnings({ "rawtypes" })
	List getTblApprovalsViewList(Map keyName);
	
	//전체상품주문관리 주문번호 상세
	@SuppressWarnings({ "rawtypes" })
	int getTblDeliversViewListCount(Map keyName);
	
	//전체상품주문관리 주문번호 상세
	@SuppressWarnings({ "rawtypes" })
	List getTblDeliversViewList(Map keyName);

}
