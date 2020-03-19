/*
 * Created on 2005-01-10
 *
 */
package miraenet.app.msg.actions;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import maf.exception.MafException;
import maf.web.http.MyHttpServletRequest;
import miraenet.app.msg.dao.ContactDB;
import modules.common.actions.SessionedAction;

/**
 * ���� > �ּҷ� > �׷캰 ����Ʈ Command
 * 
 * �ۼ��� : �ڱ���
 * �ۼ��� ��¥ : 2005-01-25
 */
public class MsgGrpListHandler extends SessionedAction   {
    
	public String doWork(MyHttpServletRequest request,
			HttpServletResponse response) throws MafException {

		String selectgrp = request.getParameter("selectgrp");  //�׷��
		String usn = super.sessionBean.getUsn();      	 //����� �ڵ� ( �ּҷ� ������ )
		// 1. ���� �ڵ��� ����
		int count = 0;
		List msgmnglist = null;
		List msggrplist = null;

		try {
			ContactDB msgmng = ContactDB.getInstance();
			count = msgmng.getGrpCount(super.oDb, usn, selectgrp);  

			if (count > 0) {  
				msgmnglist = msgmng.getMsgGrpList(super.oDb, usn, selectgrp);
				msggrplist = msgmng.getGrplist(super.oDb, usn);
			} else {
				msgmnglist = Collections.EMPTY_LIST;
			}
		} catch (Exception e) {
			throw new MafException(e);

		}
		request.setAttribute("count", new Integer(count));
		request.setAttribute("msgmnglist", msgmnglist);
		request.setAttribute("msggrplist", msggrplist);

		return "msgmnglist";
	}
}