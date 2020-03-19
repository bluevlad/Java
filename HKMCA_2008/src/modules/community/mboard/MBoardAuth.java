/*
 * Created on 2005. 12. 19.
 *
 * Copyright (c) 2004 bluevlad  All rights reserved.
 */
package modules.community.mboard;

import maf.base.BaseHttpSession;
import modules.community.mboard.beans.MbbsDataBean;
import modules.community.mboard.beans.MbbsMetaBean;

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
	public static int getUserBbsLevel(MbbsMetaBean ob, BaseHttpSession os) {
		if (os == null) {
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
	public static boolean chkListAuth(MbbsMetaBean ob, BaseHttpSession os) {
		return (ob.getGrant_list() < getUserBbsLevel(ob, os)) ? false : true;
	}

	/**
	 * 게시물 읽기 권한 조회
	 * @param ob
	 * @param os
	 * @return
	 */
	public static boolean chkReadAuth(MbbsMetaBean ob, BaseHttpSession os) {
		return (ob.getGrant_view() < getUserBbsLevel(ob, os)) ? false : true;
	}

	/**
	 * 게시물 삭제 권한 조회
	 * @param ob
	 * @param os
	 * @return
	 */
	public static boolean chkDeleteAuth(MbbsMetaBean ob, BaseHttpSession os) {
		return (ob.getGrant_delete() < getUserBbsLevel(ob, os)) ? false : true;
	}

	/**
	 * 게시물 쓰기 권한 조회 
	 * @param ob
	 * @param os
	 * @return
	 */
	public static boolean chkWriteAuth(MbbsMetaBean ob, BaseHttpSession os) {
		return (ob.getGrant_write() < getUserBbsLevel(ob, os)) ? false : true;
	}

	/**
	 * Notice 쓰기 권한 조회 
	 * @param ob
	 * @param os
	 * @return
	 */
	public static boolean chkNoticeAuth(MbbsMetaBean ob, BaseHttpSession os) {
		return (ob.getGrant_notice() < getUserBbsLevel(ob, os)) ? false : true;
	}

	/**
	 * 수정 권한 조회 (본인만가능)
	 * @param ob
	 * @param os
	 * @return
	 */
	public static boolean chkWriteEdit(MbbsDataBean item, BaseHttpSession os) {
		if (os == null) {
			return false;
		} else {
			return os.getUsn().equals(item.getUsn()) ? true : false;
		}
	}

	/**
	 * 게시물 댓글  권한 조회
	 * @param ob
	 * @param os
	 * @return
	 */
	public static boolean chkReplyAuth(MbbsMetaBean ob, BaseHttpSession os) {
		return (ob.getGrant_reply() > getUserBbsLevel(ob, os)) ? false : true;
	}

	/**
	 * 각 명령별 권한을 돌려 받는다.
	 * @param ob
	 * @param os
	 * @param cmd
	 * @param item
	 * @return
	 */
	public static String getBthAuth(MbbsMetaBean ob, BaseHttpSession os, String cmd,  MbbsDataBean item) {
		StringBuffer btnAuth = new StringBuffer(10);
		if ("list".equals(cmd)) {
			if (chkWriteAuth(ob, os)) {
				btnAuth.append(BoardConfig.BTN_AUTH_WRITE);
			}
		} else if ("view".equals(cmd)) {
			if (chkWriteAuth(ob, os)) {
				btnAuth.append(BoardConfig.BTN_AUTH_WRITE);
			}
			if (chkWriteEdit(item, os)) {
				btnAuth.append(BoardConfig.BTN_AUTH_EDIT);
			}
			if (chkDeleteAuth(ob, os)) {
				btnAuth.append(BoardConfig.BTN_AUTH_DEL);
			}
			if (chkReplyAuth(ob, os)) {
				btnAuth.append(BoardConfig.BTN_AUTH_REPLY);
			}
		} else if ("write".equals(cmd)) {
			if (chkNoticeAuth(ob, os)) {
				btnAuth.append(BoardConfig.BTN_AUTH_NOTICE);
			}
		}
		return btnAuth.toString();
	}
}
