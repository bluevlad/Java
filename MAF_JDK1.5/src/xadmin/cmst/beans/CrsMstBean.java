/*
 * crs_mstBean.java, @ 2005-05-12
 * 
 * Copyright (c) 2004 (��)�̷��� All rights reserved.
 */
package xadmin.cmst.beans;

import java.util.Date;

/**
 * @author goindole
 *
 */
public class CrsMstBean {

	/**
	* �����ڵ�
	*/	
	private String crscode = null;

	/**
	* ���� �ѱ۸�
	*/	
	private String crsname = null;

	/**
	* ���� ������
	*/	
	private String crsename = null;

	/**
	* ����������
	*/	
	private Date update_dt = null;

	/**
	* ������ ID
	*/	
	private String update_id = null;


	////////////////////////////////////////////////////////////////////////////////
	

	/**
	* Get crscode : �����ڵ�
	* DB TYPE : VARCHAR2
	*/
	public String getCrscode(){
		return this.crscode;
	}
	/**
	* Set crscode : �����ڵ�
	* DB TYPE : VARCHAR2
	*/
	public void setCrscode(String crscode){
		this.crscode = crscode;
	}

	/**
	* Get crsname : ���� �ѱ۸�
	* DB TYPE : VARCHAR2
	*/
	public String getCrsname(){
		return this.crsname;
	}
	/**
	* Set crsname : ���� �ѱ۸�
	* DB TYPE : VARCHAR2
	*/
	public void setCrsname(String crsname){
		this.crsname = crsname;
	}

	/**
	* Get crsename : ���� ������
	* DB TYPE : VARCHAR2
	*/
	public String getCrsename(){
		return this.crsename;
	}
	/**
	* Set crsename : ���� ������
	* DB TYPE : VARCHAR2
	*/
	public void setCrsename(String crsename){
		this.crsename = crsename;
	}

	/**
	* Get update_dt : ����������
	* DB TYPE : DATE
	*/
	public Date getUpdate_dt(){
		return this.update_dt;
	}
	/**
	* Set update_dt : ����������
	* DB TYPE : DATE
	*/
	public void setUpdate_dt(Date update_dt){
		this.update_dt = update_dt;
	}

	/**
	* Get update_id : ������ ID
	* DB TYPE : VARCHAR2
	*/
	public String getUpdate_id(){
		return this.update_id;
	}
	/**
	* Set update_id : ������ ID
	* DB TYPE : VARCHAR2
	*/
	public void setUpdate_id(String update_id){
		this.update_id = update_id;
	}

}
