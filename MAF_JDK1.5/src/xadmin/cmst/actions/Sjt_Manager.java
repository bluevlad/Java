/*
 * Sjt_Manager.java, @ 2005-05-12
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
import xadmin.cmst.beans.SjtMstBean;
import xadmin.cmst.dao.Crs_ManagerDB;
import xadmin.cmst.dao.Sjt_ManagerDB;
import xadmin.common.actions.AdminAction;

/**
 * @author goindole
 * 
 * 교과목 관리
 */
public class Sjt_Manager extends AdminAction {
	public String doWork(MyHttpServletRequest _req, HttpServletResponse _res)
			throws MafException {
		String forward = null;
		String cmd = _req.getP("cmd", "list");
		String v_key = _req.getP("v_key", "");
		String v_srch = _req.getP("v_srch", "");
		String v_sort = _req.getP("v_sort", "");
		String v_crscode = _req.getP("v_crscode", "ALL");

		int v_page = _req.getIntParameter("v_page", 1);
		v_page = (v_page < 1) ? 1 : v_page;
		int page_size = 15;

		int count = 0;
		int ret = 0;

		List list = null;

		Sjt_ManagerDB dao = Sjt_ManagerDB.getInstance();

		if ("list".equals(cmd)) {
			List crslist = Crs_ManagerDB.getCrsList(oDb);

			list = dao.getSjtList(oDb, v_page, page_size, v_key, v_srch,
					v_sort, v_crscode);
			count = dao.getCrsCount(oDb, v_key, v_srch, v_sort, v_crscode);

			NavigatorInfo navigator = null;
			navigator = new NavigatorInfo();

			navigator.setCurrentPage(v_page);
			navigator.setPageSize(page_size);
			navigator.setTotalCount(count);
			navigator.sync();

			_req.setAttribute("navigator", navigator);
			_req.setAttribute("list", list);
			_req.setAttribute("crslist", crslist);

			forward = cmd;
		} else if ("update".equals(cmd)) {

			List crslist = Crs_ManagerDB.getCrsList(oDb);

			String sjtcode = _req.getP("sjtcode");
			SjtMstBean item = dao.getSjtView(oDb, sjtcode);
			_req.setAttribute("item", item);
			_req.setAttribute("crslist", crslist);
			forward = "edit";
		} else if ("delete".equals(cmd)) {
			String sjtcode = _req.getP("sjtcode");

			try {
				ret = dao.remove(oDb, sjtcode);
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

		} else if ("update_act".equals(cmd)) {
			SjtMstBean mbean = new SjtMstBean();

			Setter setter = new Setter(mbean, _req);
			setter.setProperty("*");
			mbean.setUpdate_id(super.sessionBean.getUserid());

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
		} else if ("insert_act".equals(cmd)) {
			SjtMstBean mbean = new SjtMstBean();

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
		} else if ("insert".equals(cmd)) {
			List crslist = Crs_ManagerDB.getCrsList(oDb);

			SjtMstBean mbean = new SjtMstBean();
			_req.setAttribute("item", mbean);
			_req.setAttribute("crslist", crslist);
			forward = "edit";
/*			
			// 컨텐츠 목록 (차시관리)
		} else if ("chapter_list".equals(cmd)) {
			String sjtcode = _req.getP("sjtcode");
			List crslist = dao.getInxList(oDb, sjtcode);

			_req.setAttribute("list", crslist);
			_req.setAttribute("sjtcode", sjtcode);

			forward = "chapter_list";
		} else if ("chapter_update".equals(cmd)) {
			String sjtcode = _req.getP("sjtcode");
			String chasi = _req.getP("chasi");
			Inx_lstBean item = dao.getInxItem(oDb, sjtcode, chasi);
			_req.setAttribute("item", item);
			_req.setAttribute("sjtcode", sjtcode);

			forward = "chapter_update";
		} else if ("chapter_update_act".equals(cmd)) {
			String sjtcode = _req.getP("sjtcode");

			Inx_lstBean mbean = new Inx_lstBean();

			Setter setter = new Setter(mbean, _req);
			setter.setProperty("*");
			mbean.setUpdate_id(super.sessionBean.getUserid());

			try {
				ret = dao.inxUpdate(oDb, mbean);
			} catch (Throwable e) {

			}

			if (ret > 0) {
				forward = "message";
				_req.setAttribute("message", Message.UPDATE_OK);
				_req.setAttribute("next", super.control_action
						+ "?cmd=chapter_list&sjtcode=" + sjtcode);
			} else {
				forward = "error";
				_req.setAttribute("message", Message.UPDATE_FAIL);
			}
		} else if ("chapter_insert".equals(cmd)) {
			String sjtcode = _req.getP("sjtcode");
			Inx_lstBean item = new Inx_lstBean();
			_req.setAttribute("item", item);
			_req.setAttribute("sjtcode", sjtcode);

			forward = "chapter_update";
		} else if ("chapter_insert_act".equals(cmd)) {
			String sjtcode = _req.getP("sjtcode");

			Inx_lstBean mbean = new Inx_lstBean();

			Setter setter = new Setter(mbean, _req);
			setter.setProperty("*");
			mbean.setUpdate_id(super.sessionBean.getUserid());

			try {
				ret = dao.inxInsert(oDb, mbean);
			} catch (Throwable e) {

			}

			if (ret > 0) {
				forward = "message";
				_req.setAttribute("message", Message.UPDATE_OK);
				_req.setAttribute("next", super.control_action
						+ "?cmd=chapter_list&sjtcode=" + sjtcode);
			} else {
				forward = "error";
				_req.setAttribute("message", Message.UPDATE_FAIL);
			}
*/			
		} else {
			forward = "error";
			_req.setAttribute("message", Message.INVALID_REQUEST);
		}
		dao = null;
		return forward;

	}
}
