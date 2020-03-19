/*
 * @(#) CommunityAction.java 2005-02-21
 * 
 * Copyright (c) 2005 UBQ All rights reserved.
 * 세션이 필요한 Action의 슈퍼 클래스 
 */

package modules.common.actions;

import javax.servlet.http.HttpServletResponse;

import maf.exception.MafException;
import maf.web.http.MyHttpServletRequest;
import maf.web.mvc.action.BaseMafCommand;
import maf.web.session.exception.SessionInvalidatedException;

/**
 * 인증된 사용자만 사용할 servlet의 코드
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
