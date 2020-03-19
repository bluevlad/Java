/*
 * AdminAction.java, @ 2005-04-22
 * 
 * Copyright (c) 2004 (��)�̷��� All rights reserved.
 */
package xadmin.common.actions;

import javax.servlet.http.HttpServletResponse;

import maf.exception.MafException;
import maf.web.http.MyHttpServletRequest;
import maf.web.mvc.action.BaseMafCommand;

/**
 * @author goindole
 *
 */
public class AdminAction extends BaseMafCommand   {
//    private Logger logger = Logger.getLogger(CommonAction.class);

    public void process(MyHttpServletRequest _req, HttpServletResponse _res)
        throws MafException {
        String forward = null;
        
        if(super.sessionBean == null ) {
            _req.setAttribute("next","/xadmin/login.jsp");
            _req.setAttribute("target", "window.top");
            forward = "message";
        } else {
//        	if("A".equals(super.sessionBean.getUtype())) {
        	if(true) {
		        // ���� Servlet ����
		        forward = doWork(_req, _res);
        	} else {
        		_req.setAttribute("next","/");
        		_req.setAttribute("target", "window.top");
        		_req.setAttribute("message","��ȿ���� ���� �����Դϴ�.");
        		forward="message";
        	}
        }
        super.result.setForward(forward);
    }

    /**
     * �� ���ǽ� ���α׷����� �ۼ� �ؾ��� �ڵ�
     * 
     * @param _req
     * @param response
     * @return
     */
    public String doWork(MyHttpServletRequest _req, HttpServletResponse _res)
        throws MafException {
        throw new MafException("doWork�� ������ �ּ���");
    }
}
