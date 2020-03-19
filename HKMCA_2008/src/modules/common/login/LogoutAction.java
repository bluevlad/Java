/*
 * 작성된 날짜: 2005-01-14
 *

 */
package modules.common.login;

import javax.servlet.http.HttpServletResponse;

import maf.exception.MafException;
import maf.web.http.MyHttpServletRequest;
import maf.web.mvc.action.BaseMafCommand;

/**
 * @author bluevlad
 *
 */
public class LogoutAction extends BaseMafCommand {

	public void process(MyHttpServletRequest request, HttpServletResponse response) throws MafException {
		LoginManager.logOut(request, response);
		super.result.setForward("logoutok");
	}
}
