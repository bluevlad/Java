/*
 * Created on 2004. 9. 22.
 * 
 * Preferences - Java - Code Style - Code Templates
 */
package modules.community.mboard.beans;

import java.util.Date;

import modules.community.mboard.MBoardManager;



/**
 * @author bluevlad
 * 
 */
public class MbbsMetaBean {

    /**
     * BBS ID
     */
    private String bid = null;

    private String subject = null;

    /**
     * 게시물수(정상글)
     */
    private int cnt_t = 0;

    /**
     * 게시물수(해우소)
     */
    private int cnt_w = 0;

    /**
     * 관리그룹
     */
    private String grp = null;

    /**
     * 게시물수(삭제글)
     */
    private int cnt_d = 0;

    /**
     * 한 Page에나오는 Rows수
     */
    private int nrows = 15;

    /**
     * 현게시판 최종 Index
     */
    private int last_index = 0;

//    /**
//     * 사용자 쓰기(쓸수있는 사용자 그룹(APN)으로 입력
//     */
//    private String fl_write = "";
    
//    /**
//     * 사용자 읽기제한 (목록이나 내용을 읽을수 있는 권한/사용자 그룹(APN)으로 입력
//     */
//    private String fl_read = "";
    
//    /**
//     * 사용자 목록보기 제한 (목록이나 내용을 읽을수 있는 권한/사용자 그룹(APN)으로 입력
//     */
//    private String fl_list = "";
    
//    /**
//     * 공지 쓰기 권한 
//     */
//    private String fl_notice = "";
    
//    /**
//     * 사용자 인증(T:사용, F:아무나쓰기)
//     */
//    private String fl_auth = "T";

    /**
     * 사용자 리플
     */
    private String fl_reply = "T";

    /**
     * 사용자 코멘트
     */
    private String fl_comment = "F";

    /**
     * 날짜 지정(S, U)
     */
    private String fl_modify_date = null;

    /**
     * 파일 첨부 갯수
     */
    private int number_attach = 0;

    /**
     * 최대 파일 용량(MB)
     */
    private int max_attach_size = 0;

    /**
     * 게시판 타입
     */
    private String fl_board_type = "normal";

    /**
     * 총첨부파일용량
     */
    private int total_attach_size = 0;

    /**
     * 최종게시물 일자
     */
    private Date last_dt = null;

    /**
     * 게시판 사용여부
     */
    private String is_use = "T";

    /**
     * 카테고리(a,b,c; 처럼 ;을 구분자 사용)
     */
    private String category = null;

    /**
     * 강좌코드, 커뮤니티 코드(강좌코드:커뮤니티ID)
     */
    private String pgid = null;

    /**
     * 관리자 USN
     */
    private String admin_usn = null;


    /**
     * HTML 사용여부
     */
    private String fl_html = "T";

    /**
     * 사용자 Category 등록 가능
     */
    private String fl_category = "F";

    /**
     * 해우소 사용여부
     */
    private String fl_waste = "F";

    /**
     * 게시판 최대 첨부량(MB)
     */
    private int max_attach_limit = 0;

    /**
     * 게시판 상단영역
     */
    private String header = null;
    
    /**
     * 과목코드 
     */
    private String leccode = null;
    private String club_id = null;

    //	 게시판 보여줄 Layout 페이지
    private String layout = null;
    // 게시판 사이트
    private String site = "";

    private boolean IsOk = false;
    
    private int thumbwidth = 100, thumbheight = 75;
    
    
    
    private int grant_list = 9; //목록 보기 권한
    private int grant_view  = 9; //내용 보기 권한 
    private int grant_write     = 9; //글쓰기 권한
    private int grant_comment     = 9;//간단한 답글 쓰기
    private int grant_reply   = 9;//답변쓰기 권한
    private int grant_delete     = 1;//삭제 권한
    private int grant_html       = 9;//HTML 사용 권한
    private int grant_notice      = 2;//공지사항 작성 권한
    private int grant_secret      = 2;//비밀글 보기 권한
    
    ////////////////////////////////////////////////////////////////////////////////

    // 학과, 강좌명 추가
    private String cor_nm = null;
    private String cor_nm_eng = null;

    /**
	* 사용자 선택게시판(T:선택가능, F:불가)
	*/	
	private String ext_usersel = "F";

	/**
	* 목록 상단에 나올 파일
	*/	
	private String file_header = null;

	/**
	* 목록 하단에 나올 파일
	*/	
	private String file_footer = null;
	
    public String getBidPath() {
    	return MBoardManager.getBid2BidPath(this.bid);
    }
    
    /**
     * Get bid : BBS ID DB TYPE : VARCHAR2
     */
    public String getBid() {
        return this.bid;
    }

    /**
     * Set bid : BBS ID DB TYPE : VARCHAR2
     */
    public void setBid(String bid) {
        this.bid = bid;
    }

    /**
     * Get subject : DB TYPE : VARCHAR2
     */
    public String getSubject() {
        return this.subject;
    }

    /**
     * Set subject : DB TYPE : VARCHAR2
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * Get cnt_t : 게시물수(정상글) DB TYPE : NUMBER
     */
    public int getCnt_t() {
        return this.cnt_t;
    }

    /**
     * Set cnt_t : 게시물수(정상글) DB TYPE : NUMBER
     */
    public void setCnt_t(int cnt_t) {
        this.cnt_t = cnt_t;
    }

    /**
     * Get cnt_w : 게시물수(해우소) DB TYPE : NUMBER
     */
    public int getCnt_w() {
        return this.cnt_w;
    }

    /**
     * Set cnt_w : 게시물수(해우소) DB TYPE : NUMBER
     */
    public void setCnt_w(int cnt_w) {
        this.cnt_w = cnt_w;
    }

    /**
     * Get grp : 관리그룹 DB TYPE : VARCHAR2
     */
    public String getGrp() {
        return this.grp;
    }

    /**
     * Set grp : 관리그룹 DB TYPE : VARCHAR2
     */
    public void setGrp(String grp) {
        this.grp = grp;
    }

    /**
     * Get cnt_d : 게시물수(삭제글) DB TYPE : NUMBER
     */
    public int getCnt_d() {
        return this.cnt_d;
    }

    /**
     * Set cnt_d : 게시물수(삭제글) DB TYPE : NUMBER
     */
    public void setCnt_d(int cnt_d) {
        this.cnt_d = cnt_d;
    }

    /**
     * Get nrows : 한 Page에나오는 Rows수 DB TYPE : NUMBER
     */
    public int getNrows() {
        return this.nrows;
    }

    /**
     * Set nrows : 한 Page에나오는 Rows수 DB TYPE : NUMBER
     */
    public void setNrows(int nrows) {
        this.nrows = nrows;
    }

    /**
     * Get last_index : 현게시판 최종 Index DB TYPE : NUMBER
     */
    public int getLast_index() {
        return this.last_index;
    }

    /**
     * Set last_index : 현게시판 최종 Index DB TYPE : NUMBER
     */
    public void setLast_index(int last_index) {
        this.last_index = last_index;
    }

//    /**
//     * Get fl_write : 사용자 쓰기(쓸수있는 사용자 그룹(APN)으로 입력 DB TYPE : VARCHAR2
//     */
//    public String getFl_write() {
//        return this.fl_write;
//    }
//
//    /**
//     * Set fl_write : 사용자 쓰기(쓸수있는 사용자 그룹(APN)으로 입력 DB TYPE : VARCHAR2
//     */
//    public void setFl_write(String fl_write) {
//        this.fl_write = fl_write;
//    }
//
//    /**
//     * Get fl_auth : 사용자 인증(T:사용, F:아무나쓰기) DB TYPE : CHAR
//     */
//    public String getFl_auth() {
//        return this.fl_auth;
//    }
//
//    /**
//     * Set fl_auth : 사용자 인증(T:사용, F:아무나쓰기) DB TYPE : CHAR
//     */
//    public void setFl_auth(String fl_auth) {
//        this.fl_auth = fl_auth;
//    }

    /**
     * Get fl_reply : 사용자 리플 DB TYPE : CHAR
     */
    public String getFl_reply() {
        return this.fl_reply;
    }

    /**
     * Set fl_reply : 사용자 리플 DB TYPE : CHAR
     */
    public void setFl_reply(String fl_reply) {
        this.fl_reply = fl_reply;
    }

    /**
     * Get fl_comment : 사용자 코멘트 DB TYPE : CHAR
     */
    public String getFl_comment() {
        return this.fl_comment;
    }

    /**
     * Set fl_comment : 사용자 코멘트 DB TYPE : CHAR
     */
    public void setFl_comment(String fl_comment) {
        this.fl_comment = fl_comment;
    }

    /**
     * Get fl_modify_date : 날짜 지정(S, U) DB TYPE : CHAR
     */
    public String getFl_modify_date() {
        return this.fl_modify_date;
    }

    /**
     * Set fl_modify_date : 날짜 지정(S, U) DB TYPE : CHAR
     */
    public void setFl_modify_date(String fl_modify_date) {
        this.fl_modify_date = fl_modify_date;
    }

    /**
     * Get number_attach : 파일 첨부 갯수 DB TYPE : NUMBER
     */
    public int getNumber_attach() {
        return this.number_attach;
    }

    /**
     * Set number_attach : 파일 첨부 갯수 DB TYPE : NUMBER
     */
    public void setNumber_attach(int number_attach) {
        this.number_attach = number_attach;
    }

    /**
     * Get max_attach_size : 최대 파일 용량(MB) DB TYPE : NUMBER
     */
    public int getMax_attach_size() {
        return this.max_attach_size;
    }

    /**
     * Set max_attach_size : 최대 파일 용량(MB) DB TYPE : NUMBER
     */
    public void setMax_attach_size(int max_attach_size) {
        this.max_attach_size = max_attach_size;
    }

    /**
     * Get fl_board_type : 게시판 타입 DB TYPE : VARCHAR2
     */
    public String getFl_board_type() {
        return this.fl_board_type;
    }

    /**
     * Set fl_board_type : 게시판 타입 DB TYPE : VARCHAR2
     */
    public void setFl_board_type(String fl_board_type) {
        this.fl_board_type = fl_board_type;
    }

    /**
     * Get total_attach_size : 총첨부파일용량 DB TYPE : NUMBER
     */
    public int getTotal_attach_size() {
        return this.total_attach_size;
    }

    /**
     * Set total_attach_size : 총첨부파일용량 DB TYPE : NUMBER
     */
    public void setTotal_attach_size(int total_attach_size) {
        this.total_attach_size = total_attach_size;
    }

    /**
     * Get last_dt : 최종게시물 일자 DB TYPE : DATE
     */
    public Date getLast_dt() {
        return this.last_dt;
    }

    /**
     * Set last_dt : 최종게시물 일자 DB TYPE : DATE
     */
    public void setLast_dt(Date last_dt) {
        this.last_dt = last_dt;
    }

    /**
     * Get is_use : 게시판 사용여부 DB TYPE : CHAR
     */
    public String getIs_use() {
        return this.is_use;
    }

    /**
     * Set is_use : 게시판 사용여부 DB TYPE : CHAR
     */
    public void setIs_use(String is_use) {
        this.is_use = is_use;
    }

    /**
     * Get category : 카테고리(a,b,c; 처럼 ;을 구분자 사용) DB TYPE : VARCHAR2
     */
    public String getCategory() {
        return this.category;
    }

    /**
     * Set category : 카테고리(a,b,c; 처럼 ;을 구분자 사용) DB TYPE : VARCHAR2
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Get pgid : 강좌코드, 커뮤니티 코드(강좌코드:커뮤니티ID) DB TYPE : VARCHAR2
     */
    public String getPgid() {
        return this.pgid;
    }

    /**
     * Set pgid : 강좌코드, 커뮤니티 코드(강좌코드:커뮤니티ID) DB TYPE : VARCHAR2
     */
    public void setPgid(String pgid) {
        this.pgid = pgid;
    }

    /**
     * Get admin_usn : 관리자 USN DB TYPE : VARCHAR2
     */
    public String getAdmin_usn() {
        return this.admin_usn;
    }

    /**
     * Set admin_usn : 관리자 USN DB TYPE : VARCHAR2
     */
    public void setAdmin_usn(String admin_usn) {
        this.admin_usn = admin_usn;
    }


    /**
     * Get fl_html : HTML 사용여부 DB TYPE : VARCHAR2
     */
    public String getFl_html() {
        return this.fl_html;
    }

    /**
     * Set fl_html : HTML 사용여부 DB TYPE : VARCHAR2
     */
    public void setFl_html(String fl_html) {
        this.fl_html = fl_html;
    }

    /**
     * Get fl_category : 사용자 Category 등록 가능 DB TYPE : VARCHAR2
     */
    public String getFl_category() {
        return this.fl_category;
    }

    /**
     * Set fl_category : 사용자 Category 등록 가능 DB TYPE : VARCHAR2
     */
    public void setFl_category(String fl_category) {
        this.fl_category = fl_category;
    }

    /**
     * Get fl_waste : 해우소 사용여부 DB TYPE : VARCHAR2
     */
    public String getFl_waste() {
        return this.fl_waste;
    }

    /**
     * Set fl_waste : 해우소 사용여부 DB TYPE : VARCHAR2
     */
    public void setFl_waste(String fl_waste) {
        this.fl_waste = fl_waste;
    }

    /**
     * Get max_attach_limit : 게시판 최대 첨부량(MB) DB TYPE : NUMBER
     */
    public int getMax_attach_limit() {
        return this.max_attach_limit;
    }

    /**
     * Set max_attach_limit : 게시판 최대 첨부량(MB) DB TYPE : NUMBER
     */
    public void setMax_attach_limit(int max_attach_limit) {
        this.max_attach_limit = max_attach_limit;
    }

    /**
     * Get header : 게시판 상단영역 DB TYPE : VARCHAR2
     */
    public String getHeader() {
        return this.header;
    }

    /**
     * Set header : 게시판 상단영역 DB TYPE : VARCHAR2
     */
    public void setHeader(String header) {
        this.header = header;
    }

    public String getLayout() {
        return layout;
    }

    public void setLayout(String layout) {
        this.layout = layout;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

//    public String getC_path() {
//        return c_path;
//    }
//
//    public void setC_path(String c_path) {
//        this.c_path = c_path;
//    }
    public boolean isOk() {
        return IsOk;
    }
    public void setOk(boolean isOk) {
        IsOk = isOk;
    }
    public int getThumbheight() {
        return thumbheight;
    }
    public void setThumbheight(int thumbheight) {
        this.thumbheight = thumbheight;
    }
    public int getThumbwidth() {
        return thumbwidth;
    }
    public void setThumbwidth(int thumbwidth) {
        this.thumbwidth = thumbwidth;
    }
    
    
    /**
     * @return club_id을 리턴합니다.
     */
    public String getClub_id() {
        return club_id;
    }
    /**
     * @param club_id 설정하려는 club_id.
     */
    public void setClub_id(String club_id) {
        this.club_id = club_id;
    }
    /**
     * @return cor_cd을 리턴합니다.
     */
    public String getLeccode() {
        return leccode;
    }
    /**
     * @param cor_cd 설정하려는 cor_cd.
     */
    public void setLeccode(String cor_cd) {
        this.leccode = cor_cd;
    }
	/**
	 * @return Returns the cor_nm.
	 */
	public String getCor_nm() {
		return cor_nm;
	}
	/**
	 * @param cor_nm The cor_nm to set.
	 */
	public void setCor_nm(String cor_nm) {
		this.cor_nm = cor_nm;
	}
	/**
	 * @return Returns the cor_nm_eng.
	 */
	public String getCor_nm_eng() {
		return cor_nm_eng;
	}
	/**
	 * @param cor_nm_eng The cor_nm_eng to set.
	 */
	public void setCor_nm_eng(String cor_nm_eng) {
		this.cor_nm_eng = cor_nm_eng;
	}
	/**
	* Get ext_usersel : 사용자 선택게시판(T:선택가능, F:불가)
	* DB TYPE : VARCHAR2
	*/
	public String getExt_usersel(){
		return this.ext_usersel;
	}
	/**
	* Set ext_usersel : 사용자 선택게시판(T:선택가능, F:불가)
	* DB TYPE : VARCHAR2
	*/
	public void setExt_usersel(String ext_usersel){
		this.ext_usersel = ext_usersel;
	}


	/**
	* Get file_header : 목록 상단에 나올 파일
	* DB TYPE : VARCHAR2
	*/
	public String getFile_header(){
		return this.file_header;
	}
	/**
	* Set file_header : 목록 상단에 나올 파일
	* DB TYPE : VARCHAR2
	*/
	public void setFile_header(String file_header){
		this.file_header = file_header;
	}

	/**
	* Get file_footer : 목록 하단에 나올 파일
	* DB TYPE : VARCHAR2
	*/
	public String getFile_footer(){
		return this.file_footer;
	}
	/**
	* Set file_footer : 목록 하단에 나올 파일
	* DB TYPE : VARCHAR2
	*/
	public void setFile_footer(String file_footer){
		this.file_footer = file_footer;
	}
	
//	/**
//	* Get fl_read : 사용자 읽기 권한
//	* DB TYPE : VARCHAR2
//	*/
//	public String getFl_read(){
//		return this.fl_read;
//	}
//	/**
//	* Set fl_read : 사용자 읽기 권한
//	* DB TYPE : VARCHAR2
//	*/
//	public void setFl_read(String fl_read){
//		this.fl_read = MafUtil.nvl(fl_read,"");
//	}
//
//	/**
//	 * @return Returns the fl_list.
//	 */
//	public String getFl_list() {
//		return fl_list;
//	}
//
//	/**
//	 * @param fl_list The fl_list to set.
//	 */
//	public void setFl_list(String fl_list) {
//		this.fl_list = MafUtil.nvl(fl_list,"");
//	}
//
//	/**
//	 * @return Returns the fl_notice.
//	 */
//	public String getFl_notice() {
//		return fl_notice;
//	}
//
//	/**
//	 * @param fl_notice The fl_notice to set.
//	 */
//	public void setFl_notice(String fl_notice) {
//		this.fl_notice = MafUtil.nvl(fl_notice,"");
//	}

	/**
	 * @return the grant_comment
	 */
	public int getGrant_comment() {
		return grant_comment;
	}

	/**
	 * @param grant_comment the grant_comment to set
	 */
	public void setGrant_comment(int grant_comment) {
		this.grant_comment = grant_comment;
	}

	/**
	 * @return the grant_delete
	 */
	public int getGrant_delete() {
		return grant_delete;
	}

	/**
	 * @param grant_delete the grant_delete to set
	 */
	public void setGrant_delete(int grant_delete) {
		this.grant_delete = grant_delete;
	}

	/**
	 * @return the grant_html
	 */
	public int getGrant_html() {
		return grant_html;
	}

	/**
	 * @param grant_html the grant_html to set
	 */
	public void setGrant_html(int grant_html) {
		this.grant_html = grant_html;
	}

	/**
	 * @return the grant_LIST
	 */
	public int getGrant_list() {
		return grant_list;
	}

	/**
	 * @param grant_LIST the grant_LIST to set
	 */
	public void setGrant_list(int grant_LIST) {
		this.grant_list = grant_LIST;
	}

	/**
	 * @return the grant_notice
	 */
	public int getGrant_notice() {
		return grant_notice;
	}

	/**
	 * @param grant_notice the grant_notice to set
	 */
	public void setGrant_notice(int grant_notice) {
		this.grant_notice = grant_notice;
	}

	/**
	 * @return the grant_reply
	 */
	public int getGrant_reply() {
		return grant_reply;
	}

	/**
	 * @param grant_reply the grant_reply to set
	 */
	public void setGrant_reply(int grant_reply) {
		this.grant_reply = grant_reply;
	}

	/**
	 * @return the grant_secret
	 */
	public int getGrant_secret() {
		return grant_secret;
	}

	/**
	 * @param grant_secret the grant_secret to set
	 */
	public void setGrant_secret(int grant_secret) {
		this.grant_secret = grant_secret;
	}
	
	
	/**
	 * @return the grant_view
	 */
	public int getGrant_view() {
		return grant_view;
	}

	/**
	 * @param grant_view the grant_view to set
	 */
	public void setGrant_view(int grant_view) {
		this.grant_view = grant_view;
	}
	
	/**
	 * @return the grant_write
	 */
	public int getGrant_write() {
		return grant_write;
	}

	/**
	 * @param grant_write the grant_write to set
	 */
	public void setGrant_write(int grant_write) {
		this.grant_write = grant_write;
	}
	
	

}