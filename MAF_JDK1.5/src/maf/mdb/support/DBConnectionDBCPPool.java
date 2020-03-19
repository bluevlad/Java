/*
 * Created on 2006. 3. 29.
 *
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package maf.mdb.support;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import maf.mdb.DBConnectionPool;

public class DBConnectionDBCPPool extends DBConnectionPool {


	public Connection getConnection(String name) throws SQLException {
    	// DB.xml을 이용하는 경우
    	return DriverManager.getConnection("jdbc:apache:commons:dbcp:" + name);

	}

}
