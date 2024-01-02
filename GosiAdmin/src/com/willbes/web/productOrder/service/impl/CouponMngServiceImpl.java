package com.willbes.web.productOrder.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.willbes.web.productOrder.service.CouponMngService;

@Service
public class CouponMngServiceImpl  implements  CouponMngService{
	
	@Autowired
	private CouponMngDAO coupondao;

	public List<HashMap<String, String>> getCoopList(HashMap<String, String> params){
		return coupondao.getCoopList(params);
	}
	// 전체 쿠폰 리스트
	public List<HashMap<String, String>> getCouponList(	HashMap<String, String> params){
		return coupondao.getCouponList(params);
	}
	
	// 전체 쿠폰 총 건수
	public int getCouponListCount(HashMap<String, String> params){
		return coupondao.getCouponListCount(params);
	}
	
	@Override
	public HashMap<String, String> getCouponOne(HashMap<String, String> params) {
		return coupondao.getCouponOne(params);
	}
	
	@Override
	public void insertCoupon(HashMap<String, String> params){
		coupondao.insertCoupon(params);
	}
	
	@Override
	public void updateCoupon(HashMap<String, String> params){
		coupondao.updateCoupon(params);
	}
	// 전체 쿠폰 발급자 리스트
	public List<HashMap<String, String>> getCouponUserList(HashMap<String, String> params){
		return coupondao.getCouponUserList(params);
	}
	// 전체 쿠폰 발급자 총 건수
	public int getCouponUserListCount(HashMap<String, String> params){
		return coupondao.getCouponUserListCount(params);
	}

	// 전체 쿠폰 발급자 리스트
	public List<HashMap<String, String>> getCoopLectureList(HashMap<String, String> params){
		return coupondao.getCoopLectureList(params);
	}
	// 제휴 쿠폰 총 건수
	public int getCoopLectureListCount(HashMap<String, String> params){
		return coupondao.getCoopLectureListCount(params);
	}
	// 제휴 쿠폰 발급현황 리스트
	public List<HashMap<String, String>> getCoopCouponList(HashMap<String, String> params){
		return coupondao.getCoopCouponList(params);
	}
	// 제휴 쿠폰 발급현황 총 건수
	public int getCoopCouponListCount(HashMap<String, String> params){
		return coupondao.getCoopCouponListCount(params);
	}
	@Override
	public void insertCoopCoupon(HashMap<String, String> params){
		coupondao.insertCoopCoupon(params);
	}
	@Override
	public void deleteCoopCoupon(HashMap<String, String> params){
		coupondao.deleteCoopCoupon(params);
	}
	
	// 공무원 쿠폰 사용 현황
	public List<HashMap<String, String>> getCouponOrderList(HashMap<String, String> params){
		return coupondao.getCouponOrderList(params);
	}
}
