/*
 * Created on 2005. 6. 29.
 *
 * Copyright (c) 2004 UBQ All rights reserved.
 */
package modules.common.dbsync;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import maf.lib.xml.XMLDigester;
import modules.common.dbsync.beans.DbSyncConfig;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * @author UBQ
 * 
 * MS-SQL과 동기화 처리
 */
public class DBSyncManager extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1934307358511476653L;

    private  Log logger = LogFactory.getLog(DBSyncManager.class);
	
	private static DbSyncConfig configuration = null;

	public void init() throws ServletException {
		System.out.println("#########################################");
		System.out.println(">>>> DB Sync Start ...");
		reloadConfig();
//		String configFile  = getServletConfig().getInitParameter("configFile");
//		String ruleFile  = getServletConfig().getInitParameter("ruleFile");
//		ServletContext ctx = config.getServletContext();
//		String configFile = ctx.getRealPath(config.getInitParameter("configFile"));
//		String ruleFile = ctx.getRealPath(config.getInitParameter("ruleFile"));
//		reloadConfig(configFile, ruleFile);
	}

	private synchronized void reloadConfig() throws ServletException {
		try {
			String configFile  = getServletConfig().getInitParameter("configFile");
			String ruleFile  = getServletConfig().getInitParameter("ruleFile");
			ServletContext ctx = getServletContext();
			String configFilePath = ctx.getRealPath(configFile);
			String ruleFilePath = ctx.getRealPath(ruleFile);
			System.out.println(">>>> DB Sync Configure Start ...");
			configuration = (DbSyncConfig) XMLDigester.digest(configFilePath, ruleFilePath);
			System.out.println("<<<< DB Sync Configure Finished ");
		} catch (Exception ex) {
			maf.logger.Logging.trace(ex);
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
