/*
 * Created on 2005-01-10
 *
 */
package miraenet.app.msg.actions;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import maf.MafUtil;
import maf.exception.MafException;
import maf.web.http.MyHttpServletRequest;
import miraenet.app.msg.dao.ContactDB;
import miraenet.app.msg.dao.MessageDB;
import modules.common.actions.SessionedAction;

/**
 * 쪽지 > 주소록 > 친구등록 > 사람 검색 Command
 * 
 * 작성자 : 박광민
 * 작성된 날짜 : 2005-01-25
 */
public class MsgGrpSearchHandler extends SessionedAction  {
    
	public String doWork(MyHttpServletRequest request,
			HttpServletResponse response) throws MafException {
		
		String selname = request.getParameter("selname");  //이름, 학과로 검색
		String nm = request.getParameter("nm");					  //검색명

		// 1. 로직 코드의 실행
		List searchlist = null;
		MessageDB msgmst = MessageDB.getInstance();

		searchlist = msgmst.getMsgSearchList(super.oDb, selname, nm);

		request.setAttribute("searchlist", searchlist);
		request.setAttribute("nm", nm);

		String pageNum = request.getParameter("num");    
		String usn = super.sessionBean.getUsn();		//사용자 코드
		String c_group = request.getParameter("c_group"); //그룹명

		if (pageNum == null) {
			pageNum = "1";
		}
		int pageSize = 15; // 한페이지에 나올 Row 수
		int screenSize = 10; // 하단 Navigator에 나올 페이지 수
		int currentPage = MafUtil.parseInt(pageNum); //현재페이지
		int count = 0;

		// 1. 로직 코드의 실행

		List msggrplist = null;
		try {
			ContactDB msgmng = ContactDB.getInstance();
			count = msgmng.getGrpCount(super.oDb, usn, c_group);

			if (count > 0) {
				msggrplist = msgmng.getGrplist(super.oDb, usn);
			} else {
				msggrplist = Collections.EMPTY_LIST;
			}
		} catch (Exception e) {
			throw new MafException(e);

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
		request.setAttribute("msggrplist", msggrplist);

		return "addrInsert";
	}
}