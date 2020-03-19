/*
 * Created on 2006. 5. 22.
 *
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package maf.web.mvc.configuration;

import maf.aam.AAMManager;
import maf.base.BaseObject;

public class CmdConfig extends BaseObject{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String name = null;
	private String method = null;
	private String from = null;
	private String desc = null;
	private String formName = null;
	private String authtype = null;
	/**
	 * @return Returns the method.
	 */
	public String getMethod() {
		return method;
	}
	/**
	 * @param method The method to set.
	 */
	public void setMethod(String method) {
		this.method = method;
	}
	/**
	 * @return Returns the name.
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name The name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return Returns the from.
	 */
	public String getFrom() {
		return from;
	}
	/**
	 * @param from The from to set.
	 */
	public void setFrom(String from) {
		this.from = from;
	}
	/**
	 * @return Returns the form.
	 */
	public String getFormName() {
		return formName;
	}
	/**
	 * @param form The form to set.
	 */
	public void setFormName(String form) {
		this.formName = form;
	}
	/**
	 * 필요권한 정보 [C,R,U,D]
	 * @return
	 */
	public String getAuthtype() {
		return authtype;
	}
	/**
	 * 필요권한 정보 [C,R,U,D] default null
	 * @param authtype
	 */
	public void setAuthtype(String authtype) {
		if(AAMManager.AUTH_TYPE_CREATE.equals(authtype)) {
			this.authtype = AAMManager.AUTH_TYPE_CREATE;
		} else if(AAMManager.AUTH_TYPE_UPDATE.equals(authtype)) {
			this.authtype = AAMManager.AUTH_TYPE_UPDATE;
		} else if(AAMManager.AUTH_TYPE_DELETE.equals(authtype)) {
			this.authtype = AAMManager.AUTH_TYPE_DELETE;
		} else if(AAMManager.AUTH_TYPE_READ.equals(authtype)) {
			this.authtype = AAMManager.AUTH_TYPE_READ;
		}
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	
}


