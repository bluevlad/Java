/*
 * Created on 2006. 6. 20.
 *
 * Copyright (c) 2004 UBQ All rights reserved.
 * HTML Tag Áö¿ø ¿ë 
 */
package maf.web.tags.support;

import maf.web.tags.TagWriter;
import maf.web.tags.form.MafAbstractHtmlElementTag;



public abstract class MafHtmlTagSupport extends MafAbstractHtmlElementTag {
	/**
	 * Creates the {@link TagWriter} which all output will be written to. By default,
	 * the {@link TagWriter} writes its output to the {@link javax.servlet.jsp.JspWriter}
	 * for the current {@link javax.servlet.jsp.PageContext}. Subclasses may choose to
	 * change the {@link java.io.Writer} to which output is actually written.
	 */
	protected TagWriter createTagWriter() {
		return new TagWriter(this.pageContext.getOut());
	}
	
	
}
