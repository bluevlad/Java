/*
 * Created on 2006. 6. 20.
 *
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package maf.web.tag.support;

import java.io.UnsupportedEncodingException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;

public abstract class MafRequestEncodingSupport extends TagSupport {

	// *********************************************************************
	// Package-scoped constants

	static final String REQUEST_CHAR_SET = "javax.servlet.jsp.jstl.fmt.request.charset";

	// *********************************************************************
	// Private constants

	private static final String DEFAULT_ENCODING = "ISO-8859-1";

	// *********************************************************************
	// Tag attributes

	protected String value; // 'value' attribute

	// *********************************************************************
	// Derived information

	protected String charEncoding; // derived from 'value' attribute

	// *********************************************************************
	// Constructor and initialization

	public MafRequestEncodingSupport() {
		super();
		init();
	}

	private void init() {
		value = null;
	}

	// *********************************************************************
	// Tag logic

	public int doEndTag() throws JspException {
		charEncoding = value;
		if ((charEncoding == null) && (pageContext.getRequest().getCharacterEncoding() == null)) {
			// Use charset from session-scoped attribute
			charEncoding = (String) pageContext.getAttribute(REQUEST_CHAR_SET, PageContext.SESSION_SCOPE);
			if (charEncoding == null) {
				// Use default encoding
				charEncoding = DEFAULT_ENCODING;
			}
		}

		/*
		 * If char encoding was already set in the request, we don't need to set it again.
		 */
		if (charEncoding != null) {
			try {
				pageContext.getRequest().setCharacterEncoding(charEncoding);
			} catch (UnsupportedEncodingException uee) {
				throw new JspTagException(uee.getMessage());
			}
		}

		return EVAL_PAGE;
	}

	// Releases any resources we may have (or inherit)
	public void release() {
		init();
	}
}
