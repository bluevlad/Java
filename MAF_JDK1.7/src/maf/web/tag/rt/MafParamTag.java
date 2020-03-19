/*
 * Created on 2006. 6. 20.
 *
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package maf.web.tag.rt;

import javax.servlet.jsp.JspTagException;

import maf.web.tag.support.MafParamSupport;

public class MafParamTag extends MafParamSupport {
	// *********************************************************************
	// Accessor methods

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// for tag attribute
	public void setValue(Object value) throws JspTagException {
		this.value = value;
		this.valueSpecified = true;
	}
}
