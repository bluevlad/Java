package web.dday.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web.dday.service.DdayService;

@Service
public class DdayServiceImpl implements DdayService {
	@Autowired 
	private DdayDAO dao;
	
	@Override
	public List<HashMap<String, String>> list(HashMap<String, String> params) {
		return dao.list(params);
	}

	@Override
	public int listCount(HashMap<String, String> params) {
		return dao.listCount(params);
	}

	@Override
	public HashMap<String, String> view(HashMap<String, String> params) {
		return dao.view(params);
	}

	@Override
	public void insert(HashMap<String, String> params) {
		dao.insert(params);
	}

	@Override
	public void update(HashMap<String, String> params) {
		dao.update(params);
	}

	@Override
	public void delete(HashMap<String, String> params) {
		dao.delete(params);
	}

	@Override
	public List<HashMap<String, String>> categoryList(HashMap<String, String> params) {
		return dao.categoryList(params);
	}
}