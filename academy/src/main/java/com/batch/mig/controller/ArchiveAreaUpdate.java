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

public class ArchiveAreaUpdate {
	
	private DfSessionManager dfSessionManager;
	
	private Connection conn;
	private Configurations config;
	List list = new ArrayList();
	
	private String MIG_SUBJECT = "mig_archive5";
	
	public static void main(String[] args) {

		
		ArchiveAreaUpdate job = new ArchiveAreaUpdate();
		
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
			
			System.out.println("list.size() = " + list.size() );
			
			//for(int i=0; i<list.size(); i++) {
			//	String r_object_id = (String)list.get(i);
				
			//	if( i%20==0)
			//		System.out.println(i + "/" + list.size() + "] " + r_object_id );
								
				changeToUdDocument(session, "09035cf680063312");
				
			//}
			
			
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
	
	
	
	public void changeToUdDocument(IDfSession idfSession, String r_object_id)  throws Exception  {
		IDfDocument object = null;
		
		try {
			
			object = (IDfDocument) idfSession.getObject( new DfId(r_object_id));
			
			if( object != null ) {
				List aList = getListArea(r_object_id);

				//if( aList != null && aList.size() > 0) {
					object.removeAll("u_area");
				
					for(int i=0; i<aList.size(); i++) {
						object.appendString("u_area", (String)aList.get(i));
					}
										
					object.setString("log_entry", "archive5");
					
					object.save();
				//}
				
				
			}
			
		} catch (DfException e) {
			e.printStackTrace();
		}
		
	}	
	
	public List getListArea(String r_object_id)  throws Exception  {
		String sql = "";
		List aList = new ArrayList();
		
		sql = " select u_area from ud_document_rp  where r_object_id ='" + r_object_id + "' and u_area is not null and u_area != '101010' \n";		
		//if( list != null ) list.clear();
		
		ResultSet rs = null;
		PreparedStatement pstmt = null;

		
		try {
			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				aList.add(rs.getString("u_area"));
				
			}
			
			
			rs.close(); rs = null; 
			pstmt.close(); pstmt = null;
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) rs.close(); rs = null; 
			if (pstmt != null) pstmt.close(); pstmt = null;			
		}	
		
		return aList;
	}
	
	
	public void listMigShare(Connection conn)  throws Exception  {
		String sql = "";
		
		// 분류추출상에는 분류코드:101010 (국내)는 없고 다른 권역이 있을 경우, u_area에서 국내 data 삭제 
		sql = " select r_object_id from ud_document_rp where r_object_id in ( \n";
		sql += " 	select ecmid from  ud_tbl_sqs_response  \n";
		sql += " 	where ecmid in ( \n";
		sql += " 		select r_object_id  from ud_document_sp where subject like 'mig_arc%'  \n";
		sql += " 	) \n";
		sql += " 	and classfication not like '%\"101010\"%' and classfication like '%\"1010__\"%' \n";
		sql += " ) and u_area ='101010' \n";		
		
		
		/*
		sql = " select r_object_id from ud_document_rp where r_object_id in ( \n";
		sql += " select r_object_id from ud_document_sp where object_name in ( \n";
		sql += " 	select subject from  ud_tbl_sqs_response  \n";
		sql += " 	where subject in ( \n";
		sql += " 		select object_name  from ud_document_sp where subject like 'mig_research'  \n";
		sql += " 	) \n";
		sql += " 	and classfication not like '%\"101010\"%'  \n";
		sql += " ) and  subject like 'mig_research' ) \n";
		sql += " and u_area ='101010' order by 1\n";
		*/
		
		/*
		sql = " select r_object_id from ud_document_rp where r_object_id in ( \n";
		sql += " 	select ecmid from  ud_tbl_sqs_response  \n";
		sql += " 	where ecmid in ( \n";
		sql += " 		select r_object_id  from ud_document_sp where subject = 'mig_research' and log_entry not in ('research5','research6') \n";
		sql += " 	) \n";
		sql += " 	and classfication not like '%\"101010\"%' \n";
		sql += " ) and u_area ='101010' \n";
		*/
		
		/*
		sql = " select r_object_id from ud_document_rp where r_object_id in ( \n"; 
		sql += " select r_object_id from ud_document_sp where object_name in (   \n"; 
		sql += " 	select subject from  ud_tbl_sqs_response    \n"; 
		sql += " 	where subject in (   \n"; 
		sql += " 		select object_name  from ud_document_sp where subject not like 'NGV%' and u_archive_class ='out' \n"; 
		sql += " 	)   \n"; 
		sql += " 	and classfication not like '%\"101010\"%'    \n"; 
		sql += " ) and  subject not like 'NGV%' and u_archive_class ='out' )   \n"; 
		sql += " and u_area ='101010'  \n"; 
		sql += " order by 1  \n"; 
		*/
		
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