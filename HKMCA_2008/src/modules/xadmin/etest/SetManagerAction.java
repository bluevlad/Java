/*
 * Created on 2006. 06. 14.
 *
 * Copyright (c) 2004 UBQ  All rights reserved.
 */
package modules.xadmin.etest;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import maf.MafConstant;
import maf.beans.NavigatorInfo;
import maf.exception.MafException;
import maf.mdb.sqlhelper.SqlWhereBuilder;
import maf.mdb.sqlhelper.Where;
import maf.web.http.MyHttpServletRequest;
import maf.web.http.UrlAddress;
import maf.web.mvc.action.MafAction;
import maf.web.mvc.beans.ResultMessage;
import maf.web.util.SerializeHashtable;
import modules.wlc.admin.course.LectureMngDB;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 
 */
public class SetManagerAction extends MafAction {
	final private Log logger = LogFactory.getLog(this.getClass());
	SerializeHashtable listOp = null;
	String lang = null;
	private final String MESSAGE_BUNDLENAME = "common.message";

	public void preProcess(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		this.listOp = new SerializeHashtable(_req.getParameter(MafConstant.LIST_OP_NAME));
		this.lang = super.locale.getLanguage();
	}

	public void postProcess(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		result.setAttribute(MafConstant.LIST_OP_NAME, listOp);
	}

	/**
	 * 목록 가져오기
	 * 
	 * @param _req
	 * @param _res
	 */
	public void cmdList(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {

		// Order default 값 설정 시
		final String[] searchFields = { "s_settitle", "s_active_yn"};

		NavigatorInfo navigator = new NavigatorInfo(_req, this.listOp);
	
		Map param = this.listOp.getMergedParam(_req, searchFields);
		param.put("usn", super.sessionBean.getUsn());
		SqlWhereBuilder wb = oDb.getWhereBuilder();
		wb.addCond(Where.eq("active_yn", ":s_active_yn"));
		wb.addCond(Where.like("settitle", ":s_settitle"));

		SetManagerDB.getList(super.oDb, navigator, param, wb.getWhereString(param), false);
		result.setAttribute("navigator", navigator);
		result.setForward("list");
	}

	/**
	 * insert form
	 * 
	 * @param _req
	 * @param _res
	 */
	public void cmdAdd(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		Map param = new HashMap();

		result.setAttribute("subject", LectureMngDB.getCrsSub(super.oDb, param));
		result.setForward("add");
	}

	/**
	 * edit form 
	 * 
	 * @param _req
	 * @param _res
	 */
	public void cmdEdit(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {

		Map param = _req.getKeyValueMap();

		result.setAttribute("item", SetManagerDB.getOne(super.oDb, param));
		result.setAttribute("bnk", SetManagerDB.getQueMaxCnt(super.oDb, param));
		result.setAttribute("list", SetManagerDB.getSetItems(super.oDb, param));
		
		result.setForward("edit");
	}

	/**
	 * 하나의 record를 수정 한다.
	 * 
	 * @param _req
	 * @param _res
	 */
	public void cmdUpdate(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {

		Map param = _req.getKeyValueMap();
		boolean chk = false;
		// 최종 수정자
		param.put("reg_id", super.sessionBean.getUsn());
		param.put("upt_id", super.sessionBean.getUsn());
		param.put("lang", lang);

		String[] v_queids = _req.getParameterValues("v_queids");
		String[] a_queids = _req.getParameterValues("a_queids");
		
		int rcnt = 0;
		try {
			oDb.setAutoCommit(false);
			
			rcnt = SetManagerDB.updateSetOne(super.oDb, param);
			rcnt = SetManagerDB.deleteSetItems(super.oDb, param);
			
			if(v_queids != null) {
				for(int i = 0; i < v_queids.length; i ++ ) {
					param.put("queid", v_queids[i]);
					param.put("qseq", _req.getP("qseq_"+v_queids[i]));
					rcnt = SetManagerDB.insertSetItems(super.oDb, param);
				}
			}
			if("R".equals(param.get("exmtype"))){
/*				if(a_queids != null) {
					for(int i = 0; i < a_queids.length; i ++ ) {
						param.put("queid", a_queids[i]);
						param.put("qseq", _req.getP("qseq_"+a_queids[i]));
						rcnt = SetManagerDB.insertSetItems(super.oDb, param);
					}
				}*/
			} else{
				Map param2 = SetManagerDB.getQueMaxCnt(super.oDb, param);
				param.put("exmcnt1", param2.get("exmcnt1"));
				param.put("exmcnt2", param2.get("exmcnt2"));
				param.put("exmcnt3", param2.get("exmcnt3"));
				param.put("exmcnt4", param2.get("exmcnt4"));
				param.put("exmcnt5", param2.get("exmcnt5"));
			}
			SetManagerDB.updateMaxCnt(super.oDb, param);
			
			oDb.commit();
			chk = true;
		} catch (Exception e) {
			oDb.rollback();
			logger.error(e.getMessage());
			rcnt = 0;
		} finally {
			oDb.setAutoCommit(true);
		}
		
		if (chk) {
			UrlAddress next = new UrlAddress(super.controlAction);
			next.addParam(MafConstant.LIST_OP_NAME, this.listOp.getSerializeUrl());
			next.addParam("cmd", "edit");
			next.addParam("setid", param.get("setid"));
			result.setNext(next);
			result.setSuccess(true, new ResultMessage(this.MESSAGE_BUNDLENAME, "message.update.ok", new Integer(rcnt)));
		} else {
			result.setSuccess(false, new ResultMessage(this.MESSAGE_BUNDLENAME, "message.update.fail", new Integer(rcnt)));
		}
	}
	
	/**
	 * 문제 set 을 수정 한다. 
	 * 
	 * @param _req
	 * @param _res
	 */
	public void cmdUpdateItems(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		Map param = _req.getKeyValueMap();
		boolean chk = false;
		param.put("setid", _req.getP("setid"));
		// 최종 수정자
		param.put("upt_id", super.sessionBean.getUsn());
		param.put("reg_id", super.sessionBean.getUsn());
		int rcnt = 0;
		try {
			oDb.setAutoCommit(false);
			rcnt = SetManagerDB.deleteSetItems(super.oDb, param);
			String[] v_queids = _req.getParameterValues("v_queids");
			
			if(v_queids != null) {
				for(int i = 0; i < v_queids.length; i ++ ) {
					param.put("queid", v_queids[i]);
					param.put("qseq", _req.getP("qseq_"+v_queids[i]));
					rcnt = SetManagerDB.insertSetItems(super.oDb, param);
				}
			}
			Map param2 = SetManagerDB.getQueMaxCnt(super.oDb, param);
			param2.put("upt_id", super.sessionBean.getUsn());
			param2.put("setid", _req.getP("setid"));
			SetManagerDB.updateMaxCnt(super.oDb, param2);
			oDb.commit();
			chk = true;
		} catch (Exception e) {
			oDb.rollback();
			logger.error(e.getMessage());
			rcnt = 0;
		} finally {
			oDb.setAutoCommit(true);
		}
		if (chk) {
			UrlAddress next = new UrlAddress(super.controlAction);
			next.addParam("cmd", "edit");
			next.addParam("setid",  _req.getP("setid"));
			next.addParam(MafConstant.LIST_OP_NAME, this.listOp.getSerializeUrl());
			result.setNext(next);
			result.setSuccess(true, new ResultMessage(this.MESSAGE_BUNDLENAME, "message.update.ok", new Integer(rcnt)));
		} else {
			result.setSuccess(false, new ResultMessage(this.MESSAGE_BUNDLENAME, "message.update.fail", new Integer(rcnt)));
		}
	}
	
	/**
	 * insert
	 * 
	 * @param _req
	 * @param _res
	 */
	public void cmdInsert(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		int rcnt = 0;
		Map param = _req.getKeyValueMap();
		
		param.put("lang", lang);
		param.put("usn", super.sessionBean.getUsn());
		param.put("setowner", super.sessionBean.getUsn());
		boolean chk = false;
		try {
			oDb.setAutoCommit(false);
			rcnt = SetManagerDB.insertOne(super.oDb, param);
			oDb.commit();
			chk = true;
		} catch (Exception e) {
			oDb.rollback();
			logger.error(e.getMessage());
			rcnt = 0;
		} finally {
			oDb.setAutoCommit(true);
		}
		if (chk) {
			UrlAddress next = new UrlAddress(super.controlAction);
			next.addParam(MafConstant.LIST_OP_NAME, this.listOp.getSerializeUrl());
			result.setNext(next);
			result.setSuccess(true, new ResultMessage(this.MESSAGE_BUNDLENAME, "insert.ok", new Integer(rcnt)));
		} else {
			result.setSuccess(false, new ResultMessage(this.MESSAGE_BUNDLENAME, "insert.fail", new Integer(rcnt)));
		}
	}

	/**
	 * delete
	 * 
	 * @param _req
	 * @param _res
	*/

	public void cmdDelete(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {

		Map param = _req.getKeyValueMap();
		param.put("setid", _req.getP("setid"));
			
		int rcnt= 0;
		boolean chk = false;
		try {
			oDb.setAutoCommit(false);
			rcnt = SetManagerDB.deleteOne(super.oDb, param);
			oDb.commit();
			chk = true;
		} catch ( Exception e) {
			oDb.rollback();
			logger.error(e.getMessage());
			rcnt = 0;
		} finally {
			oDb.setAutoCommit(true);
		}
		if(chk ) {
			UrlAddress next = new UrlAddress (super.controlAction);
			next.addParam(MafConstant.LIST_OP_NAME, this.listOp.getSerializeUrl());
			result.setNext(next);
			result.setSuccess(true,new ResultMessage(this.MESSAGE_BUNDLENAME, "message.delete.ok", new Integer(rcnt)));
		} else {
			result.setSuccess(false,new ResultMessage(this.MESSAGE_BUNDLENAME, "message.delete.fail"));
		}
	}		
}