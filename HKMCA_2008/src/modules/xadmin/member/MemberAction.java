/*
 * Created on 2006. 06. 14.
 *
 * Copyright (c) 2004 UBQ  All rights reserved.
 */
package modules.xadmin.member;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;

import maf.MafConstant;
import maf.beans.NavigatorInfo;
import maf.exception.MafException;
import maf.lib.excel.importer.ExcelErrorInfo;
import maf.lib.excel.importer.ExcelErrorInfoBean;
import maf.lib.excel.importer.ExcelImportManager;
import maf.mdb.sqlhelper.SqlWhereBuilder;
import maf.mdb.sqlhelper.Where;
import maf.web.http.MyHttpServletRequest;
import maf.web.http.UrlAddress;
import maf.web.multipart.UploadedFile;
import maf.web.mvc.action.MafAction;
import maf.web.mvc.beans.ResultMessage;
import maf.web.util.SerializeHashtable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MemberAction extends MafAction {
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
	 * 사용자 목록 가져오기
	 * 
	 * @param _req
	 * @param _res
	 */
	public void cmdList(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		
		final String[] searchFields = {"s_userid", "s_nm"};

		// Order default 값 설정 시
		NavigatorInfo navigator = new NavigatorInfo(_req, this.listOp);
		Map param = this.listOp.getMergedParam(_req, searchFields);
		
		SqlWhereBuilder wb = oDb.getWhereBuilder();

    	wb.addCond(Where.like("userid",":s_userid"));  
    	wb.addCond(Where.like("nm",":s_nm"));      

		MemberDB.getList(super.oDb, navigator, param, wb.getWhereString(param));
		result.setAttribute("navigator", navigator);
		result.setForward("list");
	}
	
	/**
	 * 전체 사용자 하나의 record를 읽어와서 보여준다.
	 * 
	 * @param _req
	 * @param _res
	 */
	public void cmdView(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {

		Map param = new HashMap();
		param.put("usn", _req.getP("usn"));
		result.setAttribute("item", MemberDB.getOne(super.oDb, param));
		result.setForward("view");
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
	
	/**
	 * edit form 
	 * 
	 * @param _req
	 * @param _res
	 */
	public void cmdEdit(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		Map param = new HashMap();
		param.put("usn", _req.getP("usn"));
		result.setAttribute("item", MemberDB.getOne(super.oDb, param));
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
		param.put("usn", _req.getP("usn"));
		param.put("userid", _req.getP("userid"));
		
		
		// 최종 수정자
		param.put("update_usn",super.sessionBean.getUsn());
		//param.put("update_usn","parksh0808");
		int rcnt= 0;
		try {
			oDb.setAutoCommit(false);
			rcnt = MemberDB.mergeOne(super.oDb, param);
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
			result.setSuccess(true, new ResultMessage(this.MESSAGE_BUNDLENAME, "message.update.ok", new Integer(rcnt)));
		} else {
			result.setSuccess(false,new ResultMessage(this.MESSAGE_BUNDLENAME, "message.update.fail", new Integer(rcnt)));
		}
	}
	
	/**
	 * insert
	 * 
	 * @param _req
	 * @param _res
	 */
	public void cmdInsert(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {

		int rcnt = 0;
		Map param = _req.getKeyValueMap();

		try {

			oDb.setAutoCommit(false);
			rcnt = MemberDB.mergeOne(super.oDb, param);
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
			result.setSuccess(true,new ResultMessage(this.MESSAGE_BUNDLENAME, "message.insert.ok", new Integer(rcnt)));
		} else {
			result.setSuccess(false,new ResultMessage(this.MESSAGE_BUNDLENAME, "message.insert.fail", new Integer(rcnt)));
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
		param.put("usn", _req.getP("usn"));
		param.put("userid", _req.getP("userid"));
			
		int rcnt= 0;
		boolean chk = false;
		try {
			oDb.setAutoCommit(false);
			rcnt += MemberDB.deleteOne(super.oDb, param);
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
			result.setSuccess(true,new ResultMessage(this.MESSAGE_BUNDLENAME, "message.delete.ok", new Integer(rcnt)));
		} else {
			result.setSuccess(false,new ResultMessage(this.MESSAGE_BUNDLENAME, "message.delete.fail"));
		}
	}		

	/**
	 * 차수리스트 excel down
	 * 
	 * @param _req
	 * @param _res
	 * @throws MafException
	 */
	public void cmdExcel(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {

		Map param = new HashMap();
		NavigatorInfo navigator = new NavigatorInfo(_req, this.listOp);
		param.put("comp_usn", super.sessionBean.getUsn());

//		ViewerSupport.setDataSourceList(_req, MemberDB.getList(super.oDb, navigator, param));
		result.setForward("sample");

	}

	/**
	 * 수강생 등록 샘플
	 * 
	 * @param _req
	 * @param _res
	 * @throws MafException
	 */
	public void cmdUpload(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {

		ExcelImportManager up = new ExcelImportManager(

		"/WEB-INF/reports/excelUpload/edu_user.xml", null);

		this.listOp.put("comp_id", _req.getP("comp_id", listOp.get("comp_id")));
		result.setAttribute("fileinfo", up);
		result.setForward("upload");
		
	}

	/**
	 * 수강생 등록 샘플
	 * 
	 * @param _req
	 * @param _res
	 * @throws MafException
	 */
	public void cmdSample(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {

		Map param = new HashMap();
		ExcelImportManager up = new ExcelImportManager("/WEB-INF/reports/excelUpload/edu_user.xml", param);
		up.sampleDownLoader(_req, _res);
		
		result.setForward("sample");
	}

	/**
	 * 수강생 등록 excel upload_ok
	 * 
	 * @param _req
	 * @param _res
	 * @throws MafException
	 */
	public void cmdUploadOk(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {

		int rcnt = 0;
		Map param = _req.getKeyValueMap();
		boolean chk = false;
		

			UploadedFile excel = _req.getFileParameter("excelfile");

			param = new HashMap();
			param.put("reg_usn", super.sessionBean.getUsn());
			chk = false;
			StringBuffer ErrorMsg = new StringBuffer(200);
			this.listOp.put("comp_id", _req.getP("comp_id", listOp.get("comp_id")));
			param.put("comp_id", listOp.get("comp_id"));

			if (excel != null) {

				ExcelImportManager up = new ExcelImportManager("/WEB-INF/reports/excelUpload/edu_user.xml", param);

				String qty1 = (String) _req.getP("qty");
				String qty2 = listOp.get("qty");
				String qty3 = (String) param.get("qty");
				
				ExcelErrorInfo errorInfo = up.process(super.oDb, excel);
				if (errorInfo.isError()) {
					ExcelErrorInfoBean[] errList = errorInfo.getErrorList();
					if (errList != null) {
						for (int i = 0; i < errList.length; i++) {
							ExcelErrorInfoBean eb = errList[i];
							ErrorMsg.append(eb.getRow() + ",");
						}
						ErrorMsg.append(" 열에서  Error가 발생했습니다.");
					}

					chk = false;
				} else {
					chk = true;
				}			
				
			} else {
				chk = false;
				result.setForward("stdlist");
			}

		if (chk) {
			UrlAddress next = new UrlAddress(super.controlAction);
			next.addParam(MafConstant.LIST_OP_NAME, this.listOp.getSerializeUrl());
			next.addParam("cmd", "stdlist");
			result.setNext(next);
			result.setSuccess(true, new ResultMessage(this.MESSAGE_BUNDLENAME, "message.upload.ok"));
		} else {
			result.setSuccess(false, new ResultMessage(ErrorMsg.toString()));
		}
	}

}