package modules.wlc.classroom.learner;

import javax.servlet.http.HttpServletResponse;
import maf.exception.MafException;
import maf.web.http.MyHttpServletRequest;
import modules.wlc.classroom.BaseClassAction;
import modules.wlc.classroom.support.LecInfoBean;

/**
 * 강의실 학생용 Action의 Super Class
 * @author bluevlad
 *
 */
public class BaseLearnerClassAction extends BaseClassAction {



	protected LecInfoBean lecReqInfo = null;

	public void preProcess(MyHttpServletRequest _req, HttpServletResponse _res)
			throws MafException {
		super.preProcess(_req, _res);
		
		this.lecReqInfo = new LecInfoBean(super.sessionBean);
		lecReqInfo.setLec_nm(super.lectureInfo.getLec_nm());
		_req.setAttribute("lecInfo", this.lecReqInfo);
		
		
	}

	
}
