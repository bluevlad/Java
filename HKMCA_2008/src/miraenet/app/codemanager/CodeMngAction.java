/*
 * Created on 2006. 06. 14.
 *
 * Copyright (c) 2004 UBQ All rights reserved.
 */
package miraenet.app.codemanager;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import maf.MafConstant;
import maf.beans.NavigatorInfo;
import maf.beans.NavigatorOrderInfo;
import maf.exception.MafException;
import maf.mdb.sqlhelper.SqlWhereBuilder;
import maf.mdb.sqlhelper.Where;
import maf.web.context.MStoreFactory;
import maf.web.http.MyHttpServletRequest;
import maf.web.http.UrlAddress;
import maf.web.mvc.action.MafAction;
import maf.web.mvc.beans.ResultMessage;
import maf.web.util.SerializeHashtable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class CodeMngAction extends MafAction {
	private Log logger = LogFactory.getLog(this.getClass());
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
	 * list
	 * 
	 * @param _req
	 * @param _res
	 */
	public void cmdList(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {	

		final String[] searchFields = {"s_group_cd", "s_group_name"};
		
		NavigatorInfo navigator = new NavigatorInfo(_req, this.listOp, new NavigatorOrderInfo("a:group_cd"));
		Map param = this.listOp.getMergedParam(_req, searchFields);

		SqlWhereBuilder wb = oDb.getWhereBuilder();
		wb.addCond(Where.like("x.group_cd", ":s_group_cd"));
		wb.addCond(Where.like("x.group_name", ":s_group_name"));

		CodeMngDB.getList(super.oDb, navigator, param, wb.getWhereString(param));
		result.setAttribute("navigator", navigator);
		result.setForward("list");
	}	
	
	
	/**
	 * edit
	 * 
	 * @param _req
	 * @param _res
	 */
	public void cmdEdit(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		
		Map param = _req.getKeyValueMap();

		result.setAttribute("item", CodeMngDB.getGroupOne(super.oDb, param));
		result.setAttribute("list", CodeMngDB.getCode(super.oDb, param));
		result.setForward("edit");
	}
	
	/**
	 * add
	 * 
	 * @param _req
	 * @param _res
	 */
	public void cmdAdd(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {

		result.setForward("edit");
	}

	/**
	 * group insert
	 * 
	 * @param _req
	 * @param _res
	 */
	public void cmdGInsert(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		
		Map param = _req.getKeyValueMap();

		boolean chk = false;
		int rcnt = 0;
		try {
			oDb.setAutoCommit(false);
				rcnt = CodeMngDB.InsertGroupOne(super.oDb, param);
			oDb.commit();
			chk = true;
		} catch (Exception e) {
			oDb.rollback();
			logger.error(e.getMessage());
			rcnt = 0;
		} finally {
			oDb.setAutoCommit(true);
		}

		if (rcnt > 0) {
			MStoreFactory.getInstance().configure();
			UrlAddress next = new UrlAddress (super.controlAction);
			next.addParam("cmd","edit");
			next.addParam("group_cd", _req.getP("group_cd"));
			result.setSuccess(true, new ResultMessage(this.MESSAGE_BUNDLENAME, "insert.ok", new Integer(rcnt)));
			result.setNext(next);
		} else {
			result.setSuccess(false, new ResultMessage(this.MESSAGE_BUNDLENAME, "insert.fail"));
		}

	}
	
	/**
	 * insert
	 * 
	 * @param _req
	 * @param _res
	 */
	public void cmdInsert(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		
		Map param = _req.getKeyValueMap();

		boolean chk = false;
		int rcnt = 0;
		try {
			oDb.setAutoCommit(false);
				param.put("code_no", _req.getP("new_code_no"));
				rcnt = CodeMngDB.InsertCodeOne(super.oDb, param);
			oDb.commit();
			chk = true;
		} catch (Exception e) {
			oDb.rollback();
			logger.error(e.getMessage());
			rcnt = 0;
		} finally {
			oDb.setAutoCommit(true);
		}

		if (rcnt > 0) {
			MStoreFactory.getInstance().configure();
			result.setSuccess(true, new ResultMessage(this.MESSAGE_BUNDLENAME, "insert.ok", new Integer(rcnt)));
			UrlAddress next = new UrlAddress (super.controlAction);
			next.addParam("cmd","edit");
			next.addParam("group_cd", _req.getP("group_cd"));
			result.setNext(next);
		} else {
			result.setSuccess(false, new ResultMessage(this.MESSAGE_BUNDLENAME, "insert.fail"));
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
		boolean chk = false;
		int rcnt = 0;
		try {
			oDb.setAutoCommit(false);
			String[] item_ids = _req.getParameterValues("item_ids");
			String[] i_code_no = _req.getParameterValues("code_no");
			String code_nm = null;
			String seq = null;
			String del = null;

			rcnt = CodeMngDB.DeleteCode(super.oDb, param);
			
			if(i_code_no != null) {
				for(int i = 0; i < i_code_no.length; i ++ ) {
					code_nm = _req.getP("name_" + i_code_no[i]);
					seq = _req.getP("seq_" + i_code_no[i]);
					del = _req.getP("del_" + i_code_no[i]);
					param.put("code_no", i_code_no[i]);
					if(!"Y".equals(del)) {
						param.put("code_name" ,code_nm);
						param.put("seq", seq);
						CodeMngDB.InsertCodeOne(oDb, param);
					}
				}
			}
			rcnt = CodeMngDB.UpdateGroupOne(super.oDb, param);
			
			oDb.commit();
			chk = true;
		} catch (Exception e) {
			oDb.rollback();
			logger.error(e.getMessage());
			rcnt = 0;
		} finally {
			oDb.setAutoCommit(true);
		}
		
		if (chk) {
			MStoreFactory.getInstance().configure();
			UrlAddress next = new UrlAddress (super.controlAction);
			next.addParam("cmd", "edit");
			next.addParam("group_cd", param.get("group_cd"));
			result.setSuccess(true, new ResultMessage(this.MESSAGE_BUNDLENAME, "update.ok", new Integer(rcnt)));
			result.setNext(next);
		} else {
			result.setSuccess(false, new ResultMessage(this.MESSAGE_BUNDLENAME, "update.fail"));
		}
		
	}
	
	/**
	 * delete 
	 * 
	 * @param _req
	 * @param _res
	 * @throws MafException
	 */
	public void cmdDelete(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {

		Map param = _req.getKeyValueMap();
		boolean chk = false;
		int rcnt = 0;

		try {
			oDb.setAutoCommit(false);

			rcnt = CodeMngDB.DeleteCode(super.oDb, param);
			rcnt = CodeMngDB.DeleteGroupOne(super.oDb, param);
			
			oDb.commit();
			chk = true;
		} catch (Exception e) {
			oDb.rollback();
			logger.error(e.getMessage());
			rcnt = 0;
		} finally {
			oDb.setAutoCommit(true);
		}
		
        if(chk) {
            UrlAddress next = new UrlAddress (super.controlAction);
            next.addParam(MafConstant.LIST_OP_NAME, this.listOp.getSerializeUrl());
            result.setNext(next);
            result.setSuccess(true, new ResultMessage(this.MESSAGE_BUNDLENAME, "delete.ok", new Integer(rcnt)));
        } else {
            result.setSuccess(false,new ResultMessage(this.MESSAGE_BUNDLENAME, "delete.fail", new Integer(rcnt)));
        }
		
	}

}		