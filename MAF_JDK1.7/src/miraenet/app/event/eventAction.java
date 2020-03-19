/*
 * Created on 2006. 06. 14.
 *
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package miraenet.app.event;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import maf.MafConstant;
import maf.beans.NavigatorInfo;
import maf.exception.MafException;
import maf.mdb.sqlhelper.SqlWhereBuilder;
import maf.mdb.sqlhelper.Where;
import maf.web.http.MyHttpServletRequest;
import maf.web.mvc.action.MafAction;
import maf.web.util.SerializeHashtable;
import miraenet.MiConfig;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;



public class eventAction extends MafAction {
    final private Log  logger = LogFactory.getLog(eventAdmin.class);
    SerializeHashtable listOp = null;
    public void preProcess(MyHttpServletRequest _req, HttpServletResponse _res)

	throws MafException {
		this.listOp = new SerializeHashtable( _req.getParameter(MafConstant.LIST_OP_NAME) );
	}
	public void postProcess(MyHttpServletRequest _req, HttpServletResponse _res) 
	throws MafException {
		result.setAttribute(MafConstant.LIST_OP_NAME, listOp);
		result.setAttribute("filepath", _req.getContextPath()+MiConfig.DEFAULT_EVENT_FILE_PATH);
	}	

	/**
	 * list
	 * 
	 * @param _req
	 * @param _res
	 */
	public void cmdDefault(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {	

		final String[] searchFields = {"evt_title", "evt_contents"};

		NavigatorInfo navigator = new NavigatorInfo(_req, this.listOp);
		Map param = this.listOp.getMergedParam(_req, searchFields);
		
		SqlWhereBuilder wb = oDb.getWhereBuilder();

		wb.addCond(Where.like("x.evt_title", ":evt_title"));
		wb.addCond(Where.like("x.evt_contents", ":evt_contents"));

		eventDB.getList(super.oDb, navigator, param, wb.getWhereString(param));

		result.setAttribute("navigator", navigator);
		result.setForward("default");
	}
		
	/**
	 * edit
	 * 
	 * @param _req
	 * @param _res
	 */
	public void cmdView(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		Map param = _req.getKeyValueMap();

		result.setAttribute("item", eventDB.getOne(super.oDb, param));
		result.setForward("view");
	}

} 
		