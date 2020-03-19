/*
 * 작성된 날짜: 2005-02-02
 *
 */
package miraenet.app.dbadmin.actions;

import javax.servlet.http.HttpServletResponse;

import maf.web.http.MyHttpServletRequest;
import maf.web.mvc.beans.ResultMessage;
import miraenet.app.dbadmin.beans.DbUserBean;

/**
 * @author goindole, 2005-02-02
 * 
 */
public class Top extends DbAdminAction {
	public void doWork(MyHttpServletRequest _req, HttpServletResponse response) {
		try {
			DbUserBean[] users = super.oa.getUserList(super.oDb);

			result.setAttribute("users", users);
			result.setForward("top");
		} catch (Exception e) {
			result.setSuccess(false, new ResultMessage(e.getMessage()));
		}
	}
}