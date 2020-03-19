/*
 * �ۼ��� ��¥: 2005-03-02
 *
 */
package modules.community.club.beans;

import java.util.Date;

/**
 * 
 * 
 * �ۼ��� :�űԹ� �ۼ��� ��¥ : 2005-03-02
 */
public class ClubMasterBean {

	/**
	* �����ڵ�+':'+Ŭ�� ID
	*/	
	private String c_id = null;

	/**
	* Ŭ����Ī
	*/	
	private String c_name = null;

	/**
	* �û� USN
	*/	
	private String c_sysopusn = null;

	/**
	* �����̹���
	*/	
	private String c_main_image = null;

	/**
	* ����̹���
	*/	
	private String c_bg_image = null;

	/**
	* �������(S:�ý���, U:����)
	*/	
	private String c_bgtype = null;

	/**
	* ������û�Ͻ�
	*/	
	private Date c_apply_dt = null;

	/**
	* ����(A:������û��, T: ����, C:���)
	*/	
	private String c_status = null;

	/**
	* ���������Ͻ�
	*/	
	private Date c_confirm_dt = null;

	/**
	* ����Ͻ�
	*/	
	private Date c_closedt = null;

	/**
	* ȸ����
	*/	
	private int c_membercnt = 0;

	/**
	* ���ټ�
	*/	
	private int c_hit_cnt = 0;

	/**
	* ����/�����(T:����, F:�����)
	*/	
	private String is_public = null;

	/**
	* Ŭ������(CLUB_CODE.CLUB_TYPE)
	*/	
	private String c_group = null;

	/**
	* ÷��ũ������
	*/	
	private int c_size = 0;

	/**
	* ��������
	*/	
	private String c_purpose = null;

	/**
	* Ŭ�� ����
	*/	
	private String c_describe = null;

	/**
	* ���� �����Ͻ�
	*/	
	private Date c_last_dt = null;

	/**
	* ���� �湮��
	*/	
	private int c_hit_today = 0;

	/**
	* ������ ȸ�� �ڵ����ο���('T': �ڵ�����, 'F' : ��ڽ���)
	*/	
	private String c_auto_confirm = null;

	/**
	* Ŭ�� �ΰ� �̹���
	*/	
	private String c_logo_image = null;

	/**
	* �������
	*/	
	private String close_reason = null;

		
	private String cor_cd = null;

	/**
	* ��뿩�� Y:���, N:���
	*/	
	private String use_yn = null;

	
	/**
	 *  �޴�����
	 * 
	 */
	private String c_bgcolor = null;
	
    private int opacity = 100;
    
	private String sysop = null;

    ////////////////////////////////////////////////////////////////////////////////
	private String owner_nm = null;
    /**
     * @return owner_nm�� �����մϴ�.
     */
    public String getOwner_nm() {
        return owner_nm;
    }
    /**
     * @param owner_nm �����Ϸ��� owner_nm.
     */
    public void setOwner_nm(String owner_nm) {
        this.owner_nm = owner_nm;
    }
	////////////////////////////////////////////////////////////////////////////////
	

	/**
	* Get c_id : �����ڵ�+':'+Ŭ�� ID
	* DB TYPE : VARCHAR2
	*/
	public String getC_id(){
		return this.c_id;
	}
	/**
	* Set c_id : �����ڵ�+':'+Ŭ�� ID
	* DB TYPE : VARCHAR2
	*/
	public void setC_id(String c_id){
		this.c_id = c_id;
	}

	/**
	* Get c_name : Ŭ����Ī
	* DB TYPE : VARCHAR2
	*/
	public String getC_name(){
		return this.c_name;
	}
	/**
	* Set c_name : Ŭ����Ī
	* DB TYPE : VARCHAR2
	*/
	public void setC_name(String c_name){
		this.c_name = c_name;
	}

	/**
	* Get c_sysopusn : �û� USN
	* DB TYPE : VARCHAR2
	*/
	public String getC_sysopusn(){
		return this.c_sysopusn;
	}
	/**
	* Set c_sysopusn : �û� USN
	* DB TYPE : VARCHAR2
	*/
	public void setC_sysopusn(String c_sysopusn){
		this.c_sysopusn = c_sysopusn;
	}

	/**
	* Get c_main_image : �����̹���
	* DB TYPE : VARCHAR2
	*/
	public String getC_main_image(){
		return this.c_main_image;
	}
	/**
	* Set c_main_image : �����̹���
	* DB TYPE : VARCHAR2
	*/
	public void setC_main_image(String c_main_image){
		this.c_main_image = c_main_image;
	}

	/**
	* Get c_bg_image : ����̹���
	* DB TYPE : VARCHAR2
	*/
	public String getC_bg_image(){
		return this.c_bg_image;
	}
	/**
	* Set c_bg_image : ����̹���
	* DB TYPE : VARCHAR2
	*/
	public void setC_bg_image(String c_bg_image){
		this.c_bg_image = c_bg_image;
	}

	/**
	* Get c_bgtype : �������(S:�ý���, U:����)
	* DB TYPE : VARCHAR2
	*/
	public String getC_bgtype(){
		return this.c_bgtype;
	}
	/**
	* Set c_bgtype : �������(S:�ý���, U:����)
	* DB TYPE : VARCHAR2
	*/
	public void setC_bgtype(String c_bgtype){
		this.c_bgtype = c_bgtype;
	}

	/**
	* Get c_apply_dt : ������û�Ͻ�
	* DB TYPE : DATE
	*/
	public Date getC_apply_dt(){
		return this.c_apply_dt;
	}
	/**
	* Set c_apply_dt : ������û�Ͻ�
	* DB TYPE : DATE
	*/
	public void setC_apply_dt(Date c_apply_dt){
		this.c_apply_dt = c_apply_dt;
	}

	/**
	* Get c_status : ����(A:������û��, T: ����, C:���)
	* DB TYPE : VARCHAR2
	*/
	public String getC_status(){
		return this.c_status;
	}
	/**
	* Set c_status : ����(A:������û��, T: ����, C:���)
	* DB TYPE : VARCHAR2
	*/
	public void setC_status(String c_status){
		this.c_status = c_status;
	}

	/**
	* Get c_confirm_dt : ���������Ͻ�
	* DB TYPE : DATE
	*/
	public Date getC_confirm_dt(){
		return this.c_confirm_dt;
	}
	/**
	* Set c_confirm_dt : ���������Ͻ�
	* DB TYPE : DATE
	*/
	public void setC_confirm_dt(Date c_confirm_dt){
		this.c_confirm_dt = c_confirm_dt;
	}

	/**
	* Get c_closedt : ����Ͻ�
	* DB TYPE : DATE
	*/
	public Date getC_closedt(){
		return this.c_closedt;
	}
	/**
	* Set c_closedt : ����Ͻ�
	* DB TYPE : DATE
	*/
	public void setC_closedt(Date c_closedt){
		this.c_closedt = c_closedt;
	}

	/**
	* Get c_membercnt : ȸ����
	* DB TYPE : NUMBER
	*/
	public int getC_membercnt(){
		return this.c_membercnt;
	}
	/**
	* Set c_membercnt : ȸ����
	* DB TYPE : NUMBER
	*/
	public void setC_membercnt(int c_membercnt){
		this.c_membercnt = c_membercnt;
	}

	/**
	* Get c_hit_cnt : ���ټ�
	* DB TYPE : NUMBER
	*/
	public int getC_hit_cnt(){
		return this.c_hit_cnt;
	}
	/**
	* Set c_hit_cnt : ���ټ�
	* DB TYPE : NUMBER
	*/
	public void setC_hit_cnt(int c_hit_cnt){
		this.c_hit_cnt = c_hit_cnt;
	}

	/**
	* Get is_public : ����/�����(T:����, F:�����)
	* DB TYPE : VARCHAR2
	*/
	public String getIs_public(){
		return this.is_public;
	}
	/**
	* Set is_public : ����/�����(T:����, F:�����)
	* DB TYPE : VARCHAR2
	*/
	public void setIs_public(String is_public){
		this.is_public = is_public;
	}

	/**
	* Get c_group : Ŭ������(CLUB_CODE.CLUB_TYPE)
	* DB TYPE : VARCHAR2
	*/
	public String getC_group(){
		return this.c_group;
	}
	/**
	* Set c_group : Ŭ������(CLUB_CODE.CLUB_TYPE)
	* DB TYPE : VARCHAR2
	*/
	public void setC_group(String c_group){
		this.c_group = c_group;
	}

	/**
	* Get c_size : ÷��ũ������
	* DB TYPE : NUMBER
	*/
	public int getC_size(){
		return this.c_size;
	}
	/**
	* Set c_size : ÷��ũ������
	* DB TYPE : NUMBER
	*/
	public void setC_size(int c_size){
		this.c_size = c_size;
	}

	/**
	* Get c_purpose : ��������
	* DB TYPE : VARCHAR2
	*/
	public String getC_purpose(){
		return this.c_purpose;
	}
	/**
	* Set c_purpose : ��������
	* DB TYPE : VARCHAR2
	*/
	public void setC_purpose(String c_purpose){
		this.c_purpose = c_purpose;
	}

	/**
	* Get c_describe : Ŭ�� ����
	* DB TYPE : VARCHAR2
	*/
	public String getC_describe(){
		return this.c_describe;
	}
	/**
	* Set c_describe : Ŭ�� ����
	* DB TYPE : VARCHAR2
	*/
	public void setC_describe(String c_describe){
		this.c_describe = c_describe;
	}

	/**
	* Get c_last_dt : ���� �����Ͻ�
	* DB TYPE : DATE
	*/
	public Date getC_last_dt(){
		return this.c_last_dt;
	}
	/**
	* Set c_last_dt : ���� �����Ͻ�
	* DB TYPE : DATE
	*/
	public void setC_last_dt(Date c_last_dt){
		this.c_last_dt = c_last_dt;
	}

	/**
	* Get c_hit_today : ���� �湮��
	* DB TYPE : NUMBER
	*/
	public int getC_hit_today(){
		return this.c_hit_today;
	}
	/**
	* Set c_hit_today : ���� �湮��
	* DB TYPE : NUMBER
	*/
	public void setC_hit_today(int c_hit_today){
		this.c_hit_today = c_hit_today;
	}

	/**
	* Get c_auto_confirm : ������ ȸ�� �ڵ����ο���('T': �ڵ�����, 'F' : ��ڽ���)
	* DB TYPE : CHAR
	*/
	public String getC_auto_confirm(){
		return this.c_auto_confirm;
	}
	/**
	* Set c_auto_confirm : ������ ȸ�� �ڵ����ο���('T': �ڵ�����, 'F' : ��ڽ���)
	* DB TYPE : CHAR
	*/
	public void setC_auto_confirm(String c_auto_confirm){
		this.c_auto_confirm = c_auto_confirm;
	}

	/**
	* Get c_logo_image : Ŭ�� �ΰ� �̹���
	* DB TYPE : VARCHAR2
	*/
	public String getC_logo_image(){
		return this.c_logo_image;
	}
	/**
	* Set c_logo_image : Ŭ�� �ΰ� �̹���
	* DB TYPE : VARCHAR2
	*/
	public void setC_logo_image(String c_logo_image){
		this.c_logo_image = c_logo_image;
	}

	/**
	* Get close_reason : �������
	* DB TYPE : VARCHAR2
	*/
	public String getClose_reason(){
		return this.close_reason;
	}
	/**
	* Set close_reason : �������
	* DB TYPE : VARCHAR2
	*/
	public void setClose_reason(String close_reason){
		this.close_reason = close_reason;
	}

	/**
	* Get cor_cd : 
	* DB TYPE : VARCHAR2
	*/
	public String getCor_cd(){
		return this.cor_cd;
	}
	/**
	* Set cor_cd : 
	* DB TYPE : VARCHAR2
	*/
	public void setCor_cd(String cor_cd){
		this.cor_cd = cor_cd;
	}

	/**
	* Get use_yn : ��뿩�� Y:���, N:���
	* DB TYPE : VARCHAR2
	*/
	public String getUse_yn(){
		return this.use_yn;
	}
	/**
	* Set use_yn : ��뿩�� Y:���, N:���
	* DB TYPE : VARCHAR2
	*/
	public void setUse_yn(String use_yn){
		this.use_yn = use_yn;
	}

    /**
     * ����
     */
    public String getC_bgcolor() {
        return c_bgcolor;
    }
    /**
     *����
     */
    public void setC_bgcolor(String c_bgcolor) {
        this.c_bgcolor = c_bgcolor;
    }
    
    
    /**
     * ���� 
     */
    public int getOpacity() {
        return opacity;
    }
    /**
     * ����
     */
    public void setOpacity(int opacity) {
        this.opacity = opacity;
    }

	/**
	* Set sysop : 
	* DB TYPE : VARCHAR2
	*/
	public void setSysop(String sysop){
		this.sysop = sysop;
	}
    /**
     * �ü�
     */
    public String getsysop() {
        return sysop;
    }
}