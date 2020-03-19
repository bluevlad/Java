/*
 * Created on 2006. 06. 14.
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
import maf.web.util.SerializeHashtable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/*
 * 
 * 과목 관리 
 * 
*/
public class SubjectMngAction extends MafAction {
	
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
	 * 목록 가져오기
	 * 
	 * @param _req
	 * @param _res
	 */
	public void cmdList(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		
		final String[] searchFields = {"os_crs", "s_sjt_nm"};
		
		NavigatorInfo navigator = new NavigatorInfo(_req, this.listOp, new NavigatorOrderInfo("A:sjt_nm"));
	
		Map param = this.listOp.getMergedParam(_req, searchFields);
		
		SqlWhereBuilder wb = oDb.getWhereBuilder();
		wb.addCond(Where.eq("x.crs_cd", ":os_crs"));
		wb.addCond(Where.like("x.sjt_nm", ":s_sjt_nm"));

		SubjectMngDB.getList(super.oDb, navigator, param, wb.getWhereString(param), false);
		result.setAttribute("navigator", navigator);
		result.setAttribute("os_crs", param.get("os_crs"));
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
		result.setAttribute("courselist", CourseMngDB.getCourse(super.oDb, param));  //과정리스트
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
		param.put("sjt_cd", _req.getP("sjt_cd"));
		result.setAttribute("courselist", CourseMngDB.getCourse(super.oDb, param));  //과정리스트
		result.setAttribute("item", SubjectMngDB.getOne(super.oDb, param));
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
		int rcnt= 0;
		
		// 최종 수정자
		param.put("usn",super.sessionBean.getUsn());
		
		try {
			oDb.setAutoCommit(false);
			rcnt = SubjectMngDB.mergeOne(super.oDb, param);
			oDb.commit();
		} catch ( Exception e) {
			oDb.rollback();
			rcnt = 0;
		} finally {
			oDb.setAutoCommit(true);
		}

		if(rcnt>0) {
			UrlAddress next = new UrlAddress (super.controlAction);
			next.addParam(MafConstant.LIST_OP_NAME, this.listOp.getSerializeUrl());
			next.addParam("cmd", "edit");
			next.addParam("sjt_cd", _req.getP("sjt_cd"));
			result.setNext(next);
			result.setSuccess(true, new ResultMessage(this.MESSAGE_BUNDLENAME, "update.ok", new Integer(1)));
		} else {
			result.setSuccess(false,new ResultMessage(this.MESSAGE_BUNDLENAME, "update.fail", new Integer(1)));
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
		int rcnt = 0;

		try {
			oDb.setAutoCommit(false);
			// 등록자
			rcnt = SubjectMngDB.mergeOne(super.oDb, param);
			oDb.commit();
		} catch ( Exception e) {
			oDb.rollback();
			logger.error(e.getMessage());
			rcnt = 0;
		} finally {
			oDb.setAutoCommit(true);
		}
		
		if(rcnt>0) {
			UrlAddress next = new UrlAddress (super.controlAction);
			next.addParam(MafConstant.LIST_OP_NAME, this.listOp.getSerializeUrl());
			result.setNext(next);
			result.setSuccess(true,new ResultMessage(this.MESSAGE_BUNDLENAME, "insert.ok", new Integer(1)));
		} else {
			result.setSuccess(false,new ResultMessage(this.MESSAGE_BUNDLENAME, "insert.fail", new Integer(1)));
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
		String[] vsjtcd = _req.getParameterValues("v_sjt_cd");
		param.put("sjt_cd", _req.getP("sjt_cd"));
			
		int rcnt= 0;
		try {
			oDb.setAutoCommit(false);
            if(null != vsjtcd) {
            	for(int i =0; i < vsjtcd.length; i++) {
            		param.put("sjt_cd", vsjtcd[i]);
        			rcnt += SubjectMngDB.deleteOne(super.oDb, param);
           		}
           	} else {
    			rcnt += SubjectMngDB.deleteOne(super.oDb, param);
           	}
			oDb.commit();
		} catch ( Exception e) {
			oDb.rollback();
			logger.error(e.getMessage());
			rcnt = 0;
		} finally {
			oDb.setAutoCommit(true);
		}
		if(rcnt>0) {
			UrlAddress next = new UrlAddress (super.controlAction);
			next.addParam(MafConstant.LIST_OP_NAME, this.listOp.getSerializeUrl());
			result.setNext(next);
			result.setSuccess(true,new ResultMessage(this.MESSAGE_BUNDLENAME, "delete.ok", new Integer(1)));
		} else {
			result.setSuccess(false,new ResultMessage(this.MESSAGE_BUNDLENAME, "delete.fail"));
		}
	}		
} 