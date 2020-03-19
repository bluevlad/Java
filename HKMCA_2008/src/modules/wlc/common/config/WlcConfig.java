/*
 * Created on 2004. 9. 13.
 *
 */
package modules.wlc.common.config;

import maf.MafUtil;
import maf.base.BaseObject;

/**
 * @author bluevlad
 * Config ���� ������ �о� ������ �ִ´�.
 */
public class WlcConfig extends BaseObject {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public static final String DES_HASH = "wisevoice1234";

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
     * �������� ���� ���� ��ġ
     */
    public static final String DEFAULT_EXAM_FILE_PATH = "/pds/exam";

    public static final int LECTURE_SAVE_INTERVAL_TIME = Integer.parseInt(WlcConfigSupport.getProperty("lecture_save_interval_time", "1"));

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
    public static final String WEB_DOMAIN = WlcConfigSupport.getProperty("web_domain", "http://test.wisevoice.kr");
    /**
     * ������ ����(�⺻)
     */
    public static final String CONTENTS_SERVER = WlcConfigSupport.getProperty("contents_server", "http://test.wisevoice.kr");
    /**
	 * ������ ���� ���� ���
	 */
    public static final String DEFAULT_CONTENTS_FILE_PATH="/contents";
	public static final String DEFAULT_CONTENTS_FILE_PATH_TMP="/contents/tmp";

	public static final String POPUP_FILE_HTTP = "/pds/popup";
	public static final String POPUP_FILE_PATH = "/pds/popup";

	/**
	 * Debug level 1000: DEBUG <= log4j�� level�� ���� 1000���� ũ�� loggin ����
	 */
	public static final boolean DEBUG = WlcConfigSupport.getBooleanProperty("DEBUG", "FALSE");;

	public static final int DEFAULT_MAX_POST_SIZE = MafUtil.parseInt(WlcConfigSupport.getProperty("MAX_POST_SIZE", "100"));

	public static final String EXCEPTION_PRINT = WlcConfigSupport.getProperty("EXCEPTION_PRINT", "debug");

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
	public static final String WEBMASTER_EMAIL=WlcConfigSupport.getProperty("WEBMASTER_EMAIL", "");



}