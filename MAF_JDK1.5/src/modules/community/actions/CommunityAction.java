/*
 * @(#) CommunityAction.java 2005-02-21
 * 
 * Copyright (c) 2005 (��)�̷��� All rights reserved.
 * Ŀ�´��� ������
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

        // ���� Servlet ����
       doWork(_req, _res);
       
        _req.setAttribute("MYCLUBS", ClubDB.getMyClubs(oDb, sessionBean.getUsn()));
        
    }

    /**
     * �� ���ǽ� ���α׷����� �ۼ� �ؾ��� �ڵ�
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
