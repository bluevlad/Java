package com.willbes.web.manage.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.willbes.cmm.service.impl.CmmAbstractMapper;

@Repository
public class CategorySaleDAO extends CmmAbstractMapper {

	/**
	 * 경영관리 - 온라인 직종별 매출 리스트
	 * @param params
	 * @return
	 */
	public List<HashMap<String, Object>> onCategorySalesList(Map<String, Object> params) {
		return getSqlSession().selectList("manage.onCategorySalesList", params);
	}

	/**
	 * 경영관리 - 오프라인 직종별 매출 리스트
	 * @param params
	 * @return
	 */
	public List<HashMap<String, Object>> offCategorySalesList(Map<String, Object> params) {
		return getSqlSession().selectList("manage.offCategorySalesList", params);
	}

	/**
	 * 경영관리 - 온라인 직종별 매출 합계
	 * @param params
	 * @return
	 */
	public List<HashMap<String, Object>> onCategorySalesListSum(Map<String, Object> params) {
		return getSqlSession().selectList("manage.onCategorySalesListSum", params);
	}

	/**
	 * 경영관리 - 오프라인 직종별 매출 합계
	 * @param params
	 * @return
	 */
	public List<HashMap<String, Object>> offCategorySalesListSum(Map<String, Object> params) {
		return getSqlSession().selectList("manage.offCategorySalesListSum", params);
	}

	/**
	 * 경영관리 - 강사별 지급률
	 * @param params
	 * @return
	 */
	public List<HashMap<String, Object>> onTeacherPayList(Map<String, Object> params) {
		return getSqlSession().selectList("manage.onTeacherPayList", params);
	}

	/**
	 * 경영관리 - 강사별 지급률
	 * @param params
	 * @return
	 */
	public List<HashMap<String, Object>> offTeacherPayList(Map<String, Object> params) {
		return getSqlSession().selectList("manage.offTeacherPayList", params);
	}

	/**
	 * 경영관리 - 강사별 지급률 상세 (온라인)
	 * @param params
	 * @return
	 */
	public List<HashMap<String, Object>> onTeacherPayDetail(Map<String, Object> params) {
		return getSqlSession().selectList("manage.onTeacherPayDetail", params);
	}

	/**
	 * 경영관리 - 강사별 지급률 상세(학원실강)
	 * @param params
	 * @return
	 */
	public List<HashMap<String, Object>> offTeacherPayDetail(Map<String, Object> params) {
		return getSqlSession().selectList("manage.offTeacherPayDetail", params);
	}

	/**
	 * 경영관리 - 강사별 지급률 환불 상세
	 * @param params
	 * @return
	 */
	public List<HashMap<String, Object>> onTeacherPayReturnDetail(Map<String, Object> params) {
		return getSqlSession().selectList("manage.onTeacherPayReturnDetail", params);
	}

	/**
	 * 경영관리 - 강사별 지급률 환불 상세
	 * @param params
	 * @return
	 */
	public List<HashMap<String, Object>> offTeacherPayReturnDetail(Map<String, Object> params) {
		return getSqlSession().selectList("manage.offTeacherPayReturnDetail", params);
	}

	/**
	 * 경영관리 - 선수금 관리
	 * @param params
	 * @return
	 */
	public List<HashMap<String, Object>> onAdvanceReceivedList(Map<String, Object> params) {
		return getSqlSession().selectList("manage.onAdvanceReceivedList", params);
	}

	public List<HashMap<String, Object>> offAdvanceReceivedList(Map<String, Object> params) {
		return getSqlSession().selectList("manage.offAdvanceReceivedList", params);
	}

	public List<HashMap<String, Object>> offAdvanceReceivedList_D(Map<String, Object> params) {
		return getSqlSession().selectList("manage.offAdvanceReceivedList_D", params);
	}

	public List<HashMap<String, Object>> offAdvanceReceivedList_P(Map<String, Object> params) {
		return getSqlSession().selectList("manage.offAdvanceReceivedList_P", params);
	}

	/**
	 * 경영관리 - 학원 단과매출
	 * @param params
	 * @return
	 */
	public List<HashMap<String, Object>> offLecSalesList(Map<String, Object> params) {
		return getSqlSession().selectList("manage.offLecSalesList", params);
	}
	public List<HashMap<String, Object>> offLecSalesJongList(Map<String, Object> params) {
		return getSqlSession().selectList("manage.offLecSalesJongList", params);
	}

	// 단과 매출 내역 엑셀 리스트
	@SuppressWarnings({ "rawtypes" })
    public List<HashMap<String, String>> offLecSalesExcelList(HashMap<String, String> params){
		return getSqlSession().selectList("manage.offLecSalesExcelList", params);
	}

	/**
	 * 	경영관리 - 강사료 관리new (학원실강)
	 * @param params
	 * @return
	 */
	public List<HashMap<String, Object>> offTeacherAdjustList(Map<String, Object> params) {
		return getSqlSession().selectList("manage.offTeacherAdjustList", params);
	}

	public String offTeacherAdjustListCount(Map<String, Object> params) {
		return getSqlSession().selectOne("manage.offTeacherAdjustListCount", params);
	}

	/**
	 * 경영관리 - 강사별 정산 상세new (학원실강)
	 * @param params
	 * @return
	 */
	public List<HashMap<String, Object>> offTeacherAdjustDetail(Map<String, Object> params) {
		if(params.get("searchStartDate").equals(params.get("searchEndDate"))) {
			getSqlSession().update("manage.updateOffMylectureRealprice",params);
		}
		return getSqlSession().selectList("manage.offTeacherAdjustDetail", params);
	}

	public List<HashMap<String, Object>> offAdjustOrderList(Map<String, Object> params) {
		return getSqlSession().selectList("manage.offAdjustOrderList", params);
	}

	public List<HashMap<String, Object>> offAdjustDeductList(Map<String, Object> params) {
		return getSqlSession().selectList("manage.offAdjustDeductList", params);
	}

	public List<HashMap<String, Object>> offAdjustDeductEtcList(Map<String, Object> params) {
		return getSqlSession().selectList("manage.offAdjustDeductEtcList", params);
	}

	/**
	 * 경영관리 - 강사별 정산 상세정보 저장 new (학원실강)
	 * @param params
	 * @return
	 */
	public void offTeacherAdjustInsert(Map<String, Object> params) {
		getSqlSession().insert("manage.offTeacherAdjustInsert", params);
	}

	public void offTeacherAdjustUpdate(Map<String, Object> params) {
		getSqlSession().update("manage.offTeacherAdjustUpdate", params);
	}

	/**
	 * 경영관리 - 강사별 정산 상세정보의 추가사항 저장new (학원실강)
	 * @param params
	 * @return
	 */
	public void offAdjustDeductInsert(Map<String, Object> params) {

		if("0".equals(params.get("PSA_NO"))) {
			getSqlSession().insert("manage.offAdjustDeductInsert", params);
		} else {
			getSqlSession().update("manage.offAdjustDeductUpdate", params);
		}
	}
	
	/**
	 * 경영관리 - 과목별 매출 리스트
	 * @param params
	 * @return
	 */
	public List<HashMap<String, Object>> onSalesList(Map<String, Object> params) {
		return getSqlSession().selectList("manage.onSalesList", params);
	}
	
	/**
	 * 경영관리 - 과목별 매출 리스트
	 * @param params
	 * @return
	 */
	public List<HashMap<String, Object>> onSubjectSalesList(Map<String, Object> params) {
		return getSqlSession().selectList("manage.onSubjectSalesList", params);
	}
	
	/**
	 * 경영관리 - 유형별 매출 리스트
	 * @param params
	 * @return
	 */
	public List<HashMap<String, Object>> onLearningSalesList(Map<String, Object> params) {
		return getSqlSession().selectList("manage.onLearningSalesList", params);
	}
	
	/**
	 * 경영관리 - 교수별 매출 리스트
	 * @param params
	 * @return
	 */
	public List<HashMap<String, Object>> onTeacherSalesList(Map<String, Object> params) {
		return getSqlSession().selectList("manage.onTeacherSalesList", params);
	}
	
	
	public List<HashMap<String, String>> dashBoardList1(HashMap<String, String> params) {
		return getSqlSession().selectList("manage.dashBoardList1", params);
	}
	public List<HashMap<String, String>> dashBoardList2(HashMap<String, String> params) {
		return getSqlSession().selectList("manage.dashBoardList2", params);
	}
	public List<HashMap<String, String>> dashBoardList3(HashMap<String, String> params) {
		return getSqlSession().selectList("manage.dashBoardList3", params);
	}
	public List<HashMap<String, String>> dashBoardList4(HashMap<String, String> params) {
		return getSqlSession().selectList("manage.dashBoardList4", params);
	}

	public List<HashMap<String, String>> dashBoardOffList1(HashMap<String, String> params) {
		return getSqlSession().selectList("manage.dashBoardOffList1", params);
	}
	public List<HashMap<String, String>> dashBoardOffList2(HashMap<String, String> params) {
		return getSqlSession().selectList("manage.dashBoardOffList2", params);
	}
	public List<HashMap<String, String>> dashBoardOffList3(HashMap<String, String> params) {
		return getSqlSession().selectList("manage.dashBoardOffList3", params);
	}

	/**
	 * 경영관리 - 매출 리스트
	 * @param params
	 * @return
	 */
	public List<HashMap<String, Object>> 학원매출내역(Map<String, Object> params) {
		return getSqlSession().selectList("manage.학원매출내역", params);
	}
	public List<HashMap<String, Object>> 동영상매출내역(Map<String, Object> params) {
		return getSqlSession().selectList("manage.동영상매출내역", params);
	}
	public List<HashMap<String, Object>> MovieSalesD(Map<String, Object> params) {
		return getSqlSession().selectList("manage.MovieSalesD", params);
	}
	public List<HashMap<String, Object>> MovieSalesP(Map<String, Object> params) {
		return getSqlSession().selectList("manage.MovieSalesP", params);
	}
	public List<HashMap<String, Object>> MovieSalesY(Map<String, Object> params) {
		return getSqlSession().selectList("manage.MovieSalesY", params);
	}
	public List<HashMap<String, Object>> BookSales(Map<String, Object> params) {
		return getSqlSession().selectList("manage.BookSales", params);
	}

}