/*
 * Created on 2006. 09. 27
 *
 * Copyright (c) 2004 (��)�̷��� All rights reserved.
 */
package maf.web.weblogger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class WebLogger {
	/**
	 * ���� ���ӽ� log ���� 
	 * @param req
	 */
	public void makeFirstHitLog(HttpServletRequest req) {
		HttpSession s = req.getSession();
		if( s!= null && s.isNew() ) {
			
		}
	}
}

