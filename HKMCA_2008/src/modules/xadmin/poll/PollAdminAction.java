/*
 * Created on 2006. 06. 14.
 *
 * Copyright (c) 2004 UBQ All rights reserved.
 */
package modules.xadmin.poll;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;

import maf.MafConstant;
import maf.beans.NavigatorInfo;
import maf.beans.NavigatorOrderInfo;
import maf.exception.MafException;
import maf.mdb.exceptions.MdbException;
import maf.mdb.sqlhelper.SqlWhereBuilder;
import maf.web.http.MyHttpServletRequest;
import maf.web.http.UrlAddress;
import maf.web.mvc.action.MafAction;
import maf.web.mvc.beans.ResultMessage;
import maf.web.util.SerializeHashtable;
import modules.community.poll.PollDB;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class PollAdminAction extends MafAction {
    final private Log  logger = LogFactory.getLog(PollAdminAction.class);
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
		
		final String[] searchFields = {       };
		
		NavigatorInfo navigator = new NavigatorInfo(_req, this.listOp, new NavigatorOrderInfo("D:start_dt"));
		Map param = this.listOp.getMergedParam(_req, searchFields);
		SqlWhereBuilder wb = oDb.getWhereBuilder();

		PollDB.getList(super.oDb, navigator, param, wb.getWhereString(param));
		result.setAttribute("navigator", navigator);
		result.setForward("list");
	}
	
	/**
	 * insert form
	 * 
	 * @param _req
	 * @param _res
	 */
	public void cmdAdd(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		
		result.setForward("edit");
	}
	
	/**
	 * edit form 
	 * 
	 * @param _req
	 * @param _res
	 */
	public void cmdEdit(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		Map param = new HashMap();
		
		param.put("poll_id", _req.getP("poll_id"));
		
		result.setAttribute("item", PollDB.getOne(super.oDb, param));
		result.setAttribute("pollitems", PollDB.getPollItems(super.oDb, param) );
		result.setForward("edit");
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
			rcnt = PollDB.mergeOne(super.oDb, param);

			oDb.commit();
			oDb.setAutoCommit(true);
		} catch( MdbException e) {
			rcnt = 0;
			oDb.rollback();
			logger.error(e.getMessage());
		}
		
		if(rcnt > 0 ) {
			result.setNext(super.controlAction);
			result.setSuccess(true,new ResultMessage(this.MESSAGE_BUNDLENAME, "insert.ok", new Integer(rcnt)));
		} else {
			result.setSuccess(false,new ResultMessage(this.MESSAGE_BUNDLENAME, "insert.fail", new Integer(rcnt)));
		}
	}
	
	/**
	 * update
	 * 
	 * @param _req
	 * @param _res
	 */
	public void cmdUpdate(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {

		Map param = _req.getKeyValueMap();
		int rcnt = 0;
		param.put("poll_id", _req.getP("poll_id"));
		
		try {
			oDb.setAutoCommit(false);
			
			rcnt = PollDB.mergeOne(super.oDb, param);

			String[] item_ids = _req.getParameterValues("item_ids");
			String seq = null;
			String question = null;
			String del = null;
			if(item_ids != null) {
				for(int i = 0; i < item_ids.length; i ++ ) {
					seq = _req.getP("seq_" + item_ids[i]);
					question = _req.getP("question_" + item_ids[i]);
					del = _req.getP("del_" + item_ids[i]);
					param.put("item_id", item_ids[i]);
					if("Y".equals(del)) {
						PollDB.deleteItemOne(oDb, param);
					} else {
						param.put("seq",seq);
						param.put("question",question);
						PollDB.updateItemOne(oDb, param);
					}
				}
			}
			oDb.commit();
			oDb.setAutoCommit(true);
		} catch( MdbException e) {
			rcnt = 0;
			oDb.rollback();
			logger.error(e.getMessage());
		}
		
		if(rcnt > 0 ) {
			UrlAddress next = new UrlAddress (super.controlAction);
			next.addParam("cmd", "edit");
			next.addParam("poll_id", _req.getP("poll_id"));
			result.setNext(next.getHref());
			result.setSuccess(true, new ResultMessage(this.MESSAGE_BUNDLENAME, "update.ok", new Integer(rcnt)));
		} else {
			result.setSuccess(false,new ResultMessage(this.MESSAGE_BUNDLENAME, "update.fail", new Integer(rcnt)));
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
		
		param.put("poll_id", _req.getP("poll_id"));
			
		int rcnt= PollDB.deleteOne(super.oDb, param);
		if(rcnt > 0 ) {
			result.setNext(super.controlAction);
			result.setSuccess(true,new ResultMessage(this.MESSAGE_BUNDLENAME, "delete.ok", new Integer(rcnt)));
		} else {
			result.setSuccess(false,new ResultMessage(this.MESSAGE_BUNDLENAME, "delete.fail"));
		}
	}		
	
	/**
	 * 하나의 설문 문항을 추가 한다. 
	 * @param _req
	 * @param _res
	 * @throws MafException
	 */
	public void cmdAddItem(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		Map param = _req.getKeyValueMap();
		int rcnt = 0;
		param.put("poll_id", _req.getP("poll_id"));
		param.put("seq", _req.getP("seq"));
		param.put("question", _req.getP("question"));
		try {
			rcnt= PollDB.insertItemOne(super.oDb, param);
		} catch( MdbException e) {
			rcnt = 0;
			logger.error(e.getMessage());
		}
		if(rcnt > 0 ) {
			UrlAddress next = new UrlAddress (super.controlAction);
			next.addParam("cmd", "edit");
			next.addParam("poll_id", _req.getP("poll_id"));
			result.setNext(next.getHref());
			result.setSuccess(true, new ResultMessage(this.MESSAGE_BUNDLENAME, "insert.ok", new Integer(rcnt)));
		} else {
			result.setSuccess(false,new ResultMessage(this.MESSAGE_BUNDLENAME, "insert.fail", new Integer(rcnt)));
		}
	}

    /**
     * poll 결과 보기
     * @param _req
     * @param _res
     * @throws MafException
     */
    public void cmdViewResult(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		Map param = _req.getKeyValueMap();
		
		param.put("poll_id", _req.getP("poll_id"));
		result.setAttribute("poll",PollDB.getOne(oDb, param));
		result.setAttribute("items",PollDB.getPollItems(oDb, param));
		
		result.setForward("result");
    }

}
		