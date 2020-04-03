package com.willbes.web.lecture.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.willbes.web.lecture.service.KindService;
import com.willbes.web.lecture.service.impl.KindDAO;

@Service(value="kindservice")
public class KindServiceImpl implements KindService{
	@Autowired
	private KindDAO kinddao;

	@Override
	public List<HashMap<String, String>> getKindList(Object obj){
		return kinddao.getKindList(obj);
	}

	@Override
	public List<HashMap<String, String>> kindList(HashMap<String, String> params){
		return kinddao.kindList(params);
	}

	@Override
	public int kindListCount(HashMap<String, String> params){
		return kinddao.kindListCount(params);
	}

	@Override
	public void kindInsert(HashMap<String, String> params){
		kinddao.kindInsert(params);
	}

	@Override
	public List<HashMap<String, String>> kindView(HashMap<String, String> params){
		return kinddao.kindView(params);
	}

	@Override
	public void kindUpdate(HashMap<String, String> params){
		kinddao.kindUpdate(params);
	}

	@Override
	public void kindDelete(HashMap<String, String> params){
		kinddao.kindDelete(params);
	}

	@Override
	public int kindCheck(HashMap<String, String> params){
		return kinddao.kindCheck(params);
	}

	@Override
	public List<HashMap<String, String>> selectKindCode(){
		return kinddao.selectKindCode();
	}

	@Override
	public void SeqUpdate(HashMap<String, String> params){
		kinddao.SeqUpdate(params);
	}
}
