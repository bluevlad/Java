/*
 * Created on 2005. 6. 11.
 *
 * Copyright (c) 2004 (��)�̷��� All rights reserved.
 */
package xadmin.cmst.beans;

import java.util.Date;

/**
 * @author goindole
 * ������ ������ ���� 
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
	* ���Ǹ�
	*/	
	private String chptitle = null;

	/**
	* ���Ǽ���
	*/	
	private String chpdesc = null;

	/**
	* ����
	*/	
	private String prof = null;

	/**
	* �����
	*/	
	private Date reg_dt = null;

		
	private String update_id = null;

	/**
	* ����������
	*/	
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
	* Get chptitle : ���Ǹ�
	* DB TYPE : VARCHAR2
	*/
	public String getChptitle(){
		return this.chptitle;
	}
	/**
	* Set chptitle : ���Ǹ�
	* DB TYPE : VARCHAR2
	*/
	public void setChptitle(String chptitle){
		this.chptitle = chptitle;
	}

	/**
	* Get chpdesc : ���Ǽ���
	* DB TYPE : CLOB
	*/
	public String getChpdesc(){
		return this.chpdesc;
	}
	/**
	* Set chpdesc : ���Ǽ���
	* DB TYPE : CLOB
	*/
	public void setChpdesc(String chpdesc){
		this.chpdesc = chpdesc;
	}

	/**
	* Get prof : ����
	* DB TYPE : VARCHAR2
	*/
	public String getProf(){
		return this.prof;
	}
	/**
	* Set prof : ����
	* DB TYPE : VARCHAR2
	*/
	public void setProf(String prof){
		this.prof = prof;
	}

	/**
	* Get reg_dt : �����
	* DB TYPE : DATE
	*/
	public Date getReg_dt(){
		return this.reg_dt;
	}
	/**
	* Set reg_dt : �����
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

}

