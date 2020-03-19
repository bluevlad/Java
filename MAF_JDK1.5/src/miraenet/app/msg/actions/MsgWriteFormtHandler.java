/*
 * �ۼ��� ��¥: 2004-12-29
 *
 */
package miraenet.app.msg.actions;

import javax.servlet.http.HttpServletResponse;

import maf.exception.MafException;
import maf.web.http.MyHttpServletRequest;
import modules.common.actions.SessionedAction;

/**
 * ���� > ����������  Command
 * 
 * �ۼ��� : �ڱ���
 * �ۼ��� ��¥ : 2005-01-25
 */
public class MsgWriteFormtHandler extends SessionedAction  {
	public String doWork(MyHttpServletRequest request,
			HttpServletResponse response) throws MafException {

		String nm_rcv = request.getParameter("nm_snd");	//�޴� ��� �̸�
		String usn_rcv = request.getParameter("usn_snd"); //�޴� ��� �ڵ�
		
		request.setAttribute("nm_rcv", nm_rcv);    
		request.setAttribute("usn_rcv", usn_rcv); 
		
		return "writeForm";
	}
}