/*
 * CrsMng.java, @ 2005-05-12
 * 
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package xadmin.cmst.actions;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import maf.beans.NavigatorInfo;
import maf.exception.MafException;
import maf.lib.Setter;
import maf.web.http.MyHttpServletRequest;
import miraenet.Message;
import xadmin.cmst.beans.CrsMstBean;
import xadmin.cmst.dao.Crs_ManagerDB;
import xadmin.common.actions.AdminAction;

/**
 * @author goindole 과정코드 관리
 *  
 */
public class Crs_Manager extends AdminAction {
	public String doWork(MyHttpServletRequest _req, HttpServletResponse _res)
			throws MafException {
		String forward = null;
		String cmd = _req.getP("cmd", "list");
		int v_page = _req.getIntParameter("v_page", 1);
		int page_size = 10;

		int count = 0;
		int ret = 0;

		List list = null;

		Crs_ManagerDB dao = Crs_ManagerDB.getInstance();

		if ("list".equals(cmd)) {
			list = dao.getCrsList(oDb, v_page, page_size);
			count = dao.getCrsCount(oDb);

			NavigatorInfo navigator = null;
			navigator = new NavigatorInfo();

			navigator.setCurrentPage(v_page);
			navigator.setPageSize(page_size);
			navigator.setTotalCount(count);
			navigator.sync();

			_req.setAttribute("navigator", navigator);
			_req.setAttribute("list", list);

			forward = cmd;
		} else if ("update".equals(cmd)) {
			String crscode = _req.getP("crscode");
			CrsMstBean item = dao.getCrsView(oDb, crscode);
			_req.setAttribute("item", item);
			forward = "edit";
		} else if ("delete".equals(cmd)) {
			String crscode = _req.getP("crscode");
			//            CrsMstBean item = dao.getCrsView(oDb, crscode);
			//            _req.setAttribute("item", item);

			try {
				ret = dao.remove(oDb, crscode);
			} catch (Throwable e) {
			}

			if (ret > 0) {
				forward = "message";
				_req.setAttribute("message", Message.DELETE_OK);
				_req.setAttribute("next", super.control_action);
			} else {
				forward = "error";
				_req.setAttribute("message", Message.DELETE_FAIL);
			}

		} else if ("modify".equals(cmd)) {
			CrsMstBean mbean = new CrsMstBean();

			Setter setter = new Setter(mbean, _req);
			setter.setProperty("*");
			mbean.setUpdate_id(super.sessionBean.getUserid());
			oDb.setDebug(true);
			try {
				ret = dao.update(oDb, mbean);
			} catch (Throwable e) {
			}

			if (ret > 0) {
				forward = "message";
				_req.setAttribute("message", Message.UPDATE_OK);
				_req.setAttribute("next", super.control_action);
			} else {
				forward = "error";
				_req.setAttribute("message", Message.UPDATE_FAIL);
			}
		} else if ("insert".equals(cmd)) {
			CrsMstBean mbean = new CrsMstBean();

			Setter setter = new Setter(mbean, _req);
			setter.setProperty("*");
			mbean.setUpdate_id(super.sessionBean.getUserid());
			

			try {
				ret = dao.insert(oDb, mbean);
			} catch (Throwable e) {
			}

			if (ret > 0) {
				forward = "message";
				_req.setAttribute("message", Message.INSERT_OK);
				_req.setAttribute("next", super.control_action);
			} else {
				forward = "error";
				_req.setAttribute("message", Message.INSERT_FAIL);
			}
		} else if ("add".equals(cmd)) {
			CrsMstBean mbean = new CrsMstBean();
			_req.setAttribute("item", mbean);
			forward = "edit";
		} else {
			forward = "error";
			_req.setAttribute("message", Message.INVALID_REQUEST);
		}

		return forward;

	}
}
