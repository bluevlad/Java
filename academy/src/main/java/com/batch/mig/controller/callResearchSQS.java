package com.batch.mig.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.documentum.fc.client.acs.impl.common.util.NameValuePair;
import com.edms.batch.common.Configurations;
import com.edms.batch.common.DBUtil;
import com.edms.batch.mig.vo.MIContentsVO;

public class callResearchSQS {
	private final static Logger log = Logger.getLogger(callResearchSQS.class);
	
	private static Connection conn;
	private static Configurations config;
	
	/***
	 * 배치 실행 영역
	 * @param args
	 */ 
	public static void main(String[] args) {

		System.out.println("callResearchSQS ... ");

		List<MIContentsVO> list = null;
		//===============================================
		// 환경 정보 로드
		//===============================================
		config = new Configurations();
		config.getConfig();		
		
		try {

			list = getDocList();
			System.out.println("\t[getDocList ]" +  list.size());
			
			if ( list != null ) {
				for (int i = 0; i < list.size(); i++) {
					
					MIContentsVO docVO = list.get(i);

					System.out.println("\t\t[" + i + "/" +  list.size() + "]" + docVO.getrObjectId() ); 
					
					// 분류코드 요청 호출 
					constructUrl(docVO.getrObjectId());
					
					Thread.sleep(100); 
					//break;
					
					updateSubject(docVO.getrObjectId());
				}
			}
			
			
			updateNGV();
			updateMiContents();
			
			//constructUrl("0906ea6080002ce2");
			
			
		} catch (Exception ex ) {
			ex.printStackTrace();
		}
	}
	

	public static void updateSubject(String r_object_id)  throws Exception  {

		
		String sql = "update dm_sysobject_s set subject ='mig_research' where r_object_id ='" + r_object_id +  "' and  subject = 'mig_research2' ";
		
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
	
	public static void updateNGV()  throws Exception  {

		
		String sql = "update  ud_document_s set u_in_pub_team_en = 'NGV'  where u_in_pub_team = 'NGV' and  u_in_pub_team_en  = ' ' ";
		
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
		
	
	public static void updateMiContents()  throws Exception  {

		
		String sql = "update  ud_document_s set u_in_pub_team = 'MI 컨텐츠', u_in_pub_team_en = 'MI Contents' where u_in_pub_team_en  = ' ' and u_in_pub_team != 'NGV' and u_archive_class != 'out' ";
		
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
			
	
	
	/***
	 * 분류코드 요청
	 * @param path
	 * @return
	 * @throws IOException
	 */
    public static String constructUrl(String objectId) throws IOException{
        String base_url 	= config.getProperty("mig.sqs.micl017_url"); // "http://127.0.0.1:8080/ecm/inter/cl/ifMIPCL017.json";
        String auth;
        
        StringBuffer response = new StringBuffer();
        try {        	
        	//===============================================
        	// 설정 정보 확인 및 로그
        	//===============================================
        	//printInfo("//=========================================================//");
        	//printInfo("base_url : " + base_url);
        	//printInfo("objectId : " + objectId);  
	        // printInfo("//=========================================================//");	          	
        	      
	        //===============================================
	        // 분류요청 URL
	        //===============================================
	        URL obj = new URL(base_url);
	        //HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
	        HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
	        conn.setRequestMethod("POST");
	        conn.setRequestProperty("Content-Type", "application/json; utf-8");
	        conn.setReadTimeout(30000);
	        conn.setConnectTimeout(15000);
	        conn.setDoInput(true);
	        conn.setDoOutput(true);
	        
	        //===============================================
	        // 파라메터 세팅
	        //===============================================
	        String jsonInputString = "{\"ecmId\": \"" + objectId + "\"}";
	        	        
	        conn.getOutputStream().write(jsonInputString.getBytes("UTF-8"));
            conn.getOutputStream().flush();
            conn.getOutputStream().close();
            
            conn.connect();

	        //===============================================
	        // 데이터 송수신
	        //===============================================
	        BufferedReader in = new BufferedReader(
	                new InputStreamReader(conn.getInputStream()));
	        String inputLine;	        
	
	        while ((inputLine = in.readLine()) != null) {
	            response.append(inputLine);
	            //System.out.println(inputLine);
	        }
	        in.close();
	        
	        //printInfo("//=========================================================//");
	        //printInfo("response " + response.toString() + " : end");
	        //printInfo("//=========================================================//");

        } catch (Exception ex ) {
        	ex.printStackTrace();
        }

        return response.toString();
    }
	
	
	/***
	 * 분류 요청할 리스트를 조회한다.
	 * @return 
	 * @throws Exception
	 */
	private static List<MIContentsVO> getDocList() throws Exception { 
		ResultSet rs = null;
		PreparedStatement pstmt = null; 

		StringBuffer sb = new StringBuffer();
		sb.append("	select r_object_id  from ud_document_sp where subject = 'mig_research2'  ");		

		// r_object_id
		List<MIContentsVO> list = new ArrayList<MIContentsVO>();
		
		try {
			conn  = DBUtil.getConnection(config); // db 연결
			pstmt = conn.prepareStatement(sb.toString());
 
			rs = pstmt.executeQuery();

			MIContentsVO docVO = null;
			
			while (rs.next()) {
				
				docVO = new MIContentsVO();
				docVO.setrObjectId(rs.getString("r_object_id")); 
				//docVO.setObjectName(rs.getString("object_name"));				
				
				list.add(docVO);
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
	
    /***
     * 로그를 남긴다.
     * @param sLog
     */
    private static void printInfo(String sLog) {
    	log.info(sLog);
    }	
    
    
    /***
     * 쿼리 param
     * @param params
     * @return
     * @throws UnsupportedEncodingException
     */
    private static String getQuery(List<NameValuePair> params) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        boolean first = true;

        for (NameValuePair pair : params){
            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(pair.getName(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(pair.getValue(), "UTF-8"));
        }
        
        //printInfo("//=============================================//");
        //printInfo(result.toString());
        //printInfo("//=============================================//");

        return result.toString();
    }    
}
