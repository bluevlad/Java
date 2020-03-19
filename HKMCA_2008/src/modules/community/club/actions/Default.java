/*
 * Default.java, @ 2005-03-18
 * 
 * Copyright (c) 2004 UBQ All rights reserved.
 */
package modules.community.club.actions;

import javax.servlet.http.HttpServletResponse;

import maf.web.http.MyHttpServletRequest;
import maf.web.mvc.beans.ResultMessage;
import modules.community.club.beans.ClubBoardBean;
import modules.community.club.dao.ClubBoardDB;
import modules.community.club.dao.ClubDB;



/**
 * @author UBQ
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