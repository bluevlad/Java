/*
 * Created on 2005. 6. 11.
 *
 * Copyright (c) 2004 (��)�̷��� All rights reserved.
 */
package xadmin.cmst.beans;

import java.util.Date;

/**
 * @author goindole
 *
 */
public class Cnt_ItemBean {

	//==========================================
	//== �ڵ� ���� 
	//==========================================
	

	/**
	* �����ڵ�
	*/	
	private String sjtcode = null;

	/**
	* ������ID(��:200501)
	*/	
	private String cnt_id = null;

	/**
	* ����
	*/	
	private int chasi = 0;

	/**
	* ���ϸ�(���������)
	*/	
	private String filename = null;

	/**
	* ���ǳ���
	*/	
	private String chp_title = null;

	/**
	* ���ǽð�
	*/	
	private int chp_time = 0;

	/**
	* chapter ��
	*/	
	private int chapters = 0;

		
	private Date reg_dt = null;

		
	private String update_id = null;

		
	private Date update_dt = null;


	////////////////////////////////////////////////////////////////////////////////
	

	/**
	* Get sjtcode : �����ڵ�
	* DB TYPE : VARCHAR2
	*/
	public String getSjtcode(){
		return this.sjtcode;
	}
	/**
	* Set sjtcode : �����ڵ�
	* DB TYPE : VARCHAR2
	*/
	public void setSjtcode(String sjtcode){
		this.sjtcode = sjtcode;
	}

	/**
	* Get cnt_id : ������ID(��:200501)
	* DB TYPE : VARCHAR2
	*/
	public String getCnt_id(){
		return this.cnt_id;
	}
	/**
	* Set cnt_id : ������ID(��:200501)
	* DB TYPE : VARCHAR2
	*/
	public void setCnt_id(String cnt_id){
		this.cnt_id = cnt_id;
	}

	/**
	* Get chasi : ����
	* DB TYPE : NUMBER
	*/
	public int getChasi(){
		return this.chasi;
	}
	/**
	* Set chasi : ����
	* DB TYPE : NUMBER
	*/
	public void setChasi(int chasi){
		this.chasi = chasi;
	}

	/**
	* Get filename : ���ϸ�(���������)
	* DB TYPE : VARCHAR2
	*/
	public String getFilename(){
		return this.filename;
	}
	/**
	* Set filename : ���ϸ�(���������)
	* DB TYPE : VARCHAR2
	*/
	public void setFilename(String filename){
		this.filename = filename;
	}

	/**
	* Get chp_title : ���ǳ���
	* DB TYPE : VARCHAR2
	*/
	public String getChp_title(){
		return this.chp_title;
	}
	/**
	* Set chp_title : ���ǳ���
	* DB TYPE : VARCHAR2
	*/
	public void setChp_title(String chp_title){
		this.chp_title = chp_title;
	}

	/**
	* Get chp_time : ���ǽð�
	* DB TYPE : NUMBER
	*/
	public int getChp_time(){
		return this.chp_time;
	}
	/**
	* Set chp_time : ���ǽð�
	* DB TYPE : NUMBER
	*/
	public void setChp_time(int chp_time){
		this.chp_time = chp_time;
	}

	/**
	* Get chapters : chapter ��
	* DB TYPE : NUMBER
	*/
	public int getChapters(){
		return this.chapters;
	}
	/**
	* Set chapters : chapter ��
	* DB TYPE : NUMBER
	*/
	public void setChapters(int chapters){
		this.chapters = chapters;
	}

	/**
	* Get reg_dt : 
	* DB TYPE : DATE
	*/
	public Date getReg_dt(){
		return this.reg_dt;
	}
	/**
	* Set reg_dt : 
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
	* Get update_dt : 
	* DB TYPE : DATE
	*/
	public Date getUpdate_dt(){
		return this.update_dt;
	}
	/**
	* Set update_dt : 
	* DB TYPE : DATE
	*/
	public void setUpdate_dt(Date update_dt){
		this.update_dt = update_dt;
	}


}

