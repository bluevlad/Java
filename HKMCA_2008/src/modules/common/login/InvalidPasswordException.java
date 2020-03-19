/*
 * LmsException.java, @ 2005-04-08
 * 
 * Copyright (c) 2004 UBQ All rights reserved.
 */
package modules.common.login;

import javax.servlet.ServletException;

/**
 * @author bluevlad
 *
 */
public class InvalidPasswordException extends ServletException {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * 
     */
    public InvalidPasswordException() {
        super();
    }

    /**
     * @param arg0
     */
    public InvalidPasswordException(String arg0) {
        super( arg0 );
    }

    /**
     * @param arg0
     * @param arg1
     */
    public InvalidPasswordException(String arg0, Throwable arg1) {
        super( arg0, arg1 );
    }

    /**
     * @param arg0
     */
    public InvalidPasswordException(Throwable arg0) {
        super( arg0 );
    }

}
