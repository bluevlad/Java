/*
 * Created on 2006. 5. 16.
 *
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package maf.web.mvc.message;

import java.util.List;

public class ErrorMessage {
	/** 에러 메시지를 보여줄 때 사용되는 메시지의 이름. */
    private String name;
    private List params;
    private List paramMessageIds;
    
    public ErrorMessage(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void addParameter(Object param) {
        if (params == null) params = new java.util.ArrayList(3);
        params.add(param);
    }
    
    public Object[] getParameters() {
    	if (params == null) return null;
    	Object[] parameters = new Object[params.size()];
    	params.toArray(parameters);
    	return parameters;
	}
	
	public void addParameterMessageId(String messageId) {
	    if (paramMessageIds == null) paramMessageIds = new java.util.ArrayList(3);
	    paramMessageIds.add(messageId);
	}

    public String[] getParameterMessageIds() {
    	if (paramMessageIds == null) return null;
    	String[] parameterMessageIds = new String[paramMessageIds.size()];
    	paramMessageIds.toArray(parameterMessageIds);
    	return parameterMessageIds;
	}
}

