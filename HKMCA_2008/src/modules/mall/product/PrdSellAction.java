/*
 * Created on 2006. 11. 03
 *
 * Copyright (c) 2004 UBQ All rights reserved.
 */
package modules.mall.product;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import maf.MafConstant;
import maf.beans.NavigatorInfo;
import maf.beans.NavigatorOrderInfo;
import maf.exception.MafException;
import maf.mdb.sqlhelper.SqlWhereBuilder;
import maf.mdb.sqlhelper.Where;
import maf.web.http.MyHttpServletRequest;
import maf.web.mvc.action.MafAction;
import maf.web.util.SerializeHashtable;
import modules.xadmin.product.PrdSellDB;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class PrdSellAction extends MafAction {
    private Log logger = LogFactory.getLog(this.getClass());
	SerializeHashtable listOp = null;
    private final String MESSAGE_BUNDLENAME = "common.message";

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
		final String[] searchFields = {"s_prd_nm", "s_sell_nm", "s_l_price", "s_h_price"};
		
		NavigatorInfo navigator = new NavigatorInfo(_req, this.listOp, new NavigatorOrderInfo("A:prd_nm"));
	
		Map param = this.listOp.getMergedParam(_req, searchFields);
		
		SqlWhereBuilder wb = oDb.getWhereBuilder();
		wb.addCond(Where.like("x.prd_nm", ":s_prd_nm"));
		wb.addCond(Where.like("x.sell_nm", ":s_sell_nm"));
		wb.addCond(Where.between("x.psell_price", ":s_l_price", ":s_h_price"));

		PrdSellDB.getList(super.oDb, navigator, param, wb.getWhereString(param), false);
		result.setAttribute("navigator", navigator);
		result.setForward("list");
    }

    /**
     * 
     * @param _req
     * @param _res
     */
    public void cmdView(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {

        Map param = _req.getKeyValueMap();
		result.setAttribute("item", PrdSellDB.getOne(super.oDb, param));
		result.setForward("view");
    }   

}