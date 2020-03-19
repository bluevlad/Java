/*
 * Created on 2005-01-03
 *
 */
package miraenet.app.msg.actions;

import javax.servlet.http.HttpServletResponse;

import maf.exception.MafException;
import maf.web.http.MyHttpServletRequest;
import modules.common.actions.SessionedAction;

/**
 * 쪽지 > 쪽지쓰기 > 사람검색폼  Command
 * 
 * 작성자 : 박광민
 * 작성된 날짜 : 2005-01-25
 */
public class MsgSearchFormHandler extends SessionedAction  {
	public String doWork(MyHttpServletRequest request,
			HttpServletResponse response) throws MafException {
		
		String usn_rcv = request.getParameter("usn_rcv");
		String nm_rcv = request.getParameter("nm_rcv");
		request.setAttribute("usn_rcv", usn_rcv);
		request.setAttribute("nm_rcv", nm_rcv);
		
//		Logging.log(this.getClass(), ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
//		Logging.log(this.getClass(), "usn_rcv : " + usn_rcv);
//		Logging.log(this.getClass(), "nm_rcv : " + nm_rcv);
//		Logging.log(this.getClass(), "<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
		
		return "searchForm";
	}
}