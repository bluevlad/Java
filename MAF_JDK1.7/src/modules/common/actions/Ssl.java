/*
 * Created on 2005. 8. 11.
 *
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package modules.common.actions;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import maf.exception.MafException;
import maf.web.http.MyHttpServletRequest;

/**
 * @author goindole
 *
 */
public class Ssl extends CommonAction {
	public String doWork(MyHttpServletRequest _req,
			HttpServletResponse _res) throws MafException {
		String forward = "loginok";
		HttpSession session = _req.getSession();
		String sid = session.getId();
		
		_req.setAttribute("sid", sid);
		_req.setAttribute("refCK", "f");
		return forward;
	}
}

