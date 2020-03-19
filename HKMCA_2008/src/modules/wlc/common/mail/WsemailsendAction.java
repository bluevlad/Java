/*
 * Created on 2006. 110. 06.
 *
 * Copyright (c) 2006 UBQ All rights reserved.
 */
package modules.wlc.common.mail;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import maf.MafConstant;
import maf.MafProperties;
import maf.MafUtil;
import maf.exception.MafException;
import maf.util.SessionUtil;
import maf.util.StringUtils;
import maf.web.http.MyHttpServletRequest;
import maf.web.http.UrlAddress;
import maf.web.mvc.action.MafAction;
import maf.web.mvc.beans.ResultMessage;
import maf.web.util.HTMLUtil;
import maf.web.util.SerializeHashtable;
import miraenet.AppConfig;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class WsemailsendAction extends MafAction {
    private Log logger = LogFactory.getLog(this.getClass());
    SerializeHashtable listOp = null;
    private final String MESSAGE_BUNDLENAME = "common.message";

    public void preProcess(MyHttpServletRequest _req, HttpServletResponse _res)
    throws MafException {
        this.listOp = new SerializeHashtable( _req.getParameter(MafConstant.LIST_OP_NAME) );
    }

    public void postProcess(MyHttpServletRequest _req, HttpServletResponse _res)
    throws MafException {
        result.setAttribute(MafConstant.LIST_OP_NAME, listOp);
    }

    /**
     * 등록 화면
     *
     * @param _req
     * @param _res
     */
    public void cmdAdd(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
        String tmplt_id = _req.getP("tmplt_id");
        String mgp_id = _req.getP("mgp_id");
        String userid = super.sessionBean.getUsn();
        String lec_cd = SessionUtil.getLec_cd(super.sessionBean);
        String reqnumb = SessionUtil.getLec_num(super.sessionBean);

        String params[] = (String[])_req.getParameterValues("email");// 메일#ID#이름
        ArrayList ar = new ArrayList();
        StringBuffer userinfo = new StringBuffer();
        if(params != null && params.length > 0) {
            for(int i=0; i < params.length; i++) {
                String[] info = StringUtils.delimitedListToStringArray(params[i], "#");
                ar.add(info);
                userinfo.append(info[2]+";");
            }
        }

        Map param = _req.getKeyValueMap();
        param.put("userid", userid);
        param.put("isuse", "Y");

        result.setAttribute("tmplt_id", tmplt_id);
        result.setAttribute("mgp_id", mgp_id);
        result.setAttribute("lec_cd", lec_cd);
        result.setAttribute("reqnumb", reqnumb);

        result.setAttribute("ar", ar);
        result.setAttribute("userinfo", userinfo.toString());

        result.setForward("add");
    }

    /**
     * 메일보내기
     *
     * @param _req
     * @param _res
     */
    public void cmdSend(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
        String userid = super.sessionBean.getUsn();
        String mgp_id = _req.getP("mgp_id");

        Map param = _req.getKeyValueMap();
        param.put("userid", userid);

        param.put("send_id", "0");
        if(null != mgp_id && !"".equals(mgp_id)) {
            param.put("mgp_id", mgp_id);
        } else {
            param.put("mgp_id", "");
        }
        if(null != mgp_id && !"".equals(mgp_id)) {
            param.put("tmplt_id", mgp_id);
        } else {
            param.put("tmplt_id", "");
        }
        param.put("flag", "inst");

        long send_id = 0;
        boolean chk = false;
        try {
            oDb.setAutoCommit(false);
            send_id = WsemailsendDB.insertOne(super.oDb, param);
            oDb.commit();
            chk = true;
        } catch (Exception e) {
            oDb.rollback();
            logger.error(e.getMessage());
        } finally {
            oDb.setAutoCommit(true);
        }
        WlcEmailMessageBean[] beans = null;
        beans = setMailToInfoBeansParam(send_id, _req, _res);

        sendMail(beans, _req, _res);

        if( chk ) {
            UrlAddress next = new UrlAddress (super.controlAction);
            next.addParam(MafConstant.LIST_OP_NAME, this.listOp.getSerializeUrl());
            result.setNext(next);
            result.setSuccess(true, new ResultMessage(this.MESSAGE_BUNDLENAME, "message.mail.send.ok", new Long(send_id)));
        } else {
            result.setSuccess(false, new ResultMessage(this.MESSAGE_BUNDLENAME, "message.mail.send.fail", new Long(send_id)));
        }
    }

    /**
     * 메일보내기
     *
     * @param _req
     * @param _res
     */
    public void cmdSendMail(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
        String userid = super.sessionBean.getUsn();
        String mgp_id = _req.getP("mgp_id");

        Map param = _req.getKeyValueMap();
        param.put("userid", userid);

        param.put("send_id", "0");
        if(null != mgp_id && !"".equals(mgp_id)) {
            param.put("mgp_id", mgp_id);
        } else {
            param.put("mgp_id", "");
        }
        if(null != mgp_id && !"".equals(mgp_id)) {
            param.put("tmplt_id", mgp_id);
        } else {
            param.put("tmplt_id", "");
        }
        param.put("flag", "inst");

        long send_id = WsemailsendDB.insertOne(super.oDb, param);

        WlcEmailMessageBean[] beans = null;
        beans = setMailToInfoBeansComm(send_id, _req, _res);

        sendMail(beans, _req, _res);

        if( send_id > 0 ) {
            UrlAddress next = new UrlAddress (super.controlAction);
            next.addParam(MafConstant.LIST_OP_NAME, this.listOp.getSerializeUrl());
            result.setNext(next);
            result.setSuccess(true, new ResultMessage(this.MESSAGE_BUNDLENAME, "message.mail.send.ok", new Long(send_id)));
        } else {
            result.setSuccess(false, new ResultMessage(this.MESSAGE_BUNDLENAME, "message.mail.send.fail", new Long(send_id)));
        }
    }
    /**
     * 상세 조회
     *
     * @param _req
     * @param _res
     */
    public void cmdChange(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
        String tmplt_id = _req.getP("tmplt_id");

        Map param = _req.getKeyValueMap();
        param.put("tmplt_id", tmplt_id);

        result.setForward("change");
    }

    /**
     * 메일발송하기
     *
     * @param toBeans WlcEmailMessageBean[]
     * @param req MyHttpServletRequest
     * @param res HttpServletResponse
     * @return
     * @exception
     */
    public void sendMail(WlcEmailMessageBean[] toBeans, MyHttpServletRequest _req, HttpServletResponse _res) {

        String from_id = MafUtil.nvl(_req.getParameter("from_id"), "");            // 보내는 사람 메일주소
        String subject = MafUtil.nvl(_req.getParameter("mail_title"), "");         // 메일제목
        String message = MafUtil.nvl(_req.getParameter("mail_text"), "");          // 메일내용
        String mail_cc = MafUtil.nvl(_req.getParameter("mail_cc"), "");            // 참조
        String mail_bcc = MafUtil.nvl(_req.getParameter("mail_bcc"), "");          // 숨은참조
        String mail_format =MafUtil.nvl(_req.getParameter("mail_format"), "");    // 메일내용 형식(HTML, TEXT)
        String leccode = _req.getP("leccode"); //강의코드

        //HTML여부
        if("T".equals(mail_format)) message = HTMLUtil.nl2br(message);

        MailSendThread dd=null;
        if(!"".equals(from_id)) {
            dd = new MailSendThread(toBeans, message, subject, from_id);
        } else {
            dd = new MailSendThread(toBeans, message, subject, MafProperties.SMTP_SERVER_USER);
        }
        if(dd != null) {
            dd.setCc(mail_cc);
            dd.setBcc(mail_bcc);
            dd.setLeccode(leccode);
            dd.start();
        }
    }

    /**
     * 메일발송그룹을 선택하지 않고 들어온 경우 WlcEmailMessageBean 빈즈에 내용 setter
     *
     * @param send_id 메일발송내용테이블에 등록한번호
     * @param _req MyHttpServletRequest
     * @param _res HttpServletResponse
     * @return WlcEmailMessageBean[]
     * @exception
     */
    public WlcEmailMessageBean[] setMailToInfoBeansParam(long send_id, MyHttpServletRequest _req, HttpServletResponse _res) {
        String userid = super.sessionBean.getUsn(); //발송자 ID

        String from = MafUtil.nvl(_req.getParameter("from_id"), "");            // 보내는 사람 메일주소
        String mail_format =MafUtil.nvl(_req.getParameter("mail_format"), "");    // 메일내용 형식(HTML, TEXT)
        String mail_type = MafUtil.nvl(_req.getParameter("mail_type"), "");        // 메일성격[A:경고, I:알림]
        String leccode = _req.getP("leccode"); //강의코드
        String[] lecname = (String[])_req.getParameterValues("lecname");             // 강의명

        String to = _req.getParameter("usermail");   // 받는사람메일주소(다수인경우 ; 구분하여 들어옴)
        ArrayList ar = new ArrayList();
        if(to.indexOf(";") != -1) {
            String[] info = StringUtils.delimitedListToStringArray(to, ";");
            for(int i=0; i < info.length; i++) {
                ar.add(info[i]);
            }
        } else {
            ar.add(to);
        }

        WlcEmailMessageBean[] toBeans = new WlcEmailMessageBean[ar.size()];
        for(int i=0; i < ar.size();i++) {
            String email = (String)ar.get(i);
            toBeans[i] = new WlcEmailMessageBean();
            toBeans[i].setUsermail(email);
            toBeans[i].setUserid(email);
            toBeans[i].setFrom_id(from);
            toBeans[i].setSend_id(send_id);
            toBeans[i].setMail_format(mail_format);
            toBeans[i].setMail_type(mail_type);
            toBeans[i].setLeccode(leccode);
            toBeans[i].setUpdate_id(userid);
            if(lecname != null && lecname[i]!= null) {
                toBeans[i].setMapping(AppConfig.MAIL_LECNAME, lecname[i]);
            }
        }
        return toBeans;
    }

    /**
     * 메일발송그룹을 선택하지 않고 들어온 경우 WlcEmailMessageBean 빈즈에 내용 setter
     *
     * @param send_id 메일발송내용테이블에 등록한번호
     * @param _req MyHttpServletRequest
     * @param _res HttpServletResponse
     * @return WlcEmailMessageBean[]
     * @exception
     */
    public WlcEmailMessageBean[] setMailToInfoBeansComm(long send_id, MyHttpServletRequest _req, HttpServletResponse _res) {
        String userid = super.sessionBean.getUsn(); //발송자 ID

        String from = MafUtil.nvl(_req.getParameter("from_id"), "");            // 보내는 사람 메일주소
        String mail_format =MafUtil.nvl(_req.getParameter("mail_format"), "");    // 메일내용 형식(HTML, TEXT)
        String mail_type = MafUtil.nvl(_req.getParameter("mail_type"), "");        // 메일성격[A:경고, I:알림]
        String leccode = _req.getP("leccode"); //강의코드
        String[] lecname = (String[])_req.getParameterValues("lecname");             // 강의명

        String[] to = (String[])_req.getParameterValues("usermail");   // 받는사람메일주소 옆에 형식처럼 --> (email#userid#username)

        WlcEmailMessageBean[] toBeans = new WlcEmailMessageBean[to.length];
        for(int i=0; i <to.length; i++) {
            String[] info = StringUtils.delimitedListToStringArray(to[i], "#");
            toBeans[i] = new WlcEmailMessageBean();
            toBeans[i].setUsermail(info[0]);
            toBeans[i].setUserid(info[1]);
            toBeans[i].setUsernm(info[2]);
            toBeans[i].setFrom_id(from);
            toBeans[i].setSend_id(send_id);
            toBeans[i].setMail_format(mail_format);
            toBeans[i].setMail_type(mail_type);
            toBeans[i].setLeccode(leccode);
            toBeans[i].setUpdate_id(userid);
            if(lecname != null && lecname[i]!= null) {
                toBeans[i].setMapping(AppConfig.MAIL_LECNAME, lecname[i]);
            }
        }
        return toBeans;
    }

}