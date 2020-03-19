/*
 * @(#) MessageBean.java 2005-02-18
 * 
 * Copyright (c) 2005 (주)미래넷 All rights reserved.
 */

package miraenet.app.msg.beans;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import maf.base.BaseHttpSession;
import maf.lib.mail.MailAttachFileBean;


/**
 * @author goindole
 * @version 1.0
 * @modifydate 2005-02-18
 */
public class MessageBean {
    MsgAddress from = null;	//MsgAddress
    List to = null; // MsgAddress []
    String subject = null;
    String contents = null;
    List files = null;

    public MessageBean() {
    }
    

    /**
     * 메시지 내용을 가져온다. 
     * @return
     */
    public String getContents() {
        return contents;
    }
    
    /**
     * 내용을 설정한다.
     * @param contents
     */
    public void setContents(String contents) {
        this.contents = contents;
    }
    
    /**
     * 첨부파일 목록을 가져온다.
     * @return
     */
    public MailAttachFileBean[] getFiles() {
        if(to != null && files != null) {
            return (MailAttachFileBean[]) files.toArray(new MailAttachFileBean[0]);
        } else {
            return null;
        }
    }
    /**
     * 첨부 파일을 추가 한다.
     * @param file
     * @param filename
     */
    public void addFile(File file, String filename) {
        if(this.files == null) files = new ArrayList();
        MailAttachFileBean t = new MailAttachFileBean(file, filename);
        files.add(t);
    }
    /**
     * 발신자 정보를 가져온다.
     * @return
     */
    public MsgAddress getFrom() {
        return from;
    }
    /**
     * 발신자를 설정 한다. 
     * @param name
     * @param address ( sms : 발신자 전화번호, mail:발신자 email, 쪽지 : 발신자 usn) 
     * @param usn
     */
    public void setFrom(String name, String address, String usn) {
        this.from = new MsgAddress(name, address, usn );
    }

    /**
     * 메시지 제목 가져오기 
     * @return
     */
    public String getSubject() {
        return subject;
    }
    /**
     * 메시지 제목 설정 
     * @param subject
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }
    /**
     * 수신자 목록 가져 오기 
     * @return
     */
    public MsgAddress[] getTo() {
        if(to != null) {
            return (MsgAddress[]) to.toArray(new MsgAddress[0]);
        } else {
            return null;
        }
    }
    
    /**
     * 수신자를 추가 한다. 
     * @param name
     * @param address
     * @param usn
     */
    public void addTo(String name, String address, String usn ) {
        if(this.to==null) {
            to = new ArrayList();
        }
        to.add(new MsgAddress(name, address, usn ));
    }
    
}
