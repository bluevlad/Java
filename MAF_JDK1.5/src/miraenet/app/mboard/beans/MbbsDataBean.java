/*
 * 작성자 : 김상준 Created on 2004. 9. 23.
 *  
 */
package miraenet.app.mboard.beans;

import java.util.Date;

/**
 * @author 김상준(goindole@miraenet.com) Create by 2004. 9. 23. MBBS_DATA 테이블의
 *             Item을 정의
 */
public class MbbsDataBean {

	/**
	* 게시판ID
	*/	
	private String bid = null;

	/**
	* 게시물번호
	*/	
	private String c_index = null;

	/**
	* 제목
	*/	
	private String c_subject = null;

	/**
	* 등록일
	*/	
	private Date c_date = null;

	/**
	* 방문횟수
	*/	
	private int c_visit = 0;

	/**
	* 원글번호
	*/	
	private String c_ref = null;

	/**
	* 글의 depth
	*/	
	private int c_level = 0;

	/**
	* 시조원글번호
	*/	
	private String c_grp = null;

	/**
	* 시조원글내출력순서
	*/	
	private int c_step = 0;

	/**
	* 보임여부(T:보임, W:해우소,D:삭제)
	*/	
	private String c_status = null;

	/**
	* 사용자번호
	*/	
	private String usn = null;

	/**
	* 필명
	*/	
	private String wname = null;

	/**
	* 글암호
	*/	
	private String c_passwd = null;

	/**
	* 댓글수
	*/	
	private int c_ccnt = 0;

	/**
	* 등록 IP
	*/	
	private String c_ip = null;

	/**
	* HTML사용여부 H(html):T(text)
	*/	
	private String c_type = null;

	/**
	* 작성자 E-Mail 주소
	*/	
	private String c_email = null;

	/**
	* 자식글수(QnA확인용)
	*/	
	private int c_rcnt = 0;

	/**
	* 추천수
	*/	
	private int c_cnt_p = 0;

	/**
	* 받대수
	*/	
	private int c_cnt_m = 0;

	/**
	* 카테고리
	*/	
	private String c_category = null;

	/**
	* 공지여부(T:공지, 나머지:일반)
	*/	
	private String is_notice = "F";

	/**
	* 글내용
	*/	
	private String c_content = null;

	/**
	 * 대표이미지 파일명
	 */
	private String c_image = null;
	
    private String board_nm = null;	// 게시판 이름
    private String board_nm_eng = null;	// 게시판 이름_영문
    
    private String u_type = "G";
    
    private String userid = ""; // userid

	////////////////////////////////////////////////////////////////////////////////
	

	/**
	* Get bid : 게시판ID
	* DB TYPE : VARCHAR2
	*/
	public String getBid(){
		return this.bid;
	}
	/**
	* Set bid : 게시판ID
	* DB TYPE : VARCHAR2
	*/
	public void setBid(String bid){
		this.bid = bid;
	}

	/**
	* Get c_index : 게시물번호
	* DB TYPE : VARCHAR2
	*/
	public String getC_index(){
		return this.c_index;
	}
	/**
	* Set c_index : 게시물번호
	* DB TYPE : VARCHAR2
	*/
	public void setC_index(String c_index){
		this.c_index = c_index;
	}

	/**
	* Get c_subject : 제목
	* DB TYPE : VARCHAR2
	*/
	public String getC_subject(){
		return this.c_subject;
	}
	/**
	* Set c_subject : 제목
	* DB TYPE : VARCHAR2
	*/
	public void setC_subject(String c_subject){
		this.c_subject = c_subject;
	}

	/**
	* Get c_date : 등록일
	* DB TYPE : DATE
	*/
	public Date getC_date(){
		return this.c_date;
	}
	/**
	* Set c_date : 등록일
	* DB TYPE : DATE
	*/
	public void setC_date(Date c_date){
		this.c_date = c_date;
	}

	/**
	* Get c_visit : 방문횟수
	* DB TYPE : NUMBER
	*/
	public int getC_visit(){
		return this.c_visit;
	}
	/**
	* Set c_visit : 방문횟수
	* DB TYPE : NUMBER
	*/
	public void setC_visit(int c_visit){
		this.c_visit = c_visit;
	}

	/**
	* Get c_ref : 원글번호
	* DB TYPE : VARCHAR2
	*/
	public String getC_ref(){
		return this.c_ref;
	}
	/**
	* Set c_ref : 원글번호
	* DB TYPE : VARCHAR2
	*/
	public void setC_ref(String c_ref){
		this.c_ref = c_ref;
	}

	/**
	* Get c_level : 글의 depth
	* DB TYPE : NUMBER
	*/
	public int getC_level(){
		return this.c_level;
	}
	/**
	* Set c_level : 글의 depth
	* DB TYPE : NUMBER
	*/
	public void setC_level(int c_level){
		this.c_level = c_level;
	}

	/**
	* Get c_grp : 시조원글번호
	* DB TYPE : VARCHAR2
	*/
	public String getC_grp(){
		return this.c_grp;
	}
	/**
	* Set c_grp : 시조원글번호
	* DB TYPE : VARCHAR2
	*/
	public void setC_grp(String c_grp){
		this.c_grp = c_grp;
	}

	/**
	* Get c_step : 시조원글내출력순서
	* DB TYPE : NUMBER
	*/
	public int getC_step(){
		return this.c_step;
	}
	/**
	* Set c_step : 시조원글내출력순서
	* DB TYPE : NUMBER
	*/
	public void setC_step(int c_step){
		this.c_step = c_step;
	}

	/**
	* Get c_status : 보임여부(T:보임, W:해우소,D:삭제)
	* DB TYPE : CHAR
	*/
	public String getC_status(){
		return this.c_status;
	}
	/**
	* Set c_status : 보임여부(T:보임, W:해우소,D:삭제)
	* DB TYPE : CHAR
	*/
	public void setC_status(String c_status){
		this.c_status = c_status;
	}

	/**
	* Get usn : 사용자번호
	* DB TYPE : VARCHAR2
	*/
	public String getUsn(){
		return this.usn;
	}
	/**
	* Set usn : 사용자번호
	* DB TYPE : VARCHAR2
	*/
	public void setUsn(String usn){
		this.usn = usn;
	}

	/**
	* Get wname : 필명
	* DB TYPE : VARCHAR2
	*/
	public String getWname(){
		return this.wname;
	}
	/**
	* Set wname : 필명
	* DB TYPE : VARCHAR2
	*/
	public void setWname(String wname){
		this.wname = wname;
	}

	/**
	* Get c_passwd : 글암호
	* DB TYPE : VARCHAR2
	*/
	public String getC_passwd(){
		return this.c_passwd;
	}
	/**
	* Set c_passwd : 글암호
	* DB TYPE : VARCHAR2
	*/
	public void setC_passwd(String c_passwd){
		this.c_passwd = c_passwd;
	}

	/**
	* Get c_ccnt : 댓글수
	* DB TYPE : NUMBER
	*/
	public int getC_ccnt(){
		return this.c_ccnt;
	}
	/**
	* Set c_ccnt : 댓글수
	* DB TYPE : NUMBER
	*/
	public void setC_ccnt(int c_ccnt){
		this.c_ccnt = c_ccnt;
	}

	/**
	* Get c_ip : 등록 IP
	* DB TYPE : VARCHAR2
	*/
	public String getC_ip(){
		return this.c_ip;
	}
	/**
	* Set c_ip : 등록 IP
	* DB TYPE : VARCHAR2
	*/
	public void setC_ip(String c_ip){
		this.c_ip = c_ip;
	}

	/**
	* Get c_type : HTML사용여부 H(html):T(text)
	* DB TYPE : CHAR
	*/
	public String getC_type(){
		return this.c_type;
	}
	/**
	* Set c_type : HTML사용여부 H(html):T(text)
	* DB TYPE : CHAR
	*/
	public void setC_type(String c_type){
		this.c_type = c_type;
	}

	/**
	* Get c_email : 작성자 E-Mail 주소
	* DB TYPE : VARCHAR2
	*/
	public String getC_email(){
		return this.c_email;
	}
	/**
	* Set c_email : 작성자 E-Mail 주소
	* DB TYPE : VARCHAR2
	*/
	public void setC_email(String c_email){
		this.c_email = c_email;
	}

	/**
	* Get c_rcnt : 자식글수(QnA확인용)
	* DB TYPE : NUMBER
	*/
	public int getC_rcnt(){
		return this.c_rcnt;
	}
	/**
	* Set c_rcnt : 자식글수(QnA확인용)
	* DB TYPE : NUMBER
	*/
	public void setC_rcnt(int c_rcnt){
		this.c_rcnt = c_rcnt;
	}

	/**
	* Get c_cnt_p : 추천수
	* DB TYPE : NUMBER
	*/
	public int getC_cnt_p(){
		return this.c_cnt_p;
	}
	/**
	* Set c_cnt_p : 추천수
	* DB TYPE : NUMBER
	*/
	public void setC_cnt_p(int c_cnt_p){
		this.c_cnt_p = c_cnt_p;
	}

	/**
	* Get c_cnt_m : 받대수
	* DB TYPE : NUMBER
	*/
	public int getC_cnt_m(){
		return this.c_cnt_m;
	}
	/**
	* Set c_cnt_m : 받대수
	* DB TYPE : NUMBER
	*/
	public void setC_cnt_m(int c_cnt_m){
		this.c_cnt_m = c_cnt_m;
	}

	/**
	* Get c_category : 카테고리
	* DB TYPE : VARCHAR2
	*/
	public String getC_category(){
		return this.c_category;
	}
	/**
	* Set c_category : 카테고리
	* DB TYPE : VARCHAR2
	*/
	public void setC_category(String c_category){
		this.c_category = c_category;
	}

	/**
	* Get is_notice : 공지여부(T:공지, 나머지:일반)
	* DB TYPE : VARCHAR2
	*/
	public String getIs_notice(){
		return this.is_notice;
	}
	/**
	* Set is_notice : 공지여부(T:공지, 나머지:일반)
	* DB TYPE : VARCHAR2
	*/
	public void setIs_notice(String is_notice){
		this.is_notice = ("T".equals(is_notice))? "T":"F";
	}

	/**
	* Get c_content : 글내용
	* DB TYPE : LONG
	*/
	public String getC_content(){
		return this.c_content;
	}
	/**
	* Set c_content : 글내용
	* DB TYPE : LONG
	*/
	public void setC_content(String c_content){
		this.c_content = c_content;
	}


	/**
	 * 게시판 이름 
	 * @return
	 */
    public String getBoard_nm() {
        return board_nm;
    }
	/**
	 * 게시판 이름 
	 * @return
	 */
    public void setBoard_nm(String board_nm) {
        this.board_nm = board_nm;
    }
	/**
	 * 게시판 이름 (영문)
	 * @return
	 */
    public String getBoard_nm_eng() {
        return board_nm_eng;
    }
	/**
	 * 게시판 이름 (영문)
	 * @return
	 */
    public void setBoard_nm_eng(String board_nm_eng) {
        this.board_nm_eng = board_nm_eng;
    }
    
	/**
	 * 대표이미지 파일명
	 */
    public String getC_image() {
        return c_image;
    }
	/**
	 * 대표이미지 파일명
	 */
    public void setC_image(String c_image) {
        this.c_image = c_image;
    }
    
    public String getU_type() {
        return u_type;
    }
    public void setU_type(String u_type) {
        this.u_type = u_type;
    }
	/**
	 * @return Returns the userid.
	 */
	public String getUserid() {
		return userid;
	}
	/**
	 * @param userid The userid to set.
	 */
	public void setUserid(String userid) {
		this.userid = userid;
	}
    
}