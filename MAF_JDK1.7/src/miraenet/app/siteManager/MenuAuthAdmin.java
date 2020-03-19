/*
 * Created on 2006. 09. 01
 *
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package miraenet.app.siteManager;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import maf.exception.MafException;
import maf.web.http.MyHttpServletRequest;
import maf.web.mvc.action.MafAction;
import maf.web.mvc.beans.ResultMessage;
import maf.web.mvc.menu.TreeMenu;
import maf.web.session.exception.SessionInvalidatedException;
import maf.web.util.SerializeHashtable;
import miraenet.MiConfig;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MenuAuthAdmin extends MafAction {
	private Log logger = LogFactory.getLog(MenuAuthAdmin.class);
	String site = null;
    SerializeHashtable listOp = null;
    private final String MESSAGE_BUNDLENAME = "common.message";

	public void preProcess(MyHttpServletRequest _req, HttpServletResponse _res)
	throws MafException {
		if(super.sessionBean == null) {
			throw new SessionInvalidatedException();
		}
		this.site = _req.getP( "site", MiConfig.DEFAULT_SITE );
		_req.setAttribute( "site", site );
		
	}

	public void cmdDefault(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
        List sites = MstMenuDB.getSimpleSites( oDb );
        result.setAttribute( "sites", sites );
		result.setForward("default");
	}
	public void cmdTree(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {

        result.setAttribute( "menus", MstMenuDB.getMenuTree( oDb, this.site ));
        result.setForward("tree");
	}	
	public void cmdEdit(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
        String pgid = _req.getParameter( "pgid", "" );

        _req.setAttribute( "pgAuth", MenuAuthDB.getPgAuth(oDb, site, pgid));
        _req.setAttribute( "pgid", pgid );
        result.setForward("edit");

	}	
	public void cmdUpdate(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {

		Map param = _req.getKeyValueMap();
    	String[] vrole = _req.getParameterValues("vrole");
		int retValue = 0;
		boolean chk = false;
		try {
    		oDb.setAutoCommit(false);
    		
    		/* 선택한 메뉴 하위 메뉴를 모두 동일하게 권한을 적용할 경우 사용.
			MenuAuthDB.deleteMenuAuthAll(oDb, param); //메뉴 모두 삭제
			List menu_all = MenuAuthDB.getMenuList(oDb, param);
			*/
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

//               		if (param.get(ctype).toString().equals("C")) {
                    	MenuAuthDB.insertMenuAuth(oDb, param);
//                	} else {
//                    	MenuAuthDB.updateMenuAuth(oDb, param);
//                	}
                	
            		/* 선택한 메뉴 하위 메뉴를 모두 동일하게 권한을 적용할 경우 사용.
	                if(!menu_all.isEmpty()) {
	                    for(int k=0; k < menu_all.size(); k++) {
	                    	
	                    	Map menu_one = (Map)menu_all.get(k);
	            			param.put("pgid", menu_one.get("pgid"));

                   			MenuAuthDB.insertMenuAuth(oDb, param);
                   		
                   			retValue = k+1;
                		}
            		}
        		*/
               		retValue = i+1;
                }
            }
            
            oDb.commit();
            chk = true;
    	}   catch(Throwable e) {
    		logger.error(e.getMessage());
       		retValue = 0;
			oDb.rollback();
    	}

		if (chk) {
        	menuReload();
    		String forward_url = "?reload=T&cmd=edit&site=" + param.get("site") + "&pgid=" + param.get("pgid");
			result.setSuccess(true, new ResultMessage(this.MESSAGE_BUNDLENAME, "message.update.ok", new Integer(retValue)));
            result.setNext(super.controlAction + forward_url);

		} else {
			result.setSuccess(false, new ResultMessage(this.MESSAGE_BUNDLENAME, "message.update.fail"));
		}

	}
	
	private void menuReload() throws MafException {
        TreeMenu oTM = TreeMenu.getInstance(this.site);
        oTM.DataReset();
	}
}

