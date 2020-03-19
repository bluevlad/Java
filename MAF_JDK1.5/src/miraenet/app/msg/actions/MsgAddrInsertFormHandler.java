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
import modules.common.actions.SessionedAction;


/**
 * 쪽지 > 주소록 > 친구등록폼 Command
 * 
 * 작성자 : 박광민
 * 작성된 날짜 : 2005-01-25
 */
public class MsgAddrInsertFormHandler extends SessionedAction  {
//    private Logger logger = Logger.getLogger(MsgAddrInsertFormHandler.class);

	public String doWork(MyHttpServletRequest request,
			HttpServletResponse response) throws MafException {

		String pageNum = request.getParameter("num"); 		//페이지 수
		//String usn = request.getParameter("usn");  			//사용자 코드 ( 주소록 소유자 )
		String usn = sessionBean.getUsn();

		String c_group = request.getParameter("c_group");  	//그룹명

		if (pageNum == null) {
			pageNum = "1";
		}
		int pageSize = 15; 														//한페이지에 나올 Row 수
		int screenSize = 10; 													//하단 Navigator에 나올 페이지 수
		int currentPage = MafUtil.parseInt(pageNum);				//현재페이지
//		int startRow = (currentPage - 1) * pageSize;				//시작 Row
		int count = 0;

		// 1. 로직 코드의 실행
		List msggrplist = null;
		try {
			ContactDB msgmst = ContactDB.getInstance();
			count = msgmst.getGrpCount(super.oDb, usn, c_group); 

			if (count > 0) {

				msggrplist = msgmst.getGrplist(super.oDb, usn);
			} else {
				msggrplist = Collections.EMPTY_LIST;
			}
		} catch (Exception e) {
			throw new MafException(e);

		}
		// 2. 뷰에 전달할 정보 저장
		int pageCount = count / pageSize + (count % pageSize == 0 ? 0 : 1); //페이지 수
		int startPage = (currentPage - 1) / screenSize * screenSize + 1;      //시작 페이지
		int endPage = startPage + screenSize - 1;										   //마지막 페이지
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

		System.out.println("addrInsertForm");
		return "addrInsert";
	}
}