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
import maf.beans.NavigatorInfo;
import maf.beans.NavigatorOrderInfo;
import maf.exception.MafException;
import maf.mdb.sqlhelper.SqlWhereBuilder;
import maf.mdb.sqlhelper.Where;
import maf.web.http.MyHttpServletRequest;
import maf.web.http.UrlAddress;
import maf.web.mvc.action.MafAction;
import maf.web.mvc.beans.ResultMessage;
import maf.web.util.SerializeHashtable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class OrderAction extends MafAction {
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
    public void cmdList(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		final String[] searchFields = {"s_ord_sta", "s_start_dt", "s_end_dt"};
		
		NavigatorInfo navigator = new NavigatorInfo(_req, this.listOp, new NavigatorOrderInfo("D:ord_cd"));
	
		Map param = this.listOp.getMergedParam(_req, searchFields);
        param.put("usn", super.sessionBean.getUsn());
		
		SqlWhereBuilder wb = oDb.getWhereBuilder();
		wb.addCond(Where.like("x.ord_sta", ":s_ord_sta"));
		wb.addCond(Where.between("x.ord_dt", ":s_start_dt", ":s_end_dt"));

		OrdMstDB.getList(super.oDb, navigator, param, wb.getWhereString(param), false);
		result.setAttribute("navigator", navigator);
		result.setForward("list");
    }

    /**
     * 하나의 record를 읽어와서 보여준다.
     * 
     * @param _req
     * @param _res
     */
    public void cmdView(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
        Map param = _req.getKeyValueMap();

        result.setAttribute("item", OrdMstDB.getOne(super.oDb, param));
        result.setAttribute("list", OrdDetDB.getList(super.oDb, param));
        result.setForward("view");
    }

    /**
     * 하나의 record를 읽어와서 보여준다.
     * 
     * @param _req
     * @param _res
     */
    public void cmdOrdView(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
        Map param = _req.getKeyValueMap();

        param.put("usn", super.sessionBean.getUsn());
		result.setAttribute("list", BasketDB.getList(super.oDb, param));
		
        result.setForward("ord_view");
    }

    /**
     * 
     * @param _req
     * @param _res
     */
    public void cmdOrder(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {

		int rcnt = 0;
		Map param = _req.getKeyValueMap();
        param.put("usn", super.sessionBean.getUsn());
        
		try {
			oDb.setAutoCommit(false);
	        String ord_cd = OrdMstDB.getOrdCd(oDb);
	        param.put("ord_cd", ord_cd);
	        param.put("cpn_use", "N");
	        param.put("coupon_no", "");
	        param.put("ora_sta", "O");
	        param.put("total_pay", 0+"");
	        param.put("discount", 0+"");
			
			rcnt = OrdMstDB.insert(super.oDb, param);

			//
	        param.put("ord_det_sta", "O");
	        param.put("snd_dt", "");
            String[] v_psell_cd = _req.getParameterValues("v_psell_cd");
            float psell_price = 0;
            float pay = 0;
            float discount = 0;
            
            if(v_psell_cd != null) {
                for(int i = 0; i < v_psell_cd.length; i ++ ) {
        	        param.put("ord_det_no", i+1);
                    param.put("psell_cd", v_psell_cd[i]);
                    param.put("qty", _req.getP("cnt_"+v_psell_cd[i]));
                    
                    Map prd = OrdMstDB.getPrdOne(oDb, param);
                    param.put("psell_price", prd.get("psell_price")); //주문한 제품가격과 실제 제품가격을 동일하게 해준다.
                    
                    param.put("pay", _req.getP("pay_"+v_psell_cd[i]));
                    param.put("cpn_use", _req.getP("cpn_use_"+v_psell_cd[i]));
                    param.put("coupon_no", _req.getP("cpn_no_"+v_psell_cd[i]));
        			rcnt = OrdDetDB.insertOne(super.oDb, param);
        			psell_price = psell_price + (MafUtil.parseInt(param.get("qty").toString())*MafUtil.parseInt(param.get("psell_price").toString()));
                	pay = pay + (MafUtil.parseInt(param.get("qty").toString())*MafUtil.parseInt(param.get("pay").toString()));
                	discount = discount + (psell_price - pay);

                    rcnt = OrdMstDB.updateOne(super.oDb, param);

                }
            }

            param.put("total_pay", pay+"");
            param.put("discount", discount+"");
            rcnt = OrdMstDB.updateOne(super.oDb, param);
            
            rcnt = BasketDB.delete(oDb, param);
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
			result.setSuccess(true, new ResultMessage(this.MESSAGE_BUNDLENAME, "order.ok", new Integer(rcnt)));
		} else {
			result.setSuccess(false, new ResultMessage(this.MESSAGE_BUNDLENAME, "order.fail", new Integer(rcnt)));
		}
    }   

}