/*
 * Created on 2006. 6. 23.
 *
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package maf.web.context;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * Bootstrap listener to start up root WebApplicationContext.
 * Simply delegates to ContextLoader.
 *
 * <p>This listener should be registered after Log4jConfigListener in web.xml,
 * if the latter is used.
 *
 * <p>For Servlet 2.2 containers and Servlet 2.3 ones that do not initalize
 * listeners before servlets, use ContextLoaderServlet. See the latter's Javadoc
 * for details.

 */
public class MafContextLoaderListener implements ServletContextListener {
	private final Log logger = LogFactory.getLog(this.getClass());
	private MafContextLoader contextLoader;
	
	/**
	 * Initialize the root web application context.
	 */
	public void contextInitialized(ServletContextEvent event) {
		logger.info(this.getClass() + " Initialized...");
		this.contextLoader = createContextLoader();
		this.contextLoader.initWebApplicationContext(event.getServletContext());
	}

	/**
	 * Create the ContextLoader to use. Can be overridden in subclasses.
	 * @return the new ContextLoader
	 */
	protected MafContextLoader createContextLoader() {
		return new MafContextLoader();
	}

	/**
	 * Close the root web application context.
	 */
	public void contextDestroyed(ServletContextEvent event) {
		if (this.contextLoader != null) {
			this.contextLoader.closeWebApplicationContext(event.getServletContext());
		}
		logger.info(this.getClass() + " Destroyed...");
	}

}