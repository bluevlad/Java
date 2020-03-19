/*
 * @(#) MiSystemException.java, 2005-03-01
 * 
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package maf.lib.system;

public class MiSystemException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6296069989051453771L;

	public MiSystemException() {
		super();
	}

	public MiSystemException(String message) {
		super(message);
	}

	public MiSystemException(Throwable cause) {
		super(cause);
	}

	public MiSystemException(String message, Throwable cause) {
		super(message, cause);
	}

}
