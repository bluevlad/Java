/*
 * Created on 2006. 06. 14.
 *
 * Copyright (c) 2004 (주)UBQ  All rights reserved.
 */
package modules.wlc.classroom.tutor;

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
import maf.web.mvc.beans.ResultMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 설문 관리
 */
public class SurveyManagerAction extends BaseTutorClassAction {
	private Log logger = LogFactory.getLog(TestManagerAction.class);
    private final String MESSAGE_BUNDLENAME = "common.message";

    /**
     * 목록 가져오기
     * 
     * @param _req
     * @param _res
     */
    public void cmdList(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
        final String[] searchFields = {"s_surveytitle"};
        // Order default 값 설정 시
        NavigatorInfo navigator = new NavigatorInfo(_req, this.listOp);
        Map param = this.listOp.getMergedParam(_req, searchFields);
        param.put("lec_cd", super.lectureInfo.getLec_cd());

        SqlWhereBuilder wb = oDb.getWhereBuilder();
        wb.addCond(Where.like("surveytitle", ":s_surveytitle"));
        SurveyManagerDB.getList(super.oDb, navigator, param, wb.getWhereString(param), false);
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

        result.setForward("add");
    }

    /**
     * edit form 
     * 
     * @param _req
     * @param _res
     */
    public void cmdEdit(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
        Map param = new HashMap();
        param.put("sjt_cd", super.lectureInfo.getSjt_cd());
        param.put("lec_cd", super.lectureInfo.getLec_cd());
        param.put("surveyid", _req.getP("surveyid"));
        param.put("lang", super.locale.getLanguage());

        result.setAttribute("item", SurveyManagerDB.getOne(super.oDb, param));
        result.setAttribute("bnk", SurveyManagerDB.getSrvBnkList(super.oDb, param));
        result.setForward("edit");
    }

    /**
     * 하나의 record를 읽어와서 보여준다.
     * 
     * @param _req
     * @param _res
     */
    public void cmdView(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
        Map param = new HashMap();
        param.put("lang", super.locale.getLanguage());
        param.put("surveyid", _req.getP("surveyid"));

        result.setAttribute("set", SurveyManagerDB.getRstList(super.oDb, param));
        result.setAttribute("ans", SurveyManagerDB.getAnsList(super.oDb, param));
        result.setForward("view");
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
        int rcnt = 0;
        
        String[] v_queid = _req.getParameterValues("v_queid");
        param.put("surveyid", _req.getP("surveyid"));
        param.put("upt_id", super.sessionBean.getUsn());
        param.put("reg_id", super.sessionBean.getUsn());
        param.put("lang", super.locale.getLanguage());

        try {
            oDb.setAutoCommit(false);
            rcnt = SurveyManagerDB.updateOne(super.oDb, param);
            rcnt = SurveyManagerDB.deleteSrvObj(super.oDb, param);
            for(int i=0; i<v_queid.length; i++){
                param.put("queid", v_queid[i]);
                param.put("seq", _req.getP("seq_"+v_queid[i]));
                rcnt = SurveyManagerDB.insertSrvObj(super.oDb, param);
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
            result.setSuccess(true, new ResultMessage(this.MESSAGE_BUNDLENAME, "update.ok", new Integer(rcnt)));
        } else {
            result.setSuccess(false, new ResultMessage(this.MESSAGE_BUNDLENAME, "update.fail", new Integer(rcnt)));
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
        boolean chk = false;
        try {
            oDb.setAutoCommit(false);
            param.put("upt_id", super.sessionBean.getUsn());
            param.put("lec_cd", super.lectureInfo.getLec_cd());

            rcnt = SurveyManagerDB.insertOne(super.oDb, param);

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

}