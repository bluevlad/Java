/*
 * �ۼ��� ��¥: 2005-01-04
 *
 */
package miraenet.app.msg.actions;

import javax.servlet.http.HttpServletResponse;

import maf.exception.MafException;
import maf.web.http.MyHttpServletRequest;
import miraenet.app.msg.dao.MessageDB;
import modules.common.actions.SessionedAction;

/**
 * ���� > �������� > ���� Command
 * 
 * �ۼ��� : �ڱ���
 * �ۼ��� ��¥ : 2005-01-25
 */
public class MsgSndDeleteHandler extends SessionedAction  {

	public String doWork(MyHttpServletRequest request,
			HttpServletResponse response) throws MafException {
		
		//����Ʈ���������� ���� ( �ϳ� �̻� )
		String[] msgid_ck = request.getParameterValues("msgid_ck");         //���� ��ȣ
//		String[] usn_snd_ck = request.getParameterValues("usn_snd_ck"); //������ ���
		//��ȸ���������� ���� ( �ϳ� )
		String msgid = request.getParameter("msgid");
		String usn_snd = request.getParameter("Dusn_snd");

		// 1. ���� �ڵ��� ����
//		List msgmstlist = null;
		MessageDB msgmst = MessageDB.getInstance();
		try {
			if (msgid_ck == null && msgid != null
					&& usn_snd != null) {
				msgmst.snddelete(super.oDb, msgid, usn_snd);
			} else {
				for (int i = 0; i < msgid_ck.length; i++) {
					msgid = msgid_ck[i];
//					usn_snd = usn_snd_ck[i];
					msgmst.snddelete(super.oDb, msgid, super.sessionBean.getUsn());
				}
			}
			request.setAttribute("messageKey", "msgMsg.del");
		} catch (Exception e) {
			request.setAttribute("messageKey", "msgMsg.delFail");
		}
		
		request.setAttribute("next", "/msgmst/sndlist.do");
		return "message";
	}

}