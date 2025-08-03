package com.batch.mig.controller;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ResearchEconomyDBtoDB {

	public static String aws_db_user		= "HM";
	public static String aws_db_password	= "hmcki.prod@db1!";
	public static String aws_db_url		= "jdbc:postgresql://hkmc-mip-rds-ecm-prd.c9nxtm4kxfu6.ap-northeast-2.rds.amazonaws.com:11542/dm_HM_docbase";
	//public static String aws_db_url			= "jdbc:postgresql://hkmc-mip-rds-ecm-dev.ch1ejh2xsnko.ap-northeast-2.rds.amazonaws.com:11542/dm_HM_docbase";
	
	public static String aws_db_driver		= "org.postgresql.Driver";
	private static Connection miConn;
	
	public static void main(String[] args) {

        // Create a variable for the connection string.
        String connectionUrl = "jdbc:sqlserver://10.7.25.30:1433;databaseName=KARI_2009;user=autoway_search;password=GbicApr#01;encrypt=false";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection conn = null;


        String pbbs_no = null;
		System.out.println("start  : ResearchEconomyDBtoDB");
       
        	try {
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
				conn = DriverManager.getConnection(connectionUrl);
				
				miConn = getAWSConnection();
				
				String SQL ="";
				
				SQL = " SELECT \n";
				SQL += " 	no, \n";
				SQL += " 	SUBJECT, \n";
				SQL += " 	AUTHOR, \n";
				SQL += " 	REGUSER, \n";
				SQL += " 	REGDATE, \n";
				SQL += " 	PUBLICATIONDATE, \n";
				SQL += " 	SUMMARY, \n";
				SQL += " 	content, \n";
				SQL += " 	FILEID, \n";
				SQL += " 	PBBSMASTERID, \n";
				SQL += " 	PBSMASTERNAME, \n";
				SQL += " 	DIVISIONCODE3, \n";
				SQL += " 	DIVISIONNAME3, \n";
				SQL += " 	DIVISIONCODE4, \n";
				SQL += " 	DIVISIONNAME4, \n";
				SQL += " 	URL, \n";
				SQL += " 	pbstablename FROM KARI_2009.dbo.SSO_PBBS where REGDATE > DATEADD(dd, -1, GETDATE()) order by no ";

				pstmt = conn.prepareStatement(SQL);
				
				rs = pstmt.executeQuery();

				// Iterate through the data in the result set and display it.
				while (rs.next()) {
					migData(rs);
				}

				
    		} catch (ClassNotFoundException e) {
    			e.printStackTrace();
    		} catch (SQLException e) {
    			e.printStackTrace();
			} finally {
				try {
					if (rs != null) rs.close(); rs = null; 
					if (pstmt != null) pstmt.close(); pstmt = null;						
					if (conn!=null) conn.close();
					conn = null;
					
					if (miConn!=null) miConn.close();
					miConn = null;
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}


    }
	
	
	
	public static void migData(ResultSet rs) {
		
		String pbbs_no = null;
		String pbstablename = null;
        
        PreparedStatement miPstmt = null;
        ResultSet miRs = null;
 
		String iSQL = "insert into ud_tbl_ecnc_ids_rsst_ctr_eai values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,current_timestamp,?)";

		int index=1;
        
		if( rs != null ) {
			
			try {
				
				pbbs_no = rs.getString("no");
				pbstablename = rs.getString("pbstablename");
				System.out.println(" no : " + pbbs_no + " pbstablename : " + pbstablename);
				
				
				String SQL = "select no from ud_tbl_ecnc_ids_rsst_ctr_eai where no = ? and pbstablename = ?";
				miPstmt = miConn.prepareStatement(SQL);
				miPstmt.setString(1, pbbs_no);
				miPstmt.setString(2, pbstablename);
				miRs = miPstmt.executeQuery();
				index = 1;
				
				if( miRs.next() ) {
					
				} else {
					if (miRs != null) miRs.close(); miRs = null; 
					if (miPstmt != null) miPstmt.close(); miPstmt = null;
					miPstmt = miConn.prepareStatement(iSQL);
					
					miPstmt.setString(index, rs.getString(index++));
					miPstmt.setString(index, rs.getString(index++));
					miPstmt.setString(index, rs.getString(index++));
					miPstmt.setString(index, rs.getString(index++));
					miPstmt.setString(index, rs.getString(index++));
					miPstmt.setString(index, rs.getString(index++));
					miPstmt.setString(index, rs.getString(index++));
					miPstmt.setString(index, rs.getString(index++));
					miPstmt.setString(index, rs.getString(index++));
					miPstmt.setString(index, rs.getString(index++));
					miPstmt.setString(index, rs.getString(index++));
					miPstmt.setString(index, rs.getString(index++));
					miPstmt.setString(index, rs.getString(index++));
					miPstmt.setString(index, rs.getString(index++));
					miPstmt.setString(index, rs.getString(index++));
					miPstmt.setString(index, rs.getString(index++));
					miPstmt.setString(index, rs.getString(index++));
					
					miPstmt.executeUpdate();
					
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if (miRs != null) miRs.close(); miRs = null; 
					if (miPstmt != null) miPstmt.close(); miPstmt = null;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			
		}
		
		
		
		
	}
	
	
	
	public static Connection getAWSConnection() throws ClassNotFoundException, SQLException{
		Connection conn = null;

		Class.forName(aws_db_driver);
		conn = DriverManager.getConnection(aws_db_url, aws_db_user, aws_db_password);
				
		return conn;
	}
	
		
}