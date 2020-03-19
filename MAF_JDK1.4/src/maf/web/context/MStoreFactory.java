/*
 * Created on 2006. 4. 13.
 *
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package maf.web.context;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;

import maf.configuration.CodeDetailBean;
import maf.configuration.CodeInfo;
import maf.configuration.SiteCodeDetailBean;
import maf.logger.Logging;
import maf.mdb.DBResource;

public class MStoreFactory {
	private static MStoreFactory instance = null;
	private MStoreFactory() {
	}
	
	public static synchronized MStoreFactory getInstance() {
		if (instance == null) {
			instance = new MStoreFactory();
		}
		return instance;
	}
	
	public  synchronized  void configure( ) {
    	System.out.println("\n### " + this.getClass() + " configure start ### ");
		try {
	    	MakeConfDetMap();
//	    	MakeTableDictionaeyMap();
			System.out.println("### " + this.getClass() + " configure finish ### ");
		} catch (Exception e) {
			Logging.trace(e);
			System.out.println("### " + this.getClass() + " configure fail ### ");
		}
    }
    
    /** 
     * CodeMap 만들기
     * group_cd 의 list 로 만듬 
     * @throws ServletException
     */
    private void MakeConfDetMap() throws Exception {
    	Connection conn = null;
		PreparedStatement stmt = null;    	
    	ResultSet rs = null;
    	List codeList = new ArrayList(); 
    	
//    	final String sql = "  select group_cd,  code_no, code_name, bigo, seq"   +
//	    	"  from maf_code_det where active_yn = 'Y'"   +
//	    	"  order by group_cd,  SEQ, code_no"  ;
    	final String sql = " SELECT   group_cd, code_no, 'Y' def, '' lang, code_name, bigo, seq"   +
	    	"     FROM maf_code_det"   +
	    	"    WHERE active_yn = 'Y'"   +
	    	" UNION ALL"   +
	    	" SELECT   d.group_cd, d.code_no, 'N' def, l.lang, l.code_name, '' bigo, d.seq"   +
	    	"     FROM maf_code_det d, maf_code_det_lang l"   +
	    	"    WHERE d.active_yn = 'Y' AND d.group_cd = l.group_cd AND d.code_no = l.code_no"   +
	    	" ORDER BY group_cd,  seq, code_no"  ;
        try {
             conn = DBResource.getConnection();
             stmt = conn.prepareStatement(sql);
             rs = stmt.executeQuery();
             CodeDetailBean bean = null;
             while (rs.next()) {
            	  
            	 if("Y".equals(rs.getString("def"))) {
	            	 bean = new CodeDetailBean();
	            	 bean.setGroupCD(rs.getString("group_cd"));
	            	 bean.setCodeNo(rs.getString("code_no"));
	            	 bean.setCodeName(rs.getString("code_name"));
	            	 bean.setBigo(rs.getString("bigo"));
	            	 bean.setSeq(rs.getString("seq"));
	            	 codeList.add(bean);
            	 } else {
            		 bean.addLocaleString(rs.getString("lang"), rs.getString("code_name"));
            	 }
            	 
             }
        } catch (Throwable e) {
            throw new Exception(e.getMessage(), e);
        } finally {
        	if (rs != null) {try {rs.close();} catch (Exception e) {};}
			if (stmt != null) {try {stmt.close();} catch (Exception e) {};}
			if (conn != null) {try {conn.close();} catch (Exception e) {};}
			rs = null;
			stmt = null;
			conn = null;
        }
        
        CodeInfo codeInfo = new CodeInfo(codeList) ;
        
        MStore.getInstance().setConfig(MStore.KEY_CODE_DET_MAP, codeInfo);
        System.out.println("### MakeConfDetMap finish ### ");
    }
    /** 
     * CodeMap 만들기
     * site|group_cd 의 list 로 만듬 
     * @throws ServletException
     */
    private void MakeConfSiteDetMap() throws Exception {
    	Connection conn = null;
		PreparedStatement stmt = null;    	
    	ResultSet rs = null;
    	List codeList = new ArrayList(); 
    	
    	final String sql = "  select group_cd, site, code_no, code_name, bigo, seq"   +
	    	"  from maf_code_det"   +
	    	"  order by group_cd, site, SEQ, code_no"  ;
        try {
             conn = DBResource.getConnection();
             stmt = conn.prepareStatement(sql);
             rs = stmt.executeQuery();
             
             while (rs.next()) {
            	 SiteCodeDetailBean bean = new SiteCodeDetailBean();
            	 bean.setGroupCD(rs.getString("group_cd"));
            	 bean.setSite(rs.getString("site"));
            	 bean.setCodeNo(rs.getString("code_no"));
            	 bean.setCodeName(rs.getString("code_name"));
            	 bean.setBigo(rs.getString("bigo"));
            	 bean.setSeq(rs.getString("seq"));
            	 codeList.add(bean);
             }
        } catch (Throwable e) {
            throw new Exception(e.getMessage(), e);
        } finally {
        	if (rs != null) {try {rs.close();} catch (Exception e) {};}
			if (stmt != null) {try {stmt.close();} catch (Exception e) {};}
			if (conn != null) {try {conn.close();} catch (Exception e) {};}
			rs = null;
			stmt = null;
			conn = null;
        }
        
        CodeInfo codeInfo = new CodeInfo(codeList) ;
        
        MStore.getInstance().setConfig(MStore.KEY_CODE_DET_MAP, codeInfo);
        System.out.println("### MakeConfDetMap finish ### ");
    }  
//    private void MakeTableDictionaeyMap() throws ServletException {
//    	Connection conn = null;
//		PreparedStatement stmt = null;    	
//    	ResultSet rs = null;
//    	TableDictionary codeInfo = new TableDictionary();
//    	
//    	final String sql = "  SELECT table_nm, column_nm, site, title, bigo " +
//    				" FROM maf_dictionary "   +
//    				" order by site, table_nm, column_nm"  ;
//        try {
//             conn = DBResource.getConnection();
//             stmt = conn.prepareStatement(sql);
//             rs = stmt.executeQuery();
//             String site = null;
//             
//             Map CodeMap = null;
//
//             while (rs.next()) {
//
//            	 site = rs.getString("site");
//            	 if (codeInfo.containsKey(site)) {
//            		 CodeMap = (Map) codeInfo.getCodeMap(site);
//            	 } else {
//            		 CodeMap = new HashMap();
//            		 codeInfo.addSite(site,  CodeMap);
//            	 }
//            	 TableDictionaryBean bean = new TableDictionaryBean();
//            	 bean.setTableName(rs.getString("table_nm").toLowerCase());
//            	 bean.setColumnName(rs.getString("column_nm").toLowerCase());
//            	 bean.setSite(rs.getString("site"));
//            	 bean.setTitle(rs.getString("title"));
//            	 bean.setBigo(rs.getString("bigo"));
//            	 bean.setKey(bean.getTableName() +"."+bean.getColumnName());
//            	 CodeMap.put(bean.getKey(), bean);
//             }
//        } catch (Throwable e) {
//            throw new ServletException(e.getMessage(), e);
//        } finally {
//        	if (rs != null) {try {rs.close();} catch (Exception e) {};}
//			if (stmt != null) {try {stmt.close();} catch (Exception e) {};}
//			if (conn != null) {try {conn.close();} catch (Exception e) {};}
//			rs = null;
//			stmt = null;
//			conn = null;
//        }
//        
//        
//        MStore.getInstance().setConfig(MStore.KEY_TABLE_DICTIONARY, codeInfo);
//        System.out.println("### MakeTableDictionaeyMap finish ### ");
//    }
}

