/*
 * Created on 2006. 6. 20.
 *
 * Copyright (c) 2004 UBQ All rights reserved.
 */
package maf.web.tags.rt;

import javax.servlet.jsp.JspTagException;

import maf.web.tags.support.MafGetCodeNameTagSupport;

public class MafGetCodeNameTag extends MafGetCodeNameTagSupport {
	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;

	public void setGroupCd(String key) throws JspTagException {

		this.groupCdAttrValue = key;

	}

	public void setCodeNo(String key) throws JspTagException {

		this.codeNoAttrValue = key;

	}

}
