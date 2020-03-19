/*
 * 작성된 날짜: 2005-01-04
 *
 */
package miraenet.app.msg.actions;

import javax.servlet.http.HttpServletResponse;

import maf.exception.MafException;
import maf.web.http.MyHttpServletRequest;
import miraenet.app.msg.dao.MessageDB;
import modules.common.actions.SessionedAction;

/**
 * 쪽지 > 보낸쪽지 > 삭제 Command
 * 
 * 작성자 : 박광민
 * 작성된 날짜 : 2005-01-25
 */
public class MsgSndDeleteHandler extends SessionedAction  {

	public String doWork(MyHttpServletRequest request,
			HttpServletResponse response) throws MafException {
		
		//리스트페이지에서 삭제 ( 하나 이상 )
		String[] msgid_ck = request.getParameterValues("msgid_ck");         //쪽지 번호
//		String[] usn_snd_ck = request.getParameterValues("usn_snd_ck"); //보내는 사람
		//조회페이지에서 삭제 ( 하나 )
		String msgid = request.getParameter("msgid");
		String usn_snd = request.getParameter("Dusn_snd");

		// 1. 로직 코드의 실행
//		List msgmstlist = null;
		MessageDB msgmst = MessageDB.getInstance();
		try {
			if (msgid_ck == null && msgid != null
					&& usn_snd != null) {
				msgmst.snddelete(super.oDb, msgid, usn_snd);
			} else {
				for (int i = 0; i < msgid_ck.length; i++) {
					msgid = msgid_ck[i];
//					usn_snd = usn_snd_ck[i];
					msgmst.snddelete(super.oDb, msgid, super.sessionBean.getUsn());
				}
			}
			request.setAttribute("messageKey", "msgMsg.del");
		} catch (Exception e) {
			request.setAttribute("messageKey", "msgMsg.delFail");
		}
		
		request.setAttribute("next", "/msgmst/sndlist.do");
		return "message";
	}

}