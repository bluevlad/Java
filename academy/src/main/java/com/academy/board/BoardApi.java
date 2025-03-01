package com.academy.board;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.academy.board.service.BoardService;
import com.academy.board.service.BoardVO;
import com.academy.common.CORSFilter;
import com.academy.common.CommonUtil;
import com.academy.common.PaginationInfo;

@RestController
public class BoardApi extends CORSFilter {

    private BoardService boardService;

    public BoardApi(BoardService boardService) {
        this.boardService = boardService;
    }

	@GetMapping(value = "/api/getBoardList")
	public JSONObject list(@ModelAttribute("BoardVO") BoardVO boardVO, @RequestParam Map<?, ?> commandMap) throws Exception, IOException, ParseException { 
		
		HashMap<String,Object> jsonObject = new HashMap<String,Object>();
		
		String curPage = "1";
		if(!CommonUtil.empty(commandMap.get("curPage"))){
			curPage = (String)commandMap.get("curPage");
		}
		boardVO.setPageIndex(CommonUtil.parseInt(curPage));
	    
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
		jsonObject.put("paginationInfo", paginationInfo);
		
		JSONObject jObject = new JSONObject(jsonObject);
		
		return jObject;
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