/*
 * 작성된 날짜: 2005-01-14
 * 
 *  
 */
package modules.common.login;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import maf.MafProperties;
import maf.base.BaseHttpSession;
import maf.exception.MafException;
import maf.logger.Logging;
import maf.web.TokenProcessor;
import maf.web.http.MyHttpServletRequest;
import maf.web.mvc.action.MafAction;
import maf.web.mvc.beans.ResultMessage;
import maf.web.session.MySession;
import maf.web.session.SessionPool;
import maf.web.session.beans.SessionInfoBean;
import maf.web.util.TokenLib;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author bluevlad
 *  
 */
public class LoginAction extends MafAction {

	private String next = null;
	private  static Log logger = LogFactory.getLog(LoginManager.class);

	protected void preProcess(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		next = _req.getParameter("next", "/");
		_req.setAttribute("type", _req.getP("type"));
	}

	public void cmdLogin(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		String userid = _req.getParameter("userid");
		String password = _req.getParameter("password");
		String forward = null;
		BaseHttpSession ssBean = null;

		try {
				ssBean = LoginManager.login(oDb, userid, password,  _req.getRemoteAddr(), _req.getRequestedSessionId(), super.siteInfo);
				if (MafProperties.SESSION_UNIQID_YN) {
				
				// 같은 USN으로 Login한 사용자가 잇다면... 
				if (SessionPool.isUserLogined(ssBean.getUsn())) {		
					HttpSession session = _req.getSession(false);
					SessionInfoBean sb = SessionPool.getSessionInfoByUsn(ssBean.getUsn());
					session.setAttribute("TEMP_USN", ssBean.getUsn());
					_req.setAttribute("sinfo", sb);
					_req.setAttribute("TOKEN", TokenLib.getToken(_req));
					_req.setAttribute("next", next);
					
					forward = "removesess";
				} else {
					MySession.setSession(_req, _res, ssBean);
					forward = "loginok";
					_req.setAttribute("next", next);
				} 
			} else {
				MySession.setSession(_req, _res, ssBean);
				forward = "loginok";
				_req.setAttribute("next", next);
			}

		} catch (InvalidPasswordException e) {
			forward = "error";
			result.setMessage(new ResultMessage("common.login","msg.invalidpassword", userid));
		} catch (InvalidUserIdException e) {
			forward = "error";
			result.setMessage(new ResultMessage("common.login","msg.invalidUserID", userid));
		} catch (Exception e) {
			Logging.trace(e);
			forward = "error";
			result.setMessage(new ResultMessage("common.login","msg.loginfail"));			
		} finally {

		}

		result.setForward(forward);
	}

	public void cmdForm(MyHttpServletRequest _req, HttpServletResponse _res) {

		_req.setAttribute("next", next);
		this.result.setForward("loginForm");
	}

	/**
	 * 같은 USN으로 접속된 사용자가 있을시 기존 접속자 제거후 session 등록 
	 * @param _req
	 * @param _res
	 * @throws MafException
	 */
	public void cmdRemoveSession(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		String forward = null;
		BaseHttpSession ssBean = null;
		Map rv = null;

		//		 다른 사람들이 장난 못하게 token 사용 
		if (TokenProcessor.getInstance().isTokenValid(_req, true)) {
			HttpSession session = _req.getSession(false);
			String tmp_usn = (String) session.getAttribute("TEMP_USN");

			SessionPool.removeByUsn(tmp_usn);
			forward = "loginok";
			result.setMessage(new ResultMessage("common.login", "removeSession"));
			_req.setAttribute("next", next);
			
//			String site = null;
//			if(super.siteInfo != null) {
//				site = super.siteInfo.getSite();
//			}

			ssBean = LoginManager.loginbyUsn(oDb, tmp_usn, _req.getRemoteAddr(), _req.getRequestedSessionId());
//			Logging.log(this.getClass(), "tmp_usn : " + tmp_usn);

			if (ssBean != null) {
				this.siteInfo.setSite(ssBean.getSite());
				// 같은 usn으로 loing 또 있을 까
				SessionInfoBean sb = SessionPool.getSessionInfoByUsn(ssBean.getUsn());
				if (sb != null) {
					
					session.setAttribute("TEMP_USN", rv.get("USN"));
					_req.setAttribute("sinfo", sb);
					_req.setAttribute("TOKEN", TokenLib.getToken(_req));
					forward = "removesess";
				} else {
					MySession.setSession(_req, _res, ssBean);
					forward = "loginok";
				}
			} else {
				forward = "error";
				_req.setAttribute("message", "login fail !!!");
			}
		} else {
			forward = "error";
			_req.setAttribute("message", "invalid request !!!");
		}
		this.result.setForward(forward);
	}
}