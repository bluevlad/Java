package modules.wlc.classroom.tutor;

import javax.servlet.http.HttpServletResponse;

import maf.exception.MafException;
import maf.web.http.MyHttpServletRequest;
import modules.wlc.classroom.BaseClassAction;

/**
 * 강사용 Action의 Super Class 
 * @author UBQ
 *
 */
public class BaseTutorClassAction extends BaseClassAction {
	public void preProcess(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		super.preProcess(_req, _res);
	}
}