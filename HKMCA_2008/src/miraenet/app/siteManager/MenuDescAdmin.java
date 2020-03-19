/*
 * menu_admin.java, @ 2005-04-22
 * 
 * Copyright (c) 2009 UBQ All rights reserved.
 */
package miraenet.app.siteManager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;


import maf.MafProperties;
import maf.exception.MafException;
import maf.menu.TreeMenu;
import maf.web.http.MyHttpServletRequest;
import maf.web.mvc.action.MafAction;
import maf.web.mvc.beans.ResultMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author UBQ
 *  
 */
public class MenuDescAdmin extends MafAction {
	private Log logger = LogFactory.getLog(MenuDescAdmin.class);
    private final String MESSAGE_BUNDLENAME = "common.message";
	String site = null;
	String lang = null;

	public void preProcess(MyHttpServletRequest _req, HttpServletResponse _res)	throws MafException {
		if(super.sessionBean == null) {
//			throw new SessionInvalidatedException();
		}
		this.site = _req.getP( "site", MafProperties.DEFAULT_SITE );
		this.lang = super.locale.getLanguage();
		_req.setAttribute( "site", site );
		
	}

	/**
	 * default page
	 * @param _req
	 * @param _res
	 * @throws MafException
	 */
	public void cmdDefault(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
        List sites = MstMenuDB.getSimpleSites( oDb );
        result.setAttribute( "sites", sites );
		result.setForward("default");
	}
	
	/**
	 * 메뉴 Tree 보여주기 
	 * @param _req
	 * @param _res
	 * @throws MafException
	 */
	public void cmdTree(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		Map param = new HashMap();

        param.put("site", this.site);
        param.put("lang", super.locale.getLanguage());
		
        List menus = MstMenuDB.getMenuDescTree(oDb, param);
        result.setAttribute( "menus", menus );
        result.setForward("tree");
	}	
	
	/**
	 * 메뉴 수정 폼 
	 * @param _req
	 * @param _res
	 * @throws MafException
	 */
	public void cmdEdit(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		Map param = new HashMap();
		
		param.put("pgid", _req.getParameter("pgid",""));
		param.put("site", this.site);
        param.put("lang", super.locale.getLanguage());

        _req.setAttribute("item", MstMenuDB.getDesc( oDb, param ));
        result.setForward("edit");
	}	
	
	/**
	 * 메뉴 수정 폼 
	 * @param _req
	 * @param _res
	 * @throws MafException
	 */
	public void cmdView(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		Map param = new HashMap();
		
		param.put("pgid", _req.getParameter("pgid",""));
		param.put("site", this.site);
        param.put("lang", super.locale.getLanguage());

        _req.setAttribute("item", MstMenuDB.getDesc( oDb, param ));
        result.setForward("view");
	}	
	
	/**
	 * 메뉴정보 수정 db update
	 * @param _req
	 * @param _res
	 * @throws MafException
	 */
	public void cmdUpdate(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		
		int retValue = 0;
		Map param = _req.getKeyValueMap();
		
		param.put("site", this.site);
        param.put("lang", super.locale.getLanguage());
        
		try {
			oDb.setAutoCommit(false);
			retValue += MstMenuDB.updateDesc(super.oDb, param);
			oDb.commit();
		} catch (Exception e) {
			oDb.rollback();
			logger.error(e.getMessage());
			retValue = 0;
		} finally {
			oDb.setAutoCommit(true);
		}
        
        if (retValue > 0) {
        	menuReload();
    		String forward_url = "?reload=T&cmd=edit&site="+param.get("site")+"&pgid="+param.get("pgid");
			result.setSuccess(true, new ResultMessage(this.MESSAGE_BUNDLENAME, "update.ok", new Integer(retValue)));
            result.setNext(super.controlAction + forward_url);
        } else {
			result.setSuccess(false, new ResultMessage(this.MESSAGE_BUNDLENAME, "update.fail", new Integer(retValue)));
        }		

	}
	
	/**
	 * 좌측 메뉴 Tree Reload 
	 * @throws MafException
	 */
	private void menuReload() throws MafException {
        TreeMenu oTM = TreeMenu.getInstance(this.site);
        oTM.DataReset();
	}
}