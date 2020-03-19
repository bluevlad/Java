/*
 * Created on 2006. 6. 23.
 *
 * Copyright (c) 2004 UBQ All rights reserved.
 */
package maf.web.context;

import javax.servlet.ServletContext;
import maf.core.JdkVersion;
import maf.lib.calendar.configure.HolidayFactory;
import maf.mdb.MdbDbcpInit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MafContextLoader {
	private final Log logger = LogFactory.getLog(MafContextLoader.class);

	/**
	 * The root WebApplicationContext instance that this loaded manages.
	 */
	private WebApplicationContext context;

	public WebApplicationContext initWebApplicationContext(ServletContext servletContext) {
		long startTime = System.currentTimeMillis();
		if (logger.isInfoEnabled()) {
			logger.info("JDK Version " + JdkVersion.getJavaVersion() + "\n" +
					"  Root WebApplicationContext: initialization started");
		}
		
		servletContext.log("Loading root WebApplicationContext");
		try {
			// DB Pool 초기화 (사용안함!!!) 필요함 써두됨
//			MdbDbcpInit mdbcpInit = MdbDbcpInit.getInstance();
//			try {
//				mdbcpInit.init(servletContext);
//			} catch (Exception e) {
//				logger.error(e);
//			}
//			mdbcpInit = null;
			System.out.println(this.getClass() + " MafConfig start ...");
			MafConfig mconfig = MafConfig.getInstance();
			mconfig.setServletContext(servletContext);
			// Action 정보 설정 
			mconfig.init();
			
			System.out.println(this.getClass() + " Code Table Loading start ...");
			// Code Table Loading
			MStoreFactory msf = MStoreFactory.getInstance();
			msf.configure();
			msf = null;
			
			// 휴일정보 초기화
			HolidayFactory config = HolidayFactory.getInstance();
			config.init(servletContext.getRealPath("/WEB-INF/maf-config/holidays.xml"));
			
			if (logger.isInfoEnabled()) {
				long elapsedTime = System.currentTimeMillis() - startTime;
				logger.info("Root WebApplicationContext: initialization completed in " + elapsedTime + " ms");
			}
			return this.context;
		} catch (RuntimeException ex) {
			logger.error("Context initialization failed", ex);
			servletContext.setAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE, ex);
			throw ex;
		} catch (Error err) {
			logger.error("Context initialization failed", err);
			servletContext.setAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE, err);
			throw err;
		}
		
	}

	public void closeWebApplicationContext(ServletContext servletContext) {
		MdbDbcpInit mdbcpInit = MdbDbcpInit.getInstance();
		try {
			if(mdbcpInit != null) {
				mdbcpInit.destroy();
			}
		} catch (Exception e) {
			logger.error(e);
		}
		mdbcpInit = null;
		servletContext.log("Closing root WebApplicationContext");

	}
}
