/*
 * Created on 2005. 8. 11.
 *
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package xadmin.user.actions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import maf.exception.MafException;
import maf.logger.Logging;
import maf.web.http.MyHttpServletRequest;
import xadmin.common.actions.AdminAction;
import xadmin.user.dao.UserAuthDB;

/**
 * @author goindole
 *
 */
public class UserAuth extends AdminAction {
    
    public String doWork(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		
    	String forward = "default";
    	String grp_id = _req.getP("grp_id");
    	
    	String cmd = _req.getP("cmd");
    	if ("removeAuth".equals(cmd)) {
    		String[] ruser = _req.getParameterValues("ruser");
    		if (ruser != null) {
    			Map param = new HashMap();
	    		for (int i = 0; i < ruser.length; i++) {
					param.clear();
					param.put("grp_id",grp_id);
					param.put("user_id", ruser[i]);
					try {
						UserAuthDB.delUserAuth(super.oDb, param);
					} catch (Throwable e) {
						Logging.log(this.getClass(), e.getMessage());
					}
				}
    		}
    	} else if ("addAuth".equals(cmd)) {
    		String[] ruser = _req.getParameterValues("ruser");
    		
    		if (ruser != null) {
    			Map param = new HashMap();
	    		for (int i = 0; i < ruser.length; i++) {
	    			Logging.log(this.getClass(), ruser[i]);
					param.clear();
					param.put("grp_id",grp_id);
					param.put("user_id", ruser[i]);
					try {
						UserAuthDB.insertUserAuth(super.oDb, param);
					} catch (Throwable e) {
						Logging.log(this.getClass(), e.getMessage());
					}
				}
    		}
    	}
    	
    	List admUserList = UserAuthDB.getAdminUserList(super.oDb);
    	List groupUserList = UserAuthDB.getGroupUserList(super.oDb, grp_id);
    	List groupList = UserAuthDB.getGroupList(super.oDb);
    	_req.setAttribute("adminUserList", admUserList);
    	_req.setAttribute("groupUserList", groupUserList);
    	_req.setAttribute("groupList", groupList);
    	_req.setAttribute("grp_id", grp_id);
    	
    	return forward;
    }
}

