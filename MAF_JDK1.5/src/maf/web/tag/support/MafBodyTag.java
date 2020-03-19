/*
 * Created on 2006. 09. 28
 *
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package maf.web.tag.support;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public abstract class MafBodyTag extends BodyTagSupport {
	protected Log log = LogFactory.getLog(MafBodyTag.class);

	protected final int doAfterEndTag(int returnVal) {
		reset();
		return returnVal;
	}
	
	protected abstract void reset();

	/**
	 * @see javax.servlet.jsp.tagext.Tag#doEndTag()
	 */
	public int doEndTag() throws JspException {
		return doAfterEndTag(super.doEndTag());
	}
}

