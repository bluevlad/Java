/*
 * Created on 2005-01-03
 *
 */
package miraenet.app.msg.actions;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import maf.exception.MafException;
import maf.web.http.MyHttpServletRequest;
import miraenet.app.msg.dao.ContactDB;
import miraenet.app.msg.dao.MessageDB;
import modules.common.actions.SessionedAction;

/**
 * ���� > �������� > ����˻� Command
 * 
 * �ۼ��� : �ڱ���
 * �ۼ��� ��¥ : 2005-01-25
 */
public class MsgSearchHandler extends SessionedAction   {

	public String doWork(MyHttpServletRequest request,
			HttpServletResponse response) throws MafException {
		String selname = request.getParameter("selname");  //�̸�, �а�, �ּҷ����� �˻�
		String usn = sessionBean.getUsn();  //����� �ڵ� ( �ּҷ� ������ )
		String nm = request.getParameter("nm");  //�˻���

		// 1. ���� �ڵ��� ����
		List searchlist = null;
		
		if(selname.equals("3")){
			ContactDB msgmng = ContactDB.getInstance();
			searchlist = msgmng.getMsgSearchList(super.oDb, usn, nm);
		}else{
			MessageDB msgmst = MessageDB.getInstance();
			searchlist = msgmst.getMsgSearchList(super.oDb, selname, nm);
		}
		request.setAttribute("searchlist", searchlist);
		request.setAttribute("nm", nm);

		return "search";
	}
}