package com.willbes.web.manage.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface CategorySaleService {

	List<HashMap<String, Object>> onCategorySalesList(Map<String, Object> params);
	List<HashMap<String, Object>> onCategorySalesListSum(Map<String, Object> params);
	List<HashMap<String, Object>> onTeacherPayList(Map<String, Object> params);
	List<HashMap<String, Object>> onTeacherPayDetail(Map<String, Object> params);
	List<HashMap<String, Object>> onTeacherPayReturnDetail(Map<String, Object> params);
	List<HashMap<String, Object>> offCategorySalesList(Map<String, Object> params);
	List<HashMap<String, Object>> offCategorySalesListSum(Map<String, Object> params);
	List<HashMap<String, Object>> offTeacherPayList(Map<String, Object> params);
	List<HashMap<String, Object>> offTeacherPayDetail(Map<String, Object> params);
	List<HashMap<String, Object>> offTeacherPayReturnDetail(Map<String, Object> params);
	List<HashMap<String, Object>> onAdvanceReceivedList(Map<String, Object> params);
	List<HashMap<String, Object>> offAdvanceReceivedList(Map<String, Object> params);
	List<HashMap<String, Object>> offAdvanceReceivedList_D(Map<String, Object> params);
	List<HashMap<String, Object>> offAdvanceReceivedList_P(Map<String, Object> params);
	List<HashMap<String, Object>> offTeacherAdjustList(Map<String, Object> params);
	List<HashMap<String, Object>> offTeacherAdjustDetail(Map<String, Object> params);
	List<HashMap<String, Object>> offAdjustOrderList(Map<String, Object> params);
	List<HashMap<String, Object>> offAdjustDeductList(Map<String, Object> params);
	List<HashMap<String, Object>> offAdjustDeductEtcList(Map<String, Object> params);

	List<HashMap<String, Object>> offLecSalesList(Map<String, Object> params);
	List<HashMap<String, Object>> offLecSalesJongList(Map<String, Object> params);
	// 단과 매출 내역 엑셀 리스트
	@SuppressWarnings({ "rawtypes" })
	List<HashMap<String, String>> offLecSalesExcelList(HashMap<String, String> params);

	void offTeacherAdjustInsert(Map<String, Object> params);
	void offAdjustDeductInsert(Map<String, Object> params);
	void offTeacherAdjustUpdate(Map<String, Object> params);
	String offTeacherAdjustListCount(Map<String, Object> params);
	
	List<HashMap<String, Object>> onSalesList(Map<String, Object> params);
	List<HashMap<String, Object>> onSubjectSalesList(Map<String, Object> params);
	List<HashMap<String, Object>> onLearningSalesList(Map<String, Object> params);
	List<HashMap<String, Object>> onTeacherSalesList(Map<String, Object> params);
	
	
	List<HashMap<String, String>> dashBoardList1(HashMap<String, String> params);
	List<HashMap<String, String>> dashBoardList2(HashMap<String, String> params);
	List<HashMap<String, String>> dashBoardList3(HashMap<String, String> params);
	List<HashMap<String, String>> dashBoardList4(HashMap<String, String> params);

	List<HashMap<String, String>> dashBoardOffList1(HashMap<String, String> params);
	List<HashMap<String, String>> dashBoardOffList2(HashMap<String, String> params);
	List<HashMap<String, String>> dashBoardOffList3(HashMap<String, String> params);
	
	List<HashMap<String, Object>> 학원매출내역(Map<String, Object> params);
	List<HashMap<String, Object>> 동영상매출내역(Map<String, Object> params);
	List<HashMap<String, Object>> MovieSalesD(Map<String, Object> params);
	List<HashMap<String, Object>> MovieSalesP(Map<String, Object> params);
	List<HashMap<String, Object>> MovieSalesY(Map<String, Object> params);
	List<HashMap<String, Object>> BookSales(Map<String, Object> params);
	
}