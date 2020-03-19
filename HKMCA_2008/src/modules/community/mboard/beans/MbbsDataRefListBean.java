/*
 * �ۼ��� : bluevlad
 * Created on 2004. 10. 6.
 *
 */
package modules.community.mboard.beans;

import java.util.Date;


/**
 * �Խ����� ���� ���ñ� ���� 
 * @author bluevlad
 * Create by 2004. 10. 6.
 * 
 */
public class MbbsDataRefListBean {

	/**
	* �Խ���ID
	*/
	private String bid = null;

	/**
	* �Խù���ȣ
	*/
	private String c_index = null;

	/**
	* ����
	*/
	private String c_subject = null;

	/**
	* �����
	*/
	private Date c_date = null;

	/**
	* �湮Ƚ��
	*/
	private int c_visit = 0;

	/**
	* ���۹�ȣ
	*/
	private String c_ref = null;

	/**
	* ���� depth
	*/
	private int c_level = 0;

	/**
	* �������۹�ȣ
	*/
	private String c_grp = null;

	/**
	* �������۳���¼���
	*/
	private int c_step = 0;

	/**
	* ���ӿ���(T:����, W:�ؿ��,D:����)
	*/
	private String c_status = null;

	/**
	* ����ڹ�ȣ
	*/
	private String usn = null;

	/**
	* �ʸ�
	*/
	private String wname = null;


	/**
	* ��ۼ�
	*/
	private int c_ccnt = 0;

	/**
	* ��� IP
	*/
	private String c_ip = null;

	/**
	* �ۼ��� E-Mail �ּ�
	*/
	private String c_email = null;

	/**
	* �ڽıۼ�(QnAȮ�ο�)
	*/
	private int c_rcnt = 0;

	/**
	* ��õ��
	*/
	private int c_cnt_p = 0;

	/**
	* �޴��
	*/
	private int c_cnt_m = 0;

	/**
	* ī�װ�
	*/
	private String c_category = null;

	/**
	* ��������(T:����, ������:�Ϲ�)
	*/
	private String is_notice = null;

	/**
	 * ��ǥ�̹��� ���ϸ�
	 */
	private String c_image = null;
	
	/**
	 * ������ ������ ���� : P(prev), C(current), N(next)
	 */
	private String prevnext = null;
	
	////////////////////////////////////////////////////////////////////////////////
	
	/**
	* Get bid : �Խ���ID
	* DB TYPE : VARCHAR2
	*/
	public String getBid(){
		return this.bid;
	}
	/**
	* Set bid : �Խ���ID
	* DB TYPE : VARCHAR2
	*/
	public void setBid(String bid){
		this.bid = bid;
	}

	/**
	* Get c_index : �Խù���ȣ
	* DB TYPE : VARCHAR2
	*/
	public String getC_index(){
		return this.c_index;
	}
	/**
	* Set c_index : �Խù���ȣ
	* DB TYPE : VARCHAR2
	*/
	public void setC_index(String c_index){
		this.c_index = c_index;
	}

	/**
	* Get c_subject : ����
	* DB TYPE : VARCHAR2
	*/
	public String getC_subject(){
		return this.c_subject;
	}
	/**
	* Set c_subject : ����
	* DB TYPE : VARCHAR2
	*/
	public void setC_subject(String c_subject){
		this.c_subject = c_subject;
	}

	/**
	* Get c_date : �����
	* DB TYPE : DATE
	*/
	public Date getC_date(){
		return this.c_date;
	}
	/**
	* Set c_date : �����
	* DB TYPE : DATE
	*/
	public void setC_date(Date c_date){
		this.c_date = c_date;
	}

	/**
	* Get c_visit : �湮Ƚ��
	* DB TYPE : NUMBER
	*/
	public int getC_visit(){
		return this.c_visit;
	}
	/**
	* Set c_visit : �湮Ƚ��
	* DB TYPE : NUMBER
	*/
	public void setC_visit(int c_visit){
		this.c_visit = c_visit;
	}

	/**
	* Get c_ref : ���۹�ȣ
	* DB TYPE : VARCHAR2
	*/
	public String getC_ref(){
		return this.c_ref;
	}
	/**
	* Set c_ref : ���۹�ȣ
	* DB TYPE : VARCHAR2
	*/
	public void setC_ref(String c_ref){
		this.c_ref = c_ref;
	}

	/**
	* Get c_level : ���� depth
	* DB TYPE : NUMBER
	*/
	public int getC_level(){
		return this.c_level;
	}
	/**
	* Set c_level : ���� depth
	* DB TYPE : NUMBER
	*/
	public void setC_level(int c_level){
		this.c_level = c_level;
	}

	/**
	* Get c_grp : �������۹�ȣ
	* DB TYPE : VARCHAR2
	*/
	public String getC_grp(){
		return this.c_grp;
	}
	/**
	* Set c_grp : �������۹�ȣ
	* DB TYPE : VARCHAR2
	*/
	public void setC_grp(String c_grp){
		this.c_grp = c_grp;
	}

	/**
	* Get c_step : �������۳���¼���
	* DB TYPE : NUMBER
	*/
	public int getC_step(){
		return this.c_step;
	}
	/**
	* Set c_step : �������۳���¼���
	* DB TYPE : NUMBER
	*/
	public void setC_step(int c_step){
		this.c_step = c_step;
	}

	/**
	* Get c_status : ���ӿ���(T:����, W:�ؿ��,D:����)
	* DB TYPE : CHAR
	*/
	public String getC_status(){
		return this.c_status;
	}
	/**
	* Set c_status : ���ӿ���(T:����, W:�ؿ��,D:����)
	* DB TYPE : CHAR
	*/
	public void setC_status(String c_status){
		this.c_status = c_status;
	}

	/**
	* Get usn : ����ڹ�ȣ
	* DB TYPE : VARCHAR2
	*/
	public String getUsn(){
		return this.usn;
	}
	/**
	* Set usn : ����ڹ�ȣ
	* DB TYPE : VARCHAR2
	*/
	public void setUsn(String usn){
		this.usn = usn;
	}

	/**
	* Get wname : �ʸ�
	* DB TYPE : VARCHAR2
	*/
	public String getWname(){
		return this.wname;
	}
	/**
	* Set wname : �ʸ�
	* DB TYPE : VARCHAR2
	*/
	public void setWname(String wname){
		this.wname = wname;
	}

	/**
	* Get c_ccnt : ��ۼ�
	* DB TYPE : NUMBER
	*/
	public int getC_ccnt(){
		return this.c_ccnt;
	}
	/**
	* Set c_ccnt : ��ۼ�
	* DB TYPE : NUMBER
	*/
	public void setC_ccnt(int c_ccnt){
		this.c_ccnt = c_ccnt;
	}

	/**
	* Get c_ip : ��� IP
	* DB TYPE : VARCHAR2
	*/
	public String getC_ip(){
		return this.c_ip;
	}
	/**
	* Set c_ip : ��� IP
	* DB TYPE : VARCHAR2
	*/
	public void setC_ip(String c_ip){
		this.c_ip = c_ip;
	}

	/**
	* Get c_email : �ۼ��� E-Mail �ּ�
	* DB TYPE : VARCHAR2
	*/
	public String getC_email(){
		return this.c_email;
	}
	/**
	* Set c_email : �ۼ��� E-Mail �ּ�
	* DB TYPE : VARCHAR2
	*/
	public void setC_email(String c_email){
		this.c_email = c_email;
	}

	/**
	* Get c_rcnt : �ڽıۼ�(QnAȮ�ο�)
	* DB TYPE : NUMBER
	*/
	public int getC_rcnt(){
		return this.c_rcnt;
	}
	/**
	* Set c_rcnt : �ڽıۼ�(QnAȮ�ο�)
	* DB TYPE : NUMBER
	*/
	public void setC_rcnt(int c_rcnt){
		this.c_rcnt = c_rcnt;
	}

	/**
	* Get c_cnt_p : ��õ��
	* DB TYPE : NUMBER
	*/
	public int getC_cnt_p(){
		return this.c_cnt_p;
	}
	/**
	* Set c_cnt_p : ��õ��
	* DB TYPE : NUMBER
	*/
	public void setC_cnt_p(int c_cnt_p){
		this.c_cnt_p = c_cnt_p;
	}

	/**
	* Get c_cnt_m : �޴��
	* DB TYPE : NUMBER
	*/
	public int getC_cnt_m(){
		return this.c_cnt_m;
	}
	/**
	* Set c_cnt_m : �޴��
	* DB TYPE : NUMBER
	*/
	public void setC_cnt_m(int c_cnt_m){
		this.c_cnt_m = c_cnt_m;
	}

	/**
	* Get c_category : ī�װ�
	* DB TYPE : VARCHAR2
	*/
	public String getC_category(){
		return this.c_category;
	}
	/**
	* Set c_category : ī�װ�
	* DB TYPE : VARCHAR2
	*/
	public void setC_category(String c_category){
		this.c_category = c_category;
	}

	/**
	* Get is_notice : ��������(T:����, ������:�Ϲ�)
	* DB TYPE : VARCHAR2
	*/
	public String getIs_notice(){
		return this.is_notice;
	}
	/**
	* Set is_notice : ��������(T:����, ������:�Ϲ�)
	* DB TYPE : VARCHAR2
	*/
	public void setIs_notice(String is_notice){
		this.is_notice = is_notice;
	}
	/**
	 * ��ǥ�̹��� ���ϸ�
	 */
    public String getC_image() {
        return c_image;
    }
	/**
	 * ��ǥ�̹��� ���ϸ�
	 */
    public void setC_image(String c_image) {
        this.c_image = c_image;
    }
    
    /**
     * ������ ������ ���� : P(prev), C(current), N(next)
     * @return
     */
    public String getPrevnext() {
        return prevnext;
    }
    /**
     * ������ ������ ���� : P(prev), C(current), N(next)
     * @param prevnext
     */
    public void setPrevnext(String prevnext) {
        this.prevnext = prevnext;
    }
}
