package modules.wlc.classroom.tutor;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;


import maf.exception.MafException;
import maf.web.http.MyHttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 강좌용 시험 관리자 
 * @author ubq
 *
 */
public class StatAction extends BaseTutorClassAction{
	private Log logger = LogFactory.getLog(StatAction.class);
	private final String MESSAGE_BUNDLENAME = "common.message";
    
    /**
     * 시험 성적 보기 
     * @param _req
     * @param _res
     * @throws MafException
     */
    public void cmdView(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {

    	Map param = new HashMap();
		param.put("lec_cd", super.lectureInfo.getLec_cd());
		result.setAttribute("list", StatDB.getOne(super.oDb, param));
		result.setAttribute("lecStat", StatDB.getResultByLec(super.oDb, param));
		result.setForward("view");

    }
    
}
