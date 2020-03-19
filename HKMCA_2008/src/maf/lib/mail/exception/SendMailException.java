/*
 * @(#) SendMailException.java 2005-02-18
 * 
 * Copyright (c) 2005 UBQ All rights reserved.
 */

package maf.lib.mail.exception;

import maf.exception.MafException;

/**
 * @author bluevlad
 * @version 1.0
 * @modifydate 2005-02-18
 */
public class SendMailException extends MafException {

    /**
	 * 
	 */
	private static final long serialVersionUID = -8935747237681059052L;

	/**
     * 
     */
    public SendMailException() {
        super();
    }

    /**
     * @param message
     */
    public SendMailException(String message) {
        super(message);
    }

    /**
     * @param cause
     */
    public SendMailException(Throwable cause) {
        super(cause);
    }

    /**
     * @param message
     * @param cause
     */
    public SendMailException(String message, Throwable cause) {
        super(message, cause);
    }

}
