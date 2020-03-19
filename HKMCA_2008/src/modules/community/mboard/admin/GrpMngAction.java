package modules.community.mboard.admin;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import maf.MafConstant;
import maf.exception.MafException;
import maf.logger.Logging;
import maf.web.http.MyHttpServletRequest;
import maf.web.http.UrlAddress;
import maf.web.mvc.action.MafAction;
import maf.web.mvc.beans.ResultMessage;
import maf.web.util.SerializeHashtable;
import modules.xadmin.survey.BankManagerDB;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class GrpMngAction extends MafAction {
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
     * 수정
     *
     * @param _req
     * @param _res
     */
    public void cmdDefault(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {

        Map param = _req.getKeyValueMap();

        result.setAttribute("list", MbbsGrpDB.getList(super.oDb, param));
        result.setForward("default");
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
			param.put("site", "www");
            rcnt = MbbsGrpDB.insert(super.oDb, param);
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

	public void cmdUpdate(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {		
		
		Map param = _req.getKeyValueMap();
		int rcnt = 0;
		try {
			oDb.setAutoCommit(false);
			String[] i_grp = _req.getParameterValues("i_grp");
			String grp_desc = null;
			String seq = null;
			String del = null;

			rcnt = MbbsGrpDB.delete(super.oDb, param);
			
			if(i_grp != null) {
				for(int i = 0; i < i_grp.length; i ++ ) {
					grp_desc = _req.getP("desc_" + i_grp[i]);
					seq = _req.getP("seq_" + i_grp[i]);
					del = _req.getP("del_" + i_grp[i]);
					param.put("grp", i_grp[i]);
					if(!"Y".equals(del)) {
						param.put("grp_desc", grp_desc);
						param.put("seq", seq);
						param.put("site", "www");
						rcnt = MbbsGrpDB.insert(oDb, param);
					}
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
			UrlAddress next = new UrlAddress (super.controlAction);
			result.setSuccess(true, new ResultMessage(this.MESSAGE_BUNDLENAME, "update.ok", new Integer(rcnt)));
			result.setNext(next);
		} else {
			result.setSuccess(false, new ResultMessage(this.MESSAGE_BUNDLENAME, "update.fail"));
		}
		
	}

}