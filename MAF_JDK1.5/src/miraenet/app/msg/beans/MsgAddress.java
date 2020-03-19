/*
 * @(#) MsgAddress.java 2005-02-18
 * 
 * Copyright (c) 2005 (��)�̷��� All rights reserved.
 */

package miraenet.app.msg.beans;

/**
 * @author goindole
 * @version 1.0
 * @modifydate 2005-02-18
 */
public class MsgAddress {
    String nm = null;
    String address = null;
    String usn = null;
    /**
     * �̸� �� �ּ� 
     * @param nm
     * @param address
     */
    public MsgAddress(String nm, String address, String usn) {
        this.nm = nm;
        this.address = address;
        this.usn = usn;
    }
    /**
     * �ּ� : E-Mail�ּ� , USN, �޴��� ��ȣ
     * @return
     */
    public String getAddress() {
        return address;
    }

    /**
     * �̸�
     * @return
     */
    public String getNm() {
        return nm;
    }

    
    /**
     * @return usn�� �����մϴ�.
     */
    public String getUsn() {
        return usn;
    }
}
