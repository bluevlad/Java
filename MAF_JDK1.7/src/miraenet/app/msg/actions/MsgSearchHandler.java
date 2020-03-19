/*
 * Created on 2005-01-03
 *
 */
package miraenet.app.msg.actions;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import maf.exception.MafException;
import maf.web.http.MyHttpServletRequest;
import miraenet.app.msg.dao.ContactDB;
import miraenet.app.msg.dao.MessageDB;
import modules.common.actions.SessionedAction;

/**
 * 쪽지 > 쪽지쓰기 > 사람검색 Command
 * 
 * 작성자 : 박광민
 * 작성된 날짜 : 2005-01-25
 */
public class MsgSearchHandler extends SessionedAction   {

	public String doWork(MyHttpServletRequest request,
			HttpServletResponse response) throws MafException {
		String selname = request.getParameter("selname");  //이름, 학과, 주소록으로 검색
		String usn = sessionBean.getUsn();  //사용자 코드 ( 주소록 소유자 )
		String nm = request.getParameter("nm");  //검색명

		// 1. 로직 코드의 실행
		List searchlist = null;
		
		if(selname.equals("3")){
			ContactDB msgmng = ContactDB.getInstance();
			searchlist = msgmng.getMsgSearchList(super.oDb, usn, nm);
		}else{
			MessageDB msgmst = MessageDB.getInstance();
			searchlist = msgmst.getMsgSearchList(super.oDb, selname, nm);
		}
		request.setAttribute("searchlist", searchlist);
		request.setAttribute("nm", nm);

		return "search";
	}
}