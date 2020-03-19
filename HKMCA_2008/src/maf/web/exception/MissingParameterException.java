/*
 * Created on 2006. 10. 10
 *
 * Copyright (c) 2004 UBQ All rights reserved.
 */
package maf.web.exception;


public class MissingParameterException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String key = null;
	public MissingParameterException() {
	}

	public MissingParameterException(String key) {
		super("parameter " + key + " not summited");
		this.key = key;
	}

	public MissingParameterException(Throwable e) {
		super(e);
	}

	public MissingParameterException(String msg, Throwable e) {
		super(msg, e);
	}
	
	public String getKey() {
		return this.key;
	}
}
