/*
 * Created on 2005. 12. 5.
 * 게시판  데이타 관리 
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package miraenet.app.mboard.actions;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import maf.exception.MafException;
import maf.web.http.MyHttpServletRequest;
import miraenet.app.mboard.dao.AdminDataDB;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class AdminData extends MBoardAdminAction {

    private  Log logger = LogFactory.getLog(this.getClass());

	public  void doWork(MyHttpServletRequest _req, HttpServletResponse response) throws MafException{
		String cmd = _req.getP("cmd", "default");
		try {
			if ("tree".equals(cmd)) {
				String dur = _req.getP("dur", "1"); 
				List menu = AdminDataDB.getBoardList(super.oDb, dur);
				result.setAttribute("menus", menu);
				result.setForward("tree");
			} else if ("head".equals(cmd)) {
				result.setForward("head");

			} else if ("blank".equals(cmd)) {
				result.setForward("blank");

			} else if ("default".equals(cmd)) {
				result.setForward("default");
			}
		} catch (Exception e) {
			result.setError(e);
		}

	}
}
