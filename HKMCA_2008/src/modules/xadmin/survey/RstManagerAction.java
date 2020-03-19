package modules.xadmin.survey;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;

import maf.MafConstant;
import maf.beans.NavigatorInfo;
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

public class RstManagerAction extends MafAction {
    final private Log logger = LogFactory.getLog(this.getClass());
    SerializeHashtable listOp = null;
    private final String MESSAGE_BUNDLENAME = "common.message";

    public void preProcess(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
        this.listOp = new SerializeHashtable(_req.getParameter(MafConstant.LIST_OP_NAME));
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
        final String[] searchFields = { "s_exmid", "s_exmowner", "s_exmtitle", "s_exmdesc" };
        // Order default 값 설정 시
        //NavigatorInfo navigator = new NavigatorInfo(_req, this.listOp, new NavigatorOrderInfo("A:###"));
        NavigatorInfo navigator = new NavigatorInfo(_req, this.listOp);
        Map param = this.listOp.getMergedParam(_req, searchFields);
        SqlWhereBuilder wb = oDb.getWhereBuilder();
        wb.addCond(Where.like("exmid", ":s_exmid"));
        wb.addCond(Where.like("exmowner", ":s_exmowner"));
        wb.addCond(Where.like("exmtitle", ":s_exmtitle"));
        wb.addCond(Where.like("exmdesc", ":s_exmdesc"));
        SurveyManagerDB.getList(super.oDb, navigator, param, wb.getWhereString(param), false);
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
        Map param = new HashMap();
        param.put("exmid", _req.getP("exmid"));
        result.setAttribute("item", SurveyManagerDB.getOne(super.oDb, param));
        result.setForward("view");
    }

    /**
     * edit form 
     * 
     * @param _req
     * @param _res
     */
    public void cmdEdit(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
        Map param = new HashMap();
        param.put("exmid", _req.getP("exmid"));
        result.setAttribute("item", SurveyManagerDB.getOne(super.oDb, param));
        result.setForward("edit");
    }

    /**
     * 하나의 record를 수정 한다.
     * 
     * @param _req
     * @param _res
     */
    public void cmdUpdate(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
        Map param = _req.getKeyValueMap();

        param.put("exmid", _req.getP("exmid"));
        // 최종 수정자
        param.put("update_usn", super.sessionBean.getUsn());
        int rcnt = 0;
        try {
            oDb.setAutoCommit(false);
            rcnt = SurveyManagerDB.mergeOne(super.oDb, param);
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
            result.setSuccess(true, new ResultMessage(this.MESSAGE_BUNDLENAME, "message.update.ok", new Integer(rcnt)));
        } else {
            result.setSuccess(false, new ResultMessage(this.MESSAGE_BUNDLENAME, "message.update.fail", new Integer(rcnt)));
        }
    }

    /**
     * insert
     * 
     * @param _req
     * @param _res
     */
    public void cmdInsert(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
        Map param = _req.getKeyValueMap();

        int rcnt = 0;
        try {
            oDb.setAutoCommit(false);
            rcnt = SurveyManagerDB.mergeOne(super.oDb, param);
            // 최초 등록자 
            param.put("reg_usn", super.sessionBean.getUsn());
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
            result.setSuccess(true, new ResultMessage(this.MESSAGE_BUNDLENAME, "message.insert.ok", new Integer(rcnt)));
        } else {
            result.setSuccess(false, new ResultMessage(this.MESSAGE_BUNDLENAME, "message.insert.fail", new Integer(rcnt)));
        }
    }

    /**
     * insert form
     * 
     * @param _req
     * @param _res
     */
    public void cmdAdd(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
        result.setForward("add");
    }
    
}