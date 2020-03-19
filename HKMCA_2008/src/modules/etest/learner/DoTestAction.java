package modules.etest.learner;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import maf.MafUtil;
import maf.exception.MafException;
import maf.util.MafAssert;
import maf.util.StringUtils;
import maf.web.http.MyHttpServletRequest;
import maf.web.mvc.action.MafAction;
import maf.web.mvc.view.JsonViewer;
import maf.web.util.CookieUtil;
import modules.common.jason.JSONObject;
import modules.etest.support.Etest;
import modules.xadmin.etest.ExmManagerDB;

/**
 * 시험보기
 * @author bluevlad
 *
 */
public class DoTestAction extends MafAction {
	final private Log logger = LogFactory.getLog(this.getClass());
	
	private final String MESSAGE_BUNDLENAME = "common.message";
	public static final String EXAM_ID_KEY = "EXAM_ID_KEY";
	public static final String EXAM_LECNUMB_KEY = "EXAM_LECNUMB_KEY";
	public static final String EXAM_LANG_KEY = "EXAM_LANG_KEY";
	public static final String EXAM_NAME_KEY = "EXAM_NAME_KEY";

	public void preProcess(MyHttpServletRequest _req, HttpServletResponse _res)
	        throws MafException {
		super.chkLogin();
	}

	public void postProcess(MyHttpServletRequest _req, HttpServletResponse _res)
	        throws MafException {
	}

	/**
	 * 응시정보 확인 
	 * @param _req
	 * @param _res
	 * @throws MafException
	 */
	public void cmdConfirm(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		String[] chks={"exmid"};
		MafAssert.chkEmpty(_req, chks);
		Map param = new HashMap();
		param.put("exmid", _req.getP("exmid"));
		super.sessionBean.addAttribute(EXAM_ID_KEY, _req.getP("exmid"));
		result.setAttribute("setlangs", ExamListDB.getLangList(super.oDb, param));
		result.setForward("confirm");
	}

	/**
	 * 시험 시작 및 페이지 보여주기.
	 * @param _req
	 * @param _res
	 * @throws MafException
	 */
	public void cmdTest(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		Map param = new HashMap();
		param.put("usn", super.sessionBean.getUsn());
		param.put("exmid", this.getExmid());
		param.put("pageno", _req.getP("pageno", "1"));
		if ("T".equals(_req.getP("start"))) {
			super.sessionBean.addAttribute(EXAM_LANG_KEY, _req.getP("lang"));
			param.put("lang", this.getExmLang());
			Map test = ExmManagerDB.getOne(super.oDb, param);
			CookieUtil.setCookieValue(_res, EXAM_NAME_KEY, MafUtil.getString(test.get("exmtitle")));
			param.put("exmtitle", MafUtil.getString(test.get("exmtitle")));
			DoTestDB.updateRst(super.oDb, param);
		}
		
		param.put("lang", this.getExmLang());
		
		// 한페이지의 문제를 돌려 줌
		result.setAttribute("quelist", DoTestDB.getOnePage(super.oDb, param));
		// 좌측 navigation을 돌려 줌.
		result.setAttribute("navi", DoTestDB.getNavi(super.oDb, param));
		// 응시 관련 정보를 돌려 줌 .
		result.setAttribute("rstinfo", DoTestDB.getRstInfo(super.oDb, param));
		result.setAttribute("exmtitle", param.get("exmtitle"));
		result.setForward("test");
	}

	/**
	 * 답변 하나 Update
	 * @param _req
	 * @param _res
	 * @throws MafException
	 */
	public void cmdUpdateAnswer(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		final String[] reqfields = { "qseq", "ansqid" };
		super.chkEmpty(_req, reqfields);
		Map param = new HashMap();
		param.put("usn", super.sessionBean.getUsn());
		param.put("exmid", this.getExmid());
		param.put("qseq", _req.getP("qseq"));
		String[] usransw = _req.getParameterValues(_req.getP("ansqid"));
		param.put("usransw", StringUtils.arrayToDelimitedString(usransw, Etest.ANS_SEPERATOR));
		int i = DoTestDB.updateAnswerOne(oDb, param);
		int leftCnt = DoTestDB.getLeftCount(oDb, param);
		
		JSONObject jobj = new JSONObject();
		_res.setContentType(JsonViewer.CONTENTS_TYPE);
		_res.setHeader("Cache-Control", "no-cache");
		jobj.put("leftCnt", leftCnt + "");
		jobj.put("status", "ok");
		if (i > 0) {
			jobj.put("qseq", _req.getP("qseq"));
		}
		try {
			ServletOutputStream ouputStream = _res.getOutputStream();
			ouputStream.print(jobj.toString());
			ouputStream.close();
		} catch (Exception e) {
			logger.debug(e.getMessage());
		}
		result.setForward("dummy");
	}

	/**
	 * Mark 하나 Update
	 * @param _req
	 * @param _res
	 * @throws MafException
	 */
	public void cmdUpdateMark(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		final String[] reqfields = { "qseq", "marked" };
		super.chkEmpty(_req, reqfields);
		Map param = new HashMap();
		param.put("usn", super.sessionBean.getUsn());
		param.put("exmid", this.getExmid());
		param.put("qseq", _req.getP("qseq"));
		param.put("mark_yn", _req.getP("marked"));
		int i = DoTestDB.updateMarkOne(oDb, param);
		JSONObject jobj = new JSONObject();
		_res.setContentType(JsonViewer.CONTENTS_TYPE);
		_res.setHeader("Cache-Control", "no-cache");
		if (i > 0) {
			jobj.put("qseq", _req.getP("qseq"));
		}
		try {
			ServletOutputStream ouputStream = _res.getOutputStream();
			ouputStream.print(jobj.toString());
			ouputStream.close();
		} catch (Exception e) {
			logger.debug(e.getMessage());
		}
		result.setForward("dummy");
	}

	/**
	 * Session 에서 eximid 가져오기 
	 * @return
	 */
	private String getExmid() {
		return MafUtil.getString(super.sessionBean.getAttribute(EXAM_ID_KEY));
	}
	
	/**
	 * Session 에서 lecnumb 가져오기 
	 * @return
	 */
	private String getExmLecnumb() {
		return MafUtil.getString(super.sessionBean.getAttribute(EXAM_LECNUMB_KEY));
	}
	
	/**
	 * Session 에서 Lang 가져오기 
	 * @return
	 */
	private String getExmLang() {
		return MafUtil.getString(super.sessionBean.getAttribute(EXAM_LANG_KEY));
	}

	/**
	 * 시험 종료 
	 * @param _req
	 * @param _res
	 * @throws MafException
	 */
	public void cmdFinish(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {

		Map param = new HashMap();
		param.put("usn", super.sessionBean.getUsn());
		param.put("exmid", this.getExmid());
		
		int i = DoTestDB.setFin(oDb, param);
		JSONObject jobj = new JSONObject();
		_res.setContentType(JsonViewer.CONTENTS_TYPE);
		_res.setHeader("Cache-Control", "no-cache");
		if (i > 0) {
			jobj.put("status","ok");
		}
		try {
			ServletOutputStream ouputStream = _res.getOutputStream();
			ouputStream.print(jobj.toString());
			ouputStream.close();
		} catch (Exception e) {
			logger.debug(e.getMessage());
		}
		result.setForward("dummy");
	}
}