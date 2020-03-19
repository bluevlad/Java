/*
 * Created on 2004. 9. 13.
 *
 */
package miraenet;

import maf.MafUtil;
import maf.base.BaseObject;
import maf.mdb.drivers.MdbDriver;
import maf.web.servlet.LocaleResolver;
import maf.web.servlet.SiteResolver;
import maf.web.servlet.support.HeaderSiteResolver;
import maf.web.servlet.support.SessionLocaleResolver;
import miraenet.support.ConfigSupport;


/**
 * @author goindole
 * Config 설정 파일을 읽어 가지고 있는다.
 */
public class MiConfig extends BaseObject {


	/**
	 *
	 */
	private static final long serialVersionUID = 1L;


	public static final String DES_HASH = "kais234";


	public static final String SERVLET_PATH = ConfigSupport.getProperty("SERVLET_PATH", "/ehrd");

	// menu 및 jsp의 template 관리용 default 사이트
	public static final String DEFAULT_SITE = ConfigSupport.getProperty("DEFAULT_SITE","www");

	// 실제 resource 파일들의 위치
	public static final String MVC_SRC_PATH = ConfigSupport.getProperty("MVC_SRC_PATH","/web_src");

	// 가상 경로
	public static final String MVC_SERVLET_PATH = ConfigSupport.getProperty("MVC_SERVLET_PATH","/web");




	// form token key
	public static final String TRANSACTION_TOKEN_KEY = "FORMTOKEN";


	public static final String DEFAULT_CHARACTER_ENCODING = ConfigSupport.getProperty("DEFAULT_CHARACTER_ENCODING","euc-kr");

	public static final int DEFAULT_DBMS = MdbDriver.DBMS_OCI8;


	/**
	 * MAF에서 GC를 수행 할것인지 여부
	 */
	public static final boolean USE_MAF_GC = ConfigSupport.getBooleanProperty("USE_MAF_GC", "TRUE");;


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
     * 이벤트 썸네일 이미지
     */
    public static final String DEFAULT_EVENT_FILE_PATH = "/pds/event";

    /**
     * 도서몰 파일 저장 위치
     */
    public static final String DEFAULT_MALL_FILE_PATH = "/pds/mall";

    /**
     * 문제은행 파일 저장 위치
     */
    public static final String DEFAULT_EXAM_FILE_PATH = "/pds/exam";

    public static final int LECTURE_SAVE_INTERVAL_TIME = Integer.parseInt(ConfigSupport.getProperty("LECTURE_SAVE_INTERVAL_TIME", "1"));

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
    public static final String WEB_DOMAIN = ConfigSupport.getProperty("WEB_DOMAIN", "http://www.esunhrd.co.kr");
    /**
     * 컨텐츠 서버1(기본)
     */
    public static final String CONTENTS_SERVER = ConfigSupport.getProperty("CONTENTS_SERVER1", "http://devcontents.esunhrd.co.kr");
    /**
     * 컨텐츠 서버2(썬 컨텐츠-JSP 컨텐츠 서버)
     */
    public static final String CONTENTS_SERVER2 = ConfigSupport.getProperty("CONTENTS_SERVER2", "http://devcontents.esunhrd.co.kr");
    /**
     * 컨텐츠 서버3(썬 컨텐츠 서버-구 WLC 컨텐츠 서비스 서버)
     */
    public static final String CONTENTS_SERVER3 = ConfigSupport.getProperty("CONTENTS_SERVER3", "http://devcontents.esunhrd.co.kr");
    /**
     * 컨텐츠 서버4
     */
    public static final String CONTENTS_SERVER4 = ConfigSupport.getProperty("CONTENTS_SERVER4", "http://devcontents.esunhrd.co.kr");
    /**
     * 컨텐츠 서버5
     */
    public static final String CONTENTS_SERVER5 = ConfigSupport.getProperty("CONTENTS_SERVER5", "http://devcontents.esunhrd.co.kr");
    /**
	 * 컨텐츠 파일 절대 경로
	 */
    public static final String DEFAULT_CONTENTS_FILE_PATH="/data/contents";
	public static final String DEFAULT_CONTENTS_FILE_PATH_TMP="D:/App/sks/sun_esunhrd/WebContent/data/contents/tmp";

	public static final String POPUP_FILE_HTTP = "/pds/popup";
	public static final String POPUP_FILE_PATH = "/pds/popup";

	/**
	 * Debug level 1000: DEBUG <= log4j의 level과 동일 1000보다 크면 loggin 안함
	 */

	public static final boolean DEBUG = ConfigSupport.getBooleanProperty("DEBUG", "FALSE");;

	public static final int DEFAULT_MAX_POST_SIZE = MafUtil.parseInt(ConfigSupport.getProperty("MAX_POST_SIZE", "100"));

	public static final String EXCEPTION_PRINT = ConfigSupport.getProperty("EXCEPTION_PRINT", "debug");

	//	Default for Database pool
	public static final String DEFAULT_DB_POOL_NAME = ConfigSupport.getProperty("DB.POOL.NAME", "maf");


	public static final int DBSESSION_TIMEOUT = Integer.parseInt(ConfigSupport.getProperty("DBSESSION_TIMEOUT", "60"));

	public static final String SMTP_SERVER = ConfigSupport.getProperty("SMTP_SERVER", "127.0.0.1");
	public static final String SMTP_SERVER_USER = ConfigSupport.getProperty("SMTP_SERVER_USER", "develop");
	public static final String SMTP_SERVER_PASSWD = ConfigSupport.getProperty("SMTP_SERVER_PASSWD", "qwer12");

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
	public static final String WEBMASTER_EMAIL=ConfigSupport.getProperty("WEBMASTER_EMAIL", "");

	//마일리지 이미지 경로,width,height
	public static final String MIL_IMG_PATH = "/pds/mileage/img" ;
	public static final int MIL_IMG_W = 100 ;
	public static final int MIL_IMG_H = 130 ;

	//마일리지 아이콘 경로,width,height
	public static final String MIL_ICON_PATH = "/pds/mileage/icon" ;
	public static final int MIL_ICON_W = 30;
	public static final int MIL_ICON_H = 40;

	// DACOM eCredit 정보
	public static final String DACOM_ECREDIT_MID = ConfigSupport.getProperty("DACOM_ECREDIT_MID", "");
	public static final String DACOM_ECREDIT_MERT_KEY = ConfigSupport.getProperty("DACOM_ECREDIT_MERT_KEY", "");




//	/**
//	 * 세션 정보를 DB에 넣을 것인지 사용 여부"Y" 면 사용
//	 */
//	public static final String SESSION_DB_POOL_YN = "N";

	public static final int SESSION_TIMEOUT = Integer.parseInt(ConfigSupport.getProperty("SESSION_TIMEOUT", "30"));

	/**
	 * Session Pool 사용 여부 "Y" 면 사용
	 */
	public static final boolean SESSION_POOL_YN = ConfigSupport.getBooleanProperty("SESSION_POOL_YN","Y");

	/**
	 * 동일 ID로 두군데서 접금 못하는 기능 사용 여부"Y" 면 사용
	 */
	public static final boolean SESSION_UNIQID_YN = ConfigSupport.getBooleanProperty("SESSION_UNIQID_YN","N");

	public static final String DEFAULT_DB_POOL_TYPE = ConfigSupport.getProperty("DB.POOL.TYPE", "DBCP");

	public static final SiteResolver MAF_SITE_RESOLVER = getSiteResolver();


    public static SiteResolver getSiteResolver() {
    	String siteresolver = ConfigSupport.getProperty("RESOLVER_SITE", "maf.web.servlet.support.HeaderSiteResolver");
    	Class cls = null;
    	SiteResolver obj = null;
    	try {
    		 cls =  Class.forName(siteresolver);
    	} catch (Throwable e) {
    		System.out.println(siteresolver + " class not found !!! use HeaderSiteResolver");
    	}
    	try {
    		obj = (SiteResolver) cls.newInstance();
    	} catch (Throwable e) {
    		System.out.println(e.getMessage());
    	}
    	if (obj == null) {
    		System.out.println(siteresolver + " class not found !!! use HeaderSiteResolver");
    		obj = new HeaderSiteResolver();
    	}
    	return obj;
    }
    private static LocaleResolver getLocaleResolver() {
    	return new SessionLocaleResolver();
    }

	public static final LocaleResolver MAF_LOCALE_RESOLVER = getLocaleResolver();
}
