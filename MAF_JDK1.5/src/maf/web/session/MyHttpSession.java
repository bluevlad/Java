/*
 * @(#) MySession.java 1.0, 2004. 10. 1.
 * 
 * Copyright (c) 2004 (��)�̷��� All rights reserved.
 */
package maf.web.session;

import java.util.Enumeration;

import javax.servlet.ServletContext;

/**
 * File Name : MyHttpSession.java
 * ���� session�� �����ϸ� ����session ����
 * �������� ������ ���λ����� session����
 * 
 * @author ����ö
 * @version 1.0
 * @modifydate 2004. 10. 1.
 * 
 */
public interface MyHttpSession {
	
    /**
     * ���������� Cookie Name
     */
    public static final String COOKIE_NAME = "MJSESSIONID";
    /**
     * session bean Key !! JSTL���� ��� �ϹǷ� ���� ����.!!!
     */
    public static final String HTTP_SESSION_KEY = "msession";
    
    public long getCreationTime();
    
    public String getId();

    /**
     * ������ ó�� ������ �ð��� �и� �ʷ� ����Ͽ� 
     * long�� ������ �����մϴ�. 
     * ������ 70��1��1�� 00�� 00�� 00���Դϴ�.
     */
    public long getLastAccessedTime();

    public ServletContext getServletContext();

    /**
     * ���� �ð��� �����մϴ�. 
     * �׸��� �� �ð��� ������ �翬�� ������ �����ǰ���
     * (�и�����������Դϴ�)
     */
    public void setMaxInactiveInterval(int arg0);

    public int getMaxInactiveInterval();

    public Object getAttribute(String name);

    public Enumeration getAttributeNames();

    public void setAttribute(String key, Object value);

    public void removeAttribute(String name);
	/**
	 * ������ ������ ������ �����ϴ�. 
	 * ������ �Ӽ������� ������°���
	 *
	 */
    public void invalidate();

    /**
     * ���������� ���ο� session��ü�� �����ϰ� 
     * ���� Ŭ���̾�Ʈ���� ����ID�� �Ҵ����� ���� ��� true�� �����ϰ� 
     * ������ ������ �����ǰ� �ִ� ���¶�� false�� ��ȯ�մϴ�.
     */
    public boolean isNew();
}
