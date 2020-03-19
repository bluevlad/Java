/*
 * MemberJoinOk.java, @ 2005-03-20
 * 
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package miraenet.app.club.actions;

import javax.servlet.http.HttpServletResponse;

import maf.web.http.MyHttpServletRequest;

/**
 * @author goindole
 *
 */
public class MemberJoinOk   extends BaseClubAction {
    public void doWork(MyHttpServletRequest _req, HttpServletResponse response){
    	result.setForward("member_join_ok");
    }
}
