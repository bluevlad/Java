/*
 * @(#) DBResource.java 1.0, 2004. 9. 14.
 * 
 * Copyright (c) 2004 UBQ All rights reserved.
 */
package maf.mdb;

import java.sql.Connection;
import java.sql.SQLException;

import maf.MafProperties;
import maf.mdb.support.DBConnectionDBCPPool;
import maf.mdb.support.DBConnectionJNDIPool;



public class DBResource {

	/**
	 * poolName에 해당하는 Database Connection을 리턴
	 * 
	 * @param poolName
	 * @return
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException {
		return getConnection(MafProperties.DEFAULT_DB_POOL_NAME);
	}

	/** 
	 * Connection 을 돌려 준다.
	 * MafProperties.USE_SMARTCON 을 True로 설정시 AmsrtConection 사용 
	 * @param poolName
	 * @return
	 * @throws SQLException
	 */
	public static Connection getConnection(String poolName) throws SQLException {
		if (poolName == null) {
			poolName = MafProperties.DEFAULT_DB_POOL_NAME;
		}
		DBConnectionPool dbpool = null;
		if ("JNDI".equals(MafProperties.DEFAULT_DB_POOL_TYPE)) {
			dbpool = new DBConnectionJNDIPool();
		} else {
			
			dbpool = new DBConnectionDBCPPool();
		}
		if(MafProperties.USE_SMARTCON) {
			return new SmartConnection(dbpool.getConnection(poolName));
		} else {
			return dbpool.getConnection(poolName);
		}
	}
}