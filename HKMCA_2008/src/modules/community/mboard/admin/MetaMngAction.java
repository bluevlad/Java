package modules.community.mboard.admin;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import maf.MafConstant;
import maf.beans.NavigatorInfo;
import maf.exception.MafException;
import maf.logger.Logging;
import maf.mdb.sqlhelper.SqlWhereBuilder;
import maf.mdb.sqlhelper.Where;
import maf.web.http.MyHttpServletRequest;
import maf.web.http.UrlAddress;
import maf.web.mvc.action.MafAction;
import maf.web.mvc.beans.ResultMessage;
import maf.web.util.SerializeHashtable;
import modules.xadmin.survey.BankManagerDB;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MetaMngAction extends MafAction {
    final private Log logger = LogFactory.getLog(this.getClass());
    SerializeHashtable listOp = null;
	private final String MESSAGE_BUNDLENAME = "common.message.board";
	protected final String MBOARD_PATH = "/mboard";

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
        final String[] searchFields = {"s_subject", "s_fl_board_type"};
        
        NavigatorInfo navigator = new NavigatorInfo(_req, this.listOp);
        Map param = this.listOp.getMergedParam(_req, searchFields);
        
        SqlWhereBuilder wb = oDb.getWhereBuilder();
        wb.addCond(Where.like("x.subject",":s_subject"));
        wb.addCond(Where.eq("x.fl_board_type",":s_fl_board_type"));

        MbbsMetaDB.getList(super.oDb, navigator, param, wb.getWhereString(param));

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

        Map param = _req.getKeyValueMap();

        result.setAttribute("groups", MbbsGrpDB.getList(super.oDb, param));
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

		result.setAttribute("groups", MbbsGrpDB.getList(super.oDb, param));
        result.setAttribute("item", MbbsMetaDB.getOne(super.oDb, param));
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
           
            //insert to DB
            rcnt = MbbsMetaDB.mergeOne(super.oDb, param);
        } catch (Throwable e) {
            Logging.trace(e);
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
        int rcnt = 0;

        try {
            //update to DB
            rcnt= MbbsMetaDB.mergeOne(super.oDb, param);

        } catch (Throwable e) {
            Logging.trace(e);
        }

        if(rcnt>0) {
            UrlAddress next = new UrlAddress (super.controlAction);
            result.setNext(next);
            result.setSuccess(true, new ResultMessage(this.MESSAGE_BUNDLENAME, "update.ok", new Integer(rcnt)));
        } else {
            result.setSuccess(false, new ResultMessage(this.MESSAGE_BUNDLENAME, "update.fail", new Integer(rcnt)));
        }
    }

}