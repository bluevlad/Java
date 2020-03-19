/*
 * Created on 2005-01-07
 *
 */
package miraenet.app.msg.actions;

import javax.servlet.http.HttpServletResponse;

import maf.exception.MafException;
import maf.logger.Logging;
import maf.web.http.MyHttpServletRequest;
import miraenet.app.msg.beans.ContactBean;
import miraenet.app.msg.dao.ContactDB;
import modules.common.actions.SessionedAction;

/**
 * 쪽지 > 주소록 > 친구등록 Command
 * 
 * 작성자 : 박광민
 * 작성된 날짜 : 2005-01-25
 */
public class MsgAddrInsertHandler extends SessionedAction  {

	public String doWork(MyHttpServletRequest request,
			HttpServletResponse response) throws MafException {
		
		ContactDB db = ContactDB.getInstance();

		ContactBean addr = new ContactBean();
		String usn = super.sessionBean.getUsn();						    //사용자 코드 ( 주소록 소유자 )
		String[] c_usn_ck = request.getParameterValues("c_usn_ck");  //주소록 등록자 코드
		String c_group = request.getParameter("selectgrp");				   //그룹명
		try {
			for (int i = 0; i < c_usn_ck.length; i++) {
				String c_usn = null;
				c_usn = c_usn_ck[i];

				addr.setUsn(usn);
				addr.setC_usn(c_usn);
				addr.setC_group(c_group);

				db.insert(super.oDb, addr);
			}
			request.setAttribute("messageKey", "msgMsg.insrt");
		} catch (Exception e) {
			Logging.trace(e);
			request.setAttribute("messageKey", "msgMsg.insrtFail");
		}
		
		request.setAttribute("next", "/msgmst/msgmnglist.do");
		return "message";
	}
}