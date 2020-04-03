package web.mocktest.board.service;

import java.util.HashMap;
import java.util.List;

import web.board.service.BoardService;

public interface MTBoardService extends BoardService {

	List<HashMap<String, String>> boardList(HashMap<String, String> params);

	int listCount(HashMap<String, String> params);

	HashMap<String, Object> getBoardDetail(HashMap<String, String> params);
}
