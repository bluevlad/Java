/*
 * Created on 2005. 11. 18.
 *
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package miraenet.app.dbadmin.actions;

import javax.servlet.http.HttpServletResponse;

import maf.web.http.MyHttpServletRequest;

public class Default extends DbAdminAction  {
    public void doWork(MyHttpServletRequest _req, HttpServletResponse response)
    {
    		result.setForward("default");
    }
}

