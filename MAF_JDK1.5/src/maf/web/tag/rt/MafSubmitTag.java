/*
 * Created on 2006. 6. 20.
 *
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package maf.web.tag.rt;

import javax.servlet.jsp.JspTagException;

import maf.util.StringUtils;
import maf.web.tag.support.MafSubmitTagSupport;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MafSubmitTag extends MafSubmitTagSupport{
	
	private Log logger = LogFactory.getLog(this.getClass());
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// *********************************************************************
	// Accessor methods

	// for tag attribute
	public void setKey(String key) throws JspTagException {
		this.keyAttrValue = key;
		this.keySpecified = true;
	}

	// for tag attribute
	public void setBundle(String baseName) throws JspTagException {
		this.baseName = baseName;
		this.bundleSpecified = true;
	}
	// for tag attribute
	public void setLocale(String locale) throws JspTagException {
		this.localeString = locale;
		this.localeSpecified = true;
	}	
	public void setName(String name) throws JspTagException {
		this.nameAttrValue = name;
		this.nameSpecified = true;
	}
	public void setValue(String value) throws JspTagException {
		this.valueAttrValue = value;
		this.valueSpecified = true;
	}
	public void setType(String type) throws JspTagException {
		this.typeAttrValue = StringUtils.toUpperCase(type);
		this.typeSpecified = true;
	}

}
