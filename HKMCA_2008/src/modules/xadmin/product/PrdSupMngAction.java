package modules.xadmin.product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import maf.MafConstant;
import maf.beans.NavigatorInfo;
import maf.beans.NavigatorOrderInfo;
import maf.context.support.LocaleUtil;
import maf.exception.MafException;
import maf.logger.Logging;
import maf.mdb.sqlhelper.SqlWhereBuilder;
import maf.mdb.sqlhelper.Where;
import maf.web.http.MyHttpServletRequest;
import maf.web.http.UrlAddress;
import maf.web.mvc.action.MafAction;
import maf.web.mvc.beans.ResultMessage;
import maf.web.util.SerializeHashtable;
import miraenet.app.resource.ResourceManagerDB;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class PrdSupMngAction extends MafAction {
    final private Log logger = LogFactory.getLog(this.getClass());
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
     * 목록 조회
     *
     * @param _req
     * @param _res
     */
    public void cmdList(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		final String[] searchFields = {"s_prd_nm"};
		
		NavigatorInfo navigator = new NavigatorInfo(_req, this.listOp, new NavigatorOrderInfo("A:prd_nm"));
	
		Map param = this.listOp.getMergedParam(_req, searchFields);
		
		SqlWhereBuilder wb = oDb.getWhereBuilder();
		wb.addCond(Where.like("x.prd_nm", ":s_prd_nm"));

		PrdMngDB.getList(super.oDb, navigator, param, wb.getWhereString(param), false);
		result.setAttribute("navigator", navigator);
		result.setForward("list");
    }

    /**
     * 수정
     *
     * @param _req
     * @param _res
     */
    public void cmdEdit(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {

        Map param = _req.getKeyValueMap();

		result.setAttribute("item", PrdMngDB.getOne(super.oDb, param));
		result.setAttribute("list", PrdSupDB.getList(super.oDb, param));

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
        int rcnt = 0;

        try {
        	oDb.setAutoCommit(false);
            //insert to DB
            rcnt = PrdSupDB.insert(super.oDb, param);
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
        param.put("upt_id", super.sessionBean.getUsn());
        param.put("lang", super.locale.getLanguage());
        boolean chk = false;

        int rcnt = 0;

        try {
            //update to DB
            rcnt= PrdSupDB.updateOne(super.oDb, param);
            chk = true;
        } catch (Throwable e) {
            Logging.trace(e);
        }

        if( chk) {
            UrlAddress next = null;
            if("set".equals(_req.getP("from"))) {
                next = new UrlAddress ("setmanager.do");
                next.addParam("cmd", "view");
                next.addParam("setid", _req.getP("setid"));
            } else {
                next = new UrlAddress (super.controlAction);
                next.addParam("cmd", "edit");
                next.addParam("queid", param.get("queid"));
            }
            
            next.addParam(MafConstant.LIST_OP_NAME, this.listOp.getSerializeUrl());
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
        String queid = _req.getP("queid");
        int rcnt = 0;
        boolean chk = false;
        Map param = new HashMap();
        param.put("queid", queid);
        
        try {
            //update to DB
            oDb.setAutoCommit(false);
            rcnt= PrdSupDB.deleteOne(super.oDb, param);
            oDb.commit();
            chk = true;
        } catch (Throwable e) {
            oDb.rollback();
            logger.error(e.getMessage());
            rcnt = 0;
        }
        
        if(chk) {
            UrlAddress next = new UrlAddress (super.controlAction);
            next.addParam(MafConstant.LIST_OP_NAME, this.listOp.getSerializeUrl());
            result.setNext(next);
            result.setSuccess(true, new ResultMessage(this.MESSAGE_BUNDLENAME, "delete.ok", new Integer(rcnt)));
        } else {
            result.setSuccess(false,new ResultMessage(this.MESSAGE_BUNDLENAME, "delete.fail", new Integer(rcnt)));
        }
    }
    
    /**
     * 수정
     *
     * @param _req
     * @param _res
     */
    public void cmdEditLang(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
        Map param = _req.getKeyValueMap();

        String curLang = _req.getP("lang", LocaleUtil.getLocaleString(_req));
        result.setAttribute("curLang", curLang);
        List langList = ResourceManagerDB.getLangList(super.oDb);
        //특정 정보 가져오기
        result.setAttribute("from", _req.getP("from"));
        result.setAttribute("setid", _req.getP("setid"));
        result.setAttribute("langList", langList);
        result.setForward("editLang");
    }
}
