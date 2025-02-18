package com.academy.board;

import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.academy.board.service.BoardService;
import com.academy.board.service.BoardVO;
import com.academy.common.CORSFilter;
import com.academy.common.CommonUtil;
import com.academy.common.Configurations;
import com.academy.common.PaginationInfo;

@RestController
public class BoardApi extends CORSFilter {

    private BoardService boardService;

    public BoardApi(BoardService boardService) {
        this.boardService = boardService;
    }

    Configurations configurations = new Configurations();

    @Value("${board.pageUnit}")
    private int pageUnit;

    @Value("${board.pageSize}")
    private int pageSize;

	@GetMapping(value = "/api/getBoardList")
	public JSONObject list() throws Exception, IOException, ParseException { 
		
		JSONObject jsonObject = new JSONObject();

		BoardVO boardVO = new BoardVO();
	    
		/** paging */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(boardVO.getPageIndex());
		boardVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		boardVO.setRecordCountPerPage(pageUnit);
		
		jsonObject.put("data", boardService.selectBoardList(boardVO));
		
		return jsonObject;
	}

	@GetMapping(value = "/api/getBoard")
	public JSONObject view() throws Exception, IOException, ParseException { 

		int boardId = 1;
		JSONObject jsonObject = new JSONObject();
				
		BoardVO boardVO = new BoardVO();
		boardVO.setBoardId(boardId);
		
		jsonObject.put("item", boardService.getBoard(boardVO));

		return jsonObject;
	}

}