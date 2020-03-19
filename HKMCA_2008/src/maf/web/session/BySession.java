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
 * @author ����ö
 * @version 1.0
 * @modifydate 2004. 10. 1.
 */
public class BySession implements MyHttpSession {
	private HttpSession session;
	
	/**
	 * ���� session�� �����ϸ� ����session ����
	 * �������� ������ ���λ����� session����
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
     * ������ ó�� ������ �ð��� �и� �ʷ� ����Ͽ� 
     * long�� ������ �����մϴ�. 
     * ������ 70��1��1�� 00�� 00�� 00���Դϴ�.
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
     * ���� �ð��� �����մϴ�. 
     * �׸��� �� �ð��� ������ �翬�� ������ �����ǰ���
     * (�и�����������Դϴ�)
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
	 * ������ ������ ������ �����ϴ�. 
	 * ������ �Ӽ������� ������°���
	 * 
	 */
	public void invalidate() {
		this.session.invalidate();

	}

	/* ���������� ���ο� session��ü�� �����ϰ�
	 * ���� Ŭ���̾�Ʈ���� ����ID�� �Ҵ����� ���� ��� true�� 
	 * �����ϰ� ������ ������ �����ǰ� �ִ� ���¶�� false�� ��ȯ�մϴ�.
	 */
	public boolean isNew() {		
		return this.session.isNew();
	}


	
}
