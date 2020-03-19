package modules.wlc.classroom;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import maf.MafConstant;
import maf.exception.MafException;
import maf.util.SessionUtil;
import maf.web.http.MyHttpServletRequest;
import maf.web.mvc.action.MafAction;
import maf.web.util.SerializeHashtable;
import modules.wlc.classroom.beans.LectureInfo;
import modules.wlc.classroom.support.LecUtil;

/**
 * 강의실 공통 Action 
 * @author bluevlad
 *
 */
public class BaseClassAction extends MafAction {
	/**
	 * 강의 기본 정보
	 */
	protected LectureInfo lectureInfo = null;
	protected SerializeHashtable listOp = null;
    final private Log  logger = LogFactory.getLog(this.getClass());

	/**
	 * 강의 기본 정보를 setup 한다.
	 * TODO: 권한 체크 추가 
	 */
	public void preProcess(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		super.chkLogin();
		this.listOp = new SerializeHashtable(_req.getParameter(MafConstant.LIST_OP_NAME));
		Map param = new HashMap();
		String lec_cd = _req.getP("lec_cd");
		if (!"".equals(lec_cd) && lec_cd != null) {
			param.put("lec_cd", lec_cd);
			SessionUtil.setLec_cd(super.sessionBean, lec_cd);
		} else{
			param.put("lec_cd", SessionUtil.getLec_cd(super.sessionBean));
		}
		this.lectureInfo =  LecUtil.getLectureInfo(super.oDb, param);
		SessionUtil.setSjt_cd(super.sessionBean, lectureInfo.getSjt_cd());
		SessionUtil.setLec_num(super.sessionBean, lectureInfo.getLec_num());
	}

	/**
	 * 강의 기본 정보를 돌려 준다.
	 */
	public void postProcess(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		result.setAttribute(MafConstant.LIST_OP_NAME, listOp);
		result.setAttribute("Lecture", this.lectureInfo);
		result.setAttribute("lec_cd", this.lectureInfo.getLec_cd());
	}
}
