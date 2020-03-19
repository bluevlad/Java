/*
 * Created on 2006. 5. 22.
 *
 * Copyright (c) 2004 UBQ All rights reserved.
 */
package maf.exception;

public class MafInternalException extends MafException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public MafInternalException() {
    }
	
	public MafInternalException(String msg) {
        super(msg);
    }
    public MafInternalException(Throwable e) {
        super(e);
    }
    public MafInternalException(String msg, Throwable e) {
        super(msg, e);
    }
}

