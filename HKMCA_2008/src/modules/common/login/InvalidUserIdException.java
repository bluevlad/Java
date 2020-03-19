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
public class InvalidUserIdException extends ServletException {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * 
     */
    public InvalidUserIdException() {
        super();
    }

    /**
     * @param arg0
     */
    public InvalidUserIdException(String arg0) {
        super( arg0 );
    }

    /**
     * @param arg0
     * @param arg1
     */
    public InvalidUserIdException(String arg0, Throwable arg1) {
        super( arg0, arg1 );
    }

    /**
     * @param arg0
     */
    public InvalidUserIdException(Throwable arg0) {
        super( arg0 );
    }

}
