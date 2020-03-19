/*
 * ClubException.java, @ 2005-03-19
 * 
 * Copyright (c) 2004 UBQ All rights reserved.
 */
package modules.community.club;

import maf.exception.MafException;

/**
 * @author bluevlad
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
