/*
 * @(#) SessionInvalidateException.java 1.0, 2004. 10. 12.
 * 
 * Copyright (c) 2004 UBQ All rights reserved.
 */
package maf.web.session.exception;

import maf.exception.MafException;

/**
 * File Name : SessionInvalidateException.java
 * <br>
 * 
 * @author rainend
 * @version 1.0
 * @modifydate 2009. 10. 12.
 */
public class UnAuthorizedException extends MafException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2121660068530996449L;

	/**
	 * 
	 */
	public UnAuthorizedException() {
		super("해당 행위에 대한 권한이 없습니다.");
	}

	/**
	 * @param message
	 */
	public UnAuthorizedException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public UnAuthorizedException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public UnAuthorizedException(String message, Throwable cause) {
		super(message, cause);
	}
	

}
