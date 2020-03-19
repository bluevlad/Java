/*
 * Created on 2005. 6. 29.
 *
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package miraenet.app.dbsync;

import javax.servlet.http.HttpServletResponse;

import maf.exception.MafException;
import maf.web.http.MyHttpServletRequest;
import xadmin.common.actions.AdminAction;

/**
 * @author goindole
 *
 */
public class ShowConfig extends AdminAction {
	public String doWork(MyHttpServletRequest _req,
			HttpServletResponse response) throws MafException {
		String cmd = _req.getP("cmd");
		if("start".equals(cmd)) {
			DBSyncManager.startSync();
		} else if ("stop".equals(cmd)) {
			DBSyncManager.stopSync();
		}
		_req.setAttribute("info", DBSyncManager.getSyncConfig());
		_req.setAttribute("syncstatus", Boolean.valueOf(DBSyncManager.syncStatus()));
		return "default";
	}
}

