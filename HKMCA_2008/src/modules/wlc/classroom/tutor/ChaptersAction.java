/*
 * Created on 2006. 6. 22.
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
/**
 * 강의안 관리 
 * @author bluevlad
 *
 */
public class ChaptersAction extends BaseTutorClassAction {
	private final String MESSAGE_BUNDLENAME = "common.message";
    final private Log  logger = LogFactory.getLog(this.getClass());

	/**
	 * 목록 조회
	 *
	 * @param _req
	 * @param _res
	 */
	public void cmdList(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		
		Map param = new HashMap();
		String sjt_cd = _req.getP("sjt_cd", super.lectureInfo.getSjt_cd());
		String lec_cd = _req.getP("lec_cd", super.lectureInfo.getLec_cd());
		param.put("lec_cd", lec_cd);
		param.put("sjt_cd", sjt_cd);
		// 강의안정보
		result.setAttribute("list", ChaptersDB.getList(super.oDb,  param));
		result.setAttribute("sjt_cd", sjt_cd);
		result.setAttribute("lec_cd", lec_cd);
		result.setForward("list");
	}

	/**
	 * 수정
	 *
	 * @param _req
	 * @param _res
	 */
	public void cmdEdit(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		
		Map param = _req.getKeyValueMap();

		//특정 과목의 특정 컨텐츠 정보 가져오기
		result.setAttribute("item", ChaptersDB.getOne(super.oDb, param));
		//컨텐츠  목록 가져오기
//		result.setAttribute("slist", WlbLecChpDB.getLecChpList(super.oDb, param));
		result.setAttribute("sjt_cd", super.lectureInfo.getSjt_cd());
		result.setAttribute("lec_cd", super.lectureInfo.getLec_cd());
		result.setForward("edit");
	}

	/**
	 * 등록 화면
	 *
	 * @param _req
	 * @param _res
	 */
	public void cmdAdd(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {

		result.setAttribute("sjt_cd", super.lectureInfo.getSjt_cd());
		result.setAttribute("lec_cd", super.lectureInfo.getLec_cd());
		result.setForward("edit");
	}

	/**
	 * 등록
	 *
	 * @param _req
	 * @param _res
	 */
	public void cmdInsert(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		Map param = _req.getKeyValueMap();
		boolean chk = false;
		int rcnt = 0;

		// 최초 등록자
		param.put("usn",super.sessionBean.getUsn());
		param.put("itm_sequence", ChaptersDB.getMaxItmSeq(super.oDb, param)+"");
		
		try {
			oDb.setAutoCommit(false);
			rcnt = ChaptersDB.insertOne(super.oDb, param);
			oDb.commit();
			chk = true;
		} catch ( Exception e) {
			oDb.rollback();
			logger.error(e.getMessage());
		} finally {
			oDb.setAutoCommit(true);
		}
		
		if(chk ) {
			UrlAddress next = new UrlAddress (super.controlAction);
			next.addParam(MafConstant.LIST_OP_NAME, this.listOp.getSerializeUrl());
			result.setNext(next);
			result.setSuccess(true,new ResultMessage(this.MESSAGE_BUNDLENAME, "insert.ok", new Integer(rcnt)));
		} else {
			result.setSuccess(false,new ResultMessage(this.MESSAGE_BUNDLENAME, "insert.fail", new Integer(rcnt)));
		}
		
	}

	/**
	 * 수정 등록
	 *
	 * @param _req
	 * @param _res
	 */
	public void cmdUpdate(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		Map param = _req.getKeyValueMap();
		boolean chk = false;
		int rcnt = 0;
		
		param.put("upt_id", super.sessionBean.getUsn());

		try {
			oDb.setAutoCommit(false);
			// 최초 등록자
			rcnt = ChaptersDB.updateOne(super.oDb, param);
			oDb.commit();
			chk = true;
		} catch ( Exception e) {
			oDb.rollback();
			logger.error(e.getMessage());
		} finally {
			oDb.setAutoCommit(true);
		}
		
		
		chk = true;
		if (chk) {
			UrlAddress next = new UrlAddress(super.controlAction);
			next.addParam(MafConstant.LIST_OP_NAME, this.listOp.getSerializeUrl());
			result.setNext(next);
			result.setSuccess(true, new ResultMessage(this.MESSAGE_BUNDLENAME, "update.ok", new Integer(rcnt)));
		} else {
			result.setSuccess(false, new ResultMessage(this.MESSAGE_BUNDLENAME, "update.fail", new Integer(rcnt)));
		}
	}

	/**
	 * 삭제
	 *
	 * @param _req
	 * @param _res
	 */
	public void cmdDelete(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {

		Map param = _req.getKeyValueMap();
		param.put("lec_cd", super.lectureInfo.getLec_cd());
		int rcnt = ChaptersDB.delete(super.oDb, param);
		if (rcnt > 0) {
			UrlAddress next = new UrlAddress(super.controlAction);
			next.addParam(MafConstant.LIST_OP_NAME, this.listOp.getSerializeUrl());
			result.setNext(next);
			result.setSuccess(true, new ResultMessage(this.MESSAGE_BUNDLENAME, "delete.ok", new Integer(rcnt)));
		} else {
			result.setSuccess(false, new ResultMessage(this.MESSAGE_BUNDLENAME, "delete.fail", new Integer(rcnt)));
		}
	}

	/**
	 * 목록 조회
	 *
	 * @param _req
	 * @param _res
	 */
	public void cmdSeList(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		final String[] searchFields = {"sjt_cd", "daename"};
		NavigatorInfo navigator = new NavigatorInfo(_req, this.listOp, new NavigatorOrderInfo("A:htmcode"));
		
		Map param = this.listOp.getMergedParam(_req, searchFields);
		param.put("lec_cd", super.lectureInfo.getLec_cd());

		SqlWhereBuilder wb = oDb.getWhereBuilder();
		wb.addCond(Where.eq("sjt_cd", ":sjt_cd"));
		wb.addCond(Where.like("daename", ":daename"));
		
		ChaptersDB.getSeList(super.oDb, navigator, param, wb.getWhereString(param), true);
		result.setAttribute("navigator", navigator);
		result.setAttribute("sjt_cd", _req.getP("sjt_cd"));
		result.setForward("selist");
	}

	/**
	 * 일괄등록 목록 조회
	 *
	 * @param _req
	 * @param _res
	 */
	public void cmdMultiSeList(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		final String[] searchFields = {"sjt_cd", "daename"};
		NavigatorInfo navigator = new NavigatorInfo(_req, this.listOp, new NavigatorOrderInfo("A:htmcode"));

		Map param = this.listOp.getMergedParam(_req, searchFields);
		param.put("lec_cd", super.lectureInfo.getLec_cd());
		param.put("sjt_cd", super.lectureInfo.getSjt_cd());
		
		SqlWhereBuilder wb = oDb.getWhereBuilder();
		wb.addCond(Where.eq("sjt_cd", ":sjt_cd"));
		wb.addCond(Where.like("daename", ":daename"));
		
		ChaptersDB.getSeList(super.oDb, navigator, param, wb.getWhereString(param), true);
		result.setAttribute("navigator", navigator);
		result.setAttribute("sjt_cd", _req.getP("sjt_cd"));
		result.setForward("mselist");
	}

	/**
	 * 일괄 등록
	 *
	 * @param _req
	 * @param _res
	 */
	public void cmdMultiInsert(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		boolean chk = false;
		String htmcodes[] = _req.getParameterValues("htmcode_chk");
		
		int rcnt = ChaptersDB.multiInsert(super.oDb, super.lectureInfo.getLec_cd(), super.lectureInfo.getSjt_cd(), htmcodes);
		chk = true;
		if (chk) {
			UrlAddress next = new UrlAddress(super.controlAction+"?cmd=mselist&sjt_cd="+_req.getP("sjt_cd"));
			next.addParam(MafConstant.LIST_OP_NAME, this.listOp.getSerializeUrl());
			result.setNext(next);
			result.setSuccess(true, new ResultMessage(this.MESSAGE_BUNDLENAME, "message.update.ok", new Integer(rcnt)));
		} else {
			result.setSuccess(false, new ResultMessage(this.MESSAGE_BUNDLENAME, "message.update.fail", new Integer(rcnt)));
		}
	}

	/**
	 * 순서변경
	 *
	 * @param _req
	 * @param _res
	 */
	public void cmdSeqUpdate(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		String itm_sequence[] = _req.getParameterValues("itm_sequence");
		String act_key[] = _req.getParameterValues("act_key");
		
		int rcnt = ChaptersDB.updateSequence(super.oDb, act_key, itm_sequence);
		if (rcnt > 0) {
			UrlAddress next = new UrlAddress(super.controlAction);
			next.addParam(MafConstant.LIST_OP_NAME, this.listOp.getSerializeUrl());
			result.setNext(next);
			result.setSuccess(true, new ResultMessage(this.MESSAGE_BUNDLENAME, "message.update.ok", new Integer(rcnt)));
		} else {
			result.setSuccess(false, new ResultMessage(this.MESSAGE_BUNDLENAME, "message.update.fail", new Integer(rcnt)));
		}
	}
}