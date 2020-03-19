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
	 * ������� �ش� �Խ����� Level�� ���� �´�. 
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
	 * �Խ����� ��ȸ(List) ���� Ȯ��
	 * @param ob
	 * @param os
	 * @return
	 */
	public static boolean chkListAuth(MbbsMetaBean ob, BaseHttpSession os) {
		return (ob.getGrant_list() < getUserBbsLevel(ob, os)) ? false : true;
	}

	/**
	 * �Խù� �б� ���� ��ȸ
	 * @param ob
	 * @param os
	 * @return
	 */
	public static boolean chkReadAuth(MbbsMetaBean ob, BaseHttpSession os) {
		return (ob.getGrant_view() < getUserBbsLevel(ob, os)) ? false : true;
	}

	/**
	 * �Խù� ���� ���� ��ȸ
	 * @param ob
	 * @param os
	 * @return
	 */
	public static boolean chkDeleteAuth(MbbsMetaBean ob, BaseHttpSession os) {
		return (ob.getGrant_delete() < getUserBbsLevel(ob, os)) ? false : true;
	}

	/**
	 * �Խù� ���� ���� ��ȸ 
	 * @param ob
	 * @param os
	 * @return
	 */
	public static boolean chkWriteAuth(MbbsMetaBean ob, BaseHttpSession os) {
		return (ob.getGrant_write() < getUserBbsLevel(ob, os)) ? false : true;
	}

	/**
	 * Notice ���� ���� ��ȸ 
	 * @param ob
	 * @param os
	 * @return
	 */
	public static boolean chkNoticeAuth(MbbsMetaBean ob, BaseHttpSession os) {
		return (ob.getGrant_notice() < getUserBbsLevel(ob, os)) ? false : true;
	}

	/**
	 * ���� ���� ��ȸ (���θ�����)
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
	 * �Խù� ���  ���� ��ȸ
	 * @param ob
	 * @param os
	 * @return
	 */
	public static boolean chkReplyAuth(MbbsMetaBean ob, BaseHttpSession os) {
		return (ob.getGrant_reply() > getUserBbsLevel(ob, os)) ? false : true;
	}

	/**
	 * �� ��ɺ� ������ ���� �޴´�.
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
