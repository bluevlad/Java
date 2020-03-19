/*
 * ThumbnailException.java, @ 2005-03-08
 * 
 * Copyright (c) 2004 UBQ All rights reserved.
 */
package maf.lib.image;

public class ThumbnailException extends Exception {

    /**
	 * 
	 */
	private static final long serialVersionUID = 5096708168578763931L;

	/**
     * 
     */
    public ThumbnailException() {
        super();
    }

    /**
     * @param message
     */
    public ThumbnailException(String message) {
        super( message );
    }

    /**
     * @param cause
     */
    public ThumbnailException(Throwable cause) {
        super( cause );
    }

    /**
     * @param message
     * @param cause
     */
    public ThumbnailException(String message, Throwable cause) {
        super( message, cause );
    }

}
