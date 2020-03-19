/*
 * MemberView.java, @ 2005-03-19
 * 
 * Copyright (c) 2004 UBQ All rights reserved.
 */
package modules.community.club.actions;

import javax.servlet.http.HttpServletResponse;

import maf.web.http.MyHttpServletRequest;
import maf.web.mvc.beans.ResultMessage;
import modules.community.club.beans.ClubMemberBean;
import modules.community.club.dao.ClubMemberDB;



/**
 * @author UBQ
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
