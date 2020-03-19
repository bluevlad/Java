/*
 * Created on 2006. 3. 29.
 *
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package maf.mdb.support;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import maf.mdb.DBConnectionPool;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DBConnectionJNDIPool extends DBConnectionPool{
	Log logger = LogFactory.getLog(DBConnectionJNDIPool.class);
	
//	static private DBConnectionJNDIPool _instance; // The single instance

	private static InitialContext initCtx=null;

	public DBConnectionJNDIPool() {
		if (initCtx == null) {
			// Get the JNDI context
			try {
				initCtx = new InitialContext();
			} catch (Exception _ignored) {
			}
		}
	}

	/**
	 * Use Global Context 
	 * @param name
	 * @return
	 * @throws SQLException
	 */
	public Connection getConnectionGlobal(String name) throws SQLException {
		Connection con = null;
		try {
			// Global Name 
			DataSource ds = (DataSource) initCtx.lookup(name);
			con = ds.getConnection();
		} catch (NamingException me) {
			// Local Naming
			try {
				// Lookup the datasource(WebSpheere 6.0권장)
				Context envCtx = (Context) initCtx.lookup("java:comp/env");
				DataSource ds = (DataSource) envCtx.lookup(name);

				// Get the connection from the datasource
				con = ds.getConnection();
			} catch (NamingException  en) {
				throw new SQLException("Can't lookup : " + name + " : " + en.toString());
			} catch (Exception ex) {
				throw new SQLException("Can't get Connection Object : " + name + " : " + ex.toString());
			}
		} catch (Exception e) {
			throw new SQLException("Can't get Connection Object : " + name + "  : " + e.toString());
		}
		return con;
	}
	
	/**
	 * Use Local Naming JNDI datasource
	 */
	public Connection getConnection(String name) throws SQLException {
		Connection con = null;

			try {

				Context envCtx = (Context) initCtx.lookup("java:comp/env");
				DataSource ds = (DataSource) envCtx.lookup(name);

				con = ds.getConnection();
			} catch (NamingException  en) {
				throw new SQLException("Can't lookup : " + name + " : " + en.toString());
			} catch (Exception ex) {
				throw new SQLException("Can't get Connection Object : " + name + " : " + ex.toString());
			}

		return con;
	}

}
