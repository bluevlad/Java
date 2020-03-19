/*
 * @(#) SessionConstructor.java 1.0, 2004. 10. 1.
 * 
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package maf.web.session;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import maf.MafProperties;
import maf.base.BaseHttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * File Name : SessionConstructor.java <br>
 * 
 * @author 김윤철
 * @version 1.0
 * @modifydate 2004. 10. 1.
 */
public class MySession {

	private static Log logger = LogFactory.getLog(MySession.class);

	/**
	 * Session의 값을 리턴한다. Session에 사용자 정보를 만들시에는 setSession을 바로 호출한다.
	 * 
	 * @param req
	 * @param res
	 * @return
	 */
	public static MyHttpSession getSession(HttpServletRequest req, HttpServletResponse res) {
		return new BySession(req, res);
	}

	/**
	 * 
	 * 
	 * @param req
	 * @param res
	 * @param userID
	 * @return
	 */
	public static MyHttpSession setSession(HttpServletRequest req, HttpServletResponse res, BaseHttpSession sessionBean) {
		MyHttpSession mySession = null;
		try {

			mySession = new BySession(req, res);
			mySession.setAttribute(MyHttpSession.HTTP_SESSION_KEY, sessionBean);

			mySession.setMaxInactiveInterval(MafProperties.SESSION_TIMEOUT * 60);

		} catch (Exception e) {
			logger.error("SetSession Error!!!");
		}
		return mySession;
	}

	/**
	 * Session에서 bean을 삭제
	 * 
	 * @param req
	 * @param res
	 */
	public static void removeSession(HttpServletRequest req, HttpServletResponse res) {
		MyHttpSession mySession = null;
		try {
			mySession = new BySession(req, res);
			mySession.removeAttribute(MyHttpSession.HTTP_SESSION_KEY);
		} catch (Exception e) {
			logger.error("SetSession Error!!!");
		}
	}

	/**
	 * 현재 Session 정보를 돌려 준다.
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	public static BaseHttpSession getSessionBean(HttpServletRequest request, HttpServletResponse response) {
		MyHttpSession mySession = MySession.getSession(request, response);
		BaseHttpSession ssBean = (BaseHttpSession) mySession.getAttribute(MyHttpSession.HTTP_SESSION_KEY);
		if(ssBean != null) {
			
			if(MafProperties.SESSION_POOL_YN) {
		    	boolean chk = SessionPool.isValidSession(mySession);
		    	if ( !chk ) {
		    		mySession.removeAttribute(MyHttpSession.HTTP_SESSION_KEY);
		    		ssBean = null;
		    	}
			}
    	}
		return ssBean;
	}

}