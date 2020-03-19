/*
 * @(#) MailSession.java 2005-02-18
 * 
 * Copyright (c) 2005 UBQ All rights reserved.
 */

package maf.lib.mail;

import java.util.Properties;

import javax.mail.Session;



import maf.MafProperties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * @author bluevlad
 * @version 1.0
 * @modifydate 2005-02-18
 */
public class MailSession {
    static Log logger = LogFactory.getLog(MailSession.class); 
    public static Session getMailSession (String smtp_server) {
        Properties properties = System.getProperties();
        properties.put("mail.transport.protocol", "smtp");
        
        properties.put("mail.smtp.host", (smtp_server==null)? MafProperties.SMTP_SERVER:smtp_server);
        
        properties.put("mail.smtp.auth" , "true"); // 없으면 인증을 시도할수가 없다
        
        Session session = Session.getDefaultInstance(properties, null);
//        session.setDebug(Config.DEBUG);
        //Session.getInstance(properties, null);
        return session;
    }
}
