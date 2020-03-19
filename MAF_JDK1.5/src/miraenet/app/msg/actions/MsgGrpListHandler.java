/*
 * Created on 2005-01-10
 *
 */
package miraenet.app.msg.actions;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import maf.exception.MafException;
import maf.web.http.MyHttpServletRequest;
import miraenet.app.msg.dao.ContactDB;
import modules.common.actions.SessionedAction;

/**
 * 쪽지 > 주소록 > 그룹별 리스트 Command
 * 
 * 작성자 : 박광민
 * 작성된 날짜 : 2005-01-25
 */
public class MsgGrpListHandler extends SessionedAction   {
    
	public String doWork(MyHttpServletRequest request,
			HttpServletResponse response) throws MafException {

		String selectgrp = request.getParameter("selectgrp");  //그룹명
		String usn = super.sessionBean.getUsn();      	 //사용자 코드 ( 주소록 소유자 )
		// 1. 로직 코드의 실행
		int count = 0;
		List msgmnglist = null;
		List msggrplist = null;

		try {
			ContactDB msgmng = ContactDB.getInstance();
			count = msgmng.getGrpCount(super.oDb, usn, selectgrp);  

			if (count > 0) {  
				msgmnglist = msgmng.getMsgGrpList(super.oDb, usn, selectgrp);
				msggrplist = msgmng.getGrplist(super.oDb, usn);
			} else {
				msgmnglist = Collections.EMPTY_LIST;
			}
		} catch (Exception e) {
			throw new MafException(e);

		}
		request.setAttribute("count", new Integer(count));
		request.setAttribute("msgmnglist", msgmnglist);
		request.setAttribute("msggrplist", msggrplist);

		return "msgmnglist";
	}
}