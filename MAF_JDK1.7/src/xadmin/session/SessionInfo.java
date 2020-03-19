/*
 * SessionInfo.java, @ 2005-03-11
 * 
 * Copyright (c) 2004 (��)�̷��� All rights reserved.
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
 * ���� ���������� �����ش�.
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
