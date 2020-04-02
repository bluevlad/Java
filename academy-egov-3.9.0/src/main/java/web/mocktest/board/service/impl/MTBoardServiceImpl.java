package web.mocktest.board.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import web.board.service.impl.BoardServiceImpl;
import web.mocktest.board.service.MTBoardService;

@Service
public class MTBoardServiceImpl extends BoardServiceImpl implements MTBoardService {

	@Autowired
	private MTBoardDAO boardDao;

	@Override
	public List<HashMap<String, String>> boardList(HashMap<String, String> params) {
		return boardDao.boardList(params);
	}
	@Override
	public int listCount(HashMap<String, String> params) {
		return boardDao.listCount(params);
	}

	@Override
	public HashMap<String, Object> getBoardDetail(HashMap<String, String> params){
		return boardDao.getBoardDetail(params);
	}
}
