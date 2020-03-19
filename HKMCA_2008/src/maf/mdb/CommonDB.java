/*
 * CommonDB.java, @ 2005-05-12
 * 
 * Copyright (c) 2004 UBQ All rights reserved.
 */
package maf.mdb;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import maf.base.BaseHttpSession;
import maf.mdb.drivers.MdbDriver;



/**
 * @author bluevlad

 */
public class CommonDB {
	
	
    /**
     * PreparedStatement 阑 close 棺 null 贸府 
     * @param rs
     */
    protected static void release(PreparedStatement stmt) {
        try {
        	if (stmt != null) {stmt.close();}
        } catch (Throwable e) {} finally {}
        stmt = null;
    }
    
    
    
    /**
     * CallableStatement 阑 close 棺 null 贸府 
     * @param rs
     */
    protected static  void release(CallableStatement stmt) {
        try {if (stmt != null) {stmt.close();}} catch (Throwable e) {} finally {}
        stmt = null;
    }
    /**
     * ResultSet 阑 release 棺 null 贸府 
     * @param rs
     */
    protected static  void release(ResultSet rs) {
        try {if (rs != null) {rs.close();}
        } catch (Throwable e) {} finally {}
        rs = null;
    }
    
    /**
     * ResultSet 阑 release 棺 null 贸府 
     * @param rs
     */
    protected static  void release(Connection conn) {
        try {if (conn != null) {conn.close();}
        } catch (Throwable e) {} finally {}
        conn = null;
    }
    

 
}
