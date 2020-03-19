/*
 * Created on 2008. 11. 03
 *
 * Copyright (c) 2009 UBQ All rights reserved.
 */
package modules.wlc.tutor;

import javax.servlet.http.HttpServletResponse;


import maf.MafConstant;
import maf.exception.MafException;
import maf.util.SessionUtil;
import maf.web.http.MyHttpServletRequest;
import maf.web.mvc.action.MafAction;
import maf.web.util.SerializeHashtable;

/**
 * 교수자 강의실 기본 정보 셋업 
 * @author bluevlad
 *
 */
public class DefaultPage extends MafAction {
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

    public void cmdDefault(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
        SessionUtil.setSjt_cd(sessionBean, _req.getP("sjt_cd"));
        SessionUtil.setLec_cd(sessionBean, _req.getP("lec_cd"));
        if(null != _req.getP("lec_num") && !"".equals(_req.getP("lec_num"))) {
            SessionUtil.setLec_num(sessionBean, _req.getP("lec_num"));
        }
        result.setAttribute("to", _req.getP("to"));
        result.setForward("default");
    }
}