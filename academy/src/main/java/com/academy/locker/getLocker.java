package com.academy.locker;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.simple.JSONObject;

import com.academy.common.Configurations;
import com.academy.common.DBUtil;

public class getLocker {
	
    private static PrintWriter out;
	private Connection conn;
	private Configurations config;
	JSONObject jsonObject = new JSONObject();
	
	public static void main(String[] args) {

		getLocker list = new getLocker();
		list.getBoxList();
				
	}

	public void getBoxList() {
		config = new Configurations();
		String box_cd = "101";

		try {
					
			conn = DBUtil.getConnection(config);
			getBox(conn, box_cd);
			
			log("model.size() = " + jsonObject.size());

			for(int i=0; i<jsonObject.size(); i++) {
				log(jsonObject.values() + (String)jsonObject.get(i));
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {			
			try {
				if (conn != null)DBUtil.releaseConnection(conn);
				conn = null;
			} catch (Exception e) {
				e.printStackTrace();
			}

		}		
	}
	

	public void getBox(Connection conn, String box_cd)  throws Exception  {
		String sql = "";
		
		sql = "SELECT BOX_CD, BOX_NM, BOX_COUNT, BOX_PRICE, DEPOSIT, ROW_COUNT, ROW_NUM, UPD_DT, UPD_ID, START_NUM, END_NUM,";
		sql = sql + "(SELECT COUNT(BOX_NUM) FROM ACM_BOX_NUM WHERE BOX_CD = AB.BOX_CD AND BOX_FLAG = 'Y') USE_CNT,";
		sql = sql + "(SELECT COUNT(BOX_NUM) FROM ACM_BOX_NUM WHERE BOX_CD = AB.BOX_CD AND BOX_FLAG = 'N') NOT_CNT";
		sql = sql + " FROM ACM_BOX AB";
		sql = sql + " WHERE AB.BOX_CD = '101'";
		
		ResultSet rs = null;
		PreparedStatement pstmt = null;

		try {
			pstmt = conn.prepareStatement(sql);
			//pstmt.setString(1, box_cd);

			rs = pstmt.executeQuery();

			jsonObject.put("box_cd", rs.getString("BOX_CD"));
			jsonObject.put("box_nm", rs.getString("BOX_NM"));
			jsonObject.put("box_count", rs.getString("BOX_COUNT"));
			jsonObject.put("box_price", rs.getString("BOX_PRICE"));
			jsonObject.put("deposit", rs.getString("DEPOSIT"));
			jsonObject.put("row_count", rs.getString("ROW_COUNT"));
			jsonObject.put("row_num", rs.getString("ROW_NUM"));
			jsonObject.put("upd_dt", rs.getString("UPD_DT"));
			jsonObject.put("upd_id", rs.getString("UPD_ID"));
			jsonObject.put("start_num", rs.getString("START_NUM"));
			jsonObject.put("end_num", rs.getString("END_NUM"));
			jsonObject.put("use_cnt", rs.getString("USE_CNT"));
			jsonObject.put("not_cnt", rs.getString("NOT_CNT"));
			 
			rs.close(); rs = null; 
			pstmt.close(); pstmt = null;
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) rs.close(); rs = null; 
			if (pstmt != null) pstmt.close(); pstmt = null;			
		}
	}

    
    private static void log(String msg)
    {
    	System.out.println(msg);
    	if(out != null)
    	{
    		out.println(msg);
    	}
    }  
	
}