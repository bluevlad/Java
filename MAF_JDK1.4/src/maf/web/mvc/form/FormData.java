/*
 * Created on 2006. 5. 16.
 *
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package maf.web.mvc.form;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import maf.web.mvc.configuration.FormConfig;
import maf.web.mvc.configuration.ValidationConfig;
import maf.web.mvc.message.ErrorMessage;
import maf.web.mvc.message.ErrorMessageSet;

import org.apache.commons.beanutils.BeanUtils;

public class FormData {

    private Map parameterValues = new java.util.HashMap();
    private FormConfig formConfig;
    private ValidationConfig validationConfig;
    
    public FormData(FormConfig formConfig, ValidationConfig validationConfig) {
        this.formConfig = formConfig;
        this.validationConfig = validationConfig;
    }
    
    public ErrorMessageSet validate(HttpServletRequest request) {
        Map inputConfigList = formConfig.getInputConfigMap();
        List errorList = new java.util.ArrayList();
        
//        for (int i = 0 ; i < inputConfigList.size() ; i++) {
//            InputConfig inputConfig = (InputConfig)inputConfigList.get(i);
//            String[] validatorNames = inputConfig.getValidatorNames();
//            String[] argMessageId = inputConfig.getArgs();
//            String parameterName = inputConfig.getName();
//            String parameterValue = request.getParameter(parameterName);
//            
//            boolean validValue = true;
//            if (validatorNames != null && validatorNames.length > 0) {
//                for (int j = 0 ; j < validatorNames.length ; j++) {
//                    Validator validator = validationConfig.getValidator(validatorNames[j]);
//                    ConstraintConfig constraintConfig = inputConfig.getConstraintConfig(validatorNames[j]);
//                    String constraintValue = constraintConfig == null ? null : constraintConfig.getValue();
//                    if (!validator.validate(constraintValue, parameterValue)) {
//                        ErrorMessage errorMessage = new ErrorMessage(validator.getMessageId());
//                        if (constraintConfig != null && constraintConfig.getArg0() != null) {
//                            errorMessage.addParameterMessageId(constraintConfig.getArg0());
//                        } else {
//                            errorMessage.addParameterMessageId(argMessageId[0]);
//                        }
//                        if (constraintConfig != null && constraintConfig.getArg1() != null) {
//                            errorMessage.addParameterMessageId(constraintConfig.getArg1());
//                        } else {
//                            errorMessage.addParameterMessageId(argMessageId[1]);
//                        }
//                        if (constraintConfig != null && constraintConfig.getArg2() != null) {
//                            errorMessage.addParameterMessageId(constraintConfig.getArg2());
//                        } else {
//                            errorMessage.addParameterMessageId(argMessageId[2]);
//                        }
//                        
//                        errorList.add(errorMessage);
//                        validValue = false;
//                        break;
//                    }
//                }
//            }
//            parameterValues.put(parameterName, parameterValue);
//        }
        if (errorList.size() > 0) {
            ErrorMessageSet errorMessageSet = new ErrorMessageSet();
            for (int i = 0 ; i < errorList.size() ; i++) {
                errorMessageSet.addErrorMessage(
                    (ErrorMessage)errorList.get(i));
            }
            return errorMessageSet;
        }
        return null;
    }
    
    public void putInto(HttpServletRequest request) {
        request.setAttribute(FormData.class.getName(), this);
    }
    
    public static FormData getFrom(HttpServletRequest request) {
        return (FormData)request.getAttribute(FormData.class.getName());
    }
    
    public String getParameter(String name) {
        return (String)parameterValues.get(name);
    }
    
    public void copyTo(Object bean) throws IllegalAccessException, InvocationTargetException {
        BeanUtils.populate(bean, parameterValues);
    }
}