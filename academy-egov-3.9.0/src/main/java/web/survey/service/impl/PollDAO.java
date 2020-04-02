package web.survey.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;

@Repository
public class PollDAO extends EgovComAbstractDAO {

	public List<HashMap<String, String>> pollList(HashMap<String, String> params){
		return getSqlSession().selectList("poll.pollList", params);
	}

	public int pollListCount(HashMap<String, String> params){
		return getSqlSession().selectOne("poll.pollListCount", params);
	}

	public HashMap<String, String> pollView(HashMap<String, String> params){
		return getSqlSession().selectOne("poll.pollView", params);
	}

	public void pollUpdate(HashMap<String, String> params){
		getSqlSession().update("poll.pollUpdate", params);
	}

	public void pollInsert(HashMap<String, String> params){
		getSqlSession().update("poll.pollInsert", params);
	}

	public void pollDelete(HashMap<String, String> params){
		getSqlSession().delete("poll.pollDelete", params);
	}
}
