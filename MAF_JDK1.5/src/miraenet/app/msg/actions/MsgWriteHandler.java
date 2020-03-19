/*
 * �ۼ��� ��¥: 2004-12-29
 *
 * 
 * â - ȯ�� ���� - Java - �ڵ� ��Ÿ�� - �ڵ� ���ø�Ʈ
 */
package miraenet.app.msg.actions;

import java.util.StringTokenizer;

import javax.servlet.http.HttpServletResponse;

import maf.exception.MafException;
import maf.web.http.MyHttpServletRequest;
import miraenet.app.msg.beans.SimpleMessageBean;
import miraenet.app.msg.dao.MessageDB;
import modules.common.actions.SessionedAction;

/**
 * ���� > ��������  Command
 * 
 * �ۼ��� : �ڱ���
 * �ۼ��� ��¥ : 2005-01-25
 */
public class MsgWriteHandler  extends SessionedAction {
	public String doWork (MyHttpServletRequest request,
			HttpServletResponse response) throws MafException {

		SimpleMessageBean msg = new SimpleMessageBean();
		String nm_snd = sessionBean.getNm();	 //������ ��� �̸�
		String usn_snd = sessionBean.getUsn();//������ ��� �ڵ� 
		String nm_rcv = request.getParameter("nm_rcv");		 //�޴� ��� �̸�
		String usn_rcv = request.getParameter("usn_rcv");	 //�޴� ��� �ڵ�
		String title = request.getParameter("title");					 //����
		String contents = request.getParameter("contents"); //����

		StringTokenizer st1 = new StringTokenizer(nm_rcv, ",");
		StringTokenizer st2 = new StringTokenizer(usn_rcv, ";");
		MessageDB db = MessageDB.getInstance();
		try {
			oDb.setAutoCommit(false);
			
			while (st1.hasMoreTokens()) {
				nm_rcv = st1.nextToken();

				st2.hasMoreTokens();
				usn_rcv = st2.nextToken();

				msg.setNm_snd(nm_snd);
				msg.setUsn_snd(usn_snd);
				msg.setNm_rcv(nm_rcv);
				msg.setUsn_rcv(usn_rcv);
				msg.setTitle(title);
				db.insert(oDb, msg, contents);
			}
			
			oDb.commit();
			oDb.setAutoCommit(true);
			request.setAttribute("messageKey", "msgMsg.snd");
		} catch (Exception e) {
		    oDb.rollback();
			request.setAttribute("messageKey", "msgMsg.sndFail");

		}
		db = null;
		
		request.setAttribute("next", "/msgmst/sndlist.do");
		return "message";
	}
}