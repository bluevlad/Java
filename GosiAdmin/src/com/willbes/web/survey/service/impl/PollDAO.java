package com.willbes.web.survey.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.willbes.cmm.service.impl.CmmAbstractMapper;

@Repository
public class PollDAO extends CmmAbstractMapper {

	public List<HashMap<String, String>> pollList(HashMap<String, String> params){
		return getSqlSession().selectList("poll.pollList", params);
	}
	public int pollListCount(HashMap<String, String> params){
		return getSqlSession().selectOne("poll.pollListCount", params);
	}
	public HashMap<String, String> pollView(HashMap<String, String> params){
		return getSqlSession().selectOne("poll.pollView", params);
	}
	public void pollInsert(HashMap<String, String> params){
		getSqlSession().update("poll.pollInsert", params);
	}
	public void pollUpdate(HashMap<String, String> params){
		getSqlSession().update("poll.pollUpdate", params);
	}
	public void pollDelete(HashMap<String, String> params){
		getSqlSession().delete("poll.pollDelete", params);
	}

	public List<HashMap<String, String>> pollItemList(HashMap<String, String> params){
		return getSqlSession().selectList("poll.pollItemList", params);
	}
	public HashMap<String, String> pollItemView(HashMap<String, String> params){
		return getSqlSession().selectOne("poll.pollItemView", params);
	}
	public void pollItemInsert(HashMap<String, String> params){
		getSqlSession().update("poll.pollItemInsert", params);
	}
	public void pollItemUpdate(HashMap<String, String> params){
		getSqlSession().update("poll.pollItemUpdate", params);
	}
	public void pollItemDelete(HashMap<String, String> params){
		getSqlSession().delete("poll.pollItemDelete", params);
	}

	public List<HashMap<String, String>> pollResultList(HashMap<String, String> params){
		return getSqlSession().selectList("poll.pollResultList", params);
	}
}
