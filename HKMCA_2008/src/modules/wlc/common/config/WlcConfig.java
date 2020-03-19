/*
 * Created on 2004. 9. 13.
 *
 */
package modules.wlc.common.config;

import maf.MafUtil;
import maf.base.BaseObject;

/**
 * @author bluevlad
 * Config 설정 파일을 읽어 가지고 있는다.
 */
public class WlcConfig extends BaseObject {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public static final String DES_HASH = "wisevoice1234";

	public static final String BBS_LIST_URL = "/board/list.do";

    /**
     * 강의실 게시판 저장경로 /pds/bbs/강의코드/파일명
     */
    public static final String DEFAULT_BBS_ATT_FILE_HTTP="/pds/bbs";
	/**
	 * 과제물 저장경로 /pds/report/종류/과목코드/파일명
	 */
	public static final String DEFAULT_RTP_ATT_FILE_HTTP="/pds/report";
    /**
     * 팀프로젝트 저장경로 /pds/project/종류/과목코드/파일명
     */
    public static final String DEFAULT_PROJECT_ATT_FILE_HTTP="/pds/project";
    /**
     * 팀프로젝트 저장경로 /pds/project/종류/과목코드/파일명
     */
    public static final String DEFAULT_FORUM_ATT_FILE_HTTP="/pds/forum";
    /**
     * 성적관련 저장경로 /pds/score/종류/과목코드/파일명(성적 피드백 저장)
     */
    public static final String DEFAULT_SCORE_ATT_FILE_HTTP="/pds/score";
	/**
	 * webFolder 파일이 저장되 위치 /pds/webfolder
	 */
	public static final String DEFAULT_WEBFOLDER_FILE_PATH="/pds/webfolder";
	public static final String DEFAULT_FILEMAN_FILE_PATH="/pds/fileman";

    /**
     * 문제은행 파일 저장 위치
     */
    public static final String DEFAULT_EXAM_FILE_PATH = "/pds/exam";

    public static final int LECTURE_SAVE_INTERVAL_TIME = Integer.parseInt(WlcConfigSupport.getProperty("lecture_save_interval_time", "1"));

    /**
	 * 강의관련 파일 저장 위치
	 */
	public static final String DEFAULT_COURSE_FILE_PATH = "/pds/course";
	/**
	 * 강의계획서 파일 저장되는 위치 /pds/course
	 */
	public static final String DEFAULT_SYLLABUS_FILE_PATH = DEFAULT_COURSE_FILE_PATH + "/syllabus";

    /**
     * 메일 템플릿 저장 경로 /pds/course
     */
    public static final String DEFAULT_MAIL_TEMPLATE_FILE_PATH = "/pds/mail_template";

    /**
     * 웹 도메인
     */
    public static final String WEB_DOMAIN = WlcConfigSupport.getProperty("web_domain", "http://test.wisevoice.kr");
    /**
     * 컨텐츠 서버(기본)
     */
    public static final String CONTENTS_SERVER = WlcConfigSupport.getProperty("contents_server", "http://test.wisevoice.kr");
    /**
	 * 컨텐츠 파일 절대 경로
	 */
    public static final String DEFAULT_CONTENTS_FILE_PATH="/contents";
	public static final String DEFAULT_CONTENTS_FILE_PATH_TMP="/contents/tmp";

	public static final String POPUP_FILE_HTTP = "/pds/popup";
	public static final String POPUP_FILE_PATH = "/pds/popup";

	/**
	 * Debug level 1000: DEBUG <= log4j의 level과 동일 1000보다 크면 loggin 안함
	 */
	public static final boolean DEBUG = WlcConfigSupport.getBooleanProperty("DEBUG", "FALSE");;

	public static final int DEFAULT_MAX_POST_SIZE = MafUtil.parseInt(WlcConfigSupport.getProperty("MAX_POST_SIZE", "100"));

	public static final String EXCEPTION_PRINT = WlcConfigSupport.getProperty("EXCEPTION_PRINT", "debug");

	/**
	 * 메일 템플릿 파라메터, 메일발송User정보
	 *
	 */
	public static final String MAIL_MEMBER_NAME = "%$MEMBERNAME%$"; // 회원 이름
	public static final String MAIL_MEMBER_ID   = "%$MEMBERID%$";   // 회원ID
	public static final String MAIL_LECNAME     = "$%LECNAME%$";    // 강의명
	public static final String MAIL_LECCODE     = "$%LECCODE%$";    // 강의코드
	public static final String MAIL_DIVPARAM    = "##MAIL##";        // 구분

	// 사진의 width x height
	public static final int PHOTO_HEIGHT = 320;
	public static final int PHOTO_WIDTH = 240;

	//사람의 메일주소
	public static final String WEBMASTER_EMAIL=WlcConfigSupport.getProperty("WEBMASTER_EMAIL", "");



}