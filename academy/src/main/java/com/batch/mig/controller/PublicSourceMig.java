package com.batch.mig.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.documentum.fc.client.IDfDocument;
import com.documentum.fc.client.IDfSession;
import com.documentum.fc.common.DfException;
import com.documentum.fc.common.DfId;
import com.edms.batch.common.Configurations;
import com.edms.batch.common.DBUtil;
import com.edms.batch.common.DfSessionManager;
import com.edms.batch.mig.vo.MigClassVO;

public class PublicSourceMig {

	private DfSessionManager dfSessionManager;
	private Connection conn;
	private Configurations config;

	String RootCabinetPath = "/Categorization";
	

	public PublicSourceMig() {

	}

	public static void main(String[] args) {

		try {
			PublicSourceMig cmfdb = new PublicSourceMig();
			cmfdb.start();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}


	private void start() {
		
		IDfSession session = null;

		try {
			
			config = new Configurations();
			config.getConfig();

			conn = DBUtil.getAWSConnection(config);
			
			dfSessionManager = new DfSessionManager(config);
			session = dfSessionManager.getSession(config);	

			List list = getDocList();
						
			
			for(int i=0; i<list.size(); i++) {
				String[] data = (String[])list.get(i);
		
				System.out.println("[" + i + "] " + data[0] + " .. " +  data[1] );
				createClass(session, i, data);
			}
			 

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			
			try {
				if (dfSessionManager != null) dfSessionManager.disconnectSession();

				if (conn != null)DBUtil.releaseConnection(conn);
				conn = null;
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	} // start

	
	
	private void createClass(IDfSession session, int index, String[] data) throws Exception {
		
		String r_object_id = "";
		String mapCode = "";
							
		try {	
			
			if( data != null ) {
				
				r_object_id = data[0];
				mapCode = data[1];
				
				if( mapCode != null && mapCode.length() > 2) {
					
					IDfDocument obj = (IDfDocument)session.getObject(new DfId(r_object_id) );
			
					String[] map = mapCode.split(",");
					
					for( int i=0; i<map.length; i++) {
						obj.appendString("u_pub_source", map[i]);
					}
					
					obj.save();
				}

			}
			
		} catch (DfException e) {
			e.printStackTrace();
		}
			
		
	}

	
	
	
	private List getDocList() throws Exception {
		ResultSet rs = null;
		PreparedStatement pstmt = null;

		String sql = "";
		
		sql = " select \n";
		sql += " 	c.r_object_id  \n";
		sql += " 	,max(b.mapcode) mapcode\n";
		sql += " from  \n";
		sql += " 	ud_tbl_mig_archive_raw4 a, \n";
		sql += " 	ud_tbl_mig_public b, \n";
		sql += " 	ud_document_s c \n";
		sql += " where  \n";
		sql += " 	a.class_1 = b.source1  \n";
		sql += " 	and a.class_2 = b.source2  \n";
		sql += " 	and c.u_project_cd = a.project_id  \n";
		sql += " 	and a.project_id  > '20220616'  \n";
		sql += " group by c.r_object_id  \n"; 


		List list = new ArrayList();
		
		try {
			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			MigClassVO classVo = null;
			
			while (rs.next()) {
				String data[] = new String[2];
				
				data[0] = rs.getString("r_object_id");
				data[1] = rs.getString("mapcode");
				
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
		
		return list;
		
	}			
	
	
	

}
