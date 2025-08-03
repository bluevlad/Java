package com.batch.mig.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.edms.batch.common.Configurations;
import com.edms.batch.common.DBUtil;

public class MigOwnerDeptUpdate {
	
	private Connection conn;
	private Configurations config;
	
	private List list = new ArrayList();
	
	public static void main(String[] args) {
		
		MigOwnerDeptUpdate job = new MigOwnerDeptUpdate();

		job.migList();
		
	}
	

	public void migList() {

		config = new Configurations();
		config.getConfig();

		
		try {	
			
			conn = DBUtil.getAWSConnection(config);
			
			listMigData(conn);
			
			System.out.println("list.size() = " + list.size() );
			
			String data[] = new String[3];
			
			for(int i=0; i<list.size(); i++) {
				
				data = (String[])list.get(i);
				
				if( i%50 == 0)
					System.out.println("[" +  i + "] "+ data[0] + " > " + data[2]);
			
				updateDateSQL(conn, data);
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
	

	public void updateDateSQL(Connection conn, String data[])  throws Exception  {

		String owerDept_cd = "";
		String owerName = "";
		String owerId= "";
		String r_object_id = "";
		
		r_object_id = data[0];
		owerDept_cd = data[1];
		owerId = data[2];
		
	//	if( owerName != null && "null".equals(owerName) ) {
				
				String sql = "update ud_document_s set u_owner_group ='" + owerDept_cd + "', u_owner_user = '" + owerId + "'  where r_object_id ='" + r_object_id + "' ";
				
				PreparedStatement pstmt = null;
				
				try {
					
					pstmt = conn.prepareStatement(sql);
					pstmt.executeUpdate();
					pstmt.close();
					
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					try {
						if( pstmt != null )  {pstmt.close(); }
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				
		//}
		
		
	}
	
	
	public void listMigData(Connection conn)  throws Exception  {
		String sql = "";
		
		/*
		sql = "select \n";
		sql += " 	a.project_dept, \n";
		sql += " 	b.r_object_id \n";
		sql += " from  \n";
		sql += " 	ud_tbl_mig_archive_raw2 a, \n";
		sql += " 	ud_document_s b \n";
		sql += " where a.project_id  = b.u_project_cd \n";
		//sql += " and b.r_object_id ='09035cf68000799e' \n";
		sql += " order by b.r_object_id \n";
		*/
		
		
		sql = " select \n";
		sql += " 	a.project_dept_cd  \n";
		//sql += " 	,a.user_id as org_user_id \n";
		//sql += " 	,a.duty_name \n";
		sql += " 	,(select user_name from ud_user_sp where user_name = a.user_id) user_id \n";
		sql += " 	,(select description from ud_user_sp where user_name = a.user_id) user_name \n";
		sql += " 	,b.r_object_id \n";
		//sql += " 	,b.u_owner_user \n";
		//sql += " 	,b.u_owner_group \n";
		sql += " from  \n";
		sql += " 	ud_tbl_mig_archive_raw4 a, \n";
		sql += " 	ud_document_sp b \n";
		sql += " where  \n";
		sql += " 	a.project_id  = b.u_project_cd 	and a.project_id  > '20220616'  \n";
		
		
		/*
		sql = " select \n";
		sql += " 	b.r_object_id, \n";
		sql += " 	(select user_name from ud_user_sp where user_name = a.create_eeno) user_id  , \n";
		sql += " 	(select user_group_name from ud_user_sp where user_name = a.create_eeno)  project_dept_cd   \n"; 
		sql += " from  \n";
		sql += " 	ud_tbl_mig_shareplatform4 a, \n";
		sql += " 	ud_document_sp b \n";
		sql += " where a.rpt_sn  = b.u_project_cd  \n";
		*/
		
		
		if( list != null ) list.clear();
		
		ResultSet rs = null;
		PreparedStatement pstmt = null;

		
		try {
			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				String data[] = new String[3];
				
				data[0] = rs.getString("r_object_id"); 
				data[1] = rs.getString("project_dept_cd");  
				data[2] = rs.getString("user_id");  
				
				list.add(data);				
			}
			
			
			rs.close(); rs = null; 
			pstmt.close(); pstmt = null;
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) rs.close(); rs = null; 
			if (pstmt != null) pstmt.close(); pstmt = null;			
		}
		
	}
	

}