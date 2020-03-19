/*
 * Default.java, @ 2005-03-18
 * 
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package miraenet.app.club.actions;

import javax.servlet.http.HttpServletResponse;

import maf.web.http.MyHttpServletRequest;
import maf.web.mvc.beans.ResultMessage;
import miraenet.app.club.beans.ClubBoardBean;
import miraenet.app.club.dao.ClubBoardDB;
import miraenet.app.club.dao.ClubDB;

/**
 * @author goindole
 * 
 * 클럽 메인
 */
public class Default extends BaseClubAction {
	public void doWork(MyHttpServletRequest request, HttpServletResponse response) {
		try {
			ClubDB.visitClub(oDb, sessionBean.getUsn(), club_id);

			ClubBoardBean[] boardList = ClubBoardDB.getClubBoards(oDb, club_id);
			result.setAttribute("mstBean", mstBean);
			result.setAttribute("boardList", boardList);
			result.setForward("club_frame");
		} catch (Exception e) {
			result.setError(e, new ResultMessage(e.getMessage()));
		}
		
	}
}