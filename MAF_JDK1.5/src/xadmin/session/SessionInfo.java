/*
 * SessionInfo.java, @ 2005-03-11
 * 
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package xadmin.session;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import maf.exception.MafException;
import maf.web.http.MyHttpServletRequest;
import maf.web.session.SessionPool;
import xadmin.common.actions.AdminAction;

/**
 * @author goindole
 *
 * 현재 세션정보를 보여준다.
 */
public class SessionInfo extends AdminAction {
    
    public String doWork(MyHttpServletRequest request, HttpServletResponse response)
    throws MafException {
        Map s = SessionPool.getSessinInfo();
        
        request.setAttribute("sessinons", s);
        request.setAttribute("size", SessionPool.size()+"");
       return "default"; 
    }
}
