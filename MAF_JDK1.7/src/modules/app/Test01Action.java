/*
 * Created on 2006. 06. 14.
 *
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package modules.app;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import maf.MafConstant;
import maf.beans.NavigatorInfo;
import maf.exception.MafException;
import maf.mdb.sqlhelper.SqlWhereBuilder;
import maf.mdb.sqlhelper.Where;
import maf.web.http.MyHttpServletRequest;
import maf.web.mvc.action.MafAction;
import maf.web.mvc.beans.ResultMessage;
import maf.web.util.SerializeHashtable;
import modules.app._sample.Sample1DB;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;



public class Test01Action extends MafAction {
    final private Log  logger = LogFactory.getLog(Test01Action.class);
    SerializeHashtable listOp = null;
    private final String MESSAGE_BUNDLENAME = "common.message";
		
    public void preProcess(MyHttpServletRequest _req, HttpServletResponse _res) 
	throws MafException {
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
		
		final String[] searchFields = { "id",  "title",  "contents",  "reg_dt",  "stard_date",  "end_date",  "category",  "reg_nm", };

		
		NavigatorInfo navigator = new NavigatorInfo(_req, this.listOp);
	
		Map param = this.listOp.getMergedParam(_req, searchFields);
		
		SqlWhereBuilder wb = oDb.getWhereBuilder();
        wb.addCond(Where.like("title",":title"));
        
        Test01DB dao = Test01DB.getInstance();
		dao.getList(super.oDb, navigator, param, wb.getWhereString(param));
		result.setAttribute("navigator", navigator);
		result.setForward("list");
	}
	
	/**
	 * view
	 * 
	 * @param _req
	 * @param _res
	 */
	public void cmdView(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		Test01DB dao = Test01DB.getInstance();
		Map param = new HashMap();
		param.put("id", _req.getP("id"));
		
		
		
		
		
		
		
				
		result.setAttribute("item", dao.getOne(super.oDb, param));
		result.setForward("view");
	}
	
	/**
	 * edit form 
	 * 
	 * @param _req
	 * @param _res
	 */
	public void cmdEdit(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		Test01DB dao = Test01DB.getInstance();
		Map param = new HashMap();
		param.put("id", _req.getP("id"));
		
		
		
		
		
		
		
		
		result.setAttribute("item", dao.getOne(super.oDb, param));
		result.setForward("edit");
	}
	
	/**
	 * update
	 * 
	 * @param _req
	 * @param _res
	 */
	public void cmdUpdate(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {

		Test01DB dao = Test01DB.getInstance();
		Map param = _req.getKeyValueMap();

		param.put("id", _req.getP("id"));
		
		
		
		
		
		
		
				

		int rcnt= dao.updateOne(super.oDb, param);
		if(rcnt > 0 ) {
			result.setNext(super.controlAction);
			result.setSuccess(true, new ResultMessage(this.MESSAGE_BUNDLENAME, "message.update.ok", new Integer(rcnt)));
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
		Sample1DB dao = Sample1DB.getInstance();
		Map param = _req.getKeyValueMap();
		param.put("id", _req.getP("id"));
		
		
		
		
		
		
		
			
		int rcnt= dao.deleteOne(super.oDb, param);
		if(rcnt > 0 ) {
			result.setNext(super.controlAction);
			result.setSuccess(true,new ResultMessage(this.MESSAGE_BUNDLENAME, "message.delete.ok", new Integer(rcnt)));
		} else {
			result.setSuccess(false,new ResultMessage(this.MESSAGE_BUNDLENAME, "message.delete.fail"));
		}
	}		
	
	/**
	 * insert
	 * 
	 * @param _req
	 * @param _res
	 */
	public void cmdInsert(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {

		Test01DB dao = Test01DB.getInstance();
		Map param = _req.getKeyValueMap();

		int rcnt= dao.insertOne(super.oDb, param);
		if(rcnt > 0 ) {
			result.setNext(super.controlAction);
			result.setSuccess(true,new ResultMessage(this.MESSAGE_BUNDLENAME, "message.insert.ok", new Integer(rcnt)));
		} else {
			result.setSuccess(false,new ResultMessage(this.MESSAGE_BUNDLENAME, "message.insert.fail", new Integer(rcnt)));
		}
	}
	
	/**
	 * insert form
	 * 
	 * @param _req
	 * @param _res
	 */
	public void cmdAdd(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {

		result.setForward("add");
	}

} 
