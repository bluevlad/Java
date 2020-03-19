/*
 * Created on 2006. 11. 03
 *
 * Copyright (c) 2004 UBQ All rights reserved.
 */
package modules.wlc.learner;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;


import maf.MafConstant;
import maf.exception.MafException;
import maf.web.http.MyHttpServletRequest;
import maf.web.http.UrlAddress;
import maf.web.mvc.action.MafAction;
import maf.web.mvc.beans.ResultMessage;
import maf.web.util.SerializeHashtable;
import modules.wlc.dao.LectureDB;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MainAction extends MafAction {
    private Log logger = LogFactory.getLog(this.getClass());
	SerializeHashtable listOp = null;
    private final String MESSAGE_BUNDLENAME = "common.message";

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

        Map param = new HashMap();
        param.put("usn", super.sessionBean.getUsn());
        // 수강중 목록 가져오기
        result.setAttribute("list_ing", MainDB.getList(super.oDb,  param));
        // 수강신청 가능 목록 가져 오기 
        result.setAttribute("list_ext",  MainDB.getListExt(super.oDb,  param));
        result.setForward("list");
    }

    /**
     * 
     * @param _req
     * @param _res
     */
    public void cmdView(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {

        Map param = new HashMap();
        param.put("lec_cd", _req.getP("lec_cd"));
                
        result.setAttribute("item", LectureDB.getOne(super.oDb, param));
        result.setAttribute("sch", LectureDB.getLecSch(super.oDb, param));
        result.setAttribute("usn", super.sessionBean.getUsn());
        result.setAttribute("lec_cd", _req.getP("lec_cd"));
        result.setForward("view");
    }   

    /**
     * insert
     * 
     * @param _req
     * @param _res
     */
    public void cmdInsert(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
        String usn = super.sessionBean.getUsn();
        int rcnt = 0;
        Map param = _req.getKeyValueMap();
        param.put("usn", usn);
        param.put("lec_cd", _req.getP("lec_cd"));
        param.put("lec_type", _req.getP("lec_type"));
        param.put("req_stat", "LP");
        param.put("company_id", "");
                
        boolean chk = false;
        try {
            oDb.setAutoCommit(false);
            rcnt = BasClassDB.insertOne(super.oDb, param);
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
            if(rcnt == -99) {
                result.setSuccess(true,new ResultMessage(this.MESSAGE_BUNDLENAME, "req.dup"));                
            } else {
                result.setSuccess(true,new ResultMessage(this.MESSAGE_BUNDLENAME, "insert.ok", new Integer(rcnt)));                
            }
        } else {
            result.setSuccess(false,new ResultMessage(this.MESSAGE_BUNDLENAME, "insert.fail", new Integer(rcnt)));
        }
    }
    
}