/*
 * BoardAdd.java, @ 2005-03-20
 * 
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package miraenet.app.club.actions;

import javax.servlet.http.HttpServletResponse;

import maf.web.http.MyHttpServletRequest;
import maf.web.mvc.beans.ResultMessage;
import miraenet.app.club.beans.ClubBoardBean;
import miraenet.app.club.dao.ClubBoardDB;

/**
 * @author goindole
 *
 * 게시판 추가 폼 
 */
public class BoardAdd extends BaseClubAction {
    public void doWork(MyHttpServletRequest _req, HttpServletResponse response){

        String bid = _req.getParameter( "bid" );

        try {
	        ClubBoardBean boardBean = null;
	        if (bid != null) {
	            boardBean = ClubBoardDB.getBoardInfo( oDb, club_id, bid );
	        }else {
	            boardBean = new ClubBoardBean();
	        }
	
	        result.setAttribute("board", boardBean);
	
	        result.setForward( "board_add");
        } catch (Exception e) {
        	result.setError(e, new ResultMessage(this.MESAGE_BUNDLE_NAME, "unkownError"));
        }
    }

}
