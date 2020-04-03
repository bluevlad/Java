package com.willbes.web.box.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.willbes.cmm.service.impl.CmmAbstractMapper;

@Repository
public class BoxDAO extends CmmAbstractMapper {

	// 사물함 목록 조회  2013.11.08
	public List<HashMap<String, String>> boxList(HashMap<String, String> params) {
		return getSqlSession().selectList("box.boxList", params);
	}

	// 사물함 등록 처리  2013.11.08
	public void boxInsertProcess(HashMap<String, String> params) {
		getSqlSession().insert("box.boxInsertProcess",params);
	}

	// 사물함 상세정보 등록 처리
	public void boxNumInsertProcess(HashMap<String, String> params) {
		getSqlSession().insert("box.boxNumInsertProcess",params);
	}

	public HashMap<String, String> getBoxDetail(HashMap<String, String> params) {
		return getSqlSession().selectOne("box.getBoxDetail", params);
	}

	public List<HashMap<String, String>> getBoxNumList(HashMap<String, String> params) {
		return getSqlSession().selectList("box.getBoxNumList", params);
	}

	public int getBoxNumYCount(HashMap<String, String> params) {
		return getSqlSession().selectOne("box.getBoxNumYCount", params);
	}

	public void boxDeleteBoxAll(HashMap<String, String> params) {
		getSqlSession().delete("box.boxDeleteBoxAll", params);
	}

	public void boxUpdate2Process(HashMap<String, String> params) {
		getSqlSession().update("box.boxUpdate2Process", params);
	}

	public void boxUpdateProcess(HashMap<String, String> params) {
		getSqlSession().update("box.boxUpdateProcess", params);
	}

	public void boxDeleteBoxNum(HashMap<String, String> params) {
		getSqlSession().delete("box.boxDeleteBoxNum", params);
	}

	public HashMap<String, String> boxNumRentDetail(HashMap<String, String> params) {
		return getSqlSession().selectOne("box.boxNumRentDetail", params);
	}

	public HashMap<String, String> boxNumRentOrderDetail(HashMap<String, String> params) {
		return getSqlSession().selectOne("box.boxNumRentOrderDetail", params);
	}

	public List<HashMap<String, String>> boxNumRentOrderList(HashMap<String, String> params) {
		return getSqlSession().selectList("box.boxNumRentOrderList", params);
	}

	public void updateBoxFlagAsync(HashMap<String, String> params) {
		getSqlSession().update("box.updateBoxFlagAsync", params);
	}

	public void boxNumUpdateProcess(HashMap<String, String> params) {
		getSqlSession().update("box.boxNumUpdateProcess", params);
	}

	public void boxNumRentUpdateProcess(HashMap<String, String> params) {
		getSqlSession().update("box.boxNumRentUpdateProcess", params);
	}

	public void offApprovalsUpdateProcess(HashMap<String, String> params) {
		getSqlSession().update("box.offApprovalsUpdateProcess", params);
	}

	public String getOffOrderNo(HashMap<String, String> params) {
		return getSqlSession().selectOne("box.getOffOrderNo", params);
	}

	public void offOrdersInsertProcess(HashMap<String, String> params) {
		getSqlSession().insert("box.offOrdersInsertProcess",params);
	}

	public void offOrderMgntNoInsertProcess(HashMap<String, String> params) {
		getSqlSession().insert("box.offOrderMgntNoInsertProcess",params);
	}

	public void offApprovalsInsertProcess(HashMap<String, String> params) {
		getSqlSession().insert("box.offApprovalsInsertProcess",params);
	}

	public void boxRentInsertProcess(HashMap<String, String> params) {
		getSqlSession().insert("box.boxRentInsertProcess",params);
	}

	public int getBoxRentSeq(HashMap<String, String> params) {
		return getSqlSession().selectOne("box.getBoxRentSeq", params);
	}

	public void offRefundInsertProcess(HashMap<String, String> params) {
		getSqlSession().insert("box.offRefundInsertProcess",params);
	}

	public void offApprovalsRefundUpdateProcess(HashMap<String, String> params) {
		getSqlSession().update("box.offApprovalsRefundUpdateProcess", params);
	}

	public void boxNumRentRefundUpdateProcess(HashMap<String, String> params) {
		getSqlSession().update("box.boxNumRentRefundUpdateProcess", params);
	}

	public void boxNumResetUpdateProcess(HashMap<String, String> params) {
		getSqlSession().update("box.boxNumResetUpdateProcess", params);
	}

	public HashMap<String, String> boxOrderDetail(HashMap<String, String> params) {
		return getSqlSession().selectOne("box.boxOrderDetail", params);
	}

	public void boxDeleteBoxRentByOrderId(HashMap<String, String> params) {
		getSqlSession().delete("box.boxDeleteBoxRentByOrderId", params);
	}

	public void boxRentChangeUpdateProcess(HashMap<String, String> params) {
		getSqlSession().update("box.boxRentChangeUpdateProcess", params);
	}

	public void boxNumChangeUpdateProcess(HashMap<String, String> params) {
		getSqlSession().update("box.boxNumChangeUpdateProcess", params);
	}

	public List<HashMap<String, String>> getBoxOrderList(HashMap<String, String> params) {
		return getSqlSession().selectList("box.getBoxOrderList", params);
	}

	public int getBoxOrderListCount(HashMap<String, String> params) {
		return getSqlSession().selectOne("box.getBoxOrderListCount", params);
	}

	public List<HashMap<String, String>> getBoxOrderListExcel(HashMap<String, String> params) {
		return getSqlSession().selectList("box.getBoxOrderListExcel",params);
	}

	public List<HashMap<String, String>> getBoxExtOrderList(HashMap<String, String> params) {
		return getSqlSession().selectList("box.getBoxExtOrderList", params);
	}

	public int getBoxExtOrderListCount(HashMap<String, String> params) {
		return getSqlSession().selectOne("box.getBoxExtOrderListCount", params);
	}

	public List<HashMap<String, String>> getBoxExtOrderListExcel(HashMap<String, String> params) {
		return getSqlSession().selectList("box.getBoxExtOrderListExcel",params);
	}

	public void boxNumResetUpdateByOrderIdProcess(HashMap<String, String> params) {
		getSqlSession().update("box.boxNumResetUpdateByOrderIdProcess", params);
	}

	public void deleteBoxOrder(HashMap<String, String> params) {
		getSqlSession().update("box.deleteBoxOrder", params);		
	}
	
}
