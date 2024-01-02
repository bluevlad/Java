package com.willbes.web.survey.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.willbes.web.survey.service.PollService;

@Service(value="PollService")
public class PollServiceImpl implements PollService{
	@Autowired 
	private PollDAO polldao;
	
	@Override
	public List<HashMap<String, String>> pollList(HashMap<String, String> params){
		return polldao.pollList(params);
	}		
	@Override
	public int pollListCount(HashMap<String, String> params){
		return polldao.pollListCount(params);
	}
	@Override
	public HashMap<String, String> pollView(HashMap<String, String> params){
		return polldao.pollView(params);
	}		
	@Override
	public void pollInsert(HashMap<String, String> params){
		polldao.pollInsert(params);
	}	
	@Override
	public void pollUpdate(HashMap<String, String> params){
		polldao.pollUpdate(params);
	}	
	@Override
	public void pollDelete(HashMap<String, String> params){
		polldao.pollDelete(params);
	}	

	@Override
	public List<HashMap<String, String>> pollItemList(HashMap<String, String> params){
		return polldao.pollItemList(params);
	}		
	@Override
	public HashMap<String, String> pollItemView(HashMap<String, String> params){
		return polldao.pollItemView(params);
	}		
	@Override
	public void pollItemInsert(HashMap<String, String> params){
		polldao.pollItemInsert(params);
	}	
	@Override
	public void pollItemUpdate(HashMap<String, String> params){
		polldao.pollItemUpdate(params);
	}	
	@Override
	public void pollItemDelete(HashMap<String, String> params){
		polldao.pollItemDelete(params);
	}	

	@Override
	public List<HashMap<String, String>> pollResultList(HashMap<String, String> params){
		return polldao.pollResultList(params);
	}		
			
}