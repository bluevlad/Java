/*
 * Created on 2005. 8. 26.
 *
 * Copyright (c) 2004 (��)�̷��� All rights reserved.
 */
package test;

import javax.servlet.http.HttpServletResponse;

import maf.exception.MafException;
import maf.web.http.MyHttpServletRequest;
import modules.common.actions.CommonAction;

/**
 * @author goindole
 * 
 */
	public class Excel extends CommonAction {
		public String doWork(MyHttpServletRequest request,
				HttpServletResponse response) throws MafException {
			return "default";
		}
}

