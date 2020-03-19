/*
 * Created on 2006. 6. 23.
 *
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package maf.web.context;

import javax.servlet.ServletContext;

public interface WebApplicationContext  {
	/**
	 * Context attribute to bind root WebApplicationContext to on successful startup.
	 * <p>Note: If the startup of the root context fails, this attribute can contain
	 * an exception or error as value. Use WebApplicationContextUtils for convenient
	 * lookup of the root WebApplicationContext.
	 * @see org.springframework.web.context.support.WebApplicationContextUtils#getWebApplicationContext
	 * @see org.springframework.web.context.support.WebApplicationContextUtils#getRequiredWebApplicationContext
	 */
	String ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE = WebApplicationContext.class + ".ROOT";

	/**
	 * Return the standard Servlet API ServletContext for this application.
	 */
	ServletContext getServletContext();
}

