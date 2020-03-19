/*
 * Created on 2006. 6. 19.
 *
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package maf.beans;

import maf.base.BaseObject;
/**
 * JSTL의 format message에 정보를 주기 위한 bean
 * @author goindole
 *
 */
public class MessageInfo extends BaseObject{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String bundleName = null;
	private String messageKey = null;
	private String[] param = null;
	
	public MessageInfo() {
		
	}
	public MessageInfo(String bundleName, String messageKey, String[] param) {
		this.bundleName = bundleName;
		this.messageKey = messageKey;
		this.param = param;
	}
	
	/**
	 * @return Returns the bundleName.
	 */
	public String getBundleName() {
		return bundleName;
	}
	/**
	 * @param bundleName The bundleName to set.
	 */
	public void setBundleName(String bundleName) {
		this.bundleName = bundleName;
	}
	/**
	 * @return Returns the messageKey.
	 */
	public String getMessageKey() {
		return messageKey;
	}
	/**
	 * @param messageKey The messageKey to set.
	 */
	public void setMessageKey(String messageKey) {
		this.messageKey = messageKey;
	}
	/**
	 * @return Returns the param.
	 */
	public String[] getParam() {
		return param;
	}
	/**
	 * @param param The param to set.
	 */
	public void setParam(String[] param) {
		this.param = param;
	}
	
}

