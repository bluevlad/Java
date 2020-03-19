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
 * 쪽지 > 보낸쪽지 > 조회  Command
 * 
 * 작성자 : 박광민
 * 작성된 날짜 : 2005-01-25
 */
public class MsgSndViewHandler extends SessionedAction  {

	public String doWork(MyHttpServletRequest request,
			HttpServletResponse response) throws MafException {
		String msgid = request.getParameter("msgid");		  //쪽지 번호
		String usn_snd = sessionBean.getUsn(); //보내는 사람

		// 1. 로직 코드의 실행
		MessageDB msgmst = MessageDB.getInstance();
		SimpleMessageBean msgsndview = msgmst.getMsgSndview(super.oDb, usn_snd, msgid);

		// 2. 뷰에 전달할 정보 저장

		request.setAttribute("msgsndview", msgsndview);

		return "sndview";
	}

}

