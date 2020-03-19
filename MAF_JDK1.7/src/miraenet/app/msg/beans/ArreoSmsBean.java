/*
 * 작성된 날짜: 2005-02-07
 *  
 */
package miraenet.app.msg.beans;

/**
 * $snd_dttm=date("YmdHis");				예) 20041221103043
 * $cmp_msg_id=$snd_dttm.".".substr(microtime(), 2, 5);	예) 20041221103937.70827
 * $msg=’메시지 내용’;
 * $member[user_id] --> 해모수 계정			예) sky6326
 * $member[usernm] = "course_사용자신분"		예) course_이름
 * $from_no  --> 보내는 사람 전화번호			예) 01122312322	(-, 공백등이 없어야 함)
 * $to_no    --> 받는사람 전화번호			예) 01122312322	(-, 공백등이 없어야 함)
 *
 * @author goindole, 2005-02-07
 *
 */
public class ArreoSmsBean {


    	private String cmp_msg_id = null;

    	private String cmp_usr_id = null;

    	private String odr_fg = null;

    	private String sms_gb = null;

    	private String used_cd = null;

    	private String msg_gb = null;

    	private String wrt_dttm = null;

    	private String snd_dttm = null;

    	private String snd_phn_id = null;

    	private String rcv_phn_id = null;

    	private String callback = null;

    	private String snd_msg = null;

    	private String expire_val = null;

    	private String sms_st = null;

    	private String rsrvd_id = null;

    	private String rsrvd_wd = null;


    	/**
    	* Get cmp_msg_id : 
    	* DB TYPE : VARCHAR
    	*/
    	public String getCmp_msg_id(){
    		return this.cmp_msg_id;
    	}
    	/**
    	* Set cmp_msg_id : 
    	* DB TYPE : VARCHAR
    	*/
    	public void setCmp_msg_id(String cmp_msg_id){
    		this.cmp_msg_id = cmp_msg_id;
    	}

    	/**
    	* Get cmp_usr_id : 
    	* DB TYPE : VARCHAR
    	*/
    	public String getCmp_usr_id(){
    		return this.cmp_usr_id;
    	}
    	/**
    	* Set cmp_usr_id : 
    	* DB TYPE : VARCHAR
    	*/
    	public void setCmp_usr_id(String cmp_usr_id){
    		this.cmp_usr_id = cmp_usr_id;
    	}

    	/**
    	* Get odr_fg : 
    	* DB TYPE : CHAR
    	*/
    	public String getOdr_fg(){
    		return this.odr_fg;
    	}
    	/**
    	* Set odr_fg : 
    	* DB TYPE : CHAR
    	*/
    	public void setOdr_fg(String odr_fg){
    		this.odr_fg = odr_fg;
    	}

    	/**
    	* Get sms_gb : 
    	* DB TYPE : CHAR
    	*/
    	public String getSms_gb(){
    		return this.sms_gb;
    	}
    	/**
    	* Set sms_gb : 
    	* DB TYPE : CHAR
    	*/
    	public void setSms_gb(String sms_gb){
    		this.sms_gb = sms_gb;
    	}

    	/**
    	* Get used_cd : 
    	* DB TYPE : CHAR
    	*/
    	public String getUsed_cd(){
    		return this.used_cd;
    	}
    	/**
    	* Set used_cd : 
    	* DB TYPE : CHAR
    	*/
    	public void setUsed_cd(String used_cd){
    		this.used_cd = used_cd;
    	}

    	/**
    	* Get msg_gb : 
    	* DB TYPE : CHAR
    	*/
    	public String getMsg_gb(){
    		return this.msg_gb;
    	}
    	/**
    	* Set msg_gb : 
    	* DB TYPE : CHAR
    	*/
    	public void setMsg_gb(String msg_gb){
    		this.msg_gb = msg_gb;
    	}

    	/**
    	* Get wrt_dttm : 
    	* DB TYPE : VARCHAR
    	*/
    	public String getWrt_dttm(){
    		return this.wrt_dttm;
    	}
    	/**
    	* Set wrt_dttm : 
    	* DB TYPE : VARCHAR
    	*/
    	public void setWrt_dttm(String wrt_dttm){
    		this.wrt_dttm = wrt_dttm;
    	}

    	/**
    	* Get snd_dttm : 
    	* DB TYPE : VARCHAR
    	*/
    	public String getSnd_dttm(){
    		return this.snd_dttm;
    	}
    	/**
    	* Set snd_dttm : 
    	* DB TYPE : VARCHAR
    	*/
    	public void setSnd_dttm(String snd_dttm){
    		this.snd_dttm = snd_dttm;
    	}

    	/**
    	* Get snd_phn_id : 
    	* DB TYPE : VARCHAR
    	*/
    	public String getSnd_phn_id(){
    		return this.snd_phn_id;
    	}
    	/**
    	* Set snd_phn_id : 
    	* DB TYPE : VARCHAR
    	*/
    	public void setSnd_phn_id(String snd_phn_id){
    		this.snd_phn_id = snd_phn_id;
    	}

    	/**
    	* Get rcv_phn_id : 
    	* DB TYPE : VARCHAR
    	*/
    	public String getRcv_phn_id(){
    		return this.rcv_phn_id;
    	}
    	/**
    	* Set rcv_phn_id : 
    	* DB TYPE : VARCHAR
    	*/
    	public void setRcv_phn_id(String rcv_phn_id){
    		this.rcv_phn_id = rcv_phn_id;
    	}

    	/**
    	* Get callback : 
    	* DB TYPE : VARCHAR
    	*/
    	public String getCallback(){
    		return this.callback;
    	}
    	/**
    	* Set callback : 
    	* DB TYPE : VARCHAR
    	*/
    	public void setCallback(String callback){
    		this.callback = callback;
    	}

    	/**
    	* Get snd_msg : 
    	* DB TYPE : VARCHAR
    	*/
    	public String getSnd_msg(){
    		return this.snd_msg;
    	}
    	/**
    	* Set snd_msg : 
    	* DB TYPE : VARCHAR
    	*/
    	public void setSnd_msg(String snd_msg){
    		this.snd_msg = snd_msg;
    	}

    	/**
    	* Get expire_val : 
    	* DB TYPE : INTEGER
    	*/
    	public String getExpire_val(){
    		return this.expire_val;
    	}
    	/**
    	* Set expire_val : 
    	* DB TYPE : INTEGER
    	*/
    	public void setExpire_val(String expire_val){
    		this.expire_val = expire_val;
    	}

    	/**
    	* Get sms_st : 
    	* DB TYPE : CHAR
    	*/
    	public String getSms_st(){
    		return this.sms_st;
    	}
    	/**
    	* Set sms_st : 
    	* DB TYPE : CHAR
    	*/
    	public void setSms_st(String sms_st){
    		this.sms_st = sms_st;
    	}

    	/**
    	* Get rsrvd_id : 
    	* DB TYPE : VARCHAR
    	*/
    	public String getRsrvd_id(){
    		return this.rsrvd_id;
    	}
    	/**
    	* Set rsrvd_id : 
    	* DB TYPE : VARCHAR
    	*/
    	public void setRsrvd_id(String rsrvd_id){
    		this.rsrvd_id = rsrvd_id;
    	}

    	/**
    	* Get rsrvd_wd : 
    	* DB TYPE : VARCHAR
    	*/
    	public String getRsrvd_wd(){
    		return this.rsrvd_wd;
    	}
    	/**
    	* Set rsrvd_wd : 
    	* DB TYPE : VARCHAR
    	*/
    	public void setRsrvd_wd(String rsrvd_wd){
    		this.rsrvd_wd = rsrvd_wd;
    	}

    }
