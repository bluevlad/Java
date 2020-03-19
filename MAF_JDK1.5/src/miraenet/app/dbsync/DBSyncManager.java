/*
 * Created on 2005. 6. 29.
 *
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package miraenet.app.dbsync;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import maf.lib.XMLDigester;
import miraenet.app.dbsync.beans.DbSyncConfig;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author goindole
 * 
 * MS-SQL과 동기화 처리
 */
public class DBSyncManager extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1934307358511476653L;

    private  Log logger = LogFactory.getLog(this.getClass());
	
//	private static DBSyncManager instance = null;
	private static DbSyncConfig configuration = null;

	public void init(ServletConfig config) throws ServletException {
		ServletContext ctx = config.getServletContext();
		String configFile = ctx.getRealPath(config.getInitParameter("configFile"));
		String ruleFile = ctx.getRealPath(config.getInitParameter("ruleFile"));
		configure(configFile, ruleFile);
//		instance = this;
	}

	private synchronized void configure(String configFilePath, String ruleFilePath) throws ServletException {
		try {
			System.out.println(">>>> DB Sync Configure Start ...");
			configuration = (DbSyncConfig) XMLDigester.digest(configFilePath, ruleFilePath);
			System.out.println("<<<< DB Sync Configure Finished ");
		} catch (Exception ex) {
			throw new ServletException(ex.getMessage(), ex);
		}
	}

	public static DbSyncConfig getSyncConfig() {
		return configuration;
	}
	
	public static boolean startSync() {
		DbSyncThread thread = DbSyncThread.getInstance();
		thread.setSyncConfig(configuration);
		boolean chk = thread.start();
		return chk;
	}
	
	public static void stopSync() {
		DbSyncThread.getInstance().stop();
	}
	
	public static boolean syncStatus() {
		boolean chk = DbSyncThread.getInstance().isAlive();
		return chk;
	}
	
	public void destroy() {

		super.destroy();
	}
}
