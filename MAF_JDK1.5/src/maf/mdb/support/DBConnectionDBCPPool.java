/*
 * Created on 2006. 3. 29.
 *
 * Copyright (c) 2004 (��)�̷��� All rights reserved.
 */
package maf.mdb.support;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import maf.mdb.DBConnectionPool;

public class DBConnectionDBCPPool extends DBConnectionPool {


	public Connection getConnection(String name) throws SQLException {
    	// DB.xml�� �̿��ϴ� ���
    	return DriverManager.getConnection("jdbc:apache:commons:dbcp:" + name);

	}

}
