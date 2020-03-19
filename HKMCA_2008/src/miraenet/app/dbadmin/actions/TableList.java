/*
 * 작성된 날짜: 2005-02-02
 *
 */
package miraenet.app.dbadmin.actions;

import javax.servlet.http.HttpServletResponse;

import maf.web.http.MyHttpServletRequest;
import maf.web.mvc.beans.ResultMessage;


/**
 * @author Rainend
 * 
 */
public class TableList extends DbAdminAction {
	public void doWork(MyHttpServletRequest _req, HttpServletResponse response) {
		String type = _req.getParameter("type", "table");
		try {
			if ("view".equals(type)) {
				result.setAttribute("tables", super.oa.getViewList(super.oDb, super.owner));
			} else if ("procs".equals(type)) {
				result.setAttribute("tables", super.oa.getProcList(super.oDb, super.owner));
			} else {
				result.setAttribute("tables", super.oa.getTableList(super.oDb, super.owner));
			}
			result.setForward("tableList");
		} catch (Exception e) {
			result.setSuccess(false, new ResultMessage(e.getMessage()));
		}

	}
}
