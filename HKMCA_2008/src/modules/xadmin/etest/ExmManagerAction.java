/*
 * Created on 2006. 06. 14.
 *
 * Copyright (c) 2004 UBQ All rights reserved.
 */
package modules.xadmin.etest;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import maf.MafConstant;
import maf.beans.NavigatorInfo;
import maf.beans.NavigatorOrderInfo;
import maf.exception.MafException;
import maf.lib.excel.importer.ExcelErrorInfo;
import maf.lib.excel.importer.ExcelImportManager;
import maf.mdb.sqlhelper.SqlWhereBuilder;
import maf.mdb.sqlhelper.Where;
import maf.web.http.MyHttpServletRequest;
import maf.web.http.UrlAddress;
import maf.web.multipart.UploadedFile;
import maf.web.mvc.action.MafAction;
import maf.web.mvc.beans.ResultMessage;
import maf.web.mvc.view.JsonViewer;
import maf.web.mvc.view.ViewerSupport;
import maf.web.util.SerializeHashtable;
import miraenet.AppConfig;
import modules.common.jason.JSONObject;
import modules.etest.learner.ExamListDB;
import modules.etest.stat.EtestStatDB;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * ���� ����
 *
 *
 *
 */
public class ExmManagerAction extends MafAction {
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
	 * ��� ��������
	 * 
	 * @param _req
	 * @param _res
	 */
	public void cmdList(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		final String[] searchFields = { "s_exmid",  "s_exmtitle" };
		// Order default �� ���� ��
		NavigatorInfo navigator = new NavigatorInfo(_req, this.listOp, new NavigatorOrderInfo("A:exmid"));
		Map param = this.listOp.getMergedParam(_req, searchFields);
		SqlWhereBuilder wb = oDb.getWhereBuilder();
		wb.addCond(Where.like("exmid", ":s_exmid"));
		wb.addCond(Where.like("exmtitle", ":s_exmtitle"));
		ExmManagerDB.getList(super.oDb, navigator, param, wb.getWhereString(param), false);
		result.setAttribute("navigator", navigator);
		result.setForward("list");
	}

	/**
	 * �ϳ��� record�� �о�ͼ� �����ش�.
	 * 
	 * @param _req
	 * @param _res
	 */
	public void cmdView(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
        final String[] searchFields = {"exmid"};
        
		NavigatorInfo navigator = new NavigatorInfo(_req, this.listOp);
        Map param = this.listOp.getMergedParam(_req, searchFields);

		result.setAttribute("item", ExmManagerDB.getOne(super.oDb, param));
		
        SqlWhereBuilder wb = oDb.getWhereBuilder();
		
		ExmManagerDB.getRstList(super.oDb, navigator, param, wb.getWhereString(param), false);
		result.setAttribute("navigator", navigator);
		result.setAttribute("exmStat", EtestStatDB.getTestResultByExmId(super.oDb, param));
		result.setForward("view");
	}

	/**
	 * insert form
	 * 
	 * @param _req
	 * @param _res
	 */
	public void cmdAdd(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
        Map param = new HashMap();
        param.put("usn", super.sessionBean.getUsn());
//        result.setAttribute("list", ExmManagerDB.getExmSetList(super.oDb, param));
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
		param.put("exmid", _req.getP("exmid"));
		result.setAttribute("item", ExmManagerDB.getOne(super.oDb, param));
		result.setForward("edit");
	}

	/**
	 * �ϳ��� record�� ���� �Ѵ�.
	 * 
	 * @param _req
	 * @param _res
	 */
	public void cmdUpdate(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		Map param = _req.getKeyValueMap();

		int rcnt = 0;
		param.put("exmid", _req.getP("exmid"));
		param.put("usn", super.sessionBean.getUsn());

		try {
			oDb.setAutoCommit(false);
			rcnt += ExmManagerDB.mergeOne(super.oDb, param);
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
        param.put("exmowner", super.sessionBean.getUsn());
        param.put("usn", super.sessionBean.getUsn());

		try {
			oDb.setAutoCommit(false);

            rcnt = ExmManagerDB.mergeOne(super.oDb, param);

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
	 * �ѻ���ڸ� ������ �ϵ��� �Ѵ�. ���� ����� �״�� 
	 * @param _req
	 * @param _res
	 * @throws MafException
	 */
	public void cmdSetRetry(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		
		Map param = new HashMap();
		param.put("usn", _req.getP("usn"));
		param.put("exmid", _req.getP("exmid"));
		
		int i = ExmManagerDB.setRetry(oDb, param);
		
		JSONObject jobj = new JSONObject();
    	_res.setContentType(JsonViewer.CONTENTS_TYPE);
    	_res.setHeader("Cache-Control", "no-cache");

    	if(i>0) {
    		jobj.put("qseq", _req.getP("qseq"));
    	}
    	
		try {
			ServletOutputStream ouputStream = _res.getOutputStream();
			ouputStream.print(jobj.toString());
			ouputStream.close();
		} catch (Exception e) {
			logger.debug(e.getMessage());
		}
		
		result.setForward("dummy");
	}
	
	
	/**
	 * �ѻ���ڸ� ��� ���� �ϵ��� �Ѵ�. ���� ����� �״�� 
	 * @param _req
	 * @param _res
	 * @throws MafException
	 */
	public void cmdSetContinue(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		
		Map param = new HashMap();
		param.put("usn", _req.getP("usn"));
		param.put("exmid", _req.getP("exmid"));
		
		int i = ExmManagerDB.setContinue(oDb, param);
		
		JSONObject jobj = new JSONObject();
    	_res.setContentType(JsonViewer.CONTENTS_TYPE);
    	_res.setHeader("Cache-Control", "no-cache");

    	if(i>0) {
    		jobj.put("qseq", _req.getP("qseq"));
    	}
    	
		try {
			ServletOutputStream ouputStream = _res.getOutputStream();
			ouputStream.print(jobj.toString());
			ouputStream.close();
		} catch (Exception e) {
			logger.debug(e.getMessage());
		}
		
		result.setForward("dummy");
	}
	
	/**
	 * ä�� �� �� Ǯ�� ���� 
	 * @param _req
	 * @param _res
	 * @throws MafException
	 */
	public void cmdShowDetailScore(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		String[] chks = {"exmid", "usn"};
		super.chkEmpty(_req, chks);
		Map param = _req.getKeyValueMap();
		Map item = ExmManagerDB.getUserResult(super.oDb, param);
		param.put("lang", item.get("lang"));
		result.setAttribute("uitem", item);
        result.setAttribute("items", ExmManagerDB.getUserResultItem(super.oDb, param));
		result.setForward("showdetail");
	}
	
	/**
	 * ä�� 
	 * @param _req
	 * @param _res
	 * @throws MafException
	 */
	public void cmdMarkScore(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		String[] updateds = _req.getParameterValues("updated");
		String[] chks = {"exmid","usn"};
		int rcnt = 0;
		super.chkEmpty(_req, chks);
		Map param = _req.getKeyValueMap();
		param.put("upt_id", super.sessionBean.getUsn());
		
		boolean chk = false;
		try {
			oDb.setAutoCommit(false);
			if(updateds != null) {
				for(int i = 0; i < updateds.length; i++) {
					//[correct_yn, usrscore, update_dt, update_id, exmid, usn,lecnumb, qseq]
					param.put("qseq", updateds[i]);
					param.put("correct_yn", "Y".equals(_req.getP("correct_" + updateds[i])) ?"Y":"N");
					param.put("usrscore", _req.getP("usrscore_"+ updateds[i]));
					rcnt += ExmManagerDB.updateRstItems(super.oDb,param);
				}
			}
			ExmManagerDB.updateRst(super.oDb,param);
			oDb.commit();
			chk = true;
		} catch (Exception e) {
			oDb.rollback();
			logger.error(e.getMessage());
			
		} finally {
			oDb.setAutoCommit(true);
		}
		if (chk) {
			UrlAddress next = new UrlAddress(super.controlAction);
			next.addParam(MafConstant.LIST_OP_NAME, this.listOp.getSerializeUrl());
			next.addParam("cmd", "view");
			next.addParam("exmid", _req.getP("exmid"));
			result.setNext(next);
			result.setSuccess(true, new ResultMessage(this.MESSAGE_BUNDLENAME, "update.ok", new Integer(rcnt)));
		} else {
			result.setSuccess(false, new ResultMessage(this.MESSAGE_BUNDLENAME, "update.fail", new Integer(rcnt)));
		}
	}
	
	/**
	 * ������ �߰� 
	 * @param _req
	 * @param _res
	 * @throws MafException
	 */
	public void cmdAddRst (MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		final String[] chks = {"exmid"};
		super.chkEmpty(_req, chks);
		String[] v_usn = _req.getParameterValues("v_usn");
		Map param = new HashMap();
		param.put("exmid", _req.getP("exmid"));
		param.put("reg_id", super.sessionBean.getUsn());
		
		int rcnt = 0;
		boolean chk = false;
		try {
			oDb.setAutoCommit(false);
			
			if(v_usn != null) {
				for(int i = 0; i < v_usn.length; i ++ ) {
					param.put("usn", v_usn[i]);
					
					rcnt += ExmManagerDB.insertRstOne(super.oDb, param);
				}
			}
			
			oDb.commit();
			chk = true;
		} catch (Exception e) {
			oDb.rollback();
			logger.error(e.getMessage());
			rcnt = 0;
		} finally {
			oDb.setAutoCommit(true);
		}
		if (chk) {
			UrlAddress next = new UrlAddress(super.controlAction);
			next.addParam("cmd", "view");
			next.addParam("exmid",  _req.getP("exmid"));
			next.addParam(MafConstant.LIST_OP_NAME, this.listOp.getSerializeUrl());
			result.setNext(next);
			result.setSuccess(true, new ResultMessage(this.MESSAGE_BUNDLENAME, "message.insert.ok", new Integer(rcnt)));
		} else {
			result.setSuccess(false, new ResultMessage(this.MESSAGE_BUNDLENAME, "message.insert.fail", new Integer(rcnt)));
		}
	}
	
	/**
	 * ���� ���� Excel�� down
	 * @param _req
	 * @param _res
	 * @throws MafException
	 */
	public void cmdExcel (MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		Map param = new HashMap();
		param.put("exmid", _req.getP("exmid"));
		result.setAttribute("item", ExmManagerDB.getOne(super.oDb, param));
		ViewerSupport.setDataSourceList(_req, ExmManagerDB.getRstList(super.oDb, param, ""));
		result.setForward("excel");
	}
	
	public void cmdUploadExcel(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		result.setAttribute("exmid", _req.getP("exmid"));
		ExcelImportManager up = new ExcelImportManager("/WEB-INF/reports/excelUpload/etest_result.xml",null);
		result.setAttribute("fileinfo", up);
		result.setForward("uploadExcel");
		
	}
	
	/**
     * Excel UPload Sample �ٿ�ε�
     * @param _req
     * @param _res
     * @throws MafException
     */
    public void cmdUploadSampleDownload(MyHttpServletRequest _req, HttpServletResponse _res)
	throws MafException {
		
		Map param = new HashMap();
		ExcelImportManager up = new ExcelImportManager("/WEB-INF/reports/excelUpload/etest_result.xml", param);
		up.sampleDownLoader(_req, _res);
		result.setForward("dummy");
	}
    
    /**
	 * ���� Upload
	 * 
	 * @param _req
	 * @param _res
	 */	
	public void cmdUploadOk(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		String[] chks = {"exmid"};
		super.chkEmpty(_req, chks);
        UploadedFile excel = _req.getFileParameter("certifile");
        Map param = new HashMap();
        param.put("upt_id", super.sessionBean.getUsn());
        param.put("exmid", _req.getP("exmid"));
		boolean chk = false;
		String errorMsg = null;
		
        if (excel != null) {
            ExcelImportManager up = new ExcelImportManager("/WEB-INF/reports/excelUpload/etest_result.xml", param);
            ExcelErrorInfo errorInfo = up.process(super.oDb, excel);
            if(errorInfo.isError()) {
            	errorMsg = errorInfo.getMessage();
                chk = false;
            }else {
                chk = true;
            }
        }else{
        	chk = false;	
            result.setForward("view");       
        }

        if(chk ) {
			UrlAddress next = new UrlAddress (super.controlAction);
			next.addParam("cmd", "view");
			next.addParam("exmid", _req.getP("exmid"));
			next.addParam(MafConstant.LIST_OP_NAME, this.listOp.getSerializeUrl());
			result.setNext(next);
			result.setSuccess(true,new ResultMessage(this.MESSAGE_BUNDLENAME, "message.upload.ok"));
		} else {
			result.setSuccess(false,new ResultMessage(errorMsg));
		}
	}	
	
	
	/**
	 * ������ ��¿� �ɼ� ���� ������ 
	 * 
	 * @param _req
	 * @param _res
	 */
	public void cmdPrint(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		Map param = new HashMap();
		String[] chks = {"exmid"};
		super.chkEmpty(_req, chks);
		param.put("exmid", _req.getP("exmid"));
		result.setAttribute("item", ExmManagerDB.getOne(super.oDb, param));
		result.setAttribute("setlangs", ExamListDB.getLangList(super.oDb, param));
		result.setForward("print");
	}
	
	/**
	 * HTML�� ������ ���
	 * @param _req
	 * @param _res
	 * @throws MafException
	 */
	public void cmdPrintHTML(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		String[] chks = {"exmid","lang"};
		super.chkEmpty(_req, chks);
		
		Map param = new HashMap();
		param.put("exmid", _req.getP("exmid"));
		param.put("lang", _req.getP("lang"));
		Map item = ExmManagerDB.getOne(super.oDb, param);
		result.setAttribute("item", item);
		result.setAttribute("list", ExmManagerDB.getPrintList(super.oDb, param));
		result.setForward("printHTML");
	}
	
	/**
	 * PDF�� ������ ���
	 * @param _req
	 * @param _res
	 * @throws MafException
	 */
	public void cmdPrintPDF(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		String[] chks = {"exmid","lang"};
		super.chkEmpty(_req, chks);
		
		Map param = new HashMap();
		param.put("exmid", _req.getP("exmid"));
		param.put("lang", _req.getP("lang"));
		result.setAttribute("list", ExmManagerDB.getOne(super.oDb, param));
		
		Map item = ExmManagerDB.getOne(super.oDb, param);
		
		param.put("p_filepath", _req.getRealPath(AppConfig.DEFAULT_EXAM_FILE_PATH) );
		param.put("p_coursename", item.get("exmtitle"));
		
		ViewerSupport.setPapameterMap(_req, param);
		ViewerSupport.setDataSourceList(_req, ExmManagerDB.getPrintList(super.oDb, param));
		result.setForward("jpdf");
	}
	
}
