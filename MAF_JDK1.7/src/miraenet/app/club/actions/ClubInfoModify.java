/*
 * ClubInfoModify.java, @ 2005-03-20
 * 
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package miraenet.app.club.actions;

import javax.servlet.http.HttpServletResponse;

import maf.web.http.MyHttpServletRequest;

/**
 * @author goindole
 * 클럽 정보 수정 
 */
public class ClubInfoModify extends BaseClubAction {
    public void doWork(MyHttpServletRequest _req, HttpServletResponse response) {

    	result.setForward("club_info_modify");
    }

}