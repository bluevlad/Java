/*
 * Created on 2006. 07. 26
 *
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package maf.web.mvc.view;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public abstract class AbstractViewer  implements Viewer {
	private Log logger = LogFactory.getLog(this.getClass());
//	public static final String DEFAULT_CONTENT_TYPE = "text/html; charset=ISO-8859-1";	
	public static final String DEFAULT_CONTENT_TYPE = "text/html; charset=UTF-8";	
	private String contentType = DEFAULT_CONTENT_TYPE;
	
	/**
	 * Set the content type for this view.
	 * Default is "text/html; charset=ISO-8859-1".
	 * <p>May be ignored by subclasses if the view itself is assumed
	 * to set the content type, e.g. in case of JSPs.
	 * @param contentType content type for this view
	 * @see #DEFAULT_CONTENT_TYPE
	 */
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	/**
	 * Return the content type for this view.
	 */
	public String getContentType() {
		return this.contentType;
	}	
}

