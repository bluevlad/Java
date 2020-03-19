/*
 * Created on 2005-01-04
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
import modules.common.actions.SessionedAction;

/**
 * 쪽지 > 주소록 > 전체리스트 Command
 * 
 * 작성자 : 박광민
 * 작성된 날짜 : 2005-01-25
 */
public class MsgMngListHandler extends SessionedAction {
	public String doWork(MyHttpServletRequest request,
			HttpServletResponse response) throws MafException {
		String pageNum = request.getParameter("num");
		String usn = sessionBean.getUsn();      //사용자 코드 ( 주소록 등록자 )
		String c_group = request.getParameter("c_group");//그룹명

		if (pageNum == null) {
			pageNum = "1";
		}
		int pageSize = 15; // 한페이지에 나올 Row 수
		int screenSize = 10; // 하단 Navigator에 나올 페이지 수
		int currentPage = MafUtil.parseInt(pageNum);
//		int startRow = (currentPage - 1) * pageSize;
		int count = 0;

		// 1. 로직 코드의 실행
		List msgmnglist = null;
		List msggrplist = null;
		try {
			ContactDB msgmst = ContactDB.getInstance();
			count = msgmst.getGrpCount(super.oDb, usn, c_group);
//			System.out.println("count===>>" + count + ", " + usn);

			if (count > 0) {
				msgmnglist = msgmst.getMstMnglist(super.oDb, usn);
				msggrplist = msgmst.getGrplist(super.oDb, usn);
			} else {
				msgmnglist = Collections.EMPTY_LIST;
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
		request.setAttribute("msgmnglist", msgmnglist);
		request.setAttribute("msggrplist", msggrplist);

		return "msgmnglist";
	}
}