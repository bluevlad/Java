/*
 * ClubMemberBean.java, @ 2005-03-19
 * 
 * Copyright (c) 2004 (��)�̷��� All rights reserved.
 */
package miraenet.app.club.beans;

import java.util.Date;

/**
 * @author goindole
 *

 */
public class ClubMemberBean {

	/**
	* Ŭ��ID
	*/	
	private String c_id = null;

	/**
	* ����� USN
	*/	
	private String usn = null;

	/**
	* ������
	*/	
	private Date c_joindt = null;

	/**
	* ����(���Խ�û��:A, ����:T)
	*/	
	private String is_auth = null;

	/**
	* �Ұ���
	*/	
	private String c_desc = null;

	/**
	* ���������Ͻ�
	*/	
	private Date c_lastdt = null;

	/**
	* ���� Ƚ��
	*/	
	private int c_attendance = 0;

	/**
	* ������
	*/	
	private Date c_authdt = null;

	private String nm = null;
	private String userid = null;

	////////////////////////////////////////////////////////////////////////////////
    public String getIs_authExp() {
        if ("T".equals(getIs_auth())) return "����";
        else return "���Խ�û";        
    }
    ////////////////////////////////////////////////////////////////////////////////

	/**
	* Get c_id : Ŭ��ID
	* DB TYPE : VARCHAR2
	*/
	public String getC_id(){
		return this.c_id;
	}
	/**
	* Set c_id : Ŭ��ID
	* DB TYPE : VARCHAR2
	*/
	public void setC_id(String c_id){
		this.c_id = c_id;
	}

	/**
	* Get usn : ����� USN
	* DB TYPE : VARCHAR2
	*/
	public String getUsn(){
		return this.usn;
	}
	/**
	* Set usn : ����� USN
	* DB TYPE : VARCHAR2
	*/
	public void setUsn(String usn){
		this.usn = usn;
	}

	/**
	* Get c_joindt : ������
	* DB TYPE : DATE
	*/
	public Date getC_joindt(){
		return this.c_joindt;
	}
	/**
	* Set c_joindt : ������
	* DB TYPE : DATE
	*/
	public void setC_joindt(Date c_joindt){
		this.c_joindt = c_joindt;
	}

	/**
	* Get is_auth : ����(���Խ�û��:A, ����:T)
	* DB TYPE : VARCHAR2
	*/
	public String getIs_auth(){
		return this.is_auth;
	}
	/**
	* Set is_auth : ����(���Խ�û��:A, ����:T)
	* DB TYPE : VARCHAR2
	*/
	public void setIs_auth(String is_auth){
		this.is_auth = is_auth;
	}

	/**
	* Get c_desc : �Ұ���
	* DB TYPE : VARCHAR2
	*/
	public String getC_desc(){
		return this.c_desc;
	}
	/**
	* Set c_desc : �Ұ���
	* DB TYPE : VARCHAR2
	*/
	public void setC_desc(String c_desc){
		this.c_desc = c_desc;
	}

	/**
	* Get c_lastdt : ���������Ͻ�
	* DB TYPE : DATE
	*/
	public Date getC_lastdt(){
		return this.c_lastdt;
	}
	/**
	* Set c_lastdt : ���������Ͻ�
	* DB TYPE : DATE
	*/
	public void setC_lastdt(Date c_lastdt){
		this.c_lastdt = c_lastdt;
	}

	/**
	* Get c_attendance : ���� Ƚ��
	* DB TYPE : NUMBER
	*/
	public int getC_attendance(){
		return this.c_attendance;
	}
	/**
	* Set c_attendance : ���� Ƚ��
	* DB TYPE : NUMBER
	*/
	public void setC_attendance(int c_attendance){
		this.c_attendance = c_attendance;
	}

	/**
	* Get c_authdt : ������
	* DB TYPE : DATE
	*/
	public Date getC_authdt(){
		return this.c_authdt;
	}
	/**
	* Set c_authdt : ������
	* DB TYPE : DATE
	*/
	public void setC_authdt(Date c_authdt){
		this.c_authdt = c_authdt;
	}


    
    /**
     * @return nm�� �����մϴ�.
     */
    public String getNm() {
        return nm;
    }
    /**
     * @param nm �����Ϸ��� nm.
     */
    public void setNm(String nm) {
        this.nm = nm;
    }
    /**
     * @return userid�� �����մϴ�.
     */
    public String getUserid() {
        return userid;
    }
    /**
     * @param userid �����Ϸ��� userid.
     */
    public void setUserid(String userid) {
        this.userid = userid;
    }
}
