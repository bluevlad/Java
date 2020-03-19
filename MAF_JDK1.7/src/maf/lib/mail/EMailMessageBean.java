/*
 * @(#) MailBean.java 2005-02-18
 * 
 * Copyright (c) 2005 (주)미래넷 All rights reserved.
 */

package maf.lib.mail;

import java.util.ArrayList;
import java.util.List;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

/**
 * @author goindole
 * @version 1.0
 * @modifydate 2005-02-18
 */
public class EMailMessageBean {
    final static String DEFAULT_CHARSET = "euc-kr"; // euc-kr
    List listTo = null;
    List listCc = null;
    List listBcc = null;
    InternetAddress from = null; 
    String subject  = null;
    String contents  = null;
    String contentsType = null;
    String charSet = null;
    MailAttachFileBean[] files = null;
    String guid = null;
    
    
    public EMailMessageBean() {
    }
    
    /**
     * @return guid을 리턴합니다.
     */
    public String getGuid() {
        return guid;
    }
    /**
     * @param guid 설정하려는 guid.
     */
    public void setGuid(String guid) {
        this.guid = guid;
    }
    public EMailMessageBean(String to, String from, String subject, String contents) throws AddressException {
        this.addTo(to);
        this.setFrom(from);
        this.subject = subject;
        this.contents = contents;
    }
    public InternetAddress[] getTo() {
        return listToInternetAddressArray(listTo);
    }
    public void addTo(String to) throws AddressException {
        addTo( new InternetAddress(to));
    }
    
    public InternetAddress[] getBcc() {
        return listToInternetAddressArray(listBcc);
    }
    public void addBcc(String bcc) throws AddressException {
        addBcc( new InternetAddress(bcc));
    }
    
    public InternetAddress[] getCc() {
        return listToInternetAddressArray(listCc);
    }
    public void addCc(String cc) throws AddressException {
        addCc( new InternetAddress(cc));
    }    
    
    public String getContents() {
        return contents;
    }
    public void setContents(String contents) {
        this.contents = contents;
    }
    
    
    
    public String getContentsType() {
        if(contentsType == null) {
            return "text/html; charset=" + DEFAULT_CHARSET;
        } else {
            return contentsType;
        }
    }
    
    public void setContentsType(String contentsType) {
        this.contentsType = contentsType;
    }
    
    
    
    public String getCharSet() {
        if(contentsType == null) {
            return DEFAULT_CHARSET;
        } else {
            return contentsType;
        }
    }
    public void setCharSet(String charSet) {
        this.charSet = charSet;
    }
    public MailAttachFileBean[] getFiles() {
        return files;
    }
    public void setFiles(MailAttachFileBean[] files) {
        this.files = files;
    }

    public InternetAddress getFrom() {
        return from;
    }
    public void setFrom(String from) throws AddressException {
        setFrom( new InternetAddress(from));
    }    
    
    public String getSubject() {
        return subject;
    }
    public void setSubject(String subject) {
        this.subject = subject;
    }
    

//===========================================================    
    private void addTo(InternetAddress to) {
        if(this.listTo == null) {
            this.listTo = new ArrayList();
        }
        this.listTo.add(to);
    }
    
    private void addCc(InternetAddress cc) {
        if(this.listCc == null) {
            this.listCc = new ArrayList();
        }
        this.listCc.add(cc);
    }
    private void addBcc(InternetAddress bcc) {
        if(this.listBcc == null) {
            this.listBcc = new ArrayList();
        }
        this.listBcc.add(bcc);
    }
    private void setFrom(InternetAddress from) {
        this.from = from;
    }
    
    private InternetAddress[] listToInternetAddressArray( List list ) {
        if(list != null) {
            return (InternetAddress[]) list.toArray(new InternetAddress[0]);
        } else {
            return null;
        }
    }
}
