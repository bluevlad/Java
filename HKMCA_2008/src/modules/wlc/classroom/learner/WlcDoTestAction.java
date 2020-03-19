package modules.wlc.classroom.learner;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;


import maf.MafUtil;
import maf.exception.MafException;
import maf.util.MafAssert;
import maf.util.StringUtils;
import maf.web.http.MyHttpServletRequest;
import maf.web.mvc.view.JsonViewer;
import maf.web.util.CookieUtil;
import maf.web.util.SerializeHashtable;
import modules.wlc.classroom.learner.WlcDoTestDB;
import modules.common.jason.JSONObject;
import modules.etest.support.Etest;
import modules.wlc.classroom.tutor.TestManagerDB;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class WlcDoTestAction extends BaseLearnerClassAction {
	private Log logger = LogFactory.getLog(this.getClass());
	SerializeHashtable listOp = null;
	private final String MESSAGE_BUNDLENAME = "common.message";
	public static final String EXAM_ID_KEY = "EXAM_ID_KEY";
	public static final String EXAM_LECNUMB_KEY = "EXAM_LECNUMB_KEY";
	public static final String EXAM_LANG_KEY = "EXAM_LANG_KEY";
	public static final String EXAM_NAME_KEY = "EXAM_NAME_KEY";

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
		super.sessionBean.addAttribute(EXAM_LECNUMB_KEY, _req.getP("lec_num"));
		result.setAttribute("setlangs", TestManagerDB.getLangList(super.oDb, param));
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
		param.put("upt_id", super.sessionBean.getUsn());
        param.put("lang", super.locale.getLanguage());
		
		if ("T".equals(_req.getP("start"))) {
//			super.sessionBean.addAttribute(EXAM_LANG_KEY, _req.getP("lang"));
//			param.put("lang", this.getExmLang());
			Map test = TestManagerDB.getOne(super.oDb, param);
			CookieUtil.setCookieValue(_res, EXAM_NAME_KEY, MafUtil.getString(test.get("exmtitle")));
			param.put("exmtitle", MafUtil.getString(test.get("exmtitle")));
			WlcDoTestDB.updateRst(super.oDb, param);
		}
		
		
		// 한페이지의 문제를 돌려 줌
		result.setAttribute("quelist", WlcDoTestDB.getOnePage(super.oDb, param));
		// 좌측 navigation을 돌려 줌.
		result.setAttribute("navi", WlcDoTestDB.getNavi(super.oDb, param));
		// 응시 관련 정보를 돌려 줌 .
		result.setAttribute("rstinfo", WlcDoTestDB.getRstInfo(super.oDb, param));
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
		int i = TestManagerDB.updateAnswerOne(oDb, param);
		int leftCnt = TestManagerDB.getLeftCount(oDb, param);
		
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
		int i = TestManagerDB.updateMarkOne(oDb, param);
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
	public void cmdFinish(MyHttpServletRequest _req, HttpServletResponse _res)
	        throws MafException {

		Map param = new HashMap();
		param.put("usn", super.sessionBean.getUsn());
		param.put("exmid", this.getExmid());
		
		int i = WlcDoTestDB.setFin(oDb, param);
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