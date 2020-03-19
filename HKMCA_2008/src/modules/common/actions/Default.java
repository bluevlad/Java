/*
 * Default.java, @ 2005-03-29
 * 
 * Copyright (c) 2004 UBQ All rights reserved.
 */
package modules.common.actions;

import javax.servlet.http.HttpServletResponse;

import maf.exception.MafException;
import maf.web.http.MyHttpServletRequest;


/**
 * @author bluevlad
 *
 */
public class Default extends BaseCommonAction{
	public String doWork(MyHttpServletRequest _req,
			HttpServletResponse _res) throws MafException {

		return "default";
	}
}
