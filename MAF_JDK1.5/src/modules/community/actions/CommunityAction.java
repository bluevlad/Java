/*
 * @(#) CommunityAction.java 2005-02-21
 * 
 * Copyright (c) 2005 (주)미래넷 All rights reserved.
 * 커뮤니지 지원용
 */

package modules.community.actions;

import javax.servlet.http.HttpServletResponse;

import maf.exception.MafException;
import maf.web.http.MyHttpServletRequest;
import maf.web.mvc.action.BaseMafCommand;
import maf.web.session.exception.SessionInvalidatedException;
import miraenet.app.club.dao.ClubDB;

/**
 * @author goindole
 * @version 1.0
 * @modifydate 2005-02-21
 */
public class CommunityAction extends BaseMafCommand   {

    
    public void process(MyHttpServletRequest _req, HttpServletResponse _res)
        throws MafException {

        if (super.sessionBean == null) {
            throw new SessionInvalidatedException("Session Invalid!!!");
        }

        // 실제 Servlet 수행
       doWork(_req, _res);
       
        _req.setAttribute("MYCLUBS", ClubDB.getMyClubs(oDb, sessionBean.getUsn()));
        
    }

    /**
     * 각 강의실 프로그램에서 작성 해야할 코드
     * 
     * @param _req
     * @param response
     * @return
     */
    public void doWork(MyHttpServletRequest _req, HttpServletResponse response)
        throws MafException {
        result.setForward(BaseMafCommand.DEFAULT_VIEW);
    }

}
