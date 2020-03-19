/*
 * 작성된 날짜: 2005-03-02
 *
 */
package modules.community.club.beans;

import java.util.Date;

/**
 * 
 * 
 * 작성자 :신규문 작성된 날짜 : 2005-03-02
 */
public class ClubMasterBean {

	/**
	* 강좌코드+':'+클럽 ID
	*/	
	private String c_id = null;

	/**
	* 클럼명칭
	*/	
	private String c_name = null;

	/**
	* 시삽 USN
	*/	
	private String c_sysopusn = null;

	/**
	* 메인이미지
	*/	
	private String c_main_image = null;

	/**
	* 배경이미지
	*/	
	private String c_bg_image = null;

	/**
	* 배경유형(S:시스템, U:개인)
	*/	
	private String c_bgtype = null;

	/**
	* 개설신청일시
	*/	
	private Date c_apply_dt = null;

	/**
	* 상태(A:개설신청중, T: 정상, C:폐쇄)
	*/	
	private String c_status = null;

	/**
	* 개설승인일시
	*/	
	private Date c_confirm_dt = null;

	/**
	* 폐쇄일시
	*/	
	private Date c_closedt = null;

	/**
	* 회원수
	*/	
	private int c_membercnt = 0;

	/**
	* 접근수
	*/	
	private int c_hit_cnt = 0;

	/**
	* 공개/비공개(T:공개, F:비공개)
	*/	
	private String is_public = null;

	/**
	* 클럽구분(CLUB_CODE.CLUB_TYPE)
	*/	
	private String c_group = null;

	/**
	* 첨부크기제한
	*/	
	private int c_size = 0;

	/**
	* 개설취지
	*/	
	private String c_purpose = null;

	/**
	* 클럽 설명
	*/	
	private String c_describe = null;

	/**
	* 최종 접근일시
	*/	
	private Date c_last_dt = null;

	/**
	* 오늘 방문자
	*/	
	private int c_hit_today = 0;

	/**
	* 가입한 회원 자동승인여부('T': 자동승인, 'F' : 운영자승인)
	*/	
	private String c_auto_confirm = null;

	/**
	* 클럽 로고 이미지
	*/	
	private String c_logo_image = null;

	/**
	* 폐쇄이유
	*/	
	private String close_reason = null;

		
	private String cor_cd = null;

	/**
	* 사용여부 Y:사용, N:폐쇄
	*/	
	private String use_yn = null;

	
	/**
	 *  메뉴배경색
	 * 
	 */
	private String c_bgcolor = null;
	
    private int opacity = 100;
    
	private String sysop = null;

    ////////////////////////////////////////////////////////////////////////////////
	private String owner_nm = null;
    /**
     * @return owner_nm을 리턴합니다.
     */
    public String getOwner_nm() {
        return owner_nm;
    }
    /**
     * @param owner_nm 설정하려는 owner_nm.
     */
    public void setOwner_nm(String owner_nm) {
        this.owner_nm = owner_nm;
    }
	////////////////////////////////////////////////////////////////////////////////
	

	/**
	* Get c_id : 강좌코드+':'+클럽 ID
	* DB TYPE : VARCHAR2
	*/
	public String getC_id(){
		return this.c_id;
	}
	/**
	* Set c_id : 강좌코드+':'+클럽 ID
	* DB TYPE : VARCHAR2
	*/
	public void setC_id(String c_id){
		this.c_id = c_id;
	}

	/**
	* Get c_name : 클럼명칭
	* DB TYPE : VARCHAR2
	*/
	public String getC_name(){
		return this.c_name;
	}
	/**
	* Set c_name : 클럼명칭
	* DB TYPE : VARCHAR2
	*/
	public void setC_name(String c_name){
		this.c_name = c_name;
	}

	/**
	* Get c_sysopusn : 시삽 USN
	* DB TYPE : VARCHAR2
	*/
	public String getC_sysopusn(){
		return this.c_sysopusn;
	}
	/**
	* Set c_sysopusn : 시삽 USN
	* DB TYPE : VARCHAR2
	*/
	public void setC_sysopusn(String c_sysopusn){
		this.c_sysopusn = c_sysopusn;
	}

	/**
	* Get c_main_image : 메인이미지
	* DB TYPE : VARCHAR2
	*/
	public String getC_main_image(){
		return this.c_main_image;
	}
	/**
	* Set c_main_image : 메인이미지
	* DB TYPE : VARCHAR2
	*/
	public void setC_main_image(String c_main_image){
		this.c_main_image = c_main_image;
	}

	/**
	* Get c_bg_image : 배경이미지
	* DB TYPE : VARCHAR2
	*/
	public String getC_bg_image(){
		return this.c_bg_image;
	}
	/**
	* Set c_bg_image : 배경이미지
	* DB TYPE : VARCHAR2
	*/
	public void setC_bg_image(String c_bg_image){
		this.c_bg_image = c_bg_image;
	}

	/**
	* Get c_bgtype : 배경유형(S:시스템, U:개인)
	* DB TYPE : VARCHAR2
	*/
	public String getC_bgtype(){
		return this.c_bgtype;
	}
	/**
	* Set c_bgtype : 배경유형(S:시스템, U:개인)
	* DB TYPE : VARCHAR2
	*/
	public void setC_bgtype(String c_bgtype){
		this.c_bgtype = c_bgtype;
	}

	/**
	* Get c_apply_dt : 개설신청일시
	* DB TYPE : DATE
	*/
	public Date getC_apply_dt(){
		return this.c_apply_dt;
	}
	/**
	* Set c_apply_dt : 개설신청일시
	* DB TYPE : DATE
	*/
	public void setC_apply_dt(Date c_apply_dt){
		this.c_apply_dt = c_apply_dt;
	}

	/**
	* Get c_status : 상태(A:개설신청중, T: 정상, C:폐쇄)
	* DB TYPE : VARCHAR2
	*/
	public String getC_status(){
		return this.c_status;
	}
	/**
	* Set c_status : 상태(A:개설신청중, T: 정상, C:폐쇄)
	* DB TYPE : VARCHAR2
	*/
	public void setC_status(String c_status){
		this.c_status = c_status;
	}

	/**
	* Get c_confirm_dt : 개설승인일시
	* DB TYPE : DATE
	*/
	public Date getC_confirm_dt(){
		return this.c_confirm_dt;
	}
	/**
	* Set c_confirm_dt : 개설승인일시
	* DB TYPE : DATE
	*/
	public void setC_confirm_dt(Date c_confirm_dt){
		this.c_confirm_dt = c_confirm_dt;
	}

	/**
	* Get c_closedt : 폐쇄일시
	* DB TYPE : DATE
	*/
	public Date getC_closedt(){
		return this.c_closedt;
	}
	/**
	* Set c_closedt : 폐쇄일시
	* DB TYPE : DATE
	*/
	public void setC_closedt(Date c_closedt){
		this.c_closedt = c_closedt;
	}

	/**
	* Get c_membercnt : 회원수
	* DB TYPE : NUMBER
	*/
	public int getC_membercnt(){
		return this.c_membercnt;
	}
	/**
	* Set c_membercnt : 회원수
	* DB TYPE : NUMBER
	*/
	public void setC_membercnt(int c_membercnt){
		this.c_membercnt = c_membercnt;
	}

	/**
	* Get c_hit_cnt : 접근수
	* DB TYPE : NUMBER
	*/
	public int getC_hit_cnt(){
		return this.c_hit_cnt;
	}
	/**
	* Set c_hit_cnt : 접근수
	* DB TYPE : NUMBER
	*/
	public void setC_hit_cnt(int c_hit_cnt){
		this.c_hit_cnt = c_hit_cnt;
	}

	/**
	* Get is_public : 공개/비공개(T:공개, F:비공개)
	* DB TYPE : VARCHAR2
	*/
	public String getIs_public(){
		return this.is_public;
	}
	/**
	* Set is_public : 공개/비공개(T:공개, F:비공개)
	* DB TYPE : VARCHAR2
	*/
	public void setIs_public(String is_public){
		this.is_public = is_public;
	}

	/**
	* Get c_group : 클럽구분(CLUB_CODE.CLUB_TYPE)
	* DB TYPE : VARCHAR2
	*/
	public String getC_group(){
		return this.c_group;
	}
	/**
	* Set c_group : 클럽구분(CLUB_CODE.CLUB_TYPE)
	* DB TYPE : VARCHAR2
	*/
	public void setC_group(String c_group){
		this.c_group = c_group;
	}

	/**
	* Get c_size : 첨부크기제한
	* DB TYPE : NUMBER
	*/
	public int getC_size(){
		return this.c_size;
	}
	/**
	* Set c_size : 첨부크기제한
	* DB TYPE : NUMBER
	*/
	public void setC_size(int c_size){
		this.c_size = c_size;
	}

	/**
	* Get c_purpose : 개설취지
	* DB TYPE : VARCHAR2
	*/
	public String getC_purpose(){
		return this.c_purpose;
	}
	/**
	* Set c_purpose : 개설취지
	* DB TYPE : VARCHAR2
	*/
	public void setC_purpose(String c_purpose){
		this.c_purpose = c_purpose;
	}

	/**
	* Get c_describe : 클럽 설명
	* DB TYPE : VARCHAR2
	*/
	public String getC_describe(){
		return this.c_describe;
	}
	/**
	* Set c_describe : 클럽 설명
	* DB TYPE : VARCHAR2
	*/
	public void setC_describe(String c_describe){
		this.c_describe = c_describe;
	}

	/**
	* Get c_last_dt : 최종 접근일시
	* DB TYPE : DATE
	*/
	public Date getC_last_dt(){
		return this.c_last_dt;
	}
	/**
	* Set c_last_dt : 최종 접근일시
	* DB TYPE : DATE
	*/
	public void setC_last_dt(Date c_last_dt){
		this.c_last_dt = c_last_dt;
	}

	/**
	* Get c_hit_today : 오늘 방문자
	* DB TYPE : NUMBER
	*/
	public int getC_hit_today(){
		return this.c_hit_today;
	}
	/**
	* Set c_hit_today : 오늘 방문자
	* DB TYPE : NUMBER
	*/
	public void setC_hit_today(int c_hit_today){
		this.c_hit_today = c_hit_today;
	}

	/**
	* Get c_auto_confirm : 가입한 회원 자동승인여부('T': 자동승인, 'F' : 운영자승인)
	* DB TYPE : CHAR
	*/
	public String getC_auto_confirm(){
		return this.c_auto_confirm;
	}
	/**
	* Set c_auto_confirm : 가입한 회원 자동승인여부('T': 자동승인, 'F' : 운영자승인)
	* DB TYPE : CHAR
	*/
	public void setC_auto_confirm(String c_auto_confirm){
		this.c_auto_confirm = c_auto_confirm;
	}

	/**
	* Get c_logo_image : 클럽 로고 이미지
	* DB TYPE : VARCHAR2
	*/
	public String getC_logo_image(){
		return this.c_logo_image;
	}
	/**
	* Set c_logo_image : 클럽 로고 이미지
	* DB TYPE : VARCHAR2
	*/
	public void setC_logo_image(String c_logo_image){
		this.c_logo_image = c_logo_image;
	}

	/**
	* Get close_reason : 폐쇄이유
	* DB TYPE : VARCHAR2
	*/
	public String getClose_reason(){
		return this.close_reason;
	}
	/**
	* Set close_reason : 폐쇄이유
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
	* Get use_yn : 사용여부 Y:사용, N:폐쇄
	* DB TYPE : VARCHAR2
	*/
	public String getUse_yn(){
		return this.use_yn;
	}
	/**
	* Set use_yn : 사용여부 Y:사용, N:폐쇄
	* DB TYPE : VARCHAR2
	*/
	public void setUse_yn(String use_yn){
		this.use_yn = use_yn;
	}

    /**
     * 배경색
     */
    public String getC_bgcolor() {
        return c_bgcolor;
    }
    /**
     *배경색
     */
    public void setC_bgcolor(String c_bgcolor) {
        this.c_bgcolor = c_bgcolor;
    }
    
    
    /**
     * 투명도 
     */
    public int getOpacity() {
        return opacity;
    }
    /**
     * 투명도
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
     * 시샵
     */
    public String getsysop() {
        return sysop;
    }
}