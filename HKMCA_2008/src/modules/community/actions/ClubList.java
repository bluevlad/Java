/*
 * CommonClubList.java, @ 2005-03-17
 * 
 * Copyright (c) 2004 UBQ All rights reserved.
 */
package modules.community.actions;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import maf.beans.NavigatorInfo;
import maf.exception.MafException;
import maf.web.http.MyHttpServletRequest;
import modules.common.actions.BaseCommonAction;
import modules.community.club.dao.ClubDB;


/**
 * @author bluevlad
 * 
 * Ŭ�� ��� ����
 */
public class ClubList extends BaseCommonAction {
    public String doWork(MyHttpServletRequest request, HttpServletResponse response)
            throws MafException {
//      ���� ������ ��ȣ
        int v_page = request.getIntParameter( "v_page", 1 );

        //�������� ������ �Խù� ��
        int page_size = 5;
        
        //Ŭ���� ���� 
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