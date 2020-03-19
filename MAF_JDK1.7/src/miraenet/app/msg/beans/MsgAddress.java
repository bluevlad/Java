/*
 * @(#) MsgAddress.java 2005-02-18
 * 
 * Copyright (c) 2005 (주)미래넷 All rights reserved.
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
     * 이름 과 주소 
     * @param nm
     * @param address
     */
    public MsgAddress(String nm, String address, String usn) {
        this.nm = nm;
        this.address = address;
        this.usn = usn;
    }
    /**
     * 주소 : E-Mail주소 , USN, 휴대폰 번호
     * @return
     */
    public String getAddress() {
        return address;
    }

    /**
     * 이름
     * @return
     */
    public String getNm() {
        return nm;
    }

    
    /**
     * @return usn을 리턴합니다.
     */
    public String getUsn() {
        return usn;
    }
}
