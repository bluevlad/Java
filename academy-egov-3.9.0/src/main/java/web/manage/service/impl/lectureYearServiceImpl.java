package web.manage.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web.manage.service.lectureYearService;

@Service
public class lectureYearServiceImpl  implements  lectureYearService {

	@Autowired
	private lectureYearDAO dao;

	@Override
	public List<HashMap<String, String>> onPayList(Map<String, String> params) {
		return dao.onPayList(params);
	}

	@Override
	public List<HashMap<String, String>> onOrderList(Map<String, String> params) {
		return dao.onOrderList(params);
	}

	@Override
	public List<HashMap<String, String>> payUserListByLeccode(HashMap<String, String> params) {
		return dao.payUserListByLeccode(params);
	}

	@Override
	public int payUserListByLeccodeCount(HashMap<String, String> params) {
		return dao.payUserListByLeccodeCount(params);
	}

}