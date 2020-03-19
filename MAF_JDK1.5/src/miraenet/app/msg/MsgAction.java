/*
 * Created on 2006. 08. 17
 *
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package miraenet.app.msg;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import maf.MafConstant;
import maf.beans.NavigatorInfo;
import maf.exception.MafException;
import maf.mdb.sqlhelper.SqlWhereBuilder;
import maf.mdb.sqlhelper.Where;
import maf.web.http.MyHttpServletRequest;
import maf.web.mvc.action.MafAction;
import maf.web.mvc.beans.ResultMessage;
import maf.web.util.SerializeHashtable;
import modules.app._sample.Sample1DB;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MsgAction extends MafAction {
	final private Log logger = LogFactory.getLog(MsgAction.class);

	SerializeHashtable listOp = null;
	
	private final String MESSAGE_BUNDLENAME = "common.message";
	private String owner = "";
	
	public void preProcess(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		super.chkLogin();
		this.listOp = new SerializeHashtable(_req.getParameter(MafConstant.LIST_OP_NAME));
		this.owner = super.sessionBean.getUsn();
	}

	public void postProcess(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		result.setAttribute(MafConstant.LIST_OP_NAME, listOp);
	}

	/**
	 * 받은 목록 가져오기
	 * 
	 * @param _req
	 * @param _res 
	 */
	public void cmdRcvList(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {

		final String[] searchFields = { "msgid", "usn_snd", "usn_rcv", "nm_snd", "nm_rcv", "title" };

		NavigatorInfo navigator = new NavigatorInfo(_req, this.listOp);

		Map param = this.listOp.getMergedParam(_req, searchFields);
		param.put("usn_rcv", this.owner);
		SqlWhereBuilder wb = oDb.getWhereBuilder();
		wb.addCond(Where.eq("usn_rcv", ":usn_rcv"));

		MsgDB dao = MsgDB.getInstance();
		dao.getList(super.oDb, navigator, param, wb.getWhereString(param));
		result.setAttribute("navigator", navigator);
		result.setForward("rcvList");
	}
	/**
	 * 받은 목록 가져오기
	 * 
	 * @param _req
	 * @param _res 
	 */
	public void cmdSndList(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {

		final String[] searchFields = { "msgid", "usn_snd", "usn_rcv", "nm_snd", "nm_rcv", "title" };

		NavigatorInfo navigator = new NavigatorInfo(_req, this.listOp);

		Map param = this.listOp.getMergedParam(_req, searchFields);
		param.put("usn_snd", this.owner);
		SqlWhereBuilder wb = oDb.getWhereBuilder();
		wb.addCond(Where.eq("usn_snd", ":usn_rcv"));

		MsgDB dao = MsgDB.getInstance();
		dao.getList(super.oDb, navigator, param, wb.getWhereString(param));
		result.setAttribute("navigator", navigator);
		result.setForward("sndList");
	}

	/**
	 * view
	 * 
	 * @param _req
	 * @param _res
	 */
	public void cmdMsgView(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		MsgDB dao = MsgDB.getInstance();
		oDb.setDebug(true);
		Map param = new HashMap();
		param.put("msgid", _req.getP("msgid"));


		result.setAttribute("item", dao.getOne(super.oDb, param));
		result.setForward("msgView");
	}

	/**
	 * edit form 
	 * 
	 * @param _req
	 * @param _res
	 */
	public void cmdEdit(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		MsgDB dao = MsgDB.getInstance();
		Map param = new HashMap();
		param.put("msgid", _req.getP("msgid"));
		param.put("usn_snd", _req.getP("usn_snd"));
		param.put("usn_rcv", _req.getP("usn_rcv"));

		result.setAttribute("item", dao.getOne(super.oDb, param));
		result.setForward("edit");
	}

	/**
	 * update
	 * 
	 * @param _req
	 * @param _res
	 */
	public void cmdUpdate(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {

		MsgDB dao = MsgDB.getInstance();
		Map param = _req.getKeyValueMap();

		param.put("msgid", _req.getP("msgid"));
		param.put("usn_snd", _req.getP("usn_snd"));
		param.put("usn_rcv", _req.getP("usn_rcv"));

		int rcnt = dao.updateOne(super.oDb, param);
		if (rcnt > 0) {
			result.setNext(super.controlAction);
			result.setSuccess(true, new ResultMessage(this.MESSAGE_BUNDLENAME, "message.update.ok", new Integer(rcnt)));
		} else {
			result.setSuccess(false, new ResultMessage(this.MESSAGE_BUNDLENAME, "message.update.fail", new Integer(rcnt)));
		}
	}

	/**
	 * delete
	 * 
	 * @param _req
	 * @param _res
	 */
	public void cmdDelete(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		Sample1DB dao = Sample1DB.getInstance();
		Map param = _req.getKeyValueMap();
		param.put("msgid", _req.getP("msgid"));
		param.put("usn_snd", _req.getP("usn_snd"));
		param.put("usn_rcv", _req.getP("usn_rcv"));

		int rcnt = dao.deleteOne(super.oDb, param);
		if (rcnt > 0) {
			result.setNext(super.controlAction);
			result.setSuccess(true, new ResultMessage(this.MESSAGE_BUNDLENAME, "message.delete.ok", new Integer(rcnt)));
		} else {
			result.setSuccess(false, new ResultMessage(this.MESSAGE_BUNDLENAME, "message.delete.fail"));
		}
	}

	/**
	 * insert
	 * 
	 * @param _req
	 * @param _res
	 */
	public void cmdInsert(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {

		MsgDB dao = MsgDB.getInstance();
		Map param = _req.getKeyValueMap();

		int rcnt = dao.insertOne(super.oDb, param);
		if (rcnt > 0) {
			result.setNext(super.controlAction);
			result.setSuccess(true, new ResultMessage(this.MESSAGE_BUNDLENAME, "message.insert.ok", new Integer(rcnt)));
		} else {
			result.setSuccess(false, new ResultMessage(this.MESSAGE_BUNDLENAME, "message.insert.fail", new Integer(rcnt)));
		}
	}

	/**
	 * insert form
	 * 
	 * @param _req
	 * @param _res
	 */
	public void cmdMsgWrite(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {

		result.setForward("msgWrite");
	}

}
