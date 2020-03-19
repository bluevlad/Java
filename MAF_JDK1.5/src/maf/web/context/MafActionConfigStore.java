/*
 * Created on 2006. 07. 19
 *
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package maf.web.context;

import java.util.HashMap;
import java.util.Map;

import maf.configuration.support.ReloadableConfigLoader;
import maf.web.exception.ActionConfigurationNotFoundException;
import maf.web.mvc.configuration.ActionConfiguration;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MafActionConfigStore extends ReloadableConfigLoader {
	private static MafActionConfigStore instance;

	private Map comandConfigMap = null;

	private Log logger = LogFactory.getLog(MafActionConfigStore.class);

	private MafActionConfigStore() {
		comandConfigMap = new HashMap();
	}

	public static synchronized MafActionConfigStore getInstance() {
		if (instance == null) instance = new MafActionConfigStore();
		return instance;
	}

	public void addBundle2(String filename, String configFileName, String ruleFilePath) {

		ActionConfiguration rb = new ActionConfiguration();
		rb.setServletPath(filename);
		rb.setConfigFileName(configFileName);
		rb.setRuleFileName(ruleFilePath);

		synchronized (this.comandConfigMap) {
			rb = (ActionConfiguration) super.digest(rb);
			if (rb != null) {
				rb.setServletPath(filename);

				comandConfigMap.put(rb.getServletPath(), rb);

			}
		}
		//		CommandFactory factory = CommandFactory.getInstance(rb.getServletPath());
		//		factory.setConfiguration(rb);		
	}

	public ActionConfiguration getCommandConfig(String servletPath) throws ActionConfigurationNotFoundException {
//		return (ActionConfiguration) comandConfigMap.get(servletPath);
		return this.getBundle(servletPath);
	}

	/**
	 *
	 * 디버깅용 
	 * 
	 * @return
	 *@deprecated
	 */
	public final Map getCommandConfigMap() {
		return comandConfigMap;
	}
	
	private ActionConfiguration getBundle(String bundleName) throws ActionConfigurationNotFoundException {
		ActionConfiguration rb = (ActionConfiguration) comandConfigMap.get(bundleName);
		if(rb == null ) {
			throw new ActionConfigurationNotFoundException("action configuratioin file [" + bundleName +".xml] not found !!!");
		}
		rb = (ActionConfiguration) super.getConfig(rb);
		if (rb != null && rb.getRefreshTimestamp() < 0) {
			synchronized (this.comandConfigMap) {
				if (comandConfigMap == null) {
					comandConfigMap = new HashMap();
				}
				comandConfigMap.put(bundleName, rb);
			}
		}
		return rb;
	}
}
