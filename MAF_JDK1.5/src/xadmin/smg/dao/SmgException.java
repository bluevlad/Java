/*
 * Created on 2005. 8. 31.
 *
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package xadmin.smg.dao;

/**
 * @author goindole
 */
public class SmgException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = 6785016528525262548L;

	/**
	 * 
	 */
	public SmgException() {
		super();
	}

	/**
	 * @param message
	 */
	public SmgException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public SmgException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public SmgException(String message, Throwable cause) {
		super(message, cause);
	}
}

