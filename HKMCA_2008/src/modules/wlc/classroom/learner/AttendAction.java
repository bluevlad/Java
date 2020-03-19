/*
 * Created on 2006. 10. 06.
 *
 * Copyright (c) 2006 UBQ  All rights reserved.
 */
package modules.wlc.classroom.learner;

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
import modules.wlc.classroom.BaseClassAction;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class AttendAction extends BaseClassAction {
	private final String MESSAGE_BUNDLENAME = "common.message";
	private Log logger = LogFactory.getLog(AttendAction.class);

	/**
	 * 수정
	 *
	 * @param _req
	 * @param _res
	 */
	public void cmdList(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		
		Map param = _req.getKeyValueMap();

		param.put("std_usn", super.sessionBean.getUsn());
		param.put("lec_cd", super.lectureInfo.getLec_cd());
		result.setAttribute("list", AttendDB.getAttendList(super.oDb, param));
		result.setForward("list");
	}

	/**
	 * 오프라인 점수 등록 및 출석 체크
	 *
	 * @param _req
	 * @param _res
	 */
	public void cmdUpdate(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		
		int rcnt=0;
		Map param = new HashMap();
		param.put("lec_cd", super.lectureInfo.getLec_cd());
		param.put("usn", super.sessionBean.getUsn());
		
		try {
				oDb.setAutoCommit(false);
				rcnt = AttendDB.doAttend(super.oDb, param);
				oDb.commit();
		} catch (Exception e) {
			logger.error(e.getMessage());
			oDb.rollback();
		} finally {
			oDb.setAutoCommit(true);
		}

		if (rcnt > 0) {
			UrlAddress next = new UrlAddress(super.controlAction);
			next.addParam(MafConstant.LIST_OP_NAME, this.listOp.getSerializeUrl());
			result.setNext(next);
			result.setSuccess(true, new ResultMessage(this.MESSAGE_BUNDLENAME, "message.insert.ok", new Integer(rcnt)));
		} else {
			result.setSuccess(false, new ResultMessage(this.MESSAGE_BUNDLENAME, "message.insert.fail", new Integer(rcnt)));
		}
	}

}