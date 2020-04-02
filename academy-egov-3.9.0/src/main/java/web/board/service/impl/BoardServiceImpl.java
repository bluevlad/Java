package web.board.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import web.board.service.BoardService;

@Service
public class BoardServiceImpl implements BoardService {

    @Resource(name="boardDAO")
    private BoardDAO boardDao;

    public List<HashMap<String, String>> getRankCode(HashMap<String, String> params) {
        return boardDao.getRankCode(params);
    }
    public List<HashMap<String, String>> getCategoryCode(Object obj) {
        return boardDao.getCategoryCode(obj);
    }
    public HashMap<String, String> getBoardKind(HashMap<String, String> params) {
        return boardDao.getBoardKind(params);
    }
    @Override
    public List<HashMap<String, String>> boardList(HashMap<String, String> params) {
        return boardDao.boardList(params);
    }
    @Override
    public int listCount(HashMap<String, String> params) {
        return boardDao.listCount(params);
    }
    @Override
    public List<HashMap<String, String>> boardAllList(HashMap<String, String> params) {
        return boardDao.boardAllList(params);
    }
    @Override
    public int listAllCount(HashMap<String, String> params) {
        return boardDao.listAllCount(params);
    }
    @Override
    public List<HashMap<String, String>> selectIntgBoardList(Object obj) {
        return boardDao.selectIntgBoardList(obj);
    }
    @Override
    public int getIntgBoardListCount(Object obj) {
        return boardDao.getIntgBoardListCount(obj);
    }
    @Override
    public List<HashMap<String, String>> boardFAQList(HashMap<String, String> params) {
        return boardDao.boardFAQList(params);
    }
    @Override
    public int boardFAQListCount(HashMap<String, String> params) {
        return boardDao.boardFAQListCount(params);
    }

    @Override
    public int getIsReply(HashMap<String, String> params) {
        return boardDao.getIsReply(params);
    }
    @Override
    public Object boardInsertProcess(HashMap<String, Object> params) {
        return boardDao.boardInsertProcess(params);
    }
    @Override
    public Object boardFAQInsertProcess(HashMap<String, Object> params) {
        return boardDao.boardFAQInsertProcess(params);
    }
    @Override
    public Object boardReplyInsertProcess(HashMap<String, Object> params) {
        return boardDao.boardReplyInsertProcess(params);
    }
    @Override
    public void insertBoardCatInfo(HashMap<String, Object> params) {
        boardDao.insertBoardCatInfo(params);
    }
    @Override
    public void deleteBoardCatInfo(HashMap<String, Object> params) {
        boardDao.deleteBoardCatInfo(params);
    }
    @Override
    public void deleteBoardData(HashMap<String, Object> params) {
        boardDao.deleteBoardData(params);
    }
    @Override
    public HashMap<String, Object> getBoardDetail(HashMap<String, String> params){
        return boardDao.getBoardDetail(params);
    }
    @Override
    public void deleteBoardFile(HashMap<String, Object> params){
        boardDao.deleteBoardFile(params);
    }
    @Override
    public void updateBoardFile(HashMap<String, Object> params){
        boardDao.updateBoardFile(params);
    }
    @Override
    public void updateBoardHits(HashMap<String, String> params){
        boardDao.updateBoardHits(params);
    }
    @Override
    public void updateISSUE(HashMap<String, String> params){
        boardDao.updateISSUE(params);
    }
    @Override
    public void updateRECOMMEND(HashMap<String, String> params){
        boardDao.updateRECOMMEND(params);
    }
    @Override
    public void updateOPEN_YN(HashMap<String, String> params){
        boardDao.updateOPEN_YN(params);
    }
    @Override
    public List<HashMap<String, String>> getboardCodeList(HashMap<String, String> params){
        return boardDao.getboardCodeList(params);
    }
    @Override
    public HashMap<String, Object> getFileInfo(HashMap<String, Object> params){
        return boardDao.getFileInfo(params);
    }
    @Override
    public List<HashMap<String, String>> getReplyData(HashMap<String, String> params){
        return boardDao.getReplyData(params);
    }
    @Override
    public Object boardFAQUpdateProcess(HashMap<String, Object> params){
        return boardDao.boardFAQUpdateProcess(params);
    }

    @Override
    public Object boardUpdateProcess(HashMap<String, Object> params){
        return boardDao.boardUpdateProcess(params);
    }
    @Override
    public Object boardReplyUpdateProcess(HashMap<String, Object> params){
        return boardDao.boardReplyUpdateProcess(params);
    }
    @Override
    public void insertBoardFile(HashMap<String, Object> params){
        boardDao.insertBoardFile(params);
    }
    @Override
    public List<HashMap<String, String>> boardAttachFile(HashMap<String, String> params) {
        return boardDao.boardAttachFile(params);
    }
    @Override
    public List<HashMap<String, String>> boardAttachFile_Img(HashMap<String, String> params) {
        return boardDao.boardAttachFile_Img(params);
    }
    @Override
    public void Modify_deleteBoardFileData(HashMap<String, Object> params) {
        boardDao.Modify_deleteBoardFileData(params);
    }
    @Override
    public void deleteBoardFileData(HashMap<String, Object> params) {
        boardDao.deleteBoardFileData(params);
    }
    @Override
    public void insertBoardReplyFile(HashMap<String, Object> params){
        boardDao.insertBoardReplyFile(params);
    }
    @Override
    public HashMap<String, Object> getBoardDetail_Origin(HashMap<String, String> params){
        return boardDao.getBoardDetail_Origin(params);
    }
    @Override
    public void updateBoardReply(HashMap<String, Object> params){
        boardDao.updateBoardReply(params);
    }
	@Override
	public List<HashMap<String, String>> selectBoardCmmntList(HashMap<String, String> params) {
		// TODO Auto-generated method stub
		return boardDao.selectBoardCmmntList(params);
	}
	@Override
	public int selectBoardCmmntListCount(HashMap<String, String> params) {
		// TODO Auto-generated method stub
		return boardDao.selectBoardCmmntListCount(params);
	}
	@Override
	public void insertBoardCmmnt(HashMap<String, String> params) {
		// TODO Auto-generated method stub
		boardDao.insertBoardCmmnt(params);
	}
	@Override
	public void deleteBoardCmmnt(HashMap<String, String> params) {
		// TODO Auto-generated method stub
		boardDao.deleteBoardCmmnt(params);
	}
}
