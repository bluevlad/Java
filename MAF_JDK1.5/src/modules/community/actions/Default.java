/*
 * Default.java, @ 2005-03-17
 * 
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package modules.community.actions;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import maf.exception.MafException;
import maf.web.http.MyHttpServletRequest;
import miraenet.app.club.dao.ClubDB;
import miraenet.app.mboard.MBoardUtil;
import modules.common.actions.CommonAction;


/**
 * @author goindole
 *  
 */
public class Default extends CommonAction {
    public String doWork(MyHttpServletRequest request, HttpServletResponse response)
            throws MafException {
        List rlist = MBoardUtil.getRecentBoardDatabyGrp( "comm",10 );
        List clublist = ClubDB.getRecentClubs( oDb );

        request.setAttribute( "clublist", clublist );
        request.setAttribute( "recentlist", rlist );
        return "default";
    }
}