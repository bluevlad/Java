/*
 * @(#) MySession.java 1.0, 2004. 10. 1.
 * 
 * Copyright (c) 2004 UBQ All rights reserved.
 */
package maf.web.session;

import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * File Name : MySession.java
 * <br>
 * 
 * @author 김윤철
 * @version 1.0
 * @modifydate 2004. 10. 1.
 */
public class BySession implements MyHttpSession {
	private HttpSession session;
	
	/**
	 * 현재 session이 존재하면 기존session 리턴
	 * 존재하지 않으면 새로생성한 session리턴
	 * @param req
	 * @param res
	 */
	public BySession (HttpServletRequest req, HttpServletResponse res) {
		this.session = req.getSession();

	}


	public long getCreationTime() {
		
		return this.session.getCreationTime();
	}


	public String getId() {
		
		return this.session.getId();
	}

    /**
     * 세션이 처음 생성된 시간을 밀리 초로 계산하여 
     * long형 정수로 리턴합니다. 
     * 기준은 70년1월1일 00시 00분 00초입니다.
     */
	public long getLastAccessedTime() {
		
		return this.session.getLastAccessedTime();
	}

	/* (non-Javadoc)
	 * @see com.miraenet.servlet.MyHttpSession#getServletContext()
	 */
	public ServletContext getServletContext() {
		
		return this.session.getServletContext();
	}

	/* (non-Javadoc)
	 * @see com.miraenet.servlet.MyHttpSession#setMaxInactiveInterval(int)
	 */
	public void setMaxInactiveInterval(int arg0) {
		this.session.setMaxInactiveInterval(arg0);
	}

    /**
     * 세션 시간을 설정합니다. 
     * 그리고 이 시간이 지나면 당연히 세션은 마감되겠죠
     * (밀리세컨드단위입니다)
     */
	public int getMaxInactiveInterval() {		
		return this.session.getMaxInactiveInterval();
	}

	/* (non-Javadoc)
	 * @see com.miraenet.servlet.MyHttpSession#getAttribute(java.lang.String)
	 */
	public Object getAttribute(String name) {
		return this.session.getAttribute(name); 
	}

	/* (non-Javadoc)
	 * @see com.miraenet.servlet.MyHttpSession#getAttributeNames()
	 */
	public Enumeration getAttributeNames() {		
		return this.session.getAttributeNames();
	}

	/* (non-Javadoc)
	 * @see com.miraenet.servlet.MyHttpSession#setAttribute(java.lang.String, java.lang.Object)
	 */
	public void setAttribute(String key, Object value) {
		this.session.setAttribute(key, value);
	}

	/* (non-Javadoc)
	 * @see com.miraenet.servlet.MyHttpSession#removeAttribute(java.lang.String)
	 */
	public void removeAttribute(String name) {
		this.session.removeAttribute(name);
	}

	/* 
	 * 현재의 세션을 마감해 버립니다. 
	 * 세션의 속성값들이 사라지는거죠
	 * 
	 */
	public void invalidate() {
		this.session.invalidate();

	}

	/* 서버측에서 새로운 session객체를 생성하고
	 * 아직 클라이언트에게 세션ID를 할당하지 않은 경우 true를 
	 * 리턴하고 기존의 세션이 유지되고 있는 상태라면 false를 반환합니다.
	 */
	public boolean isNew() {		
		return this.session.isNew();
	}


	
}
