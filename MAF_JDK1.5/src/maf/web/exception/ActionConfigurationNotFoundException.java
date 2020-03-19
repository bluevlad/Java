/*
 * Created on 2006. 08. 22
 *
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package maf.web.exception;

import maf.exception.MafException;

public class ActionConfigurationNotFoundException extends MafException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ActionConfigurationNotFoundException() {
    }
	
	public ActionConfigurationNotFoundException(String msg) {
        super(msg);
    }
	
    public ActionConfigurationNotFoundException(Throwable e) {
        super(e);
    }
    
    public ActionConfigurationNotFoundException(String msg, Throwable e) {
        super(msg, e);
    }
}

