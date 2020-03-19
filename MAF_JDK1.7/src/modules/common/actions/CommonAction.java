/*
 * CommonAction.java, @ 2005-04-08
 * 
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package modules.common.actions;

import javax.servlet.http.HttpServletResponse;

import maf.exception.MafException;
import maf.web.http.MyHttpServletRequest;
import maf.web.mvc.action.BaseMafCommand;

/**
 * @author goindole
 *
 */
public class CommonAction extends BaseMafCommand  {
//    private Logger logger = Logger.getLogger(CommonAction.class);

    public void process(MyHttpServletRequest _req, HttpServletResponse _res)
        throws  MafException {
        String forward = null;

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
    public String doWork(MyHttpServletRequest _req, HttpServletResponse _res)
        throws  MafException {
        throw new MafException("doWork를 선언해 주세요");
    }

}