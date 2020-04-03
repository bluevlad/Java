package web.survey.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web.survey.service.PollService;

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

	
			
}