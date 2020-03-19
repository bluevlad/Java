/*
 * SjtMstBean.java, @ 2005-05-12
 * 
 * Copyright (c) 2004 (��)�̷��� All rights reserved.
 */
package xadmin.cmst.beans;

import java.util.Date;

/**
 * @author goindole
 *
 */
public class SjtMstBean {
	private String crsname = null;
	
	/**
	 * @return Returns the crsname.
	 */
	public String getCrsname() {
		return crsname;
	}
	/**
	 * @param crsname The crsname to set.
	 */
	public void setCrsname(String crsname) {
		this.crsname = crsname;
	}
	//==========================================
	//== �ڵ� ���� 
	//==========================================
	
	/**
	* �������ڵ�
	*/	
	private String sjtcode = null;

	/**
	* �����ڵ�
	*/	
	private String crscode = null;

	/**
	* ������ �ѱ۸�Ī
	*/	
	private String sjtname = null;

	/**
	* ������ ������Ī
	*/	
	private String sjtenam = null;

	/**
	* ����
	*/	
	private String sjtpoint = null;

	/**
	* ���ǽð�
	*/	
	private int lec_point = 0;

	/**
	* �ǽ��ð�
	*/	
	private int trn_point = 0;

	/**
	* ���ϰ����ڵ�
	*/	
	private String eq_sjtcode = null;

	/**
	* ���ϱ������
	*/	
	private String eq_sjtname = null;

	/**
	* ��ü�������ڵ�
	*/	
	private String od_sjtcode = null;

	/**
	* ��ü�������
	*/	
	private String od_sjtname = null;

	/**
	* ���������Ͻ�
	*/	
	private Date update_dt = null;

	/**
	* ���������� ID
	*/	
	private String update_id = null;

	/**
	* �������
	*/	
	private String sjttarget = null;

	/**
	* ����Ұ�
	*/	
	private String sjtdesc = null;

	/**
	* ���̼�����
	*/	
	private String reqsjt = null;


	////////////////////////////////////////////////////////////////////////////////
	

	/**
	* Get sjtcode : �������ڵ�
	* DB TYPE : VARCHAR2
	*/
	public String getSjtcode(){
		return this.sjtcode;
	}
	/**
	* Set sjtcode : �������ڵ�
	* DB TYPE : VARCHAR2
	*/
	public void setSjtcode(String sjtcode){
		this.sjtcode = sjtcode;
	}

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
	* Get sjtname : ������ �ѱ۸�Ī
	* DB TYPE : VARCHAR2
	*/
	public String getSjtname(){
		return this.sjtname;
	}
	/**
	* Set sjtname : ������ �ѱ۸�Ī
	* DB TYPE : VARCHAR2
	*/
	public void setSjtname(String sjtname){
		this.sjtname = sjtname;
	}

	/**
	* Get sjtenam : ������ ������Ī
	* DB TYPE : VARCHAR2
	*/
	public String getSjtenam(){
		return this.sjtenam;
	}
	/**
	* Set sjtenam : ������ ������Ī
	* DB TYPE : VARCHAR2
	*/
	public void setSjtenam(String sjtenam){
		this.sjtenam = sjtenam;
	}

	/**
	* Get sjtpoint : ����
	* DB TYPE : VARCHAR2
	*/
	public String getSjtpoint(){
		return this.sjtpoint;
	}
	/**
	* Set sjtpoint : ����
	* DB TYPE : VARCHAR2
	*/
	public void setSjtpoint(String sjtpoint){
		this.sjtpoint = sjtpoint;
	}

	/**
	* Get lec_point : ���ǽð�
	* DB TYPE : NUMBER
	*/
	public int getLec_point(){
		return this.lec_point;
	}
	/**
	* Set lec_point : ���ǽð�
	* DB TYPE : NUMBER
	*/
	public void setLec_point(int lec_point){
		this.lec_point = lec_point;
	}

	/**
	* Get trn_point : �ǽ��ð�
	* DB TYPE : NUMBER
	*/
	public int getTrn_point(){
		return this.trn_point;
	}
	/**
	* Set trn_point : �ǽ��ð�
	* DB TYPE : NUMBER
	*/
	public void setTrn_point(int trn_point){
		this.trn_point = trn_point;
	}

	/**
	* Get eq_sjtcode : ���ϰ����ڵ�
	* DB TYPE : VARCHAR2
	*/
	public String getEq_sjtcode(){
		return this.eq_sjtcode;
	}
	/**
	* Set eq_sjtcode : ���ϰ����ڵ�
	* DB TYPE : VARCHAR2
	*/
	public void setEq_sjtcode(String eq_sjtcode){
		this.eq_sjtcode = eq_sjtcode;
	}

	/**
	* Get eq_sjtname : ���ϱ������
	* DB TYPE : VARCHAR2
	*/
	public String getEq_sjtname(){
		return this.eq_sjtname;
	}
	/**
	* Set eq_sjtname : ���ϱ������
	* DB TYPE : VARCHAR2
	*/
	public void setEq_sjtname(String eq_sjtname){
		this.eq_sjtname = eq_sjtname;
	}

	/**
	* Get od_sjtcode : ��ü�������ڵ�
	* DB TYPE : VARCHAR2
	*/
	public String getOd_sjtcode(){
		return this.od_sjtcode;
	}
	/**
	* Set od_sjtcode : ��ü�������ڵ�
	* DB TYPE : VARCHAR2
	*/
	public void setOd_sjtcode(String od_sjtcode){
		this.od_sjtcode = od_sjtcode;
	}

	/**
	* Get od_sjtname : ��ü�������
	* DB TYPE : VARCHAR2
	*/
	public String getOd_sjtname(){
		return this.od_sjtname;
	}
	/**
	* Set od_sjtname : ��ü�������
	* DB TYPE : VARCHAR2
	*/
	public void setOd_sjtname(String od_sjtname){
		this.od_sjtname = od_sjtname;
	}

	/**
	* Get update_dt : ���������Ͻ�
	* DB TYPE : DATE
	*/
	public Date getUpdate_dt(){
		return this.update_dt;
	}
	/**
	* Set update_dt : ���������Ͻ�
	* DB TYPE : DATE
	*/
	public void setUpdate_dt(Date update_dt){
		this.update_dt = update_dt;
	}

	/**
	* Get update_id : ���������� ID
	* DB TYPE : VARCHAR2
	*/
	public String getUpdate_id(){
		return this.update_id;
	}
	/**
	* Set update_id : ���������� ID
	* DB TYPE : VARCHAR2
	*/
	public void setUpdate_id(String update_id){
		this.update_id = update_id;
	}

	/**
	* Get sjttarget : �������
	* DB TYPE : VARCHAR2
	*/
	public String getSjttarget(){
		return this.sjttarget;
	}
	/**
	* Set sjttarget : �������
	* DB TYPE : VARCHAR2
	*/
	public void setSjttarget(String sjttarget){
		this.sjttarget = sjttarget;
	}

	/**
	* Get sjtdesc : ����Ұ�
	* DB TYPE : CLOB
	*/
	public String getSjtdesc(){
		return this.sjtdesc;
	}
	/**
	* Set sjtdesc : ����Ұ�
	* DB TYPE : CLOB
	*/
	public void setSjtdesc(String sjtdesc){
		this.sjtdesc = sjtdesc;
	}

	/**
	* Get reqsjt : ���̼�����
	* DB TYPE : VARCHAR2
	*/
	public String getReqsjt(){
		return this.reqsjt;
	}
	/**
	* Set reqsjt : ���̼�����
	* DB TYPE : VARCHAR2
	*/
	public void setReqsjt(String reqsjt){
		this.reqsjt = reqsjt;
	}

}
