package com.willbes.web.gosi.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.willbes.web.gosi.service.PubService;

@Service(value="pubservice")
public class PubServiceImpl implements PubService{
    @Autowired 
    private PubDAO pubdao;
    
	@Override
	public List<HashMap<String, String>> getPubList(HashMap<String, String> params){
		return pubdao.getPubList(params);
	}
	@Override
	public int getPubListCount(HashMap<String, String> params){
		return pubdao.getPubListCount(params);
	}
	@Override
	public void Pub_Insert(HashMap<String, String> params){
		pubdao.Pub_Insert(params);
	}
	@Override
	public void Pub_Update(HashMap<String, String> params){
		pubdao.Pub_Update(params);
	}
	@Override
	public void Pub_delete(HashMap<String, String> params){
		pubdao.Pub_delete(params);
	}
	@Override
	public HashMap<String, String> getPubOne(HashMap<String, String> params){
		return pubdao.getPubOne(params);
	}
    public int getMaxPubNo(HashMap<String, String> params) {
        return pubdao.getMaxPubNo(params);
    }
	@Override
	public List<HashMap<String, String>> getPubFiles(HashMap<String, String> params){
		return pubdao.getPubFiles(params);
	}
	@Override
	public void AttachFile_delete(HashMap<String, String> params){
		pubdao.AttachFile_delete(params);
	}
	@Override
	public void AttachFile_insert(HashMap<String, String> params){
		pubdao.AttachFile_insert(params);
	}

}