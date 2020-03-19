/*
 * Created on 2006. 6. 21.
 *
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package maf.web.tag.support;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.tagext.Tag;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.taglibs.standard.resources.Resources;

public class MafParamSupport extends BodyTagSupport {
	private Log logger = LogFactory.getLog(this.getClass());
	
	// *********************************************************************
	// Protected state

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected Object value; // 'value' attribute

	protected boolean valueSpecified; // status

	// *********************************************************************
	// Constructor and initialization

	public MafParamSupport() {
		super();
		init();
	}

	private void init() {
		value = null;
		valueSpecified = false;
	}

	// *********************************************************************
	// Tag logic

	// Supply our value to our parent <fmt:message> tag
	public int doEndTag() throws JspException {
		Tag t = findAncestorWithClass(this, MafMessageTagSupport.class);
		if (t == null) {
			throw new JspTagException(Resources.getMessage("PARAM_OUTSIDE_MESSAGE"));
		}
		MafMessageTagSupport parent = (MafMessageTagSupport) t;

		/*
		 * Get argument from 'value' attribute or body, as appropriate, and add it to enclosing <fmt:message> tag, even if it is
		 * null or equal to "".
		 */
		Object input = null;
		// determine the input by...
		if (valueSpecified) {
			// ... reading 'value' attribute
			input = value;
		} else {
			// ... retrieving and trimming our body (TLV has ensured that it's
			// non-empty)
			input = bodyContent.getString().trim();
		}
		parent.addParam(input);

		return EVAL_PAGE;
	}

	// Releases any resources we may have (or inherit)
	public void release() {
		init();
	}
}
