package web.freeOrder.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import web.freeOrder.service.FreeOrderService;

@Service
public class FreeOrderServiceImpl  implements  FreeOrderService{
	
	/** log */
	private Logger log = Logger.getLogger(this.getClass());
	
	@Autowired
	private FreeOrderDAO freeOrderdao;
	
	// 수강신청 리스트
	@Override @SuppressWarnings("rawtypes")
	public List getMaMemberFreeOrderList(Map keyName){		
		return freeOrderdao.getMaMemberFreeOrderList(keyName);
	}
	
	// 수강신청 총 건수
	@Override @SuppressWarnings("rawtypes")
	public int getMaMemberListFreeOrderCount(Map keyName) {
		return freeOrderdao.getMaMemberListFreeOrderCount(keyName);
	}
	
	
	
	
	
	
	// 수강신청 등록 팝업 리스트
	@Override @SuppressWarnings("rawtypes")
	public List getCbLecMstBean(Map keyName){		
		return freeOrderdao.getCbLecMstBean(keyName);
	}
	
	// 수강신청 등록 팝업 리스트(이전)
	@Override @SuppressWarnings("rawtypes")
	public List getCbLecMstBean_2(Map keyName){		
		return freeOrderdao.getCbLecMstBean_2(keyName);
	}
		
	// 강의선택 팝업 카테고리 셀렉트박스 리스트
	@Override @SuppressWarnings("rawtypes")
	public List getCaCatCdList(Map keyName){		
		return freeOrderdao.getCaCatCdList(keyName);
	}
	
	// 강의선택 팝업 학습형태 셀렉트박스 리스트
	@Override @SuppressWarnings("rawtypes")
	public List getVwMenuMstTree_lec(Map keyName){		
		return freeOrderdao.getVwMenuMstTree_lec(keyName);
	}
	
	// 강의선택 팝업 과목 셀렉트박스 리스트
	@Override @SuppressWarnings("rawtypes")
	public List getCaSubjectCdList(Map keyName){		
		return freeOrderdao.getCaSubjectCdList(keyName);
	}
	
	// 강의선택 팝업  리스트
	@Override @SuppressWarnings("rawtypes")
	public List getCbLecMstFreeOrderList(Map keyName){		
		return freeOrderdao.getCbLecMstFreeOrderList(keyName);
	}
		
	// 강의선택 팝업  카운트
	@Override @SuppressWarnings("rawtypes")
	public int getCbLecMstListFreeOrderCount(Map keyName) {
		return freeOrderdao.getCbLecMstListFreeOrderCount(keyName);
	}
	
	
	
	
	
	
	
	
	// 수강신청 등록 팝업 등록처리
	@Override @SuppressWarnings("rawtypes")
	public List getMCount(Map keyName){		
		return freeOrderdao.getMCount(keyName);
	}
	
	@Override @SuppressWarnings("rawtypes")
	public List getMUser(Map keyName){		
		return freeOrderdao.getMUser(keyName);
	}
	
	@Override @SuppressWarnings("rawtypes")
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public int insertLecture1(Map keyName) {
		return freeOrderdao.insertLecture1(keyName);
	}
	
	@Override @SuppressWarnings("rawtypes")
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public int insertLecture2(Map keyName) {
		return freeOrderdao.insertLecture2(keyName);
	}
	
	@Override @SuppressWarnings("rawtypes")
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public int insertLecture3(Map keyName) {
		return freeOrderdao.insertLecture3(keyName);
	}
	
	@Override @SuppressWarnings("rawtypes")
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public int insertLecture4(Map keyName) {
		return freeOrderdao.insertLecture4(keyName);
	}
	
	@Override @SuppressWarnings("rawtypes")
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public int insertLecture5(Map keyName) {
		return freeOrderdao.insertLecture5(keyName);
	}
	
	@Override @SuppressWarnings("rawtypes")
	public List getLeccode(Map keyName){		
		return freeOrderdao.getLeccode(keyName);
	}
	
	
	
	
	
	
	
	
	
	
	
	// 수강변경 리스트
	@Override @SuppressWarnings("rawtypes")
	public List getChangeList(Map keyName){		
		return freeOrderdao.getChangeList(keyName);
	}
	
	// 수강변경 총 건수
	@Override @SuppressWarnings("rawtypes")
	public int getChangeListCount(Map keyName) {
		return freeOrderdao.getChangeListCount(keyName);
	}
	
	// 수강변경 상세  리스트
	@Override @SuppressWarnings("rawtypes")
	public List getChangeViewList(Map keyName){		
		return freeOrderdao.getChangeViewList(keyName);
	}
	
	// 수강변경 상세  리스트
	@Override @SuppressWarnings("rawtypes")
	public List getChangeViewList2(Map keyName){		
		return freeOrderdao.getChangeViewList2(keyName);
	}
	
	
	//수강변경 등록 팝업 수정처리
	@Override @SuppressWarnings("rawtypes")
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public int updateChangeLec(Map keyName) {
		return freeOrderdao.updateChangeLec(keyName);
	}
		
	//수강변경 등록 팝업 수정처리
	@Override @SuppressWarnings("rawtypes")
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public int insertChangeLec1(Map keyName) {
		return freeOrderdao.insertChangeLec1(keyName);
	}
	
	//수강변경 등록 팝업 수정처리
	@Override @SuppressWarnings("rawtypes")
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public int insertChangeLec2(Map keyName) {
		return freeOrderdao.insertChangeLec2(keyName);
	}
	
	//수강변경 등록 팝업 수정처리
	@Override @SuppressWarnings("rawtypes")
	@Transactional(readOnly=false, rollbackFor=Exception.class)
	public int insertChangeLec3(Map keyName) {
		return freeOrderdao.insertChangeLec3(keyName);
	}
	
	
	
	//전체상품주문관리 주문번호 상세
	@Override @SuppressWarnings("rawtypes")
	public List getTblApprovalsViewList(Map keyName){		
		return freeOrderdao.getTblApprovalsViewList(keyName);
	}
	
	//전체상품주문관리 주문번호 상세
	@Override @SuppressWarnings("rawtypes")
	public int getTblDeliversViewListCount(Map keyName) {
		return freeOrderdao.getTblDeliversViewListCount(keyName);
	}
	
	//전체상품주문관리 주문번호 상세
	@Override @SuppressWarnings("rawtypes")
	public List getTblDeliversViewList(Map keyName){		
		return freeOrderdao.getTblDeliversViewList(keyName);
	}

}
