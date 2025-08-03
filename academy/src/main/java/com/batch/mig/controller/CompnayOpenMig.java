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

public class CompnayOpenMig {

	private DfSessionManager dfSessionManager;
	private Connection conn;
	private Configurations config;

	String RootCabinetPath = "/Categorization";
	

	public CompnayOpenMig() {

	}

	public static void main(String[] args) {

		try {
			CompnayOpenMig cmfdb = new CompnayOpenMig();
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
		
				System.out.print("[" + i + "] " + data[0] + " .. " +  data[1] );
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
							
		String brandData = "";
		
		try {	
			
			if( data != null ) {
				
				r_object_id = data[0];
				mapCode = data[1];
				
				if( mapCode != null && mapCode.length() > 0) {
					
					IDfDocument obj = (IDfDocument)session.getObject(new DfId(r_object_id) );
			
					mapCode = mapCode.replace("|",",");
					
					obj.removeAll("u_view_company");
					obj.removeAll("u_in_pub_bland");
					
					if( mapCode.indexOf(",") > 0 ) {
						
						String[] map = mapCode.split(",");
						
						for( int i=0; i<map.length; i++) {
							
							if(  !"".equals(map[i]) ) {
								
								if( "H".equals(map[i])) { map[i] = "202010"; obj.appendString("u_in_pub_bland", map[i] ); obj.appendString("u_view_company", map[i] );}
								if( "K".equals(map[i])) { map[i] = "202020"; obj.appendString("u_in_pub_bland", map[i] ); obj.appendString("u_view_company", map[i] ); }
								
								brandData += map[i] + ",";
								
								System.out.print(".." + map[i]);
	
							}
							
						}
						if( brandData.indexOf(",")> 01 ) {
							brandData =brandData.substring(0,brandData.length()-1);
						}
					} else {
						
						if( "H".equals(mapCode)) { mapCode = "202010"; obj.appendString("u_in_pub_bland", mapCode ); obj.appendString("u_view_company", mapCode );}
						if( "K".equals(mapCode)) { mapCode = "202020"; obj.appendString("u_in_pub_bland", mapCode ); obj.appendString("u_view_company", mapCode ); }
						
						brandData = mapCode;
						
						System.out.print(".." + mapCode);
					}
					
					System.out.println("");
					
					obj.save();
					
					String[] bland = new String[2];
					bland[0] = r_object_id;
					bland[1] = brandData;
					
					updatePubBlandSQL(bland);
					
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
		sql += " 	b.r_object_id, \n";
		sql += " 	replace(replace(a.open_type,'A|',''),'|A','') open_type \n";
		sql += " from  \n";
		sql += " 	(	 \n";
		sql += " 		select  \n";
		sql += " 			project_id, \n";
		sql += " 			max(open_type) as open_type \n";
		sql += " 		from  \n";
		sql += " 		ud_tbl_mig_archive_raw4 \n";
		sql += " 		group by project_id \n";
		sql += " 	) a, \n";
		sql += " 	ud_document_sp b \n";
		sql += " where  \n";
		sql += " 	b.u_project_cd = a.project_id \n";
		sql += " 	and open_type  not in ('','A') and a.project_id  > '20220616'  \n"; 
		//sql += " and b.r_object_id = '09035cf68003bfe9' ";

		List list = new ArrayList();
		
		try {
			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			MigClassVO classVo = null;
			
			while (rs.next()) {
				String data[] = new String[2];
				
				data[0] = rs.getString("r_object_id");
				data[1] = rs.getString("open_type");
				
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
	
	
	
	public void updatePubBlandSQL(String data[])  throws Exception  {


		String pub_bland = "";
		String r_object_id = "";
		
		r_object_id = data[0];
		pub_bland = data[1];
		
		String sql = "update v_mig_search1 set u_in_pub_bland ='" + pub_bland + "', u_event_type='u', u_event_date = now() where u_content_cd = '" + r_object_id + "' ";
		
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
		
	}	
	
	

}
