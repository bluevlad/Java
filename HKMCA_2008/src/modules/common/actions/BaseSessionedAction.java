/*
 * @(#) CommunityAction.java 2005-02-21
 * 
 * Copyright (c) 2005 UBQ All rights reserved.
 * ������ �ʿ��� Action�� ���� Ŭ���� 
 */

package modules.common.actions;

import javax.servlet.http.HttpServletResponse;

import maf.exception.MafException;
import maf.web.http.MyHttpServletRequest;
import maf.web.mvc.action.BaseMafCommand;
import maf.web.session.exception.SessionInvalidatedException;

/**
 * ������ ����ڸ� ����� servlet�� �ڵ�
 * @author bluevlad
 * @version 1.0
 * @modifydate 2005-02-21
 */
public class BaseSessionedAction extends BaseMafCommand   {

    
    public void process(MyHttpServletRequest _req, HttpServletResponse _res)
        throws MafException {
        String forward = BaseMafCommand.DEFAULT_VIEW;

        if (super.sessionBean == null) {
            throw new SessionInvalidatedException("Session Invalid!!!");
        }

        // ���� Servlet ����
        forward = doWork(_req, _res);
        super.result.setForward(forward);
    }

    /**
     * �� ���ǽ� ���α׷����� �ۼ� �ؾ��� �ڵ�
     * 
     * @param _req
     * @param response
     * @return
     */
    protected String doWork(MyHttpServletRequest _req, HttpServletResponse response)
        throws MafException {
        return BaseMafCommand.DEFAULT_VIEW;
    }

}
