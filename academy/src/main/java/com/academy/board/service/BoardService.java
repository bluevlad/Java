package com.academy.board.service;

import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import com.academy.locker.service.LockerVO;
import com.academy.mapper.BoardMapper;

@Service
public class BoardService {

	private BoardMapper boardMapper;
	
	public BoardService(BoardMapper boardMapper) {
		this.boardMapper = boardMapper;
	}
	
	public ArrayList<JSONObject> selectBoardList(BoardVO boardVO) {
		return boardMapper.selectBoardList(boardVO);
	}
    public int selectBoardListTotCnt(BoardVO boardVO) {
        return boardMapper.selectBoardListTotCnt(boardVO);
    }
	
	public JSONObject getBoard(BoardVO boardVO) {
		return boardMapper.getBoard(boardVO);
	}

}