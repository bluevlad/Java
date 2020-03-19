/*
 * Created on 2006. 4. 14.
 *
 * Copyright (c) 2004 UBQ All rights reserved.
 */
package maf.exception;

import javax.servlet.ServletException;

public class MafException extends ServletException  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MafException() {
    }
	
	public MafException(String msg) {
        super(msg);
    }
	
    public MafException(Throwable e) {
        super(e);
    }
    
    public MafException(String msg, Throwable e) {
        super(msg, e);
    }
    
}

