/*
 * ClubBody.java, @ 2005-03-20
 * 
 * Copyright (c) 2004 UBQ All rights reserved.
 */
package modules.community.club.actions;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import maf.web.http.MyHttpServletRequest;
import modules.community.mboard.MBoardUtil;

/**
 * @author UBQ
 *
 */
public class ClubBody  extends BaseClubAction {
    public void doWork(MyHttpServletRequest _req, HttpServletResponse response){
        List list = MBoardUtil.getRecentNoticeDatabyClub_id(club_id,5);
        result.setAttribute("recentlist", list);
        result.setForward("club_body");

    }

}