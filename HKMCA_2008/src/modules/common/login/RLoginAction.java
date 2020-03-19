package modules.common.login;

import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import maf.base.BaseHttpSession;
import maf.exception.MafException;
import maf.util.EnDeCrypt;
import maf.web.http.MyHttpServletRequest;
import maf.web.mvc.action.MafAction;
import maf.web.mvc.beans.ResultMessage;
import maf.web.session.MySession;
import miraenet.Project;

public class RLoginAction extends MafAction {
	private String next = null;
	private  Log logger = LogFactory.getLog(RLoginAction.class);
	private final String r_login_param = Project.RLOGIN_PARAM;
	protected void preProcess(MyHttpServletRequest _req, HttpServletResponse _res)
	        throws MafException {
		next = _req.getParameter("next", "/");
		_req.setAttribute("type", _req.getP("type"));
	}

	public void cmdLogin(MyHttpServletRequest _req, HttpServletResponse _res)
	        throws MafException {
		String forward = null;
		BaseHttpSession ssBean = null;
		try {
			
			
			String userid = _req.getParameter(r_login_param,"");

			userid = EnDeCrypt.decode(userid);
			logger.debug("rlogin : [" + userid + "]");
			
			if (userid != null) {
				ssBean = LoginManager.rLoginbyUsn(oDb, userid.toUpperCase(), _req.getRemoteAddr(),
				                                 _req.getRequestedSessionId(),
				                                 super.siteInfo.getSite());
			}
		} catch (Exception e) {
			maf.logger.Logging.trace(e);
		}
		if (ssBean != null) {
			MySession.setSession(_req, _res, ssBean);
			forward = "loginok";
			_req.setAttribute("next", next);
		} else {
			forward = "error";
			result.setMessage(new ResultMessage("common.login", "msg.loginfail"));
		}
		result.setForward(forward);
	}
}
