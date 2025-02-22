package com.academy.board.service;

import java.io.Serializable;

import com.academy.common.CommonVO;

/**
*
* 게시판 VO 클래스
* @author rainend
* @version 1.0
* @see
** <pre>
* << 개정이력(Modification Information) >>
*
*  	  수정일           수정자                수정내용
*  ---------------    --------------    ---------------------------
*  2025.02.10			rainend			 게시판 등록
* </pre>
*/

public class BoardVO extends CommonVO implements Serializable {

	private static final long serialVersionUID = 638950577710720796L;

	/** 게시판코드 */
	private int boardId;
	/**  게시물제목 */
	private String boardTitle;
	/** 게시물내용 */
	private String boardMemo;
	/** by */
	private String board_by;
	/** descendants */
	private String descendants;
	/** score */
	private int score;
	/** url */
	private String url;
    
	/**
	 * boardId attribute 를 리턴한다.
	 * @return int
	 */
	public int getBoardId() {
		return boardId;
	}
	/**
	 * boardId attribute 값을 설정한다.
	 * @param boardId int
	 */
	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}
	/**
	 * boardTitle attribute 를 리턴한다.
	 * @return String
	 */
	public String getBoardTitle() {
		return boardTitle;
	}
	/**
	 * boardTitle attribute 값을 설정한다.
	 * @param boardTitle String
	 */
	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}
	/**
	 * boardMemo attribute 를 리턴한다.
	 * @return String
	 */
	public String getBoardMemo() {
		return boardMemo;
	}
	/**
	 * boardMemo attribute 값을 설정한다.
	 * @param boardMemo String
	 */
	public void setBoardMemo(String boardMemo) {
		this.boardMemo = boardMemo;
	}
	/**
	 * board_by attribute 를 리턴한다.
	 * @return String
	 */
	public String getBoard_by() {
		return board_by;
	}
	/**
	 * board_by attribute 값을 설정한다.
	 * @param board_by String
	 */
	public void setBoard_by(String board_by) {
		this.board_by = board_by;
	}
	/**
	 * descendants attribute 를 리턴한다.
	 * @return String
	 */
	public String getDescendants() {
		return descendants;
	}
	/**
	 * descendants attribute 값을 설정한다.
	 * @param descendants String
	 */
	public void setDescendants(String descendants) {
		this.descendants = descendants;
	}
	/**
	 * score attribute 를 리턴한다.
	 * @return int
	 */
	public int getScore() {
		return score;
	}
	/**
	 * score attribute 값을 설정한다.
	 * @param score int
	 */
	public void setScore(int score) {
		this.score = score;
	}
	/**
	 * url attribute 를 리턴한다.
	 * @return String
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * url attribute 값을 설정한다.
	 * @param url String
	 */
	public void setUrl(String url) {
		this.url = url;
	}

}
