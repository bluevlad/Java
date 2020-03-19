/*
 * Created on 2006. 6. 28.
 *
 * Copyright (c) 2004 UBQ All rights reserved.
 */
package modules.intranet;

import javax.servlet.http.HttpServletResponse;

import maf.exception.MafException;
import maf.menu.MenuItem;
import maf.menu.TreeMenu;
import maf.web.http.MyHttpServletRequest;
import maf.web.mvc.action.MafAction;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class intranetAction extends MafAction {
    final private Log  logger = LogFactory.getLog(this.getClass());
	public void preProcess(MyHttpServletRequest _req, HttpServletResponse _res) {

	}
	public void cmdIndex(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {

		result.setForward("index");
	}

	public void cmdTopMenu(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
	    TreeMenu oTM = TreeMenu.getInstance(super.siteInfo);
		MenuItem topMenu = oTM.getMenu("HOME");
		MenuItem[] topMenus = oTM.getChilds(topMenu, super.sessionBean);
		_req.setAttribute("topMenus", topMenus );
//		logger.debug("menu size :"+ topMenus.length);
		result.setCache(false);
		result.setForward("top");
	}

	public void cmdLeftMenu(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
	    TreeMenu oTM = TreeMenu.getInstance(super.siteInfo);
		String pgid = _req.getP("pgid");
		

		result.setAttribute("TreeMenu", oTM);
		result.setAttribute("pgid", pgid);
		result.setForward("left");
	}
}
