/*
 * Created on 2006. 07. 24
 *
 * Copyright (c) 2004 UBQ All rights reserved.
 */
package maf.mdb;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class DBConnectionPool {
//	public abstract DBConnectionPool getInstance(); 
	public abstract Connection getConnection(String name) throws SQLException ;
}

