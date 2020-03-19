/*
 * Created on 2006. 7. 6.
 *
 * Copyright (c) 2004 UBQ All rights reserved.
 */
package maf.configuration.support;

import java.io.File;
import java.io.IOException;

import maf.base.BaseObject;
import maf.util.FileUtils;

public class ConfigFileInfo extends BaseObject {
	public static final String RELOAD_FILE_NOT_FOUND = "FILE_NOT_FOUND";
	public static final String RELOAD_FILE_RELOADED = "RELOADED";
	public static final String RELOAD_FILE_CACHED = "CACHED";
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String configFileName = null;
	private String ruleFileName = null;
	private long cacheMillis = 10*1000;
	private long fileTimestamp = -1;
	private String cacheStatus = RELOAD_FILE_CACHED;
	
	private long refreshTimestamp = -1;
	
	public boolean exists() {
		return FileUtils.existFile(this.getConfigFileName());
	}
	public File getFile() throws IOException{
		return new File(this.getConfigFileName());
	}
	
	public long getFileTimestamp() {
		return fileTimestamp;
	}
	public void updateFileTimestamp() {
		File t = new File(this.getConfigFileName());
		if (t!= null) {
			this.fileTimestamp = t.lastModified();
		}
	}
	public void setRefreshTimestamp(long refreshTimestamp) {
		this.refreshTimestamp = refreshTimestamp;
	}

	public long getRefreshTimestamp() {
		return refreshTimestamp;
	}
	/**
	 * @return Returns the configFileName.
	 */
	public String getConfigFileName() {
		return configFileName;
	}
	/**
	 * @param configFileName The configFileName to set.
	 */
	public void setConfigFileName(String configFileName) {
		this.configFileName = configFileName;
	}
	/**
	 * @return Returns the ruleFileName.
	 */
	public String getRuleFileName() {
		return ruleFileName;
	}
	/**
	 * @param ruleFileName The ruleFileName to set.
	 */
	public void setRuleFileName(String ruleFileName) {
		this.ruleFileName = ruleFileName;
	}
	/**
	 * @return Returns the cacheMillis.
	 */
	public long getCacheMillis() {
		return cacheMillis;
	}
	/**
	 * @param cacheSeconds The cacheMillis to set.
	 */
	public void setCacheMillis(int cacheSeconds) {
		this.cacheMillis = (cacheSeconds * 1000);
	}
	
	
}

