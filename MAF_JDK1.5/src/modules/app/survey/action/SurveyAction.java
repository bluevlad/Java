/*
 * Created on 2006. 06. 14.
 *
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package modules.app.survey.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import maf.MafConstant;
import maf.beans.NavigatorInfo;
import maf.exception.MafException;
import maf.web.http.MyHttpServletRequest;
import maf.web.mvc.action.MafAction;
import maf.web.util.SerializeHashtable;
import modules.app.survey.dao.SurbankansDB;
import modules.app.survey.dao.SurveyDB;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SurveyAction extends MafAction {
	private Log logger = LogFactory.getLog(this.getClass());
	SerializeHashtable listOp = null;
    
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
		
		final String[] searchFields = {"title"};

		SurveyDB dao = SurveyDB.getInstance();
		NavigatorInfo navigator = new NavigatorInfo(_req, this.listOp);
		Map param = new HashMap();
		
		for(int i = 0 ;i < searchFields.length; i++) {
			if (_req.containsKey(searchFields[i])) {
				param.put(searchFields[i], _req.getParameter(searchFields[i]));
				listOp.put(searchFields[i], _req.getParameter(searchFields[i]));
			} else {
				param.put(searchFields[i], listOp.get(searchFields[i]));
			}
		}
		dao.getList(super.oDb, navigator, param, searchFields);
		result.setAttribute("navigator", navigator);
		result.setForward("list");
	}
	
	/**
	 * view
	 * 
	 * @param _req
	 * @param _res
	 */
	public void cmdView(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		SurveyDB dao = SurveyDB.getInstance();
		SurbankansDB dao_ans = SurbankansDB.getInstance();
		NavigatorInfo navigator = new NavigatorInfo(_req, this.listOp);
		Map param = new HashMap();
		param.put("bank_id", _req.getP("id"));
		param.put("gubun", _req.getP("gubun"));
		param.put("comp_id", _req.getP("comp_id"));
		result.setAttribute("item", dao.getOne(super.oDb, param));
		result.setAttribute("item_ans", dao_ans.getOne(super.oDb, param));
		result.setForward("view");
	}
	
	/**
	 * edit
	 * 
	 * @param _req
	 * @param _res
	 */
	public void cmdEdit(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		SurveyDB dao = SurveyDB.getInstance();
		SurbankansDB dao_ans = SurbankansDB.getInstance();
		Map param = new HashMap();
		param.put("bank_id", _req.getP("id"));
		param.put("gubun", _req.getP("gubun"));
		param.put("comp_id", _req.getP("comp_id"));
		result.setAttribute("item", dao.getOne(super.oDb, param));
		result.setAttribute("item_ans", dao_ans.getOne(super.oDb, param));
		result.setForward("edit");
	}
	
	/**
	 * update
	 * 
	 * @param _req
	 * @param _res
	 */
	public void cmdUpdate(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		
		final String[] searchFields = {"title"};
		
		SurveyDB dao = SurveyDB.getInstance();
		SurbankansDB dao_ans = SurbankansDB.getInstance();
		Map param = _req.getKeyValueMap();
		NavigatorInfo navigator = new NavigatorInfo(_req, this.listOp);
		param.put("bank_id", _req.getP("bank_id"));
		param.put("gubun", _req.getP("gubun"));
		param.put("comp_id", _req.getP("comp_id"));
		
		String[] ls_reply = _req.getParameterValues("reply");
		String[] ls_score = _req.getParameterValues("score");
		
		int rcnt= dao.updateOne(super.oDb, param);
		rcnt= dao_ans.deleteAll(super.oDb,param);
		/*
		logger.debug("bank_id : " + param.get("bank_id") );
		logger.debug("gubun : " + param.get("gubun") );		 
		logger.debug("comp_id : " + param.get("comp_id") );
		*/
		if ("1".equals(param.get("que_type"))){
			for (int i = 0; i < ls_reply.length ; i++) {
				rcnt= dao_ans.InsertOne(super.oDb,param,i, ls_reply[i],ls_score[i]);
			}
		}else if("2".equals(param.get("que_type"))){
			for (int i = 0; i < ls_reply.length ; i++) {
				rcnt= dao_ans.InsertOne(super.oDb,param,i, ls_reply[i],ls_score[i]);
			}
		}else{
			logger.debug("param : " + param );
			rcnt= dao_ans.InsertOne(super.oDb,param,1, "","");
		}
		
		dao.getList(super.oDb, navigator, param, searchFields);
		
		result.setAttribute("navigator", navigator);
		result.setForward("list");
	}
	
	/**
	 * insert
	 * 
	 * @param _req
	 * @param _res
	 */
	public void cmdInsert(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		
		final String[] searchFields = {"title"};
		
		SurveyDB dao = SurveyDB.getInstance();
		SurbankansDB dao_ans = SurbankansDB.getInstance();
		Map param = _req.getKeyValueMap();
		String[] ls_reply = _req.getParameterValues("reply");
		String[] ls_score = _req.getParameterValues("score");
		
		NavigatorInfo navigator = new NavigatorInfo(_req, this.listOp);
		
		param.put("bank_id",""+dao.getSeq(super.oDb));
		
		int rcnt= dao.InsertOne(super.oDb, param );
		
		if ("1".equals(param.get("que_type"))){
			for (int i = 0; i < ls_reply.length ; i++) {
				rcnt= dao_ans.InsertOne(super.oDb,param,i, ls_reply[i],ls_score[i]);
			}
		}else if("2".equals(param.get("que_type"))){
			for (int i = 0; i < ls_reply.length ; i++) {
				rcnt= dao_ans.InsertOne(super.oDb,param,i, ls_reply[i],ls_score[i]);
			}
		}else{
			rcnt= dao_ans.InsertOne(super.oDb,param,1, "","");
		}
		
		dao.getList(super.oDb, navigator, param, searchFields);
		
		result.setAttribute("navigator", navigator);
		result.setForward("list");
	}
	
	/**
	 * insert
	 * 
	 * @param _req
	 * @param _res
	 */
	public void cmdadd(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		
		final String[] searchFields = {"title"};
		
		Map param = _req.getKeyValueMap();
		NavigatorInfo navigator = new NavigatorInfo(_req, this.listOp);
		
		result.setAttribute("navigator", navigator);
		result.setForward("add");
	}
	

}
