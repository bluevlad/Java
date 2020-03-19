/*
 * Created on 2006. 11. 03
 *
 * Copyright (c) 2004 UBQ All rights reserved.
 */
package modules.mall.order;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;


import maf.MafConstant;
import maf.MafUtil;
import maf.exception.MafException;
import maf.web.http.MyHttpServletRequest;
import maf.web.http.UrlAddress;
import maf.web.mvc.action.MafAction;
import maf.web.mvc.beans.ResultMessage;
import maf.web.util.SerializeHashtable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class BasketAction extends MafAction {
    private Log logger = LogFactory.getLog(this.getClass());
	SerializeHashtable listOp = null;
    private final String MESSAGE_BUNDLENAME = "common.message.order";

    public void preProcess(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
    	this.listOp = new SerializeHashtable( _req.getParameter(MafConstant.LIST_OP_NAME) );
    }

    public void postProcess(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
    	result.setAttribute(MafConstant.LIST_OP_NAME, listOp);
    }

    /**
     * 목록 가져오기
     *
     * @param _req
     * @param _res
     */
    public void cmdBasket(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		Map param = _req.getKeyValueMap();
        param.put("usn", super.sessionBean.getUsn());
		
		result.setAttribute("list", BasketDB.getList(super.oDb, param));
		result.setForward("basket");
    }

    /**
     * 쿠폰정보를 가져온다.
     * 
     * @param _req
     * @param _res
     */
    public void cmdCoupon(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
        Map param = _req.getKeyValueMap();

        param.put("usn", super.sessionBean.getUsn());
        
		result.setAttribute("psell_cd", param.get("psell_cd"));
        result.setForward("coupon");
    }

    /**
     * 
     * @param _req
     * @param _res
     */
    public void cmdAct(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {

		int rcnt = 0;
		Map param = _req.getKeyValueMap();
        param.put("usn", super.sessionBean.getUsn());
        
		try {
			oDb.setAutoCommit(false);
			Map item = BasketDB.getOne(super.oDb, param);
			
            if(item == null){
                rcnt = BasketDB.insertOne(super.oDb, param);
            } else {
                param.put("cnt", (MafUtil.parseInt(item.get("cnt").toString())+1)+"");
                rcnt = BasketDB.updateOne(super.oDb, param);
            }

			oDb.commit();
		} catch (Exception e) {
			oDb.rollback();
			logger.error(e.getMessage());
			rcnt = 0;
		} finally {
			oDb.setAutoCommit(true);
		}
		if (rcnt>0) {
			UrlAddress next = new UrlAddress(super.controlAction);
			next.addParam(MafConstant.LIST_OP_NAME, this.listOp.getSerializeUrl());
			result.setNext(next);
			result.setSuccess(true, new ResultMessage(this.MESSAGE_BUNDLENAME, "basket.insert.ok", new Integer(rcnt)));
		} else {
			result.setSuccess(false, new ResultMessage(this.MESSAGE_BUNDLENAME, "basket.insert.fail", new Integer(rcnt)));
		}
    }   

    /**
     * 
     * @param _req
     * @param _res
     */
    public void cmdUpdate(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {

		int rcnt = 0;
		Map param = _req.getKeyValueMap();
        param.put("usn", super.sessionBean.getUsn());
        
		try {
			oDb.setAutoCommit(false);
            String[] v_psell_cd = _req.getParameterValues("v_psell_cd");
            if(v_psell_cd != null) {
                for(int i = 0; i < v_psell_cd.length; i ++ ) {
                    param.put("psell_cd", v_psell_cd[i]);
                    param.put("cnt", _req.getP("cnt_"+v_psell_cd[i]));
        			rcnt = BasketDB.updateOne(super.oDb, param);
                }
            }
			oDb.commit();
		} catch (Exception e) {
			oDb.rollback();
			logger.error(e.getMessage());
			rcnt = 0;
		} finally {
			oDb.setAutoCommit(true);
		}
		if (rcnt>0) {
			UrlAddress next = new UrlAddress(super.controlAction);
			next.addParam(MafConstant.LIST_OP_NAME, this.listOp.getSerializeUrl());
			result.setNext(next);
			result.setSuccess(true, new ResultMessage(this.MESSAGE_BUNDLENAME, "basket.update.ok", new Integer(rcnt)));
		} else {
			result.setSuccess(false, new ResultMessage(this.MESSAGE_BUNDLENAME, "basket.update.fail", new Integer(rcnt)));
		}
    }   

    /**
     * 
     * @param _req
     * @param _res
     */
    public void cmdDelete(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {

		int rcnt = 0;
		Map param = _req.getKeyValueMap();
        param.put("usn", super.sessionBean.getUsn());
        
		try {
			oDb.setAutoCommit(false);
   			rcnt = BasketDB.deleteOne(super.oDb, param);
			oDb.commit();
		} catch (Exception e) {
			oDb.rollback();
			logger.error(e.getMessage());
			rcnt = 0;
		} finally {
			oDb.setAutoCommit(true);
		}
		if (rcnt>0) {
			UrlAddress next = new UrlAddress(super.controlAction);
			next.addParam(MafConstant.LIST_OP_NAME, this.listOp.getSerializeUrl());
			result.setNext(next);
			result.setSuccess(true, new ResultMessage(this.MESSAGE_BUNDLENAME, "basket.delete.ok", new Integer(rcnt)));
		} else {
			result.setSuccess(false, new ResultMessage(this.MESSAGE_BUNDLENAME, "basket.delete.fail", new Integer(rcnt)));
		}
    }   
}