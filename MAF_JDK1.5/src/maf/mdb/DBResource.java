/*
 * @(#) DBResource.java 1.0, 2004. 9. 14.
 * 
 * Copyright (c) 2004 (��)�̷��� All rights reserved.
 */
package maf.mdb;

import java.sql.Connection;
import java.sql.SQLException;

import maf.mdb.support.DBConnectionDBCPPool;
import maf.mdb.support.DBConnectionJNDIPool;
import miraenet.MiConfig;


public class DBResource {

	/**
	 * poolName�� �ش��ϴ� Database Connection�� ����
	 * 
	 * @param poolName
	 * @return
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException {
		return getConnection(MiConfig.DEFAULT_DB_POOL_NAME);
	}

	public static Connection getConnection(String poolName) throws SQLException {
		if (poolName == null) {
			poolName = MiConfig.DEFAULT_DB_POOL_NAME;
		}
		DBConnectionPool dbpool = null;
		if ("JNDI".equals(MiConfig.DEFAULT_DB_POOL_TYPE)) {
			dbpool = new DBConnectionJNDIPool();
		} else {
			dbpool = new DBConnectionDBCPPool();
		}
		return dbpool.getConnection(poolName);
	}
}