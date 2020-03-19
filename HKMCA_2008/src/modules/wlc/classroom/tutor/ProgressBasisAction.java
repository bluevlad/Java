/*
 * Created on 2006. 9. 27.
 *
 * Copyright (c) 2006 UBQ All rights reserved.
 */
package modules.wlc.classroom.tutor;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;


import maf.MafConstant;
import maf.MafUtil;
import maf.exception.MafException;
import maf.web.http.MyHttpServletRequest;
import maf.web.http.UrlAddress;
import maf.web.mvc.beans.ResultMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 * 진도기준관리
 * @author UBQ
 *
 */
public class ProgressBasisAction extends BaseTutorClassAction {
    private Log logger = LogFactory.getLog(this.getClass());
    
    private final String MESSAGE_BUNDLENAME = "common.message";

    /**
     * 수정
     *
     * @param _req
     * @param _res
     */
    public void cmdEdit(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {

        Map param = _req.getKeyValueMap();
        param.put("lec_cd", super.lectureInfo.getLec_cd());
        result.setAttribute("item", ProgressBasisDB.getLecOne(super.oDb, param));
        result.setAttribute("ritem", ProgressBasisDB.getOne(super.oDb, param));
        result.setForward("edit");
    }

    /**
     * 수정 등록
     *
     * @param _req
     * @param _res
     */
    public void cmdUpdate(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {

        Map param = _req.getKeyValueMap();
        String prg_type[] = _req.getParameterValues("prg_type");
        String lec_weck = MafUtil.nvl(_req.getParameter("lec_weck"),"");
        String lec_datm = MafUtil.nvl(_req.getParameter("lec_datm"),"");
        String wetm = MafUtil.nvl(_req.getParameter("lec_wetm"),"");
        String exm_condition = MafUtil.nvl(_req.getParameter("exm_condition"),"");
        int iwetm = 0;
        if(wetm != null && "1".equals(wetm)) {
            iwetm = 1;
        } else iwetm = 0;

        param.put("lec_cd", super.lectureInfo.getLec_cd());
        param.put("upt_id", super.sessionBean.getUsn());
		
        int rcnt= 0;
		boolean chk = false;
		try {
			oDb.setAutoCommit(false);
			rcnt = ProgressBasisDB.update(super.oDb, super.lectureInfo.getLec_cd(), lec_datm, prg_type, lec_weck, iwetm, exm_condition);
			rcnt = ProgressBasisDB.update(super.oDb, param);
			oDb.commit();
			chk = true;
		} catch ( Exception e) {
			oDb.rollback();
			logger.error(e.getMessage());
			rcnt = 0;
		} finally {
			oDb.setAutoCommit(true);
		}
		
        if( chk) {        	
        	UrlAddress next = new UrlAddress (super.controlAction);
            next.addParam(MafConstant.LIST_OP_NAME, this.listOp.getSerializeUrl());
            result.setNext(next);
            result.setSuccess(true, new ResultMessage(this.MESSAGE_BUNDLENAME, "update.ok", new Integer(rcnt)));
        } else {
            result.setSuccess(false, new ResultMessage(this.MESSAGE_BUNDLENAME, "update.fail", new Integer(rcnt)));
        }
    }
}