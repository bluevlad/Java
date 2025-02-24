package com.academy.board;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.academy.board.service.BoardService;
import com.academy.board.service.BoardVO;
import com.academy.common.CommonUtil;
import com.academy.common.PaginationInfo;

@RestController
public class BoardApi/* extends CORSFilter*/ {

    private BoardService boardService;

    public BoardApi(BoardService boardService) {
        this.boardService = boardService;
    }

	@GetMapping(value = "/api/getBoardAll")
	public String[] all() throws Exception, IOException, ParseException { 
		
		BoardVO boardVO = new BoardVO();
		
		ArrayList<JSONObject> arrayJson = new ArrayList<JSONObject>();
		arrayJson = boardService.selectBoardIdAll(boardVO);
		
		String[] strArray = new String[arrayJson.size()];
		for (int k = 0; k < arrayJson.size(); k++) {
		    JSONObject tempJson = arrayJson.get(k);
			strArray[k] = tempJson.get("BOARD_ID").toString();
		}
		 
		return strArray;
	}

	@GetMapping(value = "/api/getBoardList")
	public ArrayList<JSONObject> list(@RequestParam Map<?, ?> commandMap, ModelMap model) throws Exception, IOException, ParseException { 
		
		JSONObject jsonObject = new JSONObject();

		BoardVO boardVO = new BoardVO();
	    
		/** paging */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(boardVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(boardVO.getPageUnit());
		paginationInfo.setPageSize(boardVO.getPageSize());

		boardVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		boardVO.setLastIndex(paginationInfo.getLastRecordIndex());
		boardVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		jsonObject.put("boardList", boardService.selectBoardList(boardVO));

		int totCnt = boardService.selectBoardListTotCnt(boardVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.addAttribute("paginationInfo", paginationInfo);
		
		return boardService.selectBoardList(boardVO);
	}

	@GetMapping(value = "/api/getBoard")
	public JSONObject view(@RequestParam Map<?, ?> commandMap, ModelMap model) throws Exception, IOException, ParseException { 

		int boardId = CommonUtil.parseInt(commandMap.get("boardId"));
		JSONObject jsonObject = new JSONObject();
				
		BoardVO boardVO = new BoardVO();
		boardVO.setBoardId(boardId);
		
//		jsonObject.put("item", boardService.getBoard(boardVO));

		return boardService.getBoard(boardVO);
	}

}