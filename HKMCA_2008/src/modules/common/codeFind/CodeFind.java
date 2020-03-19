/*
 * Created on 2006. 06. 14.
 *
 * Copyright (c) 2004 UBQ All rights reserved.
 */
package modules.common.codeFind;

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
	 * 목록 가져오기
	 * 
	 * @param _req
	 * @param _res
	 */
	public void cmdList(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		
		Map param = _req.getKeyValueMap();
		 
		logger.debug("- id : " + param.get("id") + "\n - schtype : " + param.get("schtype") + "\n - keyword : " + param.get("keyword"));

		//search, display, return 항목을 가져옴
		result.setAttribute("fieldList", CodeFindDB.getFieldList(super.oDb, param));
		
		if(!MafUtil.empty(param.get("keyword"))) {

			//sql을 가져옴
			String sql = CodeFindDB.getSQL(super.oDb, param);
			
			//목록을 가져옴
			result.setAttribute("list", CodeFindDB.getList(super.oDb, param, sql));
		
		}
		
		result.setForward("list");

	}

	/**
	 * 목록 가져오기(with Tree)
	 * 
	 * @param _req
	 * @param _res
	 */
	public void cmdTree(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		
		Map param = new HashMap();
		param.put("id", _req.getP("id")); 		

		//display, return 항목을 가져옴
		result.setAttribute("fieldList", CodeFindDB.getFieldList(super.oDb, param));
		
		// sql을 가져옴
		String sql = CodeFindDB.getSQL(super.oDb, param);

		// 목록을 가져옴
		result.setAttribute("tree", CodeFindDB.getTreeList(super.oDb, sql));	
		
		result.setForward("tree");

	}
	
}
