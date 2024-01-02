package com.willbes.web.box.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.willbes.web.box.service.impl.BoxDAO;
import com.willbes.web.box.service.BoxService;

@Service(value="boxservice")
public class BoxServiceImpl implements BoxService {

	@Autowired
	private BoxDAO boxdao;

	@Override
	public List<HashMap<String, String>> boxList(HashMap<String, String> params) {
		return boxdao.boxList(params);
	}

	@Override
	public void boxInsertProcess(HashMap<String, String> params) {
		boxdao.boxInsertProcess(params);
	}

	@Override
	public void boxNumInsertProcess(HashMap<String, String> params) {
		boxdao.boxNumInsertProcess(params);
	}

	@Override
	public HashMap<String, String> getBoxDetail(HashMap<String, String> params) {
		return boxdao.getBoxDetail(params);
	}

	@Override
	public List<HashMap<String, String>> getBoxNumList(HashMap<String, String> params) {
		return boxdao.getBoxNumList(params);
	}

	@Override
	public int getBoxNumYCount(HashMap<String, String> params) {
		return boxdao.getBoxNumYCount(params);
	}

	@Override
	public void boxDeleteBoxAll(HashMap<String, String> params) {
		boxdao.boxDeleteBoxAll(params);		
	}

	@Override
	public void boxUpdate2Process(HashMap<String, String> params) {
		boxdao.boxUpdate2Process(params);
	}

	@Override
	public void boxUpdateProcess(HashMap<String, String> params) {
		boxdao.boxUpdateProcess(params);
	}

	@Override
	public void boxDeleteBoxNum(HashMap<String, String> params) {
		boxdao.boxDeleteBoxNum(params);
	}

	@Override
	public HashMap<String, String> boxNumRentDetail(
			HashMap<String, String> params) {
		return boxdao.boxNumRentDetail(params);
	}

	@Override
	public HashMap<String, String> boxNumRentOrderDetail(
			HashMap<String, String> params) {
		return boxdao.boxNumRentOrderDetail(params);
	}

	@Override
	public List<HashMap<String, String>> boxNumRentOrderList(
			HashMap<String, String> params) {
		return boxdao.boxNumRentOrderList(params);
	}

	@Override
	public void updateBoxFlagAsync(HashMap<String, String> params) {
		boxdao.updateBoxFlagAsync(params);
	}

	@Override
	public void boxNumUpdateProcess(HashMap<String, String> params) {
		boxdao.boxNumUpdateProcess(params);
	}

	@Override
	public void boxNumRentUpdateProcess(HashMap<String, String> params) {
		boxdao.boxNumRentUpdateProcess(params);		
	}

	@Override
	public void offApprovalsUpdateProcess(HashMap<String, String> params) {
		boxdao.offApprovalsUpdateProcess(params);		
	}

	@Override
	public String getOffOrderNo(HashMap<String, String> params) {
		return boxdao.getOffOrderNo(params);
	}

	@Override
	public void offOrdersInsertProcess(HashMap<String, String> params) {
		boxdao.offOrdersInsertProcess(params);		
	}

	@Override
	public void offOrderMgntNoInsertProcess(HashMap<String, String> params) {
		boxdao.offOrderMgntNoInsertProcess(params);		
	}

	@Override
	public void offApprovalsInsertProcess(HashMap<String, String> params) {
		boxdao.offApprovalsInsertProcess(params);		
	}

	@Override
	public void boxRentInsertProcess(HashMap<String, String> params) {
		boxdao.boxRentInsertProcess(params);
	}

	@Override
	public int getBoxRentSeq(HashMap<String, String> params) {
		return boxdao.getBoxRentSeq(params);
	}

	@Override
	public void offRefundInsertProcess(HashMap<String, String> params) {
		boxdao.offRefundInsertProcess(params);
	}

	@Override
	public void offApprovalsRefundUpdateProcess(HashMap<String, String> params) {
		boxdao.offApprovalsRefundUpdateProcess(params);
	}

	@Override
	public void boxNumRentRefundUpdateProcess(HashMap<String, String> params) {
		boxdao.boxNumRentRefundUpdateProcess(params);
	}

	@Override
	public void boxNumResetUpdateProcess(HashMap<String, String> params) {
		boxdao.boxNumResetUpdateProcess(params);
	}

	@Override
	public HashMap<String, String> boxOrderDetail(HashMap<String, String> params) {
		return boxdao.boxOrderDetail(params);
	}

	@Override
	public void boxDeleteBoxRentByOrderId(HashMap<String, String> params) {
		boxdao.boxDeleteBoxRentByOrderId(params);		
	}

	@Override
	public void boxRentChangeUpdateProcess(HashMap<String, String> params) {
		boxdao.boxRentChangeUpdateProcess(params);
	}

	@Override
	public void boxNumChangeUpdateProcess(HashMap<String, String> params) {
		boxdao.boxNumChangeUpdateProcess(params);
	}

	@Override
	public List<HashMap<String, String>> getBoxOrderList(HashMap<String, String> params) {
		return boxdao.getBoxOrderList(params);
	}

	@Override
	public int getBoxOrderListCount(HashMap<String, String> params) {
		return boxdao.getBoxOrderListCount(params);
	}

	@Override
	public List<HashMap<String, String>> getBoxOrderListExcel(HashMap<String, String> params) {
		return boxdao.getBoxOrderListExcel(params);
	}

	@Override
	public List<HashMap<String, String>> getBoxExtOrderList(HashMap<String, String> params) {
		return boxdao.getBoxExtOrderList(params);
	}

	@Override
	public int getBoxExtOrderListCount(HashMap<String, String> params) {
		return boxdao.getBoxExtOrderListCount(params);
	}

	@Override
	public List<HashMap<String, String>> getBoxExtOrderListExcel(HashMap<String, String> params) {
		return boxdao.getBoxExtOrderListExcel(params);
	}

	@Override
	public void boxNumResetUpdateByOrderIdProcess(HashMap<String, String> params) {
		boxdao.boxNumResetUpdateByOrderIdProcess(params);
	}

	@Override
	public void deleteBoxOrder(HashMap<String, String> params) {
		boxdao.deleteBoxOrder(params);
	}

}