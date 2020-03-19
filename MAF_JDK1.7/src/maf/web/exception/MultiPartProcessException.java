/*
 * MaxPostSizeException.java, @ 2005-03-05
 * 
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package maf.web.exception;

/**
 * 첨부파일의 크기를 지정된 사이즈 보다 클때...
 */
public class MultiPartProcessException extends Exception {


	/**
	 * 
	 */
	private static final long serialVersionUID = 8590863206120315602L;


	public MultiPartProcessException() {
		super();

	}


	public MultiPartProcessException(String message) {
		super(message);

	}


	public MultiPartProcessException(Throwable cause) {
		super(cause);

	}


	public MultiPartProcessException(String message, Throwable cause) {
		super(message, cause);
	}

}
