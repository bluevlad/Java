/*
 * Created on 2006. 6. 20.
 *
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package maf.web.tag.rt;

import javax.servlet.jsp.JspTagException;

import maf.web.tag.support.MafFormLabelTagSupport;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MafFormLabelTag extends MafFormLabelTagSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Log logger = LogFactory.getLog(this.getClass());
	public void setName(String key) throws JspTagException {

		this.nameAttrValue = key;
		this.nameSpecified = true;
	}
}
