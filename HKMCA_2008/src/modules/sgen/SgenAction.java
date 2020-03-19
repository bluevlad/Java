/*
 * Created on 2006. 06. 14.
 *
 * Copyright (c) 2004 UBQ All rights reserved.
 */
package modules.sgen;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;



import maf.MafConstant;
import maf.beans.NavigatorInfo;
import maf.exception.MafException;
import maf.web.http.MyHttpServletRequest;
import maf.web.mvc.action.MafAction;
import maf.web.util.SerializeHashtable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SgenAction extends MafAction {
	private Log logger = LogFactory.getLog(this.getClass());
	SerializeHashtable listOp = null;
    
	public void preProcess(MyHttpServletRequest _req, HttpServletResponse _res) 
	throws MafException {
		this.listOp = new SerializeHashtable( _req.getParameter(MafConstant.LIST_OP_NAME) );
	}
	
	public void postProcess(MyHttpServletRequest _req, HttpServletResponse _res) 
	throws MafException {
		result.setAttribute(MafConstant.LIST_OP_NAME, listOp);
	}	
	
	
 
	/**
	 * cmdtable
	 * 
	 * @param _req
	 * @param _res
	 */
	public void cmdTablelist (MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		
		final String[] searchFields = {"owner","table_name"};   

		NavigatorInfo navigator = new NavigatorInfo(_req, this.listOp);
		Map param = new HashMap();
		for(int i = 0 ;i < searchFields.length; i++) { 
			if (_req.containsKey(searchFields[i])) {
				param.put(searchFields[i], _req.getParameter(searchFields[i]));
				listOp.put(searchFields[i], _req.getParameter(searchFields[i]));
			} else {
				param.put(searchFields[i], listOp.get(searchFields[i]));
			}
		}
		if ("".equals(param.get("owner"))){
			param.put("owner","ehrd");
		}
		String cmd = _req.getP(MafConstant.CMD_NAME);
		SgenDB.getColumnInfo(super.oDb, navigator, param);
		result.setAttribute("navigator", navigator);
		
		if ("tablelist".equals(cmd)){
			result.setForward("tablelist");
		} 
		else if ("dao".equals(cmd)){
			result.setForward("dao");
		}
		else if ("xml".equals(cmd)){
			result.setForward("xml");
		}
		else if ("style".equals(cmd)){
			result.setForward("style");
		}
		else if ("add".equals(cmd)){
			result.setForward("add");
		}
		else if ("edit".equals(cmd)){
			result.setForward("edit");
		}
		else if ("list".equals(cmd)){
			result.setForward("list");
		}
		else if ("view".equals(cmd)){
			result.setForward("view");
		}
	}

}
