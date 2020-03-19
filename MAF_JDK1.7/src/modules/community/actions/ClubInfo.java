/*
 * ClubView.java, @ 2005-03-18
 * 
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package modules.community.actions;

import javax.servlet.http.HttpServletResponse;

import maf.exception.MafException;
import maf.web.http.MyHttpServletRequest;
import miraenet.app.club.beans.ClubMasterBean;
import miraenet.app.club.dao.ClubDB;
import modules.common.actions.CommonAction;

/**
 * @author goindole
 *  
 */
public class ClubInfo extends CommonAction {
    public String doWork(MyHttpServletRequest request, HttpServletResponse response)
            throws MafException {
        String club_id = request.getP("club_id");
        ClubMasterBean club = ClubDB.getClubInfo(oDb, club_id);
        request.setAttribute( "club", club );
        return "default";
    }
}