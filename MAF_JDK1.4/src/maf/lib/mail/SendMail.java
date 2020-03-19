/*
 * @(#) SendMail.java 2005-02-18
 * 
 * Copyright (c) 2005 (주)미래넷 All rights reserved.
 */
package maf.lib.mail;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.event.ConnectionEvent;
import javax.mail.event.ConnectionListener;
import javax.mail.event.TransportEvent;
import javax.mail.event.TransportListener;
import javax.mail.internet.AddressException;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import maf.MafProperties;
import maf.lib.mail.exception.SendMailException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SendMail implements ConnectionListener, TransportListener {
    
    Log logger = LogFactory.getLog(getClass());
    /**
     * 
     * @param mailSession :
     *                MailSession.getMailSession(Smtp_server)를 이용 하여 가져 온다.
     * @param to :
     *                받는이 <email-id@abc.com>
     * @param cc : 참조자<email-id@abc.com>
     * @param bcc : 숨은참조자<email-id@abc.com>
     * @param from
     * @param subject
     * @param contents
     * @param files
     * @return
     * @throws SendMailException
     */
    public static void sendMail(Session mailSession, EMailMessageBean message) 
    throws SendMailException {
        SendMail sf = new SendMail();
        if (mailSession != null && sf != null) {
        	sf.send(mailSession, message);
        }
    }


    public  boolean send(Session mailSession, EMailMessageBean message)
        throws SendMailException
    {
        logger.debug( " >> Mail Send Start !!!");
        boolean flag = false;
        Transport trans = null;
        
        try {

            // 보낼 메시지의 선언.
            Message msg = new MimeMessage(mailSession);
            if(message.getFrom() !=null) {
                msg.setFrom(message.getFrom());
            } else {
                throw new SendMailException("From is null!!!");
            }
//            msg.setReplyTo(message.getTo());
            msg.setRecipients(Message.RecipientType.TO, message.getTo());
            if (message.getCc() != null) {
                msg.setRecipients(Message.RecipientType.CC, message.getCc());
            }
            if (message.getBcc() != null) {
                msg.setRecipients(Message.RecipientType.BCC, message.getBcc());
            }
            if(message.getSubject() != null) {
                msg.setSubject(MimeUtility.encodeText(message.getSubject(), EMailMessageBean.DEFAULT_CHARSET, "B"));
            } else {
                throw new SendMailException(" subject is null!!!");
            }

            //  첫번째 Message BodyPart의 생성
            MimeBodyPart mbp = new MimeBodyPart();
            // 보낼 내용의 세팅
            mbp.setContent(message.getContents(), message.getContentsType());
            
            logger.debug("ctype:" + message.getContentsType());
            
            // 첫번째 메시지 내용의 부분을 multiPart에 첨가한다.
            Multipart multi = new MimeMultipart();
            multi.addBodyPart(mbp);

            //파일 첨부코드
            if (message.getFiles() != null) {
                MailAttachFileBean[] files = message.getFiles();
                for (int i = 0; i < files.length; i++) {
                    if (files[i].getFile() != null) {
	                    DataSource fds = new FileDataSource(files[i].getFile().getAbsolutePath());
	                    MimeBodyPart m = new MimeBodyPart();
	                    m.setDataHandler(new DataHandler(fds));
	                    // ISO-8859-1 | B ?
	                    m.setFileName(MimeUtility.encodeText(files[i].getFileName(), message.getCharSet(), "B"));
	                    multi.addBodyPart(m);
                    }
                }
            }

            // 메시지의 content에 세팅하도록 한다.
            msg.setContent(multi);
            //   msg.setSentDate(new Date());
            //debugMail(msg);
            logger.debug("Mail Send >>>");
//            Transport.send(msg);

            
            //=====================================
            msg.saveChanges(); // msg객체에서 데이터를 보낼수 있도록 속성변경인증을 시도하도록 한다.
            trans = mailSession.getTransport();

            // register ourselves as listener for ConnectionEvents
            // and TransportEvents
            trans.addConnectionListener(this);
            trans.addTransportListener(this);
            
            // connect the transport
            trans.connect(MafProperties.SMTP_SERVER, MafProperties.SMTP_SERVER_USER, MafProperties.SMTP_SERVER_PASSWD);
            
            // send the message
            trans.sendMessage(msg, message.getTo());
            flag = true;
        } catch (AddressException e1) {
            throw new SendMailException("Invalid Email Address :" + e1.getMessage(), e1);
        } catch (MessagingException e0) {
            throw new SendMailException("MessagingException :" + e0.getMessage() , e0);
        } catch (Exception e2) {
            throw new SendMailException(e2.getMessage(), e2);
        } finally {
            if (trans !=null) {
	            try {trans.close();} catch (MessagingException e) {}
            }
        }
        logger.debug( " << Mail Send Finish !!!");

        return flag;
    }

//    /**
//     * Send Mail의 단축 형
//     * 
//     * @param mailSession :
//     *                MailSession.getMailSession(Smtp_server)를 이용 하여 가져 온다.
//     * @param to
//     * @param from
//     * @param subject
//     * @param contents
//     * @return
//     * @throws SendMailException,
//     *                 AddressException
//     */
//    public  static void send(Session mailSession, 
//            	String to, 
//            	String from, 
//            	String subject, String contents)
//        throws SendMailException, AddressException {
//        EMailMessageBean msg = new EMailMessageBean(to, from, subject, contents);
//        
//        SendMail sf = new SendMail();
//        sf.send(mailSession, msg);
//    }
    /**
     * 이름과 EMail주소로 주소를 만든다...
     * @param name
     * @param address
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String getMailAddress(String name, String address) throws UnsupportedEncodingException{
        if(name == null) {
            return address.trim();
        } else {
            return MimeUtility.encodeText(name, EMailMessageBean.DEFAULT_CHARSET, "B") + "<" + address.trim() + ">";
        }
        
    }
    //=========================================================================
    public void opened(ConnectionEvent arg0) {
        System.out.println(">>> ConnectionListener.opened()");
    }

    public void disconnected(ConnectionEvent arg0) {

    }

    public void closed(ConnectionEvent arg0) {
        System.out.println(">>> ConnectionListener.closed()");
    }

    public void messageDelivered(TransportEvent e) {
        System.out.print(">>> TransportListener.messageDelivered().");
        System.out.println(" Valid Addresses:");
        Address[] valid = e.getValidSentAddresses();
        if (valid != null) {
            for (int i = 0; i < valid.length; i++)
                System.out.println("    " + valid[i]);
        }
    }

    public void messageNotDelivered(TransportEvent e) {
        System.out.print(">>> TransportListener.messageNotDelivered().");
        System.out.println(" Invalid Addresses:");
        Address[] invalid = e.getInvalidAddresses();
        if (invalid != null) {
            for (int i = 0; i < invalid.length; i++)
                System.out.println("    " + invalid[i]);
        }
    }

    public void messagePartiallyDelivered(TransportEvent arg0) {
        //      SMTPTransport doesn't partially deliver msgs
    }
    
    /**
     * 메일 내용을 디버깅 한다.
     * session.setDebug(true); 가 더 유리 
     * @param msg
     * @throws MessagingException
     * @throws IOException
     */
    private void debugMail(Message msg) throws MessagingException, IOException{
        Address[]  from = msg.getFrom();
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        for(int i = 0 ; i < from.length; i++ ) {
            System.out.println("From [" + i+ "] : " +  from[i].toString());
        }
        Address[]  to = msg.getReplyTo(); //msg.getRecipients(Message.RecipientType.TO);
        for(int i = 0 ; i < to.length; i++ ) {
            System.out.println("to [" + i+ "] : " +  to[i].toString());
        }
        System.out.println ("Subject : " + msg.getSubject());
        System.out.println ("Content Type : " + msg.getContentType());
        System.out.println ("Contents Size : " + msg.getSize());
        System.out.println ("Contents  : ----------------- \n" + msg.getContent().toString() + "\n -----------------");
        Enumeration eu = null;
        if ( eu != null ) {
    		while(eu.hasMoreElements()){
    			String headerName = (String)eu.nextElement();
    			System.out.println("    "+ headerName);
    		}
    	}
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
    }
}