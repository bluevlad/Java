package com.batch.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {

	public static String db_user			= "";
	public static String db_password		= "";
	public static String db_url				= "jdbc:postgresql://";
	public static String db_driver			= "org.postgresql.Driver";


	public static String aws_db_user		= "";
	public static String aws_db_password	= "demo.demo";
	public static String aws_db_url			= "jdbc:postgresql://";
	public static String aws_db_driver		= "org.postgresql.Driver";

	
	public static Connection getConnection(Configurations config) throws ClassNotFoundException, SQLException{
		Connection conn = null;

		db_url = config.getProperty("db.url");
		db_user = config.getProperty("db.user");
		db_password = config.getProperty("db.password");
		db_driver = config.getProperty("db.driver");
		Class.forName(db_driver);
		conn = DriverManager.getConnection(db_url, db_user, db_password);
				
		return conn;
	}
	
	public static Connection getAWSConnection(Configurations config) throws ClassNotFoundException, SQLException{
		Connection conn = null;
		aws_db_url = config.getProperty("aws_db_url");
		aws_db_user = config.getProperty("aws_db_user");
		aws_db_password = config.getProperty("aws_db_password");
		aws_db_driver = config.getProperty("aws_db_driver");
		Class.forName(aws_db_driver);
		conn = DriverManager.getConnection(aws_db_url, aws_db_user, aws_db_password);
				
		return conn;
	}	
	
	/**
	 * horn DB Connection return 
	 * @return
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	public static Connection getEconConnection(Configurations config) throws ClassNotFoundException, SQLException{
		Connection conn = null;
				
		Class.forName(db_driver);
		conn = DriverManager.getConnection(db_url, db_user, db_password);
				
		return conn;
	}
	
	/**
	 * DB Connectoin close
	 * @param conn
	 */
	public static void releaseConnection(Connection conn){
		try {
			if(conn!=null) conn.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void releaseConnection(Connection conn, Statement stmt, ResultSet rset){
		try {			
			if(stmt!=null) stmt.close();
			if(rset!=null) rset.close();
			if(conn!=null) conn.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void releaseConnection(Connection conn, PreparedStatement pstmt, ResultSet rset){
		try {			
			if(pstmt!=null) pstmt.close();
			if(rset!=null) rset.close();
			if(conn!=null) conn.close();

		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void releaseConnection(Statement stmt, ResultSet rset){
		try {			
			if(stmt!=null) stmt.close();
			if(rset!=null) rset.close();

		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
