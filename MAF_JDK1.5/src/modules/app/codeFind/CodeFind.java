/*
 * Created on 2006. 06. 14.
 *
 * Copyright (c) 2004 (��)�̷��� All rights reserved.
 */
package modules.app.codeFind;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import maf.MafUtil;
import maf.exception.MafException;
import maf.web.http.MyHttpServletRequest;
import maf.web.mvc.action.MafAction;
import maf.web.util.SerializeHashtable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class CodeFind extends MafAction { 
	private Log logger = LogFactory.getLog(this.getClass());
	SerializeHashtable listOp = null;
    
	public void preProcess(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
	}
	public void postProcess(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
	}	

	/**
	 * ��� ��������
	 * 
	 * @param _req
	 * @param _res
	 */
	public void cmdList(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		
		CodeFindDB dao = CodeFindDB.getInstance();
		//Map param = _req.getParametersMap(); //id, schtype, keyword
		Map param = _req.getKeyValueMap();
		 
		logger.debug("- id : " + param.get("id") + "\n - schtype : " + param.get("schtype") + "\n - keyword : " + param.get("keyword"));

		//search, display, return �׸��� ������
		result.setAttribute("fieldList", dao.getFieldList(super.oDb, param));
		
		if(!MafUtil.empty(param.get("keyword"))) {

			//sql�� ������
			String sql = dao.getSQL(super.oDb, param);
			
			//����� ������
			result.setAttribute("list", dao.getList(super.oDb, param, sql));
		
		}
		
		result.setForward("list");

	}

	/**
	 * ��� ��������(with Tree)
	 * 
	 * @param _req
	 * @param _res
	 */
	public void cmdTree(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
//		logger.debug("START TREE");
		
		CodeFindDB dao = CodeFindDB.getInstance();
		Map param = new HashMap();
		param.put("id", _req.getP("id")); 		

		//display, return �׸��� ������
		result.setAttribute("fieldList", dao.getFieldList(super.oDb, param));
		
		// sql�� ������
		String sql = dao.getSQL(super.oDb, param);

		// ����� ������
		result.setAttribute("tree", dao.getTreeList(super.oDb, sql));	
		
		result.setForward("tree");

	}
	
}
