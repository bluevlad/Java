/*
 * Created on 2006. 10. 20
 *
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package maf.lib.calendar.exception;

import maf.exception.MafException;

public class InvalidDateStringException extends MafException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3919155584843619152L;

	/**
	 * 
	 */
	public InvalidDateStringException() {
		super();
	}

	/**
	 * @param message
	 */
	public InvalidDateStringException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public InvalidDateStringException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public InvalidDateStringException(String message, Throwable cause) {
		super(message, cause);
	}

}

