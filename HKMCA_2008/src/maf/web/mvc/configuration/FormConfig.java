/*
 * Created on 2006. 5. 16.
 *
 * Copyright (c) 2004 UBQ All rights reserved.
 */
package maf.web.mvc.configuration;

import java.util.Map;

import maf.base.BaseObject;

public class FormConfig extends BaseObject {
//	public static final String FORM_ATTRIBUTE_NAME = FormConfig.class.getName() + ".NAME";
	public static final String FORM_ATTRIBUTE_NAME = "MAF_FORM_CONFIG_NAME";
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String name;
	private String bundle;

	private Map inputConfigMap = new java.util.HashMap();

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void addInputConfig(InputConfig config) {
		inputConfigMap.put(config.getName(), config);
	}
	
	public InputConfig getInputConfig(String fieldName) {
		return ( InputConfig ) inputConfigMap.get(fieldName);
	}
	
	public final Map getInputConfigMap() {
		return inputConfigMap;
	}

	/**
	 * @return Returns the bundle.
	 */
	public String getBundle() {
		return bundle;
	}

	/**
	 * @param bundle The bundle to set.
	 */
	public void setBundle(String bundle) {
		this.bundle = bundle;
	}

}
