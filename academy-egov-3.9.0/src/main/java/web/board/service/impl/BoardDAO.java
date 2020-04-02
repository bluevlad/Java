package web.board.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import egovframework.com.cmm.service.impl.EgovComAbstractDAO;

@Repository
public class BoardDAO extends EgovComAbstractDAO {

	public List<HashMap<String, String>> getRankCode(HashMap<String, String> params) {
		return getSqlSession().selectList("board.getRankCode", params);
	}
    public List<HashMap<String, String>> getCategoryCode(Object obj) {
        return getSqlSession().selectList("board.getRankCode", obj);
    }

	public HashMap<String, String> getBoardKind(HashMap<String, String> params) {
		return getSqlSession().selectOne("board.getBoardKind" , params);
	}

	public List<HashMap<String, String>> boardList(HashMap<String, String> params) {
		return getSqlSession().selectList("board.boardList", params);
	}

	public List<HashMap<String, String>> boardAllList(HashMap<String, String> params) {
		return getSqlSession().selectList("board.boardAllList", params);
	}

	public int listCount(HashMap<String, String> params) {
		return getSqlSession().selectOne("board.boardListCount", params);
	}

	public int listAllCount(HashMap<String, String> params) {
		return getSqlSession().selectOne("board.boardAllListCount", params);
	}

    public List<HashMap<String, String>> selectIntgBoardList(Object obj) {
        return getSqlSession().selectList("board.selectIntgBoardList", obj);
    }

    public int getIntgBoardListCount(Object obj) {
        return getSqlSession().selectOne("board.getIntgBoardListCount", obj);
    }

	public List<HashMap<String, String>> boardFAQList(HashMap<String, String> params) {
		return getSqlSession().selectList("board.boardFAQList", params);
	}

	public int boardFAQListCount(HashMap<String, String> params) {
		return getSqlSession().selectOne("board.boardFAQListCount", params);
	}

	public Object boardInsertProcess(HashMap<String, Object> params) {
		return getSqlSession().insert("board.boardInsertProcess", params);
	}
	public Object boardFAQInsertProcess(HashMap<String, Object> params) {
		return getSqlSession().insert("board.boardFAQInsertProcess", params);
	}

	public void insertBoardCatInfo(HashMap<String, Object> params) {
		getSqlSession().insert("board.insertBoardCatInfo", params);
	}

	public HashMap<String, Object> getBoardDetail(HashMap<String, String> params) {
		return getSqlSession().selectOne("board.getBoardDetail", params);
	}

	public void updateISSUE(HashMap<String, String> params) {
		getSqlSession().update("board.updateISSUE", params);
	}

	public void updateOPEN_YN(HashMap<String, String> params) {
		getSqlSession().update("board.updateOPEN_YN", params);
	}

	public void updateBoardHits(HashMap<String, String> params) {
		getSqlSession().update("board.updateBoardHits", params);
	}

	public void updateRECOMMEND(HashMap<String, String> params) {
		getSqlSession().update("board.updateRECOMMEND", params);
	}

	public List<HashMap<String, String>> getboardCodeList(HashMap<String, String> params) {
		return getSqlSession().selectList("board.getboardCodeList", params);
	}

	public HashMap<String, Object> getFileInfo(HashMap<String, Object> params) {
		return getSqlSession().selectOne("board.getFileInfo", params);
	}

	public void deleteBoardCatInfo(HashMap<String, Object> params) {
		getSqlSession().delete("board.deleteBoardCatInfo", params);
	}

	public Object boardUpdateProcess(HashMap<String, Object> params) {
		return getSqlSession().update("board.boardUpdateProcess", params);
	}

	public Object boardFAQUpdateProcess(HashMap<String, Object> params) {
		return getSqlSession().update("board.boardFAQUpdateProcess", params);
	}

	public void updateBoardFile(HashMap<String, Object> params) {
		getSqlSession().update("board.updateBoardFile", params);
	}

	public void deleteBoardFile(HashMap<String, Object> params) {
		getSqlSession().delete("board.deleteBoardFile", params);
	}

	public Object boardReplyInsertProcess(HashMap<String, Object> params) {
		return getSqlSession().insert("board.boardReplyInsertProcess", params);
	}

	public int getIsReply(HashMap<String, String> params) {
		return getSqlSession().selectOne("board.getIsReply", params);
	}

	public List<HashMap<String, String>> getReplyData(HashMap<String, String> params) {
		return getSqlSession().selectList("board.getReplyData", params);
	}

	public Object boardReplyUpdateProcess(HashMap<String, Object> params) {
		return getSqlSession().update("board.boardReplyUpdateProcess", params);
	}

	public void deleteBoardData(HashMap<String, Object> params) {
		getSqlSession().delete("board.deleteBoardData", params);
	}

	public void insertBoardFile(HashMap<String, Object> params) {
		getSqlSession().insert("board.insertBoardFile", params);
	}

	public List<HashMap<String, String>> boardAttachFile(HashMap<String, String> params){
		return getSqlSession().selectList("board.boardAttachFile", params);
	}

	public List<HashMap<String, String>> boardAttachFile_Img(HashMap<String, String> params) {
		return getSqlSession().selectList("board.boardAttachFile_Img", params);
	}

	public void Modify_deleteBoardFileData(HashMap<String, Object> params) {
		getSqlSession().delete("board.Modify_deleteBoardFileData", params);
	}

	public void deleteBoardFileData(HashMap<String, Object> params) {
		getSqlSession().delete("board.deleteBoardFileData", params);
	}

	public void insertBoardReplyFile(HashMap<String, Object> params) {
		getSqlSession().insert("board.insertBoardReplyFile", params);
	}
	public HashMap<String, Object> getBoardDetail_Origin(HashMap<String, String> params) {
		return getSqlSession().selectOne("board.getBoardDetail_Origin", params);
	}
	public void updateBoardReply(HashMap<String, Object> params) {
		getSqlSession().update("board.updateBoardReply", params);
	}
	public List<HashMap<String, String>> selectBoardCmmntList(HashMap<String, String> params) {
		return getSqlSession().selectList("board.selectBoardCmmntList", params);
	}
	public int selectBoardCmmntListCount(HashMap<String, String> params) {
		return getSqlSession().selectOne("board.selectBoardCmmntListCount", params);
	}
	public void insertBoardCmmnt(HashMap<String, String> params) {
		getSqlSession().insert("board.insertBoardCmmnt", params);
	}

	public void deleteBoardCmmnt(HashMap<String, String> params) {
		getSqlSession().delete("board.deleteBoardCmmnt", params);
	}
}
