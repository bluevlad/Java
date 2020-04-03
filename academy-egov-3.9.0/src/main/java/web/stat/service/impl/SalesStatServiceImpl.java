package web.stat.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web.stat.service.SalesStatService;

@Service(value="SalesStatService")
public class SalesStatServiceImpl  implements  SalesStatService {

	@Autowired
	private SalesStatDAO dao;

	@Override
	public List<HashMap<String, String>> teacherList(HashMap<String, String> params) {
		return dao.teacherList(params);
	}
	
	@Override
	public List<HashMap<String, String>> teacherSubjectList(HashMap<String, String> params) {
		return dao.teacherSubjectList(params);
	}
	
	@Override
	public HashMap<String, String> teacherOne(HashMap<String, String> params) {
		return dao.teacherOne(params);
	}

	@Override
	public void makeOnSalesStat(Map<String, String> params) {
		dao.makeOnSalesStat(params);
	}

	@Override
	public void makeOffSalesStat(Map<String, String> params) {
		dao.makeOffSalesStat(params);		
	}

	@Override
	public List<HashMap<String, String>> teacherSalesStatList(HashMap<String, String> params) {
		return dao.teacherSalesStatList(params);
	}

	@Override
	public HashMap<String, String> userBuyStatList(HashMap<String, String> params) {
		return dao.userBuyStatList(params);
	}

	@Override
	public List<HashMap<String, String>> searchKeywordList(HashMap<String, String> params) {
		return dao.searchKeywordList(params);
	}
	@Override
	public int searchKeywordCount(HashMap<String, String> params){
		return dao.searchKeywordCount(params);
	}	
	@Override
	public int searchKeywordSum(HashMap<String, String> params){
		return dao.searchKeywordSum(params);
	}	
	
}