/*
 * Created on 2006. 5. 16.
 *
 * Copyright (c) 2004 UBQ All rights reserved.
 */
package maf.web.mvc.configuration;

import java.util.Map;

import maf.web.mvc.form.validator.Validator;

public class ValidationConfig {
	private Map validatorMap = new java.util.HashMap();
    private Map formConfigMap = new java.util.HashMap();
    
    public void addValidatorConfig(ValidatorConfig config) {
        try {
            Class validatorClass = Class.forName(config.getType());
            Validator validator = (Validator)validatorClass.newInstance();
            validator.setName(config.getName());
            validator.setMessageId(config.getMessageId());
            validatorMap.put(validator.getName(), validator);
        } catch(Throwable ex) {
            ex.printStackTrace();
        }
    }
    
    public Validator getValidator(String name) {
        return (Validator)validatorMap.get(name);
    }
    
    public void addFormConfig(FormConfig config) {
        formConfigMap.put(config.getName(), config);
    }
    
    public FormConfig getFormConfig(String name) {
        return (FormConfig)formConfigMap.get(name);
    }
}

