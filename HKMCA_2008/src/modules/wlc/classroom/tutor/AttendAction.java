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

public class AttendAction extends BaseTutorClassAction {
	private final String MESSAGE_BUNDLENAME = "common.message";
	private Log logger = LogFactory.getLog(AttendAction.class);
	/**
	 * 목록 조회 화면
	 *
	 * @param _req
	 * @param _res
	 */
	public void cmdList(MyHttpServletRequest _req, HttpServletResponse _res)  throws MafException {
		final String[] searchFields = {"nm"};
		NavigatorInfo navigator = new NavigatorInfo( _req, this.listOp, new NavigatorOrderInfo("A:nm"));
		Map param = this.listOp.getMergedParam(_req, searchFields);

		param.put("lec_cd", super.lectureInfo.getLec_cd());
		SqlWhereBuilder wb = oDb.getWhereBuilder();
		wb.addCond(Where.eq("nm", ":nm"));

		AttendDB.getList(super.oDb, navigator, param, wb.getWhereString(param));
		result.setAttribute("navigator", navigator);
		result.setAttribute("lec_cd", super.lectureInfo.getLec_cd());
		result.setForward("list");
	}

	/**
	 * 수정
	 *
	 * @param _req
	 * @param _res
	 */
	public void cmdView(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		
		Map param = _req.getKeyValueMap();

		param.put("lec_cd", super.lectureInfo.getLec_cd());
		result.setAttribute("list", AttendDB.getAttendList(super.oDb, param));
		result.setForward("view");
	}

	/**
	 * 오프라인 점수 등록 및 출석 체크
	 *
	 * @param _req
	 * @param _res
	 */
	public void cmdUpdate(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		
		String att_score[] = _req.getParameterValues("att_score");
		String seq_usn[] = _req.getParameterValues("usn"); // usn
		boolean chk = false;
		int rcnt=0;
		Map param = new HashMap();
		param.put("lec_cd", super.lectureInfo.getLec_cd());
		
		try {
			oDb.setAutoCommit(false);
			if(seq_usn != null) {
				for(int i = 0; i < seq_usn.length; i++) {
						param.put("usn", seq_usn[i]);
						param.put("att_score", att_score[i]);
						//[usn, att_score]
						 rcnt = AttendDB.updateAttend(super.oDb, param);
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
			UrlAddress next = new UrlAddress(super.controlAction);
			next.addParam(MafConstant.LIST_OP_NAME, this.listOp.getSerializeUrl());
			result.setNext(next);
			result.setSuccess(true, new ResultMessage(this.MESSAGE_BUNDLENAME, "message.insert.ok", new Integer(rcnt)));
		} else {
			result.setSuccess(false, new ResultMessage(this.MESSAGE_BUNDLENAME, "message.insert.fail", new Integer(rcnt)));
		}
	}

}