/*
 * 작성된 날짜: 2005-01-03
 *

 */
package miraenet.app.msg.actions;

import javax.servlet.http.HttpServletResponse;

import maf.exception.MafException;
import maf.web.http.MyHttpServletRequest;
import miraenet.app.msg.beans.SimpleMessageBean;
import miraenet.app.msg.dao.MessageDB;
import modules.common.actions.SessionedAction;

/**
 * 쪽지 > 받은쪽지 > 조회 Command
 * 
 * 작성자 : 박광민
 * 작성된 날짜 : 2005-01-25
 */
public class MsgRcvViewHandler extends SessionedAction {

	public String doWork(MyHttpServletRequest request,
			HttpServletResponse response) throws MafException {
		String msgid = request.getParameter("msgid");		//쪽지 번호
		String usn_rcv = super.sessionBean.getUsn();  //받는 사람

		// 1. 로직 코드의 실행

		MessageDB msgmst = MessageDB.getInstance();
		SimpleMessageBean msgmstlist = msgmst.getMsgRcvView(super.oDb, usn_rcv, msgid);

		// 2. 뷰에 전달할 정보 저장

		request.setAttribute("msgmstlist", msgmstlist);

		return "rcvview";
	}

}