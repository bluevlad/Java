package com.academy.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {

	public static String db_user			= "root";
	public static String db_password		= "dnflskfk";
	public static String db_url				= "jdbc:log4jdbc:mysql://127.0.0.1:3306/acm_basic";
	public static String db_driver			= "net.sf.log4jdbc.DriverSpy";

	public static Connection getConnection(Configurations config) throws ClassNotFoundException, SQLException{
		Connection conn = null;

		db_url = config.getProperty("Globals.mysql.Url");
		db_user = config.getProperty("Globals.mysql.UserName");
		db_password = config.getProperty("Globals.mysql.Password");
		db_driver = config.getProperty("Globals.mysql.DriverClassName");
		Class.forName(db_driver);
		conn = DriverManager.getConnection(db_url, db_user, db_password);
				
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
