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
import xadmin.user.dao.GroupAuthDB;
import xadmin.user.dao.GroupManagerDB;

/**
 * @author goindole
 *
 */
public class GroupAuth extends AdminAction {
	 
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
					param.put("menu_code", ruser[i]);
					param.put("grp_id", grp_id);
					try {
						GroupAuthDB.removeAuth(super.oDb, param);
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
					param.put("menu_code", ruser[i]);
					try {
						GroupAuthDB.addAuth(super.oDb, param);
					} catch (Throwable e) {
						Logging.log(this.getClass(), e.getMessage());
					}
				}
    		}
    	} else if("groupUpdate".equals(cmd)) {
    		Map param = _req.getKeyValueMap();
    		GroupManagerDB.updateGroup(oDb,param);
		} else if("groupInsert".equals(cmd)) {
			Map param = _req.getKeyValueMap();
			GroupManagerDB.insertGroup(oDb,param);
		} else if("groupDelete".equals(cmd)) {
			Map param = _req.getKeyValueMap();
			if(GroupManagerDB.deleteGroup(oDb,param)) {
				grp_id = null;
			} else {
				forward = "error";
				_req.setAttribute("message","삭제중 오류가 발생하였습니다.");
			}
		}
    	
    	
    	Map grp_info = GroupManagerDB.getGroupInfo(oDb, grp_id);
    	List admMenuList = GroupAuthDB.getMenuList(super.oDb);
    	List groupMenuList = GroupAuthDB.getGroupMenuList(super.oDb, grp_id);
    	List groupList = GroupAuthDB.getGroupList(super.oDb);
    	_req.setAttribute("admMenuList", admMenuList);
    	_req.setAttribute("groupMenuList", groupMenuList);
    	_req.setAttribute("groupList", groupList);
    	_req.setAttribute("grp_info", grp_info);
    	
    	return forward;
    }
}