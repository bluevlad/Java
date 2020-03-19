/*
*게시판 관련 상수 모음 
*
*/
package modules.community.mboard;

public class BoardConfig {
    
    /**
     * 게시물 등록(add)
     */   
    public static  final String CMD_ADD = "add";
    /**
     * 답글 등록(addreply)
     */      
    public static final String CMD_ADD_REPLY = "addreply";
    /**
     * 게시물 삭제(delete)
     */    
    public static final String CMD_DEL = "delete";
    /**
     * 게시물 Update(update)
     */
    public static final String CMD_UPDATE = "update";
    /**
     * 코멘트 등록(cmt_add)
     */
    public static final String CMD_CMT_ADD = "cmt_add";
    /**
     * 코멘트 삭제(cmt_del)
     */
    public static final String CMD_CMT_DEL = "cmt_del";
    
    
    /**
     * 시스템 관리자
     */
    public static final String  GRP_ADMIN = "A";
    /**
     * 게스트 (Login 안함) 
     */
    public static final String  GRP_GUEST = "Z";
    
    /**
     * 버튼 권한 전송용 Parameter 이름
     */
    public static final String PARAM_BTN_AUTH = "BTN_AUTH";
    /**
     * 쓰기권한
     */
    public static final String BTN_AUTH_WRITE = "W";
    /**
     * 삭제권한
     */
    public static final String BTN_AUTH_DEL = "D";
    /**
     * 수정권한
     */
    public static final String BTN_AUTH_EDIT = "E";
    /**
     * 답글권한
     */
    public static  final String BTN_AUTH_REPLY = "R";
    
    /**
     * NOTICE권한
     */
    public static  final String BTN_AUTH_NOTICE = "N";
}
