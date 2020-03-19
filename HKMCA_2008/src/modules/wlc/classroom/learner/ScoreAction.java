/*
 * Created on 2006. 10. 13.
 *
 * Copyright (c) 2006 UBQ All rights reserved.
 */
package modules.wlc.classroom.learner;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;


import maf.beans.NavigatorInfo;
import maf.exception.MafException;
import maf.mdb.sqlhelper.SqlWhereBuilder;
import maf.web.http.MyHttpServletRequest;
import maf.web.util.SerializeHashtable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * ���� ���� ��ȸ 
 * @author bluevlad
 *
 */
public class ScoreAction extends BaseLearnerClassAction {
    private Log logger = LogFactory.getLog(this.getClass());
    SerializeHashtable listOp = null;
    //private final String MESSAGE_BUNDLENAME = "common.message";

    /**
     * �� ��ȸ
     *
     * @param _req
     * @param _res
     */
    public void cmdView(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
        String usn = super.sessionBean.getUsn();
        String lec_cd = super.lecReqInfo.getLec_cd();

        Map param = _req.getKeyValueMap();
        param.put("usn", usn);
        param.put("lec_cd", lec_cd);

        //Ư�� ���� ��������
        result.setAttribute("item", ScoreDB.getOne(super.oDb, param));
        //�򰡱���(����) ���� ��������
        result.setAttribute("ritem", ScoreDB.getOneRate(super.oDb, param));
        result.setAttribute("usn", usn);
        result.setForward("view");
    }

    /**
     * �н� ���� �� ��ȸ
     *
     * @param _req
     * @param _res
     */
    public void cmdJindoView(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
        String usn = super.sessionBean.getUsn();
        String lec_cd = super.lecReqInfo.getLec_cd();
        String lec_num = super.lecReqInfo.getLec_num();

        final String[] searchFields = {"userid", "lec_num"};
        NavigatorInfo navigator = new NavigatorInfo(_req, this.listOp);
        Map param = this.listOp.getMergedParam(_req, searchFields);
        param.put("lec_cd", lec_cd);
        param.put("lec_num", lec_num+"" );
        param.put("usn", usn);

        SqlWhereBuilder wb = oDb.getWhereBuilder();

        ScoreDB.getJindo(super.oDb, navigator, param, wb.getWhereString(param), true);
        result.setAttribute("navigator", navigator);

        //�������� ����
        result.setAttribute("item", ScoreDB.getOneJindo(super.oDb, param));
        //������� �ּ��н��ð�
        result.setAttribute("limit_study_time", ScoreDB.getOneLimit_study_time(super.oDb, param) );
        //���н��ð� ������ ����
        result.setAttribute("totaltime", ScoreDB.getOneLearningTotalTime(super.oDb, param));

        result.setAttribute("usn", usn);
        result.setAttribute("lec_num", lec_num+"");
        result.setForward("jindo_view");
    }

    /**
     * ���� �н��� ���� ���� ��ȸ
     *
     * @param _req
     * @param _res
     */
    public void cmdExamView(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
        String usn = super.sessionBean.getUsn();
        String lec_cd = super.lecReqInfo.getLec_cd();
        String lec_num = super.lecReqInfo.getLec_num();

        final String[] searchFields = {"userid", "lec_num"};
        NavigatorInfo navigator = new NavigatorInfo(_req, this.listOp);
        Map param = this.listOp.getMergedParam(_req, searchFields);
        param.put("lec_cd", lec_cd);
        param.put("lec_num", lec_num+"" );
        param.put("usn", usn);

        SqlWhereBuilder wb = oDb.getWhereBuilder();

        ScoreDB.getExam(super.oDb, navigator, param, wb.getWhereString(param), true);
        result.setAttribute("navigator", navigator);

        result.setAttribute("lec_cd", lec_cd);
        result.setAttribute("usn", usn);
        result.setAttribute("lec_num", lec_num+"");

        result.setForward("exm_view");
    }

    /**
     * �н��� �������� ��ȸ
     *
     * @param _req
     * @param _res
     */
    public void cmdOffView(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
        String usn = super.sessionBean.getUsn();
        String lec_cd = super.lecReqInfo.getLec_cd();
        String lec_num = super.lecReqInfo.getLec_num();

        final String[] searchFields = {"userid", "lec_num"};
        NavigatorInfo navigator = new NavigatorInfo(_req, this.listOp);
        Map param = this.listOp.getMergedParam(_req, searchFields);
        param.put("lec_cd", lec_cd);
        param.put("lec_num", lec_num+"");
        param.put("usn", usn);

        SqlWhereBuilder wb = oDb.getWhereBuilder();

        ScoreDB.getOff(super.oDb, navigator, param, wb.getWhereString(param), true);
        result.setAttribute("navigator", navigator);

        result.setAttribute("lec_cd", lec_cd);
        result.setAttribute("usn", usn);
        result.setAttribute("lec_num", lec_num+"");

        result.setForward("off_view");
    }
}