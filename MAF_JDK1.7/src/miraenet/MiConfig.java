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
 * Config ���� ������ �о� ������ �ִ´�.
 */
public class MiConfig extends BaseObject {


	/**
	 *
	 */
	private static final long serialVersionUID = 1L;


	public static final String DES_HASH = "kais234";


	public static final String SERVLET_PATH = ConfigSupport.getProperty("SERVLET_PATH", "/ehrd");

	// menu �� jsp�� template ������ default ����Ʈ
	public static final String DEFAULT_SITE = ConfigSupport.getProperty("DEFAULT_SITE","www");

	// ���� resource ���ϵ��� ��ġ
	public static final String MVC_SRC_PATH = ConfigSupport.getProperty("MVC_SRC_PATH","/web_src");

	// ���� ���
	public static final String MVC_SERVLET_PATH = ConfigSupport.getProperty("MVC_SERVLET_PATH","/web");




	// form token key
	public static final String TRANSACTION_TOKEN_KEY = "FORMTOKEN";


	public static final String DEFAULT_CHARACTER_ENCODING = ConfigSupport.getProperty("DEFAULT_CHARACTER_ENCODING","euc-kr");

	public static final int DEFAULT_DBMS = MdbDriver.DBMS_OCI8;


	/**
	 * MAF���� GC�� ���� �Ұ����� ����
	 */
	public static final boolean USE_MAF_GC = ConfigSupport.getBooleanProperty("USE_MAF_GC", "TRUE");;


	public static final String BBS_LIST_URL = "/board/list.do";

    /**
     * ���ǽ� �Խ��� ������ /pds/bbs/�����ڵ�/���ϸ�
     */
    public static final String DEFAULT_BBS_ATT_FILE_HTTP="/pds/bbs";
	/**
	 * ������ ������ /pds/report/����/�����ڵ�/���ϸ�
	 */
	public static final String DEFAULT_RTP_ATT_FILE_HTTP="/pds/report";
    /**
     * ��������Ʈ ������ /pds/project/����/�����ڵ�/���ϸ�
     */
    public static final String DEFAULT_PROJECT_ATT_FILE_HTTP="/pds/project";
    /**
     * ��������Ʈ ������ /pds/project/����/�����ڵ�/���ϸ�
     */
    public static final String DEFAULT_FORUM_ATT_FILE_HTTP="/pds/forum";
    /**
     * �������� ������ /pds/score/����/�����ڵ�/���ϸ�(���� �ǵ�� ����)
     */
    public static final String DEFAULT_SCORE_ATT_FILE_HTTP="/pds/score";
	/**
	 * webFolder ������ ����� ��ġ /pds/webfolder
	 */
	public static final String DEFAULT_WEBFOLDER_FILE_PATH="/pds/webfolder";
	public static final String DEFAULT_FILEMAN_FILE_PATH="/pds/fileman";

    /**
     * �̺�Ʈ ����� �̹���
     */
    public static final String DEFAULT_EVENT_FILE_PATH = "/pds/event";

    /**
     * ������ ���� ���� ��ġ
     */
    public static final String DEFAULT_MALL_FILE_PATH = "/pds/mall";

    /**
     * �������� ���� ���� ��ġ
     */
    public static final String DEFAULT_EXAM_FILE_PATH = "/pds/exam";

    public static final int LECTURE_SAVE_INTERVAL_TIME = Integer.parseInt(ConfigSupport.getProperty("LECTURE_SAVE_INTERVAL_TIME", "1"));

    /**
	 * ���ǰ��� ���� ���� ��ġ
	 */
	public static final String DEFAULT_COURSE_FILE_PATH = "/pds/course";
	/**
	 * ���ǰ�ȹ�� ���� ����Ǵ� ��ġ /pds/course
	 */
	public static final String DEFAULT_SYLLABUS_FILE_PATH = DEFAULT_COURSE_FILE_PATH + "/syllabus";

    /**
     * ���� ���ø� ���� ��� /pds/course
     */
    public static final String DEFAULT_MAIL_TEMPLATE_FILE_PATH = "/pds/mail_template";

    /**
     * �� ������
     */
    public static final String WEB_DOMAIN = ConfigSupport.getProperty("WEB_DOMAIN", "http://www.esunhrd.co.kr");
    /**
     * ������ ����1(�⺻)
     */
    public static final String CONTENTS_SERVER = ConfigSupport.getProperty("CONTENTS_SERVER1", "http://devcontents.esunhrd.co.kr");
    /**
     * ������ ����2(�� ������-JSP ������ ����)
     */
    public static final String CONTENTS_SERVER2 = ConfigSupport.getProperty("CONTENTS_SERVER2", "http://devcontents.esunhrd.co.kr");
    /**
     * ������ ����3(�� ������ ����-�� WLC ������ ���� ����)
     */
    public static final String CONTENTS_SERVER3 = ConfigSupport.getProperty("CONTENTS_SERVER3", "http://devcontents.esunhrd.co.kr");
    /**
     * ������ ����4
     */
    public static final String CONTENTS_SERVER4 = ConfigSupport.getProperty("CONTENTS_SERVER4", "http://devcontents.esunhrd.co.kr");
    /**
     * ������ ����5
     */
    public static final String CONTENTS_SERVER5 = ConfigSupport.getProperty("CONTENTS_SERVER5", "http://devcontents.esunhrd.co.kr");
    /**
	 * ������ ���� ���� ���
	 */
    public static final String DEFAULT_CONTENTS_FILE_PATH="/data/contents";
	public static final String DEFAULT_CONTENTS_FILE_PATH_TMP="D:/App/sks/sun_esunhrd/WebContent/data/contents/tmp";

	public static final String POPUP_FILE_HTTP = "/pds/popup";
	public static final String POPUP_FILE_PATH = "/pds/popup";

	/**
	 * Debug level 1000: DEBUG <= log4j�� level�� ���� 1000���� ũ�� loggin ����
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
	 * ���� ���ø� �Ķ����, ���Ϲ߼�User����
	 *
	 */
	public static final String MAIL_MEMBER_NAME = "%$MEMBERNAME%$"; // ȸ�� �̸�
	public static final String MAIL_MEMBER_ID   = "%$MEMBERID%$";   // ȸ��ID
	public static final String MAIL_LECNAME     = "$%LECNAME%$";    // ���Ǹ�
	public static final String MAIL_LECCODE     = "$%LECCODE%$";    // �����ڵ�
	public static final String MAIL_DIVPARAM    = "##MAIL##";        // ����

	// ������ width x height
	public static final int PHOTO_HEIGHT = 320;
	public static final int PHOTO_WIDTH = 240;

	//����� �����ּ�
	public static final String WEBMASTER_EMAIL=ConfigSupport.getProperty("WEBMASTER_EMAIL", "");

	//���ϸ��� �̹��� ���,width,height
	public static final String MIL_IMG_PATH = "/pds/mileage/img" ;
	public static final int MIL_IMG_W = 100 ;
	public static final int MIL_IMG_H = 130 ;

	//���ϸ��� ������ ���,width,height
	public static final String MIL_ICON_PATH = "/pds/mileage/icon" ;
	public static final int MIL_ICON_W = 30;
	public static final int MIL_ICON_H = 40;

	// DACOM eCredit ����
	public static final String DACOM_ECREDIT_MID = ConfigSupport.getProperty("DACOM_ECREDIT_MID", "");
	public static final String DACOM_ECREDIT_MERT_KEY = ConfigSupport.getProperty("DACOM_ECREDIT_MERT_KEY", "");




//	/**
//	 * ���� ������ DB�� ���� ������ ��� ����"Y" �� ���
//	 */
//	public static final String SESSION_DB_POOL_YN = "N";

	public static final int SESSION_TIMEOUT = Integer.parseInt(ConfigSupport.getProperty("SESSION_TIMEOUT", "30"));

	/**
	 * Session Pool ��� ���� "Y" �� ���
	 */
	public static final boolean SESSION_POOL_YN = ConfigSupport.getBooleanProperty("SESSION_POOL_YN","Y");

	/**
	 * ���� ID�� �α����� ���� ���ϴ� ��� ��� ����"Y" �� ���
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
