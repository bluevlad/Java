/*
 * MaxPostSizeException.java, @ 2005-03-05
 * 
 * Copyright (c) 2004 (��)�̷��� All rights reserved.
 */
package maf.web.exception;

/**
 * ÷�������� ũ�⸦ ������ ������ ���� Ŭ��...
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
