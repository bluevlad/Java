/*
 * Created on 2006. 10. 12
 *
 * Copyright (c) 2004 UBQ All rights reserved.
 */
package modules.xadmin.poll;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import maf.mdb.Mdb;
import maf.mdb.drivers.MdbDriver;
import maf.web.context.MafConfig;
import maf.web.mvc.beans.SiteInfo;
import maf.web.util.TokenLib;
import modules.community.poll.PollDB;



public class PollManager {
	public static Map getSitePollOne(HttpServletRequest req) {
		MdbDriver oDb = null;
		Map pollInfo = new HashMap();
		SiteInfo site =MafConfig.resolveSite(req);
		try {
			oDb = Mdb.getMdbDriver();
			Map tmpMap = PollDB.getCurrentPoll(oDb);
			if(tmpMap != null) {
				pollInfo.put("poll", tmpMap);
				pollInfo.put("items", PollDB.getPollItems(oDb, tmpMap));
				req.setAttribute("TOKEN", TokenLib.getToken(req));
			}
		} catch ( Exception e) {
			return java.util.Collections.EMPTY_MAP;
		} finally {
			if (oDb != null) try {
				oDb.close();
			} catch (Exception ex) {
			}
			oDb = null;
		}
		return pollInfo;
	}
}
