/*
 * Created on 2006. 6. 20.
 *
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package maf.web.tag.rt;

import javax.servlet.jsp.JspTagException;

import maf.web.tag.support.MafGetCodeNameTagSupport;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MafGetCodeNameTag extends MafGetCodeNameTagSupport {
	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	final private Log logger = LogFactory.getLog(MafGetCodeNameTag.class);
	
	public void setGroupCd(String key) throws JspTagException {

		this.groupCdAttrValue = key;

	}
	public void setCodeNo(String key) throws JspTagException {

		this.codeNoAttrValue = key;

	}
	public void setSite(String key) throws JspTagException {

		this.siteAttrValue = key;
		this.siteSpecified = true;
	}
}
