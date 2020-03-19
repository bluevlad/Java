/*
 * Created on 2005. 6. 11.
 *
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package xadmin.cmst.beans;

import java.util.Date;

/**
 * @author goindole
 * 켄텐츠 마스터 정보 
 */
public class Cnt_MstBean {
	private String sjtname = null;
	
	
	/**
	 * @return Returns the sjtname.
	 */
	public String getSjtname() {
		return sjtname;
	}
	/**
	 * @param sjtname The sjtname to set.
	 */
	
	public void setSjtname(String sjtname) {
		this.sjtname = sjtname;
	}
	//==========================================
	//== 자동 생성 
	//==========================================
	

	/**
	* 과목코드
	*/	
	private String sjtcode = null;

	/**
	* 컨텐츠ID(예:200501)
	*/	
	private String cnt_id = null;

	/**
	* 강의명
	*/	
	private String chptitle = null;

	/**
	* 강의설명
	*/	
	private String chpdesc = null;

	/**
	* 강사
	*/	
	private String prof = null;

	/**
	* 등록일
	*/	
	private Date reg_dt = null;

		
	private String update_id = null;

	/**
	* 최종수정일
	*/	
	private Date update_dt = null;


	////////////////////////////////////////////////////////////////////////////////
	

	/**
	* Get sjtcode : 과목코드
	* DB TYPE : VARCHAR2
	*/
	public String getSjtcode(){
		return this.sjtcode;
	}
	/**
	* Set sjtcode : 과목코드
	* DB TYPE : VARCHAR2
	*/
	public void setSjtcode(String sjtcode){
		this.sjtcode = sjtcode;
	}

	/**
	* Get cnt_id : 컨텐츠ID(예:200501)
	* DB TYPE : VARCHAR2
	*/
	public String getCnt_id(){
		return this.cnt_id;
	}
	/**
	* Set cnt_id : 컨텐츠ID(예:200501)
	* DB TYPE : VARCHAR2
	*/
	public void setCnt_id(String cnt_id){
		this.cnt_id = cnt_id;
	}

	/**
	* Get chptitle : 강의명
	* DB TYPE : VARCHAR2
	*/
	public String getChptitle(){
		return this.chptitle;
	}
	/**
	* Set chptitle : 강의명
	* DB TYPE : VARCHAR2
	*/
	public void setChptitle(String chptitle){
		this.chptitle = chptitle;
	}

	/**
	* Get chpdesc : 강의설명
	* DB TYPE : CLOB
	*/
	public String getChpdesc(){
		return this.chpdesc;
	}
	/**
	* Set chpdesc : 강의설명
	* DB TYPE : CLOB
	*/
	public void setChpdesc(String chpdesc){
		this.chpdesc = chpdesc;
	}

	/**
	* Get prof : 강사
	* DB TYPE : VARCHAR2
	*/
	public String getProf(){
		return this.prof;
	}
	/**
	* Set prof : 강사
	* DB TYPE : VARCHAR2
	*/
	public void setProf(String prof){
		this.prof = prof;
	}

	/**
	* Get reg_dt : 등록일
	* DB TYPE : DATE
	*/
	public Date getReg_dt(){
		return this.reg_dt;
	}
	/**
	* Set reg_dt : 등록일
	* DB TYPE : DATE
	*/
	public void setReg_dt(Date reg_dt){
		this.reg_dt = reg_dt;
	}

	/**
	* Get update_id : 
	* DB TYPE : VARCHAR2
	*/
	public String getUpdate_id(){
		return this.update_id;
	}
	/**
	* Set update_id : 
	* DB TYPE : VARCHAR2
	*/
	public void setUpdate_id(String update_id){
		this.update_id = update_id;
	}

	/**
	* Get update_dt : 최종수정일
	* DB TYPE : DATE
	*/
	public Date getUpdate_dt(){
		return this.update_dt;
	}
	/**
	* Set update_dt : 최종수정일
	* DB TYPE : DATE
	*/
	public void setUpdate_dt(Date update_dt){
		this.update_dt = update_dt;
	}

}

