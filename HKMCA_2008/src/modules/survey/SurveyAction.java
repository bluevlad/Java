/*
 * Created on 2006. 06. 14.
 *
 * Copyright (c) 2004 UBQ All rights reserved.
 */
package modules.survey;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;
import maf.MafConstant;
import maf.exception.MafException;
import maf.util.StringUtils;
import maf.web.http.MyHttpServletRequest;
import maf.web.http.UrlAddress;
import maf.web.mvc.action.MafAction;
import maf.web.mvc.beans.ResultMessage;
import maf.web.util.SerializeHashtable;
import modules.etest.support.Etest;
import modules.xadmin.survey.SurveyManagerDB;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 설문 참여
 */
public class SurveyAction extends MafAction {
    final private Log logger = LogFactory.getLog(this.getClass());
    SerializeHashtable listOp = null;
    private final String MESSAGE_BUNDLENAME = "common.message";

    public void preProcess(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
        this.listOp = new SerializeHashtable(_req.getParameter(MafConstant.LIST_OP_NAME));
    }

    public void postProcess(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
        result.setAttribute(MafConstant.LIST_OP_NAME, listOp);
    }

    /**
     * 설문 리스트를 보여준다.
     * 
     * @param _req
     * @param _res
     */
    public void cmdList(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
        Map param = new HashMap();
        param.put("usn", super.sessionBean.getUsn());
        param.put("lang", super.locale.getLanguage());

        result.setAttribute("list", SurveyDB.getSurveyList(super.oDb, param));
        result.setForward("list");
        
    }

    /**
     * 하나의 record를 읽어와서 보여준다.
     * 
     * @param _req
     * @param _res
     */
    public void cmdView(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
        Map param = new HashMap();
        param.put("usn", super.sessionBean.getUsn());
        param.put("lang", super.locale.getLanguage());
        param.put("surveyid", _req.getP("surveyid"));

        result.setAttribute("item", SurveyDB.getSurvey(super.oDb, param));
        result.setAttribute("set", SurveyManagerDB.getRstList(super.oDb, param));
        result.setForward("view");
    }

    /**
     * insert
     * 
     * @param _req
     * @param _res
     */
    public void cmdSurvey(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
        int rcnt = 0;
        Map param = _req.getKeyValueMap();
        boolean chk = false;
        try {
            oDb.setAutoCommit(false);
            param.put("usn", super.sessionBean.getUsn());
            param.put("lang", super.locale.getLanguage());
            String[] v_queid = _req.getParameterValues("v_queid");
            String[] v_quetype = _req.getParameterValues("v_quetype");

            rcnt += SurveyDB.insertRst(super.oDb, param);
            
            if(v_queid != null) {
                for(int i = 0; i < v_queid.length; i ++ ) {
                    param.put("queid", v_queid[i]);
                    if (v_quetype[i].equals("m")){
                        String[] usransw = _req.getParameterValues(v_queid[i]);
                        param.put("usransw", StringUtils.arrayToDelimitedString(usransw, Etest.ANS_SEPERATOR));
                    } else if(v_quetype[i].equals("s") || v_quetype[i].equals("t") || v_quetype[i].equals("d")){
                        param.put("usransw", _req.getP(v_queid[i]));
                    }
                    rcnt += SurveyDB.insertItem(super.oDb, param);
                }
            }

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
            result.setSuccess(true, new ResultMessage(this.MESSAGE_BUNDLENAME, "message.insert.ok"));
        } else {
            result.setSuccess(false, new ResultMessage(this.MESSAGE_BUNDLENAME, "message.insert.fail"));
        }
    }

}
