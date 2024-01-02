package com.willbes.web.manage.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.willbes.web.manage.service.CategorySaleService;

@Service
public class CategorySaleServiceImpl  implements  CategorySaleService {

	@Autowired
	private CategorySaleDAO dao;

	@Override
	public List<HashMap<String, Object>> onCategorySalesList(Map<String, Object> params) {
		return dao.onCategorySalesList(params);
	}

	@Override
	public List<HashMap<String, Object>> onCategorySalesListSum(Map<String, Object> params) {
		return dao.onCategorySalesListSum(params);
	}

	@Override
	public List<HashMap<String, Object>> onTeacherPayList(Map<String, Object> params) {
		return dao.onTeacherPayList(params);
	}

	@Override
	public List<HashMap<String, Object>> onTeacherPayDetail(Map<String, Object> params) {
		return dao.onTeacherPayDetail(params);
	}

	@Override
	public List<HashMap<String, Object>> onTeacherPayReturnDetail(Map<String, Object> params) {
		return dao.onTeacherPayReturnDetail(params);
	}

	@Override
	public List<HashMap<String, Object>> onAdvanceReceivedList(Map<String, Object> params) {
		return dao.onAdvanceReceivedList(params);
	}
	@Override
	public List<HashMap<String, Object>> offAdvanceReceivedList(Map<String, Object> params) {
		return dao.offAdvanceReceivedList(params);
	}
	@Override
	public List<HashMap<String, Object>> offAdvanceReceivedList_D(Map<String, Object> params) {
		return dao.offAdvanceReceivedList_D(params);
	}
	@Override
	public List<HashMap<String, Object>> offAdvanceReceivedList_P(Map<String, Object> params) {
		return dao.offAdvanceReceivedList_P(params);
	}

	@Override
	public List<HashMap<String, Object>> offCategorySalesList(Map<String, Object> params) {
		return dao.offCategorySalesList(params);
	}

	@Override
	public List<HashMap<String, Object>> offCategorySalesListSum(Map<String, Object> params) {
		return dao.offCategorySalesListSum(params);
	}

	@Override
	public List<HashMap<String, Object>> offTeacherPayList(Map<String, Object> params) {
		return dao.offTeacherPayList(params);
	}

	@Override
	public List<HashMap<String, Object>> offTeacherPayDetail(Map<String, Object> params) {
		return dao.offTeacherPayDetail(params);
	}

	@Override
	public List<HashMap<String, Object>> offTeacherPayReturnDetail(Map<String, Object> params) {
		return dao.offTeacherPayReturnDetail(params);
	}

	@Override
	public List<HashMap<String, Object>> offTeacherAdjustList(Map<String, Object> params) {
		return dao.offTeacherAdjustList(params);
	}

	@Override
	public List<HashMap<String, Object>> offTeacherAdjustDetail(Map<String, Object> params) {
		return dao.offTeacherAdjustDetail(params);
	}

	@Override
	public List<HashMap<String, Object>> offAdjustOrderList(Map<String, Object> params) {
		return dao.offAdjustOrderList(params);
	}

	@Override
	public List<HashMap<String, Object>> offAdjustDeductList(Map<String, Object> params) {
		return dao.offAdjustDeductList(params);
	}

	@Override
	public List<HashMap<String, Object>> offAdjustDeductEtcList(Map<String, Object> params) {
		return dao.offAdjustDeductEtcList(params);
	}

	@Override
	public void offTeacherAdjustInsert(Map<String, Object> params) {
		dao.offTeacherAdjustInsert(params);
	}

	@Override
	public void offAdjustDeductInsert(Map<String, Object> params) {
		dao.offAdjustDeductInsert(params);		
	}

	@Override
	public void offTeacherAdjustUpdate(Map<String, Object> params) {
		dao.offTeacherAdjustUpdate(params);		
	}

	@Override
	public String offTeacherAdjustListCount(Map<String, Object> params) {
		return dao.offTeacherAdjustListCount(params);
	}

	@Override
	public List<HashMap<String, Object>> offLecSalesList(Map<String, Object> params) {
		return dao.offLecSalesList(params);
	}
	@Override
	public List<HashMap<String, Object>> offLecSalesJongList(Map<String, Object> params) {
		return dao.offLecSalesJongList(params);
	}
	
	// 전체상품주문관리 엑셀 리스트
	@Override
	public List<HashMap<String, String>> offLecSalesExcelList(HashMap<String, String> params){
	return dao.offLecSalesExcelList(params);
	}

	@Override
	public List<HashMap<String, Object>> onSalesList(Map<String, Object> params) {
		return dao.onSalesList(params);
	}

	@Override
	public List<HashMap<String, Object>> onSubjectSalesList(
			Map<String, Object> params) {
		// TODO Auto-generated method stub
		return dao.onSubjectSalesList(params);
	}

	@Override
	public List<HashMap<String, Object>> onLearningSalesList(
			Map<String, Object> params) {
		// TODO Auto-generated method stub
		return dao.onLearningSalesList(params);
	}

	@Override
	public List<HashMap<String, Object>> onTeacherSalesList(
			Map<String, Object> params) {
		// TODO Auto-generated method stub
		return dao.onTeacherSalesList(params);
	}
	
	@Override
	public List<HashMap<String, String>> dashBoardList1(HashMap<String, String> params) {
		return dao.dashBoardList1(params);
	}

	@Override
	public List<HashMap<String, String>> dashBoardList2(HashMap<String, String> params) {
		return dao.dashBoardList2(params);
	}

	@Override
	public List<HashMap<String, String>> dashBoardList3(HashMap<String, String> params) {
		return dao.dashBoardList3(params);
	}
	
	@Override
	public List<HashMap<String, String>> dashBoardList4(HashMap<String, String> params) {
		return dao.dashBoardList4(params);
	}
	
	@Override
	public List<HashMap<String, String>> dashBoardOffList1(HashMap<String, String> params) {
		return dao.dashBoardOffList1(params);
	}

	@Override
	public List<HashMap<String, String>> dashBoardOffList2(HashMap<String, String> params) {
		return dao.dashBoardOffList2(params);
	}

	@Override
	public List<HashMap<String, String>> dashBoardOffList3(HashMap<String, String> params) {
		return dao.dashBoardOffList3(params);
	}

	@Override
	public List<HashMap<String, Object>> 학원매출내역(Map<String, Object> params) {
		return dao.학원매출내역(params);
	}
	@Override
	public List<HashMap<String, Object>> 동영상매출내역(Map<String, Object> params) {
		return dao.동영상매출내역(params);
	}
	@Override
	public List<HashMap<String, Object>> MovieSalesD(Map<String, Object> params) {
		return dao.MovieSalesD(params);
	}
	@Override
	public List<HashMap<String, Object>> MovieSalesP(Map<String, Object> params) {
		return dao.MovieSalesP(params);
	}
	@Override
	public List<HashMap<String, Object>> MovieSalesY(Map<String, Object> params) {
		return dao.MovieSalesY(params);
	}
	@Override
	public List<HashMap<String, Object>> BookSales(Map<String, Object> params) {
		return dao.BookSales(params);
	}

}