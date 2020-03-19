/*
 * Created on 2006. 06. 14.
 *
 * Copyright (c) 2004 UBQ All rights reserved.
 */
package modules.xadmin.member;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import maf.MafConstant;
import maf.MafProperties;
import maf.aam.AAMLoader;
import maf.beans.NavigatorInfo;
import maf.exception.MafException;
import maf.mdb.sqlhelper.SqlWhereBuilder;
import maf.mdb.sqlhelper.Where;
import maf.menu.TreeMenu;
import maf.web.http.MyHttpServletRequest;
import maf.web.http.UrlAddress;
import maf.web.mvc.action.MafAction;
import maf.web.mvc.beans.ResultMessage;
import maf.web.util.SerializeHashtable;
import miraenet.app.siteManager.MafSiteDB;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class AuthAction extends MafAction {
    final private Log  logger = LogFactory.getLog(AuthAction.class);
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
		
		final String[] searchFields = { };
		
		NavigatorInfo navigator = new NavigatorInfo(_req, this.listOp);
		Map param = this.listOp.getMergedParam(_req, searchFields);
		
		SqlWhereBuilder wb = oDb.getWhereBuilder();

		AuthDB.getList(super.oDb, navigator, param, wb.getWhereString(param));
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

		Map param = _req.getKeyValueMap();

		result.setAttribute("sites", MafSiteDB.getList(super.oDb, param));
		result.setForward("edit");
	}

	/**
	 * edit form 
	 * 
	 * @param _req
	 * @param _res
	 */
	public void cmdEdit(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {

		final String[] searchFields = {"nm", "userid"};
		
		NavigatorInfo navigator = new NavigatorInfo(_req, this.listOp);

		Map param = this.listOp.getMergedParam(_req, searchFields);
		param.put("role_id", _req.getP("role_id",  this.listOp.get("role_id")));

		SqlWhereBuilder wb = oDb.getWhereBuilder();
        wb.addCond(Where.like("nm", ":nm"));
        wb.addCond(Where.like("userid", ":userid"));        

		AuthDB.getNotRoleList(super.oDb, navigator, param, wb.getWhereString(param));

		result.setAttribute("navigator", navigator);  //사용자 리스트
		result.setAttribute("item", AuthDB.getOne(super.oDb, param));
		result.setAttribute("list", AuthDB.getUserRoleList(super.oDb, param));
		result.setAttribute("sites", MafSiteDB.getList(super.oDb, param));
		result.setAttribute("role_id", param.get("role_id"));
		result.setForward("edit");
	}
	
	/**
	 * update
	 * 
	 * @param _req
	 * @param _res
	 */
	public void cmdUpdate(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {

		Map param = _req.getKeyValueMap();
    	String[] vusn = _req.getParameterValues("vusn");
		boolean chk = false;
		int rcnt= 0;
		try {
			oDb.setAutoCommit(false);
			rcnt += AuthDB.updateOne(super.oDb, param);
           	AuthDB.deleteRole(oDb, param);
            if(null != vusn) {
                for(int i=0; i < vusn.length; i++) {
               		param.put("usn", vusn[i]);
                   	AuthDB.insertRole(oDb, param);
                }
               	rcnt = vusn.length;
            }
			
			oDb.commit();
			AuthReload();
			chk = true;
		} catch ( Exception e) {
			oDb.rollback();
			logger.error(e.getMessage());
			rcnt = 0;
		} finally {
			oDb.setAutoCommit(true);
		}

		if(chk ) {
			UrlAddress next = new UrlAddress (super.controlAction);
			next.addParam(MafConstant.LIST_OP_NAME, this.listOp.getSerializeUrl());
			next.addParam("role_id", param.get("role_id"));
			next.addParam("cmd", "edit");
			result.setNext(next);
			result.setSuccess(true, new ResultMessage(this.MESSAGE_BUNDLENAME, "update.ok", new Integer(rcnt)));
		} else {
			result.setSuccess(false,new ResultMessage(this.MESSAGE_BUNDLENAME, "update.fail", new Integer(rcnt)));
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
           	AuthDB.deleteRole(oDb, param);
			rcnt += AuthDB.deleteOne(super.oDb, param);
			oDb.commit();
			AuthReload();
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
			result.setSuccess(true,new ResultMessage(this.MESSAGE_BUNDLENAME, "delete.ok", new Integer(rcnt)));
		} else {
			result.setSuccess(false,new ResultMessage(this.MESSAGE_BUNDLENAME, "delete.fail"));
		}
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
		
		try {
			oDb.setAutoCommit(false);
			rcnt += AuthDB.insertOne(super.oDb, param);
			oDb.commit();
			AuthReload();
		} catch ( Exception e) {
			oDb.rollback();
			logger.error(e.getMessage());
			rcnt = 0;
		} finally {
			oDb.setAutoCommit(true);
		}
		
		if(rcnt > 0 ) {
			UrlAddress next = new UrlAddress (super.controlAction);
			next.addParam("cmd", "edit");
			next.addParam("role_id", param.get("role_id").toString());
			next.addParam(MafConstant.LIST_OP_NAME, this.listOp.getSerializeUrl());
			result.setNext(next);
			result.setSuccess(true,new ResultMessage(this.MESSAGE_BUNDLENAME, "insert.ok", new Integer(rcnt)));
		} else {
			result.setSuccess(false,new ResultMessage(this.MESSAGE_BUNDLENAME, "insert.fail", new Integer(rcnt)));
		}
	}
	
	private void AuthReload() {
		AAMLoader aaml =MafProperties.getAAMLoader();
	    aaml.reload();
	   
	    TreeMenu oTM = TreeMenu.getInstance("www");
        oTM.DataReset();
	}
} 
		