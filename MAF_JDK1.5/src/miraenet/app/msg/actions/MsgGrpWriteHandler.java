/*
 * Created on 2005-01-11
 *
 */
package miraenet.app.msg.actions;

import javax.servlet.http.HttpServletResponse;

import maf.exception.MafException;
import maf.web.http.MyHttpServletRequest;
import modules.common.actions.SessionedAction;

/**
 * ���� >�ּҷ� > �׷캰 �������� Command
 * 
 * �ۼ��� : �ڱ���
 * �ۼ��� ��¥ : 2005-01-25
 */
public class MsgGrpWriteHandler extends SessionedAction {
	public String doWork(MyHttpServletRequest request,
			HttpServletResponse response) throws MafException {

		String[] c_usn_ck = request.getParameterValues("c_usn_ck");  //�ּҷ� ����� 

		// 1. ���� �ڵ��� ����
		String usn_rcv = c_usn_ck[0].substring(0, c_usn_ck[0].indexOf(":"));  //����� �ڵ�
		String nm_rcv = c_usn_ck[0].substring(c_usn_ck[0].indexOf(":") + 1, c_usn_ck[0].length());  //����� �̸�

		for (int i = 1; i < c_usn_ck.length; i++) {
			usn_rcv = usn_rcv + ";" + c_usn_ck[i].substring(0, c_usn_ck[i].indexOf(":"));
			nm_rcv = nm_rcv+ ","+ c_usn_ck[i].substring(c_usn_ck[i].indexOf(":") + 1, c_usn_ck[i].length());
		}

		request.setAttribute("nm_rcv", nm_rcv);
		request.setAttribute("usn_rcv", usn_rcv);

		return "writeForm";
	}
}