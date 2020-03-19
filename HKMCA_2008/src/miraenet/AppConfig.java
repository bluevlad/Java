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
 * Config ���� ������ �о� ������ �ִ´�.
 */
public class AppConfig extends BaseObject {

	public static final String DES_HASH = "maf12345";

    // * �� ������
	public static final String WEB_DOMAIN = PropertySupport.getProperty("WEB_DOMAIN", "http://www.maf.net");
    // * ������ ����
	public static final String CONTENTS_SERVER = PropertySupport.getProperty("CONTENTS_SERVER", "http://www.maf.net");

    // ���ǰ��� ���� ���� ��ġ
	public static final String DEFAULT_COURSE_FILE_PATH = "/pds/course";
	// �������� ���� ���� ��ġ
    public static final String DEFAULT_EXAM_FILE_PATH = DEFAULT_COURSE_FILE_PATH + "/exam";
    // ������ ������ /pds/report/����/�����ڵ�/���ϸ�
	public static final String DEFAULT_RTP_ATT_FILE_HTTP = DEFAULT_COURSE_FILE_PATH + "/report";
    // ������ ���� ���� ���
    public static final String DEFAULT_CONTENTS_FILE_PATH="/contents";
	public static final String DEFAULT_CONTENTS_FILE_PATH_TMP="/contents/tmp";

    public static final int LECTURE_SAVE_INTERVAL_TIME = Integer.parseInt(PropertySupport.getProperty("LECTURE_SAVE_INTERVAL_TIME", "1"));

	/**
	 * ���� ���ø� �Ķ����, ���Ϲ߼�User����
	 */
	public static final String MAIL_MEMBER_NAME = "%$MEMBERNAME%$"; // ȸ�� �̸�
	public static final String MAIL_MEMBER_ID   = "%$MEMBERID%$";   // ȸ��ID
	public static final String MAIL_LECNAME     = "$%LECNAME%$";    // ���Ǹ�
	public static final String MAIL_LECCODE     = "$%LECCODE%$";    // �����ڵ�
	public static final String MAIL_DIVPARAM    = "##MAIL##";        // ����
}
