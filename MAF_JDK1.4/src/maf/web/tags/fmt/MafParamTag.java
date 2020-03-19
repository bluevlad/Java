/*
 * Created on 2006. 6. 20.
 *
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package maf.web.tags.fmt;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import maf.web.tags.fmt.support.MafParamSupport;
import maf.web.tags.support.MafExpressionEvaluationUtils;

public class MafParamTag extends MafParamSupport {
	// *********************************************************************
	// Accessor methods

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// for tag attribute
	public void setValue(String value) throws JspTagException,JspException {
		this.value = MafExpressionEvaluationUtils.evaluate("value", value, pageContext);
		this.valueSpecified = true;
	}
}
