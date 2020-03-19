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
 * ���� > �ּҷ� > ��ü����Ʈ Command
 * 
 * �ۼ��� : �ڱ���
 * �ۼ��� ��¥ : 2005-01-25
 */
public class MsgMngListHandler extends SessionedAction {
	public String doWork(MyHttpServletRequest request,
			HttpServletResponse response) throws MafException {
		String pageNum = request.getParameter("num");
		String usn = sessionBean.getUsn();      //����� �ڵ� ( �ּҷ� ����� )
		String c_group = request.getParameter("c_group");//�׷��

		if (pageNum == null) {
			pageNum = "1";
		}
		int pageSize = 15; // ���������� ���� Row ��
		int screenSize = 10; // �ϴ� Navigator�� ���� ������ ��
		int currentPage = MafUtil.parseInt(pageNum);
//		int startRow = (currentPage - 1) * pageSize;
		int count = 0;

		// 1. ���� �ڵ��� ����
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
		// 2. �信 ������ ���� ����

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