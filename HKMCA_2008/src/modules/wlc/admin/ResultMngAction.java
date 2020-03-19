/*
 * Created on 2006. 03. 03.
 *
 */
package modules.wlc.admin;
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
import modules.wlc.admin.course.LectureMngDB;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/*
*/
public class ResultMngAction extends MafAction {
    final private Log  logger = LogFactory.getLog(this.getClass());
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
	 * 
	 * @param _req
	 * @param _res
	 */
	public void cmdList(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		
		final String[] searchFields = {"org_code", "org_name", "os_region", "os_distribute", "os_dealer", "s_section", "s_field", "s_lecstat", "s_lec_sdate","s_lec_edate", "s_sjt_cd", "s_lecname"};
		
		NavigatorInfo navigator = new NavigatorInfo(_req, this.listOp);
		Map param = this.listOp.getMergedParam(_req, searchFields);
		param.put("usn", super.sessionBean.getUsn());

        SqlWhereBuilder wb = oDb.getWhereBuilder();
        wb.addCond(Where.eq("x.section", ":s_section"));    
        wb.addCond(Where.eq("x.field", ":s_field"));
        wb.addCond(Where.eq("x.cert_lvl", ":s_cert_lvl"));
        wb.addCond(Where.eq("x.lecstat", ":s_lecstat"));
        wb.addCond(Where.between("x.lec_edate", ":s_lec_sdate", ":s_lec_edate"));
        wb.addCond(Where.eq("x.sjt_cd", ":s_sjt_cd"));
        wb.addCond(Where.like("x.lecname", ":s_lecname"));
        wb.addCond(Where.like("x.orgn_cd", ":org_code"));

        result.setAttribute("org_code", param.get("org_code"));
        /* 조직 검색 Response Parameter End */

		LectureMngDB.getList(super.oDb, navigator, param, wb.getWhereString(param), false);
		result.setAttribute("crs_sub", LectureMngDB.getCrsSub(super.oDb, param)); 
		result.setAttribute("navigator", navigator);
		result.setForward("list");
	}
	
	/**
	 * 
	 * @param _req
	 * @param _res
	 */
	public void cmdView(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {

		Map param = new HashMap();
		param.put("leccode", _req.getP("leccode"));
				
		result.setAttribute("item", ResultMngDB.getOne(super.oDb, param));
		result.setAttribute("list", ResultMngDB.getStdList(super.oDb, param));
		result.setForward("view");
	}
	
	/**
	 * 
	 * @param _req
	 * @param _res
	 */
	public void cmdClose(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {

		Map param = _req.getKeyValueMap();
		boolean chk = false;
		
		String[] vleccode = _req.getParameterValues("v_leccode");
		param.put("update_id",super.sessionBean.getUsn());
		param.put("lecstat","E");
		int rcnt= 0;
		try {
			oDb.setAutoCommit(false);
            if(null != vleccode) {
            	for(int i =0; i < vleccode.length; i++) {
            		param.put("leccode", vleccode[i]);
        			rcnt += ResultMngDB.closeOne(super.oDb, param);
           		}
           	}
			oDb.commit();
			chk = true;
		} catch ( Exception e) {
			oDb.rollback();
			logger.error(e.getMessage());
			rcnt = 0;
		} finally {
			oDb.setAutoCommit(true);
		}

		if(chk ) {
			UrlAddress next = new UrlAddress (super.controlAction);
			next.addParam(MafConstant.LIST_OP_NAME, this.listOp.getSerializeUrl());
			result.setNext(next);
			result.setSuccess(true, new ResultMessage(this.MESSAGE_BUNDLENAME, "message.update.ok", new Integer(rcnt)));
		} else {
			result.setSuccess(false,new ResultMessage(this.MESSAGE_BUNDLENAME, "message.update.fail", new Integer(rcnt)));
		}
	}

	/**
	 * 
	 * @param _req
	 * @param _res
	 */
	public void cmdComplete(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {

		Map param = _req.getKeyValueMap();
		boolean chk = false;
		
		String[] vuserid = _req.getParameterValues("v_userid");
		param.put("update_id",super.sessionBean.getUsn());
		param.put("req_stat","LE");
		int rcnt= 0;
		try {
			oDb.setAutoCommit(false);
            if(null != vuserid) {
            	for(int i =0; i < vuserid.length; i++) {
            		param.put("userid", vuserid[i]);
        			rcnt += ResultMngDB.updateLecReq(super.oDb, param);
           		}
           	}
			oDb.commit();
			chk = true;
		} catch ( Exception e) {
			oDb.rollback();
			logger.error(e.getMessage());
			rcnt = 0;
		} finally {
			oDb.setAutoCommit(true);
		}

		if(chk ) {
			UrlAddress next = new UrlAddress (super.controlAction);
			next.addParam(MafConstant.LIST_OP_NAME, this.listOp.getSerializeUrl());
			result.setNext(next);
			result.setSuccess(true, new ResultMessage(this.MESSAGE_BUNDLENAME, "message.update.ok", new Integer(rcnt)));
		} else {
			result.setSuccess(false,new ResultMessage(this.MESSAGE_BUNDLENAME, "message.update.fail", new Integer(rcnt)));
		}
	}
} 