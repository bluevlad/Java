/*
 * Created on 2006. 5. 18.
 *
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package maf.base;

import java.util.HashMap;
import java.util.Map;



public  class BaseHttpSession extends BaseObject {
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//	private Log logger = LogFactory.getLog(BaseHttpSession.class);
	private String sessionid = null;
	private String loginIP = null;
	private String usn = null;
	private String nm = null;
	private String userid = null;
	private String lang = null;
	
	private Map attributeMap = null;
	
	public BaseHttpSession() {
		
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
//	private Map siteRoleMap = null;
	UserRoles userRole = null;
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
	 * @return Returns the nm.
	 */
	public String getNm() {
		return nm;
	}
	/**
	 * @param nm The nm to set.
	 */
	public void setNm(String nm) {
		this.nm = nm;
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
	 * @return Returns the sessionID.
	 */
	public String getSessionid() {
		return sessionid;
	}
	
	public void addRole( String roleid) {

		
		if (userRole == null) {
			userRole = new UserRoles();
		}
		userRole.addRole(roleid);

	}
	
	public UserRoles getUserRole() {
		return this.userRole;
	}

	/**
	 * 해당 role id를 보유 했는지 확인 
	 * @param roleid
	 * @return
	 */
	public boolean hasRole(String roleid) {
		if(this.userRole != null && this.userRole.hasRole(roleid)) {
			return true;
		} else {
			return false;
		}
	}
}

