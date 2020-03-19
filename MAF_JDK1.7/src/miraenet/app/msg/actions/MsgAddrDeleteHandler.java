/*
 * 작성된 날짜: 2004-12-29
 *
 */
package miraenet.app.msg.actions;

import javax.servlet.http.HttpServletResponse;

import maf.exception.MafException;
import maf.web.http.MyHttpServletRequest;
import miraenet.app.msg.dao.ContactDB;
import modules.common.actions.SessionedAction;

/**
 * 쪽지 > 주소록 >  삭제 Command
 * 
 * 작성자 : 박광민
 * 작성된 날짜 : 2005-01-25
 */
public class MsgAddrDeleteHandler extends SessionedAction  {
    
	public String doWork(MyHttpServletRequest request,
			HttpServletResponse response) throws MafException {

		String usn = super.sessionBean.getUsn();					//사용자 코드 ( 주소록 소유자 )
		String[] c_usn_ck = request.getParameterValues("c_usn_ck");   //주소록 등록자 코드

		// 1. 로직 코드의 실행
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