package web.board.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

@Service
public class BoardTeacherServiceImpl extends BoardServiceImpl {

    @Resource(name="boardTeacherDAO")
    private BoardTeacherDAO boardTeacherDao;

    @Override
    public List<HashMap<String, String>> boardList(HashMap<String, String> params) {
        return boardTeacherDao.boardList(params);
    }
    @Override
    public int listCount(HashMap<String, String> params) {
        return boardTeacherDao.listCount(params);
    }

}
