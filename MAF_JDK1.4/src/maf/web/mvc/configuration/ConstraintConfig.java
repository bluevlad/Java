/*
 * Created on 2006. 5. 16.
 *
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package maf.web.mvc.configuration;

import maf.base.BaseObject;

public class ConstraintConfig extends BaseObject  {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String validatorName;
    private String value;
    private String arg0;
    private String arg1;
    private String arg2;
    
    public void setValidatorName(String validatorName) {
        this.validatorName = validatorName;
    }
    public String getValidatorName() {
        return validatorName;
    }
    public void setValue(String value) {
        this.value = value;
    }
    public String getValue() {
        return value;
    }
    public void setArg0(String arg0) {
        this.arg0 = arg0;
    }
    public String getArg0() {
        return arg0;
    }
    public void setArg1(String arg1) {
        this.arg1 = arg1;
    }
    public String getArg1() {
        return arg1;
    }
    public void setArg2(String arg2) {
        this.arg2 = arg2;
    }
    public String getArg2() {
        return arg2;
    }
}

