/*
 * ClubException.java, @ 2005-03-19
 * 
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package miraenet.app.club;

import maf.exception.MafException;

/**
 * @author goindole
 *
 */
public class ClubException extends MafException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 8781481174661862784L;


    public ClubException(String message) {
        super( message );
    }

    public ClubException(Throwable cause) {
        super( cause );
    }


    public ClubException(String message, Throwable cause) {
        super( message, cause );
    }

}
