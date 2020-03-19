/*
 * ClubAct.java, @ 2005-03-19
 * 
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package miraenet.app.club.actions;

import javax.servlet.http.HttpServletResponse;

import maf.lib.Setter;
import maf.web.http.MyHttpServletRequest;
import maf.web.mvc.beans.ResultMessage;
import miraenet.app.club.beans.ClubMemberBean;
import miraenet.app.club.dao.ClubMemberDB;

/**
 * @author goindole
 *
 * 일반 클럽 사용자용 Act
 */
public class ClubAct  extends BaseClubAction {
        public void doWork(MyHttpServletRequest _req, HttpServletResponse response) {
//        	Logger logger = Logger.getLogger(this.getClass());
            String cmd = _req.getParameter( "cmd" );
            
            if ("member_join".equals( cmd )) {
                /*******************************************************************
                 * 클럽 회원가입
                 ******************************************************************/
                ClubMemberBean memberBean = new ClubMemberBean();

                try {
                    Setter setter = new Setter( memberBean, _req );
                    setter.setProperty( "*" );
                    setter.setProperty( "c_id", club_id );
                    setter.setProperty( "usn", sessionBean.getUsn() );
                    
                    ClubMemberDB.memberJoin( oDb, memberBean );
                    result.setSuccess(true);
                    result.setNext("/club/member_join_ok.do?club_id="+club_id);
                } catch (Exception e) {
                    result.setSuccess(false, new ResultMessage(this.MESAGE_BUNDLE_NAME, "join.error"));
                }
                
            } else if ("secede".equals( cmd )) {
                /*******************************************************************
                 * 클럽회원 탈퇴
                 ******************************************************************/
                try {
                    ClubMemberDB.secede( oDb, club_id, sessionBean.getUsn() );
                    result.setForward("/club/default.do?club_id=" + club_id);
                    result.setSuccess(true, new ResultMessage(this.MESAGE_BUNDLE_NAME, "secede.ok"));
                } catch (Exception e) {
                    result.setSuccess(false, new ResultMessage(this.MESAGE_BUNDLE_NAME, "secede.error"));
                }
            } else {
            	result.setSuccess(false, new ResultMessage(this.MESAGE_BUNDLE_NAME, "invalid"));
            }
        }

}
