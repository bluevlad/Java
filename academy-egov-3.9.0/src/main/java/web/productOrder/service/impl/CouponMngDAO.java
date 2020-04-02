package web.productOrder.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;

@Repository
public class CouponMngDAO extends EgovComAbstractDAO {

	public List<HashMap<String, String>> getCoopList(HashMap<String, String> params) {
		return getSqlSession().selectList("CouponMng.getCoopList", params);
	}
    // 전체 쿠폰 리스트
	public List<HashMap<String, String>> getCouponList(HashMap<String, String> params) {
		return getSqlSession().selectList("CouponMng.getCouponList", params);
	}
    // 전체 쿠폰 총 건수
	public int getCouponListCount(HashMap<String, String> params) {
		return getSqlSession().selectOne("CouponMng.getCouponListCount", params);
	}

	public HashMap<String, String> getCouponOne(HashMap<String, String> params){
		return getSqlSession().selectOne("CouponMng.getCouponOne", params);
	}

	public void insertCoupon(HashMap<String, String> params){
		getSqlSession().insert("CouponMng.insertCoupon", params);
	}

	public void updateCoupon(HashMap<String, String> params){
		getSqlSession().update("CouponMng.updateCoupon", params);
	}
    // 전체 쿠폰 사용자 리스트
	public List<HashMap<String, String>> getCouponUserList(HashMap<String, String> params) {
		return getSqlSession().selectList("CouponMng.getCouponUserList", params);
	}
    // 전체 쿠폰 사용자 총 건수
	public int getCouponUserListCount(HashMap<String, String> params) {
		return getSqlSession().selectOne("CouponMng.getCouponUserListCount", params);
	}

    // 제휴 쿠폰 리스트
	public List<HashMap<String, String>> getCoopLectureList(HashMap<String, String> params) {
		return getSqlSession().selectList("CouponMng.getCoopLectureList", params);
	}
    // 제휴 쿠폰 총 건수
	public int getCoopLectureListCount(HashMap<String, String> params) {
		return getSqlSession().selectOne("CouponMng.getCoopLectureListCount", params);
	}
    // 제휴 쿠폰 발급현황 리스트
	public List<HashMap<String, String>> getCoopCouponList(HashMap<String, String> params) {
		return getSqlSession().selectList("CouponMng.getCoopCouponList", params);
	}
    // 제휴 쿠폰 발급현황 총 건수
	public int getCoopCouponListCount(HashMap<String, String> params) {
		return getSqlSession().selectOne("CouponMng.getCoopCouponListCount", params);
	}
	public void insertCoopCoupon(HashMap<String, String> params){
		getSqlSession().insert("CouponMng.insertCoopCoupon", params);
	}	
	public void deleteCoopCoupon(HashMap<String, String> params){
		getSqlSession().delete("CouponMng.deleteCoopCoupon", params);
	}	

    // 공무원 쿠폰 사용 현황
	public List<HashMap<String, String>> getCouponOrderList(HashMap<String, String> params) {
		return getSqlSession().selectList("CouponMng.getCouponOrderList", params);
	}

}