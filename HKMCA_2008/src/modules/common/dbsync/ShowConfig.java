/*
 * Created on 2005. 6. 29.
 *
 * Copyright (c) 2004 UBQ All rights reserved.
 */
package modules.common.dbsync;

import javax.servlet.http.HttpServletResponse;

import maf.exception.MafException;
import maf.web.http.MyHttpServletRequest;
import maf.web.mvc.action.MafAction;

/**
 * @author UBQ
 *
 */
public class ShowConfig extends MafAction {
	public void cmdDefault(MyHttpServletRequest _req, HttpServletResponse _res)
	        throws MafException {
		String cmd = _req.getP("cmd");
		if ("start".equals(cmd)) {
			DBSyncManager.startSync();
		} else if ("stop".equals(cmd)) {
			DBSyncManager.stopSync();
		}
	}

	public void cmdStart(MyHttpServletRequest _req, HttpServletResponse _res)
	        throws MafException {
		DBSyncManager.startSync();
	}

	public void cmdStop(MyHttpServletRequest _req, HttpServletResponse _res)
	        throws MafException {
		DBSyncManager.stopSync();
	}

	public void postProcess(MyHttpServletRequest _req, HttpServletResponse _res)
	        throws MafException {
		_req.setAttribute("info", DBSyncManager.getSyncConfig());
		_req.setAttribute("syncstatus", Boolean.valueOf(DBSyncManager.syncStatus()));
		result.setForward("default");;
	}
}
