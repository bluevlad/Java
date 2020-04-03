package com.willbes.web.mocktest.board.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.willbes.web.board.service.impl.BoardDAO;

@Repository
public class MTBoardDAO extends BoardDAO {

	public List<HashMap<String, String>> boardList(HashMap<String, String> params) {
		return getSqlSession().selectList("mockTestBoard.boardList", params);
	}

	public int listCount(HashMap<String, String> params) {
		return getSqlSession().selectOne("mockTestBoard.boardListCount", params);
	}

	public HashMap<String, Object> getBoardDetail(HashMap<String, String> params) {
		return getSqlSession().selectOne("mockTestBoard.getBoardDetail", params);
	}
}
