/*
 * Created on 2006. 10. 13.
 *
 * Copyright (c) 2006 UBQ All rights reserved.
 */
package modules.wlc.classroom.tutor;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import maf.MafConstant;
import maf.MafUtil;
import maf.beans.NavigatorInfo;
import maf.beans.NavigatorOrderInfo;
import maf.exception.MafException;
import maf.mdb.sqlhelper.SqlWhereBuilder;
import maf.mdb.sqlhelper.Where;
import maf.web.http.MyHttpServletRequest;
import maf.web.http.UrlAddress;
import maf.web.mvc.beans.ResultMessage;


//�α� ����� �� �ּ� ����
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;

public class ScoreAction extends BaseTutorClassAction {
    //private Log logger = LogFactory.getLog(this.getClass());

    private final String MESSAGE_BUNDLENAME = "common.message";


    /**
     * ��� ��ȸ
     *
     * @param _req
     * @param _res
     */
    public void cmdList(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
        final String[] searchFields = {"sch_nm", "sch_userid"};
        
        NavigatorInfo navigator = new NavigatorInfo(_req, this.listOp, new NavigatorOrderInfo("A:userid"));
        Map param = this.listOp.getMergedParam(_req, searchFields);
        param.put("lec_cd", super.lectureInfo.getLec_cd());

        SqlWhereBuilder wb = oDb.getWhereBuilder();
        
        wb.addCond(Where.like("nm",":sch_nm"));
        wb.addCond(Where.eq("userid",":sch_userid"));

        ScoreDB.getList(super.oDb, navigator, param, wb.getWhereString(param), false);
        result.setAttribute("navigator", navigator);
        result.setAttribute("lec_cd", super.lectureInfo.getLec_cd());
        result.setForward("list");
    }

    /**
     * �� ��ȸ
     *
     * @param _req
     * @param _res
     */
    public void cmdView(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {

        Map param = _req.getKeyValueMap();
        param.put("lec_cd", super.lectureInfo.getLec_cd());

        //Ư�� ���� ��������
        result.setAttribute("item", ScoreDB.getOne(super.oDb, param));
        //�򰡱���(����) ���� ��������
        result.setAttribute("ritem", ScoreDB.getOneRate(super.oDb, param));
        result.setAttribute("usn", param.get("usn"));
        result.setForward("view");
    }

    /**
     * ���� ���
     *
     * @param _req
     * @param _res
     */
    public void cmdUpdateScores(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {

        String[] vusn  = _req.getParameterValues("vusn");
        int[] addscores = new int[vusn.length];
        for(int i=0; i<vusn.length; i++){   //for ������ �����ٲ�� �ȵ�
            addscores[i] = MafUtil.parseInt(_req.getParameter(vusn[i]+"_addscore"));  //�߰�����
        }

        int rcnt= ScoreDB.updateScores(super.oDb, super.lectureInfo.getLec_cd(), vusn, addscores, super.sessionBean.getUsn());

        if( rcnt > 0) {
            UrlAddress next = new UrlAddress (super.controlAction);
            next.addParam(MafConstant.LIST_OP_NAME, this.listOp.getSerializeUrl());
            result.setNext(next);
            result.setSuccess(true, new ResultMessage(this.MESSAGE_BUNDLENAME, "update.score.ok", new Integer(rcnt)));
        } else {
            result.setSuccess(false, new ResultMessage(this.MESSAGE_BUNDLENAME, "update.score.fail", new Integer(rcnt)));
        }
    }

    /**
     * �н� ���� �� ��ȸ
     *
     * @param _req
     * @param _res
     */
    public void cmdJindoView(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
        String lec_cd = super.lectureInfo.getLec_cd();
        String usn = _req.getP("usn");

        final String[] searchFields = {"usn"};
        NavigatorInfo navigator = new NavigatorInfo(_req, this.listOp);
        Map param = this.listOp.getMergedParam(_req, searchFields);
        param.put("lec_cd", lec_cd);

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
        result.setForward("jindo_view");
    }

    /**
     * ���� �н��� ���� ���� ��ȸ
     *
     * @param _req
     * @param _res
     */
    public void cmdExamView(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
    	Map param = _req.getKeyValueMap();

        String lec_cd = super.lectureInfo.getLec_cd();

        param.put("lec_cd", lec_cd);
        result.setAttribute("list", ScoreDB.getExam(super.oDb, param));
        result.setAttribute("usn", param.get("usn"));
        result.setForward("exm_view");
    }

    /**
     * ���� �н��� ���� ���� ��ȸ
     *
     * @param _req
     * @param _res
     */
    public void cmdRptView(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {

    	Map param = _req.getKeyValueMap();

        String lec_cd = super.lectureInfo.getLec_cd();

        param.put("lec_cd", lec_cd);
		param.put("pds_cd", "RPT_GIV");

		result.setAttribute("list", ReportDB.getList(super.oDb, param));
        result.setAttribute("rpt", ScoreDB.getRptList(super.oDb, param));
        result.setAttribute("usn", param.get("usn"));

        result.setForward("rpt_view");
    }

    /**
     * �н��� �������� ��ȸ
     *
     * @param _req
     * @param _res
     */
    public void cmdOffView(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
        String lec_cd = super.lectureInfo.getLec_cd();
        String usn  = _req.getP("usn");

        final String[] searchFields = {"userid", "reqnumb"};
        NavigatorInfo navigator = new NavigatorInfo(_req, this.listOp);
        Map param = this.listOp.getMergedParam(_req, searchFields);
        param.put("lec_cd", lec_cd);
        param.put("usn", usn);

        SqlWhereBuilder wb = oDb.getWhereBuilder();

        
        ScoreDB.getOff(super.oDb, navigator, param, wb.getWhereString(param), true);
        result.setAttribute("navigator", navigator);

        result.setAttribute("lec_cd", lec_cd);
        result.setAttribute("usn", usn);

        result.setForward("off_view");
    }

    /**
     * �н��� ��Ÿ���� ��ȸ
     *
     * @param _req
     * @param _res
     */
    public void cmdEtcView(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
        String lec_cd = super.lectureInfo.getLec_cd();
        String usn  = _req.getP("usn");

        final String[] searchFields = {"userid", "reqnumb"};
        NavigatorInfo navigator = new NavigatorInfo(_req, this.listOp);
        Map param = this.listOp.getMergedParam(_req, searchFields);
        param.put("lec_cd", lec_cd);
        param.put("usn", usn);

        SqlWhereBuilder wb = oDb.getWhereBuilder();
        
        ScoreDB.getEtc(super.oDb, navigator, param, wb.getWhereString(param), true);
        result.setAttribute("navigator", navigator);

        result.setAttribute("lec_cd", lec_cd);
        result.setAttribute("usn", usn);

        result.setForward("etc_view");
    }

}