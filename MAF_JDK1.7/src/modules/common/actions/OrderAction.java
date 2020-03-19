/*
 * @(#) ShopAction.java 2005-02-21
 * 
 * Copyright (c) 2005 (주)미래넷 All rights reserved.
 * 도서몰 지원용
 */

package modules.common.actions;

import javax.servlet.http.HttpServletResponse;

import maf.exception.MafException;
import maf.web.http.MyHttpServletRequest;
import maf.web.mvc.action.BaseMafCommand;
import maf.web.session.exception.SessionInvalidatedException;

/**
 * @author goindole
 * @version 1.0
 * @modifydate 2005-02-21
 */
public class OrderAction extends BaseMafCommand   {

    
    public void process(MyHttpServletRequest _req, HttpServletResponse _res)
        throws MafException {
        String forward = BaseMafCommand.DEFAULT_VIEW;

        if (super.sessionBean == null) {
            throw new SessionInvalidatedException("Session Invalid!!!");
        }

        // 실제 Servlet 수행
        forward = doWork(_req, _res);
        super.result.setForward(forward);
    }

    /**
     * 각 강의실 프로그램에서 작성 해야할 코드
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
