/*
 * Created on 2006. 5. 16.
 *
 * Copyright (c) 2004 UBQ All rights reserved.
 */
package maf.web.mvc.configuration;

import java.util.Map;
import maf.base.BaseObject;
import maf.util.StringUtils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class InputConfig extends BaseObject {
	
	Log logger = LogFactory.getLog(InputConfig.class);
	
	private static final long serialVersionUID = 1L;

	private String name;
	private String message;
	private String type;
	// private String[] validatorNames;
	private int maxlength=0;

	private String arg0;
	private String arg1;
	private String arg2;
	private boolean required = false;
	private String bundle;
	private String codegroup;
	
	private Map constraintConfigMap = new java.util.HashMap();

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	public void setBundle(String bundle) {
		this.bundle = bundle;
	}
	
	public void setCodegroup(String codegroup) {
		this.codegroup = codegroup;
	}
	
	public void setRequired(String required) {
		this.required = StringUtils.toBoolean(required);
	}
	// public void setValidators(String validators) {
	// StringTokenizer st = new StringTokenizer(validators, ",");
	// validatorNames = new String[st.countTokens()];
	// int i = 0;
	// while(st.hasMoreTokens()) {
	// validatorNames[i++] = st.nextToken();
	// }
	// }
	// public String[] getValidatorNames() {
	// return validatorNames;
	// }
	public void setArg0(String arg0) {
		this.arg0 = arg0;
	}

	public void setArg1(String arg1) {
		this.arg1 = arg1;
	}

	public void setArg2(String arg2) {
		this.arg2 = arg2;
	}

	public String[] getArgs() {
		if (arg0 == null && arg1 == null && arg2 == null) return null;
		String[] args = new String[3];
		args[0] = arg0 == null ? "" : arg0;
		args[1] = arg1 == null ? "" : arg1;
		args[2] = arg2 == null ? "" : arg2;
		return args;
	}

	public void addConstraintConfig(ConstraintConfig config) {
		constraintConfigMap.put(config.getValidatorName(), config);
		if("required".equals(config.getValidatorName())) {
			this.required = true;
		}

	
	}

	public ConstraintConfig getConstraintConfig(String validatorName) {

		return (ConstraintConfig) constraintConfigMap.get(validatorName);
	}

	/**
	 * @return Returns the message.
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message The message to set.
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the required
	 */
	public boolean isRequired() {
		return required;
	}

	/**
	 * @return the maxlength
	 */
	public int getMaxlength() {
		return maxlength;
	}

	/**
	 * @param maxlength the maxlength to set
	 */
	public void setMaxlength(int maxlength) {
		this.maxlength = maxlength;
	}

	public String getBundle() {
		return bundle;
	}

	public String getCodegroup() {
		return codegroup;
	}
	
	
	
}
