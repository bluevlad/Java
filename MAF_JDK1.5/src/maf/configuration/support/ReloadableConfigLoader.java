/*
 * Created on 2006. 7. 6.
 *
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package maf.configuration.support;

import java.io.File;
import java.io.IOException;

import maf.lib.XMLDigester;
import maf.logger.Logging;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public abstract class ReloadableConfigLoader {
	private Log logger = LogFactory.getLog(ReloadableConfigLoader.class);
	public static final String RELOAD_FILE_NOT_FOUND = "FILE_NOT_FOUND";
	public static final String RELOAD_FILE_RELOADED = "RELOADED";
	public static final String RELOAD_FILE_CACHED = "CACHED";
	
	private Object refreshConfig(ConfigFileInfo resource) {
		long refreshTimestamp = (resource.getCacheMillis() < 0) ? -1 : System.currentTimeMillis();

		if (resource.exists()) {

			try {
				long fileTimestamp = -1;

				if (resource.getCacheMillis() >= 0) {
					// Last-modified timestamp of file will just be read if caching with timeout.
					File file = null;
					try {
						file = resource.getFile();
					}
					catch (IOException ex) {
						// Probably a class path resource: cache it forever.
						if (logger.isDebugEnabled()) {
							logger.debug(
									resource + " could not be resolved in the file system - assuming that is hasn't changed", ex);
						}
						file = null;
					}
					if (file != null) {
						fileTimestamp = file.lastModified();
						if (fileTimestamp == 0) {
							throw new IOException("File [" + file.getAbsolutePath() + "] does not exist");
						}
						if (resource != null && resource.getFileTimestamp() == fileTimestamp) {
							resource.setRefreshTimestamp(refreshTimestamp);
							return resource;
						}
					}
				}
				logger.debug("refresh : " + resource.getConfigFileName());
				return this.digest(resource);

			}

			catch (IOException ex) {
				if (logger.isWarnEnabled()) {
					logger.warn(
							"Could not parse properties file [" + resource.getConfigFileName() + "]: " + ex.getMessage(), ex);
				}
				// Empty holder representing "not valid".
				return null;
			}

		} else {
			logger.debug("file not found");
			return null;
		}
	}
	
	protected Object getConfig(ConfigFileInfo resource) {
		return this.refreshConfig(resource);		
	}
	
	protected Object digest(ConfigFileInfo resource) {
		ConfigFileInfo rb = null;
		try {
			rb = (ConfigFileInfo) XMLDigester.digest(resource.getConfigFileName(), resource.getRuleFileName());
			if(rb != null) {
				rb.setConfigFileName(resource.getConfigFileName());
				rb.setRuleFileName(resource.getRuleFileName());
				rb.updateFileTimestamp();
			} else {
				logger.error("Digest Error !!!:  configFile = " + resource.getConfigFileName() + ", ruleFile = " + resource.getRuleFileName() );
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			Logging.trace(e);
		}
		return rb;
	}
	

}

