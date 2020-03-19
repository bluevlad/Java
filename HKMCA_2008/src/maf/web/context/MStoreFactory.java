/*
 * Created on 2006. 4. 13.
 *
 * Copyright (c) 2004 UBQ All rights reserved.
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
    	
    	final String sql = "select group_cd, code_no, code_name, bigo, seq" +
	    	" from maf_code_det where active_yn = 'Y'" +
	    	" order by group_cd, SEQ, code_no";
        try {
             conn = DBResource.getConnection();
             stmt = conn.prepareStatement(sql);
             rs = stmt.executeQuery();
             CodeDetailBean bean = null;
             while (rs.next()) {
            	 bean = new CodeDetailBean();
            	 bean.setGroupCD(rs.getString("group_cd"));
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
}

