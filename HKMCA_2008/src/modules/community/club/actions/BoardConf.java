/*
 * BoardConf.java, @ 2005-03-20
 * 
 * Copyright (c) 2004 UBQ All rights reserved.
 */
package modules.community.club.actions;

import javax.servlet.http.HttpServletResponse;

import maf.web.http.MyHttpServletRequest;
import maf.web.mvc.beans.ResultMessage;
import modules.community.club.ClubManager;
import modules.community.club.beans.ClubBoardBean;
import modules.community.club.dao.ClubBoardDB;



/**
 * @author UBQ
 * 
 * �Խ��� ����
 * 
 */
public class BoardConf extends BaseClubAction {
	public void doWork(MyHttpServletRequest _req, HttpServletResponse response) {

		try {
			// Ŭ���Խ��� ����
			ClubBoardBean[] boards = ClubBoardDB.getClubBoardAll(oDb, club_id);

			result.setAttribute("MAX_BOARD_CNT", ClubManager.MAX_BOARD_CNT + "");
			result.setAttribute("boards", boards);
			
			result.setForward("board_conf");
		} catch (Exception e) {
			result.setError(e, new ResultMessage(this.MESAGE_BUNDLE_NAME, "unkownError"));
		}
	}
}
