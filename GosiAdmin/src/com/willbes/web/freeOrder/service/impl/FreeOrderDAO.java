package com.willbes.web.freeOrder.service.impl;

import java.util.Map;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import com.willbes.cmm.service.impl.CmmAbstractMapper;

@Repository
public class FreeOrderDAO extends CmmAbstractMapper {

	/** log */
    protected static final Log Logger = LogFactory.getLog(FreeOrderDAO.class);

    // 수강신청 리스트
    @SuppressWarnings({ "rawtypes" })
	public List getMaMemberFreeOrderList(Map keyName){
		return getSqlSession().selectList("freeOrder.getMaMemberFreeOrderList", keyName);
	}

    // 수강신청 총 건수
    @SuppressWarnings({ "rawtypes" })
	public int getMaMemberListFreeOrderCount(Map keyName){
		return getSqlSession().selectOne("freeOrder.getMaMemberListFreeOrderCount", keyName);
	}






    // 수강신청 등록 팝업 리스트
	@SuppressWarnings({ "rawtypes" })
	public List getCbLecMstBean(Map keyName){
		return getSqlSession().selectList("freeOrder.getCbLecMstBean", keyName);
	}

	// 수강신청 등록 팝업 리스트(이전)
	@SuppressWarnings({ "rawtypes" })
	public List getCbLecMstBean_2(Map keyName){
		return getSqlSession().selectList("freeOrder.getCbLecMstBean_2", keyName);
	}

	//강의선택 팝업 카테고리 셀렉트박스 리스트
	@SuppressWarnings({ "rawtypes" })
	public List getCaCatCdList(Map keyName){
		return getSqlSession().selectList("freeOrder.getCaCatCdList", keyName);
	}

	//강의선택 팝업 학습형태 셀렉트박스 리스트
	@SuppressWarnings({ "rawtypes" })
	public List getVwMenuMstTree_lec(Map keyName){
		return getSqlSession().selectList("freeOrder.getVwMenuMstTree_lec", keyName);
	}

	//강의선택 팝업 과목 셀렉트박스 리스트
	@SuppressWarnings({ "rawtypes" })
	public List getCaSubjectCdList(Map keyName){
		return getSqlSession().selectList("freeOrder.getCaSubjectCdList", keyName);
	}

	//강의선택 팝업  리스트
	@SuppressWarnings({ "rawtypes" })
	public List getCbLecMstFreeOrderList(Map keyName){
		return getSqlSession().selectList("freeOrder.getCbLecMstFreeOrderList", keyName);
	}

	// 강의선택 팝업  카운트
	@SuppressWarnings({ "rawtypes" })
	public int getCbLecMstListFreeOrderCount(Map keyName){
		return getSqlSession().selectOne("freeOrder.getCbLecMstListFreeOrderCount", keyName);
	}





	// 수강신청 등록 팝업 등록처리
	@SuppressWarnings({ "rawtypes" })
	public List getMCount(Map keyName){
		return getSqlSession().selectList("freeOrder.getMCount", keyName);
	}

	@SuppressWarnings({ "rawtypes" })
	public List getMUser(Map keyName){
		return getSqlSession().selectList("freeOrder.getMUser", keyName);
	}

	@SuppressWarnings({ "rawtypes" })
	public int insertLecture1(Map keyName){
		return getSqlSession().insert("freeOrder.insertLecture1", keyName);
	}

	@SuppressWarnings({ "rawtypes" })
	public int insertLecture2(Map keyName){
		return getSqlSession().insert("freeOrder.insertLecture2", keyName);
	}

	@SuppressWarnings({ "rawtypes" })
	public int insertLecture3(Map keyName){
		return getSqlSession().insert("freeOrder.insertLecture3", keyName);
	}

	@SuppressWarnings({ "rawtypes" })
	public int insertLecture4(Map keyName){
		return getSqlSession().insert("freeOrder.insertLecture4", keyName);
	}
	@SuppressWarnings({ "rawtypes" })
	public int insertLecture5(Map keyName){
		return getSqlSession().insert("freeOrder.insertLecture5", keyName);
	}

	@SuppressWarnings({ "rawtypes" })
	public List getLeccode(Map keyName){
		return getSqlSession().selectList("freeOrder.getLeccode", keyName);
	}













	// 수강변경 리스트
    @SuppressWarnings({ "rawtypes" })
	public List getChangeList(Map keyName){
		return getSqlSession().selectList("freeOrder.getChangeList", keyName);
	}

    // 수강변경 총 건수
    @SuppressWarnings({ "rawtypes" })
	public int getChangeListCount(Map keyName){
		return getSqlSession().selectOne("freeOrder.getChangeListCount", keyName);
	}

    // 수강변경 상세  리스트
    @SuppressWarnings({ "rawtypes" })
	public List getChangeViewList(Map keyName){
		return getSqlSession().selectList("freeOrder.getChangeViewList", keyName);
	}

 // 수강변경 상세  리스트
    @SuppressWarnings({ "rawtypes" })
	public List getChangeViewList2(Map keyName){
		return getSqlSession().selectList("freeOrder.getChangeViewList2", keyName);
	}

    //수강변경 등록 팝업 수정처리
    @SuppressWarnings({ "rawtypes" })
	public int updateChangeLec(Map keyName){
		return getSqlSession().update("freeOrder.updateChangeLec", keyName);
	}

    //수강변경 등록 팝업 수정처리
    @SuppressWarnings({ "rawtypes" })
	public int insertChangeLec1(Map keyName){
		return getSqlSession().insert("freeOrder.insertChangeLec1", keyName);
	}

    //수강변경 등록 팝업 수정처리
	@SuppressWarnings({ "rawtypes" })
	public int insertChangeLec2(Map keyName){
		return getSqlSession().insert("freeOrder.insertChangeLec2", keyName);
	}

	//수강변경 등록 팝업 수정처리
	@SuppressWarnings({ "rawtypes" })
	public int insertChangeLec3(Map keyName){
		return getSqlSession().insert("freeOrder.insertChangeLec3", keyName);
	}


	//전체상품주문관리 주문번호 상세
    @SuppressWarnings({ "rawtypes" })
	public List getTblApprovalsViewList(Map keyName){
		return getSqlSession().selectList("freeOrder.getTblApprovalsViewList", keyName);
	}

    //전체상품주문관리 주문번호 상세
    @SuppressWarnings({ "rawtypes" })
	public int getTblDeliversViewListCount(Map keyName){
		return getSqlSession().selectOne("freeOrder.getTblDeliversViewListCount", keyName);
	}

    //전체상품주문관리 주문번호 상세
    @SuppressWarnings({ "rawtypes" })
	public List getTblDeliversViewList(Map keyName){
		return getSqlSession().selectList("freeOrder.getTblDeliversViewList", keyName);
	}

}
