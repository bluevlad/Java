package modules.xadmin.etest;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import maf.MafConstant;
import maf.MafUtil;
import maf.beans.NavigatorInfo;
import maf.exception.MafException;
import maf.mdb.sqlhelper.SqlWhereBuilder;
import maf.mdb.sqlhelper.Where;
import maf.web.http.MyHttpServletRequest;
import maf.web.http.UrlAddress;
import maf.web.multipart.UploadedFile;
import maf.web.mvc.action.MafAction;
import maf.web.mvc.beans.ResultMessage;
import maf.web.util.SerializeHashtable;
import miraenet.AppConfig;
import modules.etest.support.Etest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * 문제 등록 관리
 * 
 * @author bluevlad
 *
 */
public class QuestionAction extends MafAction {
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
     * 목록 조회
     *
     * @param _req
     * @param _res
     */
    public void cmdList(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
        final String[] searchFields = {"s_quetitle", "setid"};
        
        NavigatorInfo navigator = new NavigatorInfo(_req, this.listOp);
        Map param = this.listOp.getMergedParam(_req, searchFields);
		String destination = null;
        
        SqlWhereBuilder wb = oDb.getWhereBuilder();
        wb.addCond(Where.like("quetitle",":s_quetitle"));
        
        QuestionDB.getList(super.oDb, navigator, param, wb.getWhereString(param));
        result.setAttribute("navigator", navigator);
        result.setForward("list");
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
		String[] v_queids = _req.getParameterValues("v_queids");
		param.put("reg_id",super.sessionBean.getUsn());

        try {
        	oDb.setAutoCommit(false);

			if(v_queids != null) {
				for(int i = 0; i < v_queids.length; i ++ ) {
					param.put("queid", v_queids[i]);
					param.put("qseq", i+1);
					rcnt = QuestionDB.insertOne(super.oDb, param);
				}
			}

        	oDb.commit();
        } catch (Exception e) {
        	logger.debug(e);
        	oDb.rollback();
        }

        if(rcnt>0) {
            UrlAddress next = new UrlAddress (super.controlAction);
            next.addParam(MafConstant.LIST_OP_NAME, this.listOp.getSerializeUrl());
            result.setNext(next);
            result.setSuccess(true, new ResultMessage(this.MESSAGE_BUNDLENAME, "insert.ok", new Integer(rcnt)));
        } else {
            result.setSuccess(false, new ResultMessage(this.MESSAGE_BUNDLENAME, "insert.fail", new Integer(rcnt)));
        }
    }

}