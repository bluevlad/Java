/*
 * CommonClubList.java, @ 2005-03-17
 * 
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package modules.community.actions;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import maf.beans.NavigatorInfo;
import maf.exception.MafException;
import maf.web.http.MyHttpServletRequest;
import miraenet.app.club.dao.ClubDB;
import modules.common.actions.CommonAction;


/**
 * @author goindole
 * 
 * 클럽 목록 보기
 */
public class ClubList extends CommonAction {
    public String doWork(MyHttpServletRequest request, HttpServletResponse response)
            throws MafException {
//      현재 페이지 번호
        int v_page = request.getIntParameter( "v_page", 1 );

        //페이지당 보여줄 게시물 수
        int page_size = 5;
        
        //클럽의 갯수 
        int clubCount = 0;
        clubCount = ClubDB.getAllClubsCount(oDb);
        
        NavigatorInfo navigator = null;
    	if(clubCount > 0 ) {
    	    navigator = new NavigatorInfo();
    	    navigator.setCurrentPage(v_page);
    	    navigator.setPageSize(page_size);
    	    navigator.setTotalCount(clubCount);
    	    navigator.sync();
    	}
    	oDb.setPage(v_page, page_size);
        List clublist = ClubDB.getAllClubs( oDb );
        request.setAttribute("navigator", navigator);
        request.setAttribute( "clublist", clublist );

        return "default";
    }
}