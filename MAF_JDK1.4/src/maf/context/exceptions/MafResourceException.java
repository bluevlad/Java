/*
 * Created on 2006. 7. 6.
 *
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package maf.context.exceptions;

import maf.exception.MafException;

public class MafResourceException extends MafException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public MafResourceException(String msg) {
        super(msg);
    }
}

