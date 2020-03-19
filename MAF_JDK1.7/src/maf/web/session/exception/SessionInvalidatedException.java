/*
 * @(#) SessionInvalidateException.java 1.0, 2004. 10. 12.
 * 
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package maf.web.session.exception;

import maf.exception.MafException;

/**
 * File Name : SessionInvalidateException.java
 * <br>
 * 
 * @author 김윤철
 * @version 1.0
 * @modifydate 2004. 10. 12.
 */
public class SessionInvalidatedException extends MafException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2121660068530996449L;

	/**
	 * 
	 */
	public SessionInvalidatedException() {
		super("로그인 정보가 없습니다. 로그인 하시기 바랍니다.");
	}

	/**
	 * @param message
	 */
	public SessionInvalidatedException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public SessionInvalidatedException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public SessionInvalidatedException(String message, Throwable cause) {
		super(message, cause);
	}
	

}
