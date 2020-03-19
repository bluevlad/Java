/*
 * Created on 2005. 10. 31.
 * 게시판 메타정보관리 (등록/수정/삭제기능)
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
		 * MyMBoardAction을 extends한 모든 Class에서 이메소드를 구현한다.
		 * 
		 */
//		doWork(_req, _res);
		listOp.put("cmd", "");
		result.setAttribute("listOp", listOp);
	}
	
	/**
	 * 게시판 목록 가져오기
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
	 * 게시판 삭제(첨부 파일 포함)
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
	 * 게시판 Update 용 form 생성
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
	 * 신규 게시판 생성 폼
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
	 * 게시판 정보 수정
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
	 * String[]를 단순 String으로 합쳐서 Return 
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
	 * 신규 게시판 생성
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
