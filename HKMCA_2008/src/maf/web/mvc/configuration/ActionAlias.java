/*
 * Created on 2006. 11. 17
 *
 * Copyright (c) 2004 UBQ All rights reserved.
 */
package maf.web.mvc.configuration;


public class ActionAlias  extends MvcConfig {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String uri = null;
	private String param = null;
	private String defaultCmd = null;
	/**
	 * @return the param
	 */
	public String getParam() {
		return param;
	}
	/**
	 * @param param the param to set
	 */
	public void setParam(String param) {
		this.param = param;
	}
	/**
	 * @return the uri
	 */
	public String getUri() {
		return uri;
	}
	/**
	 * @param uri the uri to set
	 */
	public void setUri(String uri) {
		this.uri = uri;
	}
	/**
	 * @return the defaultCmd
	 */
	public String getDefaultCmd() {
		return defaultCmd;
	}
	/**
	 * @param defaultCmd the defaultCmd to set
	 */
	public void setDefaultCmd(String defaultCmd) {
		this.defaultCmd = defaultCmd;
	}
	
	

}

