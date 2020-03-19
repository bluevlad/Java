/*
 * Created on 2006. 5. 16.
 *
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package maf.web.mvc.form.validator;

public class MinLengthValidator extends Validator {

    public boolean validate(String constraint, String value) {
        if (value == null || value.equals("")) {
            return false;
        }
        
        if (constraint.length() > 0) {
            boolean useByteLength = constraint.charAt(constraint.length() - 1) == 'b' 
                ? true : false;
            if (useByteLength) {
                int minLength = Integer.parseInt(
                   constraint.substring(0, constraint.length()-1));
                
                if (minLength > value.getBytes().length) {
                    return false;
                } else {
                    return true;
                }
            } else {
                int minLength = Integer.parseInt(constraint);
                if (minLength > value.length()) {
                    return false;
                } else {
                    return true;
                }
            }
        }
        return true;
    }
}

