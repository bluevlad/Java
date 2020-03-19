/*
 * Created on 2006. 09. 27
 *
 * Copyright (c) 2004 UBQ All rights reserved.
 */
package miraenet.app.webstat;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;



import maf.MafProperties;
import maf.MafUtil;
import maf.exception.MafException;
import maf.web.http.MyHttpServletRequest;
import maf.web.mvc.action.MafAction;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class WebStat extends MafAction {
	private Log logger = LogFactory.getLog(WebStat.class);
	String site = null;

	public void preProcess(MyHttpServletRequest _req, HttpServletResponse _res)
	throws MafException {
		super.chkLogin();
		this.site = _req.getP( "site", MafProperties.DEFAULT_SITE );
		_req.setAttribute( "site", site );
		
	}

	/**
	 * 일자별 사용자수 
	 * @param _req
	 * @param _res
	 * @throws MafException
	 */
	public void cmdDefault(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		

		Map param = new HashMap();
		Calendar cal = Calendar.getInstance();
		String yyyy = _req.getP("yyyy",cal.get(Calendar.YEAR)+"");
		String mm = _req.getP("mm");
		if(MafUtil.empty(mm)) {
			mm = (cal.get(Calendar.MONTH)+1)+"";
		}
		if(mm.length()<2) {
			mm = "0" + mm;
		}
		param.put("yyyy", yyyy);
		param.put("mm", mm);
		_req.setAttribute("yyyy", yyyy);
		_req.setAttribute("mm", mm);
		_req.setAttribute("list", WebStatDB.getLogInDaily(super.oDb, param));
		result.setForward("default");
	}
	
	/**
	 * 일자별 사용자수 
	 * @param _req
	 * @param _res
	 * @throws MafException
	 */
	public void cmdByOrgType(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		

		Map param = new HashMap();
		Calendar cal = Calendar.getInstance();
		String yyyy = _req.getP("yyyy",cal.get(Calendar.YEAR)+"");
		String mm = _req.getP("mm");
		if(MafUtil.empty(mm)) {
			mm = (cal.get(Calendar.MONTH)+1)+"";
		}
		if(mm.length()<2) {
			mm = "0" + mm;
		}
		param.put("yyyy", yyyy);
		param.put("mm", mm);
		_req.setAttribute("yyyy", yyyy);
		_req.setAttribute("mm", mm);
		_req.setAttribute("list", WebStatDB.getLogInDaily(super.oDb, param));
		result.setForward("byOrgType");
	}
	

	
}

