/*
 * Created on 2005. 8. 26.
 *
 * Copyright (c) 2004 UBQ All rights reserved.
 */
package test;

import javax.servlet.http.HttpServletResponse;
import maf.exception.MafException;
import maf.web.http.MyHttpServletRequest;
import modules.common.actions.BaseCommonAction;

/**
 * @author bluevlad
 * 
 */
	public class Excel extends BaseCommonAction {
		public String doWork(MyHttpServletRequest request,
				HttpServletResponse response) throws MafException {
			return "default";
		}
}

