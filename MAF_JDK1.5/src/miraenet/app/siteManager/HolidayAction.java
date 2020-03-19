/*
 * Created on 2006. 06. 14.
 *
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package miraenet.app.siteManager;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import maf.MafConstant;
import maf.beans.NavigatorInfo;
import maf.exception.MafException;
import maf.mdb.sqlhelper.SqlWhereBuilder;
import maf.mdb.sqlhelper.Where;
import maf.web.http.MyHttpServletRequest;
import maf.web.http.UrlAddress;
import maf.web.mvc.action.MafAction;
import maf.web.mvc.beans.ResultMessage;
import maf.web.util.SerializeHashtable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
* 
*
*
*
*/
public class HolidayAction extends MafAction {
    final private Log  logger = LogFactory.getLog(HolidayAction.class);
    SerializeHashtable listOp = null;
    private final String MESSAGE_BUNDLENAME = "common.message";
		
    public void preProcess(MyHttpServletRequest _req, HttpServletResponse _res) 
	throws MafException {
       	chkLogin();
		this.listOp = new SerializeHashtable( _req.getParameter(MafConstant.LIST_OP_NAME) );
	}
	public void postProcess(MyHttpServletRequest _req, HttpServletResponse _res) 
	throws MafException {
		result.setAttribute(MafConstant.LIST_OP_NAME, listOp);
	}	
	
	
	/**
	 * 목록 가져오기
	 * 
	 * @param _req
	 * @param _res
	 */
	public void cmdList(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		
		final String[] searchFields = {"symd", "eymd", "bigo"};
		
		NavigatorInfo navigator = new NavigatorInfo(_req, this.listOp);
	
		Map param = this.listOp.getMergedParam(_req, searchFields);
		
		SqlWhereBuilder wb = oDb.getWhereBuilder();
         
    	wb.addCond(Where.between("ymd2",":symd",":eymd"));     
    	wb.addCond(Where.like("bigo",":bigo")); 

    	HolidayDB.getList(super.oDb, navigator, param, wb.getWhereString(param));
		result.setAttribute("navigator", navigator);
		result.setForward("list");
	}
	
	/**
	 * insert form
	 * 
	 * @param _req
	 * @param _res
	 */
	public void cmdAdd(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {

		result.setForward("edit");
	}

	/**
	 * edit form 
	 * 
	 * @param _req
	 * @param _res
	 */
	public void cmdEdit(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		Map param = _req.getKeyValueMap();

		result.setAttribute("item", HolidayDB.getOne(super.oDb, param));
		result.setForward("edit");
	}
	
	/**
	 * insert
	 * 
	 * @param _req
	 * @param _res
	 */
	public void cmdInsert(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {

		int rcnt = 0;
		Map param = _req.getKeyValueMap();
		String ymd2 = param.get("ymd2").toString();
		param.put("ymd", maf.util.StringUtils.replace(ymd2, "-", ""));
		
    	try {
			oDb.setAutoCommit(false);
			rcnt += HolidayDB.insertOne(super.oDb, param);
			oDb.commit();
		} catch ( Exception e) {
			oDb.rollback();
			logger.error(e.getMessage());
			rcnt = 0;
		} finally {
			oDb.setAutoCommit(true);
		}
		
		if(rcnt > 0 ) {
			UrlAddress next = new UrlAddress (super.controlAction);
			next.addParam(MafConstant.LIST_OP_NAME, this.listOp.getSerializeUrl());
			result.setNext(next);
			result.setSuccess(true,new ResultMessage(this.MESSAGE_BUNDLENAME, "message.insert.ok", new Integer(rcnt)));
		} else {
			result.setSuccess(false,new ResultMessage(this.MESSAGE_BUNDLENAME, "message.insert.fail", new Integer(rcnt)));
		}
	}
	
	/**
	 * update
	 * 
	 * @param _req
	 * @param _res
	 */
	public void cmdUpdate(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {

		Map param = _req.getKeyValueMap();

		int rcnt= 0;
		try {
			oDb.setAutoCommit(false);
			rcnt += HolidayDB.updateOne(super.oDb, param);
			oDb.commit();
		} catch ( Exception e) {
			oDb.rollback();
			logger.error(e.getMessage());
			rcnt = 0;
		} finally {
			oDb.setAutoCommit(true);
		}

		if(rcnt > 0 ) {
			result.setSuccess(true, new ResultMessage(this.MESSAGE_BUNDLENAME, "message.update.ok", new Integer(rcnt)));
			UrlAddress next = new UrlAddress (super.controlAction);
			next.addParam("cmd", "list");
			next.addParam("ymd", param.get("ymd").toString());
			next.addParam(MafConstant.LIST_OP_NAME, this.listOp.getSerializeUrl());
			result.setNext(next);

		} else {
			result.setSuccess(false,new ResultMessage(this.MESSAGE_BUNDLENAME, "message.update.fail", new Integer(rcnt)));
		}
	}
	/**
	 * delete
	 * 
	 * @param _req
	 * @param _res
	 */	
	public void cmdDelete(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {

		Map param = _req.getKeyValueMap();
		
		int rcnt= 0;
		try {
			oDb.setAutoCommit(false);
//			rcnt += HolidayDB.deleteOne(super.oDb, param);
			oDb.commit();
		} catch ( Exception e) {
			oDb.rollback();
			logger.error(e.getMessage());
			rcnt = 0;
		} finally {
			oDb.setAutoCommit(true);
		}
		if(rcnt > 0 ) {
			UrlAddress next = new UrlAddress (super.controlAction);
			next.addParam(MafConstant.LIST_OP_NAME, this.listOp.getSerializeUrl());
			result.setNext(next);
			result.setSuccess(true,new ResultMessage(this.MESSAGE_BUNDLENAME, "message.delete.ok", new Integer(rcnt)));
		} else {
			result.setSuccess(false,new ResultMessage(this.MESSAGE_BUNDLENAME, "message.delete.fail"));
		}
	}		
	
} 
		