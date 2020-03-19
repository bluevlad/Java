/*
 * Created on 2006. 5. 18.
 *
 * Copyright (c) 2004 UBQ All rights reserved.
 */
package maf.base;

import java.util.HashMap;
import java.util.Map;

public  class BaseCookies extends BaseObject {

	private static final long serialVersionUID = 1L;
	
	//	private Log logger = LogFactory.getLog(BaseHttpSession.class);
	private String loginIP = null;
	private String usn = null;
	private String usernm = null;
	private String userid = null;
	private String lang = null;
	private String site = null;
	
	private Map attributeMap = null;
	
	public BaseCookies() {
	}
	
	public void addAttribute(String key, Object value) {
		if(this.attributeMap == null) {
			this.attributeMap = new HashMap();
		} 
		this.attributeMap.put(key, value);
	}
	
	public Object getAttribute(String key) {
		if (this.attributeMap != null) {
			return this.attributeMap.get(key);
		} else {
			return null;
		}
	}

	/**
	 * @return Returns the loginIP.
	 */
	public String getLoginIP() {
		return loginIP;
	}
	/**
	 * @param loginIP The loginIP to set.
	 */
	public void setLoginIP(String loginIP) {
		this.loginIP = loginIP;
	}
	/**
	 * @return Returns the usn.
	 */
	public String getUsn() {
		return usn;
	}
	/**
	 * @param usn The usn to set.
	 */
	public void setUsn(String usn) {
		this.usn = usn;
	}
	/**
	 * @return Returns the usernm.
	 */
	public String getUserNm() {
		return usernm;
	}
	/**
	 * @param usernm The usernm to set.
	 */
	public void setUserNm(String usernm) {
		this.usernm = usernm;
	}
	/**
	 * @return Returns the userid.
	 */
	public String getUserid() {
		return userid;
	}
	/**
	 * @param userid The userid to set.
	 */
	public void setUserid(String userid) {
		this.userid = userid;
	}
	/**
	 * @return Returns the userid.
	 */
	public String getLang() {
		return lang;
	}
	/**
	 * @param userid The userid to set.
	 */
	public void setLang(String lang) {
		this.lang = lang;
	}
	/**
	 * @return Returns the userid.
	 */
	public String getSite() {
		return site;
	}
	/**
	 * @param userid The userid to set.
	 */
	public void setSite(String site) {
		this.site = site;
	}
	
}