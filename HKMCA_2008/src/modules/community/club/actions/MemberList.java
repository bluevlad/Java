/*
 * MemberList.java, @ 2005-03-19
 * 
 * Copyright (c) 2004 UBQ All rights reserved.
 */
package modules.community.club.actions;

import javax.servlet.http.HttpServletResponse;

import maf.MafUtil;
import maf.beans.NavigatorInfo;
import maf.web.http.MyHttpServletRequest;
import maf.web.mvc.beans.ResultMessage;
import modules.community.club.beans.ClubMemberBean;
import modules.community.club.dao.ClubMemberDB;




/**
 * @author UBQ
 *  
 */
public class MemberList extends BaseClubAction {
    public void doWork(MyHttpServletRequest _req, HttpServletResponse response) {
    	try {
	        String keyword = _req.getParameter( "keyword" );
	        String item = _req.getParameter( "item" );
	
	        //���� ������ ��ȣ
	        int v_page = MafUtil.parseInt(_req.getP( "v_page"), 1 );
	
	        //�������� ������ �Խù� ��
	        int page_size = 10;
	
	        //Ŭ���� ��ü ȸ����
	        int memberCount = 0;
	        if (MafUtil.empty( keyword )) {
	            memberCount = ClubMemberDB.getMemberCount( oDb, club_id );
	        } else {
	            memberCount = ClubMemberDB.getSearchMemberCount( oDb, club_id, item, keyword );
	        }
	
	        //����Ʈ�� ������ ��ȣ
	        int articleNum = memberCount - (page_size * (v_page - 1));
	
	        //ȸ������Ʈ �迭
	        ClubMemberBean[] members = null;
	        if (MafUtil.empty( keyword )) {
	            members = ClubMemberDB.getMemberList( oDb, page_size, v_page, club_id );
	        } else {
	            members = ClubMemberDB.searchClubMember( oDb, page_size, v_page, club_id, item,
	                    keyword );
	        }
	        NavigatorInfo navigator = null;
	    	if(memberCount > 0 ) {
	    	    navigator = new NavigatorInfo();
	    	    navigator.setCurrentPage(v_page);
	    	    navigator.setPageSize(page_size);
	    	    navigator.setTotalCount(memberCount);
	    	    navigator.sync();
	    	}
	    	result.setAttribute("memberCount",memberCount+"");
	    	result.setAttribute("members",members);
	    	result.setAttribute("articleNum",articleNum+"");
	    	result.setAttribute("navigator", navigator);
	        result.setForward( "member_list" );
    	} catch (Exception e ) {
    		result.setError(e, new ResultMessage(e.getMessage()));
    	}
    }
}