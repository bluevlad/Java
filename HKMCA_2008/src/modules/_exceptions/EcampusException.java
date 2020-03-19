/*
 * LmsException.java, @ 2005-04-08
 * 
 * Copyright (c) 2004 UBQ All rights reserved.
 */
package modules._exceptions;

import javax.servlet.ServletException;

/**
 * @author bluevlad
 *
 */
public class EcampusException extends ServletException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 6362097673784387489L;

	/**
     * 
     */
    public EcampusException() {
        super();
    }

    /**
     * @param arg0
     */
    public EcampusException(String arg0) {
        super( arg0 );
    }

    /**
     * @param arg0
     * @param arg1
     */
    public EcampusException(String arg0, Throwable arg1) {
        super( arg0, arg1 );
    }

    /**
     * @param arg0
     */
    public EcampusException(Throwable arg0) {
        super( arg0 );
    }

}
