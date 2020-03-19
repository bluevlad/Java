/*
 * 작성된 날짜: 2005-01-03
 *
 */
package miraenet.app.msg.actions;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import maf.MafUtil;
import maf.exception.MafException;
import maf.web.http.MyHttpServletRequest;
import miraenet.app.msg.dao.MessageDB;
import modules.common.actions.SessionedAction;

/**
 * 쪽지 > 받은쪽지 > 리스트 Command
 * 
 * 작성자 : 박광민
 * 작성된 날짜 : 2005-01-25
 */
public class MsgRcvListHandler extends SessionedAction    {

	public String doWork(MyHttpServletRequest request,
			HttpServletResponse response) throws MafException {
		String pageNum = request.getParameter("num");
		//String usn_rcv = request.getParameter("usn_rcv");  //받는 사람
		String usn_rcv = sessionBean.getUsn();

		if (pageNum == null) {
			pageNum = "1";
		}
		int pageSize = 15; // 한페이지에 나올 Row 수
		int screenSize = 10; // 하단 Navigator에 나올 페이지 수
		int currentPage = MafUtil.parseInt(pageNum);//현재페이지
		int startRow = (currentPage - 1) * pageSize;//시작 Row
		int count = 0;

		// 1. 로직 코드의 실행
		List msgmstlist = null;
		MessageDB msgmst = MessageDB.getInstance();
		count = msgmst.getRcvCount(super.oDb, usn_rcv);

		if (count > 0) {
			msgmstlist = msgmst.getMsgRcvlist(super.oDb, usn_rcv, pageSize, startRow);
		} else {
			msgmstlist = Collections.EMPTY_LIST;
		}
		// 2. 뷰에 전달할 정보 저장

		int pageCount = count / pageSize + (count % pageSize == 0 ? 0 : 1);
		int startPage = (currentPage - 1) / screenSize * screenSize + 1;
		int endPage = startPage + screenSize - 1;
		if (endPage > pageCount) {
			endPage = pageCount;
		}
		request.setAttribute("currentPage", new Integer(currentPage));
		request.setAttribute("count", new Integer(count));
		request.setAttribute("pageCount", new Integer(pageCount));
		request.setAttribute("pageSize", new Integer(pageSize));
		request.setAttribute("screenSize", new Integer(screenSize));
		request.setAttribute("startPage", new Integer(startPage));
		request.setAttribute("endPage", new Integer(endPage));
		request.setAttribute("msgmstlist", msgmstlist);

		return "rcvlist";
	}

}