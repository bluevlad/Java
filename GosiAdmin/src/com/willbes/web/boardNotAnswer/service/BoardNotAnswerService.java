package com.willbes.web.boardNotAnswer.service;

import java.util.HashMap;
import java.util.List;

public interface BoardNotAnswerService {

	List<HashMap<String, String>> boardList(HashMap<String, String> params);

	int listCount(HashMap<String, String> params);
	
	List<HashMap<String, String>> getRankCode(HashMap<String, String> params);
	
	void insertBoardCatInfo(HashMap<String, Object> boardTestClassMap);
	
	HashMap<String, Object> getBoardDetail(HashMap<String, String> params);
	
	List<HashMap<String, String>> getboardCodeList(HashMap<String, String> params);
	
	void deleteTboardTestClass(HashMap<String, Object> params);
	
	Object boardUpdateProcess(HashMap<String, Object> params);
	
	void updateBoardFile(HashMap<String, Object> params);
	
	Object boardReplyInsertProcess(HashMap<String, Object> params);
	
	List<HashMap<String, String>> getReplyData(HashMap<String, String> params);
	
	void deleteBoardData(HashMap<String, Object> params);
	
	void updateBoardHits(HashMap<String, String> params);
	
	HashMap<String, String> getBoardKind(HashMap<String, String> params);
	
	List<HashMap<String, String>> boardAttachFile(HashMap<String, String> params);
	
	List<HashMap<String, String>> boardAttachFile_Img(HashMap<String, String> params);
	
	void insertBoardReplyFile(HashMap<String, Object> params);
	
	void Modify_deleteBoardFileData(HashMap<String, Object> params);
	
	void insertBoardFile(HashMap<String, Object> params);
	
	void deleteBoardFileData(HashMap<String, Object> params);
	
	void updateBoardReply(HashMap<String, Object> params);
	
}
