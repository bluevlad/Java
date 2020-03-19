/*
 * Created on 2006. 5. 22.
 *
 * Copyright (c) 2004 UBQ All rights reserved.
 */
package maf.exception;


public class MafSetterException extends MafException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MafSetterException() {
    }
	
	public MafSetterException(String msg) {
        super(msg);
    }
    public MafSetterException(Throwable e) {
        super(e);
    }
    public MafSetterException(String msg, Throwable e) {
        super(msg, e);
    }
    
}