package com.willbes.web.note.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.willbes.cmm.service.impl.CmmAbstractMapper;

@Repository
public class NoteDAO extends CmmAbstractMapper {

	public List<HashMap<String, String>> noteList(HashMap<String, String> params){
		return getSqlSession().selectList("note.noteList", params);
	}

	public int noteListCount(HashMap<String, String> params){
		return getSqlSession().selectOne("note.noteListCount", params);
	}

	public HashMap<String, String> noteView(HashMap<String, String> params){
		return getSqlSession().selectOne("note.noteView", params);
	}

	public void noteUpdate(HashMap<String, String> params){
		getSqlSession().update("note.noteUpdate", params);
	}

	public void noteDelete(HashMap<String, String> params){
		getSqlSession().delete("note.noteDelete", params);
	}
}
