/*
 * ClubMemberBean.java, @ 2005-03-19
 * 
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package miraenet.app.club.beans;

import java.util.Date;

/**
 * @author goindole
 *

 */
public class ClubMemberBean {

	/**
	* 클럽ID
	*/	
	private String c_id = null;

	/**
	* 사용자 USN
	*/	
	private String usn = null;

	/**
	* 가입일
	*/	
	private Date c_joindt = null;

	/**
	* 상태(가입신청중:A, 정상:T)
	*/	
	private String is_auth = null;

	/**
	* 소개글
	*/	
	private String c_desc = null;

	/**
	* 최종접속일시
	*/	
	private Date c_lastdt = null;

	/**
	* 접속 횟수
	*/	
	private int c_attendance = 0;

	/**
	* 승인일
	*/	
	private Date c_authdt = null;

	private String nm = null;
	private String userid = null;

	////////////////////////////////////////////////////////////////////////////////
    public String getIs_authExp() {
        if ("T".equals(getIs_auth())) return "승인";
        else return "가입신청";        
    }
    ////////////////////////////////////////////////////////////////////////////////

	/**
	* Get c_id : 클럽ID
	* DB TYPE : VARCHAR2
	*/
	public String getC_id(){
		return this.c_id;
	}
	/**
	* Set c_id : 클럽ID
	* DB TYPE : VARCHAR2
	*/
	public void setC_id(String c_id){
		this.c_id = c_id;
	}

	/**
	* Get usn : 사용자 USN
	* DB TYPE : VARCHAR2
	*/
	public String getUsn(){
		return this.usn;
	}
	/**
	* Set usn : 사용자 USN
	* DB TYPE : VARCHAR2
	*/
	public void setUsn(String usn){
		this.usn = usn;
	}

	/**
	* Get c_joindt : 가입일
	* DB TYPE : DATE
	*/
	public Date getC_joindt(){
		return this.c_joindt;
	}
	/**
	* Set c_joindt : 가입일
	* DB TYPE : DATE
	*/
	public void setC_joindt(Date c_joindt){
		this.c_joindt = c_joindt;
	}

	/**
	* Get is_auth : 상태(가입신청중:A, 정상:T)
	* DB TYPE : VARCHAR2
	*/
	public String getIs_auth(){
		return this.is_auth;
	}
	/**
	* Set is_auth : 상태(가입신청중:A, 정상:T)
	* DB TYPE : VARCHAR2
	*/
	public void setIs_auth(String is_auth){
		this.is_auth = is_auth;
	}

	/**
	* Get c_desc : 소개글
	* DB TYPE : VARCHAR2
	*/
	public String getC_desc(){
		return this.c_desc;
	}
	/**
	* Set c_desc : 소개글
	* DB TYPE : VARCHAR2
	*/
	public void setC_desc(String c_desc){
		this.c_desc = c_desc;
	}

	/**
	* Get c_lastdt : 최종접속일시
	* DB TYPE : DATE
	*/
	public Date getC_lastdt(){
		return this.c_lastdt;
	}
	/**
	* Set c_lastdt : 최종접속일시
	* DB TYPE : DATE
	*/
	public void setC_lastdt(Date c_lastdt){
		this.c_lastdt = c_lastdt;
	}

	/**
	* Get c_attendance : 접속 횟수
	* DB TYPE : NUMBER
	*/
	public int getC_attendance(){
		return this.c_attendance;
	}
	/**
	* Set c_attendance : 접속 횟수
	* DB TYPE : NUMBER
	*/
	public void setC_attendance(int c_attendance){
		this.c_attendance = c_attendance;
	}

	/**
	* Get c_authdt : 승인일
	* DB TYPE : DATE
	*/
	public Date getC_authdt(){
		return this.c_authdt;
	}
	/**
	* Set c_authdt : 승인일
	* DB TYPE : DATE
	*/
	public void setC_authdt(Date c_authdt){
		this.c_authdt = c_authdt;
	}


    
    /**
     * @return nm을 리턴합니다.
     */
    public String getNm() {
        return nm;
    }
    /**
     * @param nm 설정하려는 nm.
     */
    public void setNm(String nm) {
        this.nm = nm;
    }
    /**
     * @return userid을 리턴합니다.
     */
    public String getUserid() {
        return userid;
    }
    /**
     * @param userid 설정하려는 userid.
     */
    public void setUserid(String userid) {
        this.userid = userid;
    }
}
