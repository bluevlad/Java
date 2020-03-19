/*
 * Created on 2006. 03. 03.
 *
 */
package modules.wlc.admin.course;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import maf.MafConstant;
import maf.beans.NavigatorInfo;
import maf.beans.NavigatorOrderInfo;
import maf.exception.MafException;
import maf.mdb.sqlhelper.SqlWhereBuilder;
import maf.mdb.sqlhelper.Where;
import maf.web.http.MyHttpServletRequest;
import maf.web.http.UrlAddress;
import maf.web.mvc.action.MafAction;
import maf.web.mvc.beans.ResultMessage;
import maf.web.mvc.view.ViewerSupport;
import maf.web.util.SerializeHashtable;
import modules.wlc.admin.etest.WlcExmManagerDB;
import modules.wlc.common.config.WlcConfigSupport;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/*
 * 강좌 관리 
 * 
*/
public class LectureMngAction extends MafAction {
    final private Log  logger = LogFactory.getLog(this.getClass());
    SerializeHashtable listOp = null;
    private final String MESSAGE_BUNDLENAME = "common.message.lecture";
    public static final  String ATTACH_FILE_PATH=WlcConfigSupport.getProperty("lecture_file","/pds/lecture");
    
    public void preProcess(MyHttpServletRequest _req, HttpServletResponse _res) 
	throws MafException {
		this.listOp = new SerializeHashtable( _req.getParameter(MafConstant.LIST_OP_NAME) );
	}
	public void postProcess(MyHttpServletRequest _req, HttpServletResponse _res) 
	throws MafException {
		result.setAttribute(MafConstant.LIST_OP_NAME, listOp);
	}	
	
	/**
	 * 목록 보여주기
	 * @param _req
	 * @param _res
	 */
	public void cmdList(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		
		final String[] searchFields = {"s_lec_stat", "s_lec_year", "os_crs", "os_sjt", "s_lec_nm"};
		
		NavigatorInfo navigator = new NavigatorInfo(_req, this.listOp, new NavigatorOrderInfo("D:lec_year"));
		Map param = this.listOp.getMergedParam(_req, searchFields);
		String destination = null;
		
		SqlWhereBuilder wb = oDb.getWhereBuilder();
		wb.addCond(Where.eq("x.lec_stat", ":s_lec_stat"));
		wb.addCond(Where.eq("x.lec_year", ":s_lec_year"));
		wb.addCond(Where.eq("x.crs_cd", ":os_crs"));
		wb.addCond(Where.eq("x.sjt_cd", ":os_sjt"));
		wb.addCond(Where.like("x.lec_nm", ":s_lec_nm"));

        if (!"".equals(param.get("os_sjt"))) {
        	destination = "os_sjt";
        } else if  (!"".equals(param.get("os_crs"))) {
        	destination = "os_crs";
        }
		LectureMngDB.getList(super.oDb, navigator, param, wb.getWhereString(param), false);
		result.setAttribute("navigator", navigator);
		result.setAttribute("destination", destination);
		result.setForward("list");
	}
	
	/**
	 * insert form
	 * 
	 * @param _req
	 * @param _res
	 */
	public void cmdAdd(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {

		Map param = new HashMap();
		param.put("lec_cd", "");
		result.setAttribute("subject", LectureMngDB.getCrsSub(super.oDb, param));
        result.setAttribute("target", LectureMngDB.getLecTarget(super.oDb, param));
		result.setForward("add");
	}
	
	/**
	 * edit form 
	 * 
	 * @param _req
	 * @param _res
	 */
	public void cmdEdit(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		
		Map param = new HashMap();
		param.put("lec_cd", _req.getP("lec_cd"));
		Map item = LectureMngDB.getOne(super.oDb, param);
		param.put("sjt_cd", item.get("sjt_cd"));
		
		result.setAttribute("item", item);
        result.setAttribute("schedule_list", LectureMngDB.getSchedule(super.oDb, param));
        result.setAttribute("target", LectureMngDB.getLecTarget(super.oDb, param));
		result.setForward("edit");
	}
	
	/**
	 * copy form
	 * 
	 * @param _req
	 * @param _res
	 */
	public void cmdCopy(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
	
		final String[] searchFields = {"sjt_cd"};
		
		NavigatorInfo navigator = new NavigatorInfo(_req, this.listOp);
		Map param = this.listOp.getMergedParam(_req, searchFields);
		String destination = null;
		
		SqlWhereBuilder wb = oDb.getWhereBuilder();
		param.put("lec_cd", _req.getP("lec_cd"));

		wb.addCond(Where.eq("x.sjt_cd", ":sjt_cd"));

		LectureMngDB.getOne(super.oDb, param);
		LectureMngDB.getList(super.oDb, navigator, param, wb.getWhereString(param), false);
		result.setAttribute("navigator", navigator);
		
		result.setForward("copy");
	}
	
	/**
	 * 
	 * @param _req
	 * @param _res
	 */
	public void cmdUpdate(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {

		Map param = _req.getKeyValueMap();

		param.put("usn",super.sessionBean.getUsn());

		String m_lec_target[] = _req.getParameterValues("lec_target");
		String lec_target = "";
		if(m_lec_target != null) {
			for(int i=0;i < m_lec_target.length; i++) {
				lec_target = lec_target + m_lec_target[i];
			}
        	param.put("lec_target", lec_target);
		}else {
        	param.put("lec_target", "");
		}
		
		int rcnt= 0;
		try {
			oDb.setAutoCommit(false);
			
			rcnt = LectureMngDB.mergeOne(super.oDb, param);
            rcnt = LectureMngDB.updateClassSchedule(super.oDb, param);
            rcnt = LectureMngDB.updatePrgBasis(super.oDb, param);
            
			oDb.commit();
		} catch ( Exception e) {
			oDb.rollback();
			maf.logger.Logging.trace(e);
			rcnt = 0;
		} finally {
			oDb.setAutoCommit(true);
		}

		if(rcnt>0) {
			UrlAddress next = new UrlAddress (super.controlAction);
            next.addParam("cmd", "edit");
            next.addParam("lec_cd", param.get("lec_cd"));
            next.addParam(MafConstant.LIST_OP_NAME, this.listOp.getSerializeUrl());
			result.setNext(next);
			result.setSuccess(true, new ResultMessage(this.MESSAGE_BUNDLENAME, "update.ok", new Integer(rcnt)));
		} else {
			result.setSuccess(false,new ResultMessage(this.MESSAGE_BUNDLENAME, "update.fail", new Integer(rcnt)));
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
		param.put("usn",super.sessionBean.getUsn());
		param.put("lec_cd", _req.getP("sjt_cd") + "-" + _req.getP("lec_year") + "-" + _req.getP("lec_num"));
		param.put("sjt_cd", _req.getP("sjt_cd"));
		
		int rcnt = 0;
		String m_lec_target[] = _req.getParameterValues("lec_target");
		String lec_target = "";
		if(m_lec_target != null) {
			for(int i=0;i < m_lec_target.length; i++) {
				lec_target = lec_target + m_lec_target[i];
			}
        	param.put("lec_target", lec_target);
		}else {
        	param.put("lec_target", "");
		}
		
		try {
			
			oDb.setAutoCommit(false);
			rcnt = LectureMngDB.mergeOne(super.oDb, param);
            rcnt = LectureMngDB.updateClassSchedule(super.oDb, param);
            rcnt = LectureMngDB.updatePrgBasis(super.oDb, param);
            rcnt = LectureMngDB.insertRatBasis(super.oDb, param);
            
			oDb.commit();
		} catch ( Exception e) {
			maf.logger.Logging.trace(e);
			oDb.rollback();
			rcnt = 0;
		} finally {
			oDb.setAutoCommit(true);
		}
		
		if(rcnt>0) {
			UrlAddress next = new UrlAddress (super.controlAction);
			next.addParam(MafConstant.LIST_OP_NAME, this.listOp.getSerializeUrl());
			result.setNext(next);
			result.setSuccess(true,new ResultMessage(this.MESSAGE_BUNDLENAME, "insert.ok", new Integer(rcnt)));
		} else {
			result.setSuccess(false,new ResultMessage(this.MESSAGE_BUNDLENAME, "insert.fail", new Integer(rcnt)));
		}
	}
	
	/**
	 * delete
	 * 
	 * @param _req
	 * @param _res
	 */	
	public void cmdDelete(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {

		Map param = _req.getKeyValueMap();
		param.put("lec_cd", _req.getP("lec_cd"));
			
		int rcnt= 0;
		boolean chk = false;
		try {
			oDb.setAutoCommit(false);
			rcnt = LectureMngDB.deleteOne(super.oDb, param);
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
			result.setSuccess(true,new ResultMessage(this.MESSAGE_BUNDLENAME, "delete.ok", new Integer(rcnt)));
		} else {
			result.setSuccess(false,new ResultMessage(this.MESSAGE_BUNDLENAME, "delete.fail"));
		}
	}		

	/**
	 * copyLec
	 * 
	 * @param _req
	 * @param _res
	 */	
	public void cmdCopyOk(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {

		Map param = _req.getKeyValueMap();
		
		param.put("usn", super.sessionBean.getUsn());

		int rcnt= 0;
		boolean chk = false;
		try {
			oDb.setAutoCommit(false);
			rcnt = LectureMngDB.copyOne(super.oDb, param);
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
			result.setSuccess(true,new ResultMessage(this.MESSAGE_BUNDLENAME, "reopen.ok", new Integer(rcnt)));
		} else {
			result.setSuccess(false,new ResultMessage(this.MESSAGE_BUNDLENAME, "reopen.fail"));
		}
	}		

	public void cmdExcel(MyHttpServletRequest _req, HttpServletResponse _res)
	throws MafException {
		
        final String[] searchFields = {"s_section", "s_field", "s_cert_lvl", "s_lecstat", "s_lec_sdate","s_lec_edate", "s_sjt_cd", "s_lec_nm"};
		
		Map param = this.listOp.getMergedParam(_req, searchFields);
		
		param.put("usn", super.sessionBean.getUsn());

		SqlWhereBuilder wb = oDb.getWhereBuilder();
        wb.addCond(Where.eq("x.section", ":s_section"));    
        wb.addCond(Where.eq("x.field", ":s_field"));
        wb.addCond(Where.eq("x.crt_lvl", ":s_crt_lvl"));
        wb.addCond(Where.eq("x.lec_stat", ":s_lec_stat"));
        wb.addCond(Where.between("x.lec_edate", ":s_lec_sdate", ":s_lec_edate"));
        wb.addCond(Where.eq("x.sjt_cd", ":s_sjt_cd"));
        wb.addCond(Where.like("x.lec_nm", ":s_lec_nm"));
        
		ViewerSupport.setDataSourceList(_req, LectureMngDB.getList(super.oDb, param, wb.getWhereString(param)));
		
		result.setForward("excel");
	}	

}