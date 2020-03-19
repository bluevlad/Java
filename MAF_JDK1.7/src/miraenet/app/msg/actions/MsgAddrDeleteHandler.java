/*
 * �ۼ��� ��¥: 2004-12-29
 *
 */
package miraenet.app.msg.actions;

import javax.servlet.http.HttpServletResponse;

import maf.exception.MafException;
import maf.web.http.MyHttpServletRequest;
import miraenet.app.msg.dao.ContactDB;
import modules.common.actions.SessionedAction;

/**
 * ���� > �ּҷ� >  ���� Command
 * 
 * �ۼ��� : �ڱ���
 * �ۼ��� ��¥ : 2005-01-25
 */
public class MsgAddrDeleteHandler extends SessionedAction  {
    
	public String doWork(MyHttpServletRequest request,
			HttpServletResponse response) throws MafException {

		String usn = super.sessionBean.getUsn();					//����� �ڵ� ( �ּҷ� ������ )
		String[] c_usn_ck = request.getParameterValues("c_usn_ck");   //�ּҷ� ����� �ڵ�

		// 1. ���� �ڵ��� ����
		ContactDB msgmng = ContactDB.getInstance();

		try {
			for (int i = 0; i < c_usn_ck.length; i++) {
				String c_usn = null;
				c_usn = c_usn_ck[i].substring(0, c_usn_ck[i].indexOf(":"));

				msgmng.addrDelete(super.oDb, usn, c_usn);
			}
			request.setAttribute("messageKey", "msgMsg.del");
		} catch (Exception e) {
			request.setAttribute("messageKey", "msgMsg.delFail");
		}
		
		request.setAttribute("next", "/msgmst/msgmnglist.do?selname=" + request.getP("selname"));
		return "message";
	}
}