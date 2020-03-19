/*
 * SessionInfoBean.java, @ 2005-03-11
 * 
 * Copyright (c) 2004 UBQ All rights reserved.
 */
package maf.web.session.beans;

import javax.servlet.http.HttpSession;

import maf.base.BaseHttpSession;

/**
 * @author G2BKorea
 * Session ������ ������ �ٴѴ�..
 * ������ ���� Ȯ�ο�
 */
public class SessionInfoBean {

    private HttpSession httpSession = null;
    private String loginIP = null;
    private String usn = null;
    private String nm = null;
    private String sid = null;
    
    private String userid = null;
    
    public SessionInfoBean(HttpSession hs) {
        this.httpSession = hs;

    }
    
    public SessionInfoBean( HttpSession  hs, BaseHttpSession bhs  ) {
        this.httpSession = hs;
        this.loginIP = bhs.getLoginIP();
        this.usn = bhs.getUsn();
        this.userid = bhs.getUserid();
        this.nm = bhs.getNm();
        this.sid = hs.getId();

    }

    
    /**
     * @return httpSession�� �����մϴ�.
     */
    public HttpSession getHttpSession() {
        return httpSession;
    }
    
    /**
     * @return loginIP�� �����մϴ�.
     */
    public String getLoginIP() {
        return loginIP;
    }
    /**
     * @return nm�� �����մϴ�.
     */
    public String getNm() {
        return nm;
    }
    /**
     * @return usn�� �����մϴ�.
     */
    public String getUsn() {
        return usn;
    }

    
    /**
     * @return userid�� �����մϴ�.
     */
    public String getUserid() {
        return userid;
    }


	public String getSid() {
		return sid;
	}
    
}
