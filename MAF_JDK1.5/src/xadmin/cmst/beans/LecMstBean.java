/*
 * Created on 2005. 5. 17.
 * 
 * Copyright (c) 2004 (��)�̷��� All rights reserved.
 */
package xadmin.cmst.beans;

import java.util.Date;

public class LecMstBean {
	private String crsname = null;
	private String crscode = null;
	private String sjtname = null;
	private long rcnt = 0;
	
	/**
	 * ������ ��
	 * @return Returns the rCnt.
	 */
	public long getRcnt() {
		return rcnt;
	}
	/**
	 * ������ ��
	 * @param cnt The rCnt to set.
	 */
	public void setRcnt(long cnt) {
		rcnt = cnt;
	}
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
	
	
	/**
	 * @return Returns the crscode.
	 */
	public String getCrscode() {
		return crscode;
	}
	/**
	 * @param crscode The crscode to set.
	 */
	public void setCrscode(String crscode) {
		this.crscode = crscode;
	}
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


	//==========================================
	//== �ڵ� ���� 
	//==========================================
	

	/**
	* �����ڵ�-�⵵-�б�
	*/	
	private String leccode = null;

	/**
	* �����ڵ�
	*/	
	private String sjtcode = null;

		
	private String lecname = null;

		
	private String lecyear = null;

		
	private String lecterm = null;

		
	private String bunban = "1";

	/**
	* ��������
	*/	
	private int lecquot = 0;

	/**
	* �л������
	*/	
	private String admcode = null;

	/**
	* ���Ǹ�ǥ �� ����
	*/	
	private String lecobjt = null;

	/**
	* �Ѱ�����������
	*/	
	private int lectttr = 0;

	/**
	* ���ȭ�� ���� ���� ���� W : �ֺ�  T : ����
	*/	
	private String lecwtfg = null;

	/**
	* O:���� C:�� E:��������
	*/	
	private String lecstat = "C";

	/**
	* �����н��ð�
	*/	
	private int lectime = 0;

	/**
	* �н��Ⱓ�� �ϴ����� �Է�
	*/	
	private int lecgigan = 0;

	/**
	* ���� ������
	*/	
	private Date start_dt = null;

	/**
	* ���¹���
	*/	
	private String lecbound = null;

	/**
	* ���谭��
	*/	
	private String leclink = null;

	/**
	* ��������
	*/	
	private String lec_notice = null;

	/**
	* ���� �������ڵ�����
	*/	
	private int lecsample = 0;

	/**
	* ü�谭�¿���               
Y:����ü�谭��               
N:���ᰭ��(�⺻��)
	*/	
	private String isfree = "N";

		
	private Date update_dt = null;

		
	private String update_id = null;

	/**
	* ������û������
	*/	
	private Date rstart_dt = null;

	/**
	* ������û������
	*/	
	private Date rfinish_dt = null;

	/**
	* ���±���: N=����, O=��ð���
	*/	
	private String lectype = "N";

	/**
	* ���� ������
	*/	
	private Date finish_dt = null;

	/**
	* ���¸�(����)
	*/	
	private String lecenam = null;

	/**
	* ������ ID
	*/	
	private String cnt_id = null;

	/**
	* ���ǰ�ȹ������
	*/	
	private String attach_file = null;

	/**
	* ���� ���ϸ�
	*/	
	private String attach_filename = null;

	/**
	* ��������:�⼮��
	*/	
	private int rate_att = 10;

	/**
	* ��������:����
	*/	
	private int rate_exam = 60;

	/**
	* ��������:����
	*/	
	private int rate_report = 5;

	/**
	* ��������:���
	*/	
	private int rate_parti = 5;

	/**
	* ��������:����
	*/	
	private int rate_jindo = 20;

	/**
	* ���輺������:����
	*/	
	private int exam_rate_j = 30;

	/**
	* ���輺������:����
	*/	
	private int exam_rate_n = 70;
	////////////////////////////////////////////////////////////////////////////////
	

	/**
	* Get leccode : �����ڵ�-�⵵-�б�
	* DB TYPE : VARCHAR2
	*/
	public String getLeccode(){
		return this.leccode;
	}
	/**
	* Set leccode : �����ڵ�-�⵵-�б�
	* DB TYPE : VARCHAR2
	*/
	public void setLeccode(String leccode){
		this.leccode = leccode;
	}

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
	* Get lecname : 
	* DB TYPE : VARCHAR2
	*/
	public String getLecname(){
		return this.lecname;
	}
	/**
	* Set lecname : 
	* DB TYPE : VARCHAR2
	*/
	public void setLecname(String lecname){
		this.lecname = lecname;
	}

	/**
	* Get lecyear : 
	* DB TYPE : VARCHAR2
	*/
	public String getLecyear(){
		return this.lecyear;
	}
	/**
	* Set lecyear : 
	* DB TYPE : VARCHAR2
	*/
	public void setLecyear(String lecyear){
		this.lecyear = lecyear;
	}

	/**
	* Get lecterm : 
	* DB TYPE : VARCHAR2
	*/
	public String getLecterm(){
		return this.lecterm;
	}
	/**
	* Set lecterm : 
	* DB TYPE : VARCHAR2
	*/
	public void setLecterm(String lecterm){
		this.lecterm = lecterm;
	}

	/**
	* Get bunban : 
	* DB TYPE : VARCHAR2
	*/
	public String getBunban(){
		return this.bunban;
	}
	/**
	* Set bunban : 
	* DB TYPE : VARCHAR2
	*/
	public void setBunban(String bunban){
		this.bunban = bunban;
	}

	/**
	* Get lecquot : ��������
	* DB TYPE : NUMBER
	*/
	public int getLecquot(){
		return this.lecquot;
	}
	/**
	* Set lecquot : ��������
	* DB TYPE : NUMBER
	*/
	public void setLecquot(int lecquot){
		this.lecquot = lecquot;
	}

	/**
	* Get admcode : �л������
	* DB TYPE : VARCHAR2
	*/
	public String getAdmcode(){
		return this.admcode;
	}
	/**
	* Set admcode : �л������
	* DB TYPE : VARCHAR2
	*/
	public void setAdmcode(String admcode){
		this.admcode = admcode;
	}

	/**
	* Get lecobjt : ���Ǹ�ǥ �� ����
	* DB TYPE : VARCHAR2
	*/
	public String getLecobjt(){
		return this.lecobjt;
	}
	/**
	* Set lecobjt : ���Ǹ�ǥ �� ����
	* DB TYPE : VARCHAR2
	*/
	public void setLecobjt(String lecobjt){
		this.lecobjt = lecobjt;
	}

	/**
	* Get lectttr : �Ѱ�����������
	* DB TYPE : NUMBER
	*/
	public int getLectttr(){
		return this.lectttr;
	}
	/**
	* Set lectttr : �Ѱ�����������
	* DB TYPE : NUMBER
	*/
	public void setLectttr(int lectttr){
		this.lectttr = lectttr;
	}

	/**
	* Get lecwtfg : ���ȭ�� ���� ���� ���� W : �ֺ�  T : ����
	* DB TYPE : VARCHAR2
	*/
	public String getLecwtfg(){
		return this.lecwtfg;
	}
	/**
	* Set lecwtfg : ���ȭ�� ���� ���� ���� W : �ֺ�  T : ����
	* DB TYPE : VARCHAR2
	*/
	public void setLecwtfg(String lecwtfg){
		this.lecwtfg = lecwtfg;
	}

	/**
	* Get lecstat : O:���� C:�� E:��������
	* DB TYPE : VARCHAR2
	*/
	public String getLecstat(){
		return this.lecstat;
	}
	/**
	* Set lecstat : O:���� C:�� E:��������
	* DB TYPE : VARCHAR2
	*/
	public void setLecstat(String lecstat){
		this.lecstat = lecstat;
	}

	/**
	* Get lectime : �����н��ð�
	* DB TYPE : NUMBER
	*/
	public int getLectime(){
		return this.lectime;
	}
	/**
	* Set lectime : �����н��ð�
	* DB TYPE : NUMBER
	*/
	public void setLectime(int lectime){
		this.lectime = lectime;
	}

	/**
	* Get lecgigan : �н��Ⱓ�� �ϴ����� �Է�
	* DB TYPE : NUMBER
	*/
	public int getLecgigan(){
		return this.lecgigan;
	}
	/**
	* Set lecgigan : �н��Ⱓ�� �ϴ����� �Է�
	* DB TYPE : NUMBER
	*/
	public void setLecgigan(int lecgigan){
		this.lecgigan = lecgigan;
	}

	/**
	* Get start_dt : ���� ������
	* DB TYPE : DATE
	*/
	public Date getStart_dt(){
		return this.start_dt;
	}
	/**
	* Set start_dt : ���� ������
	* DB TYPE : DATE
	*/
	public void setStart_dt(Date start_dt){
		this.start_dt = start_dt;
	}

	/**
	* Get lecbound : ���¹���
	* DB TYPE : VARCHAR2
	*/
	public String getLecbound(){
		return this.lecbound;
	}
	/**
	* Set lecbound : ���¹���
	* DB TYPE : VARCHAR2
	*/
	public void setLecbound(String lecbound){
		this.lecbound = lecbound;
	}

	/**
	* Get leclink : ���谭��
	* DB TYPE : VARCHAR2
	*/
	public String getLeclink(){
		return this.leclink;
	}
	/**
	* Set leclink : ���谭��
	* DB TYPE : VARCHAR2
	*/
	public void setLeclink(String leclink){
		this.leclink = leclink;
	}

	/**
	* Get lec_notice : ��������
	* DB TYPE : VARCHAR2
	*/
	public String getLec_notice(){
		return this.lec_notice;
	}
	/**
	* Set lec_notice : ��������
	* DB TYPE : VARCHAR2
	*/
	public void setLec_notice(String lec_notice){
		this.lec_notice = lec_notice;
	}

	/**
	* Get lecsample : ���� �������ڵ�����
	* DB TYPE : NUMBER
	*/
	public int getLecsample(){
		return this.lecsample;
	}
	/**
	* Set lecsample : ���� �������ڵ�����
	* DB TYPE : NUMBER
	*/
	public void setLecsample(int lecsample){
		this.lecsample = lecsample;
	}

	/**
	* Get isfree : ü�谭�¿���               
Y:����ü�谭��               
N:���ᰭ��(�⺻��)
	* DB TYPE : VARCHAR2
	*/
	public String getIsfree(){
		return this.isfree;
	}
	/**
	* Set isfree : ü�谭�¿���               
Y:����ü�谭��               
N:���ᰭ��(�⺻��)
	* DB TYPE : VARCHAR2
	*/
	public void setIsfree(String isfree){
		this.isfree = isfree;
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
	* Get rstart_dt : ������û������
	* DB TYPE : DATE
	*/
	public Date getRstart_dt(){
		return this.rstart_dt;
	}
	/**
	* Set rstart_dt : ������û������
	* DB TYPE : DATE
	*/
	public void setRstart_dt(Date rstart_dt){
		this.rstart_dt = rstart_dt;
	}

	/**
	* Get rfinish_dt : ������û������
	* DB TYPE : DATE
	*/
	public Date getRfinish_dt(){
		return this.rfinish_dt;
	}
	/**
	* Set rfinish_dt : ������û������
	* DB TYPE : DATE
	*/
	public void setRfinish_dt(Date rfinish_dt){
		this.rfinish_dt = rfinish_dt;
	}

	/**
	* Get lectype : ���±���: N=����, O=��ð���
	* DB TYPE : CHAR
	*/
	public String getLectype(){
		return this.lectype;
	}
	/**
	* Set lectype : ���±���: N=����, O=��ð���
	* DB TYPE : CHAR
	*/
	public void setLectype(String lectype){
		this.lectype = lectype;
	}

	/**
	* Get finish_dt : ���� ������
	* DB TYPE : DATE
	*/
	public Date getFinish_dt(){
		return this.finish_dt;
	}
	/**
	* Set finish_dt : ���� ������
	* DB TYPE : DATE
	*/
	public void setFinish_dt(Date finish_dt){
		this.finish_dt = finish_dt;
	}

	/**
	* Get lecenam : ���¸�(����)
	* DB TYPE : VARCHAR2
	*/
	public String getLecenam(){
		return this.lecenam;
	}
	/**
	* Set lecenam : ���¸�(����)
	* DB TYPE : VARCHAR2
	*/
	public void setLecenam(String lecenam){
		this.lecenam = lecenam;
	}

	/**
	* Get cnt_id : ������ ID
	* DB TYPE : VARCHAR2
	*/
	public String getCnt_id(){
		return this.cnt_id;
	}
	/**
	* Set cnt_id : ������ ID
	* DB TYPE : VARCHAR2
	*/
	public void setCnt_id(String cnt_id){
		this.cnt_id = cnt_id;
	}

	/**
	* Get attach_file : ���ǰ�ȹ������
	* DB TYPE : VARCHAR2
	*/
	public String getAttach_file(){
		return this.attach_file;
	}
	/**
	* Set attach_file : ���ǰ�ȹ������
	* DB TYPE : VARCHAR2
	*/
	public void setAttach_file(String attach_file){
		this.attach_file = attach_file;
	}

	/**
	* Get attach_filename : ���� ���ϸ�
	* DB TYPE : VARCHAR2
	*/
	public String getAttach_filename(){
		return this.attach_filename;
	}
	/**
	* Set attach_filename : ���� ���ϸ�
	* DB TYPE : VARCHAR2
	*/
	public void setAttach_filename(String attach_filename){
		this.attach_filename = attach_filename;
	}

	/**
	* Get rate_att : ��������:�⼮��
	* DB TYPE : NUMBER
	*/
	public int getRate_att(){
		return this.rate_att;
	}
	/**
	* Set rate_att : ��������:�⼮��
	* DB TYPE : NUMBER
	*/
	public void setRate_att(int rate_att){
		this.rate_att = rate_att;
	}

	/**
	* Get rate_exam : ��������:����
	* DB TYPE : NUMBER
	*/
	public int getRate_exam(){
		return this.rate_exam;
	}
	/**
	* Set rate_exam : ��������:����
	* DB TYPE : NUMBER
	*/
	public void setRate_exam(int rate_exam){
		this.rate_exam = rate_exam;
	}

	/**
	* Get rate_report : ��������:����
	* DB TYPE : NUMBER
	*/
	public int getRate_report(){
		return this.rate_report;
	}
	/**
	* Set rate_report : ��������:����
	* DB TYPE : NUMBER
	*/
	public void setRate_report(int rate_report){
		this.rate_report = rate_report;
	}

	/**
	* Get rate_parti : ��������:���
	* DB TYPE : NUMBER
	*/
	public int getRate_parti(){
		return this.rate_parti;
	}
	/**
	* Set rate_parti : ��������:���
	* DB TYPE : NUMBER
	*/
	public void setRate_parti(int rate_parti){
		this.rate_parti = rate_parti;
	}

	/**
	* Get rate_jindo : ��������:����
	* DB TYPE : NUMBER
	*/
	public int getRate_jindo(){
		return this.rate_jindo;
	}
	/**
	* Set rate_jindo : ��������:����
	* DB TYPE : NUMBER
	*/
	public void setRate_jindo(int rate_jindo){
		this.rate_jindo = rate_jindo;
	}
	
	/**
	* Get exam_rate_j : ���輺������:����
	* DB TYPE : NUMBER
	*/
	public int getExam_rate_j(){
		return this.exam_rate_j;
	}
	/**
	* Set exam_rate_j : ���輺������:����
	* DB TYPE : NUMBER
	*/
	public void setExam_rate_j(int exam_rate_j){
		this.exam_rate_j = exam_rate_j;
	}

	/**
	* Get exam_rate_n : ���輺������:����
	* DB TYPE : NUMBER
	*/
	public int getExam_rate_n(){
		return this.exam_rate_n;
	}
	/**
	* Set exam_rate_n : ���輺������:����
	* DB TYPE : NUMBER
	*/
	public void setExam_rate_n(int exam_rate_n){
		this.exam_rate_n = exam_rate_n;
	}

}
