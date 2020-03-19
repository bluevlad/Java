/*
 * Copyright (c) 2009 UBQ All rights reserved.
 */
package miraenet.app.siteManager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import maf.MafProperties;
import maf.context.i18n.MafResourceStore;
import maf.exception.MafException;
import maf.menu.TreeMenu;
import maf.web.http.MyHttpServletRequest;
import maf.web.http.UrlAddress;
import maf.web.mvc.action.MafAction;
import maf.web.mvc.beans.ResultMessage;
import maf.web.session.exception.SessionInvalidatedException;
import modules.common.jason.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * @author UBQ
 *  
 */
public class MenuAdmin extends MafAction {
	private Log logger = LogFactory.getLog(MenuAdmin.class);
    private final String MESSAGE_BUNDLENAME = "common.message";
	String site = null;
	String lang = null;

	public void preProcess(MyHttpServletRequest _req, HttpServletResponse _res)	throws MafException {
		if(super.sessionBean == null) {
			throw new SessionInvalidatedException();
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
		
        List menus = MstMenuDB.getMenuTree(oDb, param);
        result.setAttribute( "menus", menus );
        result.setForward("tree");
	}	
	
	/**
	 * 메뉴 등록폼 
	 * @param _req
	 * @param _res
	 * @throws MafException
	 */
	public void cmdAdd(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
        String pnodeid = _req.getParameter( "pnodeid", "HOME" );
		Map item = _req.getKeyValueMap();

//		MstMenuBean item = new MstMenuBean();
//        item.setPnodeid( pnodeid );
//        _req.setAttribute( "item", item );
		item.put("isuse", "T");
		item.put("target", "_self");
		item.put("islink", "T");
		item.put("is_tmenu", "F");
		item.put("isservlet", "T");
		item.put("is_lmenu", "T");
		item.put("session_chk", "F");
		item.put("is_sitemap", "F");
		item.put("pnodeid", pnodeid);
		
        String pgid = ("HOME".equals(pnodeid))?"M01": pnodeid+"01"; 
        _req.setAttribute("pgid", pgid);
        _req.setAttribute("item", item);
        result.setForward("write");
	}	
	
	/**
	 * 메뉴 수정 폼 
	 * @param _req
	 * @param _res
	 * @throws MafException
	 */
	public void cmdEdit(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		Map param = _req.getKeyValueMap();
		
		param.put("pgid", _req.getParameter("pgid",""));
        param.put("lang", super.locale.getLanguage());

        _req.setAttribute("item", MstMenuDB.view( oDb, param ));
        _req.setAttribute("pgAuth", MenuAuthDB.getPgAuth(oDb, site, _req.getParameter("pgid","")));

        _req.setAttribute("pgid", param.get("pgid"));
        result.setForward("edit");
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
		
    	String[] vrole = _req.getParameterValues("vrole");

		param.put("usn", super.sessionBean.getUsn());
		param.put("upt_id", super.sessionBean.getUsn());
		param.put("admin_usn", _req.getIntParameter("ADMIN_USN")+"");
        param.put("lang", super.locale.getLanguage());
        
        String current_pgid = _req.getParameter( "current_pgid" );
        param.put("messagekey",("".equals(param.get("messagekey").toString()))?param.get("pgid"): param.get("messagekey")); 

		try {
    		oDb.setAutoCommit(false);

	        retValue = MstMenuDB.update(oDb, param, current_pgid );
	        
			String message = _req.getP("message");
			String locale = _req.getP("locale");
			
			// 선택한 메뉴 하위 메뉴를 모두 동일하게 권한을 적용할 경우 사용.
			MenuAuthDB.deleteMenuAuthAll(oDb, param); //메뉴 모두 삭제
			List menu_all = MenuAuthDB.getMenuList(oDb, param);
	
			MenuAuthDB.deleteMenuAuth(oDb, param);
			if(null != vrole) {
	            for(int i=0; i < vrole.length; i++) {
	            	String ctype = "ctype_" + vrole[i];
	            	String auth_c = "auth_c_" + vrole[i];
	               	String auth_r = "auth_r_" + vrole[i]; 
	               	String auth_u = "auth_u_" + vrole[i];
	               	String auth_d = "auth_d_" + vrole[i];
	               	
	               	if (param.get(auth_c) != "" && param.get(auth_c) != null) {
	               		param.put("auth_c", "Y");
	               	}else {
	               		param.put("auth_c", "N");
	               	}
	               	if (param.get(auth_r) != "" && param.get(auth_r) != null) {
	               		param.put("auth_r", "Y");
	               	}else {
	               		param.put("auth_r", "N");
	               	}
	               	if (param.get(auth_u) != "" && param.get(auth_u) != null) {
	               		param.put("auth_u", "Y");
	               	}else {
	               		param.put("auth_u", "N");
	               	}
	               	if (param.get(auth_d) != "" && param.get(auth_d) != null) {
	               		param.put("auth_d", "Y");
	               	}else {
	               		param.put("auth_d", "N");
	               	}
	            		
	           		param.put("role_id", vrole[i]);
	
	//           		if (param.get(ctype).toString().equals("C")) {
	//           		retValue = MenuAuthDB.insertMenuAuth(oDb, param);
	//            	} else {
	//                	MenuAuthDB.updateMenuAuth(oDb, param);
	//            	}
	            	
	        		// 선택한 메뉴 하위 메뉴를 모두 동일하게 권한을 적용할 경우 사용.
	                if(!menu_all.isEmpty()) {
	                    for(int k=0; k < menu_all.size(); k++) {
	                    	Map menu_one = (Map)menu_all.get(k);
	            			param.put("pgid", menu_one.get("pgid"));
	            			retValue = MenuAuthDB.insertMenuAuth(oDb, param);
	            		}
	        		}
	            }
	        }
	
			MafResourceStore instance = MafResourceStore.getInstance();
			boolean chk = instance.updateOneMessage("menu", param.get("messagekey").toString(), super.locale.getLanguage(), param.get("title").toString());
			if(chk) {
				instance.saveResourceConfig("menu");
				JSONObject jobj = new JSONObject();
				jobj.put("result", "ok");
				result.setJson(jobj);
			}
            
            oDb.commit();
            
    	}   catch(Throwable e) {
    		logger.error(e.getMessage());
       		retValue = 0;
			oDb.rollback();
    	}

        if (retValue > 0) {
        	menuReload();
    		String forward_url = "?reload=T&cmd=edit&site=" + param.get("site") + "&pgid=" + param.get("pgid");
            result.setSuccess(true, new ResultMessage(this.MESSAGE_BUNDLENAME, "update.ok", new Integer(retValue)));
            result.setNext(super.controlAction + forward_url);
        } else {
            result.setSuccess(false, new ResultMessage(this.MESSAGE_BUNDLENAME, "update.fail", new Integer(retValue)));
        }		

	}
	/**
	 * 메뉴 등록 db insert
	 * @param _req
	 * @param _res
	 * @throws MafException
	 */
	public void cmdInsert(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		int retValue = 0;

		Map param = _req.getKeyValueMap();
        param.put("upt_id", super.sessionBean.getUsn());
        param.put("lang", super.locale.getLanguage());
		
        param.put("messagekey",("".equals(param.get("messagekey").toString()))?param.get("pgid"): param.get("messagekey")); 

        retValue = MstMenuDB.insert(oDb, param);

        if (retValue > 0) {
        	menuReload();
    		
    		UrlAddress next = new UrlAddress (super.controlAction);
    		next.addParam("reload", "T");
    		next.addParam("cmd", "add");
    		next.addParam("site", param.get("site"));
    		next.addParam("pnodeid", param.get("pnodeid"));
            result.setSuccess(true, new ResultMessage(this.MESSAGE_BUNDLENAME, "insert.ok", new Integer(retValue)));
            result.setNext(next);
        } else {
            result.setSuccess(false, new ResultMessage(this.MESSAGE_BUNDLENAME, "insert.fail", new Integer(retValue)));
        }
	}	
	
	/**
	 * 하나의 메뉴 삭제 
	 * @param _req
	 * @param _res
	 * @throws MafException
	 */
	public void cmdDelete(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		Map param = _req.getKeyValueMap();

		String pgid = _req.getParameter( "pgid" );
        String pnodeid = _req.getParameter( "pnodeid" );
        String lang = super.locale.getLanguage();
        int retValue = 0;

		try {
			oDb.setAutoCommit(false);
			param.put("pgid", pgid);
			param.put("lang", lang);
	        retValue= MstMenuDB.delete( oDb, param);
			oDb.commit();
		} catch (Exception e) {
			oDb.rollback();
			retValue = 0;
			logger.error(e.getMessage());
		}
		oDb.setAutoCommit(true);
        
        if (retValue > 0) {
        	menuReload();
        	UrlAddress next = new UrlAddress(super.controlAction);
        	String forward_url = "?reload=T&cmd=edit&site=" + site + "&pgid=" + pnodeid;
            result.setNext(forward_url);
            result.setSuccess(true, new ResultMessage(this.MESSAGE_BUNDLENAME, "delete.ok", new Integer(retValue)));
        } else {
            result.setSuccess(false, new ResultMessage(this.MESSAGE_BUNDLENAME, "delete.fail", new Integer(retValue)));
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
