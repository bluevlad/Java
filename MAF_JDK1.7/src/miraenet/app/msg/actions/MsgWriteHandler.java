/*
 * 작성된 날짜: 2004-12-29
 *
 * 
 * 창 - 환경 설정 - Java - 코드 스타일 - 코드 템플리트
 */
package miraenet.app.msg.actions;

import java.util.StringTokenizer;

import javax.servlet.http.HttpServletResponse;

import maf.exception.MafException;
import maf.web.http.MyHttpServletRequest;
import miraenet.app.msg.beans.SimpleMessageBean;
import miraenet.app.msg.dao.MessageDB;
import modules.common.actions.SessionedAction;

/**
 * 쪽지 > 쪽지쓰기  Command
 * 
 * 작성자 : 박광민
 * 작성된 날짜 : 2005-01-25
 */
public class MsgWriteHandler  extends SessionedAction {
	public String doWork (MyHttpServletRequest request,
			HttpServletResponse response) throws MafException {

		SimpleMessageBean msg = new SimpleMessageBean();
		String nm_snd = sessionBean.getNm();	 //보내는 사람 이름
		String usn_snd = sessionBean.getUsn();//보내는 사람 코드 
		String nm_rcv = request.getParameter("nm_rcv");		 //받는 사람 이름
		String usn_rcv = request.getParameter("usn_rcv");	 //받는 사람 코드
		String title = request.getParameter("title");					 //제목
		String contents = request.getParameter("contents"); //내용

		StringTokenizer st1 = new StringTokenizer(nm_rcv, ",");
		StringTokenizer st2 = new StringTokenizer(usn_rcv, ";");
		MessageDB db = MessageDB.getInstance();
		try {
			oDb.setAutoCommit(false);
			
			while (st1.hasMoreTokens()) {
				nm_rcv = st1.nextToken();

				st2.hasMoreTokens();
				usn_rcv = st2.nextToken();

				msg.setNm_snd(nm_snd);
				msg.setUsn_snd(usn_snd);
				msg.setNm_rcv(nm_rcv);
				msg.setUsn_rcv(usn_rcv);
				msg.setTitle(title);
				db.insert(oDb, msg, contents);
			}
			
			oDb.commit();
			oDb.setAutoCommit(true);
			request.setAttribute("messageKey", "msgMsg.snd");
		} catch (Exception e) {
		    oDb.rollback();
			request.setAttribute("messageKey", "msgMsg.sndFail");

		}
		db = null;
		
		request.setAttribute("next", "/msgmst/sndlist.do");
		return "message";
	}
}