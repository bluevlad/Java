/*
 * Created on 2004. 9. 13.
 *
 */
package miraenet;

import maf.MafUtil;
import maf.base.BaseObject;
import maf.mdb.drivers.MdbDriver;
import maf.util.PropertySupport;
import maf.web.servlet.LocaleResolver;
import maf.web.servlet.SiteResolver;
import maf.web.servlet.support.HeaderSiteResolver;
import maf.web.servlet.support.SessionLocaleResolver;
/**
 * @author ubq
 * Config 설정 파일을 읽어 가지고 있는다.
 */
public class AppConfig extends BaseObject {

	public static final String DES_HASH = "maf12345";

    // * 웹 도메인
	public static final String WEB_DOMAIN = PropertySupport.getProperty("WEB_DOMAIN", "http://www.maf.net");
    // * 컨텐츠 서버
	public static final String CONTENTS_SERVER = PropertySupport.getProperty("CONTENTS_SERVER", "http://www.maf.net");

    // 강의관련 파일 저장 위치
	public static final String DEFAULT_COURSE_FILE_PATH = "/pds/course";
	// 문제은행 파일 저장 위치
    public static final String DEFAULT_EXAM_FILE_PATH = DEFAULT_COURSE_FILE_PATH + "/exam";
    // 과제물 저장경로 /pds/report/종류/과목코드/파일명
	public static final String DEFAULT_RTP_ATT_FILE_HTTP = DEFAULT_COURSE_FILE_PATH + "/report";
    // 컨텐츠 파일 절대 경로
    public static final String DEFAULT_CONTENTS_FILE_PATH="/contents";
	public static final String DEFAULT_CONTENTS_FILE_PATH_TMP="/contents/tmp";

    public static final int LECTURE_SAVE_INTERVAL_TIME = Integer.parseInt(PropertySupport.getProperty("LECTURE_SAVE_INTERVAL_TIME", "1"));

	/**
	 * 메일 템플릿 파라메터, 메일발송User정보
	 */
	public static final String MAIL_MEMBER_NAME = "%$MEMBERNAME%$"; // 회원 이름
	public static final String MAIL_MEMBER_ID   = "%$MEMBERID%$";   // 회원ID
	public static final String MAIL_LECNAME     = "$%LECNAME%$";    // 강의명
	public static final String MAIL_LECCODE     = "$%LECCODE%$";    // 강의코드
	public static final String MAIL_DIVPARAM    = "##MAIL##";        // 구분
}
