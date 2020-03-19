/*
 * MessageLogger.java, @ 2005-03-09
 * 
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package miraenet.app.msg;

import java.util.ArrayList;
import java.util.List;

import maf.mdb.Mdb;
import maf.mdb.drivers.MdbDriver;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author goindole
 *

 */
public class MessageLogger {
    public static final String STATUS_OK = "OK";
    public static final String STATUS_FAIL = "FAIL";
    
	private  Log logger = LogFactory.getLog(MessageLogger.class);
	
    private static MessageLogger instance =  new MessageLogger();
    

    public static void log(String type, String reg_ip, 
            				String fromUsn, String fromAddr, 
            				String destUsn, String destAddr,
            				String subject,  String status,
            				String guid ) {
        instance._log(type, reg_ip, fromUsn,  fromAddr, destUsn, destAddr, subject, status, guid);
    }
    
    private void _log(String type,String reg_ip, 
			String fromUsn, String fromAddr, 
			String destUsn, String destAddr,
			String subject,  String status,
			String guid ) {
        List at = new ArrayList();
        
        final String sql = "INSERT INTO LOG_MESSAGE ( " +
                "	MTYPE, FROMUSN, FROMADDR,  DESTUSN, DESTADDR, STATUS, " + 
                "	SUBJECT, MID, reg_ip) " + 
                " VALUES ( :MTYPE, :FROMUSN, :FROMADDR,  :DESTUSN, :DESTADDR, " +
                "					:STATUS, :SUBJECT0, :MID,  :reg_ip) ";
        
        at.add(type);
        at.add(fromUsn);
        at.add(fromAddr);
        at.add(destUsn);
        at.add(destAddr);
        at.add(status);
        
        at.add((subject == null)?"":subject.substring(0,subject.length()));
        at.add(guid);
        at.add(reg_ip);
        
        MdbDriver oDb = Mdb.getMdbDriver();
        try {
        	
            oDb.executeUpdate(sql, at);
        } catch (Throwable e) {

            logger.error(e.getMessage() + ":" + guid);
        } finally {
    		if (oDb != null) {try {oDb.close();} catch (Exception e) {};}
    		oDb = null;
        }
        
    }

}
