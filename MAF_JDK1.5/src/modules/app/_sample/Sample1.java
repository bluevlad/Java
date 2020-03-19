/*
 * Created on 2006. 4. 6.
 *
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package modules.app._sample;

import java.util.HashMap;
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

public class Sample1 extends MafAction {
	private Log logger = LogFactory.getLog(this.getClass());
	SerializeHashtable listOp = null;
    private final String MESSAGE_BUNDLENAME = "sample";
    
	public void preProcess(MyHttpServletRequest _req, HttpServletResponse _res) 
	throws MafException {
		this.listOp = new SerializeHashtable( _req.getParameter(MafConstant.LIST_OP_NAME) );
//		logger.debug(Test.getSql());
	}
	
	public void postProcess(MyHttpServletRequest _req, HttpServletResponse _res) 
	throws MafException {
		result.setAttribute(MafConstant.LIST_OP_NAME, listOp);
	}	
	
	/**
	 * view
	 * 
	 * @param _req
	 * @param _res
	 */
	public void cmdView(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		Sample1DB dao = Sample1DB.getInstance();
		Map param = new HashMap();
		param.put("id", _req.getP("id"));
		result.setAttribute("item", dao.getOne(super.oDb, param));
		result.setForward("view");
	}
	
	/**
	 * edit
	 * 
	 * @param _req
	 * @param _res
	 */
	public void cmdEdit(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		Sample1DB dao = Sample1DB.getInstance();
		Map param = new HashMap();
		param.put("id", _req.getP("id"));
		
		result.setAttribute("item", dao.getOne(super.oDb, param));
		result.setForward("edit");
	}
	
	/**
	 * edit
	 * 
	 * @param _req
	 * @param _res
	 */
	public void cmdAdd(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {

		result.setForward("edit");
	}	
	/**
	 * update
	 * 
	 * @param _req
	 * @param _res
	 */
	public void cmdUpdate(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		Sample1DB dao = Sample1DB.getInstance();
		Map param = _req.getKeyValueMap();
		param.put("id", _req.getP("id"));
		int rcnt= dao.updateOne(super.oDb, param);
		if(rcnt > 0 ) {
			result.setNext(super.controlAction);
			result.setSuccess(true, new ResultMessage(this.MESSAGE_BUNDLENAME, "message.update.ok", new Integer(rcnt)));
		} else {
			result.setSuccess(false,new ResultMessage(this.MESSAGE_BUNDLENAME, "message.update.fail"));
		}
	}
	public void cmdInsert(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		Sample1DB dao = Sample1DB.getInstance();
		Map param = _req.getKeyValueMap();

		int rcnt= dao.insertOne(super.oDb, param);
		if(rcnt > 0 ) {
			result.setNext(super.controlAction);
			result.setSuccess(true,new ResultMessage(this.MESSAGE_BUNDLENAME, "message.insert.ok", new Integer(rcnt)));
		} else {
			result.setSuccess(false,new ResultMessage(this.MESSAGE_BUNDLENAME, "message.insert.fail"));
		}
	}
	public void cmdDelete(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		Sample1DB dao = Sample1DB.getInstance();
		Map param = _req.getKeyValueMap();

		int rcnt= dao.deleteOne(super.oDb, param);
		if(rcnt > 0 ) {
			UrlAddress next = new UrlAddress (super.controlAction);
			next.addParam(MafConstant.LIST_OP_NAME, this.listOp.getSerializeUrl());
			result.setNext(next);
			result.setSuccess(true,new ResultMessage(this.MESSAGE_BUNDLENAME, "message.delete.ok", new Integer(rcnt)));
		} else {
			result.setSuccess(false,new ResultMessage(this.MESSAGE_BUNDLENAME, "message.delete.fail"));
		}
	}	
	/**
	 * 목록 보기
	 * 
	 * @param _req
	 * @param _res
	 */
	public void cmdList(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		final String[] searchFields = {"title","category","search_type","search_txt","start_dt","end_dt"};

		
	
		NavigatorInfo navigator = new NavigatorInfo(_req, this.listOp);
	
		Map param = this.listOp.getMergedParam(_req, searchFields);
		
		SqlWhereBuilder wb = oDb.getWhereBuilder();
		wb.addCond(Where.like("title",":title"));
		wb.addCond(Where.like("category",":category"));
		wb.addCond(Where.like(_req.getP("search_type"),":search_txt"));
		wb.addCond(Where.between("reg_dt","to_date(:start_dt,'YYYY-MM-DD')","to_date(:end_dt,'YYYY-MM-DD')"));
		Sample1DB dao = Sample1DB.getInstance();
		dao.getList(super.oDb, navigator, param, wb.getWhereString(param));
		result.setAttribute("navigator", navigator);
		result.setForward("list");

	}

}
