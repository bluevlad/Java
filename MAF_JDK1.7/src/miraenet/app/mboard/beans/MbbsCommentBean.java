/*
 * 작성자 : 김상준 Created on 2004. 10. 4.
 *  
 */
package miraenet.app.mboard.beans;

import java.util.Date;

/**
 * @author 김상준(goindole@miraenet.com) 
 * Create by 2004. 10. 4.
 *  
 * Table :  MBBS_COMMENT
 * 게시물 커멘트 정보 
 */
public class MbbsCommentBean {

	/**
	* 보드 ID
	*/
	private String bid = null;

	/**
	* 원글번호
	*/
	private String c_index = null;

	/**
	* 댓글  Sequence
	*/
	private int c_id = 0;

	/**
	* 사용자 SN
	*/
	private String usn = null;

	/**
	* 필명
	*/
	private String wname = null;

	/**
	* 암호
	*/
	private String c_passwd = null;

	/**
	* 등록일시
	*/
	private Date c_date = null;

	/**
	* 
	*/
	private String c_ip = null;

	/**
	* N:기타, P:찬성,O:반대
	*/
	private String c_opnion = null;

	/**
	* 
	*/
	private String c_memo = null;


	/**
	* Get bid : 보드 ID
	* DB TYPE : VARCHAR2
	*/
	public String getBid(){
		return this.bid;
	}
	/**
	* Set bid : 보드 ID
	* DB TYPE : VARCHAR2
	*/
	public void setBid(String bid){
		this.bid = bid;
	}

	/**
	* Get c_index : 원글번호
	* DB TYPE : VARCHAR2
	*/
	public String getC_index(){
		return this.c_index;
	}
	/**
	* Set c_index : 원글번호
	* DB TYPE : VARCHAR2
	*/
	public void setC_index(String c_index){
		this.c_index = c_index;
	}

	/**
	* Get c_id : 댓글  Sequence
	* DB TYPE : NUMBER
	*/
	public int getC_id(){
		return this.c_id;
	}
	/**
	* Set c_id : 댓글  Sequence
	* DB TYPE : NUMBER
	*/
	public void setC_id(int c_id){
		this.c_id = c_id;
	}

	/**
	* Get usn : 사용자 SN
	* DB TYPE : VARCHAR2
	*/
	public String getUsn(){
		return this.usn;
	}
	/**
	* Set usn : 사용자 SN
	* DB TYPE : VARCHAR2
	*/
	public void setUsn(String usn){
		this.usn = usn;
	}

	/**
	* Get wname : 필명
	* DB TYPE : VARCHAR2
	*/
	public String getWname(){
		return this.wname;
	}
	/**
	* Set wname : 필명
	* DB TYPE : VARCHAR2
	*/
	public void setWname(String wname){
		this.wname = wname;
	}

	/**
	* Get c_passwd : 암호
	* DB TYPE : VARCHAR2
	*/
	public String getC_passwd(){
		return this.c_passwd;
	}
	/**
	* Set c_passwd : 암호
	* DB TYPE : VARCHAR2
	*/
	public void setC_passwd(String c_passwd){
		this.c_passwd = c_passwd;
	}

	/**
	* Get c_date : 등록일시
	* DB TYPE : DATE
	*/
	public Date getC_date(){
		return this.c_date;
	}
	/**
	* Set c_date : 등록일시
	* DB TYPE : DATE
	*/
	public void setC_date(Date c_date){
		this.c_date = c_date;
	}

	/**
	* Get c_ip : 
	* DB TYPE : VARCHAR2
	*/
	public String getC_ip(){
		return this.c_ip;
	}
	/**
	* Set c_ip : 
	* DB TYPE : VARCHAR2
	*/
	public void setC_ip(String c_ip){
		this.c_ip = c_ip;
	}

	/**
	* Get c_opnion : N:기타, P:찬성,O:반대
	* DB TYPE : VARCHAR2
	*/
	public String getC_opnion(){
		return this.c_opnion;
	}
	/**
	* Set c_opnion : N:기타, P:찬성,O:반대
	* DB TYPE : VARCHAR2
	*/
	public void setC_opnion(String c_opnion){
		this.c_opnion = c_opnion;
	}

	/**
	* Get c_memo : 
	* DB TYPE : VARCHAR2
	*/
	public String getC_memo(){
		return this.c_memo;
	}
	/**
	* Set c_memo : 
	* DB TYPE : VARCHAR2
	*/
	public void setC_memo(String c_memo){
		this.c_memo = c_memo;
	}


//    private String c_index, wname, c_passwd, c_ip, c_memo, bid, usn;
//    private int c_id;
//    private Date c_date;
//    private String c_opnion;
//
//    public String getBid() {
//        return bid;
//    }
//
//    public void setBid(String bid) {
//        this.bid = bid;
//    }
//
//    public Date getC_date() {
//        return c_date;
//    }
//
//    public void setC_date(Date c_date) {
//        this.c_date = c_date;
//    }
//
//    public int getC_id() {
//        return c_id;
//    }
//
//    public void setC_id(int c_id) {
//        this.c_id = c_id;
//    }
//
//    public String getC_index() {
//        return c_index;
//    }
//
//    public void setC_index(String c_index) {
//        this.c_index = c_index;
//    }
//
//    public String getC_ip() {
//        return c_ip;
//    }
//
//    public void setC_ip(String c_ip) {
//        this.c_ip = c_ip;
//    }
//
//    public String getC_passwd() {
//        return c_passwd;
//    }
//
//    public void setC_passwd(String c_passwd) {
//        this.c_passwd = c_passwd;
//    }
//
//    public String getUsn() {
//        return usn;
//    }
//
//    public void setUsn(String usn) {
//        this.usn = usn;
//    }
//
//    public String getWname() {
//        return wname;
//    }
//
//    public void setWname(String wname) {
//        this.wname = wname;
//    }
//
//    public String getC_memo() {
//        return c_memo;
//    }
//
//    public void setC_memo(String c_memo) {
//        this.c_memo = c_memo;
//    }
//
//    public String getC_opnion() {
//        return c_opnion;
//    }
//
//    public void setC_opnion(String c_opnion) {
//        this.c_opnion = c_opnion;
//    }
}