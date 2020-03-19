/*
 * ClubInfoModify.java, @ 2005-03-20
 * 
 * Copyright (c) 2004 UBQ All rights reserved.
 */
package modules.community.club.actions;

import javax.servlet.http.HttpServletResponse;

import maf.web.http.MyHttpServletRequest;


/**
 * @author UBQ
 * 클럽 정보 수정 
 */
public class ClubInfoModify extends BaseClubAction {
    public void doWork(MyHttpServletRequest _req, HttpServletResponse response) {

    	result.setForward("club_info_modify");
    }

}