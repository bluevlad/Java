/*
 * Created on 2005. 12. 19.
 *
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package miraenet.app.mboard;

import maf.base.UserRoles;
import miraenet.app.mboard.beans.MbbsMetaBean;
import modules.common.beans.SessionBean;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MBoardAuth {
	 static Log logger = LogFactory.getLog(MBoardAuth.class);
	/**
	 * 사용자의 해당 게시판의 Level을 가져 온다. 
	 * @param ob
	 * @param os
	 * @return
	 */
	public static int getUserBbsLevel(MbbsMetaBean ob, SessionBean  os) {
		if(os == null) {
			return 10;
		} else {
			return os.getUserRole().getBbsLevel();
		}
		
	}
	
	/**
	 * 게시판의 조회(List) 권한 확인
	 * @param ob
	 * @param os
	 * @return
	 */
	public static boolean chkListAuth(MbbsMetaBean ob, SessionBean  os){
		return (ob.getGrant_list() < getUserBbsLevel(ob, os) )? false:true;
	}
	
	/**
	 * 게시물 읽기 권한 조회
	 * @param ob
	 * @param os
	 * @return
	 */
	public static boolean chkReadAuth(MbbsMetaBean ob, SessionBean  os){
		return (ob.getGrant_view() < getUserBbsLevel(ob, os) )? false:true;
	}
	
	/**
	 * 게시물 삭제 권한 조회
	 * @param ob
	 * @param os
	 * @return
	 */
	public static boolean chkDeleteAuth(MbbsMetaBean ob, SessionBean  os){
		return (ob.getGrant_delete() < getUserBbsLevel(ob, os) )? false:true;
	}
	/**
	 * 게시물 쓰기 권한 조회 
	 * @param ob
	 * @param os
	 * @return
	 */
	public static boolean chkWriteAuth(MbbsMetaBean ob, SessionBean  os){
		logger.debug("ob:" + ob.getGrant_write() + ", b:" +getUserBbsLevel(ob, os)) ;
		return (ob.getGrant_write() < getUserBbsLevel(ob, os) )? false:true;
	}
	/**
	 * Notice 쓰기 권한 조회 
	 * @param ob
	 * @param os
	 * @return
	 */
	public static boolean chkNoticeAuth(MbbsMetaBean ob, SessionBean  os){
		logger.debug("ob:" + ob.getGrant_notice() + ", b:" +getUserBbsLevel(ob, os)) ;
		return (ob.getGrant_notice() < getUserBbsLevel(ob, os) )? false:true;
	}
	/**
	 * 게시물 댓글  권한 조회
	 * @param ob
	 * @param os
	 * @return
	 */
	public static boolean chkReplyAuth(MbbsMetaBean ob, SessionBean  os){
		return (ob.getGrant_reply() > getUserBbsLevel(ob, os) )? false:true;
	}
	
	public static String getBthAuth(MbbsMetaBean ob, SessionBean  os, String cmd ) {
		StringBuffer btnAuth = new StringBuffer(10);
/*    	if(MafUtil.empty(sessionBean) )	// 쓰기 권한 확인하여  BTN_AUTH_WRITE에 넣어줌 
    	{
	        if(MafUtil.nvl(oMbbs.getBoard().getFl_write(),"").indexOf(BoardConfig.GRP_GUEST) > -1 ) {
        	    // 등록 글에 USN이 없는 경우 (0보다 작음-1) 수정과 삭제 권한 줌(암호입력폼이용).
        	    btnAuth.append(BoardConfig.BTN_AUTH_WRITE);
        	}
	        
	    } else {
	        // 시스템 관리자 또는 게시판 관리자의 경우 
	    	MafUserManager um = MafConfig.getUserManager();
	    	
	        if(um.isSuperAdmin(sessionBean.getUtype()) || 
	                sessionBean.getUsn().equals(oMbbs.getBoard().getAdmin_usn())) {
	            btnAuth.append(BoardConfig.BTN_AUTH_WRITE);
	        } else if(MafUtil.nvl(oMbbs.getBoard().getFl_write(),"").indexOf(super.auth) > -1) {
        	    btnAuth.append(BoardConfig.BTN_AUTH_WRITE);
	        }
	        
	    } */
		if ("list".equals(cmd)) {
			if(chkWriteAuth(ob,os)) {
				logger.debug("list:" +BoardConfig.BTN_AUTH_WRITE );
				btnAuth.append(BoardConfig.BTN_AUTH_WRITE);
			}
		} else if("view".equals(cmd)) {
			if(chkWriteAuth(ob,os)) {
				logger.debug("view:" +BoardConfig.BTN_AUTH_WRITE );
				btnAuth.append(BoardConfig.BTN_AUTH_WRITE);
			}
			if(chkDeleteAuth(ob,os)) {
				logger.debug("view:" +BoardConfig.BTN_AUTH_DEL );
				btnAuth.append(BoardConfig.BTN_AUTH_DEL);
			}
			if(chkReplyAuth(ob,os)) {
				logger.debug("view:" +BoardConfig.BTN_AUTH_REPLY );
				btnAuth.append(BoardConfig.BTN_AUTH_REPLY);
			}
		} else if( "write".equals(cmd)) {
			if(chkNoticeAuth(ob,os)) {
				logger.debug("write:" +BoardConfig.BTN_AUTH_NOTICE );
				btnAuth.append(BoardConfig.BTN_AUTH_NOTICE);
			}
		}
		return btnAuth.toString();
	}
}

