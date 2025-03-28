package com.academy.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.json.simple.JSONObject;

import com.academy.board.service.BoardVO;

/**
 * 게시판정보에 관한 데이터 접근 클래스를 정의한다.
 * @author rainend
 * @since 2025.02.10
 * @version 1.0
 * @see
 *
 * <pre>
 * << 개정이력(Modification Information) >>
 *
 *   수정일      		수정자           수정내용
 *  ----------------    --------    ---------------------------
 *   2025.02.10  		rainend          최초 생성
 * </pre>
 */
@Mapper
public interface BoardMapper {

    /**
     * @param MenuVO 검색조건
     * @return List 게시판 목록정보
     */
	public ArrayList<JSONObject> selectBoardList(BoardVO boardVO);
	public int selectBoardListTotCnt(BoardVO boardVO);

    /**
     * @param MenuVO 검색조건
     * @return Object 게시판 개별정보
     */
	public JSONObject getBoard(BoardVO boardVO);

    public void insertBoard(BoardVO boardVO);

    public void updateBoard(BoardVO boardVO);

    public void deleteBoard(BoardVO boardVO);

}
