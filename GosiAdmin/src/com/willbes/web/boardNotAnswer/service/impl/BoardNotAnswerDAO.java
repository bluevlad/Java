package com.willbes.web.boardNotAnswer.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.willbes.cmm.service.impl.CmmAbstractMapper;

@Repository
public class BoardNotAnswerDAO extends CmmAbstractMapper {

	public List<HashMap<String, String>> boardList(HashMap<String, String> params) {
		return getSqlSession().selectList("boardNotAnswer.boardList", params);
	}

	public int listCount(HashMap<String, String> params) {
		return getSqlSession().selectOne("boardNotAnswer.boardListCount", params);
	}

	public List<HashMap<String, String>> getRankCode(HashMap<String, String> params) {
		return getSqlSession().selectList("boardNotAnswer.getRankCode", params);
	}

	public void insertBoardCatInfo(HashMap<String, Object> params) {
		getSqlSession().insert("boardNotAnswer.insertBoardCatInfo", params);

	}

	public HashMap<String, Object> getBoardDetail(HashMap<String, String> params) {
		return getSqlSession().selectOne("boardNotAnswer.getBoardDetail", params);
	}
	public List<HashMap<String, String>> getboardCodeList(HashMap<String, String> params) {
		return getSqlSession().selectList("boardNotAnswer.getboardCodeList", params);
	}

	public void deleteTboardTestClass(HashMap<String, Object> params) {
		getSqlSession().delete("boardNotAnswer.deleteTboardTestClass", params);
	}

	public Object boardUpdateProcess(HashMap<String, Object> params) {
		return getSqlSession().update("boardNotAnswer.boardUpdateProcess", params);
	}
	public void updateBoardFile(HashMap<String, Object> params) {
		getSqlSession().update("boardNotAnswer.updateBoardFile", params);
	}

	public Object boardReplyInsertProcess(HashMap<String, Object> params) {
		return getSqlSession().insert("boardNotAnswer.boardReplyInsertProcess", params);
	}
	public List<HashMap<String, String>> getReplyData(HashMap<String, String> params) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList("boardNotAnswer.getReplyData", params);
	}

	public void deleteBoardData(HashMap<String, Object> params) {
		getSqlSession().delete("boardNotAnswer.deleteBoardData", params);
	}

	public void updateBoardHits(HashMap<String, String> params) {
		getSqlSession().update("boardNotAnswer.updateBoardHits", params);
	}

	public HashMap<String, String> getBoardKind(HashMap<String, String> params) {
		return getSqlSession().selectOne("boardNotAnswer.getBoardKind" , params);
	}

	public List<HashMap<String, String>> boardAttachFile(HashMap<String, String> params){
		return getSqlSession().selectList("boardNotAnswer.boardAttachFile", params);
	}

	public List<HashMap<String, String>> boardAttachFile_Img(HashMap<String, String> params) {
		return getSqlSession().selectList("boardNotAnswer.boardAttachFile_Img", params);
	}

	public void insertBoardReplyFile(HashMap<String, Object> params) {
		getSqlSession().insert("boardNotAnswer.insertBoardReplyFile", params);
	}

	public void Modify_deleteBoardFileData(HashMap<String, Object> params) {
		getSqlSession().delete("boardNotAnswer.Modify_deleteBoardFileData", params);
	}

	public void insertBoardFile(HashMap<String, Object> params) {
		getSqlSession().insert("boardNotAnswer.insertBoardFile", params);
	}

	public void deleteBoardFileData(HashMap<String, Object> params) {
		getSqlSession().delete("boardNotAnswer.deleteBoardFileData", params);
	}
	
	public void updateBoardReply(HashMap<String, Object> params) {
		getSqlSession().update("boardNotAnswer.updateBoardReply", params);
	}
	/*
	public List<HashMap<String, String>> boardFAQList(HashMap<String, String> params) {
		return getSqlSession().selectList("boardNotAnswer.boardFAQList", params);
	}

	public int boardFAQListCount(HashMap<String, String> params) {
		return getSqlSession().selectOne("boardNotAnswer.boardFAQListCount", params);
	}

	public Object boardInsertProcess(HashMap<String, Object> params) {
		return getSqlSession().insert("boardNotAnswer.boardInsertProcess", params);
	}
	public Object boardFAQInsertProcess(HashMap<String, Object> params) {
		return getSqlSession().insert("boardNotAnswer.boardFAQInsertProcess", params);
	}

	public HashMap<String, Object> getFileInfo(HashMap<String, Object> params) {
		return getSqlSession().selectOne("boardNotAnswer.getFileInfo", params);
	}

	public Object boardFAQUpdateProcess(HashMap<String, Object> params) {
		return getSqlSession().update("boardNotAnswer.boardFAQUpdateProcess", params);
	}

	public void deleteBoardFile(HashMap<String, Object> params) {
		getSqlSession().delete("boardNotAnswer.deleteBoardFile", params);
	}

	public int getIsReply(HashMap<String, String> params) {
		return getSqlSession().selectOne("boardNotAnswer.getIsReply", params);
	}

	public Object boardReplyUpdateProcess(HashMap<String, Object> params) {
		return getSqlSession().update("boardNotAnswer.boardReplyUpdateProcess", params);
	}
	*/

}
