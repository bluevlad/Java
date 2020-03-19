/*
 * Created on 2006. 06. 14.
 *
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package miraenet.app.event;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import maf.MafConstant;
import maf.beans.NavigatorInfo;
import maf.exception.MafException;
import maf.mdb.sqlhelper.SqlWhereBuilder;
import maf.mdb.sqlhelper.Where;
import maf.web.multipart.UploadedFile;
import maf.web.http.MyHttpServletRequest;
import maf.web.mvc.action.MafAction;
import maf.web.mvc.beans.ResultMessage;
import maf.web.util.SerializeHashtable;
import miraenet.MiConfig;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;



public class eventAdmin extends MafAction {
    final private Log  logger = LogFactory.getLog(eventAdmin.class);
    SerializeHashtable listOp = null;
    private String filepath = null;
    private final String MESSAGE_BUNDLENAME = "common.message";
    UploadedFile upfile = null;
    public void preProcess(MyHttpServletRequest _req, HttpServletResponse _res)
	throws MafException {
    	chkLogin();
		this.listOp = new SerializeHashtable( _req.getParameter(MafConstant.LIST_OP_NAME) );
	    filepath = _req.getContextPath()+MiConfig.DEFAULT_EVENT_FILE_PATH; // 썸네일이미지 저장 경로
	}
	public void postProcess(MyHttpServletRequest _req, HttpServletResponse _res) 
	throws MafException {
		result.setAttribute(MafConstant.LIST_OP_NAME, listOp);
	}	

	/**
	 * list
	 * 
	 * @param _req
	 * @param _res
	 */
	public void cmdList(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {	

		final String[] searchFields = {"evt_title", "evt_contents"};

		NavigatorInfo navigator = new NavigatorInfo(_req, this.listOp);
		Map param = this.listOp.getMergedParam(_req, searchFields);
		
		SqlWhereBuilder wb = oDb.getWhereBuilder();

		wb.addCond(Where.like("x.evt_title", ":evt_title"));
		wb.addCond(Where.like("x.evt_contents", ":evt_contents"));

		eventDB.getList(super.oDb, navigator, param, wb.getWhereString(param));

		result.setAttribute("navigator", navigator);
		result.setForward("list");
	}
		
	/**
	 * add
	 * 
	 * @param _req
	 * @param _res
	 */
	public void cmdAdd(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {	
		
		result.setForward("edit");
	}	

	/**
	 * insert
	 * 
	 * @param _req
	 * @param _res
	 */
	public void cmdInsert(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		
		String inserted_event_id = null;

		Map param = _req.getKeyValueMap();
		param.put("evt_upid", super.sessionBean.getUserid());
		param.put("evt_req", _req.getP("evt_req", "N"));

    	try {
			oDb.setAutoCommit(false);
	        upfile = _req.getFileParameter("upfile");

            if (upfile != null) {
		        if ( upfile.getFileSize() > 0 ) {
		            upfile.SaveAs(filepath, maf.MafUtil.getDBGuid(oDb));
		    		param.put("evt_img", upfile.getNewfilename());
		        }
            }

            inserted_event_id =  eventDB.insertOne(super.oDb, param);
			oDb.commit();
		} catch ( Exception e) {
			oDb.rollback();
			logger.error(e.getMessage());
			
		} finally {
			oDb.setAutoCommit(true);
		}

		if (inserted_event_id != null) {

			result.setSuccess(true, new ResultMessage(this.MESSAGE_BUNDLENAME, "message.insert.ok", new Integer(1)));
			result.setNext(this.controlAction + "?cmd=edit&evt_id=" + inserted_event_id);
		} else {
			result.setSuccess(false, new ResultMessage(this.MESSAGE_BUNDLENAME, "message.insert.fail"));
		}

	}
	
	/**
	 * edit
	 * 
	 * @param _req
	 * @param _res
	 */
	public void cmdEdit(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		Map param = _req.getKeyValueMap();

		result.setAttribute("filepath", _req.getContextPath()+MiConfig.DEFAULT_EVENT_FILE_PATH);
		result.setAttribute("item", eventDB.getOne(super.oDb, param));
		result.setForward("edit");
	}

	/**
	 * update
	 * 
	 * @param _req
	 * @param _res
	 */
	public void cmdUpdate(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		
		int rcnt = 0;

		Map param = _req.getKeyValueMap();
		param.put("evt_upid", super.sessionBean.getUserid());
		param.put("evt_req", _req.getP("evt_req", "N"));

    	try {
			oDb.setAutoCommit(false);
	        upfile = _req.getFileParameter("upfile");
            String oldfile = _req.getP("evt_img");
            String del_attach = _req.getP("del_attach");

            if("T".equals(del_attach)) {
            	maf.util.FileUtils.delFile(filepath, oldfile);
	    		param.put("evt_img", null);
            }
            
            if (upfile != null) {
	            if (upfile.getFileSize() > 0) {
		            upfile.SaveAs(filepath, maf.MafUtil.getDBGuid(oDb));
		    		param.put("evt_img", upfile.getNewfilename());
	                if (oldfile != null) {
	                	maf.util.FileUtils.delFile(filepath, oldfile);
	                }
		        }
            }
            
			rcnt += eventDB.updateOne(super.oDb, param);

			oDb.commit();
		} catch ( Exception e) {
			oDb.rollback();
			logger.error(e.getMessage());
			rcnt = 0;
		} finally {
			oDb.setAutoCommit(true);
		}

		if (rcnt > 0) {
			result.setSuccess(true, new ResultMessage(this.MESSAGE_BUNDLENAME, "message.update.ok", new Integer(rcnt)));
			result.setNext(this.controlAction + "?cmd=edit&evt_id=" + param.get("evt_id"));
		} else {
			result.setSuccess(false, new ResultMessage(this.MESSAGE_BUNDLENAME, "message.update.fail"));
		}
	}
	
	/**
	 * delete (single & multi)
	 * 
	 * @param _req
	 * @param _res
	 * @throws MafException
	 */
	public void cmdDelete(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		
		int rcnt = 0;
		Map param = _req.getKeyValueMap();
		
    	try {
			oDb.setAutoCommit(false);
            String oldfile = _req.getP("oldfile");

            if (oldfile != null) {
                maf.util.FileUtils.delFile(filepath, oldfile);
            }
			rcnt += eventDB.deleteOne(super.oDb, param);

			oDb.commit();
		} catch ( Exception e) {
			oDb.rollback();
			logger.error(e.getMessage());
			rcnt = 0;
		} finally {
			oDb.setAutoCommit(true);
		}


		if (rcnt > 0) {
			result.setSuccess(true, new ResultMessage(this.MESSAGE_BUNDLENAME, "message.delete.ok", new Integer(rcnt)));
			result.setNext(this.controlAction);
		} else {
			result.setSuccess(false, new ResultMessage(this.MESSAGE_BUNDLENAME, "message.delete.fail"));
		}
	}
	
} 
		