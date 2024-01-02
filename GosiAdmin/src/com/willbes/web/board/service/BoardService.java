package com.willbes.web.board.service;

import java.util.HashMap;
import java.util.List;

public interface BoardService {

    List<HashMap<String, String>> getRankCode(HashMap<String, String> params);

    List<HashMap<String, String>> getCategoryCode(Object obj);

    HashMap<String, String> getBoardKind(HashMap<String, String> params);

    List<HashMap<String, String>> boardList(HashMap<String, String> params);

    int listCount(HashMap<String, String> params);

    List<HashMap<String, String>> boardAllList(HashMap<String, String> params);

    int listAllCount(HashMap<String, String> params);

    List<HashMap<String, String>> selectIntgBoardList(Object obj);

    int getIntgBoardListCount(Object obj);

    List<HashMap<String, String>> boardFAQList(HashMap<String, String> params);

    int boardFAQListCount(HashMap<String, String> params);

    Object boardInsertProcess(HashMap<String, Object> params);

    Object boardFAQInsertProcess(HashMap<String, Object> params);

    void insertBoardCatInfo(HashMap<String, Object> boardTestClassMap);

    HashMap<String, Object> getBoardDetail(HashMap<String, String> params);

    void updateBoardHits(HashMap<String, String> params);

    void updateISSUE(HashMap<String, String> params);
    void updateRECOMMEND(HashMap<String, String> params);
    void updateOPEN_YN(HashMap<String, String> params);
    void updateMAIN_YN(HashMap<String, String> params);

    List<HashMap<String, String>> getboardCodeList(HashMap<String, String> params);

    HashMap<String, Object> getFileInfo(HashMap<String, Object> params);

    void deleteBoardCatInfo(HashMap<String, Object> params);

    Object boardUpdateProcess(HashMap<String, Object> params);

    Object boardFAQUpdateProcess(HashMap<String, Object> params);

    void updateBoardFile(HashMap<String, Object> params);

    void deleteBoardFile(HashMap<String, Object> params);

    Object boardReplyInsertProcess(HashMap<String, Object> params);

    int getIsReply(HashMap<String, String> params);

    List<HashMap<String, String>> getReplyData(HashMap<String, String> params);

    Object boardReplyUpdateProcess(HashMap<String, Object> params);

    void deleteBoardData(HashMap<String, Object> params);

    void insertBoardFile(HashMap<String, Object> params);

    List<HashMap<String, String>> boardAttachFile(HashMap<String, String> params);

    List<HashMap<String, String>> boardAttachFile_Img(HashMap<String, String> params);

    void Modify_deleteBoardFileData(HashMap<String, Object> params);

    void deleteBoardFileData(HashMap<String, Object> params);

    void insertBoardReplyFile(HashMap<String, Object> params);

    HashMap<String, Object> getBoardDetail_Origin(HashMap<String, String> params);
    
    void updateBoardReply(HashMap<String, Object> params);
    
    List<HashMap<String, String>> selectBoardCmmntList(HashMap<String, String> params);

    int selectBoardCmmntListCount(HashMap<String, String> params);
    
    void insertBoardCmmnt(HashMap<String, String> params);

    void deleteBoardCmmnt(HashMap<String, String> params);

}
