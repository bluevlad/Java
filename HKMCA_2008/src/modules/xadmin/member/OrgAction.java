/*
 * menu_admin.java, @ 2005-04-22
 * 
 * Copyright (c) 2004  UBQ All rights reserved.
 */
package modules.xadmin.member;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;

import maf.exception.MafException;
import maf.web.http.MyHttpServletRequest;
import maf.web.http.UrlAddress;
import maf.web.mvc.action.MafAction;
import maf.web.mvc.beans.ResultMessage;
import maf.web.session.exception.SessionInvalidatedException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author bluevlad
 *  
 */
public class OrgAction extends MafAction {
	private Log logger = LogFactory.getLog(OrgAction.class);
	private final String MESSAGE_BUNDLENAME = "message";

	public void preProcess(MyHttpServletRequest _req, HttpServletResponse _res)
	throws MafException {
		if(super.sessionBean == null) {
			throw new SessionInvalidatedException();
		}
	}

	/**
	 * default page
	 * @param _req
	 * @param _res
	 * @throws MafException
	 */
	public void cmdDefault(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {

		result.setForward("default");
	}
	
	/**
	 * �޴� Tree �����ֱ� 
	 * @param _req
	 * @param _res
	 * @throws MafException
	 */
	public void cmdTree(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		
		Map param = new HashMap();
        result.setAttribute("org", OrgDB.getList(oDb, param ));
        result.setForward("tree");
	}	
	
	/**
	 * �޴� ���� �� 
	 * @param _req
	 * @param _res
	 * @throws MafException
	 */
	public void cmdEdit(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		Map param = new HashMap();
        String org_cd = _req.getP("org_cd");
        param.put("org_cd", org_cd);
        _req.setAttribute( "item",  OrgDB.getOne(oDb, param));
        result.setForward("edit");
	}	
	
	/**
	 * �޴����� ���� db update
	 * @param _req
	 * @param _res
	 * @throws MafException
	 */
	public void cmdUpdate(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		Map param = _req.getKeyValueMap();

		int retValue = 0;
        retValue = OrgDB.updateOrg( oDb, param);
        if (retValue > 0) {
			UrlAddress next = new UrlAddress (super.controlAction);
			next.addParam("cmd","edit");
			next.addParam("reload","T");
			next.addParam("org_cd",param.get("org_cd"));
			result.setSuccess(true, new ResultMessage(this.MESSAGE_BUNDLENAME, "update.ok", new Integer(retValue)));
			result.setNext(next);
        } else {

            result.setSuccess(false, new ResultMessage("message","update.fail"));
        }		

	}
	/**
	 * �޴� ��� db insert
	 * @param _req
	 * @param _res
	 * @throws MafException
	 */
	public void cmdInsert(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		int retValue = 0;
		Map param = _req.getKeyValueMap();

		boolean chk = false;
		try {
			oDb.setAutoCommit(false);
			retValue+= OrgDB.insertOrg(super.oDb, param);
			oDb.commit();
			chk = true;
		} catch (Exception e) {
			oDb.rollback();
			logger.error(e.getMessage());
		} finally {
			oDb.setAutoCommit(true);
		}
		
		if (chk) {
			UrlAddress next = new UrlAddress (super.controlAction);
			next.addParam("cmd", "edit");
			next.addParam("reload", "T");
			next.addParam("org_cd", param.get("org_cd"));
			result.setSuccess(true, new ResultMessage(this.MESSAGE_BUNDLENAME, "insert.ok", new Integer(retValue)));
			result.setNext(next);
        } else {
            result.setSuccess(false, new ResultMessage("message","insert.fail"));
        }
	}	
	
	/**
	 * �ϳ��� �޴� ���� 
	 * @param _req
	 * @param _res
	 * @throws MafException
	 */
	public void cmdDelete(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		Map param = _req.getKeyValueMap();

		int retValue = 0;

        retValue= OrgDB.deleteOrg( oDb, param);
        if (retValue > 0) {
            result.setSuccess(true, new ResultMessage("message", "delete.ok",  new Integer(retValue)));
			UrlAddress next = new UrlAddress (super.controlAction);
			next.addParam("cmd", "add");
			next.addParam("reload", "T");
			result.setNext(next);
        } else {
            result.setSuccess(false, new ResultMessage("message", "delete.fail.existChild"));
        }

	}	
	
	/**
	 * �޴� ����� 
	 * @param _req
	 * @param _res
	 * @throws MafException
	 */
	public void cmdAdd(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
        String p_org_cd = _req.getParameter( "p_org_cd", "UBQ" );

        String org_cd = ("UBQ".equals(p_org_cd))?"M01": p_org_cd+"01"; 
        _req.setAttribute( "org_cd", org_cd );

        result.setForward("write");
	}	
	
}
