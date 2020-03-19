/*
 * Copyright (c) 2009 UBQ All rights reserved.
 */
package modules.member;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import maf.exception.MafException;
import maf.web.http.MyHttpServletRequest;
import maf.web.http.UrlAddress;
import maf.web.mvc.action.MafAction;
import maf.web.mvc.beans.ResultMessage;
import modules.xadmin.member.MemberDB;

/**
 * @author UBQ
 *  
 */
public class Userid extends MafAction {

	String form = null;
	public void preProcess(MyHttpServletRequest _req, HttpServletResponse _res)
	throws MafException {
		this.form = _req.getP("form","form");
		_req.setAttribute("form", form);
	}

	/**
	 * default page
	 * @param _req
	 * @param _res
	 * @throws MafException
	 */
	public void cmdIdUniq(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {

			result.setForward("iduniq");
	}
	
	/**
	 * default page
	 * @param _req
	 * @param _res
	 * @throws MafException
	 */
	public void cmdIdChk(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		Map param = new HashMap();
		
			param.put("userid", _req.getP("suserid"));
			int chk = MemberDB.getchkId(oDb, param);
			
			if (chk > 0){
				UrlAddress next = new UrlAddress(super.controlAction+"?cmd=iduniq");
				next.addParam("form", form);
				result.setNext(next);
				result.setSuccess(true, new ResultMessage("common.message","id.use"));
	        } else {
				UrlAddress next = new UrlAddress(super.controlAction+"?cmd=iduse");
				next.addParam("form", form);
				next.addParam("userid", _req.getP("suserid"));
				result.setNext(next);
				result.setSuccess(true, new ResultMessage("common.message","id.notuse"));
			}
	}
	
	/**
	 * default page
	 * @param _req
	 * @param _res
	 * @throws MafException
	 */
	public void cmdIdUse(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
        result.setAttribute("userid", _req.getP("userid"));            
		result.setForward("iduse");
	}
	
	/**
	 * 메뉴 Tree 보여주기 
	 * @param _req
	 * @param _res
	 * @throws MafException
	 */
	public void cmdFindId(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		
        result.setForward("findid");
	}	
	
	/**
	 * default page
	 * @param _req
	 * @param _res
	 * @throws MafException
	 */
	public void cmdRstId(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
		Map param = new HashMap();
		
		param.put("pin", _req.getP("pin1")+"-"+_req.getP("pin2"));
		param.put("nm", _req.getP("nm"));
        result.setAttribute("item", MemberDB.getFindid(oDb, param));
		result.setForward("resultid");
	}
	
	/**
	 * 메뉴 수정 폼 
	 * @param _req
	 * @param _res
	 * @throws MafException
	 */
	public void cmdFindPwd(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {
        result.setForward("findpwd");
	}	
	
}
