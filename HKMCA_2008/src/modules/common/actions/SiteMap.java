/*
 * Created on 2005. 11. 1.
 *
 * Copyright (c) 2004 UBQ All rights reserved.
 */
package modules.common.actions;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import maf.MafProperties;
import maf.exception.MafException;
import maf.web.http.MyHttpServletRequest;
import maf.web.mvc.action.MafAction;
import maf.web.util.SerializeHashtable;
import miraenet.app.siteManager.MstMenuDB;

public class SiteMap extends MafAction {
    SerializeHashtable listOp = null;
	String site = null;

    public void preProcess(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
    	this.site = _req.getP("site", MafProperties.DEFAULT_SITE );
    }

    public void cmdDefault(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {

		Map param = new HashMap();
		param.put("site", site);
        param.put("lang", super.locale.getLanguage());

		param.put("lvl", "2");
		result.setAttribute("item", MstMenuDB.getSiteMap(oDb, param));
		param.put("lvl", "3");
		result.setAttribute("sub", MstMenuDB.getSiteMap(oDb, param));
		param.put("lvl", "4");
		result.setAttribute("last", MstMenuDB.getSiteMap(oDb, param));
        result.setForward("default");
    }
}