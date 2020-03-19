/*
 * crs_mstBean.java, @ 2005-05-12
 * 
 * Copyright (c) 2004 (주)미래넷 All rights reserved.
 */
package xadmin.cmst.beans;

import java.util.Date;

/**
 * @author goindole
 *
 */
public class CrsMstBean {

	/**
	* 과정코드
	*/	
	private String crscode = null;

	/**
	* 과정 한글명
	*/	
	private String crsname = null;

	/**
	* 과정 영문명
	*/	
	private String crsename = null;

	/**
	* 최종수정일
	*/	
	private Date update_dt = null;

	/**
	* 수정자 ID
	*/	
	private String update_id = null;


	////////////////////////////////////////////////////////////////////////////////
	

	/**
	* Get crscode : 과정코드
	* DB TYPE : VARCHAR2
	*/
	public String getCrscode(){
		return this.crscode;
	}
	/**
	* Set crscode : 과정코드
	* DB TYPE : VARCHAR2
	*/
	public void setCrscode(String crscode){
		this.crscode = crscode;
	}

	/**
	* Get crsname : 과정 한글명
	* DB TYPE : VARCHAR2
	*/
	public String getCrsname(){
		return this.crsname;
	}
	/**
	* Set crsname : 과정 한글명
	* DB TYPE : VARCHAR2
	*/
	public void setCrsname(String crsname){
		this.crsname = crsname;
	}

	/**
	* Get crsename : 과정 영문명
	* DB TYPE : VARCHAR2
	*/
	public String getCrsename(){
		return this.crsename;
	}
	/**
	* Set crsename : 과정 영문명
	* DB TYPE : VARCHAR2
	*/
	public void setCrsename(String crsename){
		this.crsename = crsename;
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

	/**
	* Get update_id : 수정자 ID
	* DB TYPE : VARCHAR2
	*/
	public String getUpdate_id(){
		return this.update_id;
	}
	/**
	* Set update_id : 수정자 ID
	* DB TYPE : VARCHAR2
	*/
	public void setUpdate_id(String update_id){
		this.update_id = update_id;
	}

}
