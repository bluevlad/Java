/*
 * Created on 2005. 6. 5.
 *
 * Copyright (c) 2004 (��)�̷��� All rights reserved.
 */
package xadmin.cmst.beans;

import java.util.Date;

/**
 * @author goindole
 *  
 */
public class Inx_lstBean {

	/**
	* AttDef
	*/	
	private String sjtcode = null;

	/**
	* ����
	*/	
	private int week = 0;

	/**
	* ����
	*/	
	private int chasi = 0;
	/**
	 * ���� Update �� 
	 */
	private int oldchasi = 0;

		
	private String sjtname = null;

		
	private String mediaid = null;

		
	private String htmname = null;

	/**
	* Y:����������  
N:����������
	*/	
	private String issample = null;

		
	private Date update_dt = null;

		
	private String update_id = null;

	/**
	* ���ǽð�(��)
	*/	
	private int chptime = 0;


	////////////////////////////////////////////////////////////////////////////////
	

	/**
	* Get sjtcode : AttDef
	* DB TYPE : VARCHAR2
	*/
	public String getSjtcode(){
		return this.sjtcode;
	}
	/**
	* Set sjtcode : AttDef
	* DB TYPE : VARCHAR2
	*/
	public void setSjtcode(String sjtcode){
		this.sjtcode = sjtcode;
	}

	/**
	* Get week : ����
	* DB TYPE : NUMBER
	*/
	public int getWeek(){
		return this.week;
	}
	/**
	* Set week : ����
	* DB TYPE : NUMBER
	*/
	public void setWeek(int week){
		this.week = week;
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
	* Get sjtname : 
	* DB TYPE : VARCHAR2
	*/
	public String getSjtname(){
		return this.sjtname;
	}
	/**
	* Set sjtname : 
	* DB TYPE : VARCHAR2
	*/
	public void setSjtname(String sjtname){
		this.sjtname = sjtname;
	}

	/**
	* Get mediaid : 
	* DB TYPE : VARCHAR2
	*/
	public String getMediaid(){
		return this.mediaid;
	}
	/**
	* Set mediaid : 
	* DB TYPE : VARCHAR2
	*/
	public void setMediaid(String mediaid){
		this.mediaid = mediaid;
	}

	/**
	* Get htmname : 
	* DB TYPE : VARCHAR2
	*/
	public String getHtmname(){
		return this.htmname;
	}
	/**
	* Set htmname : 
	* DB TYPE : VARCHAR2
	*/
	public void setHtmname(String htmname){
		this.htmname = htmname;
	}

	/**
	* Get issample : Y:����������  
N:����������
	* DB TYPE : VARCHAR2
	*/
	public String getIssample(){
		return this.issample;
	}
	/**
	* Set issample : Y:����������  
N:����������
	* DB TYPE : VARCHAR2
	*/
	public void setIssample(String issample){
		this.issample = issample;
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
	* Get chptime : ���ǽð�(��)
	* DB TYPE : NUMBER
	*/
	public int getChptime(){
		return this.chptime;
	}
	/**
	* Set chptime : ���ǽð�(��)
	* DB TYPE : NUMBER
	*/
	public void setChptime(int chptime){
		this.chptime = chptime;
	}

	/**
	 * @return Returns the oldchasi.
	 */
	public int getOldchasi() {
		return oldchasi;
	}
	/**
	 * @param oldchasi The oldchasi to set.
	 */
	public void setOldchasi(int oldchasi) {
		this.oldchasi = oldchasi;
	}
}
