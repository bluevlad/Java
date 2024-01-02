package com.willbes.web.board.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public class BoardTeacherDAO extends BoardDAO {

	public List<HashMap<String, String>> boardList(HashMap<String, String> params) {
		return getSqlSession().selectList("boardTeacher.boardList", params);
	}

	public int listCount(HashMap<String, String> params) {
		return getSqlSession().selectOne("boardTeacher.boardListCount", params);
	}
}
