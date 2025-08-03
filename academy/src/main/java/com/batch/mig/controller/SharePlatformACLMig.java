package com.batch.mig.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.jsoup.Jsoup;

import com.documentum.fc.client.IDfACL;
import com.documentum.fc.client.IDfDocument;
import com.documentum.fc.client.IDfSession;
import com.documentum.fc.common.DfException;
import com.documentum.fc.common.DfId;
import com.documentum.fc.impl.util.StringUtil;
import com.edms.batch.common.Configurations;
import com.edms.batch.common.DBUtil;
import com.edms.batch.common.DfSessionManager;
import com.edms.batch.mig.vo.MIContentsVO;
import com.edms.batch.mig.vo.ResearchEconomyVO;
import com.edms.batch.mig.vo.SharePlatfromVO;

public class SharePlatformACLMig {
	
	private DfSessionManager dfSessionManager;
	
	private Connection conn;
	private Configurations config;
	List list = new ArrayList();
	
	private String MIG_SUBJECT = "mig_share4";
	
	public static void main(String[] args) {

		
		SharePlatformACLMig job = new SharePlatformACLMig();
		
		job.migList();
				
	}
	

	public void migList() {
		IDfSession session = null;
		config = new Configurations();
		config.getConfig();

		
		try {
					
			dfSessionManager = new DfSessionManager(config);
			session = dfSessionManager.getSession(config);			
			
			conn = DBUtil.getAWSConnection(config);
			
			listMigShare(conn);
			
			System.out.println("attachList.size() = " + list.size() );			
			for(int i=0; i<list.size(); i++) {
				String r_object_id = (String)list.get(i);
				
				System.out.println(i + "		" + r_object_id );
								
				changeToUdDocument(session, r_object_id);
				
			}
			
			
			listRootMigShare(conn);
			System.out.println("rootList.size() = " + list.size() );
			
			for(int i=0; i<list.size(); i++) {
				String r_object_id = (String)list.get(i);
				
				System.out.println(i + "		" + r_object_id );
								
				changeToUdDocument(session, r_object_id);
				
			}		
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {			
			try {
				if (dfSessionManager != null) dfSessionManager.disconnectSession();

				if (conn != null)DBUtil.releaseConnection(conn);
				conn = null;
			} catch (Exception e) {
				e.printStackTrace();
			}

		}		
	}
	
	
	
	public void changeToUdDocument(IDfSession idfSession, String r_object_id) {
		IDfDocument object = null;
		
		try {
			
			object = (IDfDocument) idfSession.getObject( new DfId(r_object_id));
			
			if( object != null ) { 

				if( object.getPermitEx("gr_consult_add_user") < IDfACL.DF_PERMIT_RELATE ) {
					object.grant("gr_consult_add_user",IDfACL.DF_PERMIT_RELATE, null);
				
					object.save();
				}
			}
			
		} catch (DfException e) {
			e.printStackTrace();
		}
		
		
	}	
	
	
	public void listMigShare(Connection conn)  throws Exception  {
		String sql = "";
		
		sql = " select r_object_id from ud_doc_attach_sp where u_root_obj_id  in ( \n";
		sql += " 	select r_object_id from ud_document_sp where subject = 'mig_share' and u_project_cd in ( \n";
		sql += " 	  select rpt_sn from ud_tbl_mig_shareplatform where read_team_code ='ALL' or read_team_code = '21000,37540,29000,37181,37556,34669,36753' \n";
		sql += " 	) \n";
		sql += " ) \n";
		
		//sql = " select r_object_id from ud_doc_attach_sp where u_root_obj_id  in ( select r_object_id from ud_document_sp where subject = 'mig_share' ) \n";
		
		//sql = " select r_object_id from ud_doc_attach_sp where u_root_obj_id  in ('09035cf680063f71','09035cf680064218','09035cf680063b65' ) \n";

		
		if( list != null ) list.clear();
		
		ResultSet rs = null;
		PreparedStatement pstmt = null;

		
		try {
			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				list.add(rs.getString("r_object_id"));
				
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

	public void listRootMigShare(Connection conn)  throws Exception  {
		String sql = "";
		

		sql = " select r_object_id from ud_document_sp where subject = 'mig_share' and u_project_cd in ( \n";
		sql += " 	  select rpt_sn from ud_tbl_mig_shareplatform where read_team_code ='ALL' or read_team_code = '21000,37540,29000,37181,37556,34669,36753' \n";
		sql += " ) \n";
		
	//	sql = " select r_object_id from ud_document_sp where subject = 'mig_share' \n";
	//	sql = " select r_object_id from ud_document_sp where r_object_id in ('09035cf680063f71','09035cf680064218','09035cf680063b65' ) \n";
		
		if( list != null ) list.clear();
		
		ResultSet rs = null;
		PreparedStatement pstmt = null;

		
		try {
			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				list.add(rs.getString("r_object_id"));
				
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