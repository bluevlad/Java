/*
 * Created on 2006. 09. 27
 *
 * Copyright (c) 2004 UBQ All rights reserved.
 */
package miraenet.app.webstat;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;

import maf.MafConstant;
import maf.exception.MafException;
import maf.web.http.MyHttpServletRequest;
import maf.web.mvc.action.MafAction;
import maf.web.util.SerializeHashtable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MemberStat extends MafAction {
	private Log logger = LogFactory.getLog(MemberStat.class);
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
	 * 나이별 회원수 
	 * @param _req
	 * @param _res
	 * @throws MafException
	 */
	public void cmdAge(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {

        Map param = new HashMap();

		result.setAttribute("list", MemberStatDB.getAge(super.oDb, param));
		result.setAttribute("src_type", "age");
		result.setForward("age");
	}

	/**
	 * 성별 회원수 
	 * @param _req
	 * @param _res
	 * @throws MafException
	 */
	public void cmdSex(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {

        Map param = new HashMap();

		result.setAttribute("list", MemberStatDB.getSex(super.oDb, param));
		result.setAttribute("src_type", "sex");
		result.setForward("sex");
	}

	/**
	 * 지역별 회원수 
	 * @param _req
	 * @param _res
	 * @throws MafException
	 */
	public void cmdAddr(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {

        Map param = new HashMap();

		result.setAttribute("list", MemberStatDB.getAddr(super.oDb, param));
		result.setAttribute("src_type", "addr");
		result.setForward("addr");
	}

	/**
	 * 나라별 접속현황
	 * @param _req
	 * @param _res
	 * @throws MafException
	 */
	public void cmdGeoip(MyHttpServletRequest _req, HttpServletResponse _res) throws MafException {

        Map param = new HashMap();

		result.setAttribute("list", MemberStatDB.getGeoip(super.oDb, param));
		result.setForward("geoip");
	}
}