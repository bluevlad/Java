/*
 * Created on 2006. 10. 06.
 *
 * Copyright (c) 2006 UBQ  All rights reserved.
 */
package modules.wlc.classroom.tutor;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;



import maf.MafConstant;
import maf.MafUtil;
import maf.beans.NavigatorInfo;
import maf.beans.NavigatorOrderInfo;
import maf.exception.MafException;
import maf.mdb.sqlhelper.SqlWhereBuilder;
import maf.mdb.sqlhelper.Where;
import maf.web.http.MyHttpServletRequest;
import maf.web.http.UrlAddress;
import maf.web.mvc.beans.ResultMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class WlbOffReqAction extends BaseTutorClassAction {
	private final String MESSAGE_BUNDLENAME = "common.message";
	private Log logger = LogFactory.getLog(WlbOffReqAction.class);
	/**
	 * 목록 조회 화면
	 *
	 * @param _req
	 * @param _res
	 */
	public void cmdList(MyHttpServletRequest _req, HttpServletResponse _res)  throws MafException {
		final String[] searchFields = { "lec_cd" };
		NavigatorInfo navigator = new NavigatorInfo( _req, this.listOp, new NavigatorOrderInfo("A:itm_sequence"));
		Map param = this.listOp.getMergedParam(_req, searchFields);
		param.put("lec_cd", super.lectureInfo.getLec_cd());
		SqlWhereBuilder wb = oDb.getWhereBuilder();
		wb.addCond(Where.eq("lec_cd", ":lec_cd"));
		WlbOffReqDB.getList(super.oDb, navigator, param, wb.getWhereString(param));
		result.setAttribute("navigator", navigator);
		result.setAttribute("lec_cd", super.lectureInfo.getLec_cd());
		result.setForward("list");
	}

	/**
	 * 상세조회 및 점수 입력 화면
	 *
	 * @param _req
	 * @param _res
	 */
	public void cmdEdit(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		//final String[] searchFields = {"leccode", "itm_id"};
//		NavigatorInfo navigator = new NavigatorInfo(_req, this.listOp);
		//Map param = this.listOp.getMergedParam(_req, searchFields);
		Map param = _req.getKeyValueMap();
		param.put("lec_cd", super.lectureInfo.getLec_cd());
//		SqlWhereBuilder wb = oDb.getWhereBuilder();
//		wb.addCond(Where.eq("leccode", ":leccode"));
//		WlbOffReqDB.getWlboffreqList(super.oDb, navigator, param,
//		                             wb.getWhereString(param));
		result.setAttribute("list", WlbOffReqDB.getWlboffreqList(super.oDb, param));
		result.setAttribute("lec_cd", super.lectureInfo.getLec_cd());
		//특정 과목의 특정 오프라인 강의 정보 가져오기
		result.setAttribute("item", WlbOffReqDB.getLecChpOff(super.oDb, param));
		result.setForward("edit");
	}

	/**
	 * 오프라인 점수 등록 및 출석 체크
	 *
	 * @param _req
	 * @param _res
	 */
	public void cmdUpdate(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		
		String off_score[] = _req.getParameterValues("off_score");
		String seq_no[] = _req.getParameterValues("seq_no"); // userid#lecnumb
		boolean chk = false;
		int rcnt=0;
		Map param = new HashMap();
		param.put("lec_cd", super.lectureInfo.getLec_cd());
		param.put("itm_id", _req.getP("itm_id"));
		
		try {
			oDb.setAutoCommit(false);
			if(seq_no != null) {
				String tt = null;
				String[] ttt = null;
				for(int i = 0; i < seq_no.length; i++) {
					//[correct_yn, usrscore, update_dt, update_id, exmid, usn,lecnumb, qseq]
					tt = seq_no[i];
					if(!MafUtil.empty(tt)) {
						ttt = tt.split("#");
						param.put("usn", ttt[0]);
						param.put("lec_num", ttt[1]);
						param.put("off_score",  off_score[i]);
						//[leccode, itm_id, userid, lecnumb, off_score]
						 rcnt = WlbOffReqDB.updateAttend(super.oDb, param);
					}
				}
			}
			oDb.commit();
			chk = true;
		} catch (Exception e) {
			logger.error(e.getMessage());
			oDb.rollback();
			
			
		} finally {
			oDb.setAutoCommit(true);
		}
		if (chk) {
			UrlAddress next = new UrlAddress(super.controlAction + "?cmd=edit&itm_id=" + _req.getP("itm_id"));
			next.addParam(MafConstant.LIST_OP_NAME, this.listOp.getSerializeUrl());
			result.setNext(next);
			result.setSuccess(true, new ResultMessage(this.MESSAGE_BUNDLENAME, "message.insert.ok", new Integer(rcnt)));
		} else {
			result.setSuccess(false, new ResultMessage(this.MESSAGE_BUNDLENAME, "message.insert.fail", new Integer(rcnt)));
		}
	}

//	/**
//	 * 강사출석 체크 등록
//	 *
//	 * @param _req
//	 * @param _res
//	 */
//	public void cmdUpdateAttend(MyHttpServletRequest _req, HttpServletResponse _res)
//	        throws MafException {
//		String seq_no[] = _req.getParameterValues("seq_no");
//		int rcnt = WlbOffReqDB.updateAttend(super.oDb, seq_no);
//		if (rcnt > 0) {
//			UrlAddress next = new UrlAddress(super.controlAction
//			        + "?cmd=edit&itm_id="
//			        + _req.getP("itm_id"));
//			next.addParam(MafConstant.LIST_OP_NAME, this.listOp.getSerializeUrl());
//			result.setNext(next);
//			result.setSuccess(true, new ResultMessage(this.MESSAGE_BUNDLENAME,
//			                                          "message.insert.ok",
//			                                          new Integer(rcnt)));
//		} else {
//			result.setSuccess(false, new ResultMessage(this.MESSAGE_BUNDLENAME,
//			                                           "message.insert.fail",
//			                                           new Integer(rcnt)));
//		}
//	}
}