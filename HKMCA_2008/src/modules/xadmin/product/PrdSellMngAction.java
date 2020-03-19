package modules.xadmin.product;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;


import maf.MafConstant;
import maf.beans.NavigatorInfo;
import maf.beans.NavigatorOrderInfo;
import maf.exception.MafException;
import maf.logger.Logging;
import maf.mdb.sqlhelper.SqlWhereBuilder;
import maf.mdb.sqlhelper.Where;
import maf.web.http.MyHttpServletRequest;
import maf.web.http.UrlAddress;
import maf.web.mvc.action.MafAction;
import maf.web.mvc.beans.ResultMessage;
import maf.web.util.SerializeHashtable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class PrdSellMngAction extends MafAction {
    final private Log logger = LogFactory.getLog(this.getClass());
    SerializeHashtable listOp = null;
    private final String MESSAGE_BUNDLENAME = "common.message";

    public void preProcess(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
        this.listOp = new SerializeHashtable( _req.getParameter(MafConstant.LIST_OP_NAME) );
    }

    public void postProcess(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
        result.setAttribute(MafConstant.LIST_OP_NAME, listOp);
    }

    /**
     * 목록 조회
     *
     * @param _req
     * @param _res
     */
    public void cmdList(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		final String[] searchFields = {"s_prd_nm", "s_sell_nm"};
		
		NavigatorInfo navigator = new NavigatorInfo(_req, this.listOp, new NavigatorOrderInfo("A:prd_nm"));
	
		Map param = this.listOp.getMergedParam(_req, searchFields);
		
		SqlWhereBuilder wb = oDb.getWhereBuilder();
		wb.addCond(Where.like("x.psell_nm", ":s_prd_nm"));
		wb.addCond(Where.like("x.sell_nm", ":s_sell_nm"));

		PrdSellDB.getList(super.oDb, navigator, param, wb.getWhereString(param), false);
		result.setAttribute("navigator", navigator);
		result.setForward("list");
    }

    /**
     * 수정
     *
     * @param _req
     * @param _res
     */
    public void cmdAdd(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {

		result.setForward("add");
    }

    /**
     * 수정
     *
     * @param _req
     * @param _res
     */
    public void cmdEdit(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {

        Map param = _req.getKeyValueMap();

		result.setAttribute("item", PrdSellDB.getOne(super.oDb, param));

		result.setForward("edit");
    }

    /**
     * 등록
     *
     * @param _req
     * @param _res
     */
    public void cmdInsert(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {

		Map param = _req.getKeyValueMap();
        param.put("psell_nm", param.get("prd_nm"));

        int rcnt = 0;

        try {
        	oDb.setAutoCommit(false);
            //insert to DB
            rcnt = PrdSellDB.InsertOne(super.oDb, param);
        	oDb.commit();
        } catch (Exception e) {
            Logging.trace(e);
        	oDb.rollback();
            rcnt = 0;
        }

        if( rcnt > 0 ) {
            UrlAddress next = new UrlAddress (super.controlAction);
            next.addParam(MafConstant.LIST_OP_NAME, this.listOp.getSerializeUrl());
            result.setNext(next);
            result.setSuccess(true, new ResultMessage(this.MESSAGE_BUNDLENAME, "insert.ok", new Integer(rcnt)));
        } else {
            result.setSuccess(false, new ResultMessage(this.MESSAGE_BUNDLENAME, "insert.fail", new Integer(rcnt)));
        }
    }

    /**
     * 수정 등록
     *
     * @param _req
     * @param _res
     */
    public void cmdUpdate(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {

		Map param = _req.getKeyValueMap();
        param.put("psell_nm", param.get("prd_nm"));
        boolean chk = false;

        int rcnt = 0;

        try {
            //update to DB
            rcnt = PrdSellDB.updateOne(super.oDb, param);
            chk = true;
        } catch (Throwable e) {
            Logging.trace(e);
        }

        if( chk) {
            UrlAddress next = null;
            next = new UrlAddress (super.controlAction);
            next.addParam("cmd", "edit");
            next.addParam("psell_cd", param.get("psell_cd"));
            result.setNext(next);
            result.setSuccess(true, new ResultMessage(this.MESSAGE_BUNDLENAME, "update.ok", new Integer(rcnt)));
        } else {
            result.setSuccess(false, new ResultMessage(this.MESSAGE_BUNDLENAME, "update.fail", new Integer(rcnt)));
        }
    }

    /**
     * 삭제
     *
     * @param _req
     * @param _res
     */
    public void cmdDelete(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
        int rcnt = 0;
        Map param = new HashMap();
        
        try {
            //update to DB
            oDb.setAutoCommit(false);
            rcnt = PrdSellDB.deleteOne(super.oDb, param);
            oDb.commit();
        } catch (Throwable e) {
            oDb.rollback();
            logger.error(e.getMessage());
            rcnt = 0;
        }
        
        if(rcnt>0) {
            UrlAddress next = new UrlAddress (super.controlAction);
            next.addParam(MafConstant.LIST_OP_NAME, this.listOp.getSerializeUrl());
            result.setNext(next);
            result.setSuccess(true, new ResultMessage(this.MESSAGE_BUNDLENAME, "delete.ok", new Integer(rcnt)));
        } else {
            result.setSuccess(false,new ResultMessage(this.MESSAGE_BUNDLENAME, "delete.fail", new Integer(rcnt)));
        }
    }
}