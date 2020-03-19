/*
 * Created on 2006. 6. 20.
 *
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package maf.web.tags.fmt;

import javax.servlet.jsp.JspTagException;
import maf.web.tags.support.MafSetBundleSupport;


public class MafSetBundleTag extends MafSetBundleSupport {

    //*********************************************************************
    // Accessor methods

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// for tag attribute
    public void setBasename(String basename) throws JspTagException {
        this.basename = basename;
    }
}
