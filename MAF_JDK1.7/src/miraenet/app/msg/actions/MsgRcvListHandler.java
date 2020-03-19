/*
 * �ۼ��� ��¥: 2005-01-03
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
 * ���� > �������� > ����Ʈ Command
 * 
 * �ۼ��� : �ڱ���
 * �ۼ��� ��¥ : 2005-01-25
 */
public class MsgRcvListHandler extends SessionedAction    {

	public String doWork(MyHttpServletRequest request,
			HttpServletResponse response) throws MafException {
		String pageNum = request.getParameter("num");
		//String usn_rcv = request.getParameter("usn_rcv");  //�޴� ���
		String usn_rcv = sessionBean.getUsn();

		if (pageNum == null) {
			pageNum = "1";
		}
		int pageSize = 15; // ���������� ���� Row ��
		int screenSize = 10; // �ϴ� Navigator�� ���� ������ ��
		int currentPage = MafUtil.parseInt(pageNum);//����������
		int startRow = (currentPage - 1) * pageSize;//���� Row
		int count = 0;

		// 1. ���� �ڵ��� ����
		List msgmstlist = null;
		MessageDB msgmst = MessageDB.getInstance();
		count = msgmst.getRcvCount(super.oDb, usn_rcv);

		if (count > 0) {
			msgmstlist = msgmst.getMsgRcvlist(super.oDb, usn_rcv, pageSize, startRow);
		} else {
			msgmstlist = Collections.EMPTY_LIST;
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
		request.setAttribute("msgmstlist", msgmstlist);

		return "rcvlist";
	}

}