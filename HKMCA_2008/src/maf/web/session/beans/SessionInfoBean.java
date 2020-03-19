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
 * Session 정보를 가지고 다닌다..
 * 접속자 정보 확인용
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
     * @return httpSession을 리턴합니다.
     */
    public HttpSession getHttpSession() {
        return httpSession;
    }
    
    /**
     * @return loginIP을 리턴합니다.
     */
    public String getLoginIP() {
        return loginIP;
    }
    /**
     * @return nm을 리턴합니다.
     */
    public String getNm() {
        return nm;
    }
    /**
     * @return usn을 리턴합니다.
     */
    public String getUsn() {
        return usn;
    }

    
    /**
     * @return userid을 리턴합니다.
     */
    public String getUserid() {
        return userid;
    }


	public String getSid() {
		return sid;
	}
    
}
