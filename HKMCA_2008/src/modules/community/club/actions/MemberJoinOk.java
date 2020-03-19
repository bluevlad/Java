/*
 * MemberJoinOk.java, @ 2005-03-20
 * 
 * Copyright (c) 2004 UBQ All rights reserved.
 */
package modules.community.club.actions;

import javax.servlet.http.HttpServletResponse;

import maf.web.http.MyHttpServletRequest;


/**
 * @author UBQ
 *
 */
public class MemberJoinOk   extends BaseClubAction {
    public void doWork(MyHttpServletRequest _req, HttpServletResponse response){
    	result.setForward("member_join_ok");
    }
}
