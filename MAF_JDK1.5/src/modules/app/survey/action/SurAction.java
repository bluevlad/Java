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
import maf.MafUtil;
import maf.beans.NavigatorInfo;
import maf.exception.MafException;
import maf.web.http.MyHttpServletRequest;
import maf.web.mvc.action.MafAction;
import maf.web.util.SerializeHashtable;
import modules.app.survey.dao.SurAnsDB;
import modules.app.survey.dao.SurQuestionDB;
import modules.app.survey.dao.SurbankansDB;
import modules.app.survey.dao.SursurveyDB;
import modules.app.survey.dao.SurveyDB;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SurAction extends MafAction {
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

		SursurveyDB dao = SursurveyDB.getInstance();
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
		SursurveyDB dao =SursurveyDB.getInstance();
		SurQuestionDB dao_que =SurQuestionDB.getInstance();
		Map param = new HashMap();
		param.put("gubun", _req.getP("gubun"));
		param.put("survey_id", _req.getP("survey_id"));
		param.put("comp_id", _req.getP("comp_id"));
		
		result.setAttribute("item", dao.getOne(super.oDb, param));
		result.setAttribute("quelist", dao_que.getOne(super.oDb, param));
		result.setForward("view");
	}
	
	
	/**
	 * view
	 * 
	 * @param _req
	 * @param _res
	 */
	public void cmdPreview(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		SursurveyDB dao =SursurveyDB.getInstance();
		SurQuestionDB dao_que =SurQuestionDB.getInstance();
		Map param = new HashMap();
		param.put("gubun", _req.getParameter("gubun"));
		param.put("survey_id", _req.getParameter("survey_id"));
		param.put("comp_id", _req.getParameter("comp_id"));
		logger.debug("comp_id : " + param.get("comp_id"));		
		result.setAttribute("item", dao.getOne(super.oDb, param));
		result.setAttribute("quelist", dao_que.getOne(super.oDb, param));
		result.setForward("preview");
	}
	
	/**
	 * edit
	 * 
	 * @param _req
	 * @param _res
	 */
	public void cmdEdit(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		SursurveyDB dao = SursurveyDB.getInstance();
		SurQuestionDB dao_que =SurQuestionDB.getInstance();
		Map param = new HashMap();
		
		param.put("gubun", _req.getP("gubun"));
		param.put("survey_id", _req.getP("survey_id"));
		param.put("comp_id", _req.getP("comp_id"));
		result.setAttribute("item", dao.getOne(super.oDb, param));
		result.setAttribute("quelist", dao_que.getOne(super.oDb, param));
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
		
		SursurveyDB dao = SursurveyDB.getInstance();
		SurQuestionDB dao_que = SurQuestionDB.getInstance();
		
		SurveyDB dao_bank = SurveyDB.getInstance();
		SurbankansDB dao_bank_ans = SurbankansDB.getInstance();
		
		SurAnsDB dao_ans = SurAnsDB.getInstance();
		Map param = _req.getKeyValueMap();
		
		NavigatorInfo navigator = new NavigatorInfo(_req, this.listOp);
		
		param.put("gubun", _req.getP("gubun"));
		param.put("survey_id", _req.getP("survey_id"));
		param.put("comp_id", _req.getP("comp_id"));
		int rcnt = 0;
		if ("".equals(param.get("survey_id") + "")){
			param.put("survey_id",""+dao.getSeq(super.oDb));
			//logger.debug("survey_id : " + param.get("survey_id"));
			rcnt= dao.InsertOne(super.oDb, param);
		}else{
			rcnt= dao.updateOne(super.oDb, param);
		}
		//질문 저장
		
		String[] ls_question = _req.getParameterValues("question");
		String[] ls_que_type = _req.getParameterValues("que_type");
		String[] ls_etc_yn = _req.getParameterValues("etc_yn");
		String[] ls_comment_yn = _req.getParameterValues("comment_yn");
		String[] ls_bank_id = _req.getParameterValues("bank_id");
		
		rcnt= dao_ans.deleteAll(super.oDb,param);
		rcnt= dao_que.deleteAll(super.oDb,param);
		
		if ( !MafUtil.empty(ls_question) ){
			
			for (int i = 0; i < ls_question.length ; i++) {
				//logger.debug("ls_bank_id : " + ls_bank_id[i]);
				if ("".equals(ls_bank_id[i])) {
					param.put("bank_id",""+dao_bank.getSeq(super.oDb));
					ls_bank_id[i] = (String) param.get("bank_id") ;
					param.put("title",ls_question[i]);
					// String ls_title, String ls_question, String ls_etc_yn, String ls_comment,String ls_quetype
					rcnt= dao_bank.InsertBankOne(super.oDb, param,ls_question[i],ls_question[i],ls_etc_yn[i],ls_comment_yn[i],ls_que_type[i] );
					
					if ("3".equals(ls_que_type[i]) || "4".equals(ls_que_type[i])) {
						rcnt = dao_bank_ans.InsertOne(super.oDb,param,1,"","");
					}else{
						String[] ls_reply = _req.getParameterValues("reply_" + (i+1));
						String[] ls_score = _req.getParameterValues("score_" + (i+1));
						if ( !MafUtil.empty(ls_reply) ){
							for (int j = 0; j < ls_reply.length ; j++) {
								rcnt = dao_bank_ans.InsertOne(super.oDb,param,j,ls_reply[j],ls_score[j]);
							}
						}
					}
				}
				
				rcnt= dao_que.InsertOne(super.oDb,param,i + 1, ls_question[i],ls_que_type[i],ls_etc_yn[i],ls_comment_yn[i],ls_bank_id[i]);
				//답변저장
				if ("3".equals(ls_que_type[i]) || "4".equals(ls_que_type[i])) {
					rcnt = dao_ans.InsertOne(super.oDb,param,"" + (i + 1),"1","","");
				}else{
					String[] ls_reply = _req.getParameterValues("reply_" + (i+1));
					String[] ls_score = _req.getParameterValues("score_" + (i+1));
					if ( !MafUtil.empty(ls_reply) ){
						for (int j = 0; j < ls_reply.length ; j++) {
							rcnt = dao_ans.InsertOne(super.oDb,param,"" + (i + 1),""+(j+1) ,ls_reply[j],ls_score[j]);
						}
					}
				}
			}
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
		
		SursurveyDB dao = SursurveyDB.getInstance();
		Map param = _req.getKeyValueMap();
		NavigatorInfo navigator = new NavigatorInfo(_req, this.listOp);
		param.put("survey_id",""+dao.getSeq(super.oDb));
		
		int rcnt= dao.InsertOne(super.oDb, param);
		
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
	
	/**
	 * Pop
	 * 
	 * @param _req
	 * @param _res
	 */
	public void cmdPop(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		
		final String[] searchFields = {"title","gubun"};

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
		dao.getBankList(super.oDb, navigator, param, searchFields);
		result.setAttribute("navigator", navigator);
		result.setForward("pop"); 
	}

} 
			