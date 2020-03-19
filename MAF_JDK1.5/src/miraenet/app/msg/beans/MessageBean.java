/*
 * @(#) MessageBean.java 2005-02-18
 * 
 * Copyright (c) 2005 (��)�̷��� All rights reserved.
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
     * �޽��� ������ �����´�. 
     * @return
     */
    public String getContents() {
        return contents;
    }
    
    /**
     * ������ �����Ѵ�.
     * @param contents
     */
    public void setContents(String contents) {
        this.contents = contents;
    }
    
    /**
     * ÷������ ����� �����´�.
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
     * ÷�� ������ �߰� �Ѵ�.
     * @param file
     * @param filename
     */
    public void addFile(File file, String filename) {
        if(this.files == null) files = new ArrayList();
        MailAttachFileBean t = new MailAttachFileBean(file, filename);
        files.add(t);
    }
    /**
     * �߽��� ������ �����´�.
     * @return
     */
    public MsgAddress getFrom() {
        return from;
    }
    /**
     * �߽��ڸ� ���� �Ѵ�. 
     * @param name
     * @param address ( sms : �߽��� ��ȭ��ȣ, mail:�߽��� email, ���� : �߽��� usn) 
     * @param usn
     */
    public void setFrom(String name, String address, String usn) {
        this.from = new MsgAddress(name, address, usn );
    }

    /**
     * �޽��� ���� �������� 
     * @return
     */
    public String getSubject() {
        return subject;
    }
    /**
     * �޽��� ���� ���� 
     * @param subject
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }
    /**
     * ������ ��� ���� ���� 
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
     * �����ڸ� �߰� �Ѵ�. 
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
