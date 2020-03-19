/*
 * Created on 2006. 10. 12
 *
 * Copyright (c) 2004 UBQ All rights reserved.
 */
package modules.community.poll;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;


import maf.MafConstant;
import maf.exception.MafException;
import maf.logger.Logging;
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

    public void postProcess(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
    	result.setAttribute(MafConstant.LIST_OP_NAME, listOp);
    }
    
    /**
     * 투표하기
     * @param _req
     * @param _res
     * @throws MafException
     */
    public void cmdPoll(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		Map param = _req.getKeyValueMap();

        int rcnt = 0;

        try {
        	rcnt = PollDB.updateItemHit(super.oDb, param);
        } catch (Throwable e) {
            Logging.trace(e);
        }

       	UrlAddress next = new UrlAddress (super.controlAction);
		next.addParam("cmd", "result");
		next.addParam("poll_id", _req.getP("poll_id"));
		result.setNext(next);
        if( rcnt > 0 ) {
            result.setSuccess(true, new ResultMessage(this.MESSAGE_BUNDLENAME, "poll.ok"));
    	} else {
            result.setSuccess(true, new ResultMessage(this.MESSAGE_BUNDLENAME, "poll.fail"));
    	}

    }    

    /**
     * poll 결과 보기
     * @param _req
     * @param _res
     * @throws MafException
     */
    public void cmdViewResult(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		Map param = _req.getKeyValueMap();
		
		result.setAttribute("poll",PollDB.getOne(oDb, param));
		result.setAttribute("items",PollDB.getPollItems(oDb, param));
		result.setForward("result");
    }
}