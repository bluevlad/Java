/*
 * Default.java, @ 2005-03-29
 * 
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package modules.common.actions;

import javax.servlet.http.HttpServletResponse;

import maf.exception.MafException;
import maf.web.http.MyHttpServletRequest;

/**
 * @author goindole
 *
 */
public class Default extends CommonAction{
	public String doWork(MyHttpServletRequest _req,
			HttpServletResponse _res) throws MafException {

		return "default";
	}
}
