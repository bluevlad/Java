/*
 * index.java, @ 2005-05-03
 * 
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package xadmin.common.actions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import maf.exception.MafException;
import maf.web.http.MyHttpServletRequest;

/**
 * @author goindole
 *
 */
public class Index extends AdminAction {
    public String doWork(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
        String forward = _req.getP("cmd","default");
        
        final String sql = " SELECT   mc.menu_ccode, mc.menu_cname, m.menu_code, m.menu_name, m.menu_url"   +
			"     FROM mst_admin_menu_category mc, mst_admin_menu m"   +
			"    WHERE m.menu_ccode = mc.menu_ccode"   +
			"      AND EXISTS ("   +
			"             SELECT mg.menu_code"   +
			"               FROM mst_admin_grp_user gu, mst_admin_menu_grp mg"   +
			"              WHERE gu.grp_id = mg.grp_id"   +
			"                AND gu.user_id = :userid"   +
			"                AND m.menu_code = mg.menu_code)"   +
			" ORDER BY mc.seq, m.seq"  ;
        
        if("menu".equals(forward)) {
            List at = new ArrayList();
            at.add(super.sessionBean.getUserid());
            List menus = oDb.selector(Map.class, sql, at);
            _req.setAttribute("menus", menus);
            //super.oDb.
            
        }
        
        return forward;
    }
}
