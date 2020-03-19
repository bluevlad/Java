/*
 * �ۼ��� ��¥: 2005-01-03
 *

 */
package miraenet.app.msg.actions;

import javax.servlet.http.HttpServletResponse;

import maf.exception.MafException;
import maf.web.http.MyHttpServletRequest;
import miraenet.app.msg.beans.SimpleMessageBean;
import miraenet.app.msg.dao.MessageDB;
import modules.common.actions.SessionedAction;

/**
 * ���� > �������� > ��ȸ Command
 * 
 * �ۼ��� : �ڱ���
 * �ۼ��� ��¥ : 2005-01-25
 */
public class MsgRcvViewHandler extends SessionedAction {

	public String doWork(MyHttpServletRequest request,
			HttpServletResponse response) throws MafException {
		String msgid = request.getParameter("msgid");		//���� ��ȣ
		String usn_rcv = super.sessionBean.getUsn();  //�޴� ���

		// 1. ���� �ڵ��� ����

		MessageDB msgmst = MessageDB.getInstance();
		SimpleMessageBean msgmstlist = msgmst.getMsgRcvView(super.oDb, usn_rcv, msgid);

		// 2. �信 ������ ���� ����

		request.setAttribute("msgmstlist", msgmstlist);

		return "rcvview";
	}

}