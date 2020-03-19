/*
 * @(#) MailManager.java 2005-02-18
 * 
 * Copyright (c) 2005 (주)미래넷 All rights reserved.
 */

package miraenet.app.msg;

import javax.mail.Session;

import maf.MafUtil;
import maf.base.BaseHttpSession;
import maf.lib.mail.EMailMessageBean;
import maf.lib.mail.MailSession;
import maf.lib.mail.SendMail;
import maf.lib.mail.exception.SendMailException;
import maf.logger.Trace;
import miraenet.MiConfig;
import miraenet.app.msg.beans.MessageBean;
import miraenet.app.msg.beans.MsgAddress;
import modules._exceptions.EcampusException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * @author goindole
 * @version 1.0
 * @modifydate 2005-02-18
 */
public class MailManager {
    private static MailManager instance = new MailManager();

	private  Log logger = LogFactory.getLog(MailManager.class);

    public static synchronized MailManager getInstance() {
        return instance;
    }
    private MailManager() {}
    
    public int sendmail(MessageBean msg) throws EcampusException, SendMailException
    {
    	return this.sendmail(msg, null);
    }
    
    public int sendmail(MessageBean msg, BaseHttpSession session)  
    throws SendMailException {
//        SendMail sm = new SendMail();
	    Session mailSession = MailSession.getMailSession(MiConfig.SMTP_SERVER);
//	    Session session = MailSession.getMailSession("smtp.postech.ac.kr");
//	    SendMail mng = new SendMail();
	    int cnt =0;

	    try{
            if(msg != null ) {
                MsgAddress[] addrs = msg.getTo();
                if (addrs != null) {
		            for(int i =0; i<addrs.length; i++ ) {
		                String status = null;
		                String guid = MafUtil.getGUID();
		                try{
		                    EMailMessageBean mailmsg = new EMailMessageBean();
		                    mailmsg.setFrom(SendMail.getMailAddress(msg.getFrom().getNm(), msg.getFrom().getAddress()));
		                    mailmsg.addTo(SendMail.getMailAddress(addrs[i].getNm(), addrs[i].getAddress()));
		                    mailmsg.setSubject(msg.getSubject());
		                    mailmsg.setContents(msg.getContents());
		                    mailmsg.setFiles(msg.getFiles());
		                    mailmsg.setGuid(guid);
		                    SendMail.sendMail(mailSession, mailmsg);
		                    

		                	cnt ++;
		                	status = MessageLogger.STATUS_OK;
		                } catch (Exception e) {
		                    status = MessageLogger.STATUS_FAIL;
		                    logger.error(Trace.getStackTrace(e));
//		                    logger.error(e.getMessage());
		                }
		                //ype, reg_ip, fromUsn,  fromAddr, destUsn, destAddr, subject, status, guid
		                if(session != null) {
			                MessageLogger.log("mail", session.getLoginIP(),
			                        session.getUsn(), msg.getFrom().getAddress(),
			                        addrs[i].getUsn(), addrs[i].getAddress(),
			                        msg.getSubject(), status,guid );
		                }
		            }// for
                }
            }
	    } catch (Exception e) {
	        
            throw new SendMailException("sendmail Error !!!" + e.getMessage(), e);
        }
	    return cnt;
    }
}
