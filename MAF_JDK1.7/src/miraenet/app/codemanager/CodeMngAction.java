/*
 * Created on 2006. 06. 14.
 *
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package miraenet.app.codemanager;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import maf.exception.MafException;
import maf.web.http.MyHttpServletRequest;
import maf.web.http.UrlAddress;
import maf.web.mvc.action.MafAction;
import maf.web.mvc.beans.ResultMessage;
import miraenet.MiConfig;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class CodeMngAction extends MafAction {
	private Log logger = LogFactory.getLog(this.getClass());
	String site = null;
	private final String MESSAGE_BUNDLENAME = "message";
	
    public void preProcess(MyHttpServletRequest _req, HttpServletResponse _res) 
	throws MafException {
		this.site = _req.getP( "site", MiConfig.DEFAULT_SITE );
		_req.setAttribute( "site", site );
	}
	public void postProcess(MyHttpServletRequest _req, HttpServletResponse _res) 
	throws MafException {
	}	
	
	/**
	 * default
	 * 
	 * @param _req
	 * @param _res
	 * @throws MafException
	 */
	public void cmdDefault (MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		CodeMngDB dao = CodeMngDB.getInstance();
		//site
		result.setAttribute("slist", dao.getSiteList(super.oDb));
		result.setForward("default");
	}
	
	/**
	 * list
	 * 
	 * @param _req
	 * @param _res
	 */
	public void cmdList(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {	

		CodeMngDB dao = CodeMngDB.getInstance();

		//코드그룹 목록 
		result.setAttribute("glist", dao.getGroupList(super.oDb));	
		
		//코드 목록
		Map param = new HashMap();
		param.put("site", this.site);
		result.setAttribute("clist", dao.getCodeList(super.oDb, param));
		
		result.setForward("list");
		
	}	
	
	/**
	 * group add
	 * 
	 * @param _req
	 * @param _res
	 */
	public void cmdGAdd(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {

		result.setForward("edit");
	}

	/**
	 * group insert
	 * 
	 * @param _req
	 * @param _res
	 */
	public void cmdGInsert(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		
		CodeMngDB dao = CodeMngDB.getInstance();
		Map param = _req.getKeyValueMap();

		int rcnt = dao.InsertGroupOne(super.oDb, param);

		if (rcnt > 0) {
			result.setSuccess(true, new ResultMessage(this.MESSAGE_BUNDLENAME, "insert.ok", new Integer(rcnt)));
			result.setNext(this.controlAction + "?cmd=gadd&reload=T");
		} else {
			result.setSuccess(false, new ResultMessage(this.MESSAGE_BUNDLENAME, "insert.fail"));
		}

	}
	
	/**
	 * group edit
	 * 
	 * @param _req
	 * @param _res
	 */
	public void cmdGEdit(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		
		CodeMngDB dao = CodeMngDB.getInstance();
		Map param = new HashMap();
		
		param.put("group_cd", _req.getP("group_cd"));
		result.setAttribute("item", dao.getGroupOne(super.oDb, param));
		
		result.setForward("edit");
	}
	
	/**
	 * group update 
	 * 
	 * @param _req
	 * @param _res
	 */
	public void cmdGUpdate(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {		
		
		CodeMngDB dao = CodeMngDB.getInstance();
		Map param = _req.getKeyValueMap();

		int rcnt = dao.UpdateGroupOne(super.oDb, param);
		
		if (rcnt > 0) {
			result.setSuccess(true, new ResultMessage(this.MESSAGE_BUNDLENAME, "update.ok", new Integer(rcnt)));
			result.setNext(this.controlAction + "?cmd=gedit&reload=T&group_cd=" + param.get("group_cd"));
		} else {
			result.setSuccess(false, new ResultMessage(this.MESSAGE_BUNDLENAME, "update.fail"));
		}
		
	}
	
	/**
	 * group delete 
	 * 
	 * @param _req
	 * @param _res
	 * @throws MafException
	 */
	public void cmdGDelete(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {

		CodeMngDB dao = CodeMngDB.getInstance();
		Map param = _req.getKeyValueMap();
		
		int	rcnt = dao.DeleteGroupOne(super.oDb, param);
		
		if (rcnt > 0) {
			result.setSuccess(true, new ResultMessage(this.MESSAGE_BUNDLENAME, "delete.ok", new Integer(rcnt)));
			result.setNext(this.controlAction + "?cmd=gadd&reload=T");
		} else {
			result.setSuccess(false, new ResultMessage(this.MESSAGE_BUNDLENAME, "delete.fail"));
		}
		
	}

	/**
	 * add
	 * 
	 * @param _req
	 * @param _res
	 */
	public void cmdAdd(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		CodeMngDB dao = CodeMngDB.getInstance();
		//code group
		result.setAttribute("glist", dao.getGroupList(super.oDb));
		//site
		result.setAttribute("slist", dao.getSiteList(super.oDb));
		Map item = new HashMap();
		item.put("group_cd", _req.getP("ori_group_cd"));
		result.setAttribute("item", item);
		result.setForward("edit");
	}

	/**
	 * insert
	 * 
	 * @param _req
	 * @param _res
	 */
	public void cmdInsert(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		
		CodeMngDB dao = CodeMngDB.getInstance();
		Map param = _req.getKeyValueMap();

		int rcnt = dao.InsertCodeOne(super.oDb, param);

		if (rcnt > 0) {
			result.setSuccess(true, new ResultMessage(this.MESSAGE_BUNDLENAME, "insert.ok", new Integer(rcnt)));
			UrlAddress next = new UrlAddress (super.controlAction);
			next.addParam("cmd","add");
			next.addParam("reload","T");
			next.addParam("group_cd", _req.getP("group_cd"));
			next.addParam("ori_group_cd", _req.getP("group_cd"));
			result.setNext(next);
		} else {
			result.setSuccess(false, new ResultMessage(this.MESSAGE_BUNDLENAME, "insert.fail"));
		}

	}
	
	/**
	 * edit
	 * 
	 * @param _req
	 * @param _res
	 */
	public void cmdEdit(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		
		CodeMngDB dao = CodeMngDB.getInstance();
		//code group
		result.setAttribute("glist", dao.getGroupList(super.oDb));
		//site
		result.setAttribute("slist", dao.getSiteList(super.oDb));
		
		Map param = _req.getKeyValueMap();

		result.setAttribute("item", dao.getCodeOne(super.oDb, param));
		
		result.setForward("edit");
	}
	
	/**
	 * update 
	 * 
	 * @param _req
	 * @param _res
	 */
	public void cmdUpdate(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {		
		
		CodeMngDB dao = CodeMngDB.getInstance();
		Map param = _req.getKeyValueMap();

		int rcnt = dao.UpdateCodeOne(super.oDb, param);
		
		if (rcnt > 0) {
			result.setSuccess(true, new ResultMessage(this.MESSAGE_BUNDLENAME, "update.ok", new Integer(rcnt)));
			result.setNext(this.controlAction + "?cmd=edit&reload=T&group_cd="+param.get("group_cd")+"&site="+param.get("site")+"&code_no="+param.get("code_no"));
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

		CodeMngDB dao = CodeMngDB.getInstance();
		Map param = _req.getKeyValueMap();

		int	rcnt = dao.DeleteCodeOne(super.oDb, param);
	
		if (rcnt > 0) {
			result.setSuccess(true, new ResultMessage(this.MESSAGE_BUNDLENAME, "delete.ok", new Integer(rcnt)));
			result.setNext(this.controlAction + "?cmd=add&reload=T");
		} else {
			result.setSuccess(false, new ResultMessage(this.MESSAGE_BUNDLENAME, "delete.fail"));
		}
		
	}
	
} 
			