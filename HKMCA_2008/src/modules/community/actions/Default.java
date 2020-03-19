/*
 * Default.java, @ 2005-03-17
 * 
 * Copyright (c) 2004 UBQ All rights reserved.
 */
package modules.community.actions;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import maf.exception.MafException;
import maf.web.http.MyHttpServletRequest;
import modules.common.actions.BaseCommonAction;
import modules.community.club.dao.ClubDB;
import modules.community.mboard.MBoardUtil;


/**
 * @author UBQ
 *  
 */
public class Default extends BaseCommonAction {
    public String doWork(MyHttpServletRequest request, HttpServletResponse response)
            throws MafException {
        List rlist = MBoardUtil.getRecentBoardDatabyGrp( "comm",10 );
        List clublist = ClubDB.getRecentClubs( oDb );

        request.setAttribute( "clublist", clublist );
        request.setAttribute( "recentlist", rlist );
        return "default";
    }
}