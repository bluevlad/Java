/*
 * BoardConf.java, @ 2005-03-20
 * 
 * Copyright (c) 2004 (��)�̷��� All rights reserved.
 */
package miraenet.app.club.actions;

import javax.servlet.http.HttpServletResponse;

import maf.web.http.MyHttpServletRequest;
import maf.web.mvc.beans.ResultMessage;
import miraenet.app.club.ClubManager;
import miraenet.app.club.beans.ClubBoardBean;
import miraenet.app.club.dao.ClubBoardDB;

/**
 * @author goindole
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
