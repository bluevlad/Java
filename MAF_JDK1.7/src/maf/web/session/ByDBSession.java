/*
 * Created on 2005. 8. 10.
 *
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package maf.web.session;

import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 * @author goindole
 *
 * 2005.08.10 현재 작업중
 *
 */
public class ByDBSession implements MyHttpSession {

	private HttpSession session;
	
	
	public ByDBSession (HttpServletRequest req, HttpServletResponse res) {
		this.session = req.getSession();
	}

	/* (non-Javadoc)
	 * @see com.miraenet.servlet.MyHttpSession#getCreationTime()
	 */
	public long getCreationTime() {
		
		return session.getCreationTime();
	}

	/* (non-Javadoc)
	 * @see com.miraenet.servlet.MyHttpSession#getId()
	 */
	public String getId() {
		
		return session.getId();
	}

	/* (non-Javadoc)
	 * @see com.miraenet.servlet.MyHttpSession#getLastAccessedTime()
	 */
	public long getLastAccessedTime() {
		
		return session.getLastAccessedTime();
	}

	/* (non-Javadoc)
	 * @see com.miraenet.servlet.MyHttpSession#getServletContext()
	 */
	public ServletContext getServletContext() {
		
		return session.getServletContext();
	}

	/* (non-Javadoc)
	 * @see com.miraenet.servlet.MyHttpSession#setMaxInactiveInterval(int)
	 */
	public void setMaxInactiveInterval(int arg0) {
		session.setMaxInactiveInterval(arg0);
	}

	/* (non-Javadoc)
	 * @see com.miraenet.servlet.MyHttpSession#getMaxInactiveInterval()
	 */
	public int getMaxInactiveInterval() {		
		return session.getMaxInactiveInterval();
	}

	/* (non-Javadoc)
	 * @see com.miraenet.servlet.MyHttpSession#getAttribute(java.lang.String)
	 */
	public Object getAttribute(String name) {
		return session.getAttribute(name); 
	}

	/* (non-Javadoc)
	 * @see com.miraenet.servlet.MyHttpSession#getAttributeNames()
	 */
	public Enumeration getAttributeNames() {		
		return session.getAttributeNames();
	}

	/* (non-Javadoc)
	 * @see com.miraenet.servlet.MyHttpSession#setAttribute(java.lang.String, java.lang.Object)
	 */
	public void setAttribute(String key, Object value) {
		session.setAttribute(key, value);
	}

	/* (non-Javadoc)
	 * @see com.miraenet.servlet.MyHttpSession#removeAttribute(java.lang.String)
	 */
	public void removeAttribute(String name) {
		session.removeAttribute(name);
	}

	/* (non-Javadoc)
	 * @see com.miraenet.servlet.MyHttpSession#invalidate()
	 */
	public void invalidate() {
		session.invalidate();
//		Cookie c = new Cookie(COOKIE_NAME, "");
//		c.setMaxAge(-1);
//		c.setPath("/");
//		this.res.addCookie(c);
	}

	/* (non-Javadoc)
	 * @see com.miraenet.servlet.MyHttpSession#isNew()
	 */
	public boolean isNew() {		
		return session.isNew();
	}


}

