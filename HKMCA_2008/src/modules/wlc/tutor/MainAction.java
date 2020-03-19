/*
 * Created on 2006. 11. 03
 *
 * Copyright (c) 2004 UBQ All rights reserved.
 */
package modules.wlc.tutor;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import maf.MafConstant;
import maf.aam.MafUserManager;
import maf.beans.NavigatorInfo;
import maf.beans.NavigatorOrderInfo;
import maf.exception.MafException;
import maf.mdb.sqlhelper.SqlWhereBuilder;
import maf.mdb.sqlhelper.Where;
import maf.web.http.MyHttpServletRequest;
import maf.web.mvc.action.MafAction;
import maf.web.util.SerializeHashtable;



/**
 * 과목 목록 가져 오기 
 * @author bluevlad
 *
 */
public class MainAction extends MafAction {
    //private Log logger = LogFactory.getLog(this.getClass());
	SerializeHashtable listOp = null;

    public void preProcess(MyHttpServletRequest _req, HttpServletResponse _res)
    throws MafException {
    	super.chkLogin();
    	this.listOp = new SerializeHashtable( _req.getParameter(MafConstant.LIST_OP_NAME) );
    }

    public void postProcess(MyHttpServletRequest _req, HttpServletResponse _res)
    throws MafException {
    	result.setAttribute(MafConstant.LIST_OP_NAME, listOp);
    }

    /**
     * 목록 가져오기
     *
     * @param _req
     * @param _res
     */
    public void cmdList(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {

        final String[] searchFields = {"sjt_cd", "lec_nm", "crt_lvl", "lec_sdate", "lec_edate", "lec_stat", "adminid"};

        NavigatorInfo navigator = new NavigatorInfo(_req, this.listOp, new NavigatorOrderInfo("A:lec_cd"));

        Map param = this.listOp.getMergedParam(_req, searchFields);
        param.put("usn", super.sessionBean.getUsn());
   
        SqlWhereBuilder wb = oDb.getWhereBuilder();
        wb.addCond(Where.eq("x.sjt_cd", ":sjt_cd"));
        wb.addCond(Where.like("x.lec_nm", ":lec_nm"));
        wb.addCond(Where.eq("x.crt_lvl", ":crt_lvl"));
        wb.addCond(Where.more("x.lec_sdate", ":lec_sdate"));
        wb.addCond(Where.less("x.lec_edate", ":lec_edate"));
        wb.addCond(Where.eq("x.lec_stat", ":lec_stat"));
//        if(!JmfUserManager.isSuperAdmin(super.sessionBean)){
            param.put("adminid", super.sessionBean.getUserid());
            wb.addCond(Where.eq("x.adminid", ":adminid"));
//        }
        MainDB.getList(super.oDb, navigator, param, wb.getWhereString(param), false);
        result.setAttribute("navigator", navigator);
        result.setForward("list");
    }
}