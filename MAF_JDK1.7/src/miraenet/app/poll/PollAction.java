/*
 * Created on 2006. 10. 12
 *
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package miraenet.app.poll;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import maf.MafConstant;
import maf.exception.MafException;
import maf.util.MafAssert;
import maf.web.TokenProcessor;
import maf.web.http.MyHttpServletRequest;
import maf.web.http.UrlAddress;
import maf.web.mvc.action.MafAction;
import maf.web.mvc.beans.ResultMessage;
import maf.web.util.SerializeHashtable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class PollAction extends MafAction {
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
     * poll 결과 보기
     * @param _req
     * @param _res
     * @throws MafException
     */
    public void cmdViewResult(MyHttpServletRequest _req, HttpServletResponse _res) 
    throws MafException {
		Map param = _req.getKeyValueMap();
		
		
		result.setAttribute("poll",PollDB.getOne(oDb, param));
		result.setAttribute("items",PollDB.getPollItems(oDb, param));
		
		result.setForward("result");
    }
    /**
     * 투표하기
     * @param _req
     * @param _res
     * @throws MafException
     */
    public void cmdPoll(MyHttpServletRequest _req, HttpServletResponse _res) 
    throws MafException {
    	MafAssert.chkEmpty(_req, "poll_id");
    	MafAssert.chkEmpty(_req, "item_id");
    	
    	if (TokenProcessor.getInstance().isTokenValid(_req, true)) {
    		Map param = _req.getKeyValueMap();
    		PollDB.updateItemHit(super.oDb, param);
			UrlAddress next = new UrlAddress (super.controlAction);
			next.addParam("cmd", "viewResult");
			next.addParam("poll_id", _req.getP("poll_id"));
			result.setNext(next);
			logger.debug("next:" + next.getHref());
			result.setSuccess(true,new ResultMessage("Ok"));
    	} else {
    		result.setSuccess(false,new ResultMessage("invalid request!!!"));
    	}
		

    }    
}
