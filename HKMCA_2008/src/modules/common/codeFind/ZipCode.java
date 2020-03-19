/*
 * Created on 2006. 06. 13.
 *
 * Copyright (c) 2004 UBQ All rights reserved.
 */
package modules.common.codeFind;

import javax.servlet.http.HttpServletResponse;
import maf.exception.MafException;
import maf.web.http.MyHttpServletRequest;
import maf.web.mvc.action.MafAction;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ZipCode extends MafAction {
	private Log logger = LogFactory.getLog(this.getClass());
    
	public void preProcess(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
	}
	public void postProcess(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
	}
	
	/**
	 * view
	 * 
	 * @param _req
	 * @param _res
	 */
	public void cmdView(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		result.setForward("view");
	}
}
