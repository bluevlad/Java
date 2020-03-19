/*
 * ClubView.java, @ 2005-03-18
 * 
 * Copyright (c) 2004 UBQ All rights reserved.
 */
package modules.community.actions;

import javax.servlet.http.HttpServletResponse;
import maf.exception.MafException;
import maf.web.http.MyHttpServletRequest;
import modules.common.actions.BaseCommonAction;
import modules.community.club.beans.ClubMasterBean;
import modules.community.club.dao.ClubDB;

/**
 * @author bluevlad
 *  
 */
public class ClubInfo extends BaseCommonAction {
    public String doWork(MyHttpServletRequest request, HttpServletResponse response)
            throws MafException {
        String club_id = request.getP("club_id");
        ClubMasterBean club = ClubDB.getClubInfo(oDb, club_id);
        request.setAttribute( "club", club );
        return "default";
    }
}