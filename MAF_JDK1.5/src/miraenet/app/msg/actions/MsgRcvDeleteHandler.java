/*
 * 작성된 날짜: 2004-12-29
 *
 */
package miraenet.app.msg.actions;

import javax.servlet.http.HttpServletResponse;

import maf.exception.MafException;
import maf.web.http.MyHttpServletRequest;
import miraenet.app.msg.dao.MessageDB;
import modules.common.actions.SessionedAction;

/**
 * 쪽지 > 받은쪽지 > 삭제 Command
 * 
 * 작성자 : 박광민
 * 작성된 날짜 : 2005-01-25
 */
public class MsgRcvDeleteHandler extends SessionedAction   {
	public String doWork(MyHttpServletRequest request,
			HttpServletResponse response) throws MafException {
		
	    //리스트페이지에서 삭제 ( 하나 이상 )
		String[] msgid_ck = request.getParameterValues("msgid_ck");  		//쪽지 번호

		//조회페이지에서 삭제 ( 하나 )
		String msgid = request.getParameter("msgid");
		String usn_rcv = sessionBean.getUsn();

		// 1. 로직 코드의 실행
//		List msgmstlist = null;
		MessageDB msgmst = MessageDB.getInstance();
		try {
			if (msgid_ck == null  && msgid != null ) {
				msgmst.rcvdelete(super.oDb, msgid, usn_rcv);
			} else {
				for (int i = 0; i < msgid_ck.length; i++) {
					msgid = msgid_ck[i];
					msgmst.rcvdelete(super.oDb, msgid, usn_rcv);
				}
			}
			request.setAttribute("messageKey", "msgMsg.del");
		} catch (Exception e) {
			request.setAttribute("messageKey", "msgMsg.delFail");
		}
		
		request.setAttribute("next", "/msgmst/rcvlist.do");
		return "message";
	}

}