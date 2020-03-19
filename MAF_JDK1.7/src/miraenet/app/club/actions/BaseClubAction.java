/*
 * MClubAction.java, @ 2005-03-17
 * 
 * Copyright (c) 2004 (��)�̷��� All rights reserved.
 */
package miraenet.app.club.actions;

import javax.servlet.http.HttpServletResponse;

import maf.exception.MafException;
import maf.web.http.MyHttpServletRequest;
import maf.web.mvc.action.BaseMafCommand;
import maf.web.session.exception.SessionInvalidatedException;
import miraenet.app.club.ClubException;
import miraenet.app.club.beans.ClubMasterBean;
import miraenet.app.club.dao.ClubDB;
import miraenet.app.club.dao.ClubMemberDB;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author goindole
 *  
 */
public class BaseClubAction extends BaseMafCommand  {

    static  Log logger = LogFactory.getLog(BaseClubAction.class);
    public final String MESAGE_BUNDLE_NAME = "mafClub";
    protected String club_id = null;
    protected ClubMasterBean mstBean = null;
    /**
     * ȸ��Level ����
     * 1: �ü�, 2:����ȸ��, 3:�����ȸ��, 4:�մ�
     */
   
    
    
    public void process(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
    	String memberLevel = "4";
        if (super.sessionBean == null) {
            throw new SessionInvalidatedException( "Session Invalid!!!" );
        }

        club_id = _req.getP( "club_id" );
        
        mstBean = ClubDB.getClubInfo( oDb, club_id );
        if(mstBean == null) {
            throw new ClubException("invalid Club");
        }
        
        String isAuthMember = ClubMemberDB.checkClubMember( oDb, club_id, sessionBean.getUsn() );
        
        //   �ü����� Ȯ��
        boolean isSysop = false;
        if (sessionBean.getUsn().equals(mstBean.getC_sysopusn())) {
            isSysop = true;
        }

        //ȸ��Level ����
        // 1: �ü�, 2:����ȸ��, 3:�����ȸ��, 4:�մ�
        if (isSysop) {
        	memberLevel = "1";
        }else if ("T".equals( isAuthMember )) {
        	memberLevel = "2";
        }else if ("F".equals( isAuthMember )) {
        	memberLevel = "3";
        }
        
       
        // ���� Servlet ����
        doWork( _req, _res );
       
        _req.setAttribute("mstBean", mstBean);
        _req.setAttribute( "memberLevel", memberLevel);
//        _req.setAttribute( "X_menu", MenuManager.getInstance().getMenu( "communityHome",
//                sessionBean.getUtype() ) );
        
    }

    /**
     * �� ���ǽ� ���α׷����� �ۼ� �ؾ��� �ڵ�
     * 
     * @param _req
     * @param response
     * @return
     */
    public void doWork(MyHttpServletRequest _req, HttpServletResponse response) throws MafException {
    }

}