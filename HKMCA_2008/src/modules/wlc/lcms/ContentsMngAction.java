/*
 * Created on 2006. 6. 22.
 *
 * Copyright (c) 2006 UBQ All rights reserved.
 */
package modules.wlc.lcms;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import maf.MafConstant;
import maf.aam.AAMManager;
import maf.beans.NavigatorInfo;
import maf.beans.NavigatorOrderInfo;
import maf.exception.MafException;
import maf.mdb.sqlhelper.SqlWhereBuilder;
import maf.mdb.sqlhelper.Where;
import maf.util.StringUtils;
import maf.web.exception.UploadedFileException;
import maf.web.http.MyHttpServletRequest;
import maf.web.http.UrlAddress;
import maf.web.multipart.UploadedFile;
import maf.web.mvc.action.MafAction;
import maf.web.mvc.beans.ResultMessage;
import maf.web.util.SerializeHashtable;
import miraenet.AppConfig;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ContentsMngAction extends MafAction {
	private Log logger = LogFactory.getLog(this.getClass());
	SerializeHashtable listOp = null;
	private final String MESSAGE_BUNDLENAME = "common.message";

	public void preProcess(MyHttpServletRequest _req, HttpServletResponse _res)
	        throws MafException {
		super.chkLogin();
		this.listOp = new SerializeHashtable(_req.getParameter(MafConstant.LIST_OP_NAME));
	}

	public void postProcess(MyHttpServletRequest _req, HttpServletResponse _res)
	        throws MafException {
		result.setAttribute(MafConstant.LIST_OP_NAME, listOp);
	}

	/**
	 * 
	 *
	 * @param _req
	 * @param _res
	 */
	public void cmdList(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		final String[] searchFields = {"daename", "cenname", "soname", "os_crs", "os_sjt"};
		// Order default 
		NavigatorInfo navigator = new NavigatorInfo(_req, this.listOp, new NavigatorOrderInfo("A:sjt_cd, htmcode"));
		Map param = this.listOp.getMergedParam(_req, searchFields);
		String destination = null;

		SqlWhereBuilder wb = oDb.getWhereBuilder();
		wb.addCond(Where.eq("x.crs_cd", ":os_crs"));
		wb.addCond(Where.eq("x.sjt_cd", ":os_sjt"));
		wb.addCond(Where.like("daename", ":daename"));
		wb.addCond(Where.like("cenname", ":cenname"));
		wb.addCond(Where.like("soname", ":soname"));

		ContentsMngDB.getList(super.oDb, navigator, param, wb.getWhereString(param));
		
        if (!"".equals(param.get("os_sjt"))) {
        	destination = "os_sjt";
        } else if  (!"".equals(param.get("os_crs"))) {
        	destination = "os_crs";
        }

        result.setAttribute("navigator", navigator);
		result.setAttribute("destination", destination);
		result.setForward("list");
	}

	/**
	 * 
	 *
	 * @param _req
	 * @param _res
	 */
	public void cmdEdit(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		Map param = new HashMap();
		param.put("htmcode", _req.getP("htmcode"));
		param.put("sjt_cd", _req.getP("sjt_cd"));
		result.setAttribute("item", ContentsMngDB.getOne(super.oDb, param));
		if(super.sessionBean.hasRole(AAMManager.ROLE_KEY_ROOT)) {
			result.setAttribute("isroot", Boolean.TRUE);
		} else {
			result.setAttribute("isroot", Boolean.FALSE);
		}
		result.setForward("edit");
	}

	/**
	 * 
	 *
	 * @param _req
	 * @param _res
	 */
	public void cmdUpdate(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		
		Map param = _req.getKeyValueMap();

		boolean chk = false;
		int rcnt = 0;
		try {
			oDb.setAutoCommit(false);
			ContentsMngDB.updateOne(super.oDb, param);
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
			result.setNext(next);
			result.setSuccess(true, new ResultMessage(this.MESSAGE_BUNDLENAME, "update.ok", new Integer(rcnt)));
		} else {
			result.setSuccess(false, new ResultMessage(this.MESSAGE_BUNDLENAME, "update.fail", new Integer(rcnt)));
		}
	}

	/**
	 * 
	 *
	 * @param _req
	 * @param _res
	 */
	public void cmdAdd(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		
		result.setAttribute("slist", ContentsMngDB.getSubjectList(super.oDb));
		result.setForward("edit");
	}

	/**
	 * 
	 *
	 * @param _req
	 * @param _res
	 */
	public void cmdInsert(MyHttpServletRequest _req, HttpServletResponse _res)  throws MafException {

		Map param = _req.getKeyValueMap();
		
		boolean chk = false;
		int rcnt = 0;
		try {
			oDb.setAutoCommit(false);
			param.put("htmcode", ContentsMngDB.getHtmcode(super.oDb, param)+"");
			param.put("daecode", param.get("sjt_cd")+"-"+param.get("htmcode"));
			rcnt = ContentsMngDB.insertOne(super.oDb, param);
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
			result.setNext(next);
			result.setSuccess(true, new ResultMessage(this.MESSAGE_BUNDLENAME, "insert.ok", new Integer(rcnt)));
		} else {
			result.setSuccess(false, new ResultMessage(this.MESSAGE_BUNDLENAME, "insert.fail", new Integer(rcnt)));
		}
	}

	/**
	 * 
	 *
	 * @param _req
	 * @param _res
	 */
	public void cmdDelete(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		
		Map param = _req.getKeyValueMap();
		String[] htmcodes = _req.getParameterValues("htmcode_chk");
		
		int rcnt = 0;
		try {

			oDb.setAutoCommit(false);
            if(null != htmcodes) {
            	for(int i =0; i < htmcodes.length; i++) {
                    String[] tmpData = htmcodes[i].split("#");
            		param.put("sjt_cd", tmpData[0]);
                    param.put("htmcode", tmpData[1]);
            		
            		rcnt += ContentsMngDB.deleteOne(super.oDb, param);
            	}
            }
    		rcnt = ContentsMngDB.deleteOne(super.oDb, param);

            oDb.commit();
		} catch (Exception e) {
			oDb.rollback();
			logger.error(e.getMessage());
			rcnt = 0;
		} finally {
			oDb.setAutoCommit(true);
		}
		if (rcnt > 0) {
			UrlAddress next = new UrlAddress(super.controlAction);
			next.addParam(MafConstant.LIST_OP_NAME, this.listOp.getSerializeUrl());
			result.setNext(next);
			result.setSuccess(true, new ResultMessage(this.MESSAGE_BUNDLENAME, "delete.ok", new Integer(rcnt)));
		} else {
			result.setSuccess(false, new ResultMessage(this.MESSAGE_BUNDLENAME, "delete.fail", new Integer(rcnt)));
		}
	}

	/**
	 * 
	 *
	 * @param _req
	 * @param _res
	 */
	public void cmdAddExcel(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {

		result.setAttribute("slist", ContentsMngDB.getSubjectList(super.oDb));
		result.setForward("add_excel");
	}

	/**
	 * 
	 *
	 * @param _req
	 * @param _res
	 */
	public void cmdInsertExcel(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		String[][] fields = null;
		String buffer = null;
		FileReader fr = null;
		List list = new ArrayList();
		UploadedFile excelFile = _req.getFileParameter("excel_file");
		String pPath = _req.getRealPath(AppConfig.DEFAULT_CONTENTS_FILE_PATH_TMP);
		try {
			excelFile.SaveTo(pPath, false);
			fr = new FileReader(pPath + File.separator + excelFile.getNewfilename());
			BufferedReader br = new BufferedReader(fr);
			do {
				buffer = br.readLine();
				list.add(buffer);
			} while (buffer != null);
			fields = new String[list.size()][];
			for (int i = 0; i < list.size(); i++) {
				buffer = (String) list.get(i);
				if (buffer != null) {
					fields[i] = StringUtils.getItemArray(buffer, ',');
					logger.debug("fields : " + fields[i]);
				}
			}
			fr.close();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (UploadedFileException e) {
			e.printStackTrace();
		}
		boolean chk = false;
		int rcnt = 0;
		try {
			oDb.setAutoCommit(false);
			rcnt = ContentsMngDB.insertExcel(super.oDb, fields);
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
			next.addParam(MafConstant.LIST_OP_NAME, this.listOp.getSerializeUrl());
			result.setNext(next);
			result.setSuccess(true, new ResultMessage(this.MESSAGE_BUNDLENAME, "insert.ok", new Integer(rcnt)));
		} else {
			result.setSuccess(false, new ResultMessage(this.MESSAGE_BUNDLENAME, "insert.fail", new Integer(rcnt)));
		}
	}
	
}