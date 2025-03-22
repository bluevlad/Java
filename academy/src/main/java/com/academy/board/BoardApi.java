package com.academy.board;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
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

    /** log */
	private static final Logger LOGGER = LoggerFactory.getLogger(BoardApi.class);

	@GetMapping(value = "/api/getBoardList")
	public JSONObject list(@ModelAttribute("BoardVO") BoardVO boardVO, @RequestParam Map<?, ?> commandMap) throws Exception { 
		
		HashMap<String,Object> jsonObject = new HashMap<String,Object>();
		
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
	public JSONObject view(@ModelAttribute("BoardVO") BoardVO boardVO) throws Exception { 

		HashMap<String,Object> jsonObject = new HashMap<String,Object>();
		
		jsonObject.put("boardItem", boardService.getBoard(boardVO));

		JSONObject jObject = new JSONObject(jsonObject);
		
		return jObject;
	}

	/**
	 * 게시물 등록화면.
	 * @throws Exception
	 */
	@PostMapping(value = "/api/insertBoard")
	public JSONObject insert(@ModelAttribute("BoardVO") BoardVO boardVO) throws Exception { 

		HashMap<String,Object> jsonObject = new HashMap<String,Object>();

		try {
			boardService.insertBoard(boardVO);
			jsonObject.put("retMsg", "OK");
		} catch (Exception e){
			jsonObject.put("retMsg", "FAIL");
			e.printStackTrace();
		}
		
		JSONObject jObject = new JSONObject(jsonObject);

		return jObject;
	}

	/**
	 * 게시물 정보를 업데이트 한다.
	 * @param lockerVO
	 * @throws Exception
	 */
	@PostMapping(value="/api/updateBoard")
	public JSONObject update(@ModelAttribute("BoardVO") BoardVO boardVO) throws Exception {
		
		HashMap<String,Object> jsonObject = new HashMap<String,Object>();
		
		try {
			boardService.updateBoard(boardVO);
			jsonObject.put("retMsg", "OK");
		} catch (Exception e){
			jsonObject.put("retMsg", "FAIL");
			e.printStackTrace();
		}
	
		JSONObject jObject = new JSONObject(jsonObject);

		return jObject;
	}

	/**
	 * 사물함정보를 변경한다.
	 * @param ExamVO
	 * @param commandMap
	 * @param bindingResult
	 * @param model
	 * @throws Exception
	 */
	@PostMapping(value="/api/deleteBoard")
	public JSONObject delete(@ModelAttribute("BoardVO") BoardVO boardVO) throws Exception {

		HashMap<String,Object> jsonObject = new HashMap<String,Object>();
		
		try {
			boardService.deleteBoard(boardVO);
			jsonObject.put("retMsg", "OK");
		} catch (Exception e){
			jsonObject.put("retMsg", "FAIL");
			e.printStackTrace();
		}
		
		JSONObject jObject = new JSONObject(jsonObject);

		return jObject;
	}

}