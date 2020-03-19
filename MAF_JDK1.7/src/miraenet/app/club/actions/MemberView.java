/*
 * MemberView.java, @ 2005-03-19
 * 
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package miraenet.app.club.actions;

import javax.servlet.http.HttpServletResponse;

import maf.web.http.MyHttpServletRequest;
import maf.web.mvc.beans.ResultMessage;
import miraenet.app.club.beans.ClubMemberBean;
import miraenet.app.club.dao.ClubMemberDB;

/**
 * @author goindole
 *
 * 회원 정보 보기 
 */
public class MemberView extends BaseClubAction {
        public void doWork(MyHttpServletRequest _req, HttpServletResponse response) {
        	try {
	            String usn = _req.getParameter( "usn" );
	            
	            ClubMemberBean member = ClubMemberDB.getClubMember( oDb, club_id, usn );
	            result.setAttribute("member", member);
	            result.setForward( "member_view");
        	} catch (Exception e) {
        		result.setError(e, new ResultMessage(e.getMessage()));
        	}
        }

}
