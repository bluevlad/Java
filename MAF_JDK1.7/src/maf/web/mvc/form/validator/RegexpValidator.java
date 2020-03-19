/*
 * Created on 2006. 5. 16.
 *
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package maf.web.mvc.form.validator;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexpValidator extends Validator {
    
    private Map patternMap = new java.util.HashMap();
    
    public boolean validate(String constraint, String value) {
        Pattern pattern = (Pattern)patternMap.get(constraint);
        if (pattern == null) {
            synchronized(patternMap) {
                pattern = (Pattern)patternMap.get(constraint);
                if (pattern == null) {
                    pattern = Pattern.compile(constraint);
                    patternMap.put(constraint, pattern);
                }
            }
        }
        Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }
}