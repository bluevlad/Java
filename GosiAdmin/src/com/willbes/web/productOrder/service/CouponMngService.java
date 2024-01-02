package com.willbes.web.productOrder.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface CouponMngService {
	
	/* last modified 2015-04-24 */

	List<HashMap<String, String>> getCoopList(HashMap<String, String> params);

	// 전체 쿠폰 리스트
	List<HashMap<String, String>> getCouponList(HashMap<String, String> params);
	// 전체 쿠폰 총 건수
	int getCouponListCount(HashMap<String, String> params);
	HashMap<String, String> getCouponOne(HashMap<String, String> params);
	
	void insertCoupon(HashMap<String, String> params);
	void updateCoupon(HashMap<String, String> params);

	// 전체 쿠폰 발급자 리스트
	List<HashMap<String, String>> getCouponUserList(HashMap<String, String> params);
	int getCouponUserListCount(HashMap<String, String> params);

	List<HashMap<String, String>> getCoopLectureList(HashMap<String, String> params);
	int getCoopLectureListCount(HashMap<String, String> params);

	List<HashMap<String, String>> getCoopCouponList(HashMap<String, String> params);
	int getCoopCouponListCount(HashMap<String, String> params);
	
	void insertCoopCoupon(HashMap<String, String> params);
	void deleteCoopCoupon(HashMap<String, String> params);

	// 공무원 쿠폰 사용 현황
	List<HashMap<String, String>> getCouponOrderList(HashMap<String, String> params);
}