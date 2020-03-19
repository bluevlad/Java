/*
 * Created on 2005. 11. 1.
 *
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package modules.common.actions;

import javax.servlet.http.HttpServletResponse;

import maf.exception.MafException;
import maf.web.http.MyHttpServletRequest;
import maf.web.mvc.action.MafAction;

public class SiteMap extends MafAction {
	public void doWork(MyHttpServletRequest _req,
			HttpServletResponse response)throws MafException{

        result.setForward("default");
	}

}

