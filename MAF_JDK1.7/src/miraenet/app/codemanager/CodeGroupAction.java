/*
 * Created on 2006. 06. 14.
 *
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package miraenet.app.codemanager;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import maf.MafConstant;
import maf.beans.NavigatorInfo;
import maf.beans.NavigatorOrderInfo;
import maf.exception.MafException;
import maf.web.http.MyHttpServletRequest;
import maf.web.http.UrlAddress;
import maf.web.mvc.action.MafAction;
import maf.web.mvc.beans.ResultMessage;
import maf.web.util.SerializeHashtable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class CodeGroupAction extends MafAction {
	private Log logger = LogFactory.getLog(this.getClass());
	SerializeHashtable listOp = null;
	private final String MESSAGE_BUNDLENAME = "message";
	
    public void preProcess(MyHttpServletRequest _req, HttpServletResponse _res) 
	throws MafException {
		this.listOp = new SerializeHashtable( _req.getParameter(MafConstant.LIST_OP_NAME) );
	}
	public void postProcess(MyHttpServletRequest _req, HttpServletResponse _res) 
	throws MafException {
		result.setAttribute(MafConstant.LIST_OP_NAME, listOp);
	}	
	
	
	/**
	 * list
	 * 
	 * @param _req
	 * @param _res
	 */
	public void cmdList(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		
		final String[] searchFields = {"group_cd_dup", "group_name"};

		CodeGroupDB dao = CodeGroupDB.getInstance();
		NavigatorInfo navigator = new NavigatorInfo(_req, this.listOp, new NavigatorOrderInfo("A:group_name"));
		Map param = new HashMap();
		for(int i = 0 ;i < searchFields.length; i++) {
			if (_req.containsKey(searchFields[i])) {
				param.put(searchFields[i], _req.getParameter(searchFields[i]));
				listOp.put(searchFields[i], _req.getParameter(searchFields[i]));
			} else {
				param.put(searchFields[i], listOp.get(searchFields[i]));
			}
		}

		dao.getList(super.oDb, navigator, param, searchFields);	

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
		
		CodeGroupDB dao = CodeGroupDB.getInstance();
		Map param = new HashMap();
		
		param.put("group_cd", _req.getP("group_cd"));
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
		
		CodeGroupDB dao = CodeGroupDB.getInstance();
		Map param = new HashMap();
		
		param.put("group_cd", _req.getP("group_cd"));
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
		
		CodeGroupDB dao = CodeGroupDB.getInstance();
		Map param = _req.getKeyValueMap();

		int rcnt = dao.UpdateOne(super.oDb, param);
		
		if (rcnt > 0) {
			UrlAddress next = new UrlAddress (super.controlAction);
			next.addParam(MafConstant.LIST_OP_NAME, this.listOp.getSerializeUrl());

			result.setNext(next);
			result.setSuccess(true, new ResultMessage(this.MESSAGE_BUNDLENAME, "update.ok", new Integer(rcnt)));
		} else {
			result.setSuccess(false, new ResultMessage(this.MESSAGE_BUNDLENAME, "update.fail"));
		}
		
	}
		
	/**
	 * add
	 * 
	 * @param _req
	 * @param _res
	 */
	public void cmdAdd(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {

		result.setForward("add");
	}
	
	/**
	 * insert
	 * 
	 * @param _req
	 * @param _res
	 */
	public void cmdInsert(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		
		CodeGroupDB dao = CodeGroupDB.getInstance();
		Map param = _req.getKeyValueMap();

		int rcnt = dao.InsertOne(super.oDb, param);

		if (rcnt > 0) {
			result.setSuccess(true, new ResultMessage(this.MESSAGE_BUNDLENAME, "insert.ok", new Integer(rcnt)));
		} else {
			result.setSuccess(false, new ResultMessage(this.MESSAGE_BUNDLENAME, "insert.fail"));
		}

	}
	
	/**
	 * delete (single & multi)
	 * 
	 * @param _req
	 * @param _res
	 * @throws MafException
	 */
	public void cmdDelete(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {

		CodeGroupDB dao = CodeGroupDB.getInstance();
		String[] cd_chk = _req.getParameterValues("cd_chk");
		Map param = new HashMap();
		
		int rcnt = 0;
		try{
			oDb.setAutoCommit(false);
			for(int i = 0; i < cd_chk.length; i++) {
				param.put("group_cd", cd_chk[i]);
				rcnt += dao.Delete(super.oDb, param);
			}
			oDb.commit();
		} catch( Exception e) {
			rcnt = 0;
			oDb.rollback();
			logger.error(e.getMessage());
		} finally {
			oDb.setAutoCommit(true);
		}
		
		if (rcnt > 0) {
			result.setSuccess(true, new ResultMessage(this.MESSAGE_BUNDLENAME, "delete.ok", new Integer(rcnt)));
		} else {
			result.setSuccess(false, new ResultMessage(this.MESSAGE_BUNDLENAME, "delete.fail"));
		}
		
	}

} 
			