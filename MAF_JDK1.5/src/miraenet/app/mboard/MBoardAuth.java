/*
 * Created on 2005. 12. 19.
 *
 * Copyright (c) 2004 (��)�̷��� All rights reserved.
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
	 * ������� �ش� �Խ����� Level�� ���� �´�. 
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
	 * �Խ����� ��ȸ(List) ���� Ȯ��
	 * @param ob
	 * @param os
	 * @return
	 */
	public static boolean chkListAuth(MbbsMetaBean ob, SessionBean  os){
		return (ob.getGrant_list() < getUserBbsLevel(ob, os) )? false:true;
	}
	
	/**
	 * �Խù� �б� ���� ��ȸ
	 * @param ob
	 * @param os
	 * @return
	 */
	public static boolean chkReadAuth(MbbsMetaBean ob, SessionBean  os){
		return (ob.getGrant_view() < getUserBbsLevel(ob, os) )? false:true;
	}
	
	/**
	 * �Խù� ���� ���� ��ȸ
	 * @param ob
	 * @param os
	 * @return
	 */
	public static boolean chkDeleteAuth(MbbsMetaBean ob, SessionBean  os){
		return (ob.getGrant_delete() < getUserBbsLevel(ob, os) )? false:true;
	}
	/**
	 * �Խù� ���� ���� ��ȸ 
	 * @param ob
	 * @param os
	 * @return
	 */
	public static boolean chkWriteAuth(MbbsMetaBean ob, SessionBean  os){
		logger.debug("ob:" + ob.getGrant_write() + ", b:" +getUserBbsLevel(ob, os)) ;
		return (ob.getGrant_write() < getUserBbsLevel(ob, os) )? false:true;
	}
	/**
	 * Notice ���� ���� ��ȸ 
	 * @param ob
	 * @param os
	 * @return
	 */
	public static boolean chkNoticeAuth(MbbsMetaBean ob, SessionBean  os){
		logger.debug("ob:" + ob.getGrant_notice() + ", b:" +getUserBbsLevel(ob, os)) ;
		return (ob.getGrant_notice() < getUserBbsLevel(ob, os) )? false:true;
	}
	/**
	 * �Խù� ���  ���� ��ȸ
	 * @param ob
	 * @param os
	 * @return
	 */
	public static boolean chkReplyAuth(MbbsMetaBean ob, SessionBean  os){
		return (ob.getGrant_reply() > getUserBbsLevel(ob, os) )? false:true;
	}
	
	public static String getBthAuth(MbbsMetaBean ob, SessionBean  os, String cmd ) {
		StringBuffer btnAuth = new StringBuffer(10);
/*    	if(MafUtil.empty(sessionBean) )	// ���� ���� Ȯ���Ͽ�  BTN_AUTH_WRITE�� �־��� 
    	{
	        if(MafUtil.nvl(oMbbs.getBoard().getFl_write(),"").indexOf(BoardConfig.GRP_GUEST) > -1 ) {
        	    // ��� �ۿ� USN�� ���� ��� (0���� ����-1) ������ ���� ���� ��(��ȣ�Է����̿�).
        	    btnAuth.append(BoardConfig.BTN_AUTH_WRITE);
        	}
	        
	    } else {
	        // �ý��� ������ �Ǵ� �Խ��� �������� ��� 
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

