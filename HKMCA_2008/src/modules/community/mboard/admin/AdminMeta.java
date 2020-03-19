/*
 * Created on 2005. 10. 31.
 * �Խ��� ��Ÿ�������� (���/����/�������)
 * Copyright (c) 2004 bluevlad.
 */
package modules.community.mboard.admin;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import maf.exception.MafException;
import maf.lib.Setter;
import maf.web.http.MyHttpServletRequest;
import maf.web.mvc.action.MafAction;
import maf.web.mvc.beans.ResultMessage;
import maf.web.util.SerializeHashtable;
import modules.community.mboard.MBoardManager;
import modules.community.mboard.beans.MbbsMetaBean;
import modules.community.mboard.dao.MbbsMetaDB;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class AdminMeta extends MafAction {
    private  Log logger = LogFactory.getLog(this.getClass());

	private final String MESSAGE_BUNDLENAME = "common.message.board";
	protected final String MSG_PAGE = "message";
	protected final String ERROR_MSG_PAGE = "error";
	protected final String MBOARD_PATH = "/mboard";
	protected SerializeHashtable listOp = null;
	protected String listOpStr = "";
	protected String forward = null;
	
	public void preProcess(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		
		chkLogin();
		
		this.listOpStr = _req.getParameter("LISTOP", "");
		this.listOp = new SerializeHashtable(listOpStr);
		/*
		 * MyMBoardAction�� extends�� ��� Class���� �̸޼ҵ带 �����Ѵ�.
		 * 
		 */
//		doWork(_req, _res);
		listOp.put("cmd", "");
		result.setAttribute("listOp", listOp);
	}
	
	/**
	 * �Խ��� ��� ��������
	 * @param _req
	 * @param response
	 * @return
	 */
	public void cmdList(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		try {

			String v_grp = _req.getParameter("v_grp", listOp.get("v_grp", "www"));
			String v_srch = _req.getParameter("v_srch", listOp.get("v_srch", ""));

			listOp.put("v_grp", v_grp);
			listOp.put("v_srch", v_srch);

			result.setAttribute("listOpStr", listOp.getSerializeUrl());

			MbbsMetaDB om = MbbsMetaDB.getInstance();
			result.setAttribute("titleKey", "title.board.metalist");
			result.setAttribute("groups", om.getBbsGrp(super.oDb));
			result.setAttribute("boards", om.getBbsMetaBeans(super.oDb, v_grp, v_srch));
			result.setForward("metaList");

		} catch (Exception e) {
			result.setSuccess(false, new ResultMessage(this.MESSAGE_BUNDLENAME, "list.error"));
		}
	}
	
	/**
	 * �Խ��� ����(÷�� ���� ����)
	 * @param _req
	 * @param response
	 * @return
	 */
	public void cmdDelete(MyHttpServletRequest _req, HttpServletResponse response) {

		String bid = _req.getParameter("bid", "NEW");
		try {
			MbbsMetaDB.getInstance().delete(super.oDb, bid, _req.getRealPath(MBoardManager.ATT_FILE_PATH));
			result.setNext(super.controlAction);
			result.setSuccess(true, new ResultMessage(this.MESSAGE_BUNDLENAME, "delete.ok"));
		} catch (Exception e) {
			result.setSuccess(false, new ResultMessage(this.MESSAGE_BUNDLENAME, "delete.fail"));
		}
	}
	
	/**
	 * �Խ��� Update �� form ����
	 * @param _req
	 * @param response
	 * @return
	 */
	public void cmdEdit(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		String bid = _req.getParameter("bid", "NEW");

		listOp.put("bid", bid);

		try {
			MbbsMetaDB om = MbbsMetaDB.getInstance();

			_req.setAttribute("titleKey", "title.board.metawrite");
			_req.setAttribute("groups", om.getBbsGrp(super.oDb));
			if ("NEW".equals(bid)) {
				_req.setAttribute("board", new MbbsMetaBean());
			} else {
				_req.setAttribute("board", om.view(super.oDb, bid));
			}
			result.setForward("metaWrite");
		} catch (Exception e) {
			result.setSuccess(false, new ResultMessage(this.MESSAGE_BUNDLENAME, "update.fail"));
		}
	}
	
	/**
	 * �ű� �Խ��� ���� ��
	 * @param _req
	 * @param response
	 * @return
	 */
	public void cmdAdd(MyHttpServletRequest _req, HttpServletResponse response) {
		String bid = "";
		listOp.put("bid", bid);
		try {
			MbbsMetaDB om = MbbsMetaDB.getInstance();
			result.setAttribute("titleKey", "title.board.metawrite");
			result.setAttribute("groups", om.getBbsGrp(super.oDb));
			result.setAttribute("board", new MbbsMetaBean());
			result.setForward("metaWrite");
		} catch (Exception e) {
			result.setSuccess(false, new ResultMessage(this.MESSAGE_BUNDLENAME, "insert.fail"));
		}
	}
	
	/**
	 * �Խ��� ���� ����
	 * @param _req
	 * @param response
	 * @return
	 */
	public void cmdUpdateAct(MyHttpServletRequest _req, HttpServletResponse response) {
		String bid = _req.getParameter("bid", "NEW");

		int AffectedRows = -1;
		MbbsMetaDB dao = MbbsMetaDB.getInstance();
		Map param = _req.getKeyValueMap();

		try{
			AffectedRows = dao.update(super.oDb, param);
			result.setNext(super.controlAction + "?LISTOP=" + listOpStr + "&bid=" + bid);
			result.setSuccess(true, new ResultMessage(this.MESSAGE_BUNDLENAME, "update.ok"));
		} catch ( Exception e) {
			result.setSuccess(false, new ResultMessage(this.MESSAGE_BUNDLENAME, "update.fail"));
		}
	}
	
	/**
	 * String[]�� �ܼ� String���� ���ļ� Return 
	 * @param s
	 * @return
	 */
	private String array2String(String[] s) {
		StringBuffer sAuth = new StringBuffer(20);
		if (s != null) {
			for(int i = 0; i < s.length; i++) {
				sAuth.append(s[i]);
			}
		}
		return sAuth.toString();
	}
	
	/**
	 * �ű� �Խ��� ����
	 * @param _req
	 * @param response
	 * @return
	 */
	public void cmdInsertAct(MyHttpServletRequest _req, HttpServletResponse response) {

		String bid = _req.getParameter("bid", "NEW");
		int AffectedRows = -1;
		MbbsMetaDB dao = MbbsMetaDB.getInstance();
		MbbsMetaBean oBean = new MbbsMetaBean();
		try{
			Setter setter = new Setter(oBean, _req);
			setter.setProperty("*");
			

			
			oBean.setBid(oBean.getBid().toUpperCase());
			AffectedRows = dao.insert(super.oDb, oBean);
			result.setNext(super.controlAction + "?LISTOP=" + listOpStr + "&bid=" + bid);
			result.setSuccess(true, new ResultMessage(this.MESSAGE_BUNDLENAME, "insert.ok"));
		} catch ( Throwable e) {
			result.setSuccess(false, new ResultMessage(this.MESSAGE_BUNDLENAME, "insert.fail"));
		}
	}
}
