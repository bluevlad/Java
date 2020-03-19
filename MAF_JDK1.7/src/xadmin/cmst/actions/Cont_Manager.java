/*
 * Created on 2005. 6. 8.
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
import miraenet.MiConfig;
import miraenet.Message;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import xadmin.cmst.beans.Cnt_ItemBean;
import xadmin.cmst.beans.Cnt_MstBean;
import xadmin.cmst.dao.Cnt_ManagerDB;
import xadmin.cmst.dao.Sjt_ManagerDB;
import xadmin.common.actions.AdminAction;

/**
 * @author goindole
 * 
 * 강의 컨텐츠 관리
 */
public class Cont_Manager extends AdminAction {
	private  Log logger = LogFactory.getLog(this.getClass());
	
    public String doWork(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
        String forward = null;
        String cmd = _req.getP("cmd", "list");
        String v_key = _req.getP("v_key", "");
        String v_srch = _req.getP("v_srch", "");
        String v_sort = _req.getP("v_sort", "");
        String v_crscode = _req.getP("v_crscode", "ALL");


        int v_page = _req.getIntParameter("v_page");
        v_page = (v_page < 1)? 1 : v_page;
        int page_size = 15;

        int count = 0;
        int ret = 0;

        List list = null;

        Cnt_ManagerDB dao = Cnt_ManagerDB.getInstance();

        if ("list".equals(cmd)) {
            List sjtlist = Sjt_ManagerDB.getSjtList(oDb);

            String sjtcode = _req.getP("sjtcode");
            list = dao.getCntList(oDb, v_page, page_size, v_key, v_srch, v_sort, v_crscode);
            count = dao.getCntCount(oDb, v_key, v_srch, v_sort, v_crscode);

            NavigatorInfo navigator = null;
            navigator = new NavigatorInfo();

            navigator.setCurrentPage(v_page);
            navigator.setPageSize(page_size);
            navigator.setTotalCount(count);
            navigator.sync();

            _req.setAttribute("navigator", navigator);
            _req.setAttribute("sjtlist", sjtlist);
            _req.setAttribute("list", list);
            _req.setAttribute("sjtcode", sjtcode);

            forward = "list";
        } else if ("update".equals(cmd)) {
            String sjtcode = _req.getP("sjtcode");
            String cnt_id = _req.getP("cnt_id");
            List cntList = null;
            /**
             * 컨텐츠 마스터 정보 가져 오기
             */
            Cnt_MstBean item = dao.getCntItem(oDb, sjtcode, cnt_id);
            if (item != null) {
                cntList = dao.getContentsList(oDb, item);
            }
            ;

            _req.setAttribute("item", item);
            _req.setAttribute("cntList", cntList);
            _req.setAttribute("sjtcode", sjtcode);
            _req.setAttribute("cnt_id", cnt_id);

            forward = "update";
        } else if ("update_act".equals(cmd)) {
            String sjtcode = _req.getP("sjtcode");

            Cnt_MstBean mbean = new Cnt_MstBean();
            oDb.setAutoCommit(false);

            Setter setter = new Setter(mbean, _req);
            setter.setProperty("sjtcode");
            setter.setProperty("cnt_id");
            setter.setProperty("chptitle");
            setter.setProperty("prof");
            mbean.setUpdate_id(super.sessionBean.getUserid());
            String check[] = _req.getParameterValues("check");
            try {
                ret = dao.updateMst(oDb, mbean);

                Cnt_ItemBean cbean = new Cnt_ItemBean();
                cbean.setSjtcode(mbean.getSjtcode());
                cbean.setCnt_id(mbean.getCnt_id());
                cbean.setUpdate_id(super.sessionBean.getUserid());

                oDb.setDebug(true);
                dao.deleteAllContentItem(oDb, cbean);
                if (check != null) {
                    for (int i = 0; i < check.length; i++) {
                        logger.debug(check[i] + " : " + check[i]);
                        if (check[i] != null) {
                            cbean.setChasi(_req.getIntParameter("chasi_" + check[i]));
                            cbean.setChp_title(_req.getP("chp_title_" + check[i]));
                            cbean.setChp_time(_req.getIntParameter("chp_time_" + check[i]));
                            cbean.setChapters(_req.getIntParameter("chapters_" + check[i]));
                            cbean.setFilename(_req.getP("filename_" + check[i]));
                            dao.mergeContentItem(oDb, cbean);
                        }
                    }
                }
                cbean = null;

                oDb.commit();
            } catch (Throwable e) {
                logger.error(e.getMessage());
                ret = 0;
                oDb.rollback();
            }

            if (ret > 0) {
                forward = "message";
                _req.setAttribute("message", Message.UPDATE_OK);
                _req.setAttribute("next", super.control_action + "?cmd=list&sjtcode=" + sjtcode);
            } else {
                forward = "error";
                _req.setAttribute("message", Message.UPDATE_FAIL);
            }
        } else if ("insert".equals(cmd)) {
//            String sjtcode = _req.getP("sjtcode");
            Cnt_MstBean item = new Cnt_MstBean();
            _req.setAttribute("item", item);
            List sjtlist = Sjt_ManagerDB.getSjtList(oDb);
            _req.setAttribute("sjtlist", sjtlist);
            forward = "update";
        } else if ("insert_act".equals(cmd)) {
            String sjtcode = _req.getP("sjtcode");

            Cnt_MstBean mbean = new Cnt_MstBean();
            oDb.setAutoCommit(false);

            Setter setter = new Setter(mbean, _req);
            setter.setProperty("sjtcode");
            setter.setProperty("cnt_id");
            setter.setProperty("chptitle");
            setter.setProperty("prof");
            mbean.setUpdate_id(super.sessionBean.getUserid());
            String check[] = _req.getParameterValues("check");
            try {
                ret = dao.insertMst(oDb, mbean);

                Cnt_ItemBean cbean = new Cnt_ItemBean();
                cbean.setSjtcode(mbean.getSjtcode());
                cbean.setCnt_id(mbean.getCnt_id());
                cbean.setUpdate_id(super.sessionBean.getUserid());

                oDb.setDebug(true);

                dao.deleteAllContentItem(oDb, cbean);
                if (check != null) {
                    for (int i = 0; i < check.length; i++) {
                        logger.debug(check[i] + " : " + check[i]);
                        if (check[i] != null) {
                            cbean.setChasi(_req.getIntParameter("chasi_" + check[i]));
                            cbean.setChp_title(_req.getP("chp_title_" + check[i]));
                            cbean.setChp_time(_req.getIntParameter("chp_time_" + check[i]));
                            cbean.setChapters(_req.getIntParameter("chapters_" + check[i]));
                            cbean.setFilename(_req.getP("filename_" + check[i]));
                            dao.mergeContentItem(oDb, cbean);
                        }
                    }
                }
                cbean = null;

                oDb.commit();
            } catch (Throwable e) {
                logger.error(e.getMessage());
                ret = 0;
                oDb.rollback();
            }

            if (ret > 0) {
                forward = "message";
                _req.setAttribute("message", Message.INSERT_OK);
                _req.setAttribute("next", super.control_action + "?cmd=list&sjtcode=" + sjtcode);
            } else {
                forward = "error";
                _req.setAttribute("message", Message.INSERT_FAIL);
            }
        } else if ("info".equals(cmd)) {
            _req.setAttribute("contents_path", MiConfig.DEFAULT_CONTENTS_FILE_PATH);
            forward = "info";
        } else if("getlecture".equals(cmd)) {
        	
        } else {
            forward = "error";
            _req.setAttribute("message", Message.INVALID_REQUEST);
        }
        dao = null;
        return forward;
    }
}
