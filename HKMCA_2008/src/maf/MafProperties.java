package maf;

import maf.aam.AAMLoader;
import maf.aam.DefaultAAMLoader;
import maf.base.BaseObject;
import maf.mdb.drivers.MdbDriver;
import maf.util.PropertySupport;

public class MafProperties extends BaseObject {

	// menu �� jsp�� template ������ default ����Ʈ
	public static final String DEFAULT_SITE = PropertySupport.getProperty("DEFAULT_SITE","www");
	
	public static final String DEFAULT_CHARACTER_ENCODING = PropertySupport.getProperty("DEFAULT_CHARACTER_ENCODING","UTF-8");

	public static final boolean DEBUG = PropertySupport.getBooleanProperty("DEBUG", "TRUE");;

	// Session Pool ��� ���� "Y" �� ���
	public static final boolean SESSION_POOL_YN = PropertySupport.getBooleanProperty("SESSION_POOL_YN","Y");

	// ���� ������ DB�� ���� ������ ��� ����"Y" �� ���
	public static final String SESSION_DB_POOL_YN = "N";

	// MAF���� GC�� ���� �Ұ����� ����
	public static final boolean USE_MAF_GC = PropertySupport.getBooleanProperty("USE_MAF_GC", "TRUE");;

	//	Default for Database pool
	public static final String DEFAULT_DB_POOL_NAME = PropertySupport.getProperty("DB.POOL.NAME", "jdbc/mafdb");
	public static final int DEFAULT_DBMS = MdbDriver.DBMS_OCI8;
	public static final String DEFAULT_DB_POOL_TYPE = PropertySupport.getProperty("DB.POOL.TYPE", "JNDI");

	public static final int SESSION_TIMEOUT = Integer.parseInt(PropertySupport.getProperty("SESSION_TIMEOUT", "30"));

	// SmartConnectin use Y/N
	public static final boolean USE_SMARTCON = PropertySupport.getBooleanProperty("USE_SMARTCON", "TRUE");

	// ���� ID�� �α����� ���� ���ϴ� ��� ��� ����"Y" �� ���
	public static final boolean SESSION_UNIQID_YN = PropertySupport.getBooleanProperty("SESSION_UNIQID_YN","N");

	// ���� ���
	public static final String MVC_SERVLET_PATH = PropertySupport.getProperty("MVC_SERVLET_PATH","/web");

	public static final String SMTP_SERVER = PropertySupport.getProperty("SMTP_SERVER", "127.0.0.1");
	public static final String SMTP_SERVER_USER = PropertySupport.getProperty("SMTP_SERVER_USER", "develop");
	public static final String SMTP_SERVER_PASSWD = PropertySupport.getProperty("SMTP_SERVER_PASSWD", "qwer12");

	// ���� ������ �ϰٴ��� ���� 
	public static final boolean USE_SERVICE_TRACE_YN = PropertySupport.getBooleanProperty("USE_SERVICE_TRACE_YN","N");
	
	// Conenct ���� Log�� ���� ������.
	public static final boolean LOG_CONNECT_YN = PropertySupport.getBooleanProperty("LOG_CONNECT_YN","N");

	// �ٱ��� Menu BUndle Name
	public static final String MAF_MENU_BUNDLE_NAME = PropertySupport.getProperty("MAF_MENU_BUNDLE_NAME","menu");

	// ���� resource ���ϵ��� ��ġ
	public static final String MVC_SRC_PATH = PropertySupport.getProperty("MVC_SRC_PATH","/web_src");

	public static final String FORMAT_MMDD = PropertySupport.getProperty("FORMAT_MMDD","MM.dd");
	public static final String FORMAT_SHORT_DATE = PropertySupport.getProperty("FORMAT_SHORT_DATE","yy.MM.dd");
	public static final String FORMAT_FULL_DATE = PropertySupport.getProperty("FORMAT_FULL_DATE","yyyy.MM.dd");
	public static final String FORMAT_SHORT_DATE_TIME = PropertySupport.getProperty("FORMAT_SHORT_DATE_TIME","yy.MM.dd HH:mm");
	public static final String FORMAT_FULL_DATE_TIME = PropertySupport.getProperty("FORMAT_FULL_DATE_TIME","yyyy.MM.dd HH:mm");

	// form token key
	public static final String TRANSACTION_TOKEN_KEY = "FORMTOKEN";

	public static AAMLoader getAAMLoader() {
    	AAMLoader obj = DefaultAAMLoader.getInstance();
    	return obj;
    }
    
}