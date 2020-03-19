/*
 * Created on 2006. 6. 20.
 *
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package maf.web.tag.rt;

import java.util.Collection;
import java.util.Iterator;

import javax.servlet.jsp.JspTagException;

import maf.web.tag.support.MafButtonTagSupport;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MafButtonTag extends MafButtonTagSupport { 
	private Log logger = LogFactory.getLog(this.getClass());
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// *********************************************************************
	// Accessor methods

	// for tag attribute
	public void setKey(String key) throws JspTagException {
		this.paramClear();
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
	// for tag attribute
	public void setParam(Object param) throws JspTagException {
		if(param instanceof Object[]) {
			for(int i = 0; i < ((Object[]) param).length; i++) {
				this.addParam(((Object[]) param)[i]);
			}
		} else if ( param instanceof Collection) {
			Iterator li = ((Collection) param).iterator();
			while(li.hasNext()) {
				this.addParam(li.next());
			}
			
		} else {
			this.addParam(param);
		}
	}	
}
