/*
 * Created on 2006. 6. 26.
 *
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package maf.web.requestMon;

public class RequestInfo {
	String remoteIp = null;
	String requestUri = null;
	/**
	 * @return Returns the remoteIp.
	 */
	public String getRemoteIp() {
		return remoteIp;
	}
	/**
	 * @param remoteIp The remoteIp to set.
	 */
	public void setRemoteIp(String remoteIp) {
		this.remoteIp = remoteIp;
	}
	/**
	 * @return Returns the requestUri.
	 */
	public String getRequestUri() {
		return requestUri;
	}
	/**
	 * @param requestUri The requestUri to set.
	 */
	public void setRequestUri(String requestUri) {
		this.requestUri = requestUri;
	}
	
}

